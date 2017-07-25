package com.ylife.system.service;

import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;

import java.util.List;
import java.util.Map;

/**
 * Created by XBG on 2016/6/4.
 * 参数服务
 */
public interface ParamService {

    /**
     * 根据业务类型id获取业务参数
     */
    List<Param> getParams(int typeId);


    /**
     * 根据业务类型id获取启用的业务参数
     */
    List<Param> getEnabledParams(int typeId);

    /**
     * 创建参数。
     */
    Param createParam(int typeId, ParamType paramType, String name, boolean required, Integer serialNo, String operator);

    /**
     * 编辑参数。
     */
    void editParam(int paramId, ParamType paramType, String name, Boolean required, Boolean enabled, String operator);

    /**
     * 更新序号。
     */
    void updateSerialNo(Map<Integer, Integer> map);

    /**
     * 删除参数。
     */
    void deleteParam(int paramId, String operator);

    /**
     * 获取参数
     *
     * @param paramId
     * @return
     */
    Param getByParamId(Integer paramId);

    /**
     * 根据业务ID获取可参与计算的参数
     */
    List<Param> getComputableParam(int typeId);

    /**
     * 使用。
     */
    void use(int paramId);
}
