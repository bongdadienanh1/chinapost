package com.ylife.enterprise.mapper;

import com.ylife.enterprise.model.EnterpriseManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * TestEnterpriseMapper
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEnterpriseManagerMapper {

    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;

    @Test
    public void testUpdatePasswordByManagerId() {
        EnterpriseManager manager = enterpriseManagerMapper.selectByUsername("nanjin");
        System.out.print(manager.getUsername());
    }

}
