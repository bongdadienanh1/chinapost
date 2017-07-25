package com.ylife.system.mapper;

import com.ylife.system.model.DeliveryInform;

import java.util.List;

public interface DeliveryInformMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeliveryInform record);

    int insertSelective(DeliveryInform record);

    DeliveryInform selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeliveryInform record);

    int updateByPrimaryKey(DeliveryInform record);

    DeliveryInform selectByManagerId(Long managerId);

    int updateReadById(Long id);

    int batchInsert(List<DeliveryInform> list);
}