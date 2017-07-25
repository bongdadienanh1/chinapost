package com.ylife.form.model;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/8/5.
 */
public class FormFunc {

    //促销邮豆金额
    private BigDecimal totalMarketPrice;
   //总发放邮豆=营销邮豆+促销邮豆
    private BigDecimal totalPrice;
    //营销邮豆金额
    private BigDecimal totalSalePrice;
    //新增会员数量
    private Long newCustomerAmount;

    private  Long expenditure;

    private Long enterpriseId;
    //发放企业名称
    private String grandEnterprise;

    public Long getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Long expenditure) {
        this.expenditure = expenditure;
    }

    public BigDecimal getTotalMarketPrice() {
        return totalMarketPrice;
    }

    public void setTotalMarketPrice(BigDecimal totalMarketPrice) {
        this.totalMarketPrice = totalMarketPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalSalePrice() {
        return totalSalePrice;
    }

    public void setTotalSalePrice(BigDecimal totalSalePrice) {
        this.totalSalePrice = totalSalePrice;
    }

    public Long getNewCustomerAmount() {
        return newCustomerAmount;
    }

    public void setNewCustomerAmount(Long newCustomerAmount) {
        this.newCustomerAmount = newCustomerAmount;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getGrandEnterprise() {
        return grandEnterprise;
    }

    public void setGrandEnterprise(String grandEnterprise) {
        this.grandEnterprise = grandEnterprise;
    }
}
