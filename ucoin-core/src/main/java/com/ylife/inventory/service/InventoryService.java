package com.ylife.inventory.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryBillItem;
import com.ylife.inventory.model.InventoryKey;

import java.util.List;
import java.util.Map;


public interface InventoryService {



    Inventory selectByPrimaryKey(InventoryKey key);

    Inventory selectByPrimaryKeyForUpdate(InventoryKey key);


    int updateByPrimaryKeySelective(Inventory inventory);




    /**
     * 获取库存
     *
     * @param enterpriseId 企业ID
     * @param goodsInfoId  货品ID
     */
    Inventory getInventory(long enterpriseId, long goodsInfoId);

    /**
     * 批量增加货品
     *
     * @param enterpriseId 企业ID
     * @param goods        货品信息
     */
    void setGoods(long enterpriseId, Map<Long, Integer> goods,Long code);

    /**
     *
     * @param enterpriseId  企业ID
     * @param goods   货品信息
     * @param code    单据编号
     */
    void setGoodss(long enterpriseId, List<InventoryBillItem> goods,Long code);
    /**
     * 删除某件商品
     *
     * @param enterpriseId 企业ID
     * @param goodsInfoId  货品ID
     */
    void deleteGood(long enterpriseId, long goodsInfoId);


    /**
     * 增加或减少库存
     *
     * @param key       库存主键值
     * @param inventory 库存的实际变化量，可为负值
     */
    void addActuallyInventory(InventoryKey key, int inventory);

    /**
     * 修改库存
     *
     * @param enterpriseId 企业ID
     * @param goodsInfoId  货品ID
     * @param inventory    要修改的库存值
     */
    void editInventory(long enterpriseId, long goodsInfoId, int inventory);

    /**
     * 修改可用库存
     *
     * @param enterpriseId 企业ID
     * @param goodsInfoId  货品ID
     * @param available    修改为的值
     */
    void setAvailable(long enterpriseId, long goodsInfoId, int available);

    /**
     * 增加或减少可用库存
     *
     * @param key       库存主键
     * @param available 可用库存实际变化量，可为负值
     */
    void addAvailableInventory(InventoryKey key, int available);

    /**
     * 获取预警值
     *
     * @param enterpriseId 企业id
     */
    int getWarning(long enterpriseId);

    //按仓库查询
    Page<InventoryGoodsResult> queryByInventory(Long enterpriseId, String goodsInfoName,
                                                String goodsNumber,Long brandId,Long thirdId,Pageable pageable);

    //按货品查询
    Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                               String goodsNumber,Long brandId,Long thirdId,Pageable pageable);

    /**
     * 获取指定货品在指定网点的货品及库存信息
     * @param enterpriseId
     * @param goodsInfoId
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<InventoryGoodsResult> getGoodsInventory(Long enterpriseId,Long goodsInfoId, String goodsInfoName,Integer goodsInfoType,
                                                 String goodsNumber,Long brandId,Long thirdId,Pageable pageable,String goodsInfoAdded);



    List<InventoryGoodsResult> selectGoodsAndAvailable(Long enterpriseId,
                                                 List<Long> goodsIds);


    /**
     * 获取预警货品的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getWarningGoods(Long enterpriseId,String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);


    List<Inventory> getAllGoodsInventory();

}
