package com.ylife.client.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.reflect.TypeToken;
import com.ylife.client.bean.GoodsProductDetailVo;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.utils.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jack chen
 * @version 2.0
 * @since 15/8/25
 */
@Service("OpenGoodsProductService")
public class OpenGoodsProductService{

    /**
     * 获取同步的商品信息
     * @param syncDate 同步结束时间
     * @return
     * @throws Exception
     */
    public List<GoodsProductDetailVo>  GetSyncGoodsProducts(Date syncDate) throws Exception {

        Map<String,String> map = new HashMap<>();
        //查询的开始时间戳
        String startTime = "1799-1-1 00:00:00";
        if(syncDate!=null){
            startTime = DateUtil.formatToString(DateUtil.addSecond(syncDate),"yyyy-MM-dd HH:mm:ss");
        }
        map.put("startTime",startTime);
        //查询的结束时间戳
        map.put("endTime",DateUtil.formatToString(new Date(),"yyyy-MM-dd HH:mm:ss"));

        //1、获取修改商品的货号列表
        String url = "goods/getGoodsItemNoList.htm";
        String goodsProductNoRespose = PlatformClient.get(url, map);
        if(!StringUtils.isBlank(goodsProductNoRespose)&&!StringUtils.isBlank(JSONObject.parseObject(goodsProductNoRespose).get("code").toString())&&Integer.parseInt(JSONObject.parseObject(goodsProductNoRespose).get("code").toString())==200) {
            JSONArray obj = JSONObject.parseObject(goodsProductNoRespose).getJSONArray("data");
            if(obj.size()==0){
                return null;
            }
            //货品货号集合
            String goodsItemNoList = "";
            for (int i = 0; i < obj.size(); i++) {
                goodsItemNoList += obj.getJSONObject(i).getString("goodsInfoItemNo").concat(",");
            }
            goodsItemNoList = goodsItemNoList.substring(0, goodsItemNoList.length() - 1);
            map.put("goodsItemNoList",goodsItemNoList);
            //2、根据货品编号查询货品信息
            url = "goods/getGoodsDetailList.htm";

            String goodsProductListRespose = PlatformClient.post(url, map);
            if(Integer.parseInt(JSONObject.parseObject(goodsProductListRespose).get("code").toString())==200) {
                Parser parser = new SimpleParser();
                return parser.parseJSON(JSONObject.parseObject(goodsProductListRespose).getJSONArray("data").toJSONString(), new TypeToken<List<GoodsProductDetailVo>>() {
                });
            }
        }
        return null;
    }
}
