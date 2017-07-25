package com.ylife.inventory.model;

/**
 * Created by InThEnd on 2016/6/13.
 * 单据状态
 */
public enum BillStatus {

    //审核中
    CHECKING("审核中"),

    //已审核
    CHECKED("已审核"),

    //已终止
    TERMINATED("已终止"),

    //已完成
    FINISHED("已完成"),

    PARTIAL_RECEIPT("部分收货"),

    //待发货
    WAIT_DELIVERY("待发货"),

    //待收货
    WAIT_RECEIVER("待收货"),

    //待编辑
    WAIT_EDIT("待修改"),

    //待入库
    STAY_IN("待入库"),

    //已审核未调拨
    CHECKED_NOT_TRANSFER("已同意未调拨"),

    //已审核采购
    CHECKED_NOT_PURCHASE("已同意未采购")
    ;


    private String name;

    BillStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
