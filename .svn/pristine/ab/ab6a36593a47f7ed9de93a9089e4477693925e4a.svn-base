package com.ylife.order.model;

/**
 * Created by InThEnd on 2016/5/6.
 * 订单状态
 */
public enum OrderStatus {

    /*已提交，待付款*/
    SUBMITED("0", "待付款"),
    /*已付款，待发货*/
    PAYED("1", "待发货"),
    /*已发货*/
    DELIVERED("2", "已发货"),
    /*已收货，已完成*/
    RECIEVED("3", "已完成"),
    /*已取消*/
    CANCELED("4", "已取消"),

    /**
     * ***************退货******************
     */
    /*退货已申请*/
    RETURN_APPLIED("7", "退货申请"),
    /*待客户填写物流地址*/
    RETURN_WAIT_DELIVER_INFO("8", "等待填写物流地址"),
    /*退货已拒绝*/
    RETURN_DENIED("9", "已拒绝"),
    /*待商家收货*/
    RETURN_WAIT_RECEIVE("10", "待收货"),
    /*退货收货失败*/
    RETURN_DELIVER_FAILURE("16", "收货失败"),
    /*退货待退款*/
    RETURN_WAIT_REFUND("20", "待退款"),
    /*退款已完成*/
    RETURN_FINISHED("17", "退款完成"),


    /**
     * **************退款************************
     */
    /*退款已申请*/
    REFUND_APPLIED("15", "申请退款"),
    /*退款已拒绝*/
    REFUND_DENIED("13", "拒绝退款"),
    /*退款已同意*/
    REFUND_AGREED("12", "同意退款"),
    /*退款已完成*/
    REFUND_FINISHED("18", "退款完成"),
    /*退款待退款*/
    REFUND_WAIT_REFUND("19", "待退款"),

    /*用于前端-待发货*/
    WAIT_DELIVER(null, "待发货"),
    /*用于前端-待提货*/
    WAIT_PICKUP(null, "待提货");

    private String code;

    private String name;

    OrderStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public static String getName(String code) {
        for (OrderStatus item : OrderStatus.values()) {
            if (item.getCode().equals(code)) {
                return item.name();
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return name;
    }
}
