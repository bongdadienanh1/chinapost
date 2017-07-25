package com.ylife.inventory.mapper;

import com.ylife.inventory.model.InventoryBillItem;

import java.util.List;

public interface InventoryBillItemMapper {

    int deleteByPrimaryKey(Long itemId);

    int deleteByBillId(Long billId);

    int insert(InventoryBillItem record);

    int insertSelective(InventoryBillItem record);

    InventoryBillItem selectByPrimaryKey(Long itemId);

    List<InventoryBillItem> selectByBillId(Long billId);

    int updateByPrimaryKeySelective(InventoryBillItem record);

    int updateByPrimaryKey(InventoryBillItem record);


}