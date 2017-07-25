package com.ylife.authority.service;

import com.ylife.authority.model.Authority;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/6.
 * <p/>
 * 用户权限服务。
 */
public interface AuthorityService {

    /**
     * 添加权限/角色
     *
     * @param enterpriseId  企业Id
     * @param managerId     管理员Id
     * @param authorityName 权限名称
     * @param pageIds       权限拥有的页面ID集合
     */
    void addAuthority(long enterpriseId, long managerId, String authorityName, long[] pageIds, String remark);

    /**
     * 编辑权限/角色
     *
     * @param enterpriseId
     * @param managerId
     * @param authorityName
     * @param pageIds
     * @param remark
     */
    void editAuthority(long enterpriseId, long managerId, long authorityId, String authorityName, long[] pageIds, String remark);

    /**
     * 删除权限。
     *
     * @param authorityId 权限ID
     */
    void deleteAuthority(long authorityId);

    /**
     * 给此权限分配可操作的页面。
     *
     * @param authorityId 权限ID
     * @param pageIds     页面ID的List
     */
    void setAuthorityPages(long authorityId, long[] pageIds);

    /**
     * 给权限添加可分配页面。
     *
     * @param authorityId 权限ID
     * @param pageId      页面ID
     */
    void addAuthorityPage(long authorityId, long pageId);

    /**
     * 删除此权限的可分配页面。
     *
     * @param authorityId 权限ID
     * @param pageId      页面ID
     */
    void deleteAuthorityPage(long authorityId, long pageId);

    /**
     * 获取此企业的所有权限列表。
     *
     * @param enterpriseId 企业ID
     */
    List<Authority> getEnterpriseAuthorities(long enterpriseId);

    /**
     * 获取用户所拥有的权限。
     *
     * @param managerId 管理人员ID
     */
    Authority getManagerAuthority(long managerId);


    /**
     * 设置管理人员的权限。
     *
     * @param managerId   管理人员ID
     * @param authorityId 权限ID
     */
    void setManagerAuthority(long managerId, long authorityId);

    /**
     * 删除管理人员的权限。
     *
     * @param managerId   管理人员ID
     * @param authorityId 权限ID
     */
    void deleteManagerAuthority(long managerId, long authorityId);

    /**
     * 获取系统ADMIN权限。
     */
    Authority getAdmin();

    /**
     * 获取系统次级ADMIN权限。（网点权限）
     */
    Authority getSecondAdmin();

    /**
     * 中间级权限
     * @return
     */
    Authority getNotAdmin();
}
