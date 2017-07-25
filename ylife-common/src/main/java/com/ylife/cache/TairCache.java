package com.ylife.cache;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.TairManager;
import com.taobao.tair.impl.DefaultTairManager;
import org.springframework.cache.Cache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by InThEnd on 2016/11/16.
 * tair cache
 */
public class TairCache implements Cache {

    private static final String CACHE_NAME = "tair_cache";

    private int namespace;

    private TairManager manager;

    public TairCache(List<String> confServers, String groupName, int namespace) {

        // 创建客户端实例
        DefaultTairManager tairManager = new DefaultTairManager();
        tairManager.setConfigServerList(confServers);
        // 设置组名
        tairManager.setGroupName("group_1");
        // 初始化客户端
        tairManager.init();
        manager = tairManager;
        this.namespace = namespace;
    }

    @Override
    public String getName() {
        return CACHE_NAME;
    }

    @Override
    public Object getNativeCache() {
        return manager;
    }

    @Override
    public ValueWrapper get(Object key) {
        Result<DataEntry> result = manager.get(namespace, (Serializable) key);
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
