package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.InventoryHistoryForm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoBiaoGe on 2016/11/20.
 */
public interface InventoryHistoryFormService {



    InventoryHistoryForm sumInventoryHistoryByEnterprise(List<InventoryHistoryForm> historyFormList);



    InventoryHistoryForm sumInventoryByGoods(Long maxCatalog,
                                      Long minCatalog,
                                      Date start,
                                      Date end);


    /**
     * 按网点查询库存变更报表
     * @param maxCatalog
     * @param minCatalog
     * @return
     */
    List<InventoryHsitoryByEnterpriseForm> getHistoryGoodsByEnterprie(Long maxCatalog,
                                                                 Long minCatalog,
                                                                 String goodsInfoName,
                                                                 String goodsInfoItemNo,
                                                                 Date start,
                                                                 Date end,
                                                                 Pageable pageable);
    /**
     * 按网点查询库存变更报表
     * @param maxCatalog
     * @param minCatalog
     * @return
     */
    Page<InventoryHsitoryByEnterpriseForm> getHistoryByEnterprie(Long maxCatalog,
                                                     Long minCatalog,
                                                     String goodsInfoName,
                                                     String goodsInfoItemNo,
                                                     Date start,
                                                     Date end,
                                                     Pageable pageable);



    /**
     * 按货品查询库存变更报表
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<InventoryHistoryForm> getHistoryByGoodsInfo(Long maxCatalog,
                                                     Long minCatalog,
                                                     String goodsInfoName,
                                                     String goodsInfoItemNo,
                                                     Date start,
                                                     Date end,
                                                     Pageable pageable);



}
