package com.ylife.cache.interceptor;

import com.ylife.cache.interceptor.operation.CacheOperation;
import com.ylife.utils.Assert;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * Created by InThEnd on 2017/2/27.
 */
public class CacheOperationPointCut extends StaticMethodMatcherPointcut {

    private CacheOperation operation;

    public CacheOperationPointCut(CacheOperation operation) {
        Assert.notNull(operation);
        this.operation = operation;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return operation.decide(method, targetClass);
    }

    public CacheOperation getOperation() {
        return operation;
    }

    public void setOperation(CacheOperation operation) {
        Assert.notNull(operation);
        this.operation = operation;
    }
}
