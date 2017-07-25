package com.ylife.security.event.filter;

import com.ylife.security.SecurityUtils;
import com.ylife.security.event.EventHandler;
import com.ylife.security.event.EventType;
import com.ylife.security.event.SecurityEvent;
import com.ylife.security.handler.LoginExpiredHandler;
import com.ylife.security.handler.RedirectLoginExpiredHandler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class EventConcurrentSessionFilter extends GenericFilterBean {

    private SessionRegistry sessionRegistry = new SessionRegistryImpl();

    private LogoutHandler[] handlers = new LogoutHandler[]{new SecurityContextLogoutHandler()};

    private EventHandler eventHandler;

    private LoginExpiredHandler loginExpiredHandler = new RedirectLoginExpiredHandler();

    public EventConcurrentSessionFilter() {
    }

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(sessionRegistry, "SessionRegistry required");
        Assert.notNull(loginExpiredHandler, "loginExpiredHandler required");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        if (session != null) {
            SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

            if (info != null) {
                if (info.isExpired()) {

                    String username = SecurityUtils.getUsername();

                    // Expired - abort processing
                    doLogout(request, response);

                    loginExpiredHandler.handle(request, response);

                    if (eventHandler != null) {
                        SecurityEvent event = new SecurityEvent(EventType.LOGIN_EXPIRED, request, username);
                        eventHandler.handler(event);
                    }
                    return;
                } else {
                    // Non-expired - update last request date/time
                    sessionRegistry.refreshLastRequest(info.getSessionId());
                }
            }
        }

        chain.doFilter(request, response);
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (LogoutHandler handler : handlers) {
            handler.logout(request, response, auth);
        }
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void setLogoutHandlers(LogoutHandler[] handlers) {
        Assert.notNull(handlers);
        this.handlers = handlers;
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    public void setLoginExpiredHandler(LoginExpiredHandler loginExpiredHandler) {
        this.loginExpiredHandler = loginExpiredHandler;
    }
}
