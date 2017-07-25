package com.ylife.system.model;

import com.ylife.expression.Expression;
import com.ylife.expression.exception.MagicException;
import com.ylife.utils.StringUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusinessType {

    //批量发放
    public static final int TYPE_ID_GRAND=-100;
    //批量扣减
    public static final int TYPE_ID_DECUT = -200;

    private Integer typeId;

    private String typeName;

    private String expression;

    private Integer serialNo;

    private Date createTime;

    private Date modifyTime;

    private Boolean enabled;

    private Boolean deletable;

    private List<Param> params;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String magicEx() {
        if (StringUtil.isBlank(this.getExpression())) {
            return null;
        }
        Expression ex = Expression.create(this.getExpression());
        Map<Integer, String> map = new HashMap<>();
        for (Param param : this.getParams()) {
            if (param.getRequired()) {
                map.put(param.getParamId(), param.getParamName());
            }
        }
        map.put(Param.DISCOUNT_ID, Param.DISCOUNT_NAME);
        try {
            return ex.magic(map);
        } catch (MagicException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}