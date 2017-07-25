/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.service;


import com.ylife.main.model.MobSiteBasic;

import javax.servlet.http.HttpServletRequest;

/**
 * SERVICE-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:40:24
 */
public interface MobSiteBasicService {

    /**
     * 添加站点信息
     * 
     * @param record
     * @return
     */
    int saveMobSiteBasic(MobSiteBasic record);

    /**
     * 修改站点信息
     * 
     * @param record
     * @return
     */
    int updateMobSiteBasic(MobSiteBasic record);

    /**
     * 查询当前站点信息
     * 
     * @return
     */
    MobSiteBasic selectCurrMobSiteBasic(HttpServletRequest request);
}
