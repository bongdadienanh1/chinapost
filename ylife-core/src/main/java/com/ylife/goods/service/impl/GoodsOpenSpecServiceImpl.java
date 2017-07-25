/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import com.ylife.goods.mapper.GoodsOpenSpecMapper;
import com.ylife.goods.mapper.GoodsSpecDetailMapper;
import com.ylife.goods.model.GoodsOpenSpec;
import com.ylife.goods.model.GoodsOpenSpecValueVo;
import com.ylife.goods.model.GoodsOpenSpecVo;
import com.ylife.goods.model.GoodsSpecDetail;
import com.ylife.goods.service.GoodsOpenSpecService;
import com.ylife.goods.service.GoodsOpenSpecValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品开启规格Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午3:04:14
 * @version 1.0
 */
@Service
public class GoodsOpenSpecServiceImpl implements GoodsOpenSpecService {
    // 商品开启规格Mapper
    @Resource
    private GoodsOpenSpecMapper goodsOpenSpecMapper;
    @Resource
    private GoodsOpenSpecValueService goodsOpenSpecValueService;

    @Resource
    private GoodsSpecDetailMapper detailMapper;

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsIdInBoss(Long goodsId,ArrayList<String> advMappings) {
        Map<String , Object> paramMap = new HashMap<>();
        paramMap.put("AdvMappings",advMappings);
        paramMap.put("goodsId",goodsId);
        // 根据商品ID查询开启的规格集合
        List<GoodsOpenSpecVo> openList = this.goodsOpenSpecMapper.queryOpenSpecListByGoodsId(paramMap);
        try {
            for (int i = 0; i < openList.size(); i++) {
                List<GoodsOpenSpecValueVo> goodsOpenSpecValueVos = this.goodsOpenSpecValueService.queryOpenListByGoodsAndSpecId(goodsId, openList.get(i).getSpecId());
                List<GoodsSpecDetail> goodsSpecDetails = null;
                // 查询所有规格值
                if (goodsOpenSpecValueVos != null && goodsOpenSpecValueVos.get(0) != null) {
                    goodsSpecDetails = detailMapper.selectSpecDeetailBySpecId(goodsOpenSpecValueVos.get(0).getSpecId());
                }
                // 排除重复的规格值
                /*
                 * for (GoodsSpecDetail specDetail:goodsSpecDetails) { for (int
                 * j2 = 0; j2 < goodsOpenSpecValueVos.size(); j2++) {
                 * if(specDetail
                 * .getSpecDetailId().equals(goodsOpenSpecValueVos.get
                 * (j2).getSpecValueId())){ goodsSpecDetails.remove(specDetail);
                 * continue; } } }
                 */

                // 清空已有规格值信息
                goodsOpenSpecValueVos = new ArrayList<>();
                // 添加规格值
                outer: for (int j = 0; j < goodsSpecDetails.size(); j++) {
                    /*for (int j2 = 0; j2 < goodsOpenSpecValueVos.size(); j2++) {
                        if (goodsSpecDetails.get(j).getSpecDetailId().equals(goodsOpenSpecValueVos.get(j2).getSpecValueId())) {
                            System.out.println("重复");
                            goodsOpenSpecValueVos.get(j).
                            continue outer;
                            // continue;
                        }
                    }*/
                    GoodsSpecDetail g = goodsSpecDetails.get(j);
                    // 实例化一个商品规格vo对象
                    GoodsOpenSpecValueVo vlVo = new GoodsOpenSpecValueVo();
                    // 对参数进行赋值
                    vlVo.setDelFlag("0");
                    vlVo.setSpecValueRemark(g.getSpecDetailName());
                    vlVo.setSpecId(g.getSpecId());
                    vlVo.setSpecValueId(g.getSpecDetailId());
                    vlVo.setSpecDetail(g);
                    // 添加
                    goodsOpenSpecValueVos.add(vlVo);
                }
                openList.get(i).setSpecValList(goodsOpenSpecValueVos);
            }
            return openList;
        } finally {
            openList = null;
        }
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsIdInBoss(Long goodsId) {
        return queryOpenListByGoodsIdInBoss(goodsId,null);
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsIdInBossSpec(Long goodsId) {
        ArrayList<String> advMappings = new ArrayList<>();
        advMappings.add("com.ysh.goods.dao.GoodsSpecMapper.selectByPrimaryKey");
        return queryOpenListByGoodsIdInBoss(goodsId,advMappings);
    }

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    @Override
    public void deleteByGoodsId(Long goodsId) {
        goodsOpenSpecMapper.deleteByGoodsId(goodsId);
    }

    /**
     * 根据商品ID查询开启的规格集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenListByGoodsId(Long goodsId) {
        List<GoodsOpenSpecVo> openList = this.goodsOpenSpecMapper.queryOpenSpecListByGoodsId(goodsId);
        try {
            for (int i = 0; i < openList.size(); i++) {
                openList.get(i).setSpecValList(this.goodsOpenSpecValueService.queryOpenListByGoodsAndSpecId(goodsId, openList.get(i).getSpecId()));
            }
            return openList;
        } finally {
            openList = null;
        }
    }

    /**
     * 保存商品开启规格信息
     *
     * @param goodsId 商品ID {@link Long}
     * @param specId  规格ID {@link Long}
     * @return 插入的行数{@link　Integer}
     */
    @Override
    public int saveOpenSpec(Long goodsId, Long specId) {
        // 实例化一个商品规格对象
        GoodsOpenSpec openSpec = new GoodsOpenSpec();
        try {
            // 进行赋值
            openSpec.setGoodsId(goodsId);
            openSpec.setSpecId(specId);
            openSpec.setDelFlag("0");
            // 保存商品开启规格信息
            return this.goodsOpenSpecMapper.insertSelective(openSpec);
        } finally {
            openSpec = null;
        }
    }
}
