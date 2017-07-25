package com.ylife.product.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.model.GoodsInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 * <p/>
 * 货品信息服务。
 */
public interface GoodsInfoService {
    /**
     * 商品管理的集采货品列表
     */
    Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo,
                                      String goodsInfoAdded, Long brandId, Long typeId, Long thirdId,Integer goodsInfoType,Boolean online,Boolean valetShow,String auditStatus, Pageable pageable);


    /**
     * 商品管理我的自购商品列表
     * @param enterpriseIds  选取查看的机构id，不穿参数的时候默认登录账号
     * @param onlineShow 商城和代客商城的显示
     * @param goodsInfoName 货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded 货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getSettleGoods(List<Long> enterpriseIds,Boolean onlineShow,Boolean valetShow,String goodsInfoName, String goodsInfoItemNo,
                                            String goodsInfoAdded,Long brandId,
                                            Long typeId, Long thirdId, Pageable pageable);

    /**
     * 商品管理根据节点id查看本级和下级自购商品
     * @param maxCatalog 层级索引上限
     * @param maxCatalog 层级索引下限
     * @param goodsInfoName 货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded 货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getSettleGoodsByEnterpriseId(Long maxCatalog,Long minCatalog,String goodsInfoName, String goodsInfoItemNo,
                                                          String goodsInfoAdded,Long brandId, Long typeId, Long thirdId, Pageable pageable);



    //根据货品id获取货品信息
    GoodsInfo getById(Long goodsInfoId);

    GoodsManagerResult getGoodsInfoByGoodsNumber(String goodsInfoItemNo);

   /* Page<GoodsManagerResult> getValetGoods(String goodsInfoName,Float lowPrice,Float highPrice);*/

    /**
     *获取所有商品品牌
     * @return
     */
    List<GoodsManagerResult> getBrands();

    /**
     *获取所有商品类型
     * @return
     */
    List<GoodsManagerResult> getType();

    /**
     *获取集采商品所属商家
     * @return
     */
    List<GoodsManagerResult> getThirdName(String thirdName);

    /**
     *获取自购商品所属商家
     * @return
     */
    List<GoodsManagerResult> getThirdEnterprisenName(String thirdName);


    /**
     *
     * @return
     */
    List<GoodsManagerResult> getBrandTypeThird();

    //商品下架
    int unshelves(long goodsInfoId);

    //商品商家
    int shelves(long goodsInfoId);

    //更新商品商城显示信息
    int updateShow(Boolean onlineShow,Boolean valetShow,long goodsInfoId);

    /**
     * 批量修改上下架
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    int batchUpdateGoodsInfoAdded(String[] goodsInfoIds,String goodsInfoAdded);

    /**
     * 删除货品
     * @param thirdIds
     * @return
     */
    int deleteGoodsInfo(String[] thirdIds);


}