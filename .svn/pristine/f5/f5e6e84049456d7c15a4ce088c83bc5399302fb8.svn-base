package com.ylife.system.service.impl;

import com.ylife.exception.UserOperationException;
import com.ylife.system.mapper.BusinessTypeMapper;
import com.ylife.system.mapper.ParamMapper;
import com.ylife.system.mapper.ParamValueMapper;
import com.ylife.system.model.BusinessType;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;
import com.ylife.system.model.ParamValue;
import com.ylife.system.service.BusinessTypeHistoryService;
import com.ylife.system.service.ParamValueService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * ParamValueServiceImpl
 */
@Service
public class ParamValueServiceImpl implements ParamValueService {

    @Resource
    private ParamValueMapper paramValueMapper;
    @Resource
    private ParamMapper paramMapper;
    @Resource
    private BusinessTypeMapper businessTypeMapper;
    @Resource
    private BusinessTypeHistoryService businessTypeHistoryService;

    @Override
    public void updateSerialNo(Map<Integer, Integer> map) {
        for (Integer id : map.keySet()) {
            ParamValue model = new ParamValue();
            model.setParamValueId(id);
            model.setSerialNo(map.get(id));
            paramValueMapper.updateByPrimaryKeySelective(model);
        }
    }

    @Override
    @Transactional
    public ParamValue createParamValue(int paramId, String name, BigDecimal value, Boolean enabled, Integer serialNo, String operator) {
        Param param = paramMapper.selectByPrimaryKey(paramId);
        Assert.notNull(param, "此参数不存在。");
        if (param.getParamType() != ParamType.TYPE_ENUM) {
            throw new UserOperationException("此参数非选项类型，无法添加参数。");
        }
        Assert.notNull(name);
        Assert.notNull(value);
        ParamValue value1 = paramValueMapper.selectByParamIdAndNameForUpdate(paramId, name);
        if (value1 != null) {
            throw new UserOperationException("此参数值名称已存在。");
        }
        BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId());
        String ex = type.magicEx();
        ParamValue model = new ParamValue();
        model.setParamId(paramId);
        model.setParamValueName(name);
        model.setParamValue(value);
        model.setSerialNo(serialNo);
        model.setDeletable(true);
        model.setEnabled(enabled);
        paramValueMapper.insertSelective(model);
        businessTypeHistoryService.addHistory(param.getTypeId(), type.getTypeName(), "参数【" + param.getParamName() + "】新建参数值【" + name + "】", null, ex, ex, operator);
        return model;
    }

    @Override
    @Transactional
    public void editParamValue(int valueId, String name, BigDecimal value, Boolean enabled, String operator) {
        ParamValue v = paramValueMapper.selectByPrimaryKeyForUpdate(valueId);
        Assert.notNull(v, "此参数值不存在。");
        if (v.getParamValueName().equals(name)) {
            name = null;
        }
        if (v.getEnabled().equals(enabled)) {
            enabled = null;
        }

        if (value != null && v.getParamValue().compareTo(value) == 0) {
            value = null;
        }
        ParamValue value1 = paramValueMapper.selectByParamIdAndNameForUpdate(v.getParamId(), name);
        if (value1 != null) {
            throw new UserOperationException("此参数值名称已存在。");
        }
        if (!v.getDeletable() && name != null) {
            throw new UserOperationException("此参数值已被使用，无法修改名称。");
        }
        Param param = paramMapper.selectByPrimaryKey(v.getParamId());
        ParamValue model = new ParamValue();
        model.setParamValueId(valueId);
        model.setParamValue(value);
        model.setParamValueName(name);
        model.setEnabled(enabled);
        paramValueMapper.updateByPrimaryKeySelective(model);
        boolean edit = false;
        String operation = "参数【" + param.getParamName() + "】的参数值【" + v.getParamValueName() + "】:";
        if (enabled != null) {
            if (enabled) {
                operation = operation + "\n" + "未启用修改为启用";
            } else {
                operation = operation + "\n" + "启用修改为未启用";
            }
            edit = true;
        }
        if (name != null) {
            operation = operation + "\n" + "名称从【" + v.getParamValueName() + "】修改为【" + name + "】";
            edit = true;
        }
        if (value != null) {
            operation = operation + "\n" + "值从【" + v.getParamValue() + "】修改为【" + value + "】";
            edit = true;
        }
        if (edit) {
            BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId());
            String ex = type.magicEx();
            businessTypeHistoryService.addHistory(param.getTypeId(), type.getTypeName(), operation, null, ex, ex, operator);
        }
    }

    @Override
    @Transactional
    public void deleteParamValue(int valueId, String operator) {
        ParamValue v = paramValueMapper.selectByPrimaryKeyForUpdate(valueId);
        Assert.notNull(v, "此参数值不存在。");
        if (!v.getDeletable()) {
            throw new UserOperationException("此参数值已被使用，无法删除。");
        }
        Param param = paramMapper.selectByPrimaryKey(v.getParamId());
        BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId());
        String ex = type.magicEx();
        paramValueMapper.deleteByPrimaryKey(valueId);
        businessTypeHistoryService.addHistory(param.getTypeId(), type.getTypeName(), "参数【" + param.getParamName() + "】的参数值【" + v.getParamValueName() + "】删除。", null, ex, ex, operator);
    }

    @Override
    public List<ParamValue> getParamValues(int paramId) {
        return paramValueMapper.selectByParamId(paramId);
    }

    @Override
    public List<ParamValue> getEnabledParamValues(int paramId) {
        ParamValue model = new ParamValue();
        model.setParamId(paramId);
        model.setEnabled(true);
        return paramValueMapper.selectByModel(model);
    }


    @Override
    public ParamValue getParamValue(int paramValueId) {
        return paramValueMapper.selectByPrimaryKey(paramValueId);
    }

    @Override
    public void deleteByParamId(int paramId, String operator) {
        paramValueMapper.deleteByParamId(paramId);
    }

    @Override
    public void use(int valueId) {
        ParamValue model = new ParamValue();
        model.setParamValueId(valueId);
        model.setDeletable(false);
        paramValueMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public ParamValue selectByValueNameAndId(int paramId, String valueName) {
        return paramValueMapper.selectByValueNameAndId(paramId, valueName);
    }
}
