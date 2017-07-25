package com.ylife.cache.interceptor.operation;

import com.ylife.cache.annotation.lifecircle.CacheRead;

import java.lang.annotation.Annotation;

/**
 * Created by InThEnd on 2017/2/24.
 */
public class CacheReadOperation extends AbstractAnnotationAwareCacheOperation {

    private static final String NAME = "CACHE_CURD_READ";

    @Override
    public String getName() {
        return NAME;
    }


    @Override
    protected Class<? extends Annotation> annotationClass() {
        return CacheRead.class;
    }
}
