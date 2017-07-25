package com.ylife.security.handler;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by InThEnd on 2016/12/15.
 * 区分重定向或者发送json.
 */
public class PartJsonLoginExpiredHandler implements LoginExpiredHandler {

    private String expiredUrl;

    private SendJsonDecider checker = null;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if ((checker != null) && (checker.decide(request))) {
            PrintWriter writer = response.getWriter();
            String message = JsonResponseBean.getErrorResponse(ErrorCode.LOGIN_EXPIRED, "登陆过期，请重新登陆。").toJson();
            writer.write(message);
            writer.flush();
            writer.close();
            return;
        }

        String targetUrl = expiredUrl;

        if (targetUrl != null) {
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            response.getWriter().print("This session has been expired (possibly due to multiple concurrent " + "logins being attempted as the same user).");
            response.flushBuffer();
        }
    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    public void setChecker(SendJsonDecider checker) {
        this.checker = checker;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
}
