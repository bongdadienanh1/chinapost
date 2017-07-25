package com.ylife.client.bean;

import javax.validation.constraints.NotNull;

/**
 * 微信收货地址辅助类
 * Created by Administrator on 2016/4/2.
 */
public class OrderAddress {


        /**
         *id
         */
        private Long addressId;
        /**
         *收货人名称
         */
        @NotNull(message = "收货人名称不能为空")
        private String addressName;
        /**
         * 联系手机
         * */
        private String addressMoblie;

        /**
         *收货人电话
         */
        private String addressPhone;

        /**
         *收货街道信息
         */
        private String addressDetail;

        /**
         *详细地址
         */
        private String addressDetailInfo;

        /**
         *省
         */
        private String addressProvice;

        /**
         *市
         */
        private String addressCity;

        /**
         *区县
         */
        private String addressCounty;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressCounty() {
        return addressCounty;
    }

    public void setAddressCounty(String addressCounty) {
        this.addressCounty = addressCounty;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressMoblie() {
        return addressMoblie;
    }

    public void setAddressMoblie(String addressMoblie) {
        this.addressMoblie = addressMoblie;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getAddressDetailInfo() {
        return addressDetailInfo;
    }

    public void setAddressDetailInfo(String addressDetailInfo) {
        this.addressDetailInfo = addressDetailInfo;
    }

    public String getAddressProvice() {
        return addressProvice;
    }

    public void setAddressProvice(String addressProvice) {
        this.addressProvice = addressProvice;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
}
