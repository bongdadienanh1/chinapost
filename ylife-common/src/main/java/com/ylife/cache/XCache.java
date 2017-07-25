package com.ylife.cache;

import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.Queue;

/**
 * Created by InThEnd on 2016/12/30.
 * XCache
 */
public class XCache implements Cache {


    private Queue<Serializable> needEvictKeys;

    private volatile boolean connectionWealth;

    private int default_expired_second = 3600 * 2;

    private int heartbeat_second = 1;


    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object key) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {

    }

    @Override
    public void evict(Object key) {

    }

    @Override
    public void clear() {

    }
}
