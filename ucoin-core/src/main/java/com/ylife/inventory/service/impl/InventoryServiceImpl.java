package com.ylife.inventory.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.exception.RecordNotFoundException;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.*;
import com.ylife.inventory.service.InventoryChangeService;
import com.ylife.inventory.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XuKai on 2016/4/15.
 * InventoryServiceImpl
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Resource
    private InventoryMapper inventoryMapper;

    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;

    @Resource
    private InventoryChangeService inventoryChangeService;

    @Override
    public Inventory selectByPrimaryKey(InventoryKey key) {
        return inventoryMapper.selectByPrimaryKey(key);
    }

    @Override
    public Inventory selectByPrimaryKeyForUpdate(InventoryKey key) {
        return inventoryMapper.selectByPrimaryKeyForUpdate(key);
    }

    @Override
    public int updateByPrimaryKeySelective(Inventory inventory) {
        return inventoryMapper.updateByPrimaryKeySelective(inventory);
    }

    @Override
    public Inventory getInventory(long enterpriseId, long goodsInfoId) {
        return inventoryMapper.selectByPrimaryKey(new InventoryKey(enterpriseId, goodsInfoId));
    }

    @Override
    @Transactional
    public void setGoods(long enterpriseId, Map<Long, Integer> goods,Long code) {

        InventoryChangeHistory changeHistory=new InventoryChangeHistory();
        changeHistory.setInventoryChangeType(InventoryChangeType.GOODSINFO_INSTOCK);
        changeHistory.setChangeTime(new Date());
        changeHistory.setEnterpriseId(enterpriseId);
        for (long goodsId : goods.keySet()) {
            InventoryKey key = new InventoryKey(enterpriseId, goodsId);
            Integer amount = goods.get(goodsId);
            if (amount == null || amount <= 0) {
                throw new UserOperationException("库存不能小于或者等于0。");
            } else if (inventoryMapper.selectByPrimaryKeyForUpdate(key) != null) {
                addActuallyInventory(key, amount);
                addAvailableInventory(key, amount);
            } else {
                Inventory inventory = new Inventory(new InventoryKey(enterpriseId, goodsId), amount, amount,null);
                inventoryMapper.insertSelective(inventory);
            }
            changeHistory.setCode(code);
            changeHistory.setGoodsInfoId(goodsId);
            changeHistory.setInventoryChangeAmount(amount);
            changeHistory.setInventoryAfterChange(inventoryMapper.selectByPrimaryKey(key).getInventory());
            inventoryChangeService.insertSelective(changeHistory);
        }
    }
    @Override
    @Transactional
    public void setGoodss(long enterpriseId, List<InventoryBillItem> goods,Long code) {

        InventoryChangeHistory changeHistory=new InventoryChangeHistory();
        changeHistory.setInventoryChangeType(InventoryChangeType.GOODSINFO_INSTOCK);
        changeHistory.setChangeTime(new Date());
        changeHistory.setEnterpriseId(enterpriseId);
     for (int i=0;i<goods.size();i++){
         Long goodsId = goods.get(i).getGoodsInfoId();
            InventoryKey key = new InventoryKey(enterpriseId, goodsId);
            Integer amount = goods.get(i).getAmount();
            if (amount == null || amount <= 0) {
                throw new UserOperationException("库存不能小于或者等于0。");
            } else if (inventoryMapper.selectByPrimaryKeyForUpdate(key) != null) {
                addActuallyInventory(key, amount);
                addAvailableInventory(key, amount);
            } else {
                Inventory inventory = new Inventory(new InventoryKey(enterpriseId, goodsId), amount, amount,null);
                inventoryMapper.insertSelective(inventory);
            }
            changeHistory.setCode(code);
            changeHistory.setGoodsInfoId(goodsId);
            changeHistory.setInventoryChangeAmount(amount);
            changeHistory.setInventoryAfterChange(inventoryMapper.selectByPrimaryKey(key).getInventory());
            inventoryChangeService.insertSelective(changeHistory);
        }
    }
    @Override
    public void deleteGood(long enterpriseId, long goodsId) {
        InventoryKey key = new InventoryKey(enterpriseId, goodsId);
        Inventory inventory = inventoryMapper.selectByPrimaryKey(key);
        if (inventory == null) {
            return;
        }
        if (inventory.getInventory() == 0 & inventory.getAvailable() == 0) {
            inventoryMapper.deleteByPrimaryKey(key);
        } else {
            throw new UserOperationException("货品库存不为空，无法删除！");
        }
    }

    @Override
    public int getWarning(long enterpriseId) {
        return inventoryMapper.selectWarning(enterpriseId);
    }

    @Override
    @Transactional
    public void editInventory(long enterpriseId, long goodsId, int inventory) {
        InventoryKey key = new InventoryKey(enterpriseId, goodsId);
        Inventory invent = inventoryMapper.selectByPrimaryKeyForUpdate(key);
        if (invent == null) {
            throw new RecordNotFoundException("库存中不存在此商品。");
        }
        int difference = inventory - invent.getInventory();
        addActuallyInventory(key, difference);
        addAvailableInventory(key, difference);
    }

    @Transactional
    public void addActuallyInventory(InventoryKey key, int add) {

        int result = inventoryMapper.addActuallyInventory(key, add);
        if (result == 0) {
            Inventory inventory = new Inventory(key, add, 0,null);
            inventoryMapper.insert(inventory);
        }
        Inventory inventory = inventoryMapper.selectByPrimaryKey(key);
        if (inventory.getInventory() < 0) {
            throw new UserOperationException("库存不足。");
        }
        if(inventory.getInventory()==0){
            long id =key.getEnterpriseId();
            long goodsInfoId=key.getGoodsId();
            inventoryChangeService.updateByInventory(id,goodsInfoId);
        }
    }

    @Transactional
    public void addAvailableInventory(InventoryKey key, int add) {
        int result = inventoryMapper.addAvailableInventory(key, add);
        if (result == 0) {
            Inventory inventory = new Inventory(key, 0, add,null);
            inventoryMapper.insert(inventory);
        }
        Inventory inventory = inventoryMapper.selectByPrimaryKey(key);
        if (inventory.getAvailable() < 0) {
            throw new UserOperationException("可用库存不足。");
        }
    }

    @Override
    @Transactional
    public void setAvailable(long enterpriseId, long goodsId, int available) {
        InventoryKey key = new InventoryKey(enterpriseId, goodsId);
        Inventory invent = inventoryMapper.selectByPrimaryKeyForUpdate(key);
        if (invent == null) {
            throw new RecordNotFoundException("库存中不存在此商品。");
        }
        int difference = available - invent.getAvailable();
        addAvailableInventory(key, difference);
    }

    @Override
    public Page<InventoryGoodsResult> queryByInventory(Long enterpriseId, String goodsInfoName,
                                                       String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectEndEnterpriseidByCatalog(minCatalog, maxCatalog, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countEndEnterpriseGoods(minCatalog, maxCatalog, goodsInfoName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }


    @Override
    public Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                                       String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatage = enterpriseFunction.getMinCatalog();
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectTotalInventoryGoodsByCatage(minCatage, maxCatalog, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countTotalInventoryGoodsByCatage(minCatage, maxCatalog, goodsInfoName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public Page<InventoryGoodsResult> getGoodsInventory(Long enterpriseId, Long goodsInfoId, String goodsInfoName,Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId, Pageable pageable,String goodsInfoAdded) {
        List<InventoryGoodsResult> inventoryGoodsResultList = inventoryMapper.selectGoodsAndInventory(enterpriseId, goodsInfoId, goodsInfoName,goodsInfoType, goodsNumber, brandId, thirdId, pageable,goodsInfoAdded);
        int totalElements = inventoryMapper.countGoodsAndInventory(enterpriseId, goodsInfoId, goodsInfoName,goodsInfoType, goodsNumber, brandId, thirdId,goodsInfoAdded);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResultList);
    }


    @Override
    public List<InventoryGoodsResult> selectGoodsAndAvailable(Long enterpriseId, List<Long> goodsIds) {
        return inventoryMapper.selectGoodsAndAvailable(enterpriseId, goodsIds);
    }

    @Override
    public Page<InventoryGoodsResult> getWarningGoods(Long enterpriseId,String goodsName, String goodsNumber, Long brandId, Long thirdId,Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResultList= inventoryMapper.selectWarningGoodsByInventoryKey(enterpriseId,goodsName,goodsNumber,brandId,thirdId,pageable);
        int totalElements=inventoryMapper.countWarningGoodsByInventoryKey(enterpriseId,goodsName,goodsNumber,brandId,thirdId);
        return new PageImpl<>(pageable,totalElements,inventoryGoodsResultList);
    }

    @Override
    public List<Inventory> getAllGoodsInventory() {
        return inventoryMapper.getAllGoodsInventory();
    }
}
