package com.math.gson;

import com.google.gson.reflect.TypeToken;
import com.ylife.data.json.json.*;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.inventory.model.InventoryBillItem;
import com.ylife.utils.HttpUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * TestUcoinGrandService
 */
public class TestUcoinGrandService {

    private Parser parser = new SimpleParser();

    private Renderer renderer = new SimpleRenderer();


    @Test
    public void testListGson(){
        List<String> excelList=new ArrayList<>();
        excelList.add("\"姓名\",\"身份证号\",\"邮宝促销金额\",\"存期\",\"金额\"");
        String json=renderer.render(excelList);
        System.out.println(json);
        List<List<String>> lists=new ArrayList<>();
        lists.add(excelList);
        String json2=renderer.render(lists);
        System.out.println(json2);
    }






    @Test
    public void testGson() {

        Map<Long, Integer> map = new HashMap<>();
        map.put(1l, null);
        map.put(2l,5);
        map.put(3l, 6);
        map.put(4l,7 );
        Set<Integer> set=new HashSet<>();


        String json = renderer.render(map);
        System.out.println(json);

    }

    @Test
    public void testMap(){
        Map<Integer,String> valueMap=new HashMap<>();
        valueMap.put(107,"金额");
        valueMap.put(108,"存期");
        String json=renderer.render(valueMap);
        System.out.println(json);

    }


    @Test
    public void testGson1() {
        String pages = "{\"2\":,\"3\":2,\"4\":2}";
        Map<Long, Integer> map = parser.parseJSON(pages, new TypeToken<Map<Long, Integer>>() {
        });
        System.out.println(map.get(2L));
    }

    @Test
    public void testGson100() {
        String pages = "[{\"productTime\":Sun Apr 30 2017 00:00:00,\"endWarnDay\":10,\"endTime\":Fri May 12 2017 00:00:00 GMT+0800 (中国标准时间),\"amount\":10,\"goodsInfoId\":400819}]";
        List<InventoryBillItem> map = parser.parseJSON(pages, new TypeToken<List<InventoryBillItem>>() {
        });
        System.out.println(map);
    }

    @Test
    public void testGson2() {

        Renderer renderer = SkipFieldRenderer.leave("a");
        String json = new JsonResponseBean(new A(), renderer).toJson();
        System.out.print(json);

    }


    @Test
    public void testGson3() {

        String json = new JsonResponseBean(new B()).toJson();

        System.out.print(json);

    }

    @Test
    public void testGson4() throws IOException {

        String string = HttpUtil.doGet("http://www.baidu.com/", new HashMap<String, String>());

        System.out.print(string);

    }

    public static class A {
        private String a = "a";
        private String b = "b";
        private String c = "c";
        private String d = "d";

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }
    }

    public static class B extends A {

        private String e = "e";

        public String getE() {
            return e;
        }

        public void setE(String e) {
            this.e = e;
        }
    }

}
