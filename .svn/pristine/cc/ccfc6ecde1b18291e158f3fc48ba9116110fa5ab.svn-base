/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import com.ylife.goods.mapper.GoodsCateMapper;
import com.ylife.goods.model.GoodsBreadCrumbVo;
import com.ylife.goods.model.GoodsCate;
import com.ylife.goods.model.GoodsCateVo;
import com.ylife.goods.service.GoodsCateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品分类service实现
 *
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午5:12:33
 */
@Service
public class GoodsCateServiceImpl implements GoodsCateService {
    @Resource
    private GoodsCateMapper goodsCateMapper;

    /**
     * 查询所有的商品分类
     *
     * @return 排序好的商品分类
     */
    public GoodsCateVo queryAllCate() {
        GoodsCateVo topParent = null;
        List<GoodsCateVo> allCate = null;
        try {
            topParent = this.goodsCateMapper.queryTopParent();
            allCate = this.goodsCateMapper.queryAllCate();
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /**
     * 递归判断
     *
     * @param parentId    父分类ID
     * @param allCateList 所有的分类列表
     * @return 排序好的集合
     */
    public List<GoodsCateVo> calcCateVo(Long parentId, List<GoodsCateVo> allCateList) {
        List<GoodsCateVo> cateVoList = new ArrayList<GoodsCateVo>();
        // 需要返回的分类实体
        GoodsCateVo cateVo = null;
        // 返回的分类实体的所有子分类
        List<GoodsCateVo> allSonCate = null;
        // 循环中的迭代分类
        GoodsCateVo cate = null;
        try {
            // 进行递归
            for (int i = 0; i < allCateList.size(); i++) {
                if (parentId.equals(allCateList.get(i).getCatParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GoodsCateVo();
                    cate = allCateList.get(i);
                    cateVo.setCatId(cate.getCatId());
                    cateVo.setCatName(cate.getCatName());
                    cateVo.setCatParentId(cate.getCatParentId());
                    cateVo.setCatSort(cate.getCatSort());
                    cateVo.setTypeId(cate.getTypeId());
                    cateVo.setCatDelflag(cate.getCatDelflag());
                    cateVo.setCatGrade(cate.getCatGrade());
                    cateVo.setCatSeoDesc(cate.getCatSeoDesc());
                    cateVo.setCatSeoKeyword(cate.getCatSeoKeyword());
                    cateVo.setCatSeoTitle(cate.getCatSeoTitle());
                    // 执行递归
                    allSonCate = calcCateVo(cate.getCatId(), allCateList);
                    cateVo.setCateVos(allSonCate);
                    cateVoList.add(cateVo);
                }
            }
            // 返回计算好的所有的子分类列表
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            cateVo = null;
            allSonCate = null;
            cate = null;
        }
    }

    /**
     * 根据类型ID查询VO信息,仅是查询当前分类本身以及父分类
     *
     * @param catId 类型ID
     * @return 查询到的Vo信息
     */
    public GoodsCateVo queryCateByCatId(Long catId) {
        GoodsCateVo topParent = null;
        try {
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            topParent.setParentCat(this.goodsCateMapper.queryCateByPrimaryKey(topParent.getCatParentId()));
            return topParent;
        } finally {
            topParent = null;
        }
    }

    /**
     * 根据分类ID查询分类信息,并且计算好所有的子级关系
     *
     * @param catId 分类ID
     * @return 查询到的分类信息
     */
    public GoodsCateVo queryCateById(Long catId) {
        GoodsCateVo topParent = null;
        List<GoodsCateVo> allCate = null;
        try {
            topParent = this.goodsCateMapper.queryCateVoByCatId(catId);
            allCate = this.goodsCateMapper.queryAllCate();
            topParent.setCateVos(this.calcCateVo(topParent.getCatId(), allCate));
            return topParent;
        } finally {
            topParent = null;
            allCate = null;
        }
    }

    /**
     * 计算出分类下所有子分类的ID
     *
     * @param cateVo 分类信息
     * @param catIds 子分类的集合
     * @return 计算出的子分类的集合
     */
    public Long calcAllSonCatIds(GoodsCateVo cateVo, List<Long> catIds) {
        catIds.add(cateVo.getCatId());
        Long catId;
        try {
            if (null != cateVo.getCateVos() && !cateVo.getCateVos().isEmpty()) {
                for (int i = 0; i < cateVo.getCateVos().size(); i++) {
                    catId = this.calcAllSonCatIds(cateVo.getCateVos().get(i), catIds);
                    if (null != catId) {
                        catIds.add(catId);
                    }
                }
            }
            return null;
        } finally {
            catId = null;
        }
    }

    /**
     * 根据分类ID查询下一级的子分类Id集合
     *
     * @param catId 分类ID
     * @return 查询到的集合
     */
    public List<GoodsCate> queryCatIdsByCatId(Long catId) {
        List<GoodsCate> list = this.goodsCateMapper.querySonCateByCatId(catId);
        try {
            list.add(0, this.goodsCateMapper.queryCateByPrimaryKey(catId));
            return list;
        } finally {
            list = null;
        }
    }

    /**
     * 根据分类ID查询分类和父分类信息
     *
     * @param catId 分类ID
     * @return 查询到的分类Vo
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        return this.goodsCateMapper.queryCateAndParCateByCatId(catId);
    }

    /**
     * 根据分类ID查询面包屑辅助Bean
     *
     * @param catId 分类ID {@link Long}
     * @return 查询到的面包屑辅助Bean {@link GoodsBreadCrumbVo}
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        return this.goodsCateMapper.queryBreadCrubByCatId(catId);
    }

    /**
     * 根据分类ID计算列表页的URL
     *
     * @param catId 分类ID
     * @return 计算好的URL
     */
    public String calcCatUrl(Long catId, String level) {
        String url = "";
        if ("1".equals(level)) {
            url = this.goodsCateMapper.queryFirstCatIdByFirstCatId(catId) + "-" + catId.toString();
        } else if ("2".equals(level)) {
            url = this.goodsCateMapper.queryFirstSonCatIdBySecondCatId(catId) + "-" + this.goodsCateMapper.queryParentIdBySecondCatId(catId).toString();
        }
        return url;
    }


    /**
     * 根据父分类ID 查询子分类列表
     *
     * @param parentId 父分类ID {@link Long}
     * @param cateName 父类名称
     * @return 查询到的分类列表 {@link Long}
     */
    @Override
    public List<GoodsCateVo> querySonCateVoByParentIdAndName(Long parentId, String cateName) {
        // 定义一个HashMap
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cateId", parentId);
        paramMap.put("cateName", cateName);
        // 根据父分类ID 查询子分类列表
        return this.goodsCateMapper.querySonCatVoByParm(paramMap);
    }

    /**2017/07/06 添加注释
     * 根据名称和catId查询
     * @param goodsCate
     * @return
     */

    @Override
    public List<GoodsCateVo> queryAllCateByParentIdAndLikeName(GoodsCate goodsCate) {
        // 根据父分类ID 查询子分类列表
        return goodsCateMapper.queryAllCateByParentIdAndLikeName(goodsCate);
    }

    /**
     * 添加
     * @param goodsCate
     * @return
     */
    @Override
    public int insertSelective(GoodsCate goodsCate) {
        return goodsCateMapper.insertSelective(goodsCate);
    }

    /**
     * 增删改山商品分类列表
     * @param goodsCate
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(GoodsCate goodsCate) {
        return goodsCateMapper.updateByPrimaryKeySelective(goodsCate);
    }

    /**
     *
     * @param catName 检查名称是否重复
     * @return
     */
    @Override
    public int checkGoodsCateName(String catName) {
        return goodsCateMapper.checkGoodsCateName(catName);
    }

    /**
     * 按主键查询
     * @param catId
     * @return
     */
    @Override
    public GoodsCate queryById(Long catId) {
        return goodsCateMapper.queryById(catId);
    }

    /**
     * 根据catId查询是否存在子类
     * @param catId
     * @return
     */
    @Override
    public int selectSonCountByParentId(Long catId) {
        return goodsCateMapper.selectSonCountByParentId(catId);
    }


}

