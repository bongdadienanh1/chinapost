package com.ylife.cache.interceptor.operation;

import com.ylife.cache.annotation.lifecircle.CacheUpdate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by InThEnd on 2017/2/24.
 */
public class CacheUpdateOperation extends AbstractAnnotationAwareCacheOperation {

    private static final String NAME = "CACHE_CURD_UPDATE";

    @Override
    public String getName() {
        return NAME;
    }


    @Override
    protected Class<? extends Annotation> annotationClass() {
        return CacheUpdate.class;
    }

}
