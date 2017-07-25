package com.ylife.chinapost.boss.controller;

import com.ylife.chinapost.boss.service.ManagerOuterService;
import com.ylife.utils.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by suibian on 2017-06-12.
 */
@Controller
public class ManagerController {
    /**
     * 日志记录 *
     */
    private static final MyLogger LOGGER = new MyLogger(ManagerController.class);

    @Resource
    private ManagerOuterService managerOuterService;

    /**
     * 管理员登陆
     *
     * @return 获取权限跳转地址
     */
    @RequestMapping("/iflogin")
    @ResponseBody
    public String ifLogin(HttpServletRequest request, String name, String password, String code) {
        //验证码匹配
        Integer num = -1;
        if (null == code || !code.equals(request.getSession().getAttribute("k_captcha_text_key"))) {
            return num.toString();
        }
        // 非空验证 用户名
        if (null != name) {
            LOGGER.info("管理员【" + name + "】登陆");
        }
        num = managerOuterService.ifManager(request, name, password);
        return num.toString();
    }

    /**
     * 登录跳转页面
     * */
    @RequestMapping("/checkLogin")
    public ModelAndView checkLogin(HttpServletRequest request){
        if(request.getSession().getAttribute("loginUserId") != null){
            return new ModelAndView("redirect:queryMobHomePage.htm?menuParentId=0");
        }else {
            return new ModelAndView("/login");
        }
    }

    /**
     * 退出登录
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView logOut(HttpServletRequest request) {
        // 退出登陆
        request.getSession().removeAttribute("loginUserId");
        request.getSession().removeAttribute("name");
        return new ModelAndView("/login");
    }
}