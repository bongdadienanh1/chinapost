package com.ylife.system.service.impl;

import com.ylife.exception.UserOperationException;
import com.ylife.expression.Expression;
import com.ylife.expression.Variable;
import com.ylife.system.mapper.BusinessTypeMapper;
import com.ylife.system.mapper.ParamMapper;
import com.ylife.system.mapper.ParamValueMapper;
import com.ylife.system.model.BusinessType;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;
import com.ylife.system.service.BusinessTypeHistoryService;
import com.ylife.system.service.ParamService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/4.
 * ParamServiceImpl
 */
@Service
public class ParamServiceImpl implements ParamService {

    @Resource
    private ParamMapper paramMapper;
    @Resource
    private BusinessTypeMapper businessTypeMapper;
    @Resource
    private ParamValueMapper paramValueMapper;
    @Resource
    private BusinessTypeHistoryService businessTypeHistoryService;

    @Override
    public List<Param> getParams(int typeId) {
        return paramMapper.selectByTypeId(typeId);
    }

    @Override
    public List<Param> getEnabledParams(int typeId) {
        Param model = new Param();
        model.setTypeId(typeId);
        model.setEnabled(true);
        return paramMapper.selectByModel(model);
    }

    @Override
    @Transactional
    public Param createParam(int typeId, ParamType paramType, String name, boolean required, Integer serialNo, String operator) {
        BusinessType businessType = businessTypeMapper.selectDetailsByPrimaryKey(typeId);
        String ex = businessType.magicEx();
        Assert.notNull(paramType, "参数类型不能为空。");
        Assert.hasLength(name, "参数名不能为空。");
        Param param = paramMapper.selectByTypeIdAndNameForUpdate(typeId, name);
        if (param != null) {
            throw new UserOperationException("参数名已存在。");
        }
        Param model = new Param();
        model.setTypeId(typeId);
        model.setParamType(paramType);
        model.setParamName(name);
        model.setRequired(required);
        model.setSerialNo(serialNo);
        model.setDeletable(true);
        model.setEnabled(true);
        paramMapper.insertSelective(model);
        businessTypeHistoryService.addHistory(typeId, businessType.getTypeName(), "新增参数【" + name + "】", null, ex, ex, operator);
        return model;
    }

    @Override
    @Transactional
    public void editParam(int paramId, ParamType paramType, String name, Boolean required, Boolean enabled, String operator) {
        Param param = paramMapper.selectByPrimaryKeyForUpdate(paramId);
        Assert.notNull(param, "参数不存在。");
        BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId());
        String beforeEx = type.magicEx();
        if (param.getParamName().equals(name)) {
            name = null;
        }
        if (param.getEnabled().equals(enabled)) {
            enabled = null;
        }
        if (param.getRequired().equals(required)) {
            required = null;
        }
        boolean edit = false;
        String operation = "参数【" + param.getParamName() + "】：";
        if (name != null) {
            operation = operation + "\n" + "名称由【" + param.getParamName() + "】修改为【" + name + "】";
            edit = true;
        }
        Param param2 = paramMapper.selectByTypeIdAndNameForUpdate(param.getTypeId(), name);
        if (param2 != null && name != null) {
            throw new UserOperationException("参数名已存在。");
        }
        if (!param.getDeletable() && name != null) {
            throw new UserOperationException("此参数已被使用，无法修改名称。");
        }
        //如果参数类型由选项变为其他，删除选项值。
        if (param.getParamType() == ParamType.TYPE_ENUM && (paramType != null && paramType != ParamType.TYPE_ENUM)) {
            paramValueMapper.deleteByParamId(paramId);
        }

        if (required != null) {
            edit = true;
            if (required) {
                operation = operation + "\n" + "非必填改为必填";
            } else {
                relatedToBlankExpression(param);
                operation = operation + "\n" + "必填改为非必填";
            }
        }
        if (enabled != null) {
            edit = true;
            if (enabled) {
                operation = operation + "\n" + "非启用改为启用";
            } else {
                relatedToBlankExpression(param);
                operation = operation + "\n" + "启用改为非启用";
            }
        }
        Param model = new Param();
        model.setParamId(paramId);
        model.setParamType(paramType);
        model.setParamName(name);
        model.setRequired(required);
        model.setEnabled(enabled);
        paramMapper.updateByPrimaryKeySelective(model);
        if (edit) {
            String afterEx = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId()).magicEx();
            businessTypeHistoryService.addHistory(param.getTypeId(), type.getTypeName(), operation, null, beforeEx, afterEx, operator);
        }
    }

    @Override
    public void updateSerialNo(Map<Integer, Integer> map) {
        for (Integer id : map.keySet()) {
            Param param = paramMapper.selectByPrimaryKey(id);
            param.setSerialNo(map.get(id));
            paramMapper.updateByPrimaryKeySelective(param);
        }
    }

    @Override
    @Transactional
    public void deleteParam(int paramId, String operator) {
        Param param = paramMapper.selectByPrimaryKey(paramId);
        Assert.notNull(param);
        if (!param.getDeletable()) {
            throw new UserOperationException("此参数已被使用，无法删除。");
        }
        BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId());
        String beforeEx = type.magicEx();
        relatedToBlankExpression(param);
        paramMapper.deleteByPrimaryKey(paramId);
        if (param.getParamType() == ParamType.TYPE_ENUM) {
            paramValueMapper.deleteByParamId(paramId);
        }
        String afterEx = businessTypeMapper.selectDetailsByPrimaryKey(param.getTypeId()).magicEx();
        businessTypeHistoryService.addHistory(param.getTypeId(), type.getTypeName(), "删除参数【" + param.getParamName() + "】", null, beforeEx, afterEx, operator);
    }

    @Override
    public Param getByParamId(Integer paramId) {
        return paramMapper.selectByPrimaryKey(paramId);
    }

    @Override
    public List<Param> getComputableParam(int typeId) {
        List<Param> params = paramMapper.selectComputableParamByTypeId(typeId);
        params.add(Param.getDiscountParam());
        return params;
    }

    /**
     * 如果关联到公式则清空。
     */
    private void relatedToBlankExpression(Param param) {
        BusinessType businessType = businessTypeMapper.selectByPrimaryKey(param.getTypeId());
        String ex = businessType.getExpression();
        boolean related = false;
        if (!StringUtil.isBlank(ex)) {
            Expression expression = Expression.create(ex);
            Collection<Variable> variables = expression.getVariables();
            for (Variable variable : variables) {
                if (variable.getId() == param.getParamId()) {
                    related = true;
                    break;
                }
            }
        }
        if (related) {
            businessType.setExpression(null);
            businessTypeMapper.updateByPrimaryKey(businessType);
        }
    }

    @Override
    public void use(int paramId) {
        Param model = new Param();
        model.setParamId(paramId);
        model.setDeletable(false);
        paramMapper.updateByPrimaryKeySelective(model);
    }
}
