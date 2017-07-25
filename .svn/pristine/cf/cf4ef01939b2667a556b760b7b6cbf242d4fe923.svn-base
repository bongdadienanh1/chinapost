package com.ylife.chinapost.mobile.service;


import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;

/**
 * Created by XuKai on 2016/4/21.
 * 库存管理服务
 */
public interface InventorySearchProductService {

    /**
     * 获取当前库存货品列表的page
     *
     * @param goodsInfoName 商品名称
     * @param pageable      分页信息
     */
    Page<GoodsManagerResult> getSearchProductBygoodsName(String goodsInfoName, Pageable pageable);

    /**
     * 获取商品库存
     * @param url
     * @param merchantId
     * @param merchantkey
     * @param prodNo
     * @return
     */
    Integer getStockByProdNo(String url,String merchantId,String merchantkey,String prodNo);

}
