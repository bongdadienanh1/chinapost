package com.ylife.security;

import com.ylife.security.handler.SendJsonDecider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

public class EntryPointSendJsonDecider implements SendJsonDecider, InitializingBean {

    private RequestMatcher webUrlMatcher = null;

    private RequestMatcher ajaxUrlMatcher = null;

    public EntryPointSendJsonDecider() {
    }

    @Override
    public boolean decide(HttpServletRequest request) {
        return !(webUrlMatcher.matches(request) && !ajaxUrlMatcher.matches(request));
    }

    public void setWebUrl(String webUrl) {
        webUrlMatcher = new AntPathRequestMatcher(webUrl);
    }

    public void setAjaxUrl(String ajaxUrl) {
        ajaxUrlMatcher = new AntPathRequestMatcher(ajaxUrl);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(webUrlMatcher, "webUrl required.");
        Assert.notNull(ajaxUrlMatcher, "ajaxUrl required.");
    }
}
