package com.ylife.inventory.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryBillItemMapper;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.mapper.InventoryBillItemDetailMapper;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;
import com.ylife.inventory.service.*;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/6/14.
 * InventoryBillServiceImpl
 */
@Service
public class InventoryBillServiceImpl implements InventoryBillService {

    @Resource
    private InventoryBillMapper inventoryBillMapper;
    @Resource
    private InventoryBillItemMapper inventoryBillItemMapper;
    @Resource
    private InventoryBillItemDetailMapper inventoryBillItemDetailMapper;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private InventoryChangeService inventoryChangeService;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private InventoryBillHistoryService inventoryBillHistoryService;
    @Resource
    private InventoryBillLogService inventoryBillLogService;




    private Generator generator = IdGeneratorFactory.create("INVENTORY_BILL");

    @Override
    public InventoryBill getBillById(long id) {
        InventoryBill bill= inventoryBillMapper.selectBillDetailByPrimaryKey(id);
        return bill;
    }

    @Override
    public  List<InventoryBillLog> getBillLogById(long id) {
        return inventoryBillMapper.selectBillLog(id);
    }

    @Override
    public InventoryBill getBillByCode(long code) {
        InventoryBill bill = inventoryBillMapper.selectByCode(code);
        Assert.notNull(bill, "此单据不存在。");
        return inventoryBillMapper.selectBillDetailByPrimaryKey(bill.getBillId());
    }

    @Override
    public Page<InventoryBill> getCreatedBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        List<InventoryBill> bills = inventoryBillMapper.selectByCreatorId(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countByCreatorId(enterpriseId, code, creatorName, start, end, billStatus, billType);
        return new PageImpl<>(pageable, totalElements, bills);
    }

    @Override
    public Page<InventoryBill> getHandledBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        List<InventoryBill> handleBills = inventoryBillMapper.selectHandleHistory(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countHandleHistory(enterpriseId, code, creatorName, start, end, billStatus,
                billType);
        return new PageImpl<>(pageable, totalElements, handleBills);
    }

