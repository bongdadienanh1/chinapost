package com.ylife.order.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by suibian on 2016/12/29.
 */
public class OrderRefund {
    private Long orderId;
    private String billingType;//账单类型
    private String fullname;//交易人名字
    private String idCardNo;//交易人身份证号
    private BigDecimal subsidyPrice;//返还金额
    private Integer count;//总笔数
    private Date payTime;//支付时间
    private String orderCode;//单号

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public BigDecimal getSubsidyPrice() {
        return subsidyPrice;
    }

    public void setSubsidyPrice(BigDecimal subsidyPrice) {
        this.subsidyPrice = subsidyPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
