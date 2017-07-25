package com.ylife.cache.interceptor;

import com.ylife.cache.interceptor.operation.CacheOperation;
import com.ylife.utils.Assert;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by InThEnd on 2017/2/27.
 */
public class CacheOperationPointcutAdvisor extends AbstractPointcutAdvisor implements InitializingBean {

    private CacheOperation operation;

    private CacheInterceptor advice;

    private Pointcut pointcut;

    public CacheOperationPointcutAdvisor() {
    }

    public CacheOperationPointcutAdvisor(CacheOperation operation, CacheInterceptor advice) {
        this.operation = operation;
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public CacheOperation getOperation() {
        return operation;
    }

    public void setOperation(CacheOperation operation) {
        Assert.notNull(operation);
        this.operation = operation;
        this.pointcut = new CacheOperationPointCut(operation);
    }

    public void setAdvice(CacheInterceptor advice) {
        Assert.notNull(advice);
        this.advice = advice;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(operation);
        Assert.notNull(pointcut);
        Assert.notNull(advice);
    }
}
