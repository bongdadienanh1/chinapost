package com.ylife.inventory.service.impl;

import com.ylife.inventory.mapper.InventoryBillItemDetailMapper;
import com.ylife.inventory.mapper.InventoryChangeHistoryMapper;
import com.ylife.inventory.model.InventoryChangeHistory;
import com.ylife.inventory.service.InventoryChangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by XiaoBiaoGe on 2016/11/20.
 */
@Service
public class InventoryChangeServiceImpl implements InventoryChangeService {
    @Resource
    private InventoryBillItemDetailMapper inventoryBillItemDetailMapper;
    @Resource
    private InventoryChangeHistoryMapper inventoryChangeHistoryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return inventoryChangeHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(InventoryChangeHistory record) {
        return inventoryChangeHistoryMapper.insert(record);
    }

    @Override
    public int insertSelective(InventoryChangeHistory record) {
        return inventoryChangeHistoryMapper.insertSelective(record);
    }

    @Override
    public InventoryChangeHistory getByPrimaryKey(Long id) {
        return inventoryChangeHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(InventoryChangeHistory record) {
        return inventoryChangeHistoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InventoryChangeHistory record) {
        return inventoryChangeHistoryMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByInventory(Long id, Long goodsInfoId) {
        return inventoryBillItemDetailMapper.updateByInventory(id,goodsInfoId);
    }
}