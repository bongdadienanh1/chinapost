package com.ylife.customer.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/4/11.
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCustomerWithUcoinInfoService {
    @Resource
    private CustomerWithUcoinInfoService customerWithUcoinInfoService;

    @Test
    public void getInfos() {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setIdcardNo("1234");
        Page<ChinapostCustomer> page = customerWithUcoinInfoService.getInfos(model, null, 1L, null, null, new Pageable(1, 10));
        System.out.print(page.getContent().size());
    }
}
