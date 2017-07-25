package com.ylife.form.model;

import com.ylife.inventory.model.InventoryChangeHistory;

/**
 * Created by XiaoBiaoGe on 2016/11/23.
 */
public class InventoryChangeHistoryForm extends InventoryChangeHistory {

    private String goodsInfoName;

    private String goodsSpecString;

    private String enterpriseName;

    private String accountName;

    private String goodsInfoItemNo;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public String getGoodsSpecString() {
        return goodsSpecString;
    }

    public void setGoodsSpecString(String goodsSpecString) {
        this.goodsSpecString = goodsSpecString;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }
}
