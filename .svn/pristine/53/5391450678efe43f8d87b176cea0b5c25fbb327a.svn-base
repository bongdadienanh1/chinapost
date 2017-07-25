package com.ylife.inventory.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.model.InventoryPurchaseBill;
import com.ylife.inventory.model.InventoryPurchaseBillItem;
import com.ylife.inventory.model.PurchaseBillStatus;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by suibian on 2017/2/17.
 */
public interface InventoryPurchaseBillService {

    void createPurchaseBill(Map<Long,List<InventoryPurchaseBillItem>> groupByThirdMap, Long billId,Long enterpriseId,String marks);

    /**
     * 获取采购单汇总信息
     * */
    InventoryPurchaseBill getSumDetailByPrimaryKey(Long purchaseId);

    /**
     * 获取采购单明细
     * */
    InventoryPurchaseBill getPurchaseDetailByPrimaryKey(Long purchaseId);

    /**
     * 修改商品采购单价
     * @param purchaseId
     * @param goodsInfoId
     * @param settlePrice
     * */
    void updateSettlePrice(Long purchaseId,Long goodsInfoId,BigDecimal settlePrice);

    /**
     * 删除采购单商品
     * */
    void deleteByGoodsInfoId(@Param("purchaseId")Long purchaseId, @Param("goodsInfoIds")Long[] goodsInfoIds, @Param("itemIds")Long[] itemIds);

    /**
     * 修改单个网点采购数量
     * */
    void updateByPrimaryKey(@Param("itemId")Long itemId,@Param("amount")Integer amount);

    Page<InventoryPurchaseBill> getPurchaseBillList(@Param("code") Long code,
                                                       @Param("status") PurchaseBillStatus status,
                                                       @Param("start") Date start,
                                                       @Param("end") Date end,
                                                       @Param("thirdId") Long thirdId,
                                                       @Param("pageable") Pageable pageable);
    /**
     * 确认采购订单
     * */
    void confirmPurchaseBill(long purchaseId,String marks);

    /**
     * 终止采购订单
     * */
    void terminatePurchaseBill(long purchaseId);
    /**
     * 判断补货单是否生成采购单
     * */
    int getPurchaseCountByBillId(Long billId);

    /**
     * 采购订单生成采购入库单
     * */
    void pendingPurchaseBill(long purchaseId);

    /**
     * 修改采购订单入库数量
     * */
    void updateReceiptAmountByGoodsInfoId(int receiptAmount,Long purchaseId,Long enterpriseId,Long goodsInfoId);

    /**
     * 获取采购单
     * */
    InventoryPurchaseBill getByPrimaryKey(Long purchaseId);

    void updateByPrimaryKeySelective(InventoryPurchaseBill record);

	/**
	 * 按条件查询采购订单
	 * @param code 单据编号
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param status 单据状态
	 * @param thirdId 第三方id
	 * @return
	 * */
	List<InventoryPurchaseBillItem> selectPurchaseBillListByCondition(@Param("code") Long code,
	                                                                  @Param("status") PurchaseBillStatus status,
	                                                                  @Param("start") Date start,
	                                                                  @Param("end") Date end,
	                                                                  @Param("thirdId") Long thirdId);

}
