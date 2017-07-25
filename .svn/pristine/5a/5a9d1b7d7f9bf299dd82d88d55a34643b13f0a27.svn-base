package com.ylife.creditOrder.service;

import com.ylife.order.service.CreditOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/5/12.
 */

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCreditOrderService {

    @Resource
    private CreditOrderService creditOrderService;

    @Test
    public void testcreateCreditOrder() {
        //TODO creditOrderService.createCreditOrder(1605130234331000l, CreditOrderReason.CREDIT_ORDER_REASON2, null, "测试");
    }


    @Test
    public void testreviewCreditOrder() {
        creditOrderService.reviewCreditOrder(1605130458333001L, true, "", "同意退货");
    }

    @Test
    public void testeditDeliveryNo() {
        creditOrderService.editDeliveryNo(1605130458333001L, "退货流程测试!!", "测试的快递,名字要长!!");
    }

    @Test
    public void testreceiveCreditOrder() {
        creditOrderService.receiveCreditOrder(1605130458333001L, true, "收货成功", "货已收到，等待退款");
    }


    @Test
    public void testRefund() {
        BigDecimal de = new BigDecimal(99);
        creditOrderService.refund(1605130458333001L, de, "", "测试退款",de);
    }
}