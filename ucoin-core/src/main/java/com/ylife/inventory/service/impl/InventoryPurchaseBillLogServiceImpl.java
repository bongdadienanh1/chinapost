package com.ylife.inventory.service.impl;

import com.ylife.inventory.mapper.InventoryPurchaseBillLogMapper;
import com.ylife.inventory.model.InventoryPurchaseBillLog;
import com.ylife.inventory.service.InventoryPurchaseBillLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by XuKai on 2016/6/20.
 * InventoryBillLogServiceImpl
 */
@Service
public class InventoryPurchaseBillLogServiceImpl implements InventoryPurchaseBillLogService {

    @Resource
    private InventoryPurchaseBillLogMapper inventoryPurchaseBillLogMapper;

    @Override
    public void addPurchaseBillLog(long operatorId, long purchaseId, String message, String action) {
        InventoryPurchaseBillLog log = new InventoryPurchaseBillLog();
        log.setOperatorId(operatorId);
        log.setPurchaseId(purchaseId);
        log.setCreateTime(new Date());
        log.setLogAction(action);
        log.setLogMsg(message);
        inventoryPurchaseBillLogMapper.insertSelective(log);
    }
}
