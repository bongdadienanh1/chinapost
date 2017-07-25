package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.FormInfoService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.chinapost.service.pojo.FormInfo;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.exception.UserOperationException;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.*;
import com.ylife.form.service.*;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;
import com.ylife.system.model.Param;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.system.service.ParamService;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.utils.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2016/8/5.
 */

@Service
public class FormInfoServiceImpl implements FormInfoService {

    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private FormFuncService formFuncService;
    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private ParamService paramService;
    @Resource
    private BusinessTypeService businessTypeService;
    @Resource
    private InventoryHistoryFormService inventoryHistoryFormService;
    @Resource
    private InventoryChangeFormService inventoryChangeFormService;
    @Resource
    private SupplyFormService supplyFormService;
    @Resource
    private CustomerFormService customerFormService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;


    @Override
    public FormInfo getReportInfo(Date start, Date end) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        FormInfo info = new FormInfo();
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        end = DateUtil.getNight(end);
        Date now = new Date();
        Date today = DateUtil.getMorning(now);
        Date yearStart = DateUtil.getStartYear(now);

        FormTime historyToday = formFuncService.getReportInfoByTime(maxCatalog, minCatalog, today, now);
        Long newCustomerToday = formFuncService.getNewCustomerByTime(maxCatalog, minCatalog, today, now);
        info.setExpenditureByDay(historyToday.getExpenditure());
        info.setPriceByDay(historyToday.getSumPrice());
        info.setIncreaseCustomerDay(newCustomerToday);

        FormTime historyYear = formFuncService.getReportInfoByTime(maxCatalog, minCatalog, yearStart, now);
        Long newCustomerYear = formFuncService.getNewCustomerByTime(maxCatalog, minCatalog, yearStart, now);
        info.setPriceByYear(historyYear.getSumPrice());
        info.setExpenditureByYear(historyYear.getExpenditure());
        info.setIncreaseCustomerYear(newCustomerYear);

        FormTime historyTIme = formFuncService.getReportInfoByTime(maxCatalog, minCatalog, start, end);
        Long newCustomerTime = formFuncService.getNewCustomerByTime(maxCatalog, minCatalog, start, end);
        if (historyTIme != null) {
            info.setPriceByTime(historyTIme.getSumPrice());
            info.setExpenditureByTime(historyTIme.getExpenditure());
            info.setMarketPrice(historyTIme.getSumMarketPrice());
            info.setSalePrice(historyTIme.getSumSalePrice());
        }
        info.setIncreaseCustomerTime(newCustomerTime);

        List<FormTime> historyEveryDay = formFuncService.getReportInfoEveryDay(maxCatalog, minCatalog, start, end);
        List<Date> dateList = DateUtil.dateSplit(start, end);
        FormTime formTime = new FormTime();
        for (int i = 0; i < dateList.size(); i++) {
            String day = DateUtil.formatToString(dateList.get(i), "MM-dd");
            formTime.setEveryDay(day);
            if (!historyEveryDay.contains(formTime)) {
                FormTime model = new FormTime();
                model.setSumPrice(BigDecimal.ZERO);
                model.setExpenditure(0l);
                model.setEveryDay(day);
                historyEveryDay.add(i, model);
            }
        }
        info.setDayList(historyEveryDay);

