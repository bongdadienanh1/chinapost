/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.chinapost.mobile.util;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpServletRequest;

/**
 *登录帮助类
 * */
@Controller
public final class LoginUtil {

    /**
     * 验证登录
     *
     * @param request
     * @return false 未登录 true已登录
     */
    public static boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("customerId") != null) {
            return true;
        }
        return false;
    }
}
