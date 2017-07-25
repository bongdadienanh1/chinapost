package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.FormInfoService;
import com.ylife.chinapost.service.pojo.FormInfo;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.*;
import com.ylife.form.service.FormFuncService;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.PurchaseBillStatus;
import com.ylife.inventory.service.InventoryHistoryService;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 */
@Controller
@SecurityResource(parent = "/web/formCenter", primary = false)
@RequestMapping(value = "/web/api/report", produces = "application/json;charset=utf-8")
public class FormApiController {


    @Resource
    private FormInfoService formInfoService;
    @Resource
    private FormFuncService formFuncService;
    @Resource
    private InventoryHistoryService inventoryHistoryService;
    @Resource
    private CurrentLoginService currentLoginService;


    @RequestMapping(value = "/getReportInfo")
    @ResponseBody
    public String getReportInfo(@RequestParam(value = "start",required=false)String start,
                                @RequestParam(value = "end",required = false)String end) {


        Date now =new Date();
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        }else {
            startTime=DateUtil.getWeekAgo(now);
        }

        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
        }else{
            endTime=DateUtil.getMorning(now);
        }

        FormInfo formInfo = formInfoService.getReportInfo(startTime, endTime);
        return new JsonResponseBean(formInfo).toJson();
    }


    /**
     * 邮豆发放报表
     * @param enterpriseId
     * @param idCard
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getUcoinGranForm")
    @ResponseBody
    public String getUcoinGranForm(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                   @RequestParam(value = "idCard",required = false)String idCard,
                                   @RequestParam(value = "start",required=false)String start,
                                   @RequestParam(value = "end",required = false)String end,
                                   @RequestParam(value = "page",required = false)int page,
                                   @RequestParam(value = "size",required = false)int size){
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard=StringUtil.nullOrNotBlank(idCard);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc=formInfoService.getUcoingrandReport(enterpriseId,startTime,endTime,idCard,new Pageable(page,size));
        return new JsonResponseBean(formFunc).toJson();
    }

    /**
     * 邮豆发放详情报表
     * @param enterpriseId
     * @param idCard
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getDetailUcoinGrand")
    @ResponseBody
    public String getDetailUcoinGrand(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                      @RequestParam(value = "idCard")String idCard,
                                      @RequestParam(value = "start",required=false)String start,
                                      @RequestParam(value = "end",required = false)String end,
                                      @RequestParam(value = "page",required = false)int page,
                                      @RequestParam(value = "size",required = false)int size){
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc=formInfoService.getDetailGrandInfo(enterpriseId, startTime, endTime, idCard, new Pageable(page, size));
        return new JsonResponseBean(formFunc).toJson();
    }


    /**
     * 网点基础数据报表
     * @param enterpriseId
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getNetDataReport")
    @ResponseBody
    public String getNetDataReport(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                      @RequestParam(value = "start",required=false)String start,
                                      @RequestParam(value = "end",required = false)String end,
                                      @RequestParam(value = "page",required = false)int page,
                                      @RequestParam(value = "size",required = false)int size){
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<FormFunc> formFunc = formInfoService.getBaseReport(enterpriseId, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(formFunc).toJson();
    }


    /**
     * 会员消费统计报表
     * @param idCard
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getCustomerConsume")
    @ResponseBody
    public String getCustomerConsume(@RequestParam(value = "idCard",required = false)String idCard,
                                     @RequestParam(value = "start",required=false)String start,
                                     @RequestParam(value = "end",required = false)String end,
                                     @RequestParam(value = "page",required = false)int page,
                                     @RequestParam(value = "size",required = false)int size){
        idCard = StringUtil.nullOrNotBlank(idCard);
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFunc = formInfoService.getCustomerconsumeReport(idCard, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(formFunc).toJson();
    }

    /**
     * 会员消费详情报表
     * @param idCard
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getDetailConsume")
    @ResponseBody
    public  String getDetailConsume(@RequestParam(value = "idCard")String idCard,
                                    @RequestParam(value = "start",required=false)String start,
                                    @RequestParam(value = "end",required = false)String end,
                                    @RequestParam(value = "page",required = false)int page,
                                    @RequestParam(value = "size",required = false)int size){
        Assert.notNull(idCard);
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFuncPage=formInfoService.getDetailConsume(idCard,startTime,endTime,new Pageable(page,size));
        return new JsonResponseBean(formFuncPage).toJson();
    }


    @RequestMapping(value = "/getDetailConsumeNew")
    @ResponseBody
    public String getDetailConsumeNew(@RequestParam(value = "idCard",required = false)String idCard,
                                      @RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                      @RequestParam(value = "start",required=false)String start,
                                      @RequestParam(value = "end",required = false)String end,
                                      @RequestParam(value = "page",required = false)int page,
                                      @RequestParam(value = "size",required = false)int size){
        Assert.notNull(idCard);
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFuncPage=formInfoService.getDetailConsumeNew(enterpriseId,idCard, startTime, endTime, new Pageable(page,size));
        return new JsonResponseBean(formFuncPage).toJson();
    }

    /**
     * 邮豆发放历史记录报表
     * @param enterpriseId
     * @param typeId
     * @param start
     * @param end
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getGrandHistory")
    @ResponseBody
    public String getGrandHistory(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                  @RequestParam(value = "typeId") Integer typeId,
                                  @RequestParam(value = "start",required = false) String start,
                                  @RequestParam(value = "end",required = false) String end,
                                  @RequestParam(value = "page",required = false)int page,
                                  @RequestParam(value = "size",required = false)int size){
        Assert.notNull(typeId,"没有选择业务类型");
        start=StringUtil.nullOrNotBlank(start);
        end=StringUtil.nullOrNotBlank(end);
        Date startTime = null;
        Date endTime = null;
        if(start!=null){
            startTime=DateUtil.fromString(start,Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        if(end!=null){
            endTime=DateUtil.fromString(end,Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Map<String,Object> historyMap=formInfoService.getHistoryMap(enterpriseId,typeId,startTime,endTime,new Pageable(page,size));
        return new JsonResponseBean(historyMap).toJson();
    }


    @RequestMapping(value = "/getCustomerConsumeNew")
    @ResponseBody
    public String getCustomerConsumeNew(@RequestParam(value = "idCard",required = false)String idCard,
                                        @RequestParam(value = "enterpriseId")Long enterpriseId,
                                        @RequestParam(value = "start",required=false)String start,
                                        @RequestParam(value = "end",required = false)String end,
                                        @RequestParam(value = "page",required = false,defaultValue = "1")int page,
                                        @RequestParam(value = "size",required = false,defaultValue = "10")int size){
        idCard=StringUtil.nullOrNotBlank(idCard);
        Assert.notNull(enterpriseId,"请选择要查询的网点！");
        start=StringUtil.nullOrNotBlank(start);
        end=StringUtil.nullOrNotBlank(end);
        Date startTime = null;
        Date endTime = null;
        if(start!=null){
            startTime=DateUtil.fromString(start,Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        if(end!=null){
            endTime=DateUtil.fromString(end,Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }

        Page<CustomerConsume> customerConsumePage=formInfoService.getCustomerConsumeReportNew(idCard,enterpriseId,startTime,endTime,new Pageable(page,size));
        return new JsonResponseBean(customerConsumePage).toJson();
    }

    @RequestMapping(value = "/getUcoinGrandByBatchCode")
    @ResponseBody
    public String getUcoinGrandByBatchCode(@RequestParam(value = "type",required = false)Integer type,
                                           @RequestParam(value = "start",required = false)String start,
                                           @RequestParam(value = "end" ,required = false)String end,
                                           @RequestParam(value = "page",required = false,defaultValue = "1")int page,
                                           @RequestParam(value = "size",required = false,defaultValue = "10")int size) {
        Date startTime = null;
        Date endTime = null;
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> page1 = formFuncService.selectUcoinGrandByBatchCode(type, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }


    @RequestMapping(value = "/getDetailUcoinGrandByBatchCode")
    @ResponseBody
    public String getDetailUcoinGrandByBatchCode(@RequestParam(value = "batchCode")Long batchCode,
                                                 @RequestParam(value = "page",required = false,defaultValue = "1")int page,
                                                 @RequestParam(value ="size",required = false,defaultValue = "10")int size){
        if (batchCode==null){
            //数据库错误，约定的特殊数字，短期使用
            batchCode=100l;
        }
        Page<UcoinGrandForm> page1 =  formFuncService.selectDetailUcoinGrandByBatchCode(batchCode, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }





    /**
     * 库存变动统计详情，查看商品在各个库存情况
     * @param enterpriseId
     * @param date
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getHistoryGoodsByEnterprise")
    @ResponseBody
    public String getHistoryGoodsByEnterprise(@RequestParam("enterpriseId")Long enterpriseId,
                                                  @RequestParam("date")String date,
                                                  @RequestParam(value = "goodsInfoName",required = false) String goodsInfoName,
                                                  @RequestParam(value = "goodsInfoItemNo",required = false) String goodsInfoItemNo,
                                                  @RequestParam(value = "page",required = false,defaultValue = "1")int page,
                                                  @RequestParam(value = "size",required = false,defaultValue = "1")int size){
        goodsInfoItemNo=StringUtil.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName=StringUtil.nullOrNotBlank(goodsInfoName);
        Assert.notNull(date,"选择日期");
        Date queryDate=DateUtil.fromString(date,"yyyy-MM");
        List<InventoryHsitoryByEnterpriseForm> resultList=formInfoService.getHistoryGoodsByEnterprise(enterpriseId, goodsInfoName, goodsInfoItemNo, queryDate,null);
        return new JsonResponseBean(resultList).toJson();
    }



    /**
     * 库存变动统计详情，按照网点查看
     * @param enterpriseId
     * @param date
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getInventoryHistoryByEnterprise")
    @ResponseBody
    public String getInventoryHistoryByEnterprise(@RequestParam("enterpriseId")Long enterpriseId,
                                                  @RequestParam("date")String date,
                                                  @RequestParam(value = "goodsInfoName",required = false) String goodsInfoName,
                                                  @RequestParam(value = "goodsInfoItemNo",required = false) String goodsInfoItemNo,
                                                  @RequestParam(value = "page",required = false,defaultValue = "1")int page,
                                                  @RequestParam(value ="size",required = false,defaultValue = "1")int size){
        goodsInfoItemNo=StringUtil.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName=StringUtil.nullOrNotBlank(goodsInfoName);
        Assert.notNull(date,"选择日期");
        Date queryDate=DateUtil.fromString(date,"yyyy-MM");
        Page<InventoryHsitoryByEnterpriseForm> resultPage=formInfoService.getInventoryHistoryFormByEnterprise(enterpriseId, goodsInfoName,goodsInfoItemNo,queryDate, new Pageable(page, size));
        return new JsonResponseBean(resultPage).toJson();
    }

    /**
     * 查询库存变动统计详情，按照货品查看
     * @param enterpriseId
     * @param date
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getInventoryHistoryByGoodsInfo")
    @ResponseBody
    public String getInventoryHistoryByGoodsInfo(@RequestParam("enterpriseId") Long enterpriseId,
                                                 @RequestParam("date") String date,
                                                 @RequestParam(value = "goodsInfoName",required = false) String goodsInfoName,
                                                 @RequestParam(value = "goodsInfoItemNo",required = false) String goodsInfoItemNo,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        goodsInfoItemNo=StringUtil.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName=StringUtil.nullOrNotBlank(goodsInfoName);
        Assert.notNull(date,"选择日期");
        Date queryDate=DateUtil.fromString(date,"yyyy-MM");
        Page<InventoryHistoryForm> resultPage=formInfoService.getInventoryHistoryFormByGoodsInfo(enterpriseId,goodsInfoName,goodsInfoItemNo, queryDate, new Pageable(page, size));
        return new JsonResponseBean(resultPage).toJson();
    }


    /**
     * 分网点查看商品库存变动
     * @param enterpriseId
     * @param start
     * @param end
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getInventoryChangeByEnterprise")
    @ResponseBody
    public String getInventoryChangeByEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                @RequestParam("start") String start,
                                                @RequestParam("end") String end,
                                                @RequestParam(value = "goodsInfoName",required = false) String goodsInfoName,
                                                @RequestParam(value = "goodsInfoItemNo",required = false) String goodsInfoItemNo,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date startTime = null;
        Date endTime = null;
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        goodsInfoItemNo=StringUtil.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName=StringUtil.nullOrNotBlank(goodsInfoName);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<InventoryChangeHistoryForm> inventoryChangeFormPage= formInfoService.getInventoryChangeForm(enterpriseId,startTime,endTime,goodsInfoName,goodsInfoItemNo,new Pageable(page,size));
        return  new JsonResponseBean(inventoryChangeFormPage).toJson();
    }


    /**
     * 统计库存变动接口，方便测试临时添加，做定时任务
     * @param date
     * @return
     */
    @RequestMapping(value = "/staticByTime")
    @ResponseBody
    public String staticByTime(@RequestParam("date")String date){
        Date statisTime=DateUtil.fromString(date,"yyyy-MM");
        inventoryHistoryService.insertByBatch();
        return  JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 查询网点供货明细
     * @param enterpriseId
     * @param start     创建入库单开始日期
     * @param end       创建入库单结束日期
     * @param goodsInfoType
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyDetails")
    @ResponseBody
    public String getSupplyDetails(@RequestParam("enterpriseId") Long enterpriseId,
                                                 @RequestParam(value="start",required = false) String start,
                                                 @RequestParam(value="end",required = false) String end,
                                                 @RequestParam(value = "goodsInfoType",required = false) Integer goodsInfoType,
                                                 @RequestParam(value = "billStatuss",required = false) List<BillStatus> billStatuss,
                                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                 @RequestParam(value = "thirdId",required = false) Long thirdId,
                                                 @RequestParam(value = "thirdEnterpriseId",required = false) Long thirdEnterpriseId){
        Date startTime = null;
        Date endTime = null;
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }

        Page<SupplyForm> supplyDetaqils= formInfoService.getSupplyDetails(enterpriseId, startTime, endTime,goodsInfoType, billStatuss, new Pageable(page, size),thirdId,thirdEnterpriseId);
        return  new JsonResponseBean(supplyDetaqils).toJson();
    }

    /**
     * 查询网点供货明细
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param goodsInfoType
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyDetailsForm")
    @ResponseBody
    public String getSupplyDetailsForm(@RequestParam("enterpriseId") Long enterpriseId,
                                   @RequestParam(value="orderStartTime",required = false) String orderStartTime,
                                   @RequestParam(value="orderEndTime",required = false) String orderEndTime,
                                   @RequestParam(value="readyStartTime",required = false) String readyStartTime,
                                   @RequestParam(value="readyEndTime",required = false) String readyEndTime,
                                   @RequestParam(value="warehouseStartTime",required = false) String warehouseStartTime,
                                   @RequestParam(value="warehouseEndTime",required = false) String warehouseEndTime,
                                   @RequestParam(value = "goodsInfoType",required = false) Integer goodsInfoType,
                                   @RequestParam(value = "billStatuss",required = false) List<BillStatus> billStatuss,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime=Constants.nullOrNotBlank(orderStartTime);
        orderEndTime=Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime=Constants.nullOrNotBlank(readyStartTime);
        readyEndTime=Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime=Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime=Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }


        Page<SupplyForm> supplyDetaqils= formInfoService.getSupplyDetailsForm(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, goodsInfoType, billStatuss, new Pageable(page, size));
        return  new JsonResponseBean(supplyDetaqils).toJson();
    }

    /**
     * 查询供应商供货信息,以供应商为查询条件，按照网点分组
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyByThirdAndEnterprise")
    @ResponseBody
    public String getSupplyByThirdAndEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                @RequestParam(value = "thirdId",required = false) Long thirdId,
                                                @RequestParam(value = "thirdEnterpriseId",required = false) Long thirdEnterpriseId,
                                                @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime=Constants.nullOrNotBlank(orderStartTime);
        orderEndTime=Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime=Constants.nullOrNotBlank(readyStartTime);
        readyEndTime=Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime=Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime=Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }
        Page<SupplyForm> supplyFormPage= formInfoService.getSupplyByThirdAndEnterprise(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT,thirdId,thirdEnterpriseId,billStatuss, new Pageable(page, size));
        return  new JsonResponseBean(supplyFormPage).toJson();
    }




    /**
     * 查询供应商供货信息，以供应商为查询条件，按照货品分组
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyByThirdAndGoods")
    @ResponseBody
    public String getSupplyByThirdAndGoods(@RequestParam("enterpriseId") Long enterpriseId,
                                                @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                @RequestParam(value = "thirdId",required = false) Long thirdId,
                                                @RequestParam(value = "thirdEnterpriseId",required = false) Long thirdEnterpriseId,
                                                @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime=Constants.nullOrNotBlank(orderStartTime);
        orderEndTime=Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime=Constants.nullOrNotBlank(readyStartTime);
        readyEndTime=Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime=Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime=Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }
        Page<SupplyForm> supplyFormPage= formInfoService.getSupplyByThirdAndGoods(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT,thirdId,thirdEnterpriseId,billStatuss, new Pageable(page, size));
        return  new JsonResponseBean(supplyFormPage).toJson();
    }



    /**
     * 查询网点供货明细
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param goodsInfoType
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyStatistical")
    @ResponseBody
    public String getSupplyStatistical(@RequestParam("enterpriseId") Long enterpriseId,
                                   @RequestParam(value="orderStartTime",required = false) String orderStartTime,
                                   @RequestParam(value="orderEndTime",required = false) String orderEndTime,
                                   @RequestParam(value="readyStartTime",required = false) String readyStartTime,
                                   @RequestParam(value="readyEndTime",required = false) String readyEndTime,
                                   @RequestParam(value="warehouseStartTime",required = false) String warehouseStartTime,
                                   @RequestParam(value="warehouseEndTime",required = false) String warehouseEndTime,
                                   @RequestParam(value = "goodsInfoType",required = false) Integer goodsInfoType,
                                   @RequestParam(value = "billStatuss",required = false) List<BillStatus> billStatuss,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime=Constants.nullOrNotBlank(orderStartTime);
        orderEndTime=Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime=Constants.nullOrNotBlank(readyStartTime);
        readyEndTime=Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime=Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime=Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }
        Page<SupplyForm> supplyDetaqils= formInfoService.getSupplyStatistical(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, goodsInfoType, billStatuss, new Pageable(page, size));
        return  new JsonResponseBean(supplyDetaqils).toJson();
    }


    /**
     * 获取当前机构的财富统计报表
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @RequestMapping(value = "/getWealth")
    @ResponseBody
    public String getWealth(@RequestParam("startTime")String startTime,
                            @RequestParam("endTime")String endTime){
        Long enterpriseId=currentLoginService.getCurrentLoginEnterpriseId();
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if(startTime!=null) start=DateUtil.fromString(startTime,Constants.DEFAULT_DAY_FORMAT);
        if(endTime!=null) end=DateUtil.getNight(DateUtil.fromString(endTime,Constants.DEFAULT_DAY_FORMAT));
        WealthForm wealthForm=formInfoService.getWealthFormByEnterpriseId(enterpriseId,start,end);
        wealthForm.setRemainCash(wealthForm.getRemainUdou());
        return new JsonResponseBean(wealthForm).toJson();
    }


    /**
     * 获取指定机构所有子节点的财富统计报表
     * @param enterpriseId 指定的机构
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    @RequestMapping(value = "/getSonWealth")
    @ResponseBody
    public String getSonWealth(@RequestParam("enterpriseId")Long enterpriseId,
                            @RequestParam("startTime")String startTime,
                            @RequestParam("endTime")String endTime){
        Assert.notNull(enterpriseId,"请选定机构");
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if(startTime!=null) start=DateUtil.fromString(startTime,Constants.DEFAULT_DAY_FORMAT);
        if(endTime!=null) end=DateUtil.getNight(DateUtil.fromString(endTime,Constants.DEFAULT_DAY_FORMAT));
        List<WealthForm> wealthForms=formInfoService.getSonWealthForm(enterpriseId, start, end);
        return new JsonResponseBean(wealthForms).toJson();
    }

    /**
     * 会员基础数据表的明细
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getCustomerFormDetail")
    @ResponseBody
    public String getCustomerFormDetail(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                        @RequestParam(value = "startTime",required = false)String startTime,
                                        @RequestParam(value = "endTime",required = false)String endTime,
                                        @RequestParam(value = "page",defaultValue = "1")int page,
                                        @RequestParam(value = "size",defaultValue = "10")int size){
        if (enterpriseId==null) enterpriseId=currentLoginService.getCurrentLoginEnterpriseId();
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }
        Page<CustomerFormDetail> customerFormDetailPage=formInfoService.getCustomerFormByEnterpriseId(enterpriseId,start,end,new Pageable(page,size));
        return new JsonResponseBean(customerFormDetailPage).toJson();
    }


    /**
     * 获取当前机构会员基础数据统计表
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getCustomerFormStatistic")
    @ResponseBody
    public  String getCustomerFormStatistic(@RequestParam(value = "startTime",required =false)String startTime,
                                            @RequestParam(value = "endTime",required = false)String endTime){
        Long enterpriseId=currentLoginService.getCurrentLoginEnterpriseId();
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }
        List<CustomerFormStatistic> list=formInfoService.getCustomerFormStatisticByenterpriseId(enterpriseId,start,end);
        return new JsonResponseBean(list).toJson();
    }

    /**
     * 获取下级机构的会员基础数据统计表
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getSonCustomerFormStatistic")
    @ResponseBody
    public  String getSonCustomerFormStatistic(@RequestParam("enterpriseId") Long enterpriseId,
                                               @RequestParam(value = "startTime",required = false) String startTime,
                                               @RequestParam(value = "endTime",required = false) String endTime){
        Assert.notNull(enterpriseId,"请选择网点");
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }
        List<List<CustomerFormStatistic>> list=formInfoService.getSonCustomerFormStatisticByEnterpriseId(enterpriseId,start,end);
        return new JsonResponseBean(list).toJson();
    }


    /**
     * 区域对账汇总
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/getSupplyAccountStatistic")
    @ResponseBody
    public  String getSupplyAccountStatistic(@RequestParam("enterpriseId") Long enterpriseId,
                                               @RequestParam(value = "startTime",required = false) String startTime,
                                               @RequestParam(value = "endTime",required = false) String endTime,
                                               @RequestParam(value = "statuss",required = false) List<PurchaseBillStatus> statuses){
        Assert.notNull(enterpriseId,"请选择网点");
        startTime=Constants.nullOrNotBlank(startTime);
        endTime=Constants.nullOrNotBlank(endTime);
        Date start=null;
        Date end=null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }
        List<SupplyAccount> list=formInfoService.getSupplyAccountStatistic(enterpriseId,start,end,statuses);
        return new JsonResponseBean(list).toJson();
    }
}
