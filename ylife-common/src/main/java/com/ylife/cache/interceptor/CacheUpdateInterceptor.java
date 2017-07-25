package com.ylife.cache.interceptor;

import com.ylife.cache.CurrentOperation;
import com.ylife.cache.annotation.lifecircle.CacheUpdate;
import com.ylife.utils.Null;
import com.ylife.utils.NullSafeUtils;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by InThEnd on 2017/1/6.
 * CacheUpdateInterceptor
 */
public class CacheUpdateInterceptor extends AbstractCacheEditInterceptor {

    @Override
    public CurrentOperation.Operation getOperation() {
        return CurrentOperation.Operation.UPDATE;
    }

    @Override
    CurdCacheContext parseContext(Method method, Class<?> targetClass) {
        List<CacheUpdate> updates = getAnnotations(method, targetClass, CacheUpdate.class);
        if (NullSafeUtils.isEmpty(updates)) {
            return null;
        }
        CacheUpdate update = updates.get(0);
        Class<?> clazz = update.clazz();
        if (clazz == Null.class) {
            clazz = method.getReturnType();
        }
        CurdCacheContext context = new CurdCacheContext();
        context.setClazz(clazz);
        context.setKeyProperty(update.keyProperty());
        return context;
    }
}
