package com.ylife.product.model;

import java.util.Date;

public class GoodsImage {

    private Long goodsImgId;

    private Long goodsInfoId;

    private String imageInName;

    private String imageThumName;

    private String imageBigName;

    private String imageArtworkName;

    private Integer goodsImgSort;

    private String goodsImgDelflag;

    private String goodsImgCreateName;

    private Date goodsImgCreateTime;

    private String goodsImgModifiedName;

    private Date goodsImgModifiedTime;

    private String goodsImgDelName;

    private Date goodsImgDelTime;

    public Long getGoodsImgId() {
        return goodsImgId;
    }

    public void setGoodsImgId(Long goodsImgId) {
        this.goodsImgId = goodsImgId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getImageInName() {
        return imageInName;
    }

    public void setImageInName(String imageInName) {
        this.imageInName = imageInName == null ? null : imageInName.trim();
    }

    public String getImageThumName() {
        return imageThumName;
    }

    public void setImageThumName(String imageThumName) {
        this.imageThumName = imageThumName == null ? null : imageThumName.trim();
    }

    public String getImageBigName() {
        return imageBigName;
    }

    public void setImageBigName(String imageBigName) {
        this.imageBigName = imageBigName == null ? null : imageBigName.trim();
    }

    public String getImageArtworkName() {
        return imageArtworkName;
    }

    public void setImageArtworkName(String imageArtworkName) {
        this.imageArtworkName = imageArtworkName == null ? null : imageArtworkName.trim();
    }

    public Integer getGoodsImgSort() {
        return goodsImgSort;
    }

    public void setGoodsImgSort(Integer goodsImgSort) {
        this.goodsImgSort = goodsImgSort;
    }

    public String getGoodsImgDelflag() {
        return goodsImgDelflag;
    }

    public void setGoodsImgDelflag(String goodsImgDelflag) {
        this.goodsImgDelflag = goodsImgDelflag == null ? null : goodsImgDelflag.trim();
    }

    public String getGoodsImgCreateName() {
        return goodsImgCreateName;
    }

    public void setGoodsImgCreateName(String goodsImgCreateName) {
        this.goodsImgCreateName = goodsImgCreateName == null ? null : goodsImgCreateName.trim();
    }

    public Date getGoodsImgCreateTime() {
        return goodsImgCreateTime;
    }

    public void setGoodsImgCreateTime(Date goodsImgCreateTime) {
        this.goodsImgCreateTime = goodsImgCreateTime;
    }

    public String getGoodsImgModifiedName() {
        return goodsImgModifiedName;
    }

    public void setGoodsImgModifiedName(String goodsImgModifiedName) {
        this.goodsImgModifiedName = goodsImgModifiedName == null ? null : goodsImgModifiedName.trim();
    }

    public Date getGoodsImgModifiedTime() {
        return goodsImgModifiedTime;
    }

    public void setGoodsImgModifiedTime(Date goodsImgModifiedTime) {
        this.goodsImgModifiedTime = goodsImgModifiedTime;
    }

    public String getGoodsImgDelName() {
        return goodsImgDelName;
    }

    public void setGoodsImgDelName(String goodsImgDelName) {
        this.goodsImgDelName = goodsImgDelName == null ? null : goodsImgDelName.trim();
    }

    public Date getGoodsImgDelTime() {
        return goodsImgDelTime;
    }

    public void setGoodsImgDelTime(Date goodsImgDelTime) {
        this.goodsImgDelTime = goodsImgDelTime;
    }
}