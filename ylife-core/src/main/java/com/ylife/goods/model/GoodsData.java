package com.ylife.goods.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/11/3.
 */
public class GoodsData {

    private Long catId;

    private String goodsName;

    private BigDecimal goodsPrice;

    private boolean goodsAdded;

    private Long brandId;

    private String goodsDetailDesc;

    private List<Spec> specs;

    private List<TypeSpec> typeSpecs;

    private List<GoodsInfo> goodsInfos;

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public boolean isGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(boolean goodsAdded) {
        this.goodsAdded = goodsAdded;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc;
    }

    public List<Spec> getSpecs() {
        return specs;
    }

    public void setSpecs(List<Spec> specs) {
        this.specs = specs;
    }

    public List<TypeSpec> getTypeSpecs() {
        return typeSpecs;
    }

    public void setTypeSpecs(List<TypeSpec> typeSpecs) {
        this.typeSpecs = typeSpecs;
    }

    public List<GoodsInfo> getGoodsInfos() {
        return goodsInfos;
    }

    public void setGoodsInfos(List<GoodsInfo> goodsInfos) {
        this.goodsInfos = goodsInfos;
    }

    public static class Spec {
        private String specName;
        private List<SpecDetail> details;

        public List<SpecDetail> getDetails() {
            return details;
        }

        public void setDetails(List<SpecDetail> details) {
            this.details = details;
        }

        public String getSpecName() {
            return specName;
        }

        public void setSpecName(String specName) {
            this.specName = specName;
        }

        public static class SpecDetail {

            private Long detailId;

            private String value;

            public Long getDetailId() {
                return detailId;
            }

            public void setDetailId(Long detailId) {
                this.detailId = detailId;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class TypeSpec {
        private String typeSpecName;
        private String typeSpecValue;

        public String getTypeSpecName() {
            return typeSpecName;
        }

        public void setTypeSpecName(String typeSpecName) {
            this.typeSpecName = typeSpecName;
        }

        public String getTypeSpecValue() {
            return typeSpecValue;
        }

        public void setTypeSpecValue(String typeSpecValue) {
            this.typeSpecValue = typeSpecValue;
        }
    }

    public static class GoodsInfo{
        private String goodsInfoItemNo;
        private String goodsInfoName;
        private BigDecimal goodsInfoPreferprice;
        private BigDecimal goodsInfoSettlePrice;
        private List<GoodsInfoImage> infoImages;

        public String getGoodsInfoItemNo() {
            return goodsInfoItemNo;
        }

        public void setGoodsInfoItemNo(String goodsInfoItemNo) {
            this.goodsInfoItemNo = goodsInfoItemNo;
        }

        public String getGoodsInfoName() {
            return goodsInfoName;
        }

        public void setGoodsInfoName(String goodsInfoName) {
            this.goodsInfoName = goodsInfoName;
        }

        public BigDecimal getGoodsInfoPreferprice() {
            return goodsInfoPreferprice;
        }

        public void setGoodsInfoPreferprice(BigDecimal goodsInfoPreferprice) {
            this.goodsInfoPreferprice = goodsInfoPreferprice;
        }

        public BigDecimal getGoodsInfoSettlePrice() {
            return goodsInfoSettlePrice;
        }

        public void setGoodsInfoSettlePrice(BigDecimal goodsInfoSettlePrice) {
            this.goodsInfoSettlePrice = goodsInfoSettlePrice;
        }

        public List<GoodsInfoImage> getInfoImages() {
            return infoImages;
        }

        public void setInfoImages(List<GoodsInfoImage> infoImages) {
            this.infoImages = infoImages;
        }

        public static class GoodsInfoImage {
            private String imageInName;

            public String getImageInName() {
                return imageInName;
            }

            public void setImageInName(String imageInName) {
                this.imageInName = imageInName;
            }
        }

    }
}
