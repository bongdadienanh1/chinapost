package com.ylife.cache;

import com.ylife.cache.exception.DataExistedException;
import com.ylife.cache.exception.VersionConflictException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2017/3/2.
 * 随便写的。
 */
public class JokeCacheManager implements CacheManager {

    private Map<Object, Object> cache = new ConcurrentHashMap<>();

    @SuppressWarnings("unchecked")
    @Override
    public DataWrapper get(Object key) {
        return (DataWrapper) cache.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, new DataWrapper(value, 0, 0));
    }

    @Override
    public int save(Object key, Object value, int expiredTime) throws DataExistedException {
        if (cache.containsKey(key)) {
            throw new DataExistedException();
        }
        cache.put(key, new DataWrapper(value, 0, expiredTime));
        return 0;
    }

    @Override
    public void put(Object key, Object value, int expiredTime) {
        cache.put(key, new DataWrapper(value, 0, expiredTime));
    }

    @Override
    public void put(Object key, Object value, int version, int expiredTime) throws VersionConflictException {
        DataWrapper dataWrapper = (DataWrapper) cache.get(key);
        if (dataWrapper != null && dataWrapper.getVersion() != version) {
            throw new VersionConflictException();
        }
        cache.put(key, new DataWrapper(value, version + 1, expiredTime));
    }

    @Override
    public void evict(Object key) {
        cache.remove(key);
    }

    @Override
    public void setExpiredTime(Object key, int expiredTime) {
        DataWrapper dataWrapper = (DataWrapper) cache.get(key);
        DataWrapper dataWrapper1 = new DataWrapper(dataWrapper.getData(), dataWrapper.getVersion(), expiredTime);
        cache.put(key, dataWrapper1);
    }
}
