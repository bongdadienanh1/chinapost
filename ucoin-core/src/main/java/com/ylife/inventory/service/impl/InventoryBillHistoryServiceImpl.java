package com.ylife.inventory.service.impl;

import com.ylife.inventory.mapper.InventoryBillHistoryMapper;
import com.ylife.inventory.model.InventoryBillHistory;
import com.ylife.inventory.model.InventoryBillHistoryKey;
import com.ylife.inventory.service.InventoryBillHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by InThEnd on 2016/6/15.
 * InventoryBillHistoryServiceImpl
 */
@Service
public class InventoryBillHistoryServiceImpl implements InventoryBillHistoryService {

    @Resource
    private InventoryBillHistoryMapper inventoryBillHistoryMapper;

    @Override
    public void operate(long enterpriseId, long billId) {
        InventoryBillHistory history = inventoryBillHistoryMapper.selectByPrimaryKey(new InventoryBillHistoryKey(enterpriseId, billId));
        if (history == null) {
            history = new InventoryBillHistory();
            history.setEnterpriseId(enterpriseId);
            history.setBillId(billId);
            history.setOperateTime(new Date());
            inventoryBillHistoryMapper.insertSelective(history);
        } else {
            history.setOperateTime(new Date());
            inventoryBillHistoryMapper.updateByPrimaryKey(history);
        }
    }
}
