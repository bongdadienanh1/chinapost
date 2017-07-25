package com.ylife.inventory.service.impl;

import com.ylife.inventory.mapper.InventoryBillLogMapper;
import com.ylife.inventory.model.InventoryBillLog;
import com.ylife.inventory.service.InventoryBillLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by XuKai on 2016/6/20.
 * InventoryBillLogServiceImpl
 */
@Service
public class InventoryBillLogServiceImpl implements InventoryBillLogService {

    @Resource
    private InventoryBillLogMapper inventoryBillLogMapper;

    @Override
    public void addBillLog(long operatorId, long billId, String message, String action) {
        InventoryBillLog log = new InventoryBillLog();
        log.setOperatorId(operatorId);
        log.setBillId(billId);
        log.setCreateTime(new Date());
        log.setLogAction(action);
        log.setLogMsg(message);
        inventoryBillLogMapper.insertSelective(log);
    }
}
