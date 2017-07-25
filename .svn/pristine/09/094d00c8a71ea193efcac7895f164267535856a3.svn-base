package com.ylife.form.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/8.
 */
public class FormTime {

    //发放邮豆金额
    private BigDecimal sumPrice;
    //总营销邮豆邮豆
    private BigDecimal sumMarketPrice;
    //促销邮豆金额
    private BigDecimal sumSalePrice;
    //支出笔数
    private Long expenditure;
    //新增会员数量
    private Long newCustomerAmount;

    private String everyDay;

    public String getEveryDay() {
        return everyDay;
    }

    public void setEveryDay(String everyDay) {
        this.everyDay = everyDay;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public BigDecimal getSumMarketPrice() {
        return sumMarketPrice;
    }

    public void setSumMarketPrice(BigDecimal sumMarketPrice) {
        this.sumMarketPrice = sumMarketPrice;
    }

    public BigDecimal getSumSalePrice() {
        return sumSalePrice;
    }

    public void setSumSalePrice(BigDecimal sumSalePrice) {
        this.sumSalePrice = sumSalePrice;
    }

    public Long getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Long expenditure) {
        this.expenditure = expenditure;
    }

    public Long getNewCustomerAmount() {
        return newCustomerAmount;
    }

    public void setNewCustomerAmount(Long newCustomerAmount) {
        this.newCustomerAmount = newCustomerAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormTime)) return false;

        FormTime formTime = (FormTime) o;

        if (everyDay != null ? !everyDay.equals(formTime.everyDay) : formTime.everyDay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return everyDay != null ? everyDay.hashCode() : 0;
    }
}
