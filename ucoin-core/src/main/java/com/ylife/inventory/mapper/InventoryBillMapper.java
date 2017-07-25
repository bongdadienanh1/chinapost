package com.ylife.inventory.mapper;


import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface InventoryBillMapper {

    int deleteByPrimaryKey(Long billId);

    int insert(InventoryBill record);

    int insertSelective(InventoryBill record);

    InventoryBill selectByPrimaryKey(Long billId);
    List<InventoryBillLog> selectBillLog(Long billId);
    InventoryBill selectByPrimaryKeyForUpdate(Long billId);
    List<WarnDay> selectWarnDay(
                                @Param("minCatalog")long minCatalog,
                                @Param("maxCatalog")long maxCatalog,
                                @Param("endStartDate")Date endStartDate,
                                @Param("endEndDate")Date endEndDate,
                                @Param("endDate")Date endDate,
                                @Param("startDate")Date startDate,
                                @Param("day")Integer day,
                                @Param("goodsInfoItemNo")String goodsInfoItemNo,
                                @Param("code") Long code,
                                @Param("pageable") Pageable pageable,
                                @Param("overday") String overday,
                                @Param("type") String type,
                                @Param("catalog") long catalog);
   int selectWarnDaySum(
           @Param("minCatalog")long minCatalog,
           @Param("maxCatalog")long maxCatalog,
           @Param("endStartDate")Date endStartDate,
           @Param("endEndDate")Date endEndDate,
           @Param("endDate")Date endDate,
           @Param("startDate")Date startDate,
           @Param("day")Integer day,
           @Param("goodsInfoItemNo")String goodsInfoItemNo,
           @Param("code") Long code,
           @Param("overday") String overday,
           @Param("type") String type,
           @Param("catalog") long catalog);
    int deleteWarnDay(
            @Param("id")long id);
    List<InventoryBill> selectByCreatorId(@Param("creatorId") Long creatorId,
                                          @Param("code") Long code,
                                          @Param("creatorName") String creatorName,
                                          @Param("start") Date start,
                                          @Param("end") Date end,
                                          @Param("billStatus") BillStatus billStatus,
                                          @Param("billType") BillType billType,
                                          @Param("pageable") Pageable pageable);

    int countByCreatorId(@Param("creatorId") Long creatorId,
                         @Param("code") Long code,
                         @Param("creatorName") String creatorName,
                         @Param("start") Date start,
                         @Param("end") Date end,
                         @Param("billStatus") BillStatus billStatus,
                         @Param("billType") BillType billType);

    List<InventoryBill> selectByCurrentId(@Param("currentId") Long currentId,
                                          @Param("code") Long code,
                                          @Param("creatorName") String creatorName,
                                          @Param("start") Date start,
                                          @Param("end") Date end,
                                          @Param("billStatus") BillStatus billStatus,
                                          @Param("billType") BillType billType,
                                          @Param("pageable") Pageable pageable);

    int countByCurrentId(@Param("currentId") Long currentId,
                         @Param("code") Long code,
                         @Param("creatorName") String creatorName,
                         @Param("start") Date start,
                         @Param("end") Date end,
                         @Param("billStatus") BillStatus billStatus,
                         @Param("billType") BillType billType);

    List<InventoryBill> selectHandleHistory(@Param("enterpriseId") Long enterpriseId,
                                            @Param("code") Long code,
                                            @Param("creatorName") String creatorName,
                                            @Param("start") Date start,
                                            @Param("end") Date end,
                                            @Param("billStatus") BillStatus billStatus,
                                            @Param("billType") BillType billType,
                                            @Param("pageable") Pageable pageable);

    int countHandleHistory(@Param("enterpriseId") Long enterpriseId,
                           @Param("code") Long code,
                           @Param("creatorName") String creatorName,
                           @Param("start") Date start,
                           @Param("end") Date end,
                           @Param("billStatus") BillStatus billStatus,
                           @Param("billType") BillType billType);

    List<InventoryBill> selectByModel(@Param("model") InventoryBill model, @Param("pageable") Pageable pageable);

    int countByModel(InventoryBill model);

    InventoryBill selectByCode(Long code);

    int updateByPrimaryKeySelective(InventoryBill record);

    int updateByPrimaryKey(InventoryBill record);

    InventoryBill selectBillDetailByPrimaryKey(Long billId);


    InventoryBill getBillDataFromReplementBill(@Param("outId") Long outId,
                                               @Param("billId") Long billId);

    /**
     * 根据parentId获取billId List
     * @param parentId
     * @return
     */
    List<Long> selectBillIdByParentId(Long parentId);

    /**
     *根据bill数组获取汇总单据详情
     * @param billIds
     * @return
     */
    List<InventoryMergeGoods> selectBillDetailByBillIds(@Param("billIds") Long[] billIds);

    /**
     *根据bill数组获取汇总单据汇总
     * @param billIds
     * @return
     */
    List<InventoryMergeGoods> selectBillSumByBillIds(@Param("billIds") Long[] billIds);

    Long selectPurchaseIdByBillId(Long billId);

    /**
     * 根据采购单id获取采购入库单
     * */
    List<InventoryBill> selectInventoryBillByPurchaseId(Long purchaseId);


}