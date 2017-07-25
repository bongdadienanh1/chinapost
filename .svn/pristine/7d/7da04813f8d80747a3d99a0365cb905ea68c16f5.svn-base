package com.ylife.cache.interceptor;

import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by InThEnd on 2017/2/27.
 */
public class SimplePrintCacheInterceptor implements CacheInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        System.out.println(methodName + ": before cache!");
        Object result = invocation.proceed();
        System.out.println(methodName + ": after cache!");
        return result;
    }
}
