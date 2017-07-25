package com.ylife.order.model;

import com.ylife.goods.model.GoodsProductReleSpecVo;

import java.math.BigDecimal;
import java.util.List;

public class BackOrderGoods {

    private Long id;

    private Long backorderId;

    private Long goodsInfoId;

    private Integer goodsNum;

    private BigDecimal goodsPrice;

    private String goodsInfoName;

    private String goodsInfoImage;

    private String goodsInfoItemNo;

    private BigDecimal goodsInfoSettlePrice;

    /**
     * 商品规格
     */
    private List<GoodsProductReleSpecVo> goodsProductReleSpecVoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBackorderId() {
        return backorderId;
    }

    public void setBackorderId(Long backorderId) {
        this.backorderId = backorderId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName == null ? null : goodsInfoName.trim();
    }

    public String getGoodsInfoImage() {
        return goodsInfoImage;
    }

    public void setGoodsInfoImage(String goodsInfoImage) {
        this.goodsInfoImage = goodsInfoImage == null ? null : goodsInfoImage.trim();
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo == null ? null : goodsInfoItemNo.trim();
    }

    public List<GoodsProductReleSpecVo> getGoodsProductReleSpecVoList() {
        return goodsProductReleSpecVoList;
    }

    public void setGoodsProductReleSpecVoList(List<GoodsProductReleSpecVo> goodsProductReleSpecVoList) {
        this.goodsProductReleSpecVoList = goodsProductReleSpecVoList;
    }

    public BigDecimal getGoodsInfoSettlePrice() {
        return goodsInfoSettlePrice;
    }

    public void setGoodsInfoSettlePrice(BigDecimal goodsInfoSettlePrice) {
        this.goodsInfoSettlePrice = goodsInfoSettlePrice;
    }
}