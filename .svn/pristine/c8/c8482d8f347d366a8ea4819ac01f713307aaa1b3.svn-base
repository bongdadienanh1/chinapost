package com.ylife.cache.interceptor.operation;

import com.ylife.cache.annotation.MethodAnnotationSource;
import com.ylife.cache.annotation.SimpleSpecificMethodAnnotationSource;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by InThEnd on 2017/3/1.
 */
public abstract class AbstractAnnotationAwareCacheOperation implements CacheOperation {

    private MethodAnnotationSource annotationSource = new SimpleSpecificMethodAnnotationSource();

    @Override
    public boolean decide(Method method, Class<?> targetClass) {
        return !annotationSource.getAnnotations(method, targetClass, annotationClass()).isEmpty();
    }

    protected abstract Class<? extends Annotation> annotationClass();

    public void setAnnotationSource(MethodAnnotationSource annotationSource) {
        this.annotationSource = annotationSource;
    }
}
