package com.ylife.inventory.mapper;


import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.InventoryHistoryForm;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InventoryHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryHistory record);

    int insertSelective(InventoryHistory record);

    InventoryHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(InventoryHistory record);

    int updateByPrimaryKey(InventoryHistory record);



    /**
     * 按商品统计各项变动总量
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    InventoryHistoryForm sumHistoryByGoods(@Param("maxCatalog") Long maxCatalog,
                                           @Param("minCatalog") Long minCatalog,
                                           @Param("start") Date start,
                                           @Param("end") Date end);


    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertByBatch(@Param("list")List<InventoryHistory> list,
                      @Param("recordTime")Date recordTime);


    /**
     * 从库存变动记录表按照时间统计库存变动信息
     * @param start
     * @param end
     * @return
     */
    List<InventoryHistory> statisticInventoryHistory(@Param("start")Date start,@Param("end")Date end);


    //煞笔的变动总记录求和，放进煞笔的分页合计数据
    List<InventoryHistoryForm> sumHistoryByEnterprise(@Param("maxCatalog") Long maxCatalog,
                                                      @Param("minCatalog") Long minCatalog,
                                                      @Param("start") Date start,
                                                      @Param("end") Date end,
                                                      @Param("pageable")Pageable pageable);

    /**
     * 按网点查询库存变更报表
     * @param maxCatalog
     * @param minCatalog
     * @return
     */
    List<InventoryHsitoryByEnterpriseForm> getHistoryByEnterprie(@Param("maxCatalog") Long maxCatalog,
                                                     @Param("minCatalog") Long minCatalog,
                                                     @Param("goodsInfoName")String goodsInfoName,
                                                     @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                                     @Param("start") Date start,
                                                     @Param("end") Date end,
                                                     @Param("pageable") Pageable pageable);

    int countHistoryByEnterprie(@Param("maxCatalog") Long maxCatalog,
                                @Param("minCatalog") Long minCatalog,
                                @Param("goodsInfoName")String goodsInfoName,
                                @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                @Param("start") Date start,
                                @Param("end") Date end);


    /**
     * 按货品查询库存变更报表
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<InventoryHistoryForm> getHistoryByGoodsInfo(@Param("maxCatalog") Long maxCatalog,
                                                     @Param("minCatalog") Long minCatalog,
                                                     @Param("goodsInfoName")String goodsInfoName,
                                                     @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                                     @Param("start") Date start,
                                                     @Param("end") Date end,
                                                     @Param("pageable") Pageable pageable);


    int countHistoryByGoodsInfo(@Param("maxCatalog") Long maxCatalog,
                                @Param("minCatalog") Long minCatalog,
                                @Param("goodsInfoName")String goodsInfoName,
                                @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                @Param("start") Date start,
                                @Param("end") Date end);









}