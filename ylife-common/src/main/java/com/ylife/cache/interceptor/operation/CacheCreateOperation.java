package com.ylife.cache.interceptor.operation;

import com.ylife.cache.annotation.lifecircle.CacheCreate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by InThEnd on 2017/2/24.
 */
public class CacheCreateOperation extends AbstractAnnotationAwareCacheOperation {

    private static final String NAME = "CACHE_CURD_CREATE";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected Class<? extends Annotation> annotationClass() {
        return CacheCreate.class;
    }

}
