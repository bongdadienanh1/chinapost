package com.ylife.authority.mapper;

import com.ylife.authority.model.ResourcePage;

import java.util.List;

public interface ResourcePageMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ResourcePage record);

    int insertSelective(ResourcePage record);

    ResourcePage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResourcePage record);

    int updateByPrimaryKey(ResourcePage record);

    /**
     * 获取顶级页面
     * @return
     */
    List<ResourcePage> selectByParentIdisNull();

    /**
     * 获取当前页面的子页面
     * @param parentId
     * @return
     */
    List<ResourcePage> selectByParentId(Long parentId);

    /**
     * 获取角色的所有权限
     * @param authorityId
     * @return
     */
    List<ResourcePage> selectByAuthorityId(Long authorityId);


    /**
     * 获取所有的资源
     * @return
     */
    List<ResourcePage> selectAllResourcePage();

    /**
     * 根据url获取资源信息
     * @return
     * @param url
     */
    ResourcePage selectByUrl(String url);

    /**
     * 根据url根据上级的Url
     * @param url
     * @return
     */
    ResourcePage selectParentUrlByUrl(String url);

    /**
     * 获取角色的所有权限(最上级)
     * @param authorityId
     * @return
     */
    List<ResourcePage> selectTopByAuthorityId(Long authorityId);


    /**
     * 获取角色的所有权限(报表中心)
     * @param authorityId
     * @return
     */
    List<ResourcePage> selectFormByAuthorityId(Long authorityId);
}