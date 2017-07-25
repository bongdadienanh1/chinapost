package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.service.EnterpriseRequisitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/4/19.
 * <p/>
 * 请款单据。
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRequisitionManageService {

    @Resource
    private RequisitionManageService requisitionManageService;
    @Resource
    private EnterpriseRequisitionService enterpriseRequisitionService;

  @Test
    public void testGetMyRequisition() {
        Page<EnterpriseRequisition> page = enterpriseRequisitionService.getEnterpriseRequisitions(1, null, null, new Pageable(1, 100));
        for (EnterpriseRequisition requisition : page.getContent()) {
            System.out.println(requisition.getCreateTime());
        }
    }
}
