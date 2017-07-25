package com.common.util;

import com.ylife.fdfs.FastDFS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/7/15.
 * TestFDFS
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestFDFS {

    @Resource
    private FastDFS dfs;

    @Test
    public void testSave() throws Exception {
        String s = dfs.saveFile(new byte[10], "txt", null);
        System.out.print(s);
    }

}
