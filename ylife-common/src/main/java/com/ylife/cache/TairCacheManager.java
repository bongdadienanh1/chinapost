package com.ylife.cache;

import com.taobao.tair.DataEntry;
import com.taobao.tair.Result;
import com.taobao.tair.ResultCode;
import com.taobao.tair.TairManager;
import com.taobao.tair.impl.DefaultTairManager;
import com.ylife.cache.exception.CacheException;
import com.ylife.cache.exception.CacheManagerException;
import com.ylife.cache.exception.DataExistedException;
import com.ylife.cache.exception.VersionConflictException;
import com.ylife.utils.Assert;
import org.springframework.beans.factory.InitializingBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by InThEnd on 2017/2/25.
 * 淘宝tair的CacheManager实现。
 */
public class TairCacheManager implements CacheManager, InitializingBean {

    private static final int TAIR_DEFAULT_NEW_CACHE_VERSION = 2;

    private static final int TAIR_FORCE_UPDATE_CACHE_VERSION = 0;

    private Integer namespace;

    private TairManager manager;

    private String groupName;

    private List<String> confServers = new ArrayList<>();

    private TairCacheManager() {
    }

    public TairCacheManager(List<String> confServers, String groupName, int namespace) {
        // 创建客户端实例
        DefaultTairManager tairManager = new DefaultTairManager();
        tairManager.setConfigServerList(confServers);
        // 设置组名
        tairManager.setGroupName(groupName);
        // 初始化客户端
        tairManager.init();
        manager = tairManager;
        this.namespace = namespace;
    }

    @Override
    public DataWrapper get(Object key) {
        Result<DataEntry> result = manager.get(namespace, (Serializable) key);
        DataEntry entry = result.getValue();
        if (entry == null) {
            return null;
        }
        return new DataWrapper(entry.getValue(), entry.getVersion(), entry.getExpriedDate());
    }

    @Override
    public void put(Object key, Object value) {
        ResultCode result = manager.put(namespace, (Serializable) key, (Serializable) value, TAIR_FORCE_UPDATE_CACHE_VERSION);
        if (result != ResultCode.SUCCESS) {
            throw new CacheManagerException(result.getMessage());
        }
    }

    @Override
    public int save(Object key, Object value, int expiredTime) throws DataExistedException {
        ResultCode result = manager.put(namespace, (Serializable) key, (Serializable) value, TAIR_DEFAULT_NEW_CACHE_VERSION, expiredTime);
        if (result == ResultCode.SUCCESS) {
            return TAIR_DEFAULT_NEW_CACHE_VERSION;
        }
        if (result == ResultCode.VERERROR) {
            throw new DataExistedException();
        }
        throw new CacheManagerException(result.getMessage());
    }

    @Override
    public void put(Object key, Object value, int expiredTime) {
        ResultCode result = manager.put(namespace, (Serializable) key, (Serializable) value, TAIR_FORCE_UPDATE_CACHE_VERSION, expiredTime);
        if (result == ResultCode.SUCCESS) {
            return;
        }
        throw new CacheManagerException(result.getMessage());
    }

    @Override
    public void put(Object key, Object value, int version, int expiredTime) throws VersionConflictException {
        ResultCode result = manager.put(namespace, (Serializable) key, (Serializable) value, version, expiredTime);
        if (result == ResultCode.SUCCESS) {
            return;
        }
        if (result == ResultCode.VERERROR) {
            throw new VersionConflictException();
        }
        throw new CacheManagerException(result.getMessage());
    }

    @Override
    public void evict(Object key) {
        ResultCode result = manager.delete(namespace, (Serializable) key);
        if (result == ResultCode.SUCCESS) {
            return;
        }
        throw new CacheManagerException(result.getMessage());
    }

    @Override
    public void setExpiredTime(Object key, int expiredTime) {
        throw new CacheException("Not support.");
    }

    public void setNamespace(Integer namespace) {
        this.namespace = namespace;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setConfServers(List<String> confServers) {
        this.confServers = confServers;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(namespace);
        Assert.notNull(groupName);
        Assert.notEmpty(confServers);
        // 创建客户端实例
        DefaultTairManager tairManager = new DefaultTairManager();
        tairManager.setConfigServerList(confServers);
        // 设置组名
        tairManager.setGroupName(groupName);
        // 初始化客户端
        tairManager.init();
        manager = tairManager;
    }
}
