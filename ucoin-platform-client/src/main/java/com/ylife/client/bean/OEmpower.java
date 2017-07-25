package com.ylife.client.bean;

import java.util.Date;

/**
 * 开放接口--秘钥
 * @author jack chen
 * @version 2.0
 * @since 15/9/1
 */
public class OEmpower {

    /**
     * 权限ID
     */
    private Integer appId;
    /**
     *  权限码
     */
    private String appKey;

    /**
     * 权限用户
     */
    private String appUserName;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态 0：开启 1：禁用
     */
    private String status;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 用户token
     */
    private String token;

    /**
     * token获取时间
     */
    private Date tokenTime;
    /**
     * 平台对应ID
     */
    private Integer mallId;


    public String getToken() {
        return token;
    }

    public OEmpower setToken(String token) {
        this.token = token;
        return this;
    }

    public Date getTokenTime() {
        if (this.tokenTime != null) {
            return new Date(this.tokenTime.getTime());
        }
        return null;
    }

    public OEmpower setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
        return this;
    }

    public String getAppUserName() {
        return appUserName;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public OEmpower setAppUserName(String appUserName) {
        this.appUserName = appUserName;
        return this;
    }

    public Date getBeginTime() {
        if (this.beginTime != null) {
            return new Date(this.beginTime.getTime());
        }
        return null;
    }

    public OEmpower setBeginTime(Date beginTime) {
        if (beginTime != null) {
            Date tEmp = (Date) beginTime.clone();
            if (tEmp != null) {
                this.beginTime = tEmp;
            }
        }
        return this;
    }

    public Date getEndTime() {
        if (this.endTime != null) {
            return new Date(this.endTime.getTime());
        }
        return null;
    }

    public OEmpower setEndTime(Date endTime) {
        if (endTime != null) {
            Date tEmp = (Date) endTime.clone();
            if (tEmp != null) {
                this.endTime = tEmp;
            }
        }
        return this;
    }


    public String getStatus() {
        return status;
    }

    public OEmpower setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public OEmpower setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
