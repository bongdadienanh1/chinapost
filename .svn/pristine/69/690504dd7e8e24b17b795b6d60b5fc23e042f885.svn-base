package com.ylife.UcoinGrand.service;

import com.google.gson.reflect.TypeToken;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.page.Page;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/14.
 */

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class testUcoinGrand {
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    Parser parser=new SimpleParser();
    @Test
    public void testUcoin() {
        Page<CustomerUcoinHistory> customerUcoinHistoryPage = customerUcoinHistoryService.getHistory(1l,1, null, null, null);
        List<CustomerUcoinHistory> customerUcoinHistoryList = customerUcoinHistoryPage.getContent();

        Map<String,String> paramMap=new HashMap<>();
        for (CustomerUcoinHistory customerUcoinHistory : customerUcoinHistoryList) {
            String paramjson = customerUcoinHistory.getParamJson();
            Map<String, String> map = parser.parseJSON(paramjson, new TypeToken<Map<String,String>>(){});
             for(String key:map.keySet()){
                paramMap.put(key,map.get(key));
            }
        }
        System.out.println(paramMap.get("存期"));
    }

    @Test

    public void testUcoinGrandInfoJson(){

    }


    @Test
    public void testChangeUcoin(){
        customerUcoinService.changeUcoin(510951L,307L,new BigDecimal(-120),new Date(),null,true);
        System.out.print("success!!!");
    }
}
