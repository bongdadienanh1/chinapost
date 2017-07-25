package com.ylife.chinapost.mobile.controller;

import com.ylife.chinapost.mobile.service.AccountService;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/5/6.
 * 登录控制器
 */
@Controller
public class LoginController {
    //用户service
    @Resource
    private AccountService accountService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request, String url) {
        if (StringUtil.isBlank(url)) {
            url = "main";
        }
        request.setAttribute("url", url);
        return "register";
    }

    /**
     * 验证登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 1表示用户名密码正确，0表示用户名或密码不正确
     */
    @RequestMapping(value = "/checkLogin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkLogin(String username, String password) {
        accountService.login(username, password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 退出 跳转登录
     *
     * @return ModelAndView
     */
    @RequestMapping("/logout")
    public ModelAndView loginOut(HttpServletRequest request) {
        // 清空session已登录数据 用户信息
        request.getSession().removeAttribute("customerId");
        // 跳转到登陆页面
        return new ModelAndView(new RedirectView("login.htm"));
    }
}
