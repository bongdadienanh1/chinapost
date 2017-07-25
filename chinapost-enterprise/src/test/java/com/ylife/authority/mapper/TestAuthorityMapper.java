package com.ylife.authority.mapper;

import com.ylife.authority.model.Authority;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * TestEnterpriseMapper
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAuthorityMapper {

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private AuthorityResoucePageMapper authorityResoucePageMapper;

    @Test
    public void testSelectByStatusAndEnterpriseId() {
        authorityMapper.selectAuthResultByEnterpriseId(1L);
    }

    @Test
    public void testAu(){
        List<Long> tt = authorityResoucePageMapper.selectAuthorityIdByPageId(1L);
        System.out.println(tt);
    }

    @Test
    public void testInsert(){
        Authority authority=new Authority();
        authority.setCharacterization("ceshi");
        authority.setDesignation("测试");
        int i= authorityMapper.insertSelective(authority);
        System.out.println(i);
    }

}
