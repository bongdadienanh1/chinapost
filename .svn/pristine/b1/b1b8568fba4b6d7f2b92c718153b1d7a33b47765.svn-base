package com.ylife.cache.interceptor;

import com.ylife.cache.CacheObject;
import com.ylife.cache.NirvanaCacheConfiguration;
import com.ylife.cache.CurrentOperation;
import com.ylife.cache.DataWrapper;
import com.ylife.cache.annotation.lifecircle.CacheCreate;
import com.ylife.cache.exception.CacheAnnotationParseException;
import com.ylife.cache.exception.VersionConflictException;
import com.ylife.utils.Assert;
import com.ylife.utils.Null;
import com.ylife.utils.NullSafeUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by InThEnd on 2017/1/18.
 * CacheCreateInterceptor
 */
public class CacheCreateInterceptor extends TransactionAwareCacheInterceptor {

    @Override
    final void beforeOperation(Object key, BridgeObject bridgeObject) {
    }

    @Override
    void afterSuccess(Object key, Object value, BridgeObject bridgeObject) {
        Assert.notNull("创建方法返回结果不能为null.");
        boolean success = false;
        int i = 0;
        while (!success) {
            try {
                DataWrapper dataWrapper = configuration.getCacheManager().get(key);
                CurrentOperation operation = configuration.obtainOperation(CurrentOperation.Operation.CREATE);
                if (dataWrapper != null) {
                    CacheObject cacheObject = (CacheObject) dataWrapper.getData();
                    cacheObject.operationCross(operation, value);
                    configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                } else {
                    CacheObject cacheObject = CacheObject.virtualObject();
                    cacheObject.operationCross(operation, value);
                    configuration.getCacheManager().save(key, cacheObject, configuration.obtainLiveTimes(cacheObject));
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


    }

    @Override
    void afterFailure(Object key, BridgeObject bridgeObject) {
        //do nothing.
    }

    @Override
    CurdCacheContext parseContext(Method method, Class<?> targetClass) {
        CurdCacheContext context = new CurdCacheContext();
        CacheCreate cacheCreate = null;
        List<CacheCreate> annotations = getAnnotations(method, targetClass, CacheCreate.class);
        if (!NullSafeUtils.isEmpty(annotations)) {
            cacheCreate = annotations.get(0);
        }
        if (cacheCreate != null) {
            Class<?> clazz = cacheCreate.clazz();
            Class<?> returnClass = method.getReturnType();
            if (clazz == Null.class) {
                clazz = returnClass;
            }
            if (!returnClass.isAssignableFrom(clazz)) {
                throw new CacheAnnotationParseException("要缓存的类型和方法返回值的类型不匹配。");
            }
            context.setClazz(clazz);
            context.setKeyProperty(cacheCreate.keyProperty());
        }
        return context;
    }
}
