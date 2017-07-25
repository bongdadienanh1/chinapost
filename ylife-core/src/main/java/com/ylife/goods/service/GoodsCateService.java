/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.goods.model.GoodsBreadCrumbVo;
import com.ylife.goods.model.GoodsCate;
import com.ylife.goods.model.GoodsCateVo;

import java.util.List;
import java.util.Map;

/**
 * 商品分类serivce接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:52:43
 * @version 1.0
 */
public interface GoodsCateService {
    /**
     * 查询所有的商品分类
     *
     * @return 排序好的商品分类
     */
    GoodsCateVo queryAllCate();

    /**
     * 根据分类ID查询分类信息,并且计算好所有的子级关系
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     */
    GoodsCateVo queryCateById(Long catId);

    /**
     * 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
     *
     * @param catId
     *            类型ID
     * @return 查询到的Vo信息
     */
    GoodsCateVo queryCateByCatId(Long catId);

    /**
     * 计算出分类下所有子分类的ID
     *
     * @param cateVo
     *            分类信息
     * @param catIds
     *            子分类的集合
     * @return 计算出的子分类的集合
     */
    Long calcAllSonCatIds(GoodsCateVo cateVo, List<Long> catIds);

    /**
     * 根据分类ID查询下一级的子分类Id集合
     *
     * @param catId
     *            分类ID
     * @return 查询到的集合
     */
    List<GoodsCate> queryCatIdsByCatId(Long catId);

    /**
     * 根据分类ID查询分类和父分类信息
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类Vo
     */
    GoodsCateVo queryCateAndParCateByCatId(Long catId);

    /**
     * 根据分类ID查询面包屑辅助Bean
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的面包屑辅助Bean {@link GoodsBreadCrumbVo}
     */
    GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId);

    /**
     * 根据分类ID计算列表页的URL
     *
     * @param catId
     *            分类ID
     * @return 计算好的URL
     */
    String calcCatUrl(Long catId, String level);

    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId
     *            父分类ID {@link Long}
     * @param cateName
     *            父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    List<GoodsCateVo> querySonCateVoByParentIdAndName(Long parentId,
                                                      String cateName);


    /**
     * 2017/07/03
     * 查询商品分类列表
     */
    List<GoodsCateVo> queryAllCateByParentIdAndLikeName(GoodsCate goodsCate);

    /**
     * 添加商品分类
     * @param goodsCate
     * @return
     */
    int insertSelective(GoodsCate goodsCate);

    /**
     * 编辑商品分类
     * @param goodsCate
     * @return
     */
    int updateByPrimaryKeySelective(GoodsCate goodsCate);

    /**
     * 检查商品分类名是否存在
     * @param catName
     * @return
     */
    int checkGoodsCateName(String catName);

    /**
     * 按主键查询
     * @param catId
     * @return
     */
    GoodsCate queryById(Long catId);

    /**
     * 检查是否存在子类
     * @param catId
     * @return
     */
    int selectSonCountByParentId(Long  catId);

}
