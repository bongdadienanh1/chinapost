package com.ylife.chinapost.third.controller;

import com.ylife.chinapost.third.controller.utils.Constants;
import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.exception.UserNotLoginException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by suibian on 2017-06-13.
 */
@Controller
public class MainController {
    @Resource
    private CurrentLoginService currentLoginService;

    @ExceptionHandler(UserNotLoginException.class)
    public String handlerUserNotLoginException() {
        return "/newlogin";
    }

    /**
     * 根据分类，分页查询图片信息，以供选择
     * @return
     */
    @RequestMapping("/queryImageManageByPbAndCidForChoose")
    public ModelAndView queryImageManageByPbAndCidForChoose(String location) {
        ModelAndView mav = new ModelAndView();
        /** 设置视图 */
        mav.setViewName("/image/chooseImage" + (location == null ? "" : ("_" + location)));
        mav.addObject("VERSION", Constants.JS_VERSION);
        /** 返回视图结果 */
        return mav;
    }

    /**
     * 登录成功跳转添加商品页面
     * */
    @RequestMapping("/gotoAddGoods")
    public ModelAndView gotoAddGoods() {
        //判断是否登录
        currentLoginService.getCurrentLoginThirdId();

        ModelAndView mav = new ModelAndView();
        mav.addObject("firstNav", '1');
        mav.addObject("twoNav", '1');
        mav.setViewName("/upthirdgoods");
        mav.addObject("VERSION", Constants.JS_VERSION);
        return mav;

    }
}