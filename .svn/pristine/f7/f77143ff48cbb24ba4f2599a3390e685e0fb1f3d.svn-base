/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.main.service;


import com.ylife.main.model.MobSubject;

import java.util.List;

/**
 * SERVICE-移动版可视化配置首页
 * 
 * @ClassName: MobSubjectService
 * @Description: 移动版可视化配置专题的SERVICE
 * @author Henry
 * @date 2014年10月17日 上午10:00:03
 */
public interface MobSubjectService {
    /**
     * 添加移动版可视化配置首页
     * 
     * @Title: createHomePage
     * @Description: 添加移动版可视化配置首页
     * @param homePage
     */
    void createHomePage(MobSubject homePage);

    /**
     * 修改移动版可视化配置首页
     * 
     * @Title: updateHomePage
     * @Description: 修改移动版可视化配置首页
     * @param homePage
     */
    void updateSubject(MobSubject homePage);

    /**
     * 根据商家ID查询
     * 
     * @Title: selectHomePageByMerchantId
     * @Description: 根据商家ID查询
     * @param merchantId
     * @return
     */
    MobSubject selectHomePageByMerchantId(Long merchantId);

    /**
     * 初始化商家的HomePage
     * 
     * @Title: initHomePage
     * @Description: 初始化商家的HomePage
     * @param merchantId
     */
    MobSubject initHomePage(MobSubject mobSubject);

    /**
     * 生成HTML
     * 
     * @Title: makeHtml
     * @Description: 生成HTML
     */
    void makeHtml();

    
    /**
     * 根据商家ID查询该商家的所有专题
     * 
     * @Title: selectAllSubjectUnstatusByMerchantId
     * @Description: 根据商家ID查询该商家的所有专题
     * @param merchantId
     * @return
     */
    List<MobSubject> selectAllSubjectUnstatusByMerchantId(Long merchantId);

    /**
     * 根据ID获取模板信息
     * 
     * @Title: getHomePageById
     * @Description: 根据ID获取模板信息
     * @param homepageId
     * @return
     */
    MobSubject getSubjectById(Long homepageId);

    /**
     * 根据ID删除模板信息
     * 
     * @Title: deleteHomePageById
     * @Description: 根据ID删除模板信息
     * @param homepageId
     */
    void deleteHomePageById(Long subjectId);

    /**
     * 启用模板
     * 
     * @Title: openHomePageByHIdAndMId
     * @Description: 根据商家ID和模板ID找到模板，修改启用状态，并把该商家下的其他模板禁用，启用状态字段使用temp2：0不启用 1启用
     * @param homepageId
     *            模板ID
     * @param merchantId
     *            商家ID
     */
    void openHomePageByHIdAndMId(Long homepageId, Long merchantId);

    
    /**
     * 根据商家ID获取该商家当前使用的专题信息
     * 
     * @Title: getCurrSubjectByMerchantId
     * @Description: 根据商家ID获取该商家当前使用的专题信息
     * @param merchantId
     * @return
     */
    MobSubject getCurrSubjectByMerchantId(Long merchantId);
    
    /**
     * 根据商家ID修改该商家当前使用的专题信息
     * 
     * @Title: updateSubjectById
     * @Description: 根据商家ID修改该商家当前使用的专题信息
     * @param subjectId
     * @return
     */
    void updateSubjectById(Long subjectId, String title);
}
