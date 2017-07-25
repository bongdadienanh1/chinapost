package com.ylife.chinapost.service;

import com.ylife.chinapost.service.pojo.FormInfo;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.*;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;
import com.ylife.ucoin.model.CustomerUcoinHistory;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormInfoService {

    /**
     * 获取报表信息
     *
     * @param start
     * @param end
     * @return
     */
    FormInfo getReportInfo(Date start, Date end);

    /**
     * 会员邮豆发放统计报表
     *
     * @param enterpriseId
     * @param start
     * @param end
     * @param idCard
     * @return
     */
    Page<UcoinGrandForm> getUcoingrandReport(Long enterpriseId, Date start, Date end, String idCard, Pageable pageable);

    /**
     * 根据idcard获取会员发放邮豆的详情
     *
     * @param idCard 会员身份证号
     * @return
     */
    Page<UcoinGrandForm> getDetailGrandInfo(Long enterpriseId, Date start, Date end, String idCard, Pageable pageable);

    /**
     * 会员邮豆消耗统计
     *
     * @param idCard
     * @param start
     * @param end
     * @return
     */
    Page<CustomerConsume> getCustomerconsumeReport(String idCard, Date start, Date end, Pageable pageable);

    Page<CustomerConsume> getDetailConsume(String idCard, Date start, Date end, Pageable pageable);


    /**
     * 新会员邮豆消耗统计New
     */
    Page<CustomerConsume> getDetailConsumeNew(Long enterpriseId, String idCard, Date start, Date end, Pageable pageable);

    /**
     * 网点基础数据统计
     *
     * @param enterpriseId
     * @param start
     * @param end
     * @return
     */
    Page<FormFunc> getBaseReport(Long enterpriseId, Date start, Date end, Pageable pageable);


    Map<String, Object> getHistoryMap(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable);

    Page<CustomerUcoinHistory> getGrandHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable);


    /**
     * 新的会员邮豆消耗统计
     *
     * @param idCard
     * @param start
     * @param end
     * @return
     */
    Page<CustomerConsume> getCustomerConsumeReportNew(String idCard, Long enterpriseId, Date start, Date end, Pageable pageable);


    //为了在库存变动详情报表，按仓库搜索中实现货品查询功能添加的临时接口，非通用接口。当pageable为null的时候，按仓库分页功能失效，恰好能实现查询商品的需求
    List<InventoryHsitoryByEnterpriseForm> getHistoryGoodsByEnterprise(Long enterpriseId, String goodsInfoName,
                                                                       String goodsInfoItemNo, Date date, Pageable pageable);

    Page<InventoryHsitoryByEnterpriseForm> getInventoryHistoryFormByEnterprise(Long enterpriseId, String goodsInfoName,
                                                                               String goodsInfoItemNo, Date date, Pageable pageable);




    Page<InventoryHistoryForm> getInventoryHistoryFormByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                                                  String goodsInfoItemNo,Date date,Pageable pageable);


    Page<InventoryChangeHistoryForm> getInventoryChangeForm(Long enterpriseId,
                                                      Date start,
                                                      Date end,
                                                      String goodsInfoName,
                                                      String goodsInfoItemNo,
                                                      Pageable pageable);

    Page<SupplyForm> getSupplyDetails(Long enterpriseId,Date createStartTime,Date createEndTime,Integer goodsInfoType,
                                       List<BillStatus> billStatuss,Pageable pageable,Long thirdId,Long thirdEnterpriseId);

    Page<SupplyForm> getSupplyDetailsForm(Long enterpriseId,Date orderStartTime,Date orderEndTime,Date readyStartTime,Date readyEndTime,Date warehouseStartTime,Date warehouseEndTime,Integer goodsInfoType,
                                      List<BillStatus> billStatuss,Pageable pageable);

    Page<SupplyForm> getSupplyByThirdAndEnterprise(Long enterpriseId,Date orderStartTime,
                                                   Date orderEndTime,Date readyStartTime,
                                                   Date readyEndTime,Date warehouseStartTime,
                                                   Date warehouseEndTime,Long thirdId,Long thirdEnterpriseId,
                                                   List<BillStatus> billStatuss,Pageable pageable);

    Page<SupplyForm> getSupplyByThirdAndGoods(Long enterpriseId,Date orderStartTime,
                                                   Date orderEndTime,Date readyStartTime,
                                                   Date readyEndTime,Date warehouseStartTime,
                                                   Date warehouseEndTime,Long thirdId,Long thirdEnterpriseId,
                                                   List<BillStatus> billStatuss,Pageable pageable);


    Page<SupplyForm> getSupplyStatistical(Long enterpriseId,Date orderStartTime,Date orderEndTime,Date readyStartTime,Date readyEndTime,Date warehouseStartTime,Date warehouseEndTime,Integer goodsInfoType,
                                      List<BillStatus> billStatuss,Pageable pageable);


    /**
     *
     * 获取当前机构的财富统计信息
     * @param enterpriseId
     * @param startTime  开始时间
     * @param endTime    结束时间
     * @return
     */
    WealthForm getWealthFormByEnterpriseId(Long enterpriseId,Date startTime, Date endTime);

    /**
     * 获取指定机构所有下级机构的统计信息
     * @param enterpriseId
     * @param start
     * @param end
     * @return
     */
    List<WealthForm> getSonWealthForm(Long enterpriseId,Date start,Date end);


    /**
     * 会员基础数据明细表
     * @param enterpriseId
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerFormDetail> getCustomerFormByEnterpriseId(Long enterpriseId,Date start,Date end,Pageable pageable);


    /**
     * 当前机构会员基础数据统计表
     * @param enterpriseId
     * @param start
     * @param end
     * @return
     */
    List<CustomerFormStatistic> getCustomerFormStatisticByenterpriseId(Long enterpriseId,Date start,Date end);

    /**
     * 子级机构会员基础数据统计表
     * @param enterpriseId
     * @param start
     * @param end
     * @return
     */
    List<List<CustomerFormStatistic>> getSonCustomerFormStatisticByEnterpriseId(Long enterpriseId,Date start,Date end);


    /**
     * 区域对账汇总
     * @param enterpriseId
     * @param start
     * @param end
     * @param billStatuses
     * @return
     */
    List<SupplyAccount> getSupplyAccountStatistic(Long enterpriseId,Date start,Date end,List<PurchaseBillStatus> billStatuses);


}
