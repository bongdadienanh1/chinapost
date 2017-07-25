package com.ylife.order.mapper;


import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCreditOrderMapper {

    @Resource
    private CreditOrderMapper creditOrderMapper;


    @Test
    public void testSelectByModelSelectiveAndCreateDate() {
        List<CreditOrder> list = creditOrderMapper.selectByModelSelectiveAndEnterpriseIdAndCreateDate(new CreditOrder(), null,null,null, null, null, new Pageable(1, 10));

        System.out.print(new JsonResponseBean(list).toJson());
    }

}