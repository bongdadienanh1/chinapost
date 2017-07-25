package com.ylife.chinapost.boss.service;


import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;

/**
 * Created by XuKai on 2016/4/21.
 * 库存管理服务
 */
public interface InventoryManageService {

/**
 * 获取当前库存货品列表的page
 *
 * @param goodsName   商品名称
 * @param goodsNumber 货号
 * @param brandId     品牌ID
 * @param thirdId     商家ID
 * @param pageable    分页信息
 */
Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName, Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId,String goodsInfoAdded,Integer onlineShow, Pageable pageable);


}