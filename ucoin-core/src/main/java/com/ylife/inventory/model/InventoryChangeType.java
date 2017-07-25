package com.ylife.inventory.model;

/**
 * Created by XiaoBiaoGe on 2016/11/19.
 */
public enum InventoryChangeType {

    GOODSINFO_INSTOCK("商品入库"),

    GOODSINFO_LESS_REPORT("商品报损"),

    GOODSINFO_MORE_REPORT("商品报溢"),

    GOODSINFO_TRANSFER_IN("商品调入"),

    GOODSINFO_TRANSFER_OUT("商品调出"),

    ORDER_CONSUME("订单消耗"),

    ORDER_RETURNED("订单退货"),

    PURCHASEGOODS_INSTOCH("采购商品入库")
    ;


    public String name;

    InventoryChangeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
