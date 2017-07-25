package com.ylife.system.mapper;


import com.ylife.system.model.BusinessType;

import java.util.List;

public interface BusinessTypeMapper {

    int deleteByPrimaryKey(Integer typeId);

    int insert(BusinessType record);

    int insertSelective(BusinessType record);

    BusinessType selectByPrimaryKey(Integer typeId);

    BusinessType selectByPrimaryKeyForUpdate(Integer typeId);

    int updateByPrimaryKeySelective(BusinessType record);

    int updateByPrimaryKey(BusinessType record);

    List<BusinessType> selectAll();

    List<BusinessType> selectAllByExpressionNotNull();

    BusinessType selectByNameForUpdate(String name);

    BusinessType selectEnabledDetailsByPrimaryKey(int typeId);

    BusinessType selectDetailsByPrimaryKey(int typeId);

}