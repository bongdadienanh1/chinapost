package com.ylife.inventory.service;

import com.ylife.inventory.model.InventoryHistory;

import java.util.Date;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2016/11/21.
 */
public interface InventoryHistoryService {



    void insertByBatch();

    void insertTemporary();

    /**
     * 定时任务
     * 按时间统计库存变动信息
     * @param start
     * @param end
     * @return
     */
    List<InventoryHistory> statisticInventoryHistory(Date start,Date end);









}
