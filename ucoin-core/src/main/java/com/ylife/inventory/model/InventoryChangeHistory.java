package com.ylife.inventory.model;

import java.util.Date;


/**
 * 库存变动历史记录
 */
public class InventoryChangeHistory {
    private Long id;

    private Long enterpriseId;

    private Long goodsInfoId;

    private Integer inventoryChangeAmount;

    private Integer inventoryAfterChange;

    private InventoryChangeType inventoryChangeType;

    private Date changeTime;

    private Long code;

    private Long billId;

    private Long orderId;

    private Long backOrderId;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getInventoryAfterChange() {
        return inventoryAfterChange;
    }

    public void setInventoryAfterChange(Integer inventoryAfterChange) {
        this.inventoryAfterChange = inventoryAfterChange;
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

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getInventoryChangeAmount() {
        return inventoryChangeAmount;
    }

    public void setInventoryChangeAmount(Integer inventoryChangeAmount) {
        this.inventoryChangeAmount = inventoryChangeAmount;
    }

    public InventoryChangeType getInventoryChangeType() {
        return inventoryChangeType;
    }

    public void setInventoryChangeType(InventoryChangeType inventoryChangeType) {
        this.inventoryChangeType = inventoryChangeType;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(Long backOrderId) {
        this.backOrderId = backOrderId;
    }
}