/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.order.service.impl;

import com.ylife.order.mapper.MarketingMapper;
import com.ylife.order.mapper.MarketingScopeMapper;
import com.ylife.order.model.Marketing;
import com.ylife.order.model.MarketingScope;
import com.ylife.order.service.MarketingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author ggn 2014-03-24 促销信息service
 */
@Service("MarketingService")
public class MarketingServiceImpl implements MarketingService {

    // Map中使用的key值
    private static final String BRANDIDP = "brandIdP";
    private static final String GROUPID = "groupId";
    private static final String CATEID = "cateId";
    private static final String GOODSINFOID = "goodsInfoId";

    @Resource
    private MarketingMapper marketingMapper;
    @Resource
    private MarketingScopeMapper marketingScopeMapper;

    /*
     * 
     * 
     * @see
     * com.ysh.marketing.service.MarketingService#queryByCreatimeMarketings
     * (java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public long queryByCreatimeMarketings(Long goodsId, Long groupId, Long cateId, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSINFOID, goodsId);
        map.put(GROUPID, groupId);
        map.put(CATEID, cateId);
        map.put(BRANDIDP, brandId);
        List<Marketing> marketing = marketingMapper.queryByCreatimemarketings(map);
        // 判断该商品是否存在优惠
        if (marketing == null || marketing.isEmpty()) {
            return 0;
        }
        return marketing.get(0).getMarketingId();
    }

    /*
    *
    *
    * @see
    * com.ysh.marketing.service.MarketingService#queryOrderMarketingByGoodsId
    * (java.util.List)
    */
    @Override
    public List<Marketing> queryOrderMarketingByGoodsId(List<Long> goodsId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", goodsId);
        if (thirdId != null) {
            map.put("thirdId", thirdId);
        }
        return marketingMapper.queryOrderMarketingByGoodsId(map);
    }
}
