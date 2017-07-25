package com.ylife.order.mapper;


import com.ylife.order.model.CreditOrderLog;

import java.util.List;

public interface CreditOrderLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CreditOrderLog record);

    int insertSelective(CreditOrderLog record);

    CreditOrderLog selectByPrimaryKey(Long id);

    List<CreditOrderLog> selectByCreditOrderId(Long creditOrderId);

    int updateByPrimaryKeySelective(CreditOrderLog record);

    int updateByPrimaryKey(CreditOrderLog record);
}