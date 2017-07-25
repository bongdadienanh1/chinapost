package com.ylife.inventory.service;

import com.ylife.inventory.model.InventoryChangeHistory;

/**
 * Created by XiaoBiaoGe on 2016/11/20.
 */
public interface InventoryChangeService {

    int deleteByPrimaryKey(Long id);

    int insert(InventoryChangeHistory record);

    int insertSelective(InventoryChangeHistory record);

    InventoryChangeHistory getByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryChangeHistory record);

    int updateByPrimaryKey(InventoryChangeHistory record);

    int updateByInventory(Long id,Long goodsInfoId);
}
