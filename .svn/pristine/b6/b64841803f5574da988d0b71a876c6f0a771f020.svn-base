package com.ylife.chinapost.service;

import com.ylife.chinapost.service.pojo.ParamValueInfo;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * 系统设置
 */
public interface SystemManageService {

    /**
     * 获取所有业务类型。
     */
    List<BusinessType> getAll();

    /**
     * 获取业务类型变动记录。
     */
    Page<BusinessTypeHistory> getHistories(Integer typeId, Date startTime, Date endTime, Pageable pageable);

    /**
     * 获取可以参与计算的参数
     */
    List<Param> getComputableParam(int typeId);

    /**
     * 添加业务类型
     *
     * @param name 业务类型名称
     */
    BusinessType addBusinessType(String name, Integer serialNo);

    /**
     * 编辑业务类型。
     *
     * @param typeId  业务类型ID
     * @param name    业务类型名称
     * @param enabled 是否启用
     */
    void editBusinessType(int typeId, String name, Boolean enabled);

    /**
     * 删除业务类型。
     *
     * @param typeId 业务类型ID
     */
    void deleteBusinessType(int typeId);

    /**
     * 添加参数。
     *
     * @param typeId     业务类型ID
     * @param paramType  参数类型
     * @param name       参数名
     * @param required   参数是否必需填
     * @param valueInfos 参数值集合
     */
    Param addParam(int typeId, ParamType paramType, String name, boolean required, Integer serialNo, List<ParamValueInfo> valueInfos);

    /**
     * 编辑参数。
     *
     * @param paramId  参数ID
     * @param name     参数名称
     * @param required 参数是否必需填
     * @param enabled  是否启用
     */
    void editParam(int paramId, String name, Boolean required, Boolean enabled);

    /**
     * 增加参数值。
     */
    ParamValue addParamValue(int paramId, String name, BigDecimal value, boolean enabled, Integer serialNo);

    /**
     * 删除参数。
     */
    void deleteParam(int paramId);

    /**
     * 编辑参数值。
     */
    void editParamValue(int valueId, String name, BigDecimal value, Boolean enabled);

    /**
     * 删除参数值。
     *
     * @param valueId 参数值ID
     */
    void deleteParamValue(int valueId);

    /**
     * 添加公式
     *
     * @param typeId     业务类型
     * @param expression 公式
     * @param note       备注
     */
    void editExpression(int typeId, String expression, String note);

    /**
     * 更新业务类型顺序。
     */
    void updateTypeSerial(Map<Integer, Integer> map);

    /**
     * 更新参数顺序。
     */
    void updateParamSerial(Map<Integer, Integer> map);

    /**
     * 更新参数值顺序。
     */
    void updateParamValueSerial(Map<Integer, Integer> map);

}
