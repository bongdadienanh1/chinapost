/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.order.service;

import com.ylife.order.model.Marketing;

import java.util.List;

/**
 * 促销serivce
 * 
 * @author NINGPAI-LIH
 * @since 2014年3月21日下午 17:18:00
 * 
 */
public interface MarketingService {

    /**
     * 查询促销加入的促销id
     *
     * @param goodsId
     *            商品主键
     * @param groupId
     *            分组主键
     * @param cateId
     *            分组id
     * @param brandId
     *            品牌id
     * @return 促销id
     * @author NINGPAI-LIH
     */
    long queryByCreatimeMarketings(Long goodsId, Long groupId, Long cateId,
                                   Long brandId);

    /**
     * 根据商品id查询商品参加的订单促销
     *
     * @param goodsId
     *            商品id
     * @return 订单促销
     */
    List<Marketing> queryOrderMarketingByGoodsId(List<Long> goodsId,
                                                 Long thirdId);

}
