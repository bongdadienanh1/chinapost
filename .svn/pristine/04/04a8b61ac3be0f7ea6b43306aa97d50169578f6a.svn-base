package com.ylife.chinapost.security;

import com.ylife.security.handler.SendJsonDecider;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

public class EnterpriseAjaxSendJsonDecider implements SendJsonDecider {

    private static String DEFAULT_AJAX_RUL = "/web/api/**";

    private RequestMatcher pathMatcher = new AntPathRequestMatcher(DEFAULT_AJAX_RUL);

    @Override
    public boolean decide(HttpServletRequest request) {
        return pathMatcher.matches(request);
    }

    public void setAjaxUrl(String ajaxUrl) {
        pathMatcher = new AntPathRequestMatcher(ajaxUrl);
    }

}
