package com.ylife.cache;

import com.ylife.utils.Assert;

/**
 * Created by InThEnd on 2017/1/5.
 * cache查询结果包装类。
 */
public class DataWrapper {

    private int version;

    private int expiredTime;

    private Object data;

    /**
     * data不允许为null
     */
    public DataWrapper(Object data, int version, int expiredTime) {
        Assert.notNull(data);
        this.data = data;
        this.version = version;
        this.expiredTime = expiredTime;
    }

    public int getVersion() {
        return version;
    }

    public Object getData() {
        return data;
    }

    public int getExpiredTime() {
        return expiredTime;
    }
}
