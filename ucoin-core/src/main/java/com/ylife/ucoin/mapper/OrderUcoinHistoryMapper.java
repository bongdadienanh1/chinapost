package com.ylife.ucoin.mapper;

import com.ylife.ucoin.model.OrderUcoinHistory;

public interface OrderUcoinHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderUcoinHistory record);

    int insertSelective(OrderUcoinHistory record);

    OrderUcoinHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderUcoinHistory record);

    int updateByPrimaryKey(OrderUcoinHistory record);
}