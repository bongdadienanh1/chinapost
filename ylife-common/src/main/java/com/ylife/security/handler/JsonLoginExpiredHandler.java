package com.ylife.security.handler;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by InThEnd on 2016/12/15.
 * 登录过期处理器。
 */
public class JsonLoginExpiredHandler implements LoginExpiredHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter writer = response.getWriter();
        String message = JsonResponseBean.getErrorResponse(ErrorCode.LOGIN_EXPIRED, "登陆过期，请重新登陆。").toJson();
        writer.write(message);
        writer.flush();
        writer.close();
    }
}
