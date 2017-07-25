package com.ylife.wealth.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * TestEnterpriseMapper
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEnterpriseRequisitionMapper {

    @Resource
    private EnterpriseRequisitionMapper enterpriseRequisitionMapper;

    @Test
    public void testSelectByStatusAndEnterpriseId() {
        RequisitionStatus[] statuses = {};
        List<EnterpriseRequisition> list = enterpriseRequisitionMapper.selectByStatusAndEnterpriseId(1L, statuses, null, null, new Pageable(1, 100));
        System.out.print(list.size());
    }

    @Test
    public void testInsertSelective() {
        EnterpriseRequisition requisition = new EnterpriseRequisition();
        requisition.setCreateTime(new Date());
        requisition.setEnterpriseId(1L);
        requisition.setParentId(2L);
        requisition.setFee(new BigDecimal("1.22"));
        requisition.setRemark("asdfasgasd");
        requisition.setStatus(RequisitionStatus.APPLIED);
        enterpriseRequisitionMapper.insertSelective(requisition);
        System.out.print(requisition.getId());

    }
}
