package com.ylife.cache;

import com.ylife.cache.exception.DataExistedException;
import com.ylife.cache.exception.VersionConflictException;

/**
 * Created by InThEnd on 2017/1/4.
 * CacheManager
 */
public interface CacheManager {

    /**
     * 根据Key值获取，如果不存在，返回null.
     */
    DataWrapper get(Object key);

    /**
     * 保存一个值，无过期时间。
     */
    void put(Object key, Object value);

    /**
     * 保存一个新值,如果key值已存在，抛出DataExistedException
     *
     * @param expiredTime 过期时间。
     * @return 返回保存数据的版本号。
     * @throws DataExistedException 当值已存在时抛出异常。
     */
    int save(Object key, Object value, int expiredTime) throws DataExistedException;

    /**
     * 保存一个值。
     *
     * @param expiredTime 过期时间。
     */
    void put(Object key, Object value, int expiredTime);

    /**
     * 保存一个值。
     *
     * @param version     当前版本号。
     * @param expiredTime 过期时间。
     * @throws VersionConflictException 当版本冲突之后抛出此异常。
     */
    void put(Object key, Object value, int version, int expiredTime) throws VersionConflictException;

    /**
     * 移除一个值。
     */
    void evict(Object key);


    /**
     * 设置过期时间。
     */
    void setExpiredTime(Object key, int expiredTime);

}
