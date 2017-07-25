package com.ylife.inventory.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.form.model.InventoryChangeHistoryForm;
import com.ylife.inventory.model.InventoryChangeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InventoryChangeHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryChangeHistory record);

    int insertSelective(InventoryChangeHistory record);

    InventoryChangeHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryChangeHistory record);

    int updateByPrimaryKey(InventoryChangeHistory record);



    /**
     * 按照网点统计库存有变动商品，及变动原因和数量
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<InventoryChangeHistoryForm> selectInventoryChangeByEnterprise(@Param("maxCatalog") Long maxCatalog,
                                                                 @Param("minCatalog") Long minCatalog,
                                                                 @Param("start") Date start,
                                                                 @Param("end") Date end,
                                                                 @Param("goodsInfoName")String goodsInfoName,
                                                                 @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                                                 @Param("pageable")Pageable pageable);

    int countInventoryChangeByEnterprise(@Param("maxCatalog") Long maxCatalog,
                                         @Param("minCatalog") Long minCatalog,
                                         @Param("start") Date start,
                                         @Param("end") Date end,
                                         @Param("goodsInfoName")String goodsInfoName,
                                         @Param("goodsInfoItemNo")String goodsInfoItemNo);
}