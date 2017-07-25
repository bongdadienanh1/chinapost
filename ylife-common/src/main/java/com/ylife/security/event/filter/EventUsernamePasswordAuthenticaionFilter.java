package com.ylife.security.event.filter;

import com.ylife.security.event.EventHandler;
import com.ylife.security.event.EventType;
import com.ylife.security.event.SecurityEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/4/27.
 * 使用了验证码的验证过滤器。
 */
public class EventUsernamePasswordAuthenticaionFilter extends UsernamePasswordAuthenticationFilter {

    private EventHandler eventHandler;

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        SecurityEvent event = new SecurityEvent(EventType.LOGIN_SUCCESS, request, obtainUsername(request));
        eventHandler.handler(event);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        SecurityEvent event = new SecurityEvent(EventType.LOGIN_FAILURE, request, obtainUsername(request));
        eventHandler.handler(event);
    }


    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}
