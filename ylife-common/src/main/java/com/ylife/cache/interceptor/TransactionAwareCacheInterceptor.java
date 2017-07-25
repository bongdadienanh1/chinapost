package com.ylife.cache.interceptor;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by InThEnd on 2017/1/16.
 * TransactionAwareCacheInterceptor
 */
public abstract class TransactionAwareCacheInterceptor extends AbstractSwitchableCurdInterceptor {

    @Override
    public Object process(MethodInvocation invocation) throws Throwable {
        final Object key = getCacheKey(invocation.getMethod(), invocation.getThis().getClass(), invocation.getArguments());
        final BridgeObject bridgeObject = new BridgeObject();
        beforeOperation(key, bridgeObject);
        try {
            final Object object = invocation.proceed();
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCompletion(int status) {
                        if (status == TransactionSynchronization.STATUS_COMMITTED) {
                            afterSuccess(key, object, bridgeObject);
                        }
                    }
                });
            } else {
                afterSuccess(key, object, bridgeObject);
            }
            return object;
        } catch (Throwable throwable) {
            if (TransactionSynchronizationManager.isSynchronizationActive()) {
                TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
                    @Override
                    public void afterCompletion(int status) {
                        if (status == TransactionSynchronization.STATUS_ROLLED_BACK) {
                            afterFailure(key, bridgeObject);
                        }
                    }
                });
            } else {
                afterFailure(key, bridgeObject);
            }
            throw throwable;
        }
    }

    abstract void beforeOperation(Object key, BridgeObject bridgeObject);

    abstract void afterSuccess(Object key, Object value, BridgeObject bridgeObject);

    abstract void afterFailure(Object key, BridgeObject bridgeObject);

    class BridgeObject {

        private Object msg;

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
            this.msg = msg;
        }
    }

}
