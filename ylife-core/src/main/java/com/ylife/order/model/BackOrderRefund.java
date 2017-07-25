package com.ylife.order.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by suibian on 2016/12/29.
 */
public class BackOrderRefund {
    private Long backorderId;
    private String billingType;//账单类型
    private String fullname;//交易人名字
    private String idCardNo;//交易人身份证号
    private BigDecimal backSubsidyPrice;//返还金额
    private Integer count;//总笔数
    private Date payTime;//支付时间
    private String backOrderCode;//单号

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
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

    public Long getBackorderId() {
        return backorderId;
    }

    public void setBackorderId(Long backorderId) {
        this.backorderId = backorderId;
    }

    public BigDecimal getBackSubsidyPrice() {
        return backSubsidyPrice;
    }

    public void setBackSubsidyPrice(BigDecimal backSubsidyPrice) {
        this.backSubsidyPrice = backSubsidyPrice;
    }

    public String getBackOrderCode() {
        return backOrderCode;
    }

    public void setBackOrderCode(String backOrderCode) {
        this.backOrderCode = backOrderCode;
    }
}
