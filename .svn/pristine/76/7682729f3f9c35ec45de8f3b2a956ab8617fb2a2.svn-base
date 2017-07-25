package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.RequisitionManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/20.
 * 请款单据。
 */
@Controller
@SecurityResource(parent = "/web/Requisition", primary = false)
@RequestMapping(value = "/web/api/requisition", produces = "application/json;charset=utf-8")
public class RequisitionManageAPIController {

    @Resource
    private RequisitionManageService requisitionManageService;

    /**
     * 获取我的请求。
     */
    @RequestMapping("/myReq")
    @ResponseBody
    public String getMyRequisition(@RequestParam(value = "code", required = false) Long code,
                                   @RequestParam(value = "status", required = false) RequisitionStatus status,
                                   @RequestParam(value = "start", required = false) String start,
                                   @RequestParam(value = "end", required = false) String end,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<EnterpriseRequisition> page1 = requisitionManageService.getMyRequisition(code, status, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 我的待办。
     */
    @RequestMapping("/myNotHandleReq")
    @ResponseBody
    public String getNotHandleRequisition(@RequestParam(value = "code", required = false) Long code,
                                          @RequestParam(value = "status", required = false) RequisitionStatus status,
                                          @RequestParam(value = "start", required = false) String start,
                                          @RequestParam(value = "end", required = false) String end,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                          @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<EnterpriseRequisition> page1 = requisitionManageService.getNotHandleRequisition(code, status, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 我的已办。
     */
    @RequestMapping("/myHandledReq")
    @ResponseBody
    public String getHandledRequisition(@RequestParam(value = "code", required = false) Long code,
                                        @RequestParam(value = "status", required = false) RequisitionStatus status,
                                        @RequestParam(value = "start", required = false) String start,
                                        @RequestParam(value = "end", required = false) String end,
                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                        @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<EnterpriseRequisition> page1 = requisitionManageService.getHandledRequisition(code, status, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 通过。
     */
    @RequestMapping("/passReq")
    @ResponseBody
    public String passRequisition(@RequestParam("id") long requisitionId) {
        requisitionManageService.passRequisition(requisitionId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 拒绝。
     */
    @RequestMapping("/denyReq")
    @ResponseBody
    public String denyRequisition(@RequestParam("id") long requisitionId) {
        requisitionManageService.denyRequisition(requisitionId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 支付。
     */
    @RequestMapping("/payReq")
    @ResponseBody
    public String payRequisition(@RequestParam("id") long requisitionId,
                                 @RequestParam("payFee") BigDecimal payFee,
                                 @RequestParam("paykey") String paykey) {
        requisitionManageService.payRequisition(requisitionId, payFee, paykey);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 所有状态。
     */
    @RequestMapping("/statuses")
    @ResponseBody
    public String getStatuses() {
        return new JsonResponseBean(RequisitionStatus.values()).toJson();
    }

}