        List<FormTime> newCustomerAmount = formFuncService.newCustomerAmount(maxCatalog, minCatalog, start, end);
        for (int i = 0; i < dateList.size(); i++) {
            String day = DateUtil.formatToString(dateList.get(i), "MM-dd");
            formTime.setEveryDay(day);
            if (!newCustomerAmount.contains(formTime)) {
                FormTime model = new FormTime();
                model.setNewCustomerAmount(0l);
                model.setEveryDay(day);
                newCustomerAmount.add(i, model);
            }
            info.setNewCustomerAmountList(newCustomerAmount);
        }
        return info;
    }


    @Override
    public Page<UcoinGrandForm> getUcoingrandReport(Long enterpriseId, Date start, Date end, String idCard, Pageable pageable) {
        EnterpriseFunction function;
        if (enterpriseId == null) {
            function = currentLoginService.getCurrentLoginEnterpriseFunc();
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        }
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getucoinGrandForm(idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Page<UcoinGrandForm> getDetailGrandInfo(Long enterpriseId, Date start, Date end, String idCard, Pageable pageable) {
        EnterpriseFunction function;
        if (enterpriseId == null) {
            function = currentLoginService.getCurrentLoginEnterpriseFunc();
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        }
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getDetailGrandForm(idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Page<CustomerConsume> getCustomerconsumeReport(String idCard, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getCustomerConsume(idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Page<CustomerConsume> getDetailConsume(String idCard, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getDetailConsume(idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Page<CustomerConsume> getDetailConsumeNew(Long enterpriseId, String idCard, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getDetailConsumeNew(enterpriseId, idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Page<FormFunc> getBaseReport(Long enterpriseId, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.getNetdata(maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public Map<String, Object> getHistoryMap(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable) {
        Map<String, Object> historyMap = new HashMap<>();
        List<Param> params = paramService.getParams(typeId);
        List<String> list = new ArrayList<>();
        for (Param param : params) {
            list.add(param.getParamName());
        }
        if (list.size() == 0) {
            historyMap.put("thead", "");
        } else {
            historyMap.put("thead", list);
        }
        Page<CustomerUcoinHistory> customerUcoinHistoryPage = ucoinGrandService.getGrandHistory(enterpriseId, typeId, start, end, pageable);
        historyMap.put("data", customerUcoinHistoryPage);
        return historyMap;
    }

    @Override
    public Page<CustomerUcoinHistory> getGrandHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable) {
        return ucoinGrandService.getGrandHistory(enterpriseId, typeId, start, end, pageable);
    }


    @Override
    public Page<CustomerConsume> getCustomerConsumeReportNew(String idCard, Long enterpriseId, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.newGetCustomerConsume(idCard, maxCatalog, minCatalog, start, end, pageable);
    }

    @Override
    public List<InventoryHsitoryByEnterpriseForm> getHistoryGoodsByEnterprise(Long enterpriseId, String goodsInfoName, String goodsInfoItemNo, Date date, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        Date latMonth = DateUtil.getFirstDayOfMonth(date);
        Date thisMonth = DateUtil.getLastDayOfMonth(date);
        List<InventoryHsitoryByEnterpriseForm> outList = inventoryHistoryFormService.getHistoryGoodsByEnterprie(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, latMonth, thisMonth, null);
        return outList;
    }

    @Override
    public Page<InventoryHsitoryByEnterpriseForm> getInventoryHistoryFormByEnterprise(Long enterpriseId, String goodsInfoName,
                                                                                      String goodsInfoItemNo, Date date, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        Date latMonth = DateUtil.getFirstDayOfMonth(date);
        Date thisMonth = DateUtil.getLastDayOfMonth(date);
        Page<InventoryHsitoryByEnterpriseForm> outPage = inventoryHistoryFormService.getHistoryByEnterprie(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, latMonth, thisMonth, pageable);
        return outPage;
    }

    @Override
    public Page<InventoryHistoryForm> getInventoryHistoryFormByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                                                         String goodsInfoItemNo, Date date, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        Date latMonth = DateUtil.getFirstDayOfMonth(date);
        Date thisMonth = DateUtil.getLastDayOfMonth(date);
        return inventoryHistoryFormService.getHistoryByGoodsInfo(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, latMonth, thisMonth, pageable);
    }

    @Override
    public Page<InventoryChangeHistoryForm> getInventoryChangeForm(Long enterpriseId, Date start, Date end, String goodsInfoName, String goodsInfoItemNo, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return inventoryChangeFormService.getHistoryChangeForm(maxCatalog, minCatalog, start, end, goodsInfoName, goodsInfoItemNo, pageable);
    }

    @Override
    public Page<SupplyForm> getSupplyDetails(Long enterpriseId, Date createStartTime, Date createEndTime, Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable,Long thirdId,Long thirdEnterpriseId) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return supplyFormService.getSupplyDetails(maxCatalog, minCatalog, createStartTime, createEndTime, goodsInfoType, billStatuss, pageable,thirdId,thirdEnterpriseId);
    }

    @Override
    public Page<SupplyForm> getSupplyDetailsForm(Long enterpriseId, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return supplyFormService.getSupplyDetailsForm(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss, pageable);
    }



    @Override
    public Page<SupplyForm> getSupplyByThirdAndEnterprise(Long enterpriseId, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Long thirdId, Long thirdEnterpriseId, List<BillStatus> billStatuss, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return supplyFormService.getSupplyByThirdAndEnterprise(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss, pageable);
    }

    @Override
    public Page<SupplyForm> getSupplyByThirdAndGoods(Long enterpriseId, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Long thirdId, Long thirdEnterpriseId, List<BillStatus> billStatuss, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return supplyFormService.getSupplyByThirdAndGoods(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, thirdEnterpriseId, thirdId, billStatuss, pageable);
    }

    @Override
    public Page<SupplyForm> getSupplyStatistical(Long enterpriseId, Date orderStartTime, Date orderEndTime, Date readyStartTime, Date readyEndTime, Date warehouseStartTime, Date warehouseEndTime, Integer goodsInfoType, List<BillStatus> billStatuss, Pageable pageable) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return supplyFormService.getSupplyStatistical(maxCatalog, minCatalog, orderStartTime, orderEndTime, readyStartTime, readyEndTime, warehouseStartTime, warehouseEndTime, goodsInfoType, billStatuss, pageable);
    }

    @Override
    public WealthForm getWealthFormByEnterpriseId(Long enterpriseId,Date startTime, Date endTime) {
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return formFuncService.selectWealthFormByEnterpriseId(enterpriseId, maxCatalog, minCatalog, startTime, endTime);
    }

    @Override
    public List<WealthForm> getSonWealthForm(Long enterpriseId, Date start, Date end) {
        EnterpriseFunction currentFunc = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        if (currentFunc == null || currentFunc.getEnd()) throw new UserOperationException("请正确选定非底级网点的机构");
        List<WealthForm> wealthFormList = new ArrayList<>();
        List<EnterpriseFunction> functions = enterpriseFunctionMapper.selectByParentId(enterpriseId);
        if (functions != null) {
            for (EnterpriseFunction function : functions) {
                Long id = function.getId();
                Long maxCatalog = function.getMaxCatalog();
                Long minCatalog = function.getMinCatalog();
                WealthForm wealthForm = formFuncService.selectWealthFormByEnterpriseId(id, maxCatalog, minCatalog, start, end);
                wealthForm.setRemainCash(wealthForm.getRemainUdou());
                wealthFormList.add(wealthForm);
            }
        }
        return wealthFormList;
    }

    @Override
    public Page<CustomerFormDetail> getCustomerFormByEnterpriseId(Long enterpriseId, Date start, Date end, Pageable pageable) {
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        return customerFormService.getCustomerFormByEnterpriseId(maxCatalog,minCatalog,start,end,pageable);
    }


    @Override
    public List<CustomerFormStatistic> getCustomerFormStatisticByenterpriseId(Long enterpriseId,Date start, Date end) {
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = function.getMaxCatalog();
        Long minCatalog = function.getMinCatalog();
        List<CustomerFormStatistic> customerFormStatisticList=customerFormService.getCustomerFormStatisticByenterpriseId(enterpriseId,maxCatalog,minCatalog,start,end);
        String enterpriseName=enterpriseInfoService.getEnterpriseInfo(enterpriseId).getEnterpriseName();
        if(customerFormStatisticList.size()==0 || (customerFormStatisticList.size()>0 && !customerFormStatisticList.get(0).getCustomerRef().equals("new"))){
            CustomerFormStatistic customerFormStatistic=new CustomerFormStatistic();
            customerFormStatistic.setEnterpriseId(enterpriseId);
            customerFormStatistic.setEnd(function.getEnd());
            customerFormStatistic.setEnterpriseName(enterpriseName);
            customerFormStatistic.setCustomerRef("new");
            customerFormStatisticList.add(customerFormStatistic);
            Collections.reverse(customerFormStatisticList);
        }

        if(customerFormStatisticList.size() ==0 || (customerFormStatisticList.size()==1 && customerFormStatisticList.get(0).getCustomerRef().equals("new"))){
            CustomerFormStatistic customerFormStatisticOld=new CustomerFormStatistic();
            customerFormStatisticOld.setCustomerRef("old");
            customerFormStatisticOld.setEnterpriseId(enterpriseId);
            customerFormStatisticOld.setEnd(function.getEnd());
            customerFormStatisticOld.setEnterpriseName(enterpriseName);
            customerFormStatisticList.add(customerFormStatisticOld);

        }
        return customerFormStatisticList;
    }

    @Override
    public List<List<CustomerFormStatistic>> getSonCustomerFormStatisticByEnterpriseId(Long enterpriseId,Date start, Date end) {
        EnterpriseFunction currentFunc = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        if (currentFunc == null || currentFunc.getEnd()) throw new UserOperationException("请正确选定非底级网点的机构");
        List<List<CustomerFormStatistic>> customerFormStatistics=new ArrayList<>();
        List<EnterpriseFunction> functions = enterpriseFunctionMapper.selectByParentId(enterpriseId);
        if(functions!=null){
            for(EnterpriseFunction function:functions){
                Long id=function.getId();
                Long maxCatalog = function.getMaxCatalog();
                Long minCatalog = function.getMinCatalog();
                List<CustomerFormStatistic> customerFormStatisticList=customerFormService.getCustomerFormStatisticByenterpriseId(id,maxCatalog,minCatalog,start,end);
                if(customerFormStatisticList.size()==0 || (customerFormStatisticList.size()>0 && !customerFormStatisticList.get(0).getCustomerRef().equals("new"))){
                    CustomerFormStatistic customerFormStatistic=new CustomerFormStatistic();
                    customerFormStatistic.setEnterpriseId(id);
                    customerFormStatistic.setEnd(function.getEnd());
                    customerFormStatistic.setEnterpriseName(enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
                    customerFormStatistic.setCustomerRef("new");
                    customerFormStatisticList.add(customerFormStatistic);
                    Collections.reverse(customerFormStatisticList);
                }

                if(customerFormStatisticList.size() ==0 || (customerFormStatisticList.size()==1 && customerFormStatisticList.get(0).getCustomerRef().equals("new"))){
                    CustomerFormStatistic customerFormStatisticOld=new CustomerFormStatistic();
                    customerFormStatisticOld.setCustomerRef("old");
                    customerFormStatisticOld.setEnterpriseId(id);
                    customerFormStatisticOld.setEnd(function.getEnd());
                    customerFormStatisticOld.setEnterpriseName(enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
                    customerFormStatisticList.add(customerFormStatisticOld);

                }
                customerFormStatistics.add(customerFormStatisticList);
            }
        }
        return customerFormStatistics;
    }

    private SupplyAccount getSupplyAccount(List<SupplyAccount> items,SupplyAccount supplyAccount) {

        if(supplyAccount==null){
            supplyAccount = new SupplyAccount();
            supplyAccount.setEnterpriseName("合计");
            supplyAccount.setBossFee(BigDecimal.ZERO);
            supplyAccount.setNyFee(BigDecimal.ZERO);
            supplyAccount.setFlmFee(BigDecimal.ZERO);
            supplyAccount.setJlyFee(BigDecimal.ZERO);
            supplyAccount.setThFee(BigDecimal.ZERO);
            supplyAccount.setLwFee(BigDecimal.ZERO);
            supplyAccount.setYhFee(BigDecimal.ZERO);
            supplyAccount.setYshFee(BigDecimal.ZERO);
            supplyAccount.setTotalFee(BigDecimal.ZERO);
        }
        for (SupplyAccount item : items) {
            supplyAccount.setBossFee(supplyAccount.getBossFee().add(item.getBossFee() == null ? BigDecimal.ZERO : item.getBossFee()));
            supplyAccount.setNyFee(supplyAccount.getNyFee().add(item.getNyFee() == null ? BigDecimal.ZERO : item.getNyFee()));
            supplyAccount.setFlmFee(supplyAccount.getFlmFee().add(item.getFlmFee() == null ? BigDecimal.ZERO : item.getFlmFee()));
            supplyAccount.setJlyFee(supplyAccount.getJlyFee().add(item.getJlyFee() == null ? BigDecimal.ZERO : item.getJlyFee()));
            supplyAccount.setThFee(supplyAccount.getThFee().add(item.getThFee() == null ? BigDecimal.ZERO : item.getThFee()));
            supplyAccount.setLwFee(supplyAccount.getLwFee().add(item.getLwFee() == null ? BigDecimal.ZERO : item.getLwFee()));
            supplyAccount.setYhFee(supplyAccount.getYhFee().add(item.getYhFee() == null ? BigDecimal.ZERO : item.getYhFee()));
            supplyAccount.setYshFee(supplyAccount.getYshFee().add(item.getYshFee() == null ? BigDecimal.ZERO : item.getYshFee()));
            supplyAccount.setTotalFee(supplyAccount.getTotalFee().add(item.getTotalFee() == null ? BigDecimal.ZERO : item.getTotalFee()));
        }

        return supplyAccount;
    }

    /**
     * 区域对账汇总
     *
     * @param enterpriseId
     * @param start
     * @param end
     * @param billStatuses
     * @return
     */
    @Override
    public List<SupplyAccount> getSupplyAccountStatistic(Long enterpriseId, Date start, Date end, List<PurchaseBillStatus> billStatuses) {

        List<SupplyAccount> supplyAccountList = new ArrayList<>();
        EnterpriseFunction currentFunc = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        SupplyAccount supplyAccount = null;
        if (currentFunc.getEnd()){//网点
            Long maxCatalog = currentFunc.getMaxCatalog();
            Long minCatalog = currentFunc.getMinCatalog();
            List<SupplyAccount> items = supplyFormService.getSupplyAccountStatistical(enterpriseId,maxCatalog,minCatalog,start,end,billStatuses);
            if(items.size()>0) {
                supplyAccount = getSupplyAccount(items,supplyAccount);
                supplyAccountList.addAll(items);
            }

        }else{//非网点
            List<EnterpriseFunction> functions = enterpriseFunctionMapper.selectByParentId(enterpriseId);
            if(functions!=null){
                for(EnterpriseFunction function:functions){
                    Long id=function.getId();
                    Long maxCatalog = function.getMaxCatalog();
                    Long minCatalog = function.getMinCatalog();
                    List<SupplyAccount> items =supplyFormService.getSupplyAccountStatistical(id, maxCatalog, minCatalog, start, end, billStatuses);

                    if(items.size()>0) {
                        supplyAccount = getSupplyAccount(items,supplyAccount);
                        supplyAccountList.addAll(items);
                    }
                }
            }
        }

        //添加合计
        if(supplyAccountList.size()>0){
            supplyAccountList.add(supplyAccount);
        }

        return supplyAccountList;
    }
}
