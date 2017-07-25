package com.ylife.inventory.mapper;

import com.ylife.inventory.model.InventoryBillItemDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryBillItemDetailMapper {

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(InventoryBillItemDetail record);

    /**
     *
     * @param itemId
     * @return
     */
    List<InventoryBillItemDetail> select(long itemId);

    /**
     *
     * @param id  删除的预警id
     * @return
     */
    int deleteWarnDay(
            @Param("id")long id);

    /**
     *
     * @param inventoryBillItemDetail  更新预警
     * @return
     */
    int updateWarnDay(InventoryBillItemDetail inventoryBillItemDetail);


    int updateByInventory(  @Param("id")Long id,  @Param("goodsInfoId")Long goodsInfoId);
}