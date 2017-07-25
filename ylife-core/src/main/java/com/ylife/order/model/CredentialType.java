package com.ylife.order.model;

/**
 * Created by InThEnd on 2016/5/13.
 * 申请凭证
 */
public enum CredentialType {

    /*无凭证*/
    NO_CREDENTIAL("无凭证"),

    /*有发票*/
    INVOICE("有发票"),

    /*有质检报告*/
    QUALITY_REPORT("有质检报告");

    private String tag;

    CredentialType(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
