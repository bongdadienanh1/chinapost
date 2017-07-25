package com.common.util;

import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.mapper.GoodsInfoMapper;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/26.
 */
public class TestExcel {
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    GoodsManagerResult goodsManagerResult;

    @Test
    public  void testExportExcel(){

        Map<String,String> map=new HashMap<>();
        map.put("goodsInfoName","货品名称");
        map.put("specString","货品规格");
        map.put("goodsInfoTypeName","货品类型");
        map.put("goodsBrand","品牌名称");
        map.put("goodsInfoId","货品id");
        GoodsManagerResult goodsManagerResults = goodsInfoMapper.selectByGoodsInfoId(3759l);
        File file=new File("E:/商品管理.xls");

    }

    @Test
    public  void testExcel(){
        double d = Double.parseDouble("3.21302E+17");
        System.out.println(d);
    }

    @Test
    public void testList(){
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list);
        System.out.println(list.size());
        StringBuffer stringBuffer = new StringBuffer();
        for (String string : list){
            stringBuffer.append(string);
        }
        System.out.println(stringBuffer);
        list.set(1,"11");
        System.out.println(list);
    }


    @Test
    public void testPwd(){

        String password = "ffff~1";
        if(!password.matches("(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$")){
            System.out.println("登录密码长度为8~20位且必须包含数字和字母");
            return;
        }

        System.out.println("yse");
    }
}
