package com.ylife.hierarchyManagerService;

import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/17.
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class testHierarchyManager {


    @Resource
    EnterpriseInfoService enterpriseInfoService;

    @Resource
    EnterpriseFunctionMapper enterpriseFunctionMapper;

    @Test
    public  void testHierarchyManager(){

        List<EnterpriseInfo> enterpriseInfoList= enterpriseInfoService.getEnterpriseInfos(1);
        System.out.println(new JsonResponseBean(enterpriseInfoList).toJson());

    }


    @Test
    public void testFunc(){
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(700L);
        System.out.println(function.getMaxCatalog());
    }
}