    @Override
    public Page<InventoryBill> getUnHandleBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                                BillType billType, Pageable pageable) {
        List<InventoryBill> bills = inventoryBillMapper.selectByCurrentId(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countByCurrentId(enterpriseId, code, creatorName, start, end, billStatus, billType);
        return new PageImpl<>(pageable, totalElements, bills);
    }

    @Override
    @Transactional
    public long createBill(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId, Long applyId, String reason, Map<Long, Integer> sku) {
        GoodsInfo goodsInfo = new GoodsInfo();
        for (Long goodsInfoId : sku.keySet()) {
            Long id = goodsInfoId;
            goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsInfoId);
            break;
        }

        Assert.notNull(type);
        Assert.notEmpty(sku);
        if (StringUtil.isBlank(reason)) {
            throw new UserOperationException("调拨原因没填写！");
        }
        InventoryBill bill = new InventoryBill();
        BillStatus status = null;
        switch (type) {
            case INVENTORY_TRANSFER:
                Assert.notNull(outId, "调出仓库没有填写！");
                Assert.notNull(inId, "调入仓没有填写！");
                status = BillStatus.WAIT_DELIVERY;
                bill.setCurrentId(outId);
                bill.setStockTime(new Date());
                break;
            case REPLENISHMENT:
            case LESS_REPORT:
            case MORE_REPORT:
                //如果自购商品，直接单据完成，并扣减库存
                if (goodsInfo.getGoodsInfoType() == 1) {
                    status = BillStatus.FINISHED;
                    bill.setCurrentId(null);
                    bill.setRecipientTime(new Date());
                    outId = inId = null;
                } else {
                    Assert.notNull(nextOperatorId);
                    outId = inId = null;
                    status = BillStatus.CHECKING;
                    bill.setCurrentId(nextOperatorId);
                }

                break;
            case INVENTORY_INSTOCK:
                outId = null;
                status = BillStatus.FINISHED;
                bill.setRecipientTime(new Date());
                break;
        }
        if (applyId != null) {
            InventoryBill applyBill = inventoryBillMapper.selectByPrimaryKey(applyId);
            bill.setApplyTime(applyBill.getCreateTime());
        }
        bill.setBillStatus(status);
        bill.setBillType(type);
        bill.setCode(generator.generate());
        bill.setCreateTime(new Date());
        bill.setCreatorId(creatorId);
        bill.setInId(inId);
        bill.setOutId(outId);
        bill.setReason(reason);
        inventoryBillMapper.insertSelective(bill);
        Long billId = bill.getBillId();
        addItems(billId, sku);
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(billId);
        if (type == BillType.REPLENISHMENT) {
            for (InventoryBillItem item : items) {
                GoodsInfo info=goodsInfoMapper.selectByPrimaryKey(item.getGoodsInfoId());
                item.setGoodsInfoUnit(info.getGoodsInfoUnit());
                item.setGoodsInfoPack(info.getGoodsInfoPack());
                item.setGoodsInfoId(info.getGoodsInfoId());
                inventoryBillItemMapper.updateByPrimaryKeySelective(item);
            }
        }

        if (goodsInfo.getGoodsInfoType() == 1) {
            if (type == BillType.MORE_REPORT) {
                addAvailableInventory(creatorId, items, false);
                addActuallyInventory(creatorId, items, false, InventoryChangeType.GOODSINFO_MORE_REPORT);
            } else if (type == BillType.LESS_REPORT) {
                addAvailableInventory(creatorId, items, true);
                addActuallyInventory(creatorId, items, true, InventoryChangeType.GOODSINFO_LESS_REPORT);
            }
            //inventoryBillHistoryService.operate(creatorId,billId);
        } else {
            if (type == BillType.LESS_REPORT) {
                addAvailableInventory(creatorId, items, true);
            }
        }
        return billId;

    }

    @Transactional
    public long createBills(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId, Long applyId, String reason, List<InventoryBillItem> sku) {
        GoodsInfo goodsInfo = new GoodsInfo();
        for (InventoryBillItem item : sku) {
            goodsInfo = goodsInfoMapper.selectByPrimaryKey(item.getGoodsInfoId());
            break;
        }

        Assert.notNull(type);
        Assert.notEmpty(sku);

        InventoryBill bill = new InventoryBill();
        BillStatus status = null;
        switch (type) {
            case INVENTORY_INSTOCK:
                outId = null;
                status = BillStatus.FINISHED;
                bill.setRecipientTime(new Date());
                break;
            case INVENTORY_TRANSFER:
                Assert.notNull(outId, "调出仓库没有填写！");
                Assert.notNull(inId, "调入仓没有填写！");
                status = BillStatus.WAIT_DELIVERY;
                bill.setCurrentId(outId);
                bill.setStockTime(new Date());
                break;
        }
        if (applyId != null) {
            InventoryBill applyBill = inventoryBillMapper.selectByPrimaryKey(applyId);
            bill.setApplyTime(applyBill.getCreateTime());
        }
        bill.setBillStatus(status);
        bill.setBillType(type);
        bill.setCode(generator.generate());
        bill.setCreateTime(new Date());
        bill.setCreatorId(creatorId);
        bill.setInId(inId);
        bill.setOutId(outId);
        bill.setReason(reason);
        inventoryBillMapper.insertSelective(bill);
        Long billId = bill.getBillId();
        addItemss(billId, sku);
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(billId);
        if (type == BillType.REPLENISHMENT) {
            for (InventoryBillItem item : items) {
                GoodsInfo info=goodsInfoMapper.selectByPrimaryKey(item.getGoodsInfoId());
                item.setGoodsInfoUnit(info.getGoodsInfoUnit());
                item.setGoodsInfoPack(info.getGoodsInfoPack());
                item.setGoodsInfoId(info.getGoodsInfoId());
                inventoryBillItemMapper.updateByPrimaryKeySelective(item);

            }
        }

        if (goodsInfo.getGoodsInfoType() == 1) {
            if (type == BillType.MORE_REPORT) {
                addAvailableInventory(creatorId, items, false);
                addActuallyInventory(creatorId, items, false, InventoryChangeType.GOODSINFO_MORE_REPORT);
            } else if (type == BillType.LESS_REPORT) {
                addAvailableInventory(creatorId, items, true);
                addActuallyInventory(creatorId, items, true, InventoryChangeType.GOODSINFO_LESS_REPORT);
            }
            //inventoryBillHistoryService.operate(creatorId,billId);
        } else {
            if (type == BillType.LESS_REPORT) {
                addAvailableInventory(creatorId, items, true);
            }
        }
        return billId;

    }

    /**
     * zx
     * @param creatorId
     * @param status
     * @param inventoryMergeGoodses
     * @param reason
     * @return
     */
    @Override
    public long createMergeBill(long creatorId, BillStatus status, List<InventoryMergeGoods> inventoryMergeGoodses, String reason) {
        Assert.notEmpty(inventoryMergeGoodses);

//        if (StringUtil.isBlank(reason)) {
//            throw new UserOperationException("调拨原因没填写！");
//        }
        InventoryBill bill = new InventoryBill();
        bill.setCode(generator.generate());
        bill.setCreatorId(creatorId);
        bill.setCurrentId(creatorId);
        if(status==BillStatus.FINISHED){
            bill.setBillStatus(BillStatus.CHECKED_NOT_PURCHASE);
        }else{
            bill.setBillStatus(status);
        }

        bill.setBillType(BillType.MERGE_REPLENISHMENT);
        bill.setCreateTime(new Date());
        bill.setReason(reason);
        inventoryBillMapper.insertSelective(bill);
        Long billId = bill.getBillId();
        for (InventoryMergeGoods inventoryMergeGoods:inventoryMergeGoodses){
            for (InventoryBillItem inventoryBillItem : inventoryMergeGoods.getItems()) {
                inventoryBillItem.setItemId(null);
                inventoryBillItem.setBillId(billId);
                GoodsInfo goodsInfo = goodsInfoService.getById(inventoryBillItem.getGoodsInfoId());
                Assert.isTrue(inventoryBillItem.getAmount() != null && inventoryBillItem.getAmount() > 0, "参数中商品名称为：" + goodsInfo.getGoodsInfoName() + "的商品数量不能为0。");
                inventoryBillItemMapper.insertSelective(inventoryBillItem);
            }
        }
        return billId;

    }


    @Override
    public List<Long> getBillIdByParentId(long parentId) {
        return inventoryBillMapper.selectBillIdByParentId(parentId);
    }

    @Override
    public List<InventoryMergeGoods> getBillDetailByBillIds(Long[] billIds) {
        return inventoryBillMapper.selectBillDetailByBillIds(billIds);
    }

    @Override
    public List<InventoryMergeGoods> getBillSumByBillIds(Long[] billIds) {
        return inventoryBillMapper.selectBillSumByBillIds(billIds);
    }

    @Override
    @Transactional
    public void approveBill(long id, Long nextOperatorId, Map<Long, Integer> amounts) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行批准操作。");
        }
        List<InventoryBillItem> items=inventoryBillItemMapper.selectByBillId(id);
        for (InventoryBillItem item : items) {
            if (item.getGoodsInfoPack() != null) {
                if (new BigDecimal(amounts.get(item.getItemId())).divideAndRemainder(item.getGoodsInfoPack())[1].compareTo(BigDecimal.ZERO) != 0) {
                    throw new UserOperationException("审核数量必须是装箱数的整数倍！");
                }
            }
        }
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setCurrentId(nextOperatorId);
        inventoryBillMapper.updateByPrimaryKeySelective(model);
        updateAmount(bill.getBillId(), amounts);
    }

    /**
     * zx
     * @param id    ID
     * @param isTop 是否为顶级账号操作
     * @param sku   商品SKU
     */
    @Override
    @Transactional
    public void agreeBill(long id, Boolean isTop, Map<Long, Integer> sku) {
        agreeBill(id,isTop,sku,null);
    }

    @Override
    @Transactional
    public void agreeBill(long id,Boolean isTop, Map<Long, Integer> sku,Long enterpriseId){
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        BillType type;
        if (bill == null || (type = bill.getBillType()) == BillType.INVENTORY_TRANSFER) {
            throw new IllegalArgumentException("此单据无法进行同意操作。");
        }
        List<InventoryBillItem> items=inventoryBillItemMapper.selectByBillId(id);
        for (InventoryBillItem item : items) {
            if (item.getGoodsInfoPack() != null) {
                if (new BigDecimal(sku.get(item.getItemId())).divideAndRemainder(item.getGoodsInfoPack())[1].compareTo(BigDecimal.ZERO) != 0) {
                    throw new UserOperationException("审核数量必须是装箱数的整数倍！");
                }
            }
        }
        BillStatus status = BillStatus.FINISHED;
        switch (type) {
            case REPLENISHMENT: //补货单
                Assert.notNull(isTop);
                if (!isTop) { //中间级
                    updateAmount(bill.getBillId(), sku);
                    status = BillStatus.CHECKED_NOT_TRANSFER;  //已审核未调拨
                    bill.setCurrentId(enterpriseId);
                }else{ //顶级
                    status = BillStatus.CHECKED_NOT_PURCHASE; //已审核未采购
                    bill.setCurrentId(enterpriseId);
                }
                break;
            case LESS_REPORT:
                //操作库存,记录库存变动
                //List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
                //addAvailableInventory(bill.getCreatorId(), items, true);
                addActuallyInventory(bill.getCreatorId(), items, true, InventoryChangeType.GOODSINFO_LESS_REPORT);
                enterpriseId = null;
                break;
            case MORE_REPORT:
                //操作库存,记录库存变动
                // items = inventoryBillItemMapper.selectByBillId(id);
                addAvailableInventory(bill.getCreatorId(), items, false);
                addActuallyInventory(bill.getCreatorId(), items, false, InventoryChangeType.GOODSINFO_MORE_REPORT);
                enterpriseId = null;
                break;
        }
        //判断代办已办
        bill.setCurrentId(enterpriseId);
     /*  bill.setCurrentId(null);*/
        bill.setBillStatus(status);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    @Transactional
    public void terminateBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null) {
            throw new IllegalArgumentException("此单据无法进行终止操作。");
        }
        if (bill.getBillType() == BillType.LESS_REPORT && bill.getBillStatus()==BillStatus.CHECKING) {
            List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(bill.getBillId());
            addAvailableInventory(bill.getCreatorId(), items, false);
        }
	    if (bill.getBillType() == BillType.INVENTORY_TRANSFER && bill.getBillStatus() == BillStatus.PARTIAL_RECEIPT) {
		    List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(bill.getBillId());
		    returnAvailableInventory(bill.getOutId(), items);
	    }
        bill.setCurrentId(null);
        bill.setBillStatus(BillStatus.TERMINATED);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    @Transactional
    public void finishBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行完成操作。");
        }
        bill.setBillStatus(BillStatus.FINISHED);
        bill.setCurrentId(null);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }


    @Override
    @Transactional
    public void backBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        BillType type;
        if (bill == null || (type = bill.getBillType()) == BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行退回操作。");
        }
        BillStatus status = BillStatus.WAIT_EDIT;
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(status);
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        switch (type) {
            case LESS_REPORT:
                //操作可用库存
                addAvailableInventory(bill.getCreatorId(), items, false);
            case MORE_REPORT:
                model.setCurrentId(bill.getCreatorId());
                break;
            case INVENTORY_TRANSFER:
                model.setCurrentId(bill.getOutId());
                //操作库存
                addAvailableInventory(bill.getOutId(), items, false);
                break;
        }
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public void submitBill(long id, Long nextOperatorId, Map<Long, Integer> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || (bill.getBillType() != BillType.LESS_REPORT && bill.getBillType() != BillType.MORE_REPORT)) {
            throw new IllegalArgumentException("此单据无法进行提交操作。");
        }
        BillType type=bill.getBillType();

        inventoryBillItemMapper.deleteByBillId(id);
        addItems(id, sku);
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        switch (type){
            case LESS_REPORT:
                addAvailableInventory(bill.getCreatorId(),items,true);
                break;
            case MORE_REPORT:
                break;
        }
        //操作报损报溢单状态
        BillStatus status = BillStatus.CHECKING;
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(status);
        model.setCurrentId(nextOperatorId);
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public void deliveryBill(long id, Map<Long, Integer> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKey(id);
        if (bill == null || bill.getBillType() != BillType.INVENTORY_TRANSFER) {
            throw new IllegalArgumentException("此单据无法进行发货操作。");
        }
        if (bill.getBillStatus() == BillStatus.WAIT_EDIT) {
            Assert.notEmpty(sku);
            inventoryBillItemMapper.deleteByBillId(bill.getBillId());
            addItems(bill.getBillId(), sku);
        }
        //操作库存
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        addAvailableInventory(bill.getOutId(), items, true);
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(BillStatus.WAIT_RECEIVER);
        model.setCurrentId(bill.getInId());
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public Boolean receiptBill(long id, List<InventoryBillItem> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || (bill.getBillType() != BillType.INVENTORY_TRANSFER && bill.getBillType()!=BillType.PURCHASE_INVENTORY_INSTOCK)) {
            throw new IllegalArgumentException("此单据无法进行收货操作。");
        }
        Boolean isHandled = true;
        Boolean skuInvalid= true;

        Assert.notEmpty(sku, "商品收货数量没填写！");
        for (int i=0;i<sku.size();i++){
            InventoryBillItem items=sku.get(i);
            Integer   cMount=items.getAmount();

            if(cMount!=0){
                skuInvalid=false;
                break;
            }
        }

        if(skuInvalid) throw new UserOperationException("商品收货数量没填写");
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        //未收获完成的商品列表
        List<InventoryBillItem> unhandleItems = new ArrayList<>();
        for (InventoryBillItem item :items) {
            if(item.getAmount()==item.getReceiptAmount()){
                continue;
            }
            Integer receiptAmount = item.getReceiptAmount() == null ? 0 : item.getReceiptAmount();
            for (InventoryBillItem itemSku:sku){
                if(item.getGoodsInfoId().equals(itemSku.getGoodsInfoId())){

                    Integer checkAmount = itemSku.getAmount();
                    if (item.getAmount() < (checkAmount + receiptAmount)) {
                        throw new UserOperationException("商品收货数量不能大于发货数量，请检查收货数量！");
                    }
                    Date sta=null;
                    Date end = null;
                    int endWarnDay = 0;
                    sta=itemSku.getProductTime();
                    end=itemSku.getEndTime();
                    endWarnDay=itemSku.getEndWarnDay();
                    if (!Objects.equals(item.getAmount(), receiptAmount)) {
                        if (checkAmount != 0) {
                            receiptAmount = itemSku.getAmount() + receiptAmount;
                            item.setReceiptAmount(receiptAmount);

                            inventoryBillItemMapper.updateByPrimaryKey(item);
                            long itemBillId=item.getItemId();
                            InventoryBillItemDetail   itemDetail = new InventoryBillItemDetail();
                            itemDetail.setItemId(itemBillId);
                            itemDetail.setReceiptAmount(itemSku.getAmount());
                            itemDetail.setProductTime(sta);
                            itemDetail.setEndTime(end);
                            itemDetail.setCreateTime(new Date());
                            itemDetail.setEndWarnDay(endWarnDay);
                            inventoryBillItemDetailMapper.insertSelective(itemDetail);
                            InventoryBillItem unhandleItem = new InventoryBillItem();
                            unhandleItem.setBillId(item.getBillId());
                            unhandleItem.setGoodsInfoId(item.getGoodsInfoId());
                            unhandleItem.setInfo(item.getInfo());
                            unhandleItem.setItemId(item.getItemId());
                            unhandleItem.setAmount(checkAmount);
                            unhandleItems.add(unhandleItem);
                        }
                    }

                    break;
                }
            }
            if (!Objects.equals(item.getAmount(), receiptAmount)) {
                isHandled = false;
            }
        }


        //操作库存,记录库存变动
        if (bill.getBillType()==BillType.INVENTORY_TRANSFER) addActuallyInventory(bill.getOutId(), unhandleItems, true, InventoryChangeType.GOODSINFO_TRANSFER_OUT);
        if(bill.getBillType() == BillType.PURCHASE_INVENTORY_INSTOCK){
            addActuallyInventory(bill.getInId(), unhandleItems, false, InventoryChangeType.PURCHASEGOODS_INSTOCH);
        }else {
            addActuallyInventory(bill.getInId(), unhandleItems, false, InventoryChangeType.GOODSINFO_TRANSFER_IN);
        }
        addAvailableInventory(bill.getInId(), unhandleItems, false);
        if (isHandled) {
            bill.setBillStatus(BillStatus.FINISHED);
            bill.setCurrentId(null);
        } else {
            bill.setBillStatus(BillStatus.PARTIAL_RECEIPT);
            bill.setCurrentId(bill.getInId());
        }
        bill.setRecipientTime(new Date());
        inventoryBillMapper.updateByPrimaryKey(bill);

        return isHandled;
    }


    private void updateAmount(long billId, Map<Long, Integer> sku) {
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(billId);
        for (InventoryBillItem item : items) {
            InventoryBillItem itemModel = new InventoryBillItem();
            itemModel.setItemId(item.getItemId());
            Integer checkAmount = sku.get(item.getItemId());
            Assert.isTrue(checkAmount != null && checkAmount > 0, "参数中某个项目不存在或者数量小于等于0。");
            itemModel.setCheckedAmount(checkAmount);
            inventoryBillItemMapper.updateByPrimaryKeySelective(itemModel);
        }
    }

    private void addItems(long billId, Map<Long, Integer> sku) {
        for (Long infoId : sku.keySet()) {
            InventoryBillItem item = new InventoryBillItem();
            item.setBillId(billId);
            item.setGoodsInfoId(infoId);
            Integer amount = sku.get(infoId);
            GoodsInfo goodsInfo = goodsInfoService.getById(infoId);
            Assert.isTrue(amount != null && amount > 0, "参数中商品名称为：" + goodsInfo.getGoodsInfoName() + "的商品数量不能为0。");
            item.setAmount(amount);
            inventoryBillItemMapper.insertSelective(item);
        }
    }
  private void addItemss(long billId,  List<InventoryBillItem> sku) {
        for (int i =0; i<sku.size(); i++) {

            InventoryBillItem item = sku.get(i);
            item.setBillId(billId);
            item.getGoodsInfoId();
            Integer amount = item.getAmount();
            GoodsInfo goodsInfo = goodsInfoService.getById(item.getGoodsInfoId());
            Assert.isTrue(amount != null && amount > 0, "参数中商品名称为：" + goodsInfo.getGoodsInfoName() + "的商品数量不能为0。");
            inventoryBillItemMapper.insertSelective(item);
            InventoryBillItemDetail itemDetail = new InventoryBillItemDetail();
            itemDetail.setItemId(item.getItemId());
            itemDetail.setReceiptAmount(amount);
            itemDetail.setProductTime(item.getProductTime());
            itemDetail.setEndTime(item.getEndTime());
            itemDetail.setCreateTime(new Date());
            itemDetail.setEndWarnDay(item.getEndWarnDay());
            inventoryBillItemDetailMapper.insertSelective(itemDetail);

        }
   }

    private void addActuallyInventory(long enterpriseId, List<InventoryBillItem> items, Boolean out, InventoryChangeType type) {
        InventoryChangeHistory inventoryChangeHistory = new InventoryChangeHistory();
        Date recordTime = new Date();
        inventoryChangeHistory.setChangeTime(recordTime);
        inventoryChangeHistory.setEnterpriseId(enterpriseId);
        inventoryChangeHistory.setInventoryChangeType(type);
        for (InventoryBillItem item : items) {
            if (out) {
                inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), -item.getAmount());
                //inventoryChangeService.insertSelective(inventoryChangeHistory);
            } else {
                inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), item.getAmount());
                //inventoryChangeService.insertSelective(inventoryChangeHistory);
            }
            InventoryBill bill = inventoryBillMapper.selectByPrimaryKey(item.getBillId());
            Long goodsInfoId = item.getGoodsInfoId();
            inventoryChangeHistory.setGoodsInfoId(goodsInfoId);
            inventoryChangeHistory.setBillId(item.getBillId());
            inventoryChangeHistory.setCode(bill.getCode());
            inventoryChangeHistory.setChangeTime(new Date());
            inventoryChangeHistory.setInventoryChangeAmount(item.getAmount());
            inventoryChangeHistory.setInventoryAfterChange(inventoryService.selectByPrimaryKeyForUpdate(new InventoryKey(enterpriseId, goodsInfoId)).getInventory());
            inventoryChangeService.insertSelective(inventoryChangeHistory);
        }

    }

    private void addAvailableInventory(long enterpriseId, List<InventoryBillItem> items, Boolean out) {
        List<Long> errorList = new ArrayList<>();
        for (InventoryBillItem item : items) {
            if (out) {
                try {
                    inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), -item.getAmount());
                } catch (UserOperationException e) {
                    errorList.add(item.getGoodsInfoId());
                }
            } else {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), item.getAmount());
            }
        }
        if (errorList.size() > 0) {
            throw new UserOperationException(errorList);
        }
    }

	//部分收货终止，返回可用库存及实际库存
	private void returnAvailableInventory(long enterpriseId, List<InventoryBillItem> items) {
		List<Long> errorList = new ArrayList<>();
		for (InventoryBillItem item : items) {
				try {
					Integer amount = item.getAmount();
					Integer receipt = (item.getReceiptAmount() == null) ? 0 : item.getReceiptAmount();
					Integer add = amount - receipt;
					inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), add);
				} catch (UserOperationException e) {
					errorList.add(item.getGoodsInfoId());
				}
			}
		if (errorList.size() > 0) {
			throw new UserOperationException(errorList);
		}
	}


    @Override
    public void updateByPrimaryKeySelective(InventoryBill inventoryBill) {
        inventoryBillMapper.updateByPrimaryKeySelective(inventoryBill);
    }

    @Override
    public InventoryBill getBillDataFromReplementBill(Long outId, Long billId) {
        return inventoryBillMapper.getBillDataFromReplementBill(outId, billId);
    }




    @Override
    public void createStorageBill(Map<Long,List<InventoryPurchaseBillItem>> itemMap,Long purchaseId,Long creatorId,String reason){
        for(Long key : itemMap.keySet()){
            InventoryBill inventoryBill = new InventoryBill();
            inventoryBill.setCode(generator.generate());
            inventoryBill.setCreatorId(creatorId);
            inventoryBill.setCurrentId(key);
            inventoryBill.setBillStatus(BillStatus.STAY_IN);
            inventoryBill.setBillType(BillType.PURCHASE_INVENTORY_INSTOCK);
            inventoryBill.setCreateTime(new Date());
            inventoryBill.setReason(reason);
            inventoryBill.setPurchaseId(purchaseId);
            inventoryBill.setInId(key);
            inventoryBill.setCurrentId(key);
            inventoryBillMapper.insertSelective(inventoryBill);
            Long billId = inventoryBill.getBillId();
            for (InventoryPurchaseBillItem item:itemMap.get(key)){
                InventoryBillItem billItem = new InventoryBillItem();
                billItem.setAmount(item.getSumAmount());
                billItem.setSettlePrice(item.getSettlePrice());
                billItem.setGoodsInfoId(item.getGoodsinfoId());
                billItem.setPurchasePrice(item.getSettlePrice());
                billItem.setBillId(billId);
                inventoryBillItemMapper.insertSelective(billItem);
            }
            inventoryBillHistoryService.operate(creatorId, billId);
            inventoryBillLogService.addBillLog(creatorId, billId, null, "[创建]");
        }
    }

    @Override
    public Long getPurchaseIdByBillId(long billId){
        return inventoryBillMapper.selectPurchaseIdByBillId(billId);
    }

    @Override
    public List<InventoryBill> getInventoryBillByPurchaseId(Long purchaseId) {
        return inventoryBillMapper.selectInventoryBillByPurchaseId(purchaseId);
    }

    @Override
    public void forceFinishBill(Long billId) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(billId);
        if (bill == null ||bill.getBillType()!=BillType.PURCHASE_INVENTORY_INSTOCK) {
            throw new IllegalArgumentException("此单据无法进行完成操作。");
        }
        bill.setBillStatus(BillStatus.FINISHED);
        bill.setCurrentId(null);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    public int updateByPrimaryKey(InventoryBill record) {
        return inventoryBillMapper.updateByPrimaryKey(record);
    }


    @Override
    public List<WarnDay> selectWarnDay(long minCatalog, long maxCatalog, Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code,Pageable  pageable,String overday,String type,long catalog) {

        return inventoryBillMapper.selectWarnDay(minCatalog, maxCatalog, endStartDate, endEndDate, endDate, startDate, day, goodsInfoItemNo, code,pageable,overday,type,catalog);

    }


    @Override
    public int slectWarnDaySum(long minCatalog, long maxCatalog, Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code,String overday,String type,long catalog) {


        return inventoryBillMapper.selectWarnDaySum(minCatalog, maxCatalog, endStartDate, endEndDate, endDate, startDate, day, goodsInfoItemNo, code,overday,type,catalog);
    }

    @Override
    public int deleteWarnDay(long id) {
        return inventoryBillItemDetailMapper.deleteWarnDay(id);
    }

    @Override
    public int updateWarnDay(InventoryBillItemDetail inventoryBillItemDetail) {

        return inventoryBillItemDetailMapper.updateWarnDay(inventoryBillItemDetail);
    }

}