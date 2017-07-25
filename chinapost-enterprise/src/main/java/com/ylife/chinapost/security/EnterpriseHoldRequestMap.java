package com.ylife.chinapost.security;

import com.ylife.authority.model.Authority;
import com.ylife.authority.model.ResourcePage;
import com.ylife.chinapost.security.service.EnterpriseSecurityService;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/30.
 */
@Service
public class EnterpriseHoldRequestMap {

    private static HashMap<RequestMatcher, Set<ConfigAttribute>> requestMap = new HashMap<>();

    private static Set<ConfigAttribute> allAttribute = new HashSet<>();


    @Resource
    private EnterpriseSecurityService enterpriseSecurityService;

    /**
     * 初始化所有的资源
     */
    public void init() {
        // 读取所有权限点
        Collection<Authority> allAuthority = enterpriseSecurityService.getAllAuthority();
        for (Authority authEntity : allAuthority) {
            SecurityConfig attrConfig = new SecurityConfig(authEntity.getId().toString());
            allAttribute.add(attrConfig);
        }
        // 读取所有资源
        Collection<ResourcePage> allResources = enterpriseSecurityService.getAllResourcePage();
        // 循环所有资源
        for (ResourcePage resourceEntiry : allResources) {
            // 按照资源查询和资源相关的权限点
            Collection<Long> authEntities = enterpriseSecurityService.getAuthorityIdAndParentByPageId(resourceEntiry.getId());
            // 把此关系保存到requestMap里
            // 获取资源
            RequestMatcher matcher = new AntPathRequestMatcher(resourceEntiry.getUrl());
            // 循环权限 定义一个权限的集合,和此资源对应起来,添加到HashMap里
            Set<ConfigAttribute> array = new HashSet<>();
            for (Long auth : authEntities) {
                // 转化权限对象为SecurityConfig
                SecurityConfig securityConfig = new SecurityConfig(
                        auth.toString());
                array.add(securityConfig);
            }
            requestMap.put(matcher, array);
        }

        EnterpriseSecurityMetadataSource.requestMap = requestMap;
        EnterpriseSecurityMetadataSource.allAttribute = allAttribute;
    }
}
