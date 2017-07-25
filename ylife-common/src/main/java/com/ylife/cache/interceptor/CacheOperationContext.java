package com.ylife.cache.interceptor;

import com.ylife.cache.exception.CacheException;
import com.ylife.cache.interceptor.operation.CacheOperation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.*;

/**
 * Created by InThEnd on 2017/2/22.
 */
public class CacheOperationContext {

    private BeanDefinitionRegistry registry;

    private Set<String> operationNames = new HashSet<>();

    private Set<CacheOperation> cacheOperations = new HashSet<>();

    private Map<String, CacheInterceptor> interceptorMap = new HashMap<>();

    private final Object lock = new Object();

    private static final String ADVISOR_NAME_PREFIX = "com.nirvana.cache.defaultAdvisor";

    private int index = 1;

    public CacheOperationContext(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    private void registerAdvisor(CacheOperation operation, CacheInterceptor interceptor) {
        RootBeanDefinition advisorDef = new RootBeanDefinition(CacheOperationPointcutAdvisor.class);
        advisorDef.getPropertyValues().add("operation", operation);
        advisorDef.getPropertyValues().add("advice", interceptor);
        advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        registry.registerBeanDefinition(ADVISOR_NAME_PREFIX + index, advisorDef);
        index++;
    }


    public void register(CacheOperation operation, CacheInterceptor interceptor) {
        synchronized (lock) {
            String operationName = operation.getName();
            if (operationNames.contains(operationName)) {
                throw new CacheException("注册失败，已存在名称为： " + operation.getName() + " 的Operation.");
            }
            operationNames.add(operationName);
            cacheOperations.add(operation);
            interceptorMap.put(operationName, interceptor);
            registerAdvisor(operation, interceptor);
        }
    }

    public CacheInterceptor getInterceptor(String operationName) {
        synchronized (lock) {
            return interceptorMap.get(operationName);
        }
    }

    public Collection<CacheOperation> getCacheOperations() {
        return cacheOperations;
    }

    public Collection<String> getOperationNames() {
        return operationNames;
    }

}
