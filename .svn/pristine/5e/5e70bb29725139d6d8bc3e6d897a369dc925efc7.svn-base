package com.ylife.chinapost.service;

import com.ylife.customer.model.ChinapostCustomer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUcoinGrandService {

    @Resource
    private UcoinGrandService ucoinGrandService;

    @Test
    public void testIsNewCustomer() {
        boolean result = ucoinGrandService.isNewCustomer("3211211989111663191");
        System.out.println(result);
    }

    @Test
    public void testGetCustomer() {
        ChinapostCustomer customer = ucoinGrandService.getCustomer("321121198911166319");
        System.out.println(customer.getFullname());
    }

    public void testParseFile() {
    }

    public void testSingleGrand() {
    }

    public void batchGrand() {
    }

}
