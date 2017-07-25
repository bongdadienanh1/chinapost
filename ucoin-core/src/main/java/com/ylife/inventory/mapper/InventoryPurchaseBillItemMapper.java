package com.ylife.inventory.mapper;

import com.ylife.inventory.model.InventoryPurchaseBillItem;
import com.ylife.inventory.model.PurchaseBillStatus;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface InventoryPurchaseBillItemMapper {
    int deleteByPrimaryKey(Long itemId);

    int insert(InventoryPurchaseBillItem record);

    int insertSelective(InventoryPurchaseBillItem record);

    InventoryPurchaseBillItem selectByPrimaryKey(Long itemId);

    int updateByPrimaryKeySelective(InventoryPurchaseBillItem record);

    int updateByPrimaryKey(InventoryPurchaseBillItem record);

    List<InventoryPurchaseBillItem> selectByPurchaseGoodsId(@Param("purchaseId")Long purchaseId,@Param("goodsInfoId")Long goodsInfoId);

    void updatePurchaseGoodsId(@Param("settlePrice")BigDecimal settlePrice, @Param("purchaseId")Long purchaseId,@Param("goodsInfoId")Long goodsInfoId);

    void deleteByGoodsInfoId(@Param("purchaseId")Long purchaseId,@Param("goodsInfoIds")Long[] goodsInfoIds,@Param("itemIds")Long[] itemIds);

    void updateByItemId(@Param("itemId")Long itemId,@Param("amount")Integer amount);

    void updateReceiptAmountByGoodsInfoId(@Param("receiptAmount")int receiptAmount,@Param("purchaseId")Long purchaseId,@Param("enterpriseId")Long enterpriseId,@Param("goodsInfoId")Long goodsInfoId);

    Integer selectSumAmountByKey(Long purchaseId);

	List<InventoryPurchaseBillItem> selectPurchaseBillListByCondition(@Param("code") Long code,
	                                                                  @Param("status") PurchaseBillStatus status,
	                                                                  @Param("start") Date start,
	                                                                  @Param("end") Date end,
	                                                                  @Param("thirdId") Long thirdId);
}