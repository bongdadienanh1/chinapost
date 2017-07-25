package com.ylife.order.service;

import com.ylife.chinapost.service.OrderManageService;
import com.ylife.customer.service.CustomerService;
import com.ylife.data.page.PageBean;
import com.ylife.order.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOrderService {

    @Resource
    private OrderService orderService;

    @Resource
    private CustomerService customerService;

    @Resource
    private OrderManageService orderManageService;

   /* @Test
    public void testNewSubmitOrder() {
        List<Order> result = orderService.submitOrder(4390L, 307331L, 4493L, 1, true, 12L, null,null,null);
        System.out.println(result);
    }*/

    @Test
    public void testopensubmitOrder() throws Exception {
        //orderService.syncSubmitOrder(201604290917481L);
    }

    @Test
    public void testUpdateOrderStatus() throws IOException {
        orderService.updateOrderStatus();
    }

    @Test
    public void testConfirmOrder() throws IOException {
        //orderService.syncConfirmOrder("2016042315141438");
    }

    @Test
    public void testQueryAllMyOrders() {
        Map<String, Object> paramMap = new HashMap<>();
        PageBean pb = new PageBean();
        paramMap.put("customerId", 227151);
        PageBean pageBean = customerService.queryAllMyOrders(paramMap, pb);
        System.out.print(pageBean);
    }

    @Test
    public void testQueryAllBackMyOrders() {
        Map<String, Object> paramMap = new HashMap<>();
        PageBean pb = new PageBean();
        paramMap.put("customerId", 227151);
        PageBean pageBean = customerService.queryAllBackMyOrders(paramMap, pb);
        System.out.print(pageBean);
    }

    @Test
    public void testCancleOrdersTring() {
        orderManageService.cancelOrderTiming();
    }
}
