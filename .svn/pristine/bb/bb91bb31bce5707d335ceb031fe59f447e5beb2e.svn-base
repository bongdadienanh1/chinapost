package com.ylife.chinapost.mobile.service.impl;


import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.mobile.service.InventorySearchProductService;
import com.ylife.common.model.ApiResult;
import com.ylife.common.model.OpenProduct;
import com.ylife.common.model.OpenUtil;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.DateUtil;
import com.ylife.utils.HttpUtil;
import com.ylife.utils.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XuKai on 2016/4/21.
 * InventoryManageServiceImpl
 */
@Service
public class InventorySearchProductServiceImpl implements InventorySearchProductService {

    Parser SIMPLE_PARSER = new SimpleParser();

    @Resource
    private GoodsInfoService goodsInfoService;

    @Override
    public Page<GoodsManagerResult> getSearchProductBygoodsName(String goodsInfoName, Pageable pageable) {
        return goodsInfoService.getGoods(goodsInfoName, null, "1", null, null, null, 2, true, null, "1", pageable);
    }

    /**
     * 获取商品库存
     *
     * @param url
     * @param merchantId
     * @param merchantkey
     * @param prodNo
     * @return
     */
    @Override
    public Integer getStockByProdNo(String url,String merchantId, String merchantkey, String prodNo) {
        //1、获取商品库存
        Map<String,String> map = new HashMap<>();
        String timestamp =DateUtil.formatToString(new Date(), "yyyyMMddHHmmss");
        map.put("merchantId",merchantId);
        map.put("timestamp",timestamp);
        map.put("sign",MD5Util.md5Hex(merchantId + timestamp + merchantkey));
        map.put("prodNo",prodNo);
        //请求供应商商品库存
        ApiResult apiResult = SIMPLE_PARSER.parseJSON(HttpUtil.doPost(url, map), new TypeToken<ApiResult>() {
        });
        //验签
        if(OpenUtil.isCheck(apiResult.getMerchantId(),apiResult.getTimestamp(),apiResult.getSign()).getReturnCode()==200){
            if(apiResult.getData() !=null){
                return ((OpenProduct)apiResult.getData()).getProdNum();
            }
        }
        return 0;
    }
}




