/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.service;


import com.ylife.data.page.PageBean;
import com.ylife.main.model.MobPageTag;

import java.util.List;

/**
 * SERVICE-移动版页面标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午7:19:25
 */
public interface MobPageTagService {
    /**
     * 删除页面标签
     * 
     * @param mobPageTagId
     * @return
     */
    int deleteMobPageTag(Long mobPageTagId, Long userId);

    /**
     * 添加页面标签
     * 
     * @param mobPageTag
     * @return
     */
    int createMobPageTag(MobPageTag mobPageTag);

    /**
     * 修改页面标签
     * 
     * @param mobPageTag
     * @return
     */
    int updateMobPageTag(MobPageTag mobPageTag);

    /**
     * 查看页面标签
     * 
     * @param mobPageTagId
     * @return
     */
    MobPageTag getMobPageTag(Long mobPageTagId);

    /**
     * 按排序获取页面标签
     *
     * @param sort
     * @return
     */
    MobPageTag getPageTagBySort(Integer sort);


    /**
     * 按排序获取页面标签,包含单身信息
     *
     * @param sort
     * @return
     */
    MobPageTag getPageTagAllBySort(Integer sort);

    /**
     * 分页查询页面标签
     * 
     * @param pb
     * @return
     */
    PageBean selectMobPageTagByPb(PageBean pb);

    /**
     * 查询所有页面标签
     * 
     * @return
     */
    List<MobPageTag> selectMobPageTagForSite();

    /**
     * 修改页面标签启用状态
     * 
     * @param mobPageTagId
     * @return
     */
    int changeMobPageTagUserdStatus(Long mobPageTagId);


}
