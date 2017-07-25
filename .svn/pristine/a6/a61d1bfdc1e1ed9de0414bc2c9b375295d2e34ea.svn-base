package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.NewGrandService;
import com.ylife.chinapost.service.UcoinBillManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.BackOrderRefund;
import com.ylife.order.model.OrderRefund;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseAllocation;
import com.ylife.wealth.model.EnterpriseBatchAllocation;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/26.
 * UcoinBillManageAPIController
 */
@Controller
@SecurityResource(parent = "/web/BillManager", primary = false)
@RequestMapping(value = "/web/api/billmanage", produces = "application/json;charset=utf-8")
public class UcoinBillManageAPIController {

    @Resource
    private UcoinBillManageService ucoinBillManageService;

    @Resource
    private NewGrandService newGrandService;

    @Resource
    private CreditOrderService creditOrderService;

    @Resource
    private OrderService orderService;

    /**
     * 获取父企业给本企业分配的记录。
     */
    @RequestMapping(value = "/parentAllocat")
    @ResponseBody
    public String getParentAllocations(@RequestParam(value = "code", required = false) Long code,
                                       @RequestParam(value = "start", required = false) String start,
                                       @RequestParam(value = "end", required = false) String end,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<EnterpriseAllocation> page1 = ucoinBillManageService.getParentAllocations(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }


    /**
     * 根据批量ID获取分配记录。
     */
    @RequestMapping(value = "/getAllocatByBatch")
    @ResponseBody
    public String getAllocationByBatchId(@RequestParam(value = "batchId") long batchId,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<EnterpriseAllocation> page1 = ucoinBillManageService.getAllocationByBatchId(batchId, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取我的分配记录。
     */
    @RequestMapping(value = "/myAllocat")
    @ResponseBody
    public String getMyAllocations(@RequestParam(value = "code", required = false) Long code,
                                   @RequestParam(value = "start", required = false) String start,
                                   @RequestParam(value = "end", required = false) String end,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<EnterpriseBatchAllocation> page1 = ucoinBillManageService.getMyAllocations(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取我的邮宝发放记录。
     */
    @RequestMapping(value = "/myGrand")
    @ResponseBody
    public String getGrands(@RequestParam(value = "code", required = false) Long code,
                            @RequestParam(value = "start", required = false) String start,
                            @RequestParam(value = "end", required = false) String end,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<EnterpriseBatchGrand> page1 = ucoinBillManageService.getGrands(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取我的邮宝扣减记录。
     */
    @RequestMapping(value = "/myDeduct")
    @ResponseBody
    public String getDeducts(@RequestParam(value = "code", required = false) Long code,
                             @RequestParam(value = "start", required = false) String start,
                             @RequestParam(value = "end", required = false) String end,
                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                             @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<CustomerUcoinHistory> page1 = ucoinBillManageService.getDeducts(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 根据批量获取记录。
     *
     * @param batchId 批量ID
     */
    @RequestMapping(value = "/getGrandByBatch")
    @ResponseBody
    public String getGrandHistoryByBatchId(@RequestParam(value = "batchId") long batchId,
                                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                           @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<CustomerUcoinHistory> page1 = ucoinBillManageService.getGrandHistoryByBatchId(batchId, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }


    @RequestMapping(value = "/getEnterpriseSubsidy")
    @ResponseBody
    public String getEnterpriseSubsidy(@RequestParam(value = "code",required = false)Long code,
                                       @RequestParam(value = "start",required = false)String start,
                                       @RequestParam(value = "end",required = false)String end,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Date startTime=Constants.nullOrMorning(start);
        Date endTime=Constants.nullOrNight(end);
        Page<CustomerUcoinHistory> customerUcoinHistoryPage=ucoinBillManageService.getEnterpriseSubsidy(code,startTime,endTime,new Pageable(page,size));
        return new JsonResponseBean(customerUcoinHistoryPage).toJson();

    }

    /**
     * 获取订单退款返还
     * @param backOrderId 退单Id
     * */
    @RequestMapping(value = "/getBackOrderSubsidy")
    @ResponseBody
    public String getBackOrderSubsidy(@RequestParam(value = "backOrderId")long backOrderId){
        BackOrderRefund backOrderRefund =creditOrderService.selectBackOrderRefundByKey(backOrderId);
        return new JsonResponseBean(backOrderRefund).toJson();
    }

    /**
     * 获取网点订单补贴
     * @param orderId 订单Id
     * */
    @RequestMapping(value = "/getOrderSubsidy")
    @ResponseBody
    public String getOrderSubsidy(@RequestParam(value = "orderId")long orderId){
        OrderRefund orderSubsidy=orderService.selectOrderPrimaryKey(orderId);
        return new JsonResponseBean(orderSubsidy).toJson();
    }

/**-----------------------------------------市局统一发放扣减的账单-------------------------------------------------------------------**/

    /**
     * 获取我的邮宝发放记录。
     */
    @RequestMapping(value = "/getBatchGrands")
    @ResponseBody
    public String getBatchGrands(@RequestParam(value = "code", required = false) Long code,
                            @RequestParam(value = "start", required = false) String start,
                            @RequestParam(value = "end", required = false) String end,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<EnterpriseBatchGrand> page1 = newGrandService.getMyGrand(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }


    /**
     * 获取我的邮宝发放记录。
     */
    @RequestMapping(value = "/getBatchDecut")
    @ResponseBody
    public String getBatchDecuts(@RequestParam(value = "code", required = false) Long code,
                                 @RequestParam(value = "start", required = false) String start,
                                 @RequestParam(value = "end", required = false) String end,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<EnterpriseBatchGrand> page1 = newGrandService.getMyDecut(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }



    @RequestMapping(value = "/getDetailByBatch")
    @ResponseBody
    public String getDetailByBatch(@RequestParam(value = "batchId", required = false) Long batchId,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Page<CustomerUcoinHistory> page1 = ucoinBillManageService.getGrandHistoryByBatchId(batchId, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取网点补贴。
     */
    @RequestMapping(value = "/getEnterpriseIdOrderSubsidy")
    @ResponseBody
    public String getEnterpriseIdOrderSubsidy(@RequestParam(value = "code", required = false) Long code,
                                 @RequestParam(value = "start", required = false) String start,
                                 @RequestParam(value = "end", required = false) String end,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<CurrEnterpriseBill> orderPage = newGrandService.getEnterpriseIdOrderSubsidy(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(orderPage).toJson();
    }

    /**
     * 获取订单退款返还。
     */
    @RequestMapping(value = "/getEnterpriseIdBackOrderSubsidy")
    @ResponseBody
    public String getEnterpriseIdBackOrderSubsidy(@RequestParam(value = "code", required = false) Long code,
                                              @RequestParam(value = "start", required = false) String start,
                                              @RequestParam(value = "end", required = false) String end,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);
        Page<CurrEnterpriseBill> backOrderPage = newGrandService.getEnterpriseIdBackOrderSubsidy(code, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(backOrderPage).toJson();
    }


}
