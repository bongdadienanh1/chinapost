package com.ylife.system.model;

import java.util.List;

public class Param {

    public static final int DISCOUNT_ID = 0;

    public static final String DISCOUNT_NAME = "网点折扣比例";

    private Integer paramId;

    private ParamType paramType;

    private String paramName;

    private Integer typeId;

    private Integer serialNo;

    private Boolean required;

    private Boolean enabled;

    private Boolean deletable;

    private List<ParamValue> paramValues;

    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public ParamType getParamType() {
        return paramType;
    }

    public void setParamType(ParamType paramType) {
        this.paramType = paramType;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
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

    public List<ParamValue> getParamValues() {
        return paramValues;
    }

    public void setParamValues(List<ParamValue> paramValues) {
        this.paramValues = paramValues;
    }

    public static Param getDiscountParam() {
        Param param = new Param();
        param.setParamId(DISCOUNT_ID);
        param.setParamName(DISCOUNT_NAME);
        param.setSerialNo(10000);
        param.setParamType(ParamType.TYPE_FLOAT);
        return param;
    }
}