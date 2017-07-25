package com.ylife.inventory.model;

import java.util.Date;

public class InventoryHistory {
    protected Long id;

    protected Long enterpriseId;

    protected Long goodsInfoId;

    protected Integer inventoryAmount;

    protected Integer availableAmount;

    protected Integer increaseAmount;

    protected Integer instockAmount;

    protected Integer transferIn;

    protected Integer orderBack;

    protected Integer consumeAmount;

    protected Integer transferOut;

    protected Integer orderConsume;

    protected Integer gainsAmount;

    protected Integer lessReport;

    protected Integer moreReport;

    protected Date recordTime;

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

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getInventoryAmount() {
        return inventoryAmount;
    }

    public void setInventoryAmount(Integer inventoryAmount) {
        this.inventoryAmount = inventoryAmount;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    public Integer getIncreaseAmount() {
        return increaseAmount;
    }

    public void setIncreaseAmount(Integer increaseAmount) {
        this.increaseAmount = increaseAmount;
    }

    public Integer getInstockAmount() {
        return instockAmount;
    }

    public void setInstockAmount(Integer instockAmount) {
        this.instockAmount = instockAmount;
    }

    public Integer getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(Integer transferIn) {
        this.transferIn = transferIn;
    }

    public Integer getOrderBack() {
        return orderBack;
    }

    public void setOrderBack(Integer orderBack) {
        this.orderBack = orderBack;
    }

    public Integer getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Integer consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public Integer getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(Integer transferOut) {
        this.transferOut = transferOut;
    }

    public Integer getOrderConsume() {
        return orderConsume;
    }

    public void setOrderConsume(Integer orderConsume) {
        this.orderConsume = orderConsume;
    }

    public Integer getGainsAmount() {
        return gainsAmount;
    }

    public void setGainsAmount(Integer gainsAmount) {
        this.gainsAmount = gainsAmount;
    }

    public Integer getLessReport() {
        return lessReport;
    }

    public void setLessReport(Integer lessReport) {
        this.lessReport = lessReport;
    }

    public Integer getMoreReport() {
        return moreReport;
    }

    public void setMoreReport(Integer moreReport) {
        this.moreReport = moreReport;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
}