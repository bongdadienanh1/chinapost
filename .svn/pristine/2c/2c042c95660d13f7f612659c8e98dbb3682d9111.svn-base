package com.ylife.cache.interceptor.operation;

import com.ylife.cache.annotation.MethodAnnotationSource;
import com.ylife.cache.annotation.SimpleSpecificMethodAnnotationSource;
import com.ylife.cache.annotation.lifecircle.CacheCreate;

import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by InThEnd on 2017/2/27.
 */
public class TrueCacheOperation implements CacheOperation {

    private MethodAnnotationSource annotationSource = new SimpleSpecificMethodAnnotationSource();

    @Override
    public String getName() {
        return "TRUE";
    }

    @Override
    public boolean decide(Method method, Class<?> targetClass) {
        System.out.println("咩咩咩！！！");
        Collection<CacheCreate> annotations = annotationSource.getAnnotations(method, targetClass, CacheCreate.class);
        return !annotations.isEmpty();
    }

}
