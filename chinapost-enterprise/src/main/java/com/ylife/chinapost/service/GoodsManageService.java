package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
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
                                      Long typeId, Long thirdId,Integer goodsInfoType,Boolean online, Boolean valetShow,Pageable pageable);

    /**
     * 商品管理我的自购商品列表
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
    Page<GoodsManagerResult> getSettleGoods(Boolean onlineShow,Boolean valetShow,String goodsInfoName, String goodsInfoItemNo,
                                            String goodsInfoAdded,Long brandId,
                                            Long typeId, Long thirdId, Pageable pageable);

    /**
     * 商品管理根据节点id查看自购商品
     * @param enterpriseId 节点id
     * @param goodsInfoName 货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded 货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getSettleGoodsByEnterpriseId(Long enterpriseId,String goodsInfoName, String goodsInfoItemNo,
                                            String goodsInfoAdded,Long brandId, Long typeId, Long thirdId, Pageable pageable);





    List<GoodsManagerResult> selectBrand();

    List<GoodsManagerResult> selectType();

    List<GoodsManagerResult> selectThirdName(String thirdName);

    List<GoodsManagerResult> selectThirdEnterpriseName(String thirdName);

    List<GoodsManagerResult> getBrandTypeThird();

    GoodsManagerResult getByGoodsInfoId(long goodsInfoId);


    /**
     * 导出excel
     * @return
     */

    Boolean exportExcel();

    /**
     * 保存商品
     * @param jsonData
     */
    void saveGoods(String jsonData,String goodsDetailDesc);


    /**
     *
     * @param goodsInfoId
     * @return
     */
    GoodsInfo getGoodsInfoById(long goodsInfoId);


    /**
     * 修改商品及货品
     * @param jsonData
     */
    void updateGoods(String jsonData,String goodsDetailDesc);


}
