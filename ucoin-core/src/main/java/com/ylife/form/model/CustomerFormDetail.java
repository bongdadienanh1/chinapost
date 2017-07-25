package com.ylife.form.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by XiaoBiaoGe on 2017/1/17.
 */
public class CustomerFormDetail {

    //父级机构id
    private Long parentId;
    //当前机构id
    private Long currentId;
    //当前机构的end信息
    private Boolean end;
    //父级机构名称
    private String parentName;
    //当前机构名称
    private String currentName;
    //会员id
    private Long customerId;
    //会员姓名
    private String fullName;
    //会员身份证号
    private String idCardNo;
    //会员属性：新；旧
    private String customerRef;
    //会员创建时间
    private Date createTime;
    //上期余额
    private BigDecimal lastPrice;
    //本期余额
    private BigDecimal currentPrice;
    //发放次数
    private int grandFrequency;
    //发放金额
    private BigDecimal grandPrice;
    //扣减次数
    private int decutFrequency;
    //扣减金额
    private BigDecimal decutPrice;
    //线下兑换次数
    private int consumeFrequencyOffline;
    //线下兑换邮豆
    private BigDecimal consumePriceOffline;
    //线下补贴邮豆
    private BigDecimal consumeSubsidyPriceOffline;
    //线上兑换次数
    private int consumeFrequencyOnline;
    //线上兑换邮豆
    private BigDecimal consumePriceOnline;
    //线上兑换结算价
    private BigDecimal consumeSettlePrice;
    //退款次数
    private int refundFrequency;
    //退款结算价
    private BigDecimal refundSettlePrice;


    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getGrandFrequency() {
        return grandFrequency;
    }

    public void setGrandFrequency(int grandFrequency) {
        this.grandFrequency = grandFrequency;
    }

    public BigDecimal getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(BigDecimal grandPrice) {
        this.grandPrice = grandPrice;
    }

    public int getDecutFrequency() {
        return decutFrequency;
    }

    public void setDecutFrequency(int decutFrequency) {
        this.decutFrequency = decutFrequency;
    }

    public BigDecimal getDecutPrice() {
        return decutPrice;
    }

    public void setDecutPrice(BigDecimal decutPrice) {
        this.decutPrice = decutPrice;
    }

    public int getConsumeFrequencyOffline() {
        return consumeFrequencyOffline;
    }

    public void setConsumeFrequencyOffline(int consumeFrequencyOffline) {
        this.consumeFrequencyOffline = consumeFrequencyOffline;
    }

    public BigDecimal getConsumePriceOffline() {
        return consumePriceOffline;
    }

    public void setConsumePriceOffline(BigDecimal consumePriceOffline) {
        this.consumePriceOffline = consumePriceOffline;
    }

    public BigDecimal getConsumeSubsidyPriceOffline() {
        return consumeSubsidyPriceOffline;
    }

    public void setConsumeSubsidyPriceOffline(BigDecimal consumeSubsidyPriceOffline) {
        this.consumeSubsidyPriceOffline = consumeSubsidyPriceOffline;
    }

    public int getConsumeFrequencyOnline() {
        return consumeFrequencyOnline;
    }

    public void setConsumeFrequencyOnline(int consumeFrequencyOnline) {
        this.consumeFrequencyOnline = consumeFrequencyOnline;
    }

    public BigDecimal getConsumePriceOnline() {
        return consumePriceOnline;
    }

    public void setConsumePriceOnline(BigDecimal consumePriceOnline) {
        this.consumePriceOnline = consumePriceOnline;
    }

    public BigDecimal getConsumeSettlePrice() {
        return consumeSettlePrice;
    }

    public void setConsumeSettlePrice(BigDecimal consumeSettlePrice) {
        this.consumeSettlePrice = consumeSettlePrice;
    }

    public int getRefundFrequency() {
        return refundFrequency;
    }

    public void setRefundFrequency(int refundFrequency) {
        this.refundFrequency = refundFrequency;
    }

    public BigDecimal getRefundSettlePrice() {
        return refundSettlePrice;
    }

    public void setRefundSettlePrice(BigDecimal refundSettlePrice) {
        this.refundSettlePrice = refundSettlePrice;
    }
}
