package com.ylife.security.handler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/12/15.
 * 登录失效处理器。
 */
public interface LoginExpiredHandler {

    void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}
