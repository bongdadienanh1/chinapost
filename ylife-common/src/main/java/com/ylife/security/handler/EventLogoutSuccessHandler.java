package com.ylife.security.handler;

import com.ylife.security.SecurityUtils;
import com.ylife.security.event.EventHandler;
import com.ylife.security.event.EventType;
import com.ylife.security.event.SecurityEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by InThEnd on 2016/12/15.
 * EventLogoutSuccessHandler
 */
public class EventLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

    private EventHandler eventHandler;

    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        super.handle(request, response, authentication);
        if (eventHandler != null) {
            SecurityEvent event = new SecurityEvent(EventType.LOGOUT_SUCCESS, request, SecurityUtils.getUsernameFromAuthentication(authentication));
            eventHandler.handler(event);
        }
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
}
