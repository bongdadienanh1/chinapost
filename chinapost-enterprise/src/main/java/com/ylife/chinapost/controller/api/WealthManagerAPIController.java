package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.service.WealthManageService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseRequisition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/11.
 * 财富管理控制器
 */
@Controller
@SecurityResource(parent = "/web/UCoinManager", primary = false)
@RequestMapping(value = "/web/api/wealth", produces = "application/json;charset=utf-8")
public class WealthManagerAPIController {

    private Parser parser = new SimpleParser();

    @Resource
    private WealthManageService wealthManageService;

    /**
     * 申请。
     *
     * @param amount 数量
     * @param reason 原因
     */
    @RequestMapping("/requisition")
    @ResponseBody
    public String launchRequisition(@RequestParam("amount") BigDecimal amount, @RequestParam("reason") String reason) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) == 1, "申请数量必须大于0");
        wealthManageService.launchRequisition(amount, reason);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 分配。
     *
     * @param allocationJson 分配的JSON
     * @param paykey         支付密码
     */
    @RequestMapping("/allocation")
    @ResponseBody
    public String allocation(@RequestParam("allocationJson") String allocationJson, @RequestParam("paykey") String paykey) {
        Map<Long, BigDecimal> allocations = parser.parseJSON(allocationJson, new TypeToken<Map<Long, BigDecimal>>() {
        });
        wealthManageService.wealthAllocat(paykey, allocations);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 获取最近账单。
     */
    @RequestMapping("/getCurrentBill")
    @ResponseBody
    public String getCurrentBill() {
        List<CurrEnterpriseBill> list = wealthManageService.getCurrentBill();
        return new JsonResponseBean(list).toJson();
    }

    /**
     * 获取最近请款单。
     */
    @RequestMapping("/getCurrentReq")
    @ResponseBody
    public String getCurrentReq() {
        List<EnterpriseRequisition> list = wealthManageService.getCurrentReq();
        return new JsonResponseBean(list).toJson();
    }
}
