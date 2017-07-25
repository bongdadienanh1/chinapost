package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.service.HierarchyManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/4/14.
 * 层级管理控制器。
 */
@Controller
@SecurityResource(parent = "/web/hiemanager", primary = false)
@RequestMapping(value = "/web/api/hierarchy", produces = "application/json;charset=utf-8")
public class HierarchyManageAPIController {

    @Resource
    private HierarchyManageService hierarchyManageService;

    /**
     * 添加。
     *
     * @param name     企业名称
     * @param username 用户名
     * @param password 支付密码
     */
    @RequestMapping("/addSon")
    @ResponseBody
    public String addSon(@RequestParam("name") String name,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password,
                         @RequestParam(value = "discountPct", required = false) BigDecimal discountPct,
                         @RequestParam("end") boolean end,
                         @RequestParam(value = "hasPermit", required = false) Boolean hasPermit,
                         @RequestParam(value = "organizationName", required = false) String organizationName) {
        if (discountPct != null) {
            Assert.isTrue(discountPct.compareTo(BigDecimal.ZERO) == 1 || discountPct.compareTo(new BigDecimal(10000000)) <= 0, "网点折扣比例必须大于0小于等于10000000.");
        }
        Assert.hasLength(name);
        Assert.hasLength(username);
        Assert.hasLength(password);
        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            throw new IllegalArgumentException("密码为8-20位的数字和字母组合");
        }
        if(username.equals(password)){
            throw new IllegalArgumentException("登录密码不能与帐号相同");
        }
        hierarchyManageService.addSonEnterprise(name, discountPct, end, hasPermit, username, password, StringUtil.nullOrNotBlank(organizationName));
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 编辑。
     *
     * @param enterpriseId 企业ID
     * @param name         企业名称
     * @param hasPermit    是否有代客下单权限
     */
    @RequestMapping("/editSon")
    @ResponseBody
    public String editSon(@RequestParam("enterpriseId") Long enterpriseId,
                          @RequestParam("name") String name,
                          @RequestParam(value = "discountPct", required = false) BigDecimal discountPct,
                          @RequestParam(value = "end", required = false) Boolean end,
                          @RequestParam(value = "hasPermit", required = false) Boolean hasPermit,
                          @RequestParam(value = "organizationName", required = false) String organizationName) {
        if (discountPct != null) {
            Assert.isTrue(discountPct.compareTo(BigDecimal.ZERO) == 1 || discountPct.compareTo(new BigDecimal(10000000)) <= 0, "网点折扣比例必须大于0小于等于10000000.");
        }
        Assert.hasLength(name);
        hierarchyManageService.editEnterprise(enterpriseId, discountPct, end, hasPermit, name,StringUtil.nullOrNotBlank(organizationName));
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 删除。
     */
    @RequestMapping("/deleteSon")
    @ResponseBody
    public String deleteSon(@RequestParam("enterpriseId") Long enterpriseId) {
        hierarchyManageService.deleteEnterprise(enterpriseId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 设置密码。
     */
    @RequestMapping("/editPassword")
    @ResponseBody
    public String editPassword(@RequestParam("enterpriseId") Long enterpriseId,
                               @RequestParam("password") String password) {
        Assert.hasLength(password.trim(), "密码不能为空。");
        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            throw new IllegalArgumentException("密码为8-20位的数字和字母组合");
        }
        hierarchyManageService.editPassword(enterpriseId, password.trim());
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 设置支付密码。
     */
    @RequestMapping("/editPayKey")
    @ResponseBody
    public String editPayKey(@RequestParam("enterpriseId") Long enterpriseId,
                             @RequestParam("payKey") String payKey) {
        Assert.hasLength(payKey.trim(), "支付密码不能为空。");
        hierarchyManageService.editPayKey(enterpriseId, payKey.trim());
        return JsonResponseBean.getSuccessResponse().toJson();
    }
}
