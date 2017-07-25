package com.ylife.chinapost.service;


import com.ylife.chinapost.controller.api.InventoryManageAPIController;
import com.ylife.chinapost.service.impl.InventoryManageServiceImpl;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XuKai on 2016/4/21.
 * 库存管理服务
 */
public interface InventoryManageService {

    /**
     * billId 单号id
     */
    List<InventoryBillLog> getBillLog(long billId);
    /**
     *
     * @param id 删除预警货品id
     * @return
     */
    int deleteWarnDay(long id);

    /**
     *
     * @param goodsInfoId  商品id
     * @return
     */
    Inventory selectByGoodsInfoId(Long goodsInfoId);

    /**
     *
     * @param inventoryBillItemDetail  更新预警
     * @return
     */
    int updateWarnDay(InventoryBillItemDetail inventoryBillItemDetail);

    /**
     *
     * @param goodsInfoId     商品id
     * @param goodsInfoWarning
     * @return
     */
    int editWarning(Long goodsInfoId,Integer goodsInfoWarning);

    /**
     *
     * @param minCatalog   查询等级区间
     * @param maxCatalog   查询等级区间
     * @param endStartDate 到期开始时间
     * @param endEndDate   到期结束时间
     * @param endDate      入库开始时间
     * @param startDate    入库结束时间
     * @param day          查询多少天数之内
     * @param goodsInfoItemNo  货品编号
     * @param code           单据编号
     * @param overday        到期提醒方式
     * @return
     */
    Page<WarnDay> selectWarnDay(long minCatalog,long maxCatalog,Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code, Pageable pageable,String overday,String type,long catalog);



    /**
     * 获取当前库存货品列表的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getInventoryGoodsResult(String goodsName, String goodsNumber, Long brandId, Long thirdId,Integer goodsInfoType, Pageable pageable);

    /**
     * 获取当前库存货品列表的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName,Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 获取具体库存货品实例
     *
     * @param goodsInfoId 货品ID
     */
    InventoryGoodsResult getInventoryGoodsResultByPrimarykey(long goodsInfoId);


    /**
     * 获取子级网点
     */
    Map<String, Object> sonEnterprise();

    /**
     * 选择货品，获取货品列表(已经删除的货品不显示)
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     */
    Page<InventoryGoodsResult> getGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 增加库存值
     *
     * @param goodsInfoId 货品ID
     * @param inventory   库存数量
     */
    void addInventory(long goodsInfoId, int inventory);

    /**
     * 修改库存
     */
    void editInventory(long goodsInfoId, int inventory);

    /**
     * 修改可用库存
     *
     * @param goodsInfoId 货品ID
     * @param available   可用数量
     */
    void editAvailable(long goodsInfoId, int available);

    /**
     * 删除零库存商品
     */
    void deleteGoods(long goodsInfoId);

    /**
     * 添加仓库货品
     *
     * @param goods
     */
    void addGoods(Map<Long, Integer> goods);
    void addGoodss(List<InventoryBillItem> goods);
    /**
     * 设置预警库存
     */
    void setWarning(int warning);

    /**
     * 获取预警货品的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getWarningGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId,Pageable pageable);

    /**
     * 获取指定货品信息以及网点库存信息
     *
     * @param enterpriseId
     * @param goodsInfoId
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<InventoryGoodsResult> getGoodsAndInventory(Long enterpriseId, Long goodsInfoId, String goodsInfoName, Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId, Pageable pageable,String goodsInfoAdded);


    /**
     * 获取具体库存预警商品实例
     */
    InventoryGoodsResult getWarningGood(long goodsInfoId);

    /**
     * 获取预警值
     */
    int getWarning();

    //按仓库查询
    Page<List<InventoryGoodsResult>> queryByInventory(Long enterpriseId, String goodsInfoName,
                                                      String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    //按货品查询
    Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                                String goodsNumber, Long brandId, Long thirdId, Pageable pageable);


    /*****************************************************************************************************************************/
    /*****************************************************请货，调拨，报损、报溢*****************************************************/
    /*****************************************************************************************************************************/

    /**
     * 根据ID获取单据。
     *
     * @param billId ID
     */
    InventoryBill getBillById(long billId);

    /**
     * 根据code获取单据。
     *
     * @param code 单据编号
     */
    InventoryBill getBillByCode(long code);

