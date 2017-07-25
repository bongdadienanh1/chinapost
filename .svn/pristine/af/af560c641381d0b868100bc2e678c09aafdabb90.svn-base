/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.goods.mapper.GoodsTypeParamMapper;
import com.ylife.goods.model.GoodsTypeParam;
import com.ylife.goods.service.GoodsTypeParamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类型详细参数Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午10:03:02
 * @version
 */
@Service
public class GoodsTypeParamServiceImpl implements GoodsTypeParamService {
    // 商品类型详细参数 Dao
    @Resource
    private GoodsTypeParamMapper goodsTypeParamMapper;

    /**
     * 根据主键ID查询商品详细参数
     *
     * @param paramId
     *            详细参数的主键ID {@link java.lang.Long}
     * @return 详细参数实体 {@link com.ysh.goods.bean.GoodsTypeParam}
     */
    public GoodsTypeParam queryByPrimaryKey(Long paramId) {
        // 根据主键ID查询
        return this.goodsTypeParamMapper.selectByPrimaryKey(paramId);
    }

    /**
     * 根据类型ID查询详细参数的列表
     *
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的详细参数的列表 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsTypeParam}
     */
    public List<GoodsTypeParam> queryParamListByTypeId(Long typeId) {
        //根据类型ID查询详细参数列表
        return this.goodsTypeParamMapper.queryTypeParamByTypeId(typeId);
    }
}
