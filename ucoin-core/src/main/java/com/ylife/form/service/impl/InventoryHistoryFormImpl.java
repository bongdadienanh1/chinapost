package com.ylife.form.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.InventoryHistoryForm;
import com.ylife.form.service.InventoryHistoryFormService;
import com.ylife.inventory.mapper.InventoryHistoryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by XiaoBiaoGe on 2016/11/20.
 */
@Service
public class InventoryHistoryFormImpl implements InventoryHistoryFormService {

    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;
    @Resource
    private InventoryHistoryMapper inventoryHistoryMapper;

    @Override
    public InventoryHistoryForm sumInventoryByGoods(Long maxCatalog, Long minCatalog, Date start, Date end) {
        return inventoryHistoryMapper.sumHistoryByGoods(maxCatalog, minCatalog, start, end);
    }


     @Override
    public InventoryHistoryForm sumInventoryHistoryByEnterprise(List<InventoryHistoryForm> list){
        Integer lastMonthInventory=0;
        Integer consumeAmount=0;
        Integer gainsAmount=0;
        Integer tansferOut=0;
        Integer transferIn=0;
        Integer orderConsume=0;
        Integer orderBack=0;
        Integer moreReport=0;
        Integer lessReport=0;
        Integer inventoryAmount=0;
        Integer instockAmount=0;
        Integer increaseAmount=0;
         Long enterpriseId=null;
        for(InventoryHistoryForm inventoryHistoryForm:list){
            enterpriseId=inventoryHistoryForm.getEnterpriseId();
            Integer lastMonth=inventoryHistoryForm.getLastMonthInventory();
            lastMonth=lastMonth==null?0:lastMonth;
            lastMonthInventory += lastMonth;
            Integer consume=inventoryHistoryForm.getConsumeAmount();
            consume=consume==null?0:consume;
            consumeAmount+=consume;
            Integer gains=inventoryHistoryForm.getGainsAmount();
            gains=gains==null?0:gains;
            gainsAmount+=gains;
            Integer out=inventoryHistoryForm.getTransferOut();
            out=out==null?0:out;
            tansferOut+=out;
            Integer in=inventoryHistoryForm.getTransferIn();
            in=in==null?0:in;
            transferIn+=in;
            Integer orderCon=inventoryHistoryForm.getOrderConsume();
            orderCon=orderCon==null?0:orderCon;
            orderConsume+=orderCon;
            Integer back=inventoryHistoryForm.getOrderBack();
            back=back==null?0:back;
            orderBack+=back;
            Integer more=inventoryHistoryForm.getMoreReport();
            more=more==null?0:moreReport;
            moreReport+=more;
            Integer less=inventoryHistoryForm.getLessReport();
            less=less==null?0:less;
            lessReport+=less;
            Integer amount=inventoryHistoryForm.getInventoryAmount();
            amount=amount==null?0:amount;
            inventoryAmount+=amount;
            Integer instock=inventoryHistoryForm.getInstockAmount();
            instock=instock==null?0:instock;
            instockAmount+=instock;
            Integer increase=inventoryHistoryForm.getIncreaseAmount();
            increase=increase==null?0:increase;
            increaseAmount+=increase;
        }
        InventoryHistoryForm historyForm=new InventoryHistoryForm();
         historyForm.setEnterpriseId(enterpriseId);
        historyForm.setSumFlag("合计");
        historyForm.setLastMonthInventory(lastMonthInventory);
        historyForm.setConsumeAmount(consumeAmount);
        historyForm.setGainsAmount(gainsAmount);
        historyForm.setTransferIn(transferIn);
        historyForm.setTransferOut(tansferOut);
        historyForm.setOrderConsume(orderConsume);
        historyForm.setOrderBack(orderBack);
        historyForm.setMoreReport(moreReport);
        historyForm.setLessReport(lessReport);
        historyForm.setInventoryAmount(inventoryAmount);
        historyForm.setInstockAmount(instockAmount);
        historyForm.setIncreaseAmount(increaseAmount);
       return historyForm;

    }

    @Override
    public List<InventoryHsitoryByEnterpriseForm> getHistoryGoodsByEnterprie(Long maxCatalog, Long minCatalog, String goodsInfoName, String goodsInfoItemNo, Date start, Date end, Pageable pageable) {
        List<InventoryHsitoryByEnterpriseForm> enterpriseForms = inventoryHistoryMapper.getHistoryByEnterprie(maxCatalog, minCatalog, goodsInfoName,goodsInfoItemNo,start, end, null);
        return enterpriseForms;
    }

    @Override
    public Page<InventoryHsitoryByEnterpriseForm> getHistoryByEnterprie(Long maxCatalog, Long minCatalog, String goodsInfoName, String goodsInfoItemNo, Date start, Date end, Pageable pageable) {
        List<InventoryHsitoryByEnterpriseForm> list = inventoryHistoryMapper.getHistoryByEnterprie(maxCatalog, minCatalog, goodsInfoName,goodsInfoItemNo,start, end, pageable);
        for(InventoryHsitoryByEnterpriseForm inventoryHsitoryByEnterpriseForm:list){
            List<InventoryHistoryForm> formList=inventoryHsitoryByEnterpriseForm.getFormList();
            InventoryHistoryForm inventoryHistoryForm=sumInventoryHistoryByEnterprise(formList);
            inventoryHsitoryByEnterpriseForm.setInventoryHistoryForm(inventoryHistoryForm);
        }
        int totalElements = inventoryHistoryMapper.countHistoryByEnterprie(maxCatalog, minCatalog,goodsInfoName,goodsInfoItemNo, start, end);
        return new PageImpl<>(pageable, totalElements, list);


    }

    @Override
    public Page<InventoryHistoryForm> getHistoryByGoodsInfo(Long maxCatalog, Long minCatalog, String goodsInfoName,
                                                            String goodsInfoItemNo,Date start,Date end,Pageable pageable) {
        List<InventoryHistoryForm> list = inventoryHistoryMapper.getHistoryByGoodsInfo(maxCatalog, minCatalog, goodsInfoName,goodsInfoItemNo,start, end, pageable);
        List<InventoryHistoryForm> sumList=inventoryHistoryMapper.getHistoryByGoodsInfo(maxCatalog,minCatalog,goodsInfoName,goodsInfoItemNo,start,end,null);

        int totalElements = inventoryHistoryMapper.countHistoryByGoodsInfo(maxCatalog, minCatalog,goodsInfoName,goodsInfoItemNo, start, end);
        if (pageable != null && pageable.getPageNumber() == totalElements / pageable.getPageSize() + 1) {

            InventoryHistoryForm inventoryHistoryForm=sumInventoryHistoryByEnterprise(sumList);
            list.add(inventoryHistoryForm);
        }
        return new PageImpl<>(pageable, totalElements, list);
    }

}
