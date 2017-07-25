/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.PageBean;
import com.ylife.goods.model.GoodsSpec;
import com.ylife.goods.model.GoodsSpecDetail;
import com.ylife.goods.model.GoodsSpecVo;

import java.util.List;

/**
 * 商品规格Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午6:26:38
 * @version 1.0
 */
public interface GoodsSpecService {

    /**
     * 根据主键查询商品规格信息
     * 
     * @param specId
     *            规格ID
     * @return 查询到的商品规格Vo的实体 {@link com.ysh.goods.vo.GoodsSpecVo}
     */
    GoodsSpecVo queryBySpecPrimaryKey(Long specId);

    /**
     * 根据主键查询商品规格信息
     *
     * @param specId
     *            规格ID
     * @return 查询到的商品规格Vo的实体 {@link com.ysh.goods.vo.GoodsSpecVo}
     */
    GoodsSpecVo queryBySpecPrimaryKeySpec(Long specId);

    /**
     * 根据分页辅助Bean查询规格的分页列表
     * 
     * @param pb
     *            分页帮助类 {@link com.ysh.util.PageBean}
     * @return 塞进列表的分页辅助类 {@link com.ysh.util.PageBean}
     */
    PageBean qyerySpecListByPageBean(PageBean pb);

    /**
     * 把传递过来的数组转换为规格值集合
     * 
     * @param specDetailIds
     *            所有的规格值ID数组
     * @param specDetailDelflag
     *            所有的规格值的删除标记
     * @param specDetailName
     *            所有的规格值得名称
     * @param specDetailNickname
     *            所有的规格值得别名
     * @param specDetailImg
     *            所有的规格值的图片
     * @param specDetailSort
     *            所有的规格值的排序
     * @return 整理好的列表 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsSpecDetail}
     */
    List<GoodsSpecDetail> changeSpecDetail(String[] specDetailIds,
            String[] specDetailDelflag, String[] specDetailName,
            String[] specDetailNickname, String[] specDetailImg,
            String[] specDetailSort);

    /**
     * 查询所有的商品规格
     * 
     * @return 查询到的规格列表
     */
    List<GoodsSpec> queryAllSpec();

    /**
     * 查询所有的商品规格包含删除的
     * 
     * @return 查询到的规格列表
     */
    List<GoodsSpec> queryAllSpecIncludeDel();

    /**
     * 根据商品ID查询关联的规格Vo
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ysh.goods.vo.GoodsSpecVo}
     */
    List<GoodsSpecVo> querySpecVoByGoodsId(Long goodsId);

    /**
     * 验证规格名称是否可用
     * 
     * @param specName
     *            待验证的规格名称 {@link java.lang.String}
     * @return 可用返回true 不可用返回false
     */
    boolean checkSpecName(String specName);

    /**
     * 保存规格信息
     * 
     * @param goodsSpec
     *            规格信息
     * @param username
     *            用户名
     */
    void saveGoodsSpec(GoodsSpec goodsSpec, String username);

    /**
     * 修改规格信息
     * 
     * @param goodsSpec
     *            规格信息
     * @param attribute
     *            用户名
     */
    void updateGoodsSpec(GoodsSpec goodsSpec, String username);
}
