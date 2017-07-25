package com.ylife.cache.interceptor;

import com.ylife.cache.CacheObject;
import com.ylife.cache.NirvanaCacheConfiguration;
import com.ylife.cache.CurrentOperation;
import com.ylife.cache.DataWrapper;
import com.ylife.cache.exception.VersionConflictException;
import com.ylife.utils.Assert;

/**
 * Created by InThEnd on 2017/1/17.
 * AbstractCacheEditInterceptor
 */
public abstract class AbstractCacheEditInterceptor extends TransactionAwareCacheInterceptor {

    /**
     * 获取要塞入CacheObject的值。<br/>
     * 如果是{@link com.ylife.cache.CurrentOperation.Operation#UPDATE},直接返回value。<br/>
     * 如果是{@link com.ylife.cache.CurrentOperation.Operation#DELETE},返回null。
     *
     * @see #getOperation()
     * @see CacheObject
     */
    private Object obtainValue(Object value) {
        CurrentOperation.Operation operation = getOperation();
        Assert.notNull(operation);
        switch (operation) {
            case UPDATE:
                return value;
            case DELETE:
                return null;
            default:
                return null;
        }
    }

    /**
     * 返回操作类型。此类型决定{@link AbstractCacheEditInterceptor}的行为。
     *
     * @see com.ylife.cache.CurrentOperation.Operation
     */
    public abstract CurrentOperation.Operation getOperation();

    @Override
    void beforeOperation(Object key, BridgeObject bridgeObject) {
        boolean success = false;
        int i = 0;
        while (!success) {
            try {
                DataWrapper dataWrapper = configuration.getCacheManager().get(key);
                CurrentOperation operation = configuration.obtainOperation(getOperation());
                bridgeObject.setMsg(operation);
                if (dataWrapper != null) {
                    CacheObject cacheObject = (CacheObject) dataWrapper.getData();
                    cacheObject.operationBegin(operation);
                    configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                } else {
                    CacheObject cacheObject = CacheObject.virtualObject();
                    cacheObject.operationBegin(operation);
                    configuration.getCacheManager().save(key, cacheObject, configuration.obtainLiveTimes(cacheObject));
                }
                success = true;
            } catch (VersionConflictException e) {
                if (i < configuration.getRetryTimes()) {
                    i++;
                } else {
                    throw new RuntimeException("缓存写入失败，重试次数超过限制次数：" + configuration.getRetryTimes());
                }
            }
        }
    }

    @Override
    void afterSuccess(Object key, Object value, BridgeObject bridgeObject) {
        boolean success = false;
        int i = 0;
        while (!success) {
            try {
                DataWrapper dataWrapper = configuration.getCacheManager().get(key);
                CacheObject cacheObject = (CacheObject) dataWrapper.getData();
                cacheObject.operationSuccess((CurrentOperation) bridgeObject.getMsg(), obtainValue(value));
                configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                success = true;
            } catch (VersionConflictException e) {
                if (i < configuration.getRetryTimes()) {
                    i++;
                } else {
                    throw new RuntimeException("缓存写入失败，重试次数超过限制次数：" + configuration.getRetryTimes());
                }
            }
        }
    }

    @Override
    void afterFailure(Object key, BridgeObject bridgeObject) {
        boolean success = false;
        int i = 0;
        while (!success) {
            try {
                DataWrapper dataWrapper = configuration.getCacheManager().get(key);
                CacheObject cacheObject = (CacheObject) dataWrapper.getData();
                cacheObject.operationFailure((CurrentOperation) bridgeObject.getMsg());
                configuration.getCacheManager().put(key, cacheObject, dataWrapper.getVersion(), configuration.obtainLiveTimes(cacheObject));
                success = true;
            } catch (VersionConflictException e) {
                if (i < configuration.getRetryTimes()) {
                    i++;
                } else {
                    throw new RuntimeException("缓存写入失败，重试次数超过限制次数：" + configuration.getRetryTimes());
                }
            }
        }
    }

}
