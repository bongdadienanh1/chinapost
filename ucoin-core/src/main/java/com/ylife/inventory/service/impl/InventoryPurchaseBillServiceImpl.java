package com.ylife.inventory.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.InventoryPurchaseBillItemMapper;
import com.ylife.inventory.mapper.InventoryPurchaseBillMapper;
import com.ylife.inventory.model.InventoryPurchaseBill;
import com.ylife.inventory.model.InventoryPurchaseBillItem;
import com.ylife.inventory.model.PurchaseBillStatus;
import com.ylife.inventory.service.InventoryPurchaseBillLogService;
import com.ylife.inventory.service.InventoryPurchaseBillService;
import com.ylife.utils.Assert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by suibian on 2017/2/17.
 */
@Service
public class InventoryPurchaseBillServiceImpl implements InventoryPurchaseBillService {
    @Resource
    private InventoryPurchaseBillMapper inventoryPurchaseBillMapper;
    @Resource
    private InventoryPurchaseBillItemMapper inventoryPurchaseBillItemMapper;
    @Resource
    private InventoryPurchaseBillLogService inventoryPurchaseBillLogService;

    private Generator generator = IdGeneratorFactory.create("INVENTORY_BILL");

    @Override
    public void createPurchaseBill(Map<Long,List<InventoryPurchaseBillItem>> groupByThirdMap, Long billId,Long enterpriseId,String marks) {
        Assert.notEmpty(groupByThirdMap,"服务器内部错误！");
        for(Long key : groupByThirdMap.keySet()){
            InventoryPurchaseBill purchaseBill = new InventoryPurchaseBill();
            purchaseBill.setThirdId(key);
            purchaseBill.setCode(generator.generate());
            purchaseBill.setStatus(PurchaseBillStatus.TO_CONFIRM);
            purchaseBill.setCreateTime(new Date());
            purchaseBill.setBillId(billId);
            purchaseBill.setMarks(marks);
            inventoryPurchaseBillMapper.insertSelective(purchaseBill);
            Long purchaseId = purchaseBill.getPurchaseId();
            for(InventoryPurchaseBillItem purchaseBillItem:groupByThirdMap.get(key)){
                purchaseBillItem.setPurchaseId(purchaseId);
                inventoryPurchaseBillItemMapper.insertSelective(purchaseBillItem);
            }
            inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId,purchaseId,null, "[创建]");
        }
    }

    @Override
    public InventoryPurchaseBill getSumDetailByPrimaryKey(Long purchaseId) {
        return inventoryPurchaseBillMapper.selectSumDetailByPrimaryKey(purchaseId);
    }

    @Override
    public InventoryPurchaseBill getPurchaseDetailByPrimaryKey(Long purchaseId) {
        return inventoryPurchaseBillMapper.selectPurchaseDetailByPrimaryKey(purchaseId);
    }

    @Override
    @Transactional
    public void updateSettlePrice(Long purchaseId,Long goodsInfoId,BigDecimal settlePrice){
        inventoryPurchaseBillItemMapper.updatePurchaseGoodsId(settlePrice,purchaseId,goodsInfoId);
    }

    @Override
    public void deleteByGoodsInfoId(@Param("purchaseId")Long purchaseId, @Param("goodsInfoIds")Long[] goodsInfoIds, @Param("itemIds")Long[] itemIds){
        inventoryPurchaseBillItemMapper.deleteByGoodsInfoId(purchaseId,goodsInfoIds,itemIds);
    }

    @Override
    public void updateByPrimaryKey(Long itemId,Integer amount){
        inventoryPurchaseBillItemMapper.updateByItemId(itemId,amount);
    }

    @Override
    public Page<InventoryPurchaseBill> getPurchaseBillList(@Param("code") Long code, @Param("status") PurchaseBillStatus status, @Param("start") Date start, @Param("end") Date end,@Param("thirdId") Long thirdId, @Param("pageable") Pageable pageable) {
        List<InventoryPurchaseBill> billList = inventoryPurchaseBillMapper.selectPurchaseBillList(code,status,start,end,thirdId,pageable);
        int totalElements = inventoryPurchaseBillMapper.countPurchaseBillList(code,status,start,end,thirdId,pageable);
        return new PageImpl<>(pageable, totalElements, billList);
    }

    @Override
    public void confirmPurchaseBill(long purchaseId,String marks){
        InventoryPurchaseBill bill =  inventoryPurchaseBillMapper.selectByPrimaryKey(purchaseId);
        if(bill == null){
            throw new IllegalArgumentException("此单据无法进行确认操作。");
        }
        Integer purchaseAmount = inventoryPurchaseBillItemMapper.selectSumAmountByKey(purchaseId);
        bill.setPurchaseAmount(purchaseAmount);
        bill.setStatus(PurchaseBillStatus.FINISHED);
        bill.setMarks(marks);
        inventoryPurchaseBillMapper.updateByPrimaryKeySelective(bill);
    }

    @Override
    public void terminatePurchaseBill(long purchaseId) {
        InventoryPurchaseBill bill =  inventoryPurchaseBillMapper.selectByPrimaryKey(purchaseId);
        if(bill == null){
            throw new IllegalArgumentException("此单据无法进行确认操作。");
        }
        bill.setStatus(PurchaseBillStatus.TERMINATED);
        inventoryPurchaseBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    public void pendingPurchaseBill(long purchaseId){
        InventoryPurchaseBill bill =  inventoryPurchaseBillMapper.selectByPrimaryKey(purchaseId);
        if(bill == null){
            throw new IllegalArgumentException("此单据无法进行确认操作。");
        }
        bill.setStatus(PurchaseBillStatus.COMMODITY_STORAGE);
        inventoryPurchaseBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    public void updateReceiptAmountByGoodsInfoId(int receiptAmount, Long purchaseId, Long enterpriseId, Long goodsInfoId) {
        inventoryPurchaseBillItemMapper.updateReceiptAmountByGoodsInfoId(receiptAmount,purchaseId,enterpriseId,goodsInfoId);
    }

    @Override
    public InventoryPurchaseBill getByPrimaryKey(Long purchaseId) {
        return inventoryPurchaseBillMapper.selectByPrimaryKey(purchaseId);
    }

    @Override
    public void updateByPrimaryKeySelective(InventoryPurchaseBill record) {
        inventoryPurchaseBillMapper.updateByPrimaryKeySelective(record);
    }

	//	按条件查询采购订单
	@Override
	public List<InventoryPurchaseBillItem> selectPurchaseBillListByCondition(@Param("code") Long code, @Param("status") PurchaseBillStatus status, @Param("start") Date start, @Param("end") Date end, @Param("thirdId") Long thirdId) {
		return inventoryPurchaseBillItemMapper.selectPurchaseBillListByCondition(code, status, start, end, thirdId);
	}


	@Override
    public int getPurchaseCountByBillId(Long billId) {
        return inventoryPurchaseBillMapper.selectPurchaseCountByBillId(billId);
    }

}
