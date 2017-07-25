package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.authority.model.ResourcePage;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.security.EnterpriseHoldRequestMap;
import com.ylife.chinapost.service.RoleManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Xukai on 2016/4/18.
 * 角色管理。
 */
@Controller
@SecurityResource(parent = "/web/RoleManager", primary = false)
@RequestMapping(value = "/web/api/rolemanager", produces = "application/json;charset=utf-8")
public class RoleManageAPIController {

    @Resource
    private RoleManageService roleManageService;
    @Resource
    private EnterpriseHoldRequestMap enterpriseHoldRequestMap;

    @RequestMapping("/addNewAuthority")
    @ResponseBody
    public String addAuthority(@RequestParam(value = "name") String name,
                               @RequestParam(value = "pageIds", required = false) String pageIds,
                               @RequestParam(value = "remark", required = false) String remark) {
        Assert.hasLength(name);
        long[] array = Constants.SIMPLE_PARSER.parseJSON(pageIds, new TypeToken<long[]>() {
        });
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("pageIds不能为空。");
        }
        roleManageService.addNewAuthority(name, array, remark);
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/deleteAuthority")
    @ResponseBody
    public String deleteAuthority(@RequestParam(value = "authorityIds", required = false) String authorityIds) {
        long[] ids = Constants.SIMPLE_PARSER.parseJSON(authorityIds, new TypeToken<long[]>() {
        });
        for (long authorityId : ids) {
            roleManageService.deleteAuthority(authorityId);
        }
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editAuthority")
    @ResponseBody
    public String editAuthority(@RequestParam(value = "authorityId", required = false) long authorityId,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "pageIds", required = false) String pageIds,
                                @RequestParam(value = "remark", required = false) String remark) {

        long[] ids = Constants.SIMPLE_PARSER.parseJSON(pageIds, new TypeToken<long[]>() {
        });
        roleManageService.editAuthority(authorityId, ids, name, remark);
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/getAuthority")
    @ResponseBody
    public String getAuthority(@RequestParam(value = "authorityId", required = false) long authorityId) {
        List<ResourcePage> pageList = roleManageService.getAuthorityPages(authorityId);
        return new JsonResponseBean(pageList).leave("id").toJson();
    }

    @RequestMapping("/addNewmanager")
    @ResponseBody
    public String addAuthority(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "staffname") String staffname,
                               @RequestParam(value = "photoImg", required = false) String photoImg,
                               @RequestParam(value = "cellphone") String cellphone,
                               @RequestParam(value = "authorityId") long authorityId) {
        Assert.isValid(Constants.PHONE_VALIDATOR, cellphone, "手机号码格式不正确。");
        Assert.hasLength(cellphone);
        Assert.hasLength(staffname);
        Assert.hasLength(password);
        Assert.hasLength(username);

        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            throw new IllegalArgumentException("密码为8-20位的数字和字母组合");
        }
        if(username.equals(password)){
            throw new IllegalArgumentException("登录密码不能与帐号相同");
        }
        roleManageService.addNewManager(username, password, staffname, photoImg, cellphone, authorityId);
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/deletemanager")
    @ResponseBody
    public String deletemanager(@RequestParam("managerIds") String managerIds) {
        long[] ids = Constants.SIMPLE_PARSER.parseJSON(managerIds, new TypeToken<long[]>() {
        });
        for (long managerId : ids) {
            roleManageService.deleteManager(managerId);
        }
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editmanagerpassword")
    @ResponseBody
    public String editManagerPassword(@RequestParam("managerId") long managerId,
                                      @RequestParam(value = "password") String password) {
        Assert.hasLength(password);
        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            throw new IllegalArgumentException("密码为8-20位的数字和字母组合");
        }
        roleManageService.editManagerPassword(managerId, password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/editManagerBuff")
    @ResponseBody
    public String editManagerBuff(@RequestParam(value = "managerId", required = false) long managerId,
                                  @RequestParam(value = "staffname") String staffname,
                                  @RequestParam(value = "cellphone") String cellphone,
                                  @RequestParam(value = "nonDisabled") Boolean nonDisabled,
                                  @RequestParam(value = "authorityId") long authorityId) {
        Assert.hasLength(staffname, cellphone);
        Assert.isValid(Constants.PHONE_VALIDATOR, cellphone, "手机号码格式不正确。");
        roleManageService.editManagerBuff(managerId, staffname, cellphone, !nonDisabled, authorityId);
        enterpriseHoldRequestMap.init();
        return JsonResponseBean.getSuccessResponse().toJson();
    }
}
