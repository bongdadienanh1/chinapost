/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.order.mapper;

import com.ylife.order.model.OrderLog;

import java.util.List;

/**
 * 订单记录表
 * 
 * @author NINGPAI-LIH
 * @since 2014年8月2日16:23:04
 * 
 */
public interface OrderLogMapper {

    /**
     * 插入订单记录信息
     * 
     * @param record
     *            订单记录信息
     * @return
     */
    int insertSelective(OrderLog record);

    /**
     * 根据订单id查询订单操作记录
     * 
     * @param orderId
     *            订单id
     * @return
     */
    List<OrderLog> selectOrderLogByParam(Long orderId);

    /**
     *
     * @param orderLogId
     * @return
     */
    OrderLog selectByPrimaryKey(Long orderLogId);
}
