package com.ylife.form.model;

/**
 * Created by XiaoBiaoGe on 2016/12/5.
 */

import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.BillType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供货报表统计model
 * 1、供货商供货统计
 * 2、网点供货统计
 */

public class SupplyForm {
    //单据id
    private Long billId;
    //单据编号
    private Long code;
    //单据创建网点id
    private Long creatorId;
    //调拨单的备货时间
    private Date stockTime;
    //确认收货时间
    private Date recipientTime;
    //请货单请货时间
    private Date applyTime;
    //单据类型
    private BillType billType;
    //单据状态
    private BillStatus billStatus;
    //商品名称
    private String goodsInfoName;
    //商品编号
    private String goodsInfoItemNo;
    //商品类型
    private Integer goodsInfoType;
    //发货数量
    private Integer deliveryAmount;
    //结算价
    private BigDecimal settlePrice;
    //金额
    private BigDecimal price;

    private BigDecimal totalPrice;

    //入库单创建时间
    private Date createTime;

    //网点名称
    private String enterpriseName;
    //网点地址
    private String address;

    private String linkMan;

    private String linkMobile;
    //供应商名称
    private String thirdName;
    //网点Id
    private Long enterpriseId;
    //thirdId
    private Long thirdId;

    private String sumFlag;

    private Integer receiptAmount;

    public String getLinkMobile() {
        return linkMobile;
    }

    public void setLinkMobile(String linkMobile) {
        this.linkMobile = linkMobile;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getSumFlag() {
        return sumFlag;
    }

    public void setSumFlag(String sumFlag) {
        this.sumFlag = sumFlag;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

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

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    public Integer getGoodsInfoType() {
        return goodsInfoType;
    }

    public void setGoodsInfoType(Integer goodsInfoType) {
        this.goodsInfoType = goodsInfoType;
    }

    public Integer getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Integer deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Date getStockTime() {
        return stockTime;
    }

    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount) {
        this.receiptAmount = receiptAmount;
    }
}
