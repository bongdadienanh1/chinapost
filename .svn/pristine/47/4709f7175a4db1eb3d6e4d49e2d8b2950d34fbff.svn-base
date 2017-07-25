package com.ylife.chinapost.security.service;

import com.ylife.authority.model.Authority;
import com.ylife.authority.model.ResourcePage;
import com.ylife.enterprise.model.EnterpriseManager;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/9.
 * 此服务提供给Spring security使用。
 */
public interface EnterpriseSecurityService {

    /**
     * 根据登录名获取EnterpriseManager实体。
     */
    EnterpriseManager findByLoginName(String username);

    /**
     * 根据管理员ID获取权限ID
     */
    Long getAuthorityId(Long managerId);

    /**
     * 检验权限和资源关系是否存在。
     */
    boolean check(long authorityId, long resourceId);

    /**
     * 获取所有资源
     */
    List<ResourcePage> getAllResourcePage();

    /**
     * 获取所有权限
     */
    List<Authority> getAllAuthority();

    /**
     * 根据资源获取权限
     *
     * @param pageId 资源ID
     */
    List<Long> getAuthorityIdByPageId(Long pageId);


    /**
     * 根据资源获取权限及上级权限
     *
     * @param pageId 资源ID
     */
    List<Long> getAuthorityIdAndParentByPageId(Long pageId);

    /**
     * 根据url根据上级的Url
     *
     * @param url url
     */
    ResourcePage getParentUrlByUrl(String url);


}
