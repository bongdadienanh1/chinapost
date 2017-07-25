package com.ylife.security.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/12/15.
 * 重定向
 */
public class RedirectLoginExpiredHandler implements LoginExpiredHandler, InitializingBean {

    private String expiredUrl;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public RedirectLoginExpiredHandler() {
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (expiredUrl != null) {
            redirectStrategy.sendRedirect(request, response, expiredUrl);
        } else {
            response.getWriter().print("This session has been expired (possibly due to multiple concurrent " + "logins being attempted as the same user).");
            response.flushBuffer();
        }

    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.isTrue(expiredUrl == null || UrlUtils.isValidRedirectUrl(expiredUrl), expiredUrl + " isn't a valid redirect URL");
        Assert.notNull(redirectStrategy, "redirectStrategy required");
    }
}
