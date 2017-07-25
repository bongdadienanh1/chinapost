package com.ylife.chinapost.controller;

import com.ylife.security.captcha.CaptchaOutputter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/4/27.
 * 验证码控制器。
 */
@Controller
@RequestMapping("/web")
public class CaptchaController {

    @Resource
    private CaptchaOutputter captchaOutputter;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaOutputter.outPut(request, response);
    }
}
