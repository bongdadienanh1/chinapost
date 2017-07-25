package com.ylife.security.handler;

import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest arg0, HttpServletResponse response, AccessDeniedException arg2) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        String message = JsonResponseBean.getErrorResponse(ErrorCode.ACCESS_DENIED, arg2.getMessage()).toJson();
        writer.write(message);
        writer.flush();
        writer.close();
    }

}
