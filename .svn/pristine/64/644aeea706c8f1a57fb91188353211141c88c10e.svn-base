/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ylife.goods.mapper.GoodsTypeSpecMapper;
import com.ylife.goods.model.GoodsTypeSpecVo;
import com.ylife.goods.service.GoodsSpecService;
import com.ylife.goods.service.GoodsTypeSpecService;
import org.springframework.stereotype.Service;

/**
 * 商品类型关联商品规格Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午9:20:16
 * @version 1.0
 */
@Service("GoodsTypeSpecService")
public class GoodsTypeSpecServiceImpl implements GoodsTypeSpecService {
    @Resource
    private GoodsTypeSpecMapper goodsTypeSpecMapper;
    @Resource
    private GoodsSpecService goodsSpecService;

    /**
     * 根据类型ID查询商品类型关联的规格VO
     *
     * @param typeId
     * @return
     */
    public List<GoodsTypeSpecVo> queryTypeSpecByTypeId(Long typeId) {
        //执行查询方法
        return this.goodsTypeSpecMapper.queryTypeSpecBytypeId(typeId);
    }

}
