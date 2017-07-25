package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.DataAccessService;
import com.ylife.exception.AccessRecordException;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.model.InventoryBill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/6/16.
 * DataAccessServiceImpl
 */
@Service
public class DataAccessServiceImpl implements DataAccessService {

    @Resource
    private InventoryBillMapper inventoryBillMapper;

    @Override
    public void enterpriseAcessBill(long enterpriseId, long billId) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKey(billId);
        if (enterpriseId != bill.getCurrentId()) {
            throw new AccessRecordException("无法访问此单据。");
        }
    }
}
