/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.order.mapper;

import com.ylife.order.model.Marketing;

import java.util.List;
import java.util.Map;

/**
 * @author ggn 2014-03-24 促销信息接口
 */
public interface MarketingMapper {
    /**
     * 根据商品和活动分组id查询优惠id
     * 
     * @return 优惠id
     * @author NINGPAI-LIH
     */
    List<Marketing> queryByCreatimemarketings(Map<String, Object> map);

    /**
     * 查询某个商品下的订单促销
     *
     * @return 查询到的促销
     * @author NINGPAI-LIH
     */
    List<Marketing> queryOrderMarketingByGoodsId(Map<String, Object> map);

    /**
     * 根据商品id和促销类型查询促销信息
     *
     * @param
     * @return
     */
    Marketing queryMarketingByGoodIdAndtype(Map<String, Object> map);

    /**
     * 查询促销详细信息
     *
     * @param marketingId
     * @{link java.lang.Long}
     * @return Marketing
     */
    Marketing marketingDetail(Long marketingId);
}
