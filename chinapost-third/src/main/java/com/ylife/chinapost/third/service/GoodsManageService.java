package com.ylife.chinapost.third.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsProductReleSpec;
import com.ylife.goods.model.GoodsSpecDetail;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.model.GoodsInfo;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * <p/>
 * 商品管理服务。
 */
public interface GoodsManageService {

    /**
     * 商品管理的集采货品列表
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param goodsInfoType 商品类型：集采，自购
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo,
                                      String goodsInfoAdded, Long brandId,
                                      Long typeId, Long thirdId, Integer goodsInfoType, Boolean online, Boolean valetShow,String auditStatus, Pageable pageable);


    /**
     * 保存商品
     * @param jsonData
     * @param goodsDetailDesc
     */
    void saveGoods(String jsonData, String goodsDetailDesc);


    /**
     * 根据商品Id获取商品信息
     * @param goodsInfoId
     * @return
     */
    GoodsInfo getGoodsInfoById(long goodsInfoId);


    /**
     * 修改商品及货品
     * @param jsonData
     * @param goodsDetailDesc
     */
    void updateGoods(String jsonData, String goodsDetailDesc);


    /**
     * 处理数据
     * @param list
     * @param arr
     * @param items
     * @param result
     * @return
     */
    List<List<GoodsProductReleSpec>> getAllGoodsSpecDetail(List<List<GoodsSpecDetail>> list, List<GoodsSpecDetail> arr, List<GoodsSpecDetail> items, List<List<GoodsProductReleSpec>> result);


    /**
     * 批量上下架
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    int batchUpdateGoodsInfoAdded(String goodsInfoIds, String goodsInfoAdded);

    /**
     * 修改货品库存
     * @param goodsInfoId 货品id
     * @param goodsInfoStock
     * */
    int updateStockByGoodsInfoId(Long goodsInfoId,Long goodsInfoStock);

}
