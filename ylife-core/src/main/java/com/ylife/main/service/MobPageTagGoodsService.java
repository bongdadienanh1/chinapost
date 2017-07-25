/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.service;


import com.ylife.data.page.PageBean;
import com.ylife.main.model.GoodsSiteSearchBean;
import com.ylife.main.model.MobPageTagGoods;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * SERVICE-移动页面标签商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:31:22
 */
public interface MobPageTagGoodsService {
    /**
     * 根据主键删除
     * 
     * @param MobPageTagGoodsproductId
     * @return
     */
    int deleteMobPageTagGoods(Long MobPageTagGoodsproductId, Long userId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveMobPageTagGoods(MobPageTagGoods record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateMobPageTagGoods(MobPageTagGoods record);

    /**
     * 根据主键查询
     * 
     * @param MobPageTagGoodsproductId
     * @return
     */
    MobPageTagGoods getMobPageTagGoodsById(Long MobPageTagGoodsproductId);

    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品
     * 
     * @param pb
     * @param mobPageTagId
     * @param isHot
     * @return
     */
    PageBean selectMobPageTagGoodsByParam(PageBean pb, Long mobPageTagId, String isHot);

    /**
     * 根据移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     * @param mobPageTagId
     * @param isHot
     * @return
     */
    List<MobPageTagGoods> selectMobPageTagGoodsByParamForSite(Long mobPageTagId, String isHot);
    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     *
     * @param mobPageTagId
     * @param isHot
     * @return
     */
    List<MobPageTagGoods> selectMobPageTagGoodsByParamForChannelSite(HttpServletRequest request, Long mobPageTagId, String isHot, GoodsSiteSearchBean searchBean, PageBean pageBean);
}
