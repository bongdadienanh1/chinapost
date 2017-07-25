package com.ylife.order.model;

/**
 * Created by InThEnd on 2016/5/3.
 * 退单状态。
 */
public enum CreditOrderStatus {

    /**
     * ******************************退货*******************************
     */
    /*退货已申请*/
    RETURN_APPLIED("0", "退货申请"),
    /*退货已同意*/
    RETURN_AGREED("1", "同意退货"),
    /*退货已拒绝*/
    RETURN_DENIED("2", "拒绝退货"),
    /*退货等待用户填写物流地址*/
    RETURN_WAIT_DELIVER_INFO("9", "待客户填写物流地址"),
    /*退货待收货*/
    RETURN_DELIVERING("3", "待收货"),
    /*退货收货失败*/
    RETURN_DELIVER_FAILURE("8", "收货失败"),
    /*退货待退款*/
    RETURN_WAIT_REFUND("11", "退货待退款"),
    /*退货已完成*/
    RETURN_FINISHED("4", "退货结束"),

    /**
     * ******************************退款*******************************
     */
    /*退款已申请*/
    REFUND_APPLIED("6", "审核退款"),
    /*退款已拒绝*/
    REFUND_DENIED("7", "拒绝退款"),
    /*退款已同意*/
    REFUND_AGREED("12", "退款待退款"),
    /*退款已完成*/
    REFUND_FINISHED("10", "退款成功");


    private String code;

    private String tag;

    CreditOrderStatus(String code, String tag) {
        this.code = code;
        this.tag = tag;
    }

    public String getCode() {
        return code;
    }

    public String getTag() {
        return tag;
    }

    public static String getName(String code) {
        for (CreditOrderStatus item : CreditOrderStatus.values()) {
            if (item.getCode().equals(code)) {
                return item.name();
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return tag;
    }
}
