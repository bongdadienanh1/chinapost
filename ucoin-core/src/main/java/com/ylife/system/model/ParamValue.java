package com.ylife.system.model;

import java.math.BigDecimal;

public class ParamValue {

    private Integer paramValueId;

    private String paramValueName;

    private BigDecimal paramValue;

    private Integer paramId;

    private Integer serialNo;

    private Boolean enabled;

    private Boolean deletable;

    public Integer getParamValueId() {
        return paramValueId;
    }

    public void setParamValueId(Integer paramValueId) {
        this.paramValueId = paramValueId;
    }

    public String getParamValueName() {
        return paramValueName;
    }

    public void setParamValueName(String paramValueName) {
        this.paramValueName = paramValueName == null ? null : paramValueName.trim();
    }

    public BigDecimal getParamValue() {
        return paramValue;
    }

    public void setParamValue(BigDecimal paramValue) {
        this.paramValue = paramValue;
    }

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getDeletable() {
        return deletable;
    }

    public void setDeletable(Boolean deletable) {
        this.deletable = deletable;
    }
}