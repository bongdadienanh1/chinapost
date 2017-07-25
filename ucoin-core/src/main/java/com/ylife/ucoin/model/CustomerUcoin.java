package com.ylife.ucoin.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/1.
 * U宝余额
 */
public class CustomerUcoin {

    private Long id;

    private Long enterpriseId;

    private Long customerId;

    private BigDecimal resePrice;

    private Date createTime;

    private Date startTime;

    private Date endTime;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getResePrice() {
        return resePrice;
    }

    public void setResePrice(BigDecimal resePrice) {
        this.resePrice = resePrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
