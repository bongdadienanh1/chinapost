package com.ylife.inventory.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/14.
 * 库存事务单据服务
 */
public interface InventoryBillService {

    /**
     * 根据ID获取单据。
     *
     * @param id ID
     */
    InventoryBill getBillById(long id);

    /**
     * 根据ID获取单据。
     *
     * @param id ID
     */
    List<InventoryBillLog> getBillLogById(long id);

    /**
     * 根据code获取单据。
     *
     * @param code 单据编号
     */
    InventoryBill getBillByCode(long code);

    /**
     * 获取创建的单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getCreatedBills(long enterpriseId, Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取已处理的单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getHandledBills(long enterpriseId, Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取待处理单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getUnHandleBills(long enterpriseId, Long code, String creatorName, Date start, Date end,
                                         BillStatus billStatus,
                                         BillType billType, Pageable pageable);

    /**
     * 创建单据
     *
     * @param creatorId      创建人ID
     * @param type           单据类型
     * @param nextOperatorId 下一个操作者ID
     * @param outId          出库点ID
     * @param inId           入库点ID
     * @param reason         申请原因
     * @param sku            商品SKU
     */
    long createBill(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId,Long applyId, String reason, Map<Long, Integer> sku);

    /**
     * 创建单据
     *
     * @param creatorId      创建人ID
     * @param type           单据类型
     * @param nextOperatorId 下一个操作者ID
     * @param outId          出库点ID
     * @param inId           入库点ID
     * @param reason         申请原因
     * @param sku            商品SKU
     */
    long createBills(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId,Long applyId, String reason, List<InventoryBillItem> sku);
    /**
     * 批准单据
     *
     * @param id             ID
     * @param nextOperatorId 下一个操作者ID
     * @param amounts        审核数量
     */
    void approveBill(long id, Long nextOperatorId, Map<Long, Integer> amounts);

    /**
     * zx
     * @param id
     * @param isTop
     * @param sku
     */
    void agreeBill(long id, Boolean isTop, Map<Long, Integer> sku);

    /**
     * zx
     * @param id
     * @param enterpriseId
     * @param isTop
     * @param sku
     */
    void agreeBill(long id,Boolean isTop, Map<Long, Integer> sku,Long enterpriseId);
    /**
     * 终止单据
     *
     * @param id ID
     */
    void terminateBill(long id);

    /**
     * 完成单据
     *
     * @param id ID
     */
    void finishBill(long id);

    /**
     * 退回单据
     *
     * @param id ID
     */
    void backBill(long id);

    /**
     * 提交单据（报损报溢单经过退回，修改后重新提交。）
     *
     * @param id             单据ID
     * @param nextOperatorId 下一个操作者ID
     * @param sku            货品SKU
     */
    void submitBill(long id, Long nextOperatorId, Map<Long, Integer> sku);

    /**
     * 发货
     *
     * @param id  ID
     * @param sku 商品SKU
     */
    void deliveryBill(long id, Map<Long, Integer> sku);

    /**
     * 收货
     *
     * @param id ID
     */
    Boolean receiptBill(long id,List<InventoryBillItem> sku);


    void updateByPrimaryKeySelective(InventoryBill inventoryBill);

    InventoryBill getBillDataFromReplementBill(Long outId,Long billId);

    /**
     * 创建合并后的补货单
     * */
    long createMergeBill(long creatorId,BillStatus status, List<InventoryMergeGoods> inventoryMergeGoodses,String reason);


    /**
     * 根据parentId 获取补货单 id
     * @param parentId
     * */
    List<Long> getBillIdByParentId(long parentId);

    /**
     * 根据补货单 数组Id获取合并单据详情
     * @param billIds
     * */
    List<InventoryMergeGoods> getBillDetailByBillIds(Long[] billIds);

    /**
     *根据bill数组获取汇总单据汇总信息
     * @param billIds
     * @return
     */
    List<InventoryMergeGoods> getBillSumByBillIds(Long[] billIds);

    /**
     * 采购订单生成采购入库单
     * */
    void createStorageBill(Map<Long,List<InventoryPurchaseBillItem>> itemMap, Long purchaseId, Long creatorId, String reason);

    /**
     * 根据采购入库单获取采购订单Id
     * */
    Long getPurchaseIdByBillId(long billId);

    /**
     * 根据采购单id获取采购入库单
     * */
    List<InventoryBill> getInventoryBillByPurchaseId(Long purchaseId);

    /**
     * 采购单强制结算
     * */
    void forceFinishBill(Long billId);

    int updateByPrimaryKey(InventoryBill record);

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
     * @param pageable      分页
     * @param overday        到期提醒方式
     * @return
     */
    List<WarnDay> selectWarnDay(long minCatalog,long maxCatalog,Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code,Pageable  pageable,String overday,String type,long catalog);

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
    int slectWarnDaySum(long minCatalog,long maxCatalog,Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code,String overday,String type,long catalog);

    /**
     *
     * @param id   到期预警id
     * @return
     */
    int deleteWarnDay(long id);

    /**
     *
     * @param inventoryBillItemDetail  更新到期时间   入库时间   保质期
     * @return
     */
    int updateWarnDay(InventoryBillItemDetail inventoryBillItemDetail);




}
