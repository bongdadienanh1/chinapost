package com.ylife.order.mapper;


import com.ylife.order.model.OrderProduct;

public interface OrderProductMapper {
    int deleteByPrimaryKey(Long orderGoodsId);

    int insert(OrderProduct record);

    int insertSelective(OrderProduct record);

    OrderProduct selectByPrimaryKey(Long orderGoodsId);

    int updateByPrimaryKeySelective(OrderProduct record);

    int updateByPrimaryKey(OrderProduct record);
}