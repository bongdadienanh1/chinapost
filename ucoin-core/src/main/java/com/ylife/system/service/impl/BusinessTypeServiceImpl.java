package com.ylife.system.service.impl;

import com.ylife.exception.UserOperationException;
import com.ylife.expression.Expression;
import com.ylife.expression.Variable;
import com.ylife.expression.exception.ParseException;
import com.ylife.system.mapper.BusinessTypeMapper;
import com.ylife.system.mapper.ParamMapper;
import com.ylife.system.mapper.ParamValueMapper;
import com.ylife.system.model.BusinessType;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;
import com.ylife.system.service.BusinessTypeHistoryService;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/6.
 * BusinessTypeServiceImpl
 */
@Service
public class BusinessTypeServiceImpl implements BusinessTypeService {

    @Resource
    private BusinessTypeMapper businessTypeMapper;
    @Resource
    private ParamMapper paramMapper;
    @Resource
    private ParamValueMapper paramValueMapper;
    @Resource
    private BusinessTypeHistoryService businessTypeHistoryService;

    @Override
    public BusinessType getByTypeId(Integer typeId) {
        return businessTypeMapper.selectByPrimaryKey(typeId);
    }

    @Override
    @Transactional
    public void editExpression(int typeId, String expression, String operator, String note) {
        BusinessType before = businessTypeMapper.selectDetailsByPrimaryKey(typeId);
        Assert.notNull(before, "业务类型不存在。");
        String beforeEx = before.magicEx();
        Expression expression1;
        try {
            expression1 = Expression.create(expression);
        } catch (ParseException e) {
            throw new UserOperationException(e.getMessage());
        }
        for (Variable variable : expression1.getVariables()) {
            int id = variable.getId();
            if (id == Param.DISCOUNT_ID) {
                continue;
            }
            Param param = paramMapper.selectByPrimaryKey(id);
            if (param == null || param.getTypeId() != typeId || param.getParamType() == ParamType.TYPE_TXT || !param.getRequired() || !param.getEnabled()) {
                throw new UserOperationException("表达式错误，参数不存在、未启用、不属于此业务类型或者参数无法参入计算。");
            }
        }
        BusinessType model = new BusinessType();
        model.setTypeId(typeId);
        model.setExpression(expression);
        businessTypeMapper.updateByPrimaryKeySelective(model);
        BusinessType after = businessTypeMapper.selectDetailsByPrimaryKey(typeId);
        String afterEx = after.magicEx();
        businessTypeHistoryService.addHistory(model.getTypeId(), before.getTypeName(), "新增/修改公式。", note, beforeEx, afterEx, operator);
    }

    @Override
    public void updateSerialNo(Map<Integer, Integer> map) {
        for (Integer id : map.keySet()) {
            BusinessType model = businessTypeMapper.selectByPrimaryKey(id);
            model.setTypeId(id);
            model.setSerialNo(map.get(id));
            businessTypeMapper.updateByPrimaryKeySelective(model);
        }
    }

    @Override
    @Transactional
    public BusinessType creatBusinessType(String name, Integer serialNo, String operator) {
        BusinessType type = businessTypeMapper.selectByNameForUpdate(name);
        if (type != null) {
            throw new UserOperationException("此业务类型名称已存在。");
        }
        Date now = new Date();
        BusinessType model = new BusinessType();
        model.setTypeName(name);
        model.setCreateTime(now);
        model.setModifyTime(now);
        model.setDeletable(true);
        model.setEnabled(true);
        model.setSerialNo(serialNo);
        businessTypeMapper.insertSelective(model);
        businessTypeHistoryService.addHistory(model.getTypeId(), model.getTypeName(), "新建业务类型。", null, null, null, operator);
        return model;
    }

    @Override
    @Transactional
    public void editBusinessType(int typeId, String name, Boolean enabled, String operator) {
        BusinessType type2 = businessTypeMapper.selectByPrimaryKeyForUpdate(typeId);
        Assert.notNull(type2, "此业务类型不存在。");
        if (type2.getTypeName().equals(name)) {
            name = null;
        }
        BusinessType type1 = businessTypeMapper.selectByNameForUpdate(name);
        if (type1 != null && name != null) {
            throw new UserOperationException("此业务类型名称已存在。");
        }
        if (!type2.getDeletable() && name != null) {
            throw new UserOperationException("此业务类型已被使用，名称无法修改。");
        }
        BusinessType model = new BusinessType();
        model.setTypeId(typeId);
        model.setTypeName(name);
        model.setModifyTime(new Date());
        model.setEnabled(enabled);
        businessTypeMapper.updateByPrimaryKeySelective(model);
        if (name != null) {
            String ex = businessTypeMapper.selectDetailsByPrimaryKey(typeId).magicEx();
            businessTypeHistoryService.addHistory(model.getTypeId(), name, "修改业务类型名称：名称从【" + type2.getTypeName() + "】修改为【" + name + "】", null, ex, ex, operator);
        }
    }

    @Override
    @Transactional
    public void deleteBusinessType(int typeId, String operator) {
        BusinessType type = businessTypeMapper.selectDetailsByPrimaryKey(typeId);
        Assert.notNull(type, "此业务类型不存在。");
        if (!type.getDeletable()) {
            throw new UserOperationException("此业务类型已被使用，无法删除。");
        }
        paramValueMapper.deleteByTypeId(typeId);
        paramMapper.deleteByTypeId(typeId);
        businessTypeMapper.deleteByPrimaryKey(typeId);
        businessTypeHistoryService.addHistory(typeId, type.getTypeName(), "删除业务类型。", null, type.magicEx(), null, operator);
    }

    @Override
    public List<BusinessType> getAll() {
        return businessTypeMapper.selectAll();
    }

    @Override
    public List<BusinessType> getEnabledTypes() {
        return businessTypeMapper.selectAllByExpressionNotNull();
    }

    @Override
    public BusinessType getDetails(int typeId) {
        return businessTypeMapper.selectEnabledDetailsByPrimaryKey(typeId);
    }

    @Override
    public void use(int typeId) {
        BusinessType model = new BusinessType();
        model.setTypeId(typeId);
        model.setDeletable(false);
        businessTypeMapper.updateByPrimaryKeySelective(model);
    }
}
