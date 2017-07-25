package com.ylife.chinapost.boss.controller;

import com.ylife.chinapost.boss.controller.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by suibian on 2017-06-13.
 */
@Controller
public class MianController {
    /**
     * 根据分类，分页查询图片信息，以供选择
     * @return
     */
    @RequestMapping("/queryImageManageByPbAndCidForChoose")
    public ModelAndView queryImageManageByPbAndCidForChoose(String location) {
        ModelAndView mav = new ModelAndView();
        /** 设置视图 */
        mav.setViewName("/infoImage/chooseImage" + (location == null ? "" : ("_" + location)));
        mav.addObject("VERSION", Constants.JS_VERSION);
        /** 返回视图结果 */
        return mav;
    }
}
