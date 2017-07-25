package com.ylife.inventory.model;

import java.util.Date;
import java.util.List;

public class InventoryPurchaseBill {
    private Long purchaseId;

    private Long thirdId;

    private Long billId;

    private Long code;

    private PurchaseBillStatus status;

    private String marks;

    private Date createTime;

    private String thirdName;

    private Integer receiptSumAmount;

    private Integer purchaseAmount;

    private List<InventoryPurchaseBillItem> items;

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }


    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks == null ? null : marks.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public PurchaseBillStatus getStatus() {
        return status;
    }

    public void setStatus(PurchaseBillStatus status) {
        this.status = status;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public List<InventoryPurchaseBillItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryPurchaseBillItem> items) {
        this.items = items;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }


    public Integer getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(Integer purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getReceiptSumAmount() {
        return receiptSumAmount;
    }

    public void setReceiptSumAmount(Integer receiptSumAmount) {
        this.receiptSumAmount = receiptSumAmount;
    }
}