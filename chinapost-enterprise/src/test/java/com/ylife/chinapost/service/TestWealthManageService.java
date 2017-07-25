package com.ylife.chinapost.service;

import com.ylife.chinapost.service.pojo.SonsWealthResult;
import com.ylife.chinapost.service.pojo.WealthManageResult;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.service.EnterpriseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/14.
 * <p/>
 * TestWealthManageService
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWealthManageService {

    @Resource
    private WealthManageService wealthManageService;
    @Resource
    private EnterpriseService enterpriseService;

    @Test
    public void testGetSonsWealthResult() {
        long enterpriseId = 1L;
        SonsWealthResult result = new SonsWealthResult();
        BigDecimal totalWealth = new BigDecimal("0.00");
        List<Enterprise> enterpriseList = enterpriseService.getEnterprises(enterpriseId);
        List<WealthManageResult.SonWealth> sonWealths = new ArrayList<>();
        for (Enterprise enterprise : enterpriseList) {
            BigDecimal sonTotalWealth = enterpriseService.getTotalWealth(enterprise.getId());
            totalWealth = totalWealth.add(sonTotalWealth);
            //          WealthManageResult.SonWealth wealth = new SonsWealthResult.SonWealth(enterprise.getId(), enterprise.getEnterpriseName(), sonTotalWealth);
//            sonWealths.add(wealth);
        }
    }
}
