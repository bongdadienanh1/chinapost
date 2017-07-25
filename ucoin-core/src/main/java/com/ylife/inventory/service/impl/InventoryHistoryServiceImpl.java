package com.ylife.inventory.service.impl;

import com.ylife.inventory.mapper.InventoryHistoryMapper;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryHistory;
import com.ylife.inventory.service.InventoryHistoryService;
import com.ylife.utils.DateUtil;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2016/11/21.
 */

@Service
@Component
public class InventoryHistoryServiceImpl implements InventoryHistoryService {

    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private InventoryHistoryMapper inventoryHistoryMapper;

    @Override
    public void insertByBatch() {
        Date recordTime=new Date();
        Date start= DateUtil.getFirstDayOfMonth(recordTime);
        Date end = DateUtil.getLastDayOfMonth(recordTime);
        List<Inventory> inventories=inventoryMapper.getAllGoodsInventory();
        List<InventoryHistory> inventoryHistories=inventoryHistoryMapper.statisticInventoryHistory(start,end);
        for(Inventory inventory:inventories){
            InventoryHistory inventoryHistory=new InventoryHistory();
            inventoryHistory.setInventoryAmount(inventory.getInventory());
            inventoryHistory.setEnterpriseId(inventory.getEnterpriseId());
            inventoryHistory.setGoodsInfoId(inventory.getGoodsId());
            inventoryHistories.add(inventoryHistory);
        }
        //recordTime=DateUtil.getNight(recordTime);
        inventoryHistoryMapper.insertByBatch(inventoryHistories,end);
    }


    @Override
    public void insertTemporary() {
        Date recordTime=DateUtil.fromString("2016-11-30","yyyy-MM-dd");
        Date start= DateUtil.getFirstDayOfMonth(recordTime);
        Date end = DateUtil.getLastDayOfMonth(recordTime);
        List<Inventory> inventories=inventoryMapper.getAllGoodsInventory();
        List<InventoryHistory> inventoryHistories=inventoryHistoryMapper.statisticInventoryHistory(start,end);
        for(Inventory inventory:inventories){
            InventoryHistory inventoryHistory=new InventoryHistory();
            inventoryHistory.setInventoryAmount(inventory.getInventory());
            inventoryHistory.setEnterpriseId(inventory.getEnterpriseId());
            inventoryHistory.setGoodsInfoId(inventory.getGoodsId());
            inventoryHistories.add(inventoryHistory);
        }
        //recordTime=DateUtil.getNight(recordTime);
        inventoryHistoryMapper.insertByBatch(inventoryHistories,end);
    }

    @Override
    public List<InventoryHistory> statisticInventoryHistory(Date start, Date end) {
        return inventoryHistoryMapper.statisticInventoryHistory(start,end);
    }


}
