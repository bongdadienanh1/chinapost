/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.goods.model.GoodsTypeExpandParamVo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品扩展属性Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午2:45:12
 * @version 1.0
 */
public interface GoodsTypeExpandParamService {


    /**
     * 根据类型ID查询所有的扩展属性
     * 
     * @param typeId
     *            分类ID {@link java.lang.Longs}
     * @return 查询到的扩展属性的集合 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsTypeExpandParam}
     */
    List<GoodsTypeExpandParamVo> queryParamListByTypeId(Long typeId);
}
