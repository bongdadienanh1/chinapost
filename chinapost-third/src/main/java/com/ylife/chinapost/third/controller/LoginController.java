package com.ylife.chinapost.third.controller;

import com.ylife.chinapost.third.service.LoginService;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by suibian on 2017-06-30.
 */
@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public String login() {
        return "/newlogin";
    }

    /**
     * 账号密码登录third
     * @param userName 用户名
     * @param password 密码
     * @param code 验证码
     * @return
     * */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public String checkLogin(HttpServletRequest request,String userName, String password, String code){
        Integer num;
        if (null == code || !code.equals(request.getSession().getAttribute("k_captcha_text_key"))) {
            num = 202;
        }else if(StringUtil.isBlank(userName) || StringUtil.isBlank(password)){
            num = 203;
        }else {
            num = loginService.checkLogin(request,userName,password);
        }
        return num.toString();
    }

    /**
     * 退出登录
     *
     * @return ModelAndView
     */
    @RequestMapping("/logOut")
    public ModelAndView logOut(HttpServletRequest request) {
        // 退出登陆
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("storeName");
        return new ModelAndView("/newlogin");
    }
}
