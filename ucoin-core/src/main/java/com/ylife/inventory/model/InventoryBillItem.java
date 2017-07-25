package com.ylife.inventory.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InventoryBillItem {

    private Long itemId;

    private Long billId;

    private Long goodsInfoId;

    private Integer amount;

    private Integer checkedAmount;
    //收货数量，此处指已经到货的数量
    private Integer receiptAmount;

    private String goodsInfoUnit;
    //装箱数
    private BigDecimal goodsInfoPack;

    private ItemGoodsInfo info;

    private BigDecimal settlePrice;

    private Long purchaseId;

    private BigDecimal purchasePrice;
    private Date productTime;
    private Date endTime;
    private  int endWarnDay;
    private List<WarnDay> warnDay;

    public List<WarnDay> getWarnDay() {
        return warnDay;
    }

    public void setWarnDay(List<WarnDay> warnDay) {
        this.warnDay = warnDay;
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getEndWarnDay() {
        return endWarnDay;
    }

    public void setEndWarnDay(int endWarnDay) {
        this.endWarnDay = endWarnDay;
    }


    public String getGoodsInfoUnit() {
        return goodsInfoUnit;
    }

    public void setGoodsInfoUnit(String goodsInfoUnit) {
        this.goodsInfoUnit = goodsInfoUnit;
    }

    public BigDecimal getGoodsInfoPack() {
        return goodsInfoPack;
    }

    public void setGoodsInfoPack(BigDecimal goodsInfoPack) {
        this.goodsInfoPack = goodsInfoPack;
    }

    public Integer getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public ItemGoodsInfo getInfo() {
        return info;
    }

    public void setInfo(ItemGoodsInfo info) {
        this.info = info;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Integer checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public static class ItemGoodsInfo {
        private Long goodsInfoId;

        private String goodsInfoName;

        private String goodsSpecification;
        //货品规格值
        private String goodsSpecValue;
        //规格字符串
        private String specString;

        private String goodsInfoItemNo;

        private Integer goodsInfoType;

        private Integer inventory;

        private Integer available;

        //结算价
        private BigDecimal settlePrice;

        //商家id
        private Long thirdId;

        //商家名称
        private String thirdName;


        public Integer getGoodsInfoType() {
            return goodsInfoType;
        }

        public void setGoodsInfoType(Integer goodsInfoType) {
            this.goodsInfoType = goodsInfoType;
        }

        public Long getThirdId() {
            return thirdId;
        }

        public void setThirdId(Long thirdId) {
            this.thirdId = thirdId;
        }

        public String getThirdName() {
            return thirdName;
        }

        public void setThirdName(String thirdName) {
            this.thirdName = thirdName;
        }

        public BigDecimal getSettlePrice() {
            return settlePrice;
        }

        public void setSettlePrice(BigDecimal settlePrice) {
            this.settlePrice = settlePrice;
        }

        public Long getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(Long goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public Integer getInventory() {
            return inventory;
        }

        public void setInventory(Integer inventory) {
            this.inventory = inventory;
        }

        public Integer getAvailable() {
            return available;
        }

        public void setAvailable(Integer available) {
            this.available = available;
        }

        public String getGoodsInfoName() {
            return goodsInfoName;
        }

        public void setGoodsInfoName(String goodsInfoName) {
            this.goodsInfoName = goodsInfoName;
        }


        public String getGoodsSpecification() {
            return goodsSpecification;
        }

        public void setGoodsSpecification(String goodsSpecification) {
            this.goodsSpecification = goodsSpecification;
        }

        public String getGoodsSpecValue() {
            return goodsSpecValue;
        }

        public void setGoodsSpecValue(String goodsSpecValue) {
            this.goodsSpecValue = goodsSpecValue;
        }

        public String getSpecString() {
            return specString;
        }

        public void setSpecString(String specString) {
            this.specString = specString;
        }

        public String getGoodsInfoItemNo() {
            return goodsInfoItemNo;
        }

        public void setGoodsInfoItemNo(String goodsInfoItemNo) {
            this.goodsInfoItemNo = goodsInfoItemNo;
        }

    }
}