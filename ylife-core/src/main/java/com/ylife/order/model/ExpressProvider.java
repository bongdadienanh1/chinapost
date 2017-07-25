package com.ylife.order.model;

/**
 * Created by Administrator on 2016/5/12.
 * 快递公司名称
 */
public enum ExpressProvider {

    SHUNFENG("1", "顺丰快递"),
    TIANTIAN("2", "天天快递"),
    SHENTONG("3", "申通快递"),
    YUANTONG("4", "圆通快递");

    private String code;

    private String tag;

    ExpressProvider(String code, String tag) {
        this.code = code;
        this.tag = tag;
    }


    public String getCode() {
        return code;
    }

    public String getTag() {
        return tag;
    }

}
