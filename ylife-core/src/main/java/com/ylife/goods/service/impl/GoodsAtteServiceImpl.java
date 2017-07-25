/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsAtteMapper;
import com.ylife.goods.model.ValueUtil;
import com.ylife.goods.service.GoodsAtteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品关注Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年3月18日 下午4:41:38
 * @version 1.0
 */
@Service("GoodsAtteService")
public class GoodsAtteServiceImpl implements GoodsAtteService {
    @Resource
    private GoodsAtteMapper goodsAtteMapper;
    /**
     * 删除商品关注信息
     *
     * @param atteId
     *            主键ID {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteAtte(Long atteId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("atteId", atteId);
            // 执行删除商品关系信息方法
            return goodsAtteMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            map = null;
        }
    }

    /**
     * 批量删除商品关注信息
     *
     * @param atteIds
     *            {@link java.lang.Long}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDelAtte(Long[] atteIds) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("atteIds", atteIds);
            // 打印日志
            // 执行批量删除商品关注信息方法
            return goodsAtteMapper.batchDelete(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据货品ID和分页参数查询关注信息
     *
     * @param pb
     *            分页辅助Bean {@link com.ysh.util.PageBean}
     * @param productId
     *            {@link java.lang.Long}
     * @return {@link com.ysh.util.PageBean}
     */
    public PageBean querybyProductId(PageBean pb, Long productId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("productId", productId);
            // 根据货品id和分页参数查询关注信息行数
            pb.setRows(this.goodsAtteMapper.queryTotalCountToProduct(map));
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 根据货品id和分页参数查询关注信息集合
            pb.setList(this.goodsAtteMapper.queryByProductId(map));
            // 返回结果
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 查询商品关注列表
     */
    @Override
    public int selectGoodsAtteCount() {
        // 返回结果
        return goodsAtteMapper.queryTotalCount(null);
    }

}
