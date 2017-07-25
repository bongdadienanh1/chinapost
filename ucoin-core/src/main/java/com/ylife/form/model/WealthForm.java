package com.ylife.form.model;

import java.math.BigDecimal;

/**
 * Created by XiaoBiaoGe on 2017/1/13.
 */
public class WealthForm {

    //邮豆和现金之间的兑换比例
    private final static Integer scale = 1250;

    Long enterpriseId;

    Boolean end;

    String enterpriseName;
    //预拨款
    BigDecimal appropriation;
    //发放邮豆
    BigDecimal grandUdou;
    //扣减邮豆
    BigDecimal decutUdou;
    //补贴邮豆
    BigDecimal subsidyUdou;
    //退还邮豆
    BigDecimal backUdou;
    //剩余邮豆
    BigDecimal remainUdou;
    //剩余现金价值
    BigDecimal remainCash;

    public static Integer getScale() {
        return scale;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

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
        this.enterpriseName = enterpriseName;
    }

    public BigDecimal getAppropriation() {
        return appropriation;
    }

    public void setAppropriation(BigDecimal appropriation) {
        this.appropriation = appropriation;
    }

    public BigDecimal getGrandUdou() {
        return grandUdou;
    }

    public void setGrandUdou(BigDecimal grandUdou) {
        this.grandUdou = grandUdou;
    }

    public BigDecimal getDecutUdou() {
        return decutUdou;
    }

    public void setDecutUdou(BigDecimal decutUdou) {
        this.decutUdou = decutUdou;
    }

    public BigDecimal getSubsidyUdou() {
        return subsidyUdou;
    }

    public void setSubsidyUdou(BigDecimal subsidyUdou) {
        this.subsidyUdou = subsidyUdou;
    }

    public BigDecimal getBackUdou() {
        return backUdou;
    }

    public void setBackUdou(BigDecimal backUdou) {
        this.backUdou = backUdou;
    }

    public BigDecimal getRemainUdou() {
        return remainUdou;
    }

    public void setRemainUdou(BigDecimal remainUdou) {
        this.remainUdou = remainUdou;
    }

    public BigDecimal getRemainCash() {
        return  remainCash;
    }

    public void setRemainCash(BigDecimal remainUdou) {
        remainUdou=remainUdou==null?BigDecimal.ZERO:remainUdou;
        this.remainCash=remainUdou.divide(new BigDecimal(scale), 2, BigDecimal.ROUND_UP);
    }
}
