package com.ylife.chinapost.boss.util;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * NewURLInterceptor.
 *
 * @Author henry .
 * @Create 2017-07-04 17:26.
 */
public class NewURLInterceptor extends HandlerInterceptorAdapter {
    private static final String MENUID = "menuId";
    private static final String MENUPARENTID = "menuParentId";
    /**
     * 在请求处理前拦截URL 进行相应处理
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        sessionMenu(request);
        return true;
    }

    public static void sessionMenu(HttpServletRequest request) {
        if (request.getParameter(MENUID) != null) {
            request.getSession().setAttribute(MENUID, request.getParameter(MENUID));
        }
        if (request.getParameter(MENUPARENTID) != null) {
            request.getSession().setAttribute(MENUPARENTID, request.getParameter(MENUPARENTID));
        }
    }
}
