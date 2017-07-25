package com.ylife.chinapost.boss.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.Order;

import javax.servlet.http.HttpServletResponse;

/**
 * OrderBossService.
 *
 * @Author henry .
 * @Create 2017-07-02 16:13.
 */
public interface OrderBossService {
    /**
     * 查询全部Order带分页
     * @author henry
     * @date 2017-07-02 16:14:43
     * @param order
     * @param pb
     * @return String
     */
    Page<Order> queryListForPageOrder(Order order, Pageable pb,String createStart,String createEnd,
                                      String payStart,String payEnd);

    /**
     * 按主键查询Order
     * @author henry
     * @date 2017-07-02 16:14:43
     * @param orderId
     * @return  Order
     */
    Order queryById(Long orderId);

    /**
     * 导出订单
     * @param order
     * @param pb
     * @param createStart
     * @param createEnd
     * @param payStart
     * @param payEnd
     * @param response
     */
    void exportOrderExcel(Order order, Pageable pb, String createStart, String createEnd,
                          String payStart, String payEnd, HttpServletResponse response);
}
