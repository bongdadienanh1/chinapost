package com.ylife.inventory.mapper;

import com.ylife.inventory.model.InventoryBillHistory;
import com.ylife.inventory.model.InventoryBillHistoryKey;

public interface InventoryBillHistoryMapper {

    int deleteByPrimaryKey(InventoryBillHistoryKey key);

    int insert(InventoryBillHistory record);

    int insertSelective(InventoryBillHistory record);

    InventoryBillHistory selectByPrimaryKey(InventoryBillHistoryKey key);

    int updateByPrimaryKeySelective(InventoryBillHistory record);

    int updateByPrimaryKey(InventoryBillHistory record);
}