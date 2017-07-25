package com.ylife.inventory.model;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/16.
 */
public class WarnDay {
    private int id;
    private long code;
    private String goodInfoImgId;
    private String goodINfoName;
    private String remark;
    private String goodInfoItemNo;
    private Date createTime;
    private Date endTime;
    private Date productTime;
    private int endWarnDay;
    private int receiptAmount;
    private String enterpriseName;
    private String thirdName;
    private String brandName;
    private  long catalogs;
    private Integer currentAmount;
    private Integer billId;
    private String billType;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getGoodInfoImgId() {
        return goodInfoImgId;
    }

    public void setGoodInfoImgId(String goodInfoImgId) {
        this.goodInfoImgId = goodInfoImgId;
    }

    public String getGoodINfoName() {
        return goodINfoName;
    }

    public void setGoodINfoName(String goodINfoName) {
        this.goodINfoName = goodINfoName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGoodInfoItemNo() {
        return goodInfoItemNo;
    }

    public void setGoodInfoItemNo(String goodInfoItemNo) {
        this.goodInfoItemNo = goodInfoItemNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public int getEndWarnDay() {
        return endWarnDay;
    }

    public void setEndWarnDay(int endWarnDay) {
        this.endWarnDay = endWarnDay;
    }

    public int getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(int receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public long getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(long catalogs) {
        this.catalogs = catalogs;
    }
}
