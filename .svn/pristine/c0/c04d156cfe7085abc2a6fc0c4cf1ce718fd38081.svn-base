package com.ylife.order.service;

import com.ylife.order.model.BackOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBackOrderService {
    @Resource
    private BackOrderService backOrderService;
    @Resource
    private CreditOrderService creditOrderService;

    @Test
    public void testInsertBackOrder() throws UnsupportedEncodingException {
        BackOrder backOrder = new BackOrder();
        backOrder.setBackOrderCode("201605041001");
        backOrder.setOrderCode("201604290917481");
        backOrder.setBackTime(new Date());
        backOrder.setBackReason("刚才误操作..............");
        backOrder.setBackPrice(BigDecimal.ONE);
        backOrder.setBackCheck("0");
        backOrder.setBusinessId(0L);
        backOrder.setIsBack("2");
        backOrder.setBackReason("2");
        backOrder.setBackWay("2");
        backOrder.setCustomerId(4390L);
        int result = backOrderService.insertBackOrderInfo(backOrder);
        System.out.println(result);
    }

    @Test
    public void testSubmitBackOrder() throws Exception {
        backOrderService.opensubmitBackOrder("2016042909335947");
    }

    @Test
    public void testUpdateOrderStatus() throws IOException {
        creditOrderService.updateBackOrderStatus();
    }


}
