package com.ylife.client.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.ylife.client.bean.BackOrder;
import com.ylife.client.bean.OOrderAllInfo;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jack chen
 * @version 2.0
 * @since 15/8/31
 */
@Service("OpenOrderService")
public class OpenOrderService{

    /**
     * 获取同步优生活订单信息
     * @param orderList 需要同步邮宝订单信息(已付款未发货的订单)
     * @return
     * @throws IOException
     */
    public List<OOrderAllInfo> updateOrderStatus(String orderList) throws IOException {

        Map<String,String> map = new HashMap<>();

        //1、查询已付款未发货的订单
        map.put("orderList", orderList);

        //2、获取优生活商城修改的订单
        String url = "order/getOrderCodeList.htm";
        String orderRespose = PlatformClient.post(url, map);
        if(!StringUtils.isBlank(orderRespose)&&!StringUtils.isBlank(JSONObject.parseObject(orderRespose).get("code").toString())&&Integer.parseInt(JSONObject.parseObject(orderRespose).get("code").toString())==200) {
            JSONArray obj = JSONObject.parseObject(orderRespose).getJSONArray("data");
            if (obj.size() == 0) {
                return null;
            }
            //订单号集合
            String orderCodeList = "";
            for (int i = 0; i < obj.size(); i++) {
                orderCodeList += obj.get(i).toString().concat(",");
            }
            orderCodeList = orderCodeList.substring(0, orderCodeList.length() - 1);
            map.put("OrderCoderList", orderCodeList);
            //2、根据货品编号查询货品信息
            url = "order/getOrderDetailList.htm";

            String orderListRespose = PlatformClient.post(url, map);
            if (Integer.parseInt(JSONObject.parseObject(orderListRespose).get("code").toString()) == 200) {
                Parser parser = new SimpleParser();
                return parser.parseJSON(JSONObject.parseObject(orderListRespose).getJSONArray("data").toJSONString(), new TypeToken<List<OOrderAllInfo>>() {
                });
            }
        }
        return null;
    }

    /**
     * 获取同步优生活退单信息
     * @param backOrderCodeList 需要同步邮宝退单信息(已申请退款、退货)
     * @return
     * @throws IOException
     */
    public List<BackOrder> updateBackOrderStatus(String backOrderCodeList) throws IOException {

        Map<String,String> map = new HashMap<>();

        //1、查询已提交的退单
        map.put("backOrderCodeList", backOrderCodeList);

        //2、获取优生活商城修改的退单
        String url = "order/getBackOrderCodeList.htm";
        String orderRespose = PlatformClient.post(url, map);
        if(!StringUtils.isBlank(orderRespose)&&!StringUtils.isBlank(JSONObject.parseObject(orderRespose).get("code").toString())&&Integer.parseInt(JSONObject.parseObject(orderRespose).get("code").toString())==200) {
            JSONArray obj = JSONObject.parseObject(orderRespose).getJSONArray("data");
            if (obj.size() == 0) {
                return null;
            }
            //退单号集合
            String backOrderCodeStr = "";
            for (int i = 0; i < obj.size(); i++) {
                backOrderCodeStr += obj.get(i).toString().concat(",");
            }
            backOrderCodeStr = backOrderCodeStr.substring(0, backOrderCodeStr.length() - 1);
            map.put("backOrderCodeList", backOrderCodeStr);
            //2、根据货品编号查询货品信息
            url = "order/getBackOrderList.htm";

            String orderListRespose = PlatformClient.post(url, map);
            if (Integer.parseInt(JSONObject.parseObject(orderListRespose).get("code").toString()) == 200) {
                Parser parser = new SimpleParser();
                return parser.parseJSON(JSONObject.parseObject(orderListRespose).getJSONArray("data").toJSONString(), new TypeToken<List<BackOrder>>() {
                });
            }
        }
        return null;
    }
}