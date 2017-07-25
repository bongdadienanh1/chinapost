package com.common.util;

import com.ylife.authority.mapper.ResourcePageMapper;
import com.ylife.authority.model.ResourcePage;
import com.ylife.utils.ResolverHelper;
import com.ylife.utils.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/29.
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ResolverHelperUtil {

    @Resource
    private ResourcePageMapper resourcePageMapper;

    @Test
    public  void testSecurityResolver() throws Exception {
        ResolverHelper resolverHelper = new ResolverHelper();
        Map<String,Object> map = resolverHelper.ResolverSecurityResource();
        Map<String,Object> tt = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            boolean flag = false;
            if (!StringUtil.isBlank(entry.getKey()) && !entry.getKey().equals("/") && ((ResolverHelper.SecurityBean) entry.getValue()).getPriority()==0) {
                ResourcePage parentResourcePage = resourcePageMapper.selectByUrl(((ResolverHelper.SecurityBean)entry.getValue()).getParent());
                if(parentResourcePage != null) {
                    flag = true;
                    ResourcePage resourcePage = new ResourcePage();
                    resourcePage.setUrl(entry.getKey());
                    resourcePage.setDesignation(((ResolverHelper.SecurityBean) entry.getValue()).getDescription());
                    resourcePage.setParentId(parentResourcePage.getId());
                    resourcePage.setSort(((ResolverHelper.SecurityBean) entry.getValue()).getPriority());
                    resourcePageMapper.insertSelective(resourcePage);
                }
            }
            if(!flag){
                tt.put(entry.getKey(),entry.getValue());
            }
        }
        System.out.print(map);
        System.out.print(tt);
    }
}
