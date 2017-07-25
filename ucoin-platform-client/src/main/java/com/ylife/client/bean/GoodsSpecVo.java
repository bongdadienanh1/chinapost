/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.client.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 商品规格VO
 *
 */
public class GoodsSpecVo implements Serializable {
    // 规格ID
    private Long specId;

    // 规格名称
    private String specName;

    // 规格备注
    private String specRemark;

    // 规格别名
    private String specNickname;

    // 显示类型 0:文字 1：图片
    private String specShowtype;

    // 显示方式 0:下拉 1：平铺
    private String specShowmode;

    // 所有的规格值
    private List<Object> specDetails;

    public Long getSpecId() {
        return specId;
    }

    public void setSpecId(Long specId) {
        this.specId = specId;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getSpecRemark() {
        return specRemark;
    }

    public void setSpecRemark(String specRemark) {
        this.specRemark = specRemark;
    }

    public String getSpecNickname() {
        return specNickname;
    }

    public void setSpecNickname(String specNickname) {
        this.specNickname = specNickname;
    }

    public String getSpecShowtype() {
        return specShowtype;
    }

    public void setSpecShowtype(String specShowtype) {
        this.specShowtype = specShowtype;
    }

    public String getSpecShowmode() {
        return specShowmode;
    }

    public void setSpecShowmode(String specShowmode) {
        this.specShowmode = specShowmode;
    }

    public List<Object> getSpecDetails() {
        return specDetails;
    }

    public void setSpecDetails(List<Object> specDetails) {
        this.specDetails = specDetails;
    }

}
