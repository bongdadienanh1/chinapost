package com.ylife.chinapost.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/5/6.
 */
@Controller
public class MainController {

    /**
     * 进入首页
     * @return
     */
    @RequestMapping("/main")
    public ModelAndView index(){
        return new ModelAndView("redirect:initMain.htm");
    }


    /**
     * 进入模本页
     * @return
     */
    @RequestMapping("/subject1")
    public ModelAndView subject(Long subjectId){
        return new ModelAndView("subject/topic_subject".concat(subjectId.toString()));
    }
}
