package com.ylife.inventory.mapper.pojo;


import com.ylife.product.model.GoodsImage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
/*库存选品封装类*/
public class GoodsManagerResult {

    //商品详情介绍
    private String goodsDetailDesc;
    //货品id
    private Long goodsInfoId;
    // 货品图片
    private String goodsInfoImgId;
    //货品名称
    private String goodsInfoName;
    //货品规格名称
    private String goodsSpecification;
    //货品规格值
    private String goodsSpecValue;
    //规格字符串
    private String specString;
    //货品编号
    private String goodsInfoItemNo;
    //商城价
    private BigDecimal goodsInfoPreferPrice;
    //结算价
    private BigDecimal goodsInfoSettlePrice;

    //是否上架   0:下架   1:上架   2:未采集   3:线下
    private String goodsInfoAdded;
    //分类
    private String goodsInfoTypeName;
    //货品品牌
    private String goodsBrand;
    //集采商品所属商家
    private String thirdName;
    //品牌id
    private long brandId;
    //所属商家（第三方店铺）id
    private long thirdId;
    //第三方店铺id
    private long storeId;
    //第三方店铺名称
    private String storeName;
    //种类id
    private long typeId;
    //是否显示
    private Boolean onlineShow;
    //代客商城显示
    private Boolean valetShow;
    private  Integer inventory;
    private  Integer available;
    //商品类型 0:集采商品 1：自购商品
    private Integer goodsInfoType;
    // 审核状态
    private String auditStatus;
    // 拒绝原因
    private String refuseReason;
    // 运费
    private BigDecimal freightPrice;

    //自购商品的网点名称
    String enterpriseName;
    //自购商品所属网点id
    Long enterpriseId;

    //商品自身的预警值
    Integer goodsInfoWarning;

    //商品库存
    private Integer goodsInfoStock;


    public Integer getGoodsInfoWarning() {
        return goodsInfoWarning;
    }

    public void setGoodsInfoWarning(Integer goodsInfoWarning) {
        this.goodsInfoWarning = goodsInfoWarning;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public BigDecimal getGoodsInfoSettlePrice() {
        return goodsInfoSettlePrice;
    }

    public void setGoodsInfoSettlePrice(BigDecimal goodsInfoSettlePrice) {
        this.goodsInfoSettlePrice = goodsInfoSettlePrice;
    }

    public Integer getGoodsInfoType() {
        return goodsInfoType;
    }

    public void setGoodsInfoType(Integer goodsInfoType) {
        this.goodsInfoType = goodsInfoType;
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

    private BigDecimal lowPrice;

    private BigDecimal highPrice;

    private List<GoodsImage> images;

    public Boolean getOnlineShow() {
        return onlineShow;
    }

    public void setOnlineShow(Boolean onlineShow) {
        this.onlineShow = onlineShow;
    }

    public Boolean getValetShow() {
        return valetShow;
    }

    public void setValetShow(Boolean valetShow) {
        this.valetShow = valetShow;
    }

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public List<GoodsImage> getImages() {
        return images;
    }

    public void setImages(List<GoodsImage> images) {
        this.images = images;
    }


    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getThirdId() {
        return thirdId;
    }

    public void setThirdId(long thirdId) {
        this.thirdId = thirdId;
    }

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
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

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    public String getGoodsInfoTypeName() {
        return goodsInfoTypeName;
    }

    public void setGoodsInfoTypeName(String goodsInfoTypeName) {
        this.goodsInfoTypeName = goodsInfoTypeName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public Integer getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Integer goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }
}