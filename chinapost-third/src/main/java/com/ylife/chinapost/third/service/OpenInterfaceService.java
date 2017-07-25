package com.ylife.chinapost.third.service;


import com.ylife.common.model.ApiResult;

import java.util.Map;

/**
 * OpenInterfaceService.
 *
 * @Author henry .
 * @Create 2017-07-19 16:58.
 */
public interface OpenInterfaceService {
    /**
     * 1.7	商户订单状态通知接口
     * @param paramMap
     * @return
     */
    ApiResult deliveryOrderStatus(Map<String, Object> paramMap);
}
