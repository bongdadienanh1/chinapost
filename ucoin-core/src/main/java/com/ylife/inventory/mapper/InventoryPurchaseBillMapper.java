package com.ylife.inventory.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.inventory.model.InventoryPurchaseBill;
import com.ylife.inventory.model.PurchaseBillStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InventoryPurchaseBillMapper {
    int deleteByPrimaryKey(Long purchaseId);

    int insert(InventoryPurchaseBill record);

    int insertSelective(InventoryPurchaseBill record);

    InventoryPurchaseBill selectByPrimaryKey(Long purchaseId);

    int updateByPrimaryKeySelective(InventoryPurchaseBill record);

    int updateByPrimaryKey(InventoryPurchaseBill record);

    InventoryPurchaseBill selectSumDetailByPrimaryKey(Long purchaseId);

    InventoryPurchaseBill selectPurchaseDetailByPrimaryKey(Long purchaseId);

    List<InventoryPurchaseBill> selectPurchaseBillList(@Param("code") Long code,
                                                       @Param("status") PurchaseBillStatus status,
                                                       @Param("start") Date start,
                                                       @Param("end") Date end,
                                                       @Param("thirdId") Long thirdId,
                                                       @Param("pageable") Pageable pageable);

    int countPurchaseBillList(@Param("code") Long code,
                              @Param("status") PurchaseBillStatus status,
                              @Param("start") Date start,
                              @Param("end") Date end,
                              @Param("thirdId") Long thirdId,
                              @Param("pageable") Pageable pageable);

    int selectPurchaseCountByBillId(Long billId);
}