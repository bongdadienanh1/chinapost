package com.ylife.chinapost.service.pojo;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/7.
 * <p/>
 * U宝发放信息pojo
 */
public class UcoinGrandInfo {

    //身份证
    private String idCardNo;
    //姓名
    private String name;

    //促销邮宝金额
    String saleAmount;
    //备注
    String remark;
    //业务类型id
    private Integer typeId;

    //key:paramName，value:paramValueName
    private Map<String,String> valueMap;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Map<String, String> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, String> valueMap) {
        this.valueMap = valueMap;
    }


    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(String saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
