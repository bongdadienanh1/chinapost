package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.SupplyAccount;
import com.ylife.form.model.SupplyForm;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface SupplyFormService {

    /**
     * 网点供货明细查询
     * @param maxCatalog 网点最大索引
     * @param minCatalog 本网点索引
     * @param createStartTime     创建入库单开始日期
     * @param createEndTime       创建入库单结束日期
     * @param goodsInfoType 商品类型（0表示集采 1表示自购）
     * @param billStatuss 单据状态
     * @param pageable 分页
     * @return
     */
    Page<SupplyForm> getSupplyDetails(Long maxCatalog,Long minCatalog,Date createStartTime,Date createEndTime,Integer goodsInfoType,
                                       List<BillStatus> billStatuss,Pageable pageable,Long thirdId,Long thirdEnterpriseId);


    /**
     * 网点供货明细查询(包含调拨单)
     * @param maxCatalog 网点最大索引
     * @param minCatalog 本网点索引
     * @param orderStartTime 定货开始日期
     * @param orderEndTime 定货结束日期
     * @param readyStartTime 备货开始日期
     * @param readyEndTime 备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime 入库结束日期
     * @param goodsInfoType 商品类型（0表示集采 1表示自购）
     * @param billStatuss 单据状态
     * @param pageable 分页
     * @return
     */
    Page<SupplyForm> getSupplyDetailsForm(Long maxCatalog,Long minCatalog,Date orderStartTime,Date orderEndTime,Date readyStartTime,Date readyEndTime,Date warehouseStartTime,Date warehouseEndTime,Integer goodsInfoType,
                                      List<BillStatus> billStatuss,Pageable pageable);

    /**
     * 供货商发货商品统计,按照网点分组
     * @param maxCatalog 网点最大索引
     * @param minCatalog 本网点索引
     * @param orderStartTime 定货开始日期
     * @param orderEndTime 定货结束日期
     * @param readyStartTime 备货开始日期
     * @param readyEndTime 备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    Page<SupplyForm> getSupplyByThirdAndEnterprise(Long maxCatalog,Long minCatalog,
                                                   Date orderStartTime,Date orderEndTime,
                                                   Date readyStartTime,Date readyEndTime,
                                                   Date warehouseStartTime,Date warehouseEndTime,
                                                   Long thirdEnterpriseId,Long thirdId,
                                                   List<BillStatus> billStatuss,Pageable pageable);

    /**
     * 供货商发货商品统计，按照商品分组
     * @param maxCatalog 网点最大索引
     * @param minCatalog 本网点索引
     * @param orderStartTime 定货开始日期
     * @param orderEndTime 定货结束日期
     * @param readyStartTime 备货开始日期
     * @param readyEndTime 备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    Page<SupplyForm> getSupplyByThirdAndGoods(Long maxCatalog,Long minCatalog,
                                                   Date orderStartTime,Date orderEndTime,
                                                   Date readyStartTime,Date readyEndTime,
                                                   Date warehouseStartTime,Date warehouseEndTime,
                                                   Long thirdEnterpriseId,Long thirdId,
                                                   List<BillStatus> billStatuss,Pageable pageable);



    Map<Long,BigDecimal> sumInfo(List<SupplyForm> list);



    /**
     * 网点供货统计（按供货商分类合并）
     * @param maxCatalog 网点最大索引
     * @param minCatalog 本网点索引
     * @param orderStartTime 定货开始日期
     * @param orderEndTime 定货结束日期
     * @param readyStartTime 备货开始日期
     * @param readyEndTime 备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime 入库结束日期
     * @param goodsInfoType 商品类型（0表示集采 1表示自购）
     * @param billStatuss 单据状态
     * @param pageable 分页
     * @return
     */
    Page<SupplyForm> getSupplyStatistical(Long maxCatalog,Long minCatalog,Date orderStartTime,Date orderEndTime,Date readyStartTime,Date readyEndTime,Date warehouseStartTime,Date warehouseEndTime,Integer goodsInfoType,
                                      List<BillStatus> billStatuss,Pageable pageable);


    /**
     * 区域对账汇总
     * @param enterpriseId
     * @param maxCatalog
     * @param minCatalog
     * @param startTime
     * @param endTime
     * @param purchaseBillStatuses
     * @return
     */
    List<SupplyAccount> getSupplyAccountStatistical(Long enterpriseId,Long maxCatalog,Long minCatalog,Date startTime,Date endTime,List<PurchaseBillStatus> purchaseBillStatuses);

}

