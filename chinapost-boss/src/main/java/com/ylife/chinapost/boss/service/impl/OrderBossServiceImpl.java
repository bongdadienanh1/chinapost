package com.ylife.chinapost.boss.service.impl;

import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.OrderBossService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.Order;
import com.ylife.order.service.OrderService;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * OrderBossServiceImpl.
 *
 * @Author henry .
 * @Create 2017-07-02 16:14.
 */
@Service(value="orderBossService")
public class OrderBossServiceImpl implements OrderBossService{

    @Resource
    private OrderService orderService;

    /**
     *
     * @author  henry
     * @date 2017-07-02 16:14:43
     * @see OrderBossService#queryListForPageOrder(Order order, Pageable pb,String createStart,String createEnd,
            String payStart,String payEnd)
     * @param order
     * @param pb
     * @return Page
     */
    public Page<Order> queryListForPageOrder(Order order, Pageable pb,String createStart,String createEnd,
                                             String payStart,String payEnd) {
        // 设置查询标识
        order.setGoodsInfoType("1");
        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart,  Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart,  Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd,  Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        return orderService.getOrder(order,null,null,null,createStartTime,createEndTime,payStartTime,payEndTime,pb);
    }

    /**
     *
     * @author  henry
     * @date 2017-07-02 16:14:43
     * @see OrderBossService#queryById(Long orderId)
     * @param orderId
     * @return
     */
    public Order queryById(Long orderId) {
        return orderService.getOrderById(orderId);
    }

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
    public void exportOrderExcel(Order order, Pageable pb, String createStart, String createEnd, String payStart, String payEnd, HttpServletResponse response) {
        // 设置查询标识
        order.setGoodsInfoType("1");
        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, "yyyy-MM-dd HH:mm:ss");
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd,"yyyy-MM-dd HH:mm:ss");
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, "yyyy-MM-dd HH:mm:ss");
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, "yyyy-MM-dd HH:mm:ss");
        }
        try{
            orderService.exportOrderExcel(order,null,null,null,createStartTime,createEndTime,payStartTime,payEndTime,pb,response);
        } catch (Exception e){
            System.out.println("excel导出异常"+e.getMessage());
        }

    }
}