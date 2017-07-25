package com.ylife.form.model;

import java.math.BigDecimal;
import java.util.List;

public class CustomerFormStatistic {
    //企业名称
    private String enterpriseName;

    private  Long enterpriseId;
    //是否是底级网点
    private Boolean end;
    //会员属性
    private String customerRef;
    //用户数量
    private Integer customerAmount;
    //发放用户数量
    private Integer grandCustomerAmount;
    //发放金额
    private BigDecimal grandPrice;
    //扣减用户数量
    private Integer decutCustomerAmount;
    //扣减金额
    private BigDecimal decutPrice;
    //兑换用户数量
    private Integer consumeCustomerAmount;
    //兑换金额
    private BigDecimal consumePrice;
    //补贴用户数量
    private Integer consumeSubsidyCustomerAmount;
    //补贴金额
    private BigDecimal consumeSubsidyPrice;
    //兑换结算金额
    private BigDecimal consumeSettlePrice;
    //退款结算金额
    private BigDecimal refundSettlePrice;
    //当前余额
    private BigDecimal currentPrice;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName == null ? null : enterpriseName.trim();
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public String getCustomerRef() {
        return customerRef;
    }

    public void setCustomerRef(String customerRef) {
        this.customerRef = customerRef == null ? null : customerRef.trim();
    }

    public Integer getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(Integer customerAmount) {
        this.customerAmount = customerAmount;
    }

    public Integer getGrandCustomerAmount() {
        return grandCustomerAmount;
    }

    public void setGrandCustomerAmount(Integer grandCustomerAmount) {
        this.grandCustomerAmount = grandCustomerAmount;
    }

    public BigDecimal getGrandPrice() {
        return grandPrice;
    }

    public void setGrandPrice(BigDecimal grandPrice) {
        this.grandPrice = grandPrice;
    }

    public Integer getDecutCustomerAmount() {
        return decutCustomerAmount;
    }

    public void setDecutCustomerAmount(Integer decutCustomerAmount) {
        this.decutCustomerAmount = decutCustomerAmount;
    }

    public BigDecimal getDecutPrice() {
        return decutPrice;
    }

    public void setDecutPrice(BigDecimal decutPrice) {
        this.decutPrice = decutPrice;
    }

    public Integer getConsumeCustomerAmount() {
        return consumeCustomerAmount;
    }

    public void setConsumeCustomerAmount(Integer consumeCustomerAmount) {
        this.consumeCustomerAmount = consumeCustomerAmount;
    }

    public BigDecimal getConsumePrice() {
        return consumePrice;
    }

    public void setConsumePrice(BigDecimal consumePrice) {
        this.consumePrice = consumePrice;
    }

    public Integer getConsumeSubsidyCustomerAmount() {
        return consumeSubsidyCustomerAmount;
    }

    public void setConsumeSubsidyCustomerAmount(Integer consumeSubsidyCustomerAmount) {
        this.consumeSubsidyCustomerAmount = consumeSubsidyCustomerAmount;
    }

    public BigDecimal getConsumeSubsidyPrice() {
        return consumeSubsidyPrice;
    }

    public void setConsumeSubsidyPrice(BigDecimal consumeSubsidyPrice) {
        this.consumeSubsidyPrice = consumeSubsidyPrice;
    }

    public BigDecimal getConsumeSettlePrice() {
        return consumeSettlePrice;
    }

    public void setConsumeSettlePrice(BigDecimal consumeSettlePrice) {
        this.consumeSettlePrice = consumeSettlePrice;
    }

    public BigDecimal getRefundSettlePrice() {
        return refundSettlePrice;
    }

    public void setRefundSettlePrice(BigDecimal refundSettlePrice) {
        this.refundSettlePrice = refundSettlePrice;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public List<CustomerFormStatistic> getList() {
        return list;
    }

    public void setList(List<CustomerFormStatistic> list) {
        this.list = list;
    }

    private List<CustomerFormStatistic> list;
}