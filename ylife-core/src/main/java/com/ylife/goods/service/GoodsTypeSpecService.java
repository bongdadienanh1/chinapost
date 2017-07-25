/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.goods.model.GoodsTypeSpecVo;

import java.util.List;

/**
 * 商品类型关联规格表Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午4:41:41
 * @version 1.0
 */
public interface GoodsTypeSpecService {

    /**
     * 根据类型ID查询商品类型关联的规格VO
     * 
     * @param typeId
     * @return
     */
    List<GoodsTypeSpecVo> queryTypeSpecByTypeId(Long typeId);
}
