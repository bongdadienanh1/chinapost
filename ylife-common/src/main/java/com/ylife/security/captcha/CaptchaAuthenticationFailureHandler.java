package com.ylife.security.captcha;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CaptchaAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest arg0, HttpServletResponse response, AuthenticationException arg2) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String message;
        if (arg2 instanceof CaptchaException)
            message = JsonResponseBean.getErrorResponse(ErrorCode.CAPTCHA_ERROR, arg2.getMessage()).toJson();
        else
            message = JsonResponseBean.getErrorResponse(ErrorCode.AUTHENTICATE_FAILURE, arg2.getMessage()).toJson();

        writer.write(message);
        writer.flush();
        writer.close();

    }

}
