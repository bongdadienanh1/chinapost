package com.ylife.system.mapper;

import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4.
 */

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSystemParamMapper {

    @Resource
    private ParamMapper paramMapper;

    @Test
    public void testParam() {

        List<Param> params = paramMapper.selectByTypeId(1);
        for (Param param : params) {
            System.out.println(param.getParamName());
        }
    }

    @Test
    public void testEditParam() {
        Param param = new Param();
        param.setParamId(7);
        param.setParamName("吃屎1");
        param.setParamType(ParamType.TYPE_TXT);
        paramMapper.updateByPrimaryKeySelective(param);
    }
}
