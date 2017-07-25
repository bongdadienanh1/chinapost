package com.ylife.enterprise.service;

import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.CustomerEnterpriseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/11.
 * TestEnterpriseManagerService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEnterpriseManagerService {

    @Resource
    private EnterpriseManagerService enterpriseManagerService;
    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;
    @Resource
    private CustomerEnterpriseService customerEnterpriseService;

    @Test
    public void addEnterpriseManager() {
        enterpriseManagerService.addEnterpriseManager(1L, false, "laoshan", "123456", "18114421971", null, "张阅山", 1L);
    }

    @Test
    public void addEnterpriseManager1() {
        List<ChinapostCustomer> list = chinapostCustomerMapper.selectAll();
        for (ChinapostCustomer customer : list) {
            customerEnterpriseService.addRelation(customer.getId(), customer.getEnterpriseId() == null ? 1 : customer.getEnterpriseId());
        }
    }
}
