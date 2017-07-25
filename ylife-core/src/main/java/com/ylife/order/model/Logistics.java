package com.ylife.order.model;

import java.util.Date;

public class Logistics {
    private Long npLogisticsId;

    private String npLogisticsNo;

    private String npLogisticsName;

    private Date createtime;

    private Long backOrderId;

    private String temp;

    public Long getNpLogisticsId() {
        return npLogisticsId;
    }

    public void setNpLogisticsId(Long npLogisticsId) {
        this.npLogisticsId = npLogisticsId;
    }

    public String getNpLogisticsNo() {
        return npLogisticsNo;
    }

    public void setNpLogisticsNo(String npLogisticsNo) {
        this.npLogisticsNo = npLogisticsNo == null ? null : npLogisticsNo.trim();
    }

    public String getNpLogisticsName() {
        return npLogisticsName;
    }

    public void setNpLogisticsName(String npLogisticsName) {
        this.npLogisticsName = npLogisticsName == null ? null : npLogisticsName.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(Long backOrderId) {
        this.backOrderId = backOrderId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }
}