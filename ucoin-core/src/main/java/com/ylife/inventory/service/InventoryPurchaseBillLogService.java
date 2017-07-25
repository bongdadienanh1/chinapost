package com.ylife.inventory.service;

/**
 * Created by XuKai on 2016/6/20.
 * 单据日志服务
 */
public interface InventoryPurchaseBillLogService {

    void addPurchaseBillLog(long operatorId, long purchaseId, String message, String action);
}
