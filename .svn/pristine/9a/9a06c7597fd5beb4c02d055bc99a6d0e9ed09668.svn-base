package com.ylife.system.service;

import com.ylife.system.model.BusinessType;

import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * 业务类型服务
 */
public interface BusinessTypeService {


    BusinessType getByTypeId(Integer typeId);

    /**
     * 编辑公式。
     */
    void editExpression(int typeId, String expression, String operator, String note);

    /**
     * 更新序号。
     */
    void updateSerialNo(Map<Integer, Integer> map);

    /**
     * 添加业务类型
     *
     * @param name
     */
    BusinessType creatBusinessType(String name, Integer serialNo, String operator);

    /**
     * 编辑业务类型。
     */
    void editBusinessType(int typeId, String name, Boolean enabled, String operator);

    /**
     * 删除业务类型。
     */
    void deleteBusinessType(int typeId, String operator);

    /**
     * 获取所有业务类型。
     */
    List<BusinessType> getAll();

    /**
     * 获取所有启用的业务类型。
     */
    List<BusinessType> getEnabledTypes();

    /**
     * 获取业务类型详情。
     */
    BusinessType getDetails(int typeId);

    /**
     * 使用。
     */
    void use(int typeId);
}
