package com.math.gson;

import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.Renderer;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.json.json.SimpleRenderer;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.goods.model.GoodsData;
import com.ylife.utils.CheckIdcard;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/27.
 * test
 */
public class TestCompare {

    private Parser parser = new SimpleParser();

    private Renderer renderer = new SimpleRenderer();

    @Test
    public void testJson(){
        Long[] ids= new Long[]{10L,20L,30L};
        List<Long> list=new ArrayList<>();
        list.add(1l);
        list.add(2l);
        String listJson=renderer.render(ids);
        System.out.println(listJson);
        Map<Long,Integer> map=new HashMap<>();
        map.put(1l,10);
        map.put(2l,20);
        String mapJson=renderer.render(map);
        System.out.println(mapJson);

        List<String> list1=new ArrayList<>();
        list1.add("1");list1.add(null);list1.add("3");
        String list1String=renderer.render(list1);
        System.out.println(list1String);

    }

    @Test
    public void testGoodsJson(){
        GoodsData goodsData = new GoodsData();
        goodsData.setCatId(123L);
        goodsData.setGoodsName("LOL");
        goodsData.setGoodsDetailDesc("<p style=\"text-align:center;\">\n" +
                "\t<img align=\"absMiddle\" src=\"http://img03.taobaocdn.com/imgextra/i3/1957978841/T2wJgoXDVXXXXXXXXX-1957978841.jpg_.webp\" />\n" +
                "</p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<img align=\"absMiddle\" src=\"http://img02.taobaocdn.com/imgextra/i2/1957978841/T2VfeiXSFXXXXXXXXX-1957978841.jpg_.webp\" />\n" +
                "</p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<img align=\"absMiddle\" src=\"http://img04.taobaocdn.com/imgextra/i4/1957978841/T2wzajXGpXXXXXXXXX-1957978841.jpg_.webp\" /><span style=\"line-height:1.5;\"></span>\n" +
                "</p>");
        goodsData.setGoodsPrice(new BigDecimal(123));
        goodsData.setGoodsAdded(true);
        goodsData.setBrandId(234L);
        List<GoodsData.Spec> spec = new ArrayList<>();

        GoodsData.Spec spec1 = new GoodsData.Spec();
        spec1.setSpecName("颜色");
        List<GoodsData.Spec.SpecDetail> specDetails = new ArrayList<>();
        GoodsData.Spec.SpecDetail specDetail1 = new GoodsData.Spec.SpecDetail();
        specDetail1.setValue("红色");
        GoodsData.Spec.SpecDetail specDetail2 = new GoodsData.Spec.SpecDetail();
        specDetail2.setValue("绿色");
        GoodsData.Spec.SpecDetail specDetail3 = new GoodsData.Spec.SpecDetail();
        specDetail3.setValue("蓝色");
        specDetails.add(specDetail1);
        specDetails.add(specDetail2);
        specDetails.add(specDetail3);
        spec1.setDetails(specDetails);

        GoodsData.Spec spec2 = new GoodsData.Spec();
        spec2.setSpecName("大小");
        List<GoodsData.Spec.SpecDetail> specDetails1 = new ArrayList<>();
        GoodsData.Spec.SpecDetail specDetail4 = new GoodsData.Spec.SpecDetail();
        specDetail4.setValue("128G");
        GoodsData.Spec.SpecDetail specDetail5 = new GoodsData.Spec.SpecDetail();
        specDetail5.setValue("64G");
        GoodsData.Spec.SpecDetail specDetail6 = new GoodsData.Spec.SpecDetail();
        specDetail6.setValue("32G");
        specDetails1.add(specDetail4);
        specDetails1.add(specDetail5);
        specDetails1.add(specDetail6);
        spec2.setDetails(specDetails1);

        spec.add(spec1);
        spec.add(spec2);

        goodsData.setSpecs(spec);


        List<GoodsData.TypeSpec> typeSpecs = new ArrayList<>();

        GoodsData.TypeSpec typeSpec = new GoodsData.TypeSpec();
        typeSpec.setTypeSpecName("品牌");
        typeSpec.setTypeSpecValue("Apple");
        GoodsData.TypeSpec typeSpec1 = new GoodsData.TypeSpec();
        typeSpec1.setTypeSpecName("扩展属性");
        typeSpec1.setTypeSpecValue("扩展属性值");

        typeSpecs.add(typeSpec);
        typeSpecs.add(typeSpec1);

        goodsData.setTypeSpecs(typeSpecs);


        List<GoodsData.GoodsInfo> goodsInfos = new ArrayList<>();

        GoodsData.GoodsInfo goodsInfo = new GoodsData.GoodsInfo();
        goodsInfo.setGoodsInfoItemNo("1111111111111");
        goodsInfo.setGoodsInfoName("货品1");
        goodsInfo.setGoodsInfoSettlePrice(new BigDecimal(12));
        goodsInfo.setGoodsInfoPreferprice(new BigDecimal(12.8));


        List<GoodsData.GoodsInfo.GoodsInfoImage> goodsInfoImages = new ArrayList<>();
        GoodsData.GoodsInfo.GoodsInfoImage goodsInfoImage = new GoodsData.GoodsInfo.GoodsInfoImage();
        goodsInfoImage.setImageInName("sdfssdfsdfsdfsfsfsfsfs");
        GoodsData.GoodsInfo.GoodsInfoImage goodsInfoImage1 = new GoodsData.GoodsInfo.GoodsInfoImage();
        goodsInfoImage1.setImageInName("sdfssdfsdfsdfsfsfsfsfs");

        goodsInfoImages.add(goodsInfoImage);
        goodsInfoImages.add(goodsInfoImage1);
        goodsInfo.setInfoImages(goodsInfoImages);

        GoodsData.GoodsInfo goodsInfo1 = new GoodsData.GoodsInfo();
        goodsInfo1.setGoodsInfoItemNo("1111111111111");
        goodsInfo1.setGoodsInfoName("货品1");
        goodsInfo1.setGoodsInfoSettlePrice(new BigDecimal(12));
        goodsInfo1.setGoodsInfoPreferprice(new BigDecimal(12.8));


        List<GoodsData.GoodsInfo.GoodsInfoImage> goodsInfoImages1 = new ArrayList<>();
        GoodsData.GoodsInfo.GoodsInfoImage goodsInfoImage2 = new GoodsData.GoodsInfo.GoodsInfoImage();
        goodsInfoImage2.setImageInName("sdfssdfsdfsdfsfsfsfsfs");
        GoodsData.GoodsInfo.GoodsInfoImage goodsInfoImage3= new GoodsData.GoodsInfo.GoodsInfoImage();
        goodsInfoImage3.setImageInName("sdfssdfsdfsdfsfsfsfsfs");

        goodsInfoImages1.add(goodsInfoImage2);
        goodsInfoImages1.add(goodsInfoImage3);

        goodsInfo1.setInfoImages(goodsInfoImages1);
        goodsInfos.add(goodsInfo);
        goodsInfos.add(goodsInfo1);

        goodsData.setGoodsInfos(goodsInfos);


        String mapJson=renderer.render(goodsData);
        System.out.println(mapJson);

    }

