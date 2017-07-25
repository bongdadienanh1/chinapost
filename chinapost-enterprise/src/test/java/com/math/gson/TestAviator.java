package com.math.gson;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventoryManager.service.TestInventoryManagerService;
import com.ylife.order.model.BackOrderGoods;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.service.CreditOrderService;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.utils.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/6/2.
 */

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestAviator {

    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;


    @Test
    public void testMap() {
        System.out.println(getMap(1l));
    }



    public Map<String, Object> getMap(long enterpriseId) {
        Enterprise info = enterpriseService.getEnterprise(enterpriseId);
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", info.getId());
        map.put("name", info.getEnterpriseName());
        map.put("end", function.getEnd());
        List<Enterprise> sons = enterpriseMapper.selectByParentId(enterpriseId);
        List<Map> sonsMaps = new ArrayList<>();
        for (Enterprise enterprise : sons) {
            sonsMaps.add(getMap(enterprise.getId()));
        }
        map.put("sons", sonsMaps);
        return map;
    }



    @Test
    public void testDateUtil(){
       String ds="2016-10-5 02:15:17";
        Date date=DateUtil.fromString(ds, "yyyy-MM-dd hh:mm:ss");

        Date result=DateUtil.getFirstDayOfMonth(date);
        String s=DateUtil.formatToString(result,"yyyy-MM-dd hh:mm:ss");
        System.out.println(s);

        Date result2=DateUtil.getLastDayOfMonth(date);
        String s2=DateUtil.formatToString(result2,"yyyy-MM-dd hh:mm:ss");
        System.out.println(s2);
    }

    @Test
    public void testAviator() {
        String expression="id*(b-c)*10";
        Expression compile=AviatorEvaluator.compile(expression);
        Map<String,Object> env=new HashMap<>();
        long avalue=10;
        long bvalue=50;
        long cvalue=30;
        env.put("id",avalue);
        env.put("b",bvalue);
        env.put("c",cvalue);
        Object result=compile.execute(env);
        System.out.println(result);

        int a=12;
        String b=String.valueOf(a);
        System.out.println(b);
    }


    @Test
    public void testBianLiang() {

        //传入公式编译
        String expression = "inventory*10+available*12+200";
        Expression compile = AviatorEvaluator.compile(expression);

        InventoryKey key=new InventoryKey(1l,3754l);
       // GoodsInfo goodsInfo=goodsInfoMapper.selectByPrimaryKey(3754l);
        //System.out.println(goodsInfo.getGoodsInfoName());

        Inventory inventory =inventoryMapper.selectByPrimaryKey(key);
        Map<String, Object> env = new HashMap<String, Object>();
        long inventor=inventory.getInventory();
        long available=inventory.getAvailable();

        env.put("inventory", inventor);
        env.put("available",available);
        String result = compile.execute(env).toString();
        System.out.println(result);
    }


    @Test
    public void testSttring(){

        List<String> list=Arrays.asList("",null,"");
        System.out.println(list);


    }

    @Test
    public  void testTrim(){
        CreditOrder backOrder = creditOrderService.selectByCreditOrderCode(1607200233942000l);

        List<BackOrderGoods> backOrderGoodses = backOrder.getBackOrderGoods();
        String backGoodsName="";
        for (BackOrderGoods backOrderGoods : backOrderGoodses) {
            String goodsINfoName = backOrderGoods.getGoodsInfoName() + "(" + backOrderGoods.getGoodsNum() + ")";
            backGoodsName += goodsINfoName +",";
        }

        int length=backGoodsName.length();
        backGoodsName=backGoodsName.substring(0,length-1);
        System.out.println(backGoodsName);
        System.out.println(backGoodsName.length());
        System.out.println(backGoodsName.indexOf(1));
    }


    @Test
    public void testBigDecimal(){
        BigDecimal a=new BigDecimal("4.1555");
        DecimalFormat df=new DecimalFormat("#.00");
        String b=df.format(a);
        System.out.println(b);
        BigDecimal c= a.setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println(c);


        System.out.println(DateUtil.getStartYear(DateUtil.fromString("20141010", "yyyyMMdd")));
    }

    @Test
    public void testDateUtils(){
        Date now =new Date();
        Date date=DateUtil.getWeekAgo(now);
        System.out.println(date);
        String dateStr="2016-06-05";
        Date date1 =DateUtil.fromString(dateStr, "yyyy-MM-dd");
        System.out.println(DateUtil.formatToString(date1,"MM-dd"));
        System.out.println(DateUtil.getMorning(date1));
        System.out.println(DateUtil.getNight(date1));

    }


    @Test
    public void testSubString(){
        String idCard="321302198806260815";
        System.out.println(idCard.substring(16, 17));

        BigDecimal a=new BigDecimal(2);
        a.subtract(new BigDecimal(1));
        System.out.println(a);
    }






}
