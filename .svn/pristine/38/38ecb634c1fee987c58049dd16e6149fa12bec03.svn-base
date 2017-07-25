package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.SystemManageService;
import com.ylife.chinapost.service.pojo.ParamValueInfo;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import com.ylife.system.model.*;
import com.ylife.system.service.BusinessTypeHistoryService;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.system.service.ParamService;
import com.ylife.system.service.ParamValueService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * SystemManagerServiceImpl
 */
@Service
public class SystemManageServiceImpl implements SystemManageService {

    @Resource
    private BusinessTypeService businessTypeService;
    @Resource
    private ParamService paramService;
    @Resource
    private ParamValueService paramValueService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private BusinessTypeHistoryService businessTypeHistoryService;

    @Override
    public List<BusinessType> getAll() {
        List<BusinessType> types = businessTypeService.getAll();
        for (BusinessType type : types) {
            type.setExpression(type.magicEx());
        }
        return types;
    }

    @Override
    public Page<BusinessTypeHistory> getHistories(Integer typeId, Date startTime, Date endTime, Pageable pageable) {
        return businessTypeHistoryService.get(typeId, startTime, endTime, pageable);
    }

    @Override
    public List<Param> getComputableParam(int typeId) {
        return paramService.getComputableParam(typeId);
    }

    @Override
    public BusinessType addBusinessType(String name, Integer serialNo) {
        return businessTypeService.creatBusinessType(name, serialNo, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    public void editBusinessType(int typeId, String name, Boolean enabled) {
        businessTypeService.editBusinessType(typeId, name, enabled, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    @Transactional
    public void deleteBusinessType(int typeId) {
        businessTypeService.deleteBusinessType(typeId, currentLoginService.getCurrentLoginUsername());
    }


    @Override
    @Transactional
    public Param addParam(int typeId, ParamType paramType, String name, boolean required, Integer serialNo, List<ParamValueInfo> valueInfos) {
        Assert.notNull(paramType, "参数类型不能为空。");
        Param param = paramService.createParam(typeId, paramType, name, required, serialNo, currentLoginService.getCurrentLoginUsername());
        List<ParamValue> values = new ArrayList<>();
        if (paramType == ParamType.TYPE_ENUM && valueInfos != null) {
            for (int i = 0; i < valueInfos.size(); i++) {
                ParamValueInfo info = valueInfos.get(i);
                ParamValue paramValue = paramValueService.createParamValue(param.getParamId(), info.getParamValueName(), info.getParamValue(), true, i, currentLoginService.getCurrentLoginUsername());
                values.add(paramValue);
            }
        }
        param.setParamValues(values);
        return param;
    }

    @Override
    public void editParam(int paramId, String name, Boolean required, Boolean enabled) {
        paramService.editParam(paramId, null, name, required, enabled, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    public ParamValue addParamValue(int paramId, String name, BigDecimal value, boolean enabled, Integer serialNo) {
        return paramValueService.createParamValue(paramId, name, value, enabled, serialNo, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    public void deleteParam(int paramId) {
        paramService.deleteParam(paramId, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    @Transactional
    public void editParamValue(int valueId, String name, BigDecimal value, Boolean enabled) {
        paramValueService.editParamValue(valueId, name, value, enabled, currentLoginService.getCurrentLoginUsername());
    }

    @Override
    public void deleteParamValue(int valueId) {
        paramValueService.deleteParamValue(valueId, currentLoginService.getCurrentLoginUsername());
    }


    @Override
    public void editExpression(int typeId, String expression, String note) {
        businessTypeService.editExpression(typeId, expression, currentLoginService.getCurrentLoginUsername(), note);
    }

    @Override
    public void updateTypeSerial(Map<Integer, Integer> map) {
        businessTypeService.updateSerialNo(map);
    }

    @Override
    public void updateParamSerial(Map<Integer, Integer> map) {
        paramService.updateSerialNo(map);
    }

    @Override
    public void updateParamValueSerial(Map<Integer, Integer> map) {
        paramValueService.updateSerialNo(map);
    }
}
