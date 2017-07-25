package com.ylife.customer.mapper;

import com.ylife.customer.model.CustomerEnterpriseKey;

public interface CustomerEnterpriseMapper {

    int deleteByPrimaryKey(CustomerEnterpriseKey key);

    int insert(CustomerEnterpriseKey record);

    int insertSelective(CustomerEnterpriseKey record);
}