    @Test
    public void test123() {
        String str[] = {"A", "B", "C", "D", "E"};

        int nCnt = str.length;

        int nBit = (0xFFFFFFFF >>> (32 - nCnt));

        for (int i = 1; i <= nBit; i++) {
            for (int j = 0; j < nCnt; j++) {
                if ((i << (31 - j)) >> 31 == -1) {
                    System.out.print(str[j]);
                }
            }
            System.out.println("");
        }

    }

    @Test
    public void test234(){
        String arg1[] = {"1","2"};
        String arg2[] = {"a","b"};
        String arg3[] = {"#","$"};
        String arg4[] = {"x","y"};
        getAllPath(arg1,arg2,arg3);
        getAllPath(arg1,arg2,arg3,arg4);
    }

    public static void getAllPath(String[] ...args) {
        int itemNumber = args.length;
        int runCount = (int) Math.pow(2, itemNumber);
        for (int index = 0; index < runCount; index++) {
            String location = String.format("%0" + itemNumber + "d", Integer.valueOf(Integer.toBinaryString(index)));
            int item = 0;
            for (String[] tmp : args) {
                System.out.printf("%s ", tmp[location.charAt(item++) - '0']);
            }
            System.out.println();
        }
    }

    public static void test(List<String[]> list, String[] arr, String str)
    {
        for (int i = 0; i < list.size(); i++)
        {
            //取得当前的数组
            if (i == list.indexOf(arr))
            {
                //迭代数组
                for (String st : arr)
                {
                    st = str + st;
                    if (i < list.size() - 1)
                    {
                        test(list, list.get(i + 1), st);
                    }
                    else if (i == list.size() - 1)
                    {
                        System.out.println(st);
                    }
                }
            }
        }
    }

    @Test
    public void test345(){
        String[] arr1 = { "1", "2"};
        String[] arr2 = { "a"};
        String[] arr3 = { "#", "$" };
        String[] arg4 = {"x","y"};
        List<String[]> list = new ArrayList<String[]>();
        list.add(arr1);
        list.add(arr2);
        list.add(arr3);
        list.add(arg4);
        test(list, arr1, "");
    }



    @Test
    public void testCompare() {

        BigDecimal remain = new BigDecimal(20);
        BigDecimal resePrice = new BigDecimal(30);
        int result = resePrice.compareTo(remain);
        if (result > 0) {
            System.out.println("大于");
        } else if (result == 0) {
            System.out.println("等于");

        } else {
            System.out.println("小于");
        }
        System.out.println(new java.text.DecimalFormat("0.00").format(4.014));
    }


    @Test
    public void testCheckIdCard(){
        String idCard="14010719931003222X";
        Boolean result=CheckIdcard.isValid(idCard);
        System.out.println(result);
    }



    @Test
    public void testGenerate() throws InterruptedException {
        Set<Long> set = new HashSet<>();
        Generator generator1 = IdGeneratorFactory.create("test");
        for (int i = 0; i < 1005; i++) {
            long id = generator1.generate();
            if (set.contains(id)) {
                System.out.print("炸！");
            }
            set.add(id);
        }
    }



    public static class TestRun implements Runnable {

        private Generator generator;

        private Set<Long> set;

        private int number;

        public TestRun(Generator generator, Set set, int i) {
            this.generator = generator;
            this.set = set;
            this.number = i;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                long id = generator.generate();
                if (set.contains(id)) {
                    throw new RuntimeException("重复了！");
                }
                set.add(id);
            }
        }
    }
}

