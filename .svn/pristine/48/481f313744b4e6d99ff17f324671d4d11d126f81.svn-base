package com.ylife.chinapost.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于生成securityMetadataSource。
 */
public class EnterpriseSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    static HashMap<RequestMatcher, Set<ConfigAttribute>> requestMap = new HashMap<>();
    static Set<ConfigAttribute> allAttribute = new HashSet<>();

    @Resource
    private EnterpriseHoldRequestMap enterpriseHoldRequestMap;

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return allAttribute;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    //获取资源所需权限。这里仅仅放入资源的ID
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 把对象转化为请求
        final HttpServletRequest request = ((FilterInvocation) object)
                .getRequest();
        // 看看有没有可以匹配的,如果有匹配的就立刻返回
        return requestMap.get(new AntPathRequestMatcher(request.getServletPath()));
    }

    @PostConstruct
    public void init() {
        enterpriseHoldRequestMap.init();
    }

}