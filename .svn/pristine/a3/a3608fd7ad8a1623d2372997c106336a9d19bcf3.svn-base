package com.ylife.chinapost.service;

import com.ylife.authority.mapper.pojo.AuthWithMAmountResult;
import com.ylife.enterprise.mapper.pojo.ManagerWithAuthNameResult;
import com.ylife.authority.model.ResourcePage;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * 角色管理。
 */
public interface RoleManageService {

    /**
     * 获取当前企业的所有角色/成员数量
     */
    List<AuthWithMAmountResult> selectAuthResultByEnterpriseId();

    /**
     * 添加新角色
     *
     * @param name
     * @param pageIds
     */
    void addNewAuthority(String name, long[] pageIds, String remark);

    /**
     * 编辑角色
     *
     * @param authorityId
     */
    void editAuthority(long authorityId, long[] pageIds, String name, String remark);

    /**
     * 删除角色
     *
     * @param authorityId
     */
    void deleteAuthority(long authorityId);

    /**
     * 获取企业员工(管理员)
     */
    List<ManagerWithAuthNameResult> getMyManagerInfo();

    /**
     * 添加新员工
     *
     * @param username  用户名
     * @param staffname 真实姓名
     * @param cellphone 电话
     */
    void addNewManager(String username, String password, String staffname, String photoImg, String cellphone, long authorityId);

    /**
     * 删除员工
     *
     * @param manageId
     */
    void deleteManager(long manageId);

    /**
     * 修改密码
     */
    void editManagerPassword(long managerId, String password);

    /**
     * 编辑员工状态
     */
    void editManagerBuff(long managerId, String staffname, String cellphone, boolean nonDisabled, long authorityId);

    /**
     * 获取角色的页面
     */
    List<ResourcePage> getAuthorityPages(long authorityId);

    /**
     * 获取顶级页面
     */
    List<ResourcePage> getTopPages();

    /**
     * 获取子页面
     */
    List<ResourcePage> getSonPages(long pageId);

}
