package com.ylife.form.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.form.model.SupplyAccount;
import com.ylife.form.model.SupplyForm;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface SupplyFormMapper {

    /**
     * 按条件查询网点供货明细
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param createStartTime     创建入库单开始日期
     * @param createEndTime       创建入库单结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    List<SupplyForm> selectSupplyDetails(@Param("maxCatalog") Long maxCatalog,
                                         @Param("minCatalog") Long minCatalog,
                                         @Param("createStartTime") Date createStartTime,
                                         @Param("createEndTime") Date createEndTime,
                                         @Param("thirdId") Long thirdId,
                                         @Param("thirdEnterpriseId")Long thirdEnterpriseId,
                                         @Param("goodsInfoType") Integer goodsInfoType,
                                         @Param("billStatuss") List<BillStatus> billStatuss,
                                         @Param("pageable") Pageable pageable);

    /**
     * 按条件查询网点供货明细
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param createStartTime     创建入库单开始日期
     * @param createEndTime       创建入库单结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @return
     */
    int selectSupplyDetailsCount(@Param("maxCatalog") Long maxCatalog,
                                 @Param("minCatalog") Long minCatalog,
                                 @Param("createStartTime") Date createStartTime,
                                 @Param("createEndTime") Date createEndTime,
                                 @Param("goodsInfoType") Integer goodsInfoType,
                                 @Param("thirdId") Long thirdId,
                                 @Param("thirdEnterpriseId")Long thirdEnterpriseId,
                                 @Param("billStatuss") List<BillStatus> billStatuss);



    /**
     * 按条件查询网点供货明细
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    List<SupplyForm> selectSupplyDetailsForm(@Param("maxCatalog") Long maxCatalog,
                                         @Param("minCatalog") Long minCatalog,
                                         @Param("orderStartTime") Date orderStartTime,
                                         @Param("orderEndTime") Date orderEndTime,
                                         @Param("readyStartTime") Date readyStartTime,
                                         @Param("readyEndTime") Date readyEndTime,
                                         @Param("warehouseStartTime") Date warehouseStartTime,
                                         @Param("warehouseEndTime") Date warehouseEndTime,
                                         @Param("goodsInfoType") Integer goodsInfoType,
                                         @Param("billStatuss") List<BillStatus> billStatuss,
                                         @Param("pageable") Pageable pageable);

    /**
     * 按条件查询网点供货明细
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @return
     */
    int selectSupplyDetailsFormCount(@Param("maxCatalog") Long maxCatalog,
                                 @Param("minCatalog") Long minCatalog,
                                 @Param("orderStartTime") Date orderStartTime,
                                 @Param("orderEndTime") Date orderEndTime,
                                 @Param("readyStartTime") Date readyStartTime,
                                 @Param("readyEndTime") Date readyEndTime,
                                 @Param("warehouseStartTime") Date warehouseStartTime,
                                 @Param("warehouseEndTime") Date warehouseEndTime,
                                 @Param("goodsInfoType") Integer goodsInfoType,
                                 @Param("billStatuss") List<BillStatus> billStatuss);



    /**
     * 按条件统计供应商发货信息，并且按照网点分组
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    List<SupplyForm> selectSupplyByThirdAndEnterprise(@Param("maxCatalog") Long maxCatalog,
                                                      @Param("minCatalog") Long minCatalog,
                                                      @Param("orderStartTime") Date orderStartTime,
                                                      @Param("orderEndTime") Date orderEndTime,
                                                      @Param("readyStartTime") Date readyStartTime,
                                                      @Param("readyEndTime") Date readyEndTime,
                                                      @Param("warehouseStartTime") Date warehouseStartTime,
                                                      @Param("warehouseEndTime") Date warehouseEndTime,
                                                      @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                                      @Param("thirdId") Long thirdId,
                                                      @Param("billStatuss") List<BillStatus> billStatuss,
                                                      @Param("pageable") Pageable pageable);

    /**
     * 按条件统计供应商发货信息，并且按照网点分组--的数据条数
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @return
     */
    int countSupplyByThirdAndEnterprise(@Param("maxCatalog") Long maxCatalog,
                                        @Param("minCatalog") Long minCatalog,
                                        @Param("orderStartTime") Date orderStartTime,
                                        @Param("orderEndTime") Date orderEndTime,
                                        @Param("readyStartTime") Date readyStartTime,
                                        @Param("readyEndTime") Date readyEndTime,
                                        @Param("warehouseStartTime") Date warehouseStartTime,
                                        @Param("warehouseEndTime") Date warehouseEndTime,
                                        @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                        @Param("thirdId") Long thirdId,
                                        @Param("billStatuss") List<BillStatus> billStatuss);


    /**
     * 统计供应商在各个网点发货价格总和,按照网点分组
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @return
     */
    List<SupplyForm> sumSupplyByThirdAndEnterprise(@Param("maxCatalog") Long maxCatalog,
                                                   @Param("minCatalog") Long minCatalog,
                                                   @Param("orderStartTime") Date orderStartTime,
                                                   @Param("orderEndTime") Date orderEndTime,
                                                   @Param("readyStartTime") Date readyStartTime,
                                                   @Param("readyEndTime") Date readyEndTime,
                                                   @Param("warehouseStartTime") Date warehouseStartTime,
                                                   @Param("warehouseEndTime") Date warehouseEndTime,
                                                   @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                                   @Param("thirdId") Long thirdId,
                                                   @Param("billStatuss") List<BillStatus> billStatuss);

    /**
     * 统计供应商在各个网点发货价格总和,按照网点分组
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @return
     */
    BigDecimal sumSupplyByThirdAndGoods(@Param("maxCatalog") Long maxCatalog,
                                        @Param("minCatalog") Long minCatalog,
                                        @Param("orderStartTime") Date orderStartTime,
                                        @Param("orderEndTime") Date orderEndTime,
                                        @Param("readyStartTime") Date readyStartTime,
                                        @Param("readyEndTime") Date readyEndTime,
                                        @Param("warehouseStartTime") Date warehouseStartTime,
                                        @Param("warehouseEndTime") Date warehouseEndTime,
                                        @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                        @Param("thirdId") Long thirdId,
                                        @Param("billStatuss") List<BillStatus> billStatuss);


    /**
     * 统计供应商在某个中间机构及以下网点发货价格总和
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @return
     */
    int sumTotalByThirdAndEnterprise(@Param("maxCatalog") Long maxCatalog,
                                     @Param("minCatalog") Long minCatalog,
                                     @Param("orderStartTime") Date orderStartTime,
                                     @Param("orderEndTime") Date orderEndTime,
                                     @Param("readyStartTime") Date readyStartTime,
                                     @Param("readyEndTime") Date readyEndTime,
                                     @Param("warehouseStartTime") Date warehouseStartTime,
                                     @Param("warehouseEndTime") Date warehouseEndTime,
                                     @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                     @Param("thirdId") Long thirdId,
                                     @Param("billStatuss") List<BillStatus> billStatuss);


    /**
     * 按条件统计供应商发货信息，并且按照商品分组
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    List<SupplyForm> selectSupplyByThirdAndGoods(@Param("maxCatalog") Long maxCatalog,
                                                 @Param("minCatalog") Long minCatalog,
                                                 @Param("orderStartTime") Date orderStartTime,
                                                 @Param("orderEndTime") Date orderEndTime,
                                                 @Param("readyStartTime") Date readyStartTime,
                                                 @Param("readyEndTime") Date readyEndTime,
                                                 @Param("warehouseStartTime") Date warehouseStartTime,
                                                 @Param("warehouseEndTime") Date warehouseEndTime,
                                                 @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                                 @Param("thirdId") Long thirdId,
                                                 @Param("billStatuss") List<BillStatus> billStatuss,
                                                 @Param("pageable") Pageable pageable);


    /**
     * 按条件统计供应商发货信息，并且按照商品分组--的数据条数
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param thirdEnterpriseId  自购商品商家id，即上传自购商品的网点id
     * @param thirdId            集采商品的商家id
     * @param billStatuss        单据状态
     * @return
     */
    int countSupplyByThirdAndGoods(@Param("maxCatalog") Long maxCatalog,
                                   @Param("minCatalog") Long minCatalog,
                                   @Param("orderStartTime") Date orderStartTime,
                                   @Param("orderEndTime") Date orderEndTime,
                                   @Param("readyStartTime") Date readyStartTime,
                                   @Param("readyEndTime") Date readyEndTime,
                                   @Param("warehouseStartTime") Date warehouseStartTime,
                                   @Param("warehouseEndTime") Date warehouseEndTime,
                                   @Param("thirdEnterpriseId") Long thirdEnterpriseId,
                                   @Param("thirdId") Long thirdId,
                                   @Param("billStatuss") List<BillStatus> billStatuss);


    /**
     * 网点供货统计（按供货商分类合并）
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @param pageable           分页
     * @return
     */
    List<SupplyForm> selectSupplyStatistical(@Param("maxCatalog") Long maxCatalog,
                                             @Param("minCatalog") Long minCatalog,
                                             @Param("orderStartTime") Date orderStartTime,
                                             @Param("orderEndTime") Date orderEndTime,
                                             @Param("readyStartTime") Date readyStartTime,
                                             @Param("readyEndTime") Date readyEndTime,
                                             @Param("warehouseStartTime") Date warehouseStartTime,
                                             @Param("warehouseEndTime") Date warehouseEndTime,
                                             @Param("goodsInfoType") Integer goodsInfoType,
                                             @Param("billStatuss") List<BillStatus> billStatuss,
                                             @Param("pageable") Pageable pageable);

    /**
     * 网点供货统计总行数（按供货商分类合并）
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @return
     */
    int selectSupplyStatisticalCount(@Param("maxCatalog") Long maxCatalog,
                                     @Param("minCatalog") Long minCatalog,
                                     @Param("orderStartTime") Date orderStartTime,
                                     @Param("orderEndTime") Date orderEndTime,
                                     @Param("readyStartTime") Date readyStartTime,
                                     @Param("readyEndTime") Date readyEndTime,
                                     @Param("warehouseStartTime") Date warehouseStartTime,
                                     @Param("warehouseEndTime") Date warehouseEndTime,
                                     @Param("goodsInfoType") Integer goodsInfoType,
                                     @Param("billStatuss") List<BillStatus> billStatuss);

    /**
     * 网点供货统计（按供货商分类合并）
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @return
     */
    List<SupplyForm> selectEachEnterpriseSum(@Param("maxCatalog") Long maxCatalog,
                                             @Param("minCatalog") Long minCatalog,
                                             @Param("orderStartTime") Date orderStartTime,
                                             @Param("orderEndTime") Date orderEndTime,
                                             @Param("readyStartTime") Date readyStartTime,
                                             @Param("readyEndTime") Date readyEndTime,
                                             @Param("warehouseStartTime") Date warehouseStartTime,
                                             @Param("warehouseEndTime") Date warehouseEndTime,
                                             @Param("goodsInfoType") Integer goodsInfoType,
                                             @Param("billStatuss") List<BillStatus> billStatuss);

    /**
     * 网点供货统计总行数（按供货商分类合并）
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param orderStartTime     定货开始日期
     * @param orderEndTime       定货结束日期
     * @param readyStartTime     备货开始日期
     * @param readyEndTime       备货结束日期
     * @param warehouseStartTime 入库开始日期
     * @param warehouseEndTime   入库结束日期
     * @param goodsInfoType      商品类型（0表示集采 1表示自购）
     * @param billStatuss        单据状态
     * @return
     */
    BigDecimal selectAllEnterpriseSum(@Param("maxCatalog") Long maxCatalog,
                                  @Param("minCatalog") Long minCatalog,
                                  @Param("orderStartTime") Date orderStartTime,
                                  @Param("orderEndTime") Date orderEndTime,
                                  @Param("readyStartTime") Date readyStartTime,
                                  @Param("readyEndTime") Date readyEndTime,
                                  @Param("warehouseStartTime") Date warehouseStartTime,
                                  @Param("warehouseEndTime") Date warehouseEndTime,
                                  @Param("goodsInfoType") Integer goodsInfoType,
                                  @Param("billStatuss") List<BillStatus> billStatuss);

    /**
     * 按条件查询区域对账单
     *
     * @param maxCatalog         网点最大索引
     * @param minCatalog         本网点索引
     * @param startTime          开始日期
     * @param endTime            结束日期
     * @param enterpriseId       备货开始日期
     * @param statuss            单据状态
     * @return
     */
    List<SupplyAccount> selectSupplyAccountStatistical(@Param("enterpriseId") Long enterpriseId,
                                        @Param("maxCatalog") Long maxCatalog,
                                         @Param("minCatalog") Long minCatalog,
                                         @Param("startTime") Date startTime,
                                         @Param("endTime") Date endTime,
                                         @Param("statuss") List<PurchaseBillStatus> statuss);
}
