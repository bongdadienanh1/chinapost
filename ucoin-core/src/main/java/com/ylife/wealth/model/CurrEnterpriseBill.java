package com.ylife.wealth.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/8/23.
 * 最近账单
 */
public class CurrEnterpriseBill {

    private Long id;

    private Long code;

    private Integer count;

    private BigDecimal fee;

    private Date createTime;

    private String type;

    private String sendType;

    public CurrEnterpriseBill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }
}
