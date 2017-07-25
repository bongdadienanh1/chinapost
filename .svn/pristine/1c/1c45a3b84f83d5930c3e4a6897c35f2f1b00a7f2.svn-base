package com.ylife.cache.interceptor;

import com.ylife.cache.CurrentOperation;
import com.ylife.cache.annotation.lifecircle.CacheDelete;
import com.ylife.cache.annotation.lifecircle.CacheRead;
import com.ylife.utils.Null;
import com.ylife.utils.NullSafeUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by InThEnd on 2017/1/8.
 * CacheDeleteInterceptor
 */
public class CacheDeleteInterceptor extends AbstractCacheEditInterceptor {

    @Override
    public CurrentOperation.Operation getOperation() {
        return CurrentOperation.Operation.DELETE;
    }

    @Override
    CurdCacheContext parseContext(Method method, Class<?> targetClass) {
        List<CacheDelete> deletes = getAnnotations(method, targetClass, CacheDelete.class);
        if (NullSafeUtils.isEmpty(deletes)) {
            return null;
        }
        CacheDelete cacheDelete = deletes.get(0);
        if (cacheDelete == null) {
            return null;
        }
        Class<?> clazz = cacheDelete.clazz();
        if (clazz == Null.class) {
            clazz = method.getReturnType();
        }
        CurdCacheContext context = new CurdCacheContext();
        context.setClazz(clazz);
        context.setKeyProperty(cacheDelete.keyProperty());
        return context;
    }
}
