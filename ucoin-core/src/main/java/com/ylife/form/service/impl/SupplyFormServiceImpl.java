package com.ylife.form.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.SupplyFormMapper;
import com.ylife.form.model.SupplyAccount;
import com.ylife.form.model.SupplyForm;
import com.ylife.form.service.SupplyFormService;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 */
@Service
public class SupplyFormServiceImpl implements SupplyFormService {

    @Resource
    private SupplyFormMapper supplyFormMapper;

    /**
     * 网点供货明细查询
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
    @Override
    public Page<SupplyForm> getSupplyDetails(Long maxCatalog, Long minCatalog, Date createStartTime, Date createEndTime,Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable,Long thirdId,Long thirdEnterpriseId) {
        List<SupplyForm> supplyFormList = supplyFormMapper.selectSupplyDetails(maxCatalog, minCatalog, createStartTime, createEndTime,thirdId,thirdEnterpriseId, goodsInfoType, billStatuss, pageable);
        int totalElements = supplyFormMapper.selectSupplyDetailsCount(maxCatalog, minCatalog, createStartTime, createEndTime, goodsInfoType,thirdId,thirdEnterpriseId, billStatuss);
        return new PageImpl<>(pageable, totalElements, supplyFormList);
    }

    /**
     * 网点供货明细查询(包含调拨单)
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
    @Override
    public Page<SupplyForm> getSupplyDetailsForm(Long maxCatalog, Long minCatalog, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable) {
        List<SupplyForm> supplyFormList = supplyFormMapper.selectSupplyDetailsForm(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss, pageable);
        int totalElements = supplyFormMapper.selectSupplyDetailsFormCount(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss);
        return new PageImpl<>(pageable, totalElements, supplyFormList);
    }


    @Override
    public Page<SupplyForm> getSupplyByThirdAndEnterprise(Long maxCatalog, Long minCatalog, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Long thirdEnterpriseId, Long thirdId, List<BillStatus> billStatuss, Pageable pageable) {
        List<SupplyForm> supplyForms = supplyFormMapper.selectSupplyByThirdAndEnterprise(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss, pageable);
        List<SupplyForm> sumList = supplyFormMapper.sumSupplyByThirdAndEnterprise(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss);
        Map<Long,BigDecimal> sumMap=sumInfo(sumList);
        for(SupplyForm supplyForm:supplyForms){
            BigDecimal totalPrice=sumMap.get(supplyForm.getEnterpriseId());
            supplyForm.setTotalPrice(totalPrice);
        }
        int totalElements = supplyFormMapper.countSupplyByThirdAndEnterprise(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss);
        if (pageable == null || pageable.getPageNumber() == totalElements / pageable.getPageSize() + 1) {
            BigDecimal allTotalPrice=BigDecimal.ZERO;
            for(Long key:sumMap.keySet()) allTotalPrice = allTotalPrice.add(sumMap.get(key)==null?BigDecimal.ZERO:sumMap.get(key));
            SupplyForm supplyForm=new SupplyForm();
            supplyForm.setTotalPrice(allTotalPrice);
            supplyForm.setSumFlag("合计");
            supplyForms.add(supplyForm);
        }
        return new PageImpl<>(pageable, totalElements, supplyForms);
    }


    @Override
    public Page<SupplyForm> getSupplyByThirdAndGoods(Long maxCatalog, Long minCatalog, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Long thirdEnterpriseId, Long thirdId, List<BillStatus> billStatuss, Pageable pageable) {
        List<SupplyForm> supplyForms = supplyFormMapper.selectSupplyByThirdAndGoods(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss, pageable);
        BigDecimal totalPrice=supplyFormMapper.sumSupplyByThirdAndGoods(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss);
        int totalElements = supplyFormMapper.countSupplyByThirdAndGoods(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss);
        if (pageable == null || pageable.getPageNumber() == totalElements / pageable.getPageSize() + 1) {
            SupplyForm supplyForm=new SupplyForm();
            supplyForm.setTotalPrice(totalPrice);
            supplyForm.setSumFlag("合计");
            supplyForms.add(supplyForm);
        }
        return new PageImpl<>(pageable, totalElements, supplyForms);
    }

    @Override
    public Map<Long, BigDecimal> sumInfo(List<SupplyForm> list) {
        Map<Long,BigDecimal> sumMap=new HashMap<>();
        for(SupplyForm supplyForm:list){
            sumMap.put(supplyForm.getEnterpriseId(),supplyForm.getTotalPrice());
        }
        return sumMap;
    }





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
    @Override
    public Page<SupplyForm> getSupplyStatistical(Long maxCatalog, Long minCatalog, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable) {
        List<SupplyForm> supplyForms = supplyFormMapper.selectSupplyStatistical(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss, pageable);
        List<SupplyForm> sumList = supplyFormMapper.selectEachEnterpriseSum(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss);
        Map<Long,BigDecimal> sumMap=sumInfo(sumList);
        for(SupplyForm supplyForm:supplyForms){
            BigDecimal totalPrice=sumMap.get(supplyForm.getEnterpriseId());
            supplyForm.setTotalPrice(totalPrice);
        }
        int totalElements = supplyFormMapper.selectSupplyStatisticalCount(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss);
        if (pageable == null || pageable.getPageNumber() == totalElements / pageable.getPageSize() + 1) {
            SupplyForm supplyForm=new SupplyForm();
            supplyForm.setTotalPrice(supplyFormMapper.selectAllEnterpriseSum(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss));
            supplyForm.setSumFlag("合计");
            supplyForms.add(supplyForm);
        }
        return new PageImpl<>(pageable, totalElements, supplyForms);
    }

    /** 区域对账汇总
     * @param enterpriseId
     * @param maxCatalog
     * @param minCatalog
     * @param startTime
     * @param endTime
     * @param purchaseBillStatuses
     * @return
     */
    @Override
    public List<SupplyAccount> getSupplyAccountStatistical(Long enterpriseId, Long maxCatalog, Long minCatalog, Date startTime, Date endTime, List<PurchaseBillStatus> purchaseBillStatuses) {
        return supplyFormMapper.selectSupplyAccountStatistical(enterpriseId,maxCatalog,minCatalog,startTime,endTime,purchaseBillStatuses);
    }
}
