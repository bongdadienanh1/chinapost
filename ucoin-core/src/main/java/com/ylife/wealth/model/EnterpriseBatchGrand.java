package com.ylife.wealth.model;

import java.math.BigDecimal;
import java.util.Date;

public class EnterpriseBatchGrand {

    private Long id;

    private Long enterpriseId;

    private Long code;

    private BigDecimal fee;

    private String sendType;

    private Date createTime;

    private String remark;

    private Integer ucoinCount;

    private String grandType;

    private BigDecimal ucoinPrice;

    private Integer typeId;

    private Long batchCode;

    private String managerName;

    private Long managerId;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

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

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUcoinCount() {
        return ucoinCount;
    }

    public void setUcoinCount(Integer ucoinCount) {
        this.ucoinCount = ucoinCount;
    }

    public String getGrandType() {
        return grandType;
    }

    public void setGrandType(String grandType) {
        this.grandType = grandType == null ? null : grandType.trim();
    }

    public BigDecimal getUcoinPrice() {
        return ucoinPrice;
    }

    public void setUcoinPrice(BigDecimal ucoinPrice) {
        this.ucoinPrice = ucoinPrice;
    }

    public Long getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(Long batchCode) {
        this.batchCode = batchCode;
    }
}