package com.ylife.customer.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.PageBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCustomerService {
    @Resource
    private CustomerService customerService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;

    @Test
    public void testQueryAllMyOrders(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", 4390L);
        paramMap.put("createTime",new Date());
        PageBean result = customerService.queryAllMyOrders(paramMap,new PageBean());
        System.out.println(result);
    }

    @Test
    public void testQueryAllBackMyOrders(){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", 227151L);
        paramMap.put("createTime",new Date());
        PageBean result = customerService.queryAllBackMyOrders(paramMap, new PageBean());
        System.out.println(result);
    }

    @Test
    public void testUpdateByList() {
        Map<Long,ChinapostCustomer> map=new HashMap<>();

        ChinapostCustomer customer = new ChinapostCustomer();
        customer.setId(510566l);
        customer.setFullname("88");
        map.put(510566l,customer);
        ChinapostCustomer chinapostCustomer=new ChinapostCustomer();
        chinapostCustomer.setId(510567l);
        chinapostCustomer.setFullname("99");
        map.put(510566l,customer);
        chinapostCustomerService.updateByBatch(map);
    }
}
