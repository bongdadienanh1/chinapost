package com.ylife.authority.service;

import com.ylife.authority.model.ResourcePage;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/6.
 * <p/>
 * 页面相关服务。
 */
public interface ResourcePageService {

    /**
     * 获得最顶级页面列表。
     */
    List<ResourcePage> getTopPages();

    /**
     * 获取此页面的子页面。
     *
     * @param pageId 页面ID
     */
    List<ResourcePage> getSonPages(long pageId);

    /**
     * 获得此权限可操作的所有页面。
     *
     * @param authorityId 权限ID
     */
    List<ResourcePage> getAuthorityPages(long authorityId);
}
