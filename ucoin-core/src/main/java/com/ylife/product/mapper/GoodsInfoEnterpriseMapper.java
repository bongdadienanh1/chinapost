package com.ylife.product.mapper;

import com.ylife.product.model.GoodsInfoEnterpriseKey;

public interface GoodsInfoEnterpriseMapper {

    int deleteByPrimaryKey(GoodsInfoEnterpriseKey key);

    int insert(GoodsInfoEnterpriseKey record);

    int insertSelective(GoodsInfoEnterpriseKey record);
}