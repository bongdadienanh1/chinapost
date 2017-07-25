package com.ylife.enterprise.model;

import java.util.Date;

public class EnterpriseInfoHistory {
    private Long id;

    private Long enterpriseId;

    private String linkMan;

    private String linkMoblie;

    private String linkAddress;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan == null ? null : linkMan.trim();
    }

    public String getLinkMoblie() {
        return linkMoblie;
    }

    public void setLinkMoblie(String linkMoblie) {
        this.linkMoblie = linkMoblie == null ? null : linkMoblie.trim();
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress == null ? null : linkAddress.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}