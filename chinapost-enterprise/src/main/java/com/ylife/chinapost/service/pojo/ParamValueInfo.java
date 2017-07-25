package com.ylife.chinapost.service.pojo;

import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/6/7.
 * 参数值信息
 */
public class ParamValueInfo {

    private String paramValueName;

    private BigDecimal paramValue;

    public ParamValueInfo() {
    }

    public String getParamValueName() {
        return paramValueName;
    }

    public void setParamValueName(String paramValueName) {
        this.paramValueName = paramValueName;
    }

    public BigDecimal getParamValue() {
        return paramValue;
    }

    public void setParamValue(BigDecimal paramValue) {
        this.paramValue = paramValue;
    }
}
