package com.ylife.system.service;

import com.ylife.system.model.ParamValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * 参数值服务
 */
public interface ParamValueService {

    /**
     * 更新序号。
     */
    void updateSerialNo(Map<Integer, Integer> map);

    /**
     * 新建参数值。（如果参数非枚举，抛出异常。）
     */
    ParamValue createParamValue(int paramId, String name, BigDecimal value, Boolean enabled, Integer serialNo, String operator);

    /**
     * 编辑参数值。
     */
    void editParamValue(int valueId, String name, BigDecimal value, Boolean enabled, String operator);

    /**
     * 删除参数值。
     */
    void deleteParamValue(int valueId, String operator);

    /**
     * 根据参数ID获取参数值。
     */
    List<ParamValue> getParamValues(int paramId);

    /**
     * 根据参数ID获取启用的参数值。
     */
    List<ParamValue> getEnabledParamValues(int paramId);

    /**
     * 获取参数值。
     */
    ParamValue getParamValue(int paramValueId);

    /**
     * 根据参数ID删除值。
     */
    void deleteByParamId(int paramId, String operator);

    /**
     * 使用。
     */
    void use(int valueId);

    /**
     * 根据参数值名称和参数id获取参数值
     *
     * @param paramId   参数id
     * @param valueName 参数值名称
     * @return
     */
    ParamValue selectByValueNameAndId(int paramId, String valueName);
}
