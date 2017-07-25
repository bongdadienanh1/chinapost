package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.AccountCenterService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/5/11.
 * 账号控制器
 */
@Controller
@SecurityResource(parent = "/web/Account", primary = false)
@RequestMapping(value = "/web/api/accountcenter", produces = "application/json;charset=utf-8")
public class AccountServiceAPIController {

    @Resource
    private AccountCenterService accountCenterService;

    @Resource
    private CurrentLoginService currentLoginService;

    @RequestMapping("/editEnterPriseInfo")
    @ResponseBody
    public String editEnterpriseInfo(@RequestParam(value = "enterpriseName", required = false) String enterpriseName,
                                     @RequestParam(value = "profile", required = false) String profile,
                                     @RequestParam(value = "imgUrl", required = false) String imgUrl,
                                     @RequestParam(value = "provinceId", required = false) Long provinceId,
                                     @RequestParam(value = "cityId", required = false) Long cityId,
                                     @RequestParam(value = "districtId", required = false) Long districtId,
                                     @RequestParam(value = "addrDetail", required = false) String addrDetail,
                                     @RequestParam(value = "registerAddress", required = false) String registerAddress,
                                     @RequestParam(value = "linkman", required = false) String linkman,
                                     @RequestParam(value = "mobile", required = false) String mobile) {
        addrDetail = StringUtil.nullOrNotBlank(addrDetail);
        mobile = StringUtil.nullOrNotBlank(mobile);
        accountCenterService.editEnterPriseInfo(enterpriseName, profile, imgUrl, registerAddress, linkman, provinceId, cityId, districtId, addrDetail, mobile);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editAccountInfo")
    @ResponseBody
    public String editAccountInfo(@RequestParam(value = "accountName", required = false) String accountName,
                                  @RequestParam(value = "provinceId", required = false) Long provinceId,
                                  @RequestParam(value = "cityId", required = false) Long cityId,
                                  @RequestParam(value = "districtId", required = false) Long districtId,
                                  @RequestParam(value = "addrDetail", required = false) String addrDetail,
                                  @RequestParam(value = "linkman", required = false) String linkman,
                                  @RequestParam(value = "linkMobile", required = false) String mobile) {
        mobile = StringUtil.nullOrNotBlank(mobile);
        if (mobile != null) {
            Assert.isValid(Constants.PHONE_VALIDATOR, mobile, "手机号码格式不正确。");
        }
        accountCenterService.editAccountInfo(accountName, provinceId, cityId, districtId, addrDetail, linkman, mobile);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editpassword")
    @ResponseBody
    public String editPassword(@RequestParam(value = "prePwd") String prePwd, @RequestParam(value = "password") String password) {
        if (StringUtil.isBlank(password.trim())) {
            throw new IllegalArgumentException("密码不能为空。");
        }
        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            throw new IllegalArgumentException("密码为8-20位的数字和字母组合");
        }
        if(currentLoginService.getCurrentLoginUsername().equals(password)){
            throw new IllegalArgumentException("登录密码不能与帐号相同");
        }
        accountCenterService.editPassword(prePwd, password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editpaykey")
    @ResponseBody
    public String editPayKey(@RequestParam(value = "prePK", required = false) String prePK, @RequestParam(value = "paykey") String paykey) {
        if (StringUtil.isBlank(paykey.trim())) {
            throw new IllegalArgumentException("支付密码不能为空。");
        }
        accountCenterService.editPayKey(prePK, paykey);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    @RequestMapping("/editIdentification")
    @ResponseBody
    public String editIdentification(String identification) {
        if (StringUtil.isBlank(identification.trim())) {
            throw new IllegalArgumentException("机构标识不能为空。");
        }
        accountCenterService.editIdentification(identification);
        return JsonResponseBean.getSuccessResponse().toJson();
    }






}
