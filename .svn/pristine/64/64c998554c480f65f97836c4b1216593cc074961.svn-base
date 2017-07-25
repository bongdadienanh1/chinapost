package com.ylife.security.event;

import javax.servlet.ServletRequest;

/**
 * Created by InThEnd on 2016/12/14.
 * 验证事件。
 */
public class SecurityEvent {

    private String username;

    private EventType eventType;

    private ServletRequest request;

    public SecurityEvent(EventType eventType, ServletRequest request, String username) {
        this.eventType = eventType;
        this.request = request;
        this.username = username;
    }


    public EventType getEventType() {
        return eventType;
    }

    public String getUsername() {
        return username;
    }

    public ServletRequest getRequest() {
        return request;
    }
}