    /**
     * 获取创建的单据。
     */
    Page<InventoryBill> getCreatedBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                        BillType billType,Pageable pageable);

    /**
     * 获取已处理的单据。
     */
    Page<InventoryBill> getHandledBills(Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取待处理单据。
     */
    Page<InventoryBill> getUnHandleBills(Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                         BillType billType, Pageable pageable);

    /**
     * 创建单据
     *
     * @param type   单据类型
     * @param outId  出库点ID
     * @param inId   入库点ID
     * @param reason 申请原因
     * @param sku    商品SKU
     */
    void createBills(BillType type, Long outId, Long inId, Long applyId, String reason,List<InventoryBillItem> sku);
    void receiptBill(long billId, String comment, List<InventoryBillItem> sku);
    void createBill(BillType type, Long outId, Long inId,Long billId, String reason, Map<Long, Integer> sku);


    void createBillFromPurchaseBill(Long purchaseId,String reason);

    /**
     * 批准单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param amounts 审核数量表
     */
    void approveBill(long billId, String comment, Map<Long, Integer> amounts);


    void agreeBill(long billId,String comment, Map<Long, Integer> sku);
    /**
     * 同意单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param sku     商品SKU
     */
    void agreeBill(long billId,String comment, Map<Long, Integer> sku,Long enterpriseId);

    /**
     * 终止单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void terminateBill(long billId, String comment);

    /**
     * 完成单据
     *
     * @param billId 单据号
     */
    void finishBill(long billId);

    /**
     * 退回单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void backBill(long billId, String comment);

    /**
     * 提交单据（报损报溢单经过退回，修改后重新提交。）
     *
     * @param billId  单据ID
     * @param comment 流转意见
     * @param sku     货品SKU
     */
    void submitBill(long billId, String comment, Map<Long, Integer> sku);

    /**
     * 发货
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param sku     商品SKU
     */
    void deliveryBill(long billId, String comment, Map<Long, Integer> sku);

    /**
     * 收货
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void receiptBill(long billId, String comment,Map<Long,Integer> sku);


    /**
     *  更新库存单据读取状态
     *  @param read 是否已读
     *  @param billId 单据id
     *  *
     */
    void updateInventoryBill(Long billId,Boolean read);



    InventoryBill getBillDataFromReplementBill(Long billId);

    List<InventoryGoodsResult> getGoodsAndAvailable(Long enterpriseId,List<Long> goodsIds);


    /**
     * 根据补货单 数组Id获取合并单据详情
     * @param flag 0为获取详情，1为获取汇总
     * @param billIds
     * */
    List<InventoryMergeGoods> getMergeBillByBillIds(Long[] billIds, Integer flag);

    /**
     * 根据补货单 parentId 获取合并补货到详情
     * @param parentId
     * */
    List<InventoryMergeGoods> getMergeBillByParentId(Long parentId);

    /**
     * 创建合并后的补货单
     * @param status 补货单状态
     * @param billIds 合并补货单id
     * @param reason 原因
     * */
    long createMergeBill(BillStatus status,Long[] billIds,String reason);

    /**
     * 根据供应商创建采购单
     * @param parentId 合并补货单id
     * */
    void createPurchaseBill(Long parentId);


    /**
     * 获取采购单汇总信息
     * */
    InventoryPurchaseBill getSumDetailByPrimaryKey(Long purchaseId);

    /**
     * 获取采购单明细
     * */
    InventoryPurchaseBill getPurchaseDetailByKey(Long purchaseId);


    /**
     * 修改商品采购单价
     * @param purchaseId
     * @param goodsInfoId
     * @param settlePrice
     * */
    void updateSettlePrice(Long purchaseId,Long goodsInfoId,BigDecimal settlePrice);

    /**
     * 删除采购单商品
     * */
    void deleteByGoodsInfoId(@Param("purchaseId")Long purchaseId, @Param("goodsInfoIds")Long[] goodsInfoIds, @Param("itemIds")Long[] itemIds);

    /**
     * 修改单个网点采购数量
     * */
    void updateByPrimaryKey(@Param("itemId")Long itemId,@Param("amount")Integer amount);

    Page<InventoryPurchaseBill> getPurchaseBillList(@Param("code") Long code,
                                                    @Param("status") PurchaseBillStatus status,
                                                    @Param("start") Date start,
                                                    @Param("end") Date end,
                                                    @Param("thirdId") Long thirdId,
                                                    @Param("pageable") Pageable pageable);

    Map<String,Object> parsePurchaseFile(MultipartFile file) throws IOException, InvalidFormatException;

    List<InventoryBillItem> getMergeBillByExce(List<InventoryManageServiceImpl.PurchaseGoods> purchaseGoodses);

    void createPurchaseBIllByExcel(Map<String,Object> map,String marks);

    /**
     * 确认采购订单
     * */
    void confirmPurchaseBill(long purchaseId,String comment,String marks);

    /**
     * 终止采购订单
     * */
    void terminatePurchaseBill(long purchaseId,String comment);

    Map<Long, Map<Long, InventoryManageServiceImpl.PurchaseGoods>> getExcelData(Map<String, Object> map);

    /**
     * 判断合并补货单是否生成采购单
     * @return true已生成采购单 false为生成采购单
     * */
    Boolean judgePurchaseByBillId(Long billId);

    /**
     * 采购订单生成采购入库单
     * */
    void createStorageBill(Long purchaseId,String reason);

    /**
     * 采购单待商品入库
     * */
    void pendingPurchaseBill(long purchaseId, String comment);

    /**
     * 采购单修改入库数量
     * */
//    void confirmStorage(Long billId,Map<Long, Integer> sku,String comment);
    void confirmStorage(Long billId,List<InventoryBillItem> sku,String comment);

    /**
     * 结算采购订单
     * */
    void settlementPurchase(Long purchaseId,String comment);

    /**
     * 强制结算
     * @param purchaseId
     */
    void forceFinishBill(Long purchaseId);

    void formCreateStorageBill(Long enterpriseId, List<InventoryManageAPIController.StorageGoodsInfo> goodsInfos, String reason);

    void createPurchaseBillByBillid(Long billId);




	/**
	 * 按条件查询采购订单
	 * @param code 单据编号
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param status 单据状态
	 * @param thirdId 第三方id
	 * @return
	 * */
	List<InventoryPurchaseBillItem> selectPurchaseBillListByCondition(@Param("code") Long code,
	                                                                  @Param("status") PurchaseBillStatus status,
	                                                                  @Param("start") Date start,
	                                                                  @Param("end") Date end,
	                                                                  @Param("thirdId") Long thirdId);


}
