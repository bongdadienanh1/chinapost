package com.ylife.client.bean;

import java.util.Date;

public class OpenLog {
    private Long logId;

    private String openType;

    private Date createTime;

    private String logContent;

    private Short logStatus;

    private Long orderCode;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getOpenType() {
        return openType;
    }

    public void setOpenType(String openType) {
        this.openType = openType == null ? null : openType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent == null ? null : logContent.trim();
    }

    public Short getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Short logStatus) {
        this.logStatus = logStatus;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }
}