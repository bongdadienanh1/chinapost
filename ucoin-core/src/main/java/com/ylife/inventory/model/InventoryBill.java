package com.ylife.inventory.model;

import java.util.Date;
import java.util.List;

public class InventoryBill {

    private Long billId;

    private Long code;

    private Long creatorId;

    private Date createTime;

    private Date recipientTime;

    private Date applyTime;

    private Date stockTime;

    private Long currentId;

    private BillType billType;

    private BillStatus billStatus;

    private String reason;

    private Long outId;

    private Long inId;

    private Boolean readFlag;

    private Long parentId;

    private Long purchaseId;
    private List<WarnDay> warnDay;

    public List<WarnDay> getWarnDay() {
        return warnDay;
    }

    public void setWarnDay(List<WarnDay> warnDay) {
        this.warnDay = warnDay;
    }

    public Boolean getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(Boolean readFlag) {
        this.readFlag = readFlag;
    }

    private TransferInfo tansferInfo;

    private List<InventoryBillItem> items;

    private List<InventoryBillLog> logs;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getRecipientTime() {
        return recipientTime;
    }

    public void setRecipientTime(Date recipientTime) {
        this.recipientTime = recipientTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public TransferInfo getTansferInfo() {
        return tansferInfo;
    }

    public void setTansferInfo(TransferInfo tansferInfo) {
        this.tansferInfo = tansferInfo;
    }

    public List<InventoryBillItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryBillItem> items) {
        this.items = items;
    }

    public List<InventoryBillLog> getLogs() {
        return logs;
    }

    public void setLogs(List<InventoryBillLog> logs) {
        this.logs = logs;
    }

    public Date getStockTime() {
        return stockTime;
    }

    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public static class TransferInfo {

        private String inName;
        //收货公司联系人
        private String consignMan;
        //收货人联系电话
        private String consignMobile;
        //收货人联系地址
        private String consignAddress;

        public String getConsignMan() {
            return consignMan;
        }

        public void setConsignMan(String consignMan) {
            this.consignMan = consignMan;
        }

        public String getConsignMobile() {
            return consignMobile;
        }

        public void setConsignMobile(String consignMobile) {
            this.consignMobile = consignMobile;
        }

        public String getConsignAddress() {
            return consignAddress;
        }

        public void setConsignAddress(String consignAddress) {
            this.consignAddress = consignAddress;
        }

        private String outName;

        private String creatorName;

        private String currentName;

        public String getInName() {
            return inName;
        }

        public void setInName(String inName) {
            this.inName = inName;
        }

        public String getOutName() {
            return outName;
        }

        public void setOutName(String outName) {
            this.outName = outName;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }
    }


}