package com.ylife.inventory.mapper;

import com.ylife.inventory.model.InventoryPurchaseBillLog;

public interface InventoryPurchaseBillLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(InventoryPurchaseBillLog record);

    int insertSelective(InventoryPurchaseBillLog record);

    InventoryPurchaseBillLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(InventoryPurchaseBillLog record);

    int updateByPrimaryKey(InventoryPurchaseBillLog record);
}