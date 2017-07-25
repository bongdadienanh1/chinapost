package com.ylife.cache.interceptor;

import com.ylife.cache.CacheObject;
import com.ylife.cache.DataWrapper;
import com.ylife.cache.annotation.lifecircle.CacheRead;
import com.ylife.cache.exception.VersionConflictException;
import com.ylife.utils.Null;
import com.ylife.utils.NullSafeUtils;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by InThEnd on 2017/1/6.
 * CacheReadInterceptor
 */
public class CacheReadInterceptor extends AbstractSwitchableCurdInterceptor {

    @Override
    public Object process(MethodInvocation invocation) throws Throwable {

        Object key = getCacheKey(invocation.getMethod(), invocation.getThis().getClass(), invocation.getArguments());
        Object result = null;
        boolean success = false;
        int i = 0;
        while (!success) {
            try {
                DataWrapper dataWrapper = configuration.getCacheManager().get(key);
                CacheObject cacheObject;
                if (dataWrapper == null) {
                    cacheObject = CacheObject.virtualObject();
                    int version = configuration.getCacheManager().save(key, cacheObject, configuration.obtainLiveTimes(cacheObject));
                    result = invocation.proceed();
                    cacheObject.setValue(result);
                    configuration.getCacheManager().put(key, cacheObject, version, configuration.obtainLiveTimes(cacheObject));
                } else {
                    cacheObject = (CacheObject) dataWrapper.getData();
                    cacheObject.removeDeathOperation(configuration.getOperationLivesSecond());
                    if (cacheObject.needRefresh()) {
                        result = invocation.proceed();
                        cacheObject.refresh(result);
                        configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                    } else {
                        if (cacheObject.getValueWrapper() == null) {
                            result = invocation.proceed();
                            cacheObject.setValue(result);
                            configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                        } else {
                            result = cacheObject.getValueWrapper().getValue();
                        }
                    }

                }
                success = true;
            } catch (VersionConflictException e) {
                if (i < configuration.getRetryTimes()) {
                    i++;
                } else {
                    throw new RuntimeException("缓存写入失败，重试次数超过限制次数：" + configuration.getRetryTimes());
                }
            }
        }
        return result;
    }

    @Override
    CurdCacheContext parseContext(Method method, Class<?> targetClass) {
        List<CacheRead> cacheReads = getAnnotations(method, targetClass, CacheRead.class);
        if (NullSafeUtils.isEmpty(cacheReads)) {
            return null;
        }
        CacheRead cacheRead = cacheReads.get(0);
        if (cacheRead == null) {
            return null;
        }
        Class<?> clazz = cacheRead.clazz();
        if (clazz == Null.class) {
            clazz = method.getReturnType();
        }
        CurdCacheContext context = new CurdCacheContext();
        context.setClazz(clazz);
        context.setKeyProperty(cacheRead.keyProperty());
        return context;
    }
}