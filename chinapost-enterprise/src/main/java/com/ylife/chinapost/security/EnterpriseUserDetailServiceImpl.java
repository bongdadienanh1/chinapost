package com.ylife.chinapost.security;

import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.chinapost.security.service.EnterpriseSecurityService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于验证的组件，用户登录和取得用户权限。
 */
@Transactional
public class EnterpriseUserDetailServiceImpl implements UserDetailsService {

    @Resource
    private EnterpriseSecurityService enterpriseSecurityService;

    /**
     * 加载用户信息，同时登录也调整到此处。
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EnterpriseManager manager;
        manager = enterpriseSecurityService.findByLoginName(username);
        if (manager == null) {
            throw new UsernameNotFoundException("用户名未找到。");
        }
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(manager);
        return new User(manager.getUsername(), manager.getPassword(), true, true, true, manager.getNonDisabled(), grantedAuths);
    }

    /**
     * 获取用户所拥有的权限。
     * 这里仅加入了用户拥有的Authority的ID
     */
    private Set<GrantedAuthority> obtionGrantedAuthorities(EnterpriseManager user) {
        Long authorityId = enterpriseSecurityService.getAuthorityId(user.getId());
        Set<GrantedAuthority> authSet = new HashSet<>();
        if (authorityId != null) {
            authSet.add(new SimpleGrantedAuthority(authorityId + ""));
        }
        return authSet;
    }
}