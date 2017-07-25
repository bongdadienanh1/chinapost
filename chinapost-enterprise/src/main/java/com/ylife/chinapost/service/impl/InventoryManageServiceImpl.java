package com.ylife.chinapost.service.impl;


import com.ylife.chinapost.controller.api.InventoryManageAPIController;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.DataAccessService;
import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;
import com.ylife.inventory.service.*;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.Assert;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by XuKai on 2016/4/21.
 * InventoryManageServiceImpl
 */
@Service
public class InventoryManageServiceImpl implements InventoryManageService {

    @Resource
    private InventoryService inventoryService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private InventoryBillService inventoryBillService;
    @Resource
    private InventoryBillHistoryService inventoryBillHistoryService;
    @Resource
    private EnterpriseMapper enterpriseMapper;
	@Resource
	private InventoryBillMapper inventoryBillMapper;
    @Resource
    private DataAccessService dataAccessService;
    @Resource
    private InventoryBillLogService inventoryBillLogService;
    @Resource
    private InventoryPurchaseBillLogService inventoryPurchaseBillLogService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private InventoryPurchaseBillService inventoryPurchaseBillService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    private static final String ACCOUNT_NAME = "机构编号";
    private static final int ACCOUNT_SERIAL = 0;
    private static final String GOODS_NUMBER = "货品编号";
    private static final int GOODS_NUMBER_SERIAL = 1;
    private static final String GOODS_NAME = "货品名称";
    private static final int GOODS_NAME_SERIAL = 2;
    private static final String PURCHASE_PRICE = "采购单价";
    private static final int PURCHASE_PRICE_SERIAL = 3;
    private static final String PURCHASE_AMOUNT = "采购数量";
    private static final int PURCHASE_AMOUNT_SERIAL = 4;

    private static final int EXCEL_HEADS_LENGTH = 5;
    private static final int ERROR_LIMIT = 100;

    @Override
    public Page<WarnDay> selectWarnDay(long minCatalog,long maxCatalog,Date endStartDate, Date endEndDate, Date endDate, Date startDate, Integer day, String goodsInfoItemNo, Long code, Pageable pageable,String overday,String type,long catalog) {
        List<WarnDay> warnDayList = inventoryBillService.selectWarnDay(minCatalog, maxCatalog, endStartDate, endEndDate, endDate, startDate, day, goodsInfoItemNo, code, pageable, overday,type,catalog);
        int warnDayListSum = inventoryBillService.slectWarnDaySum(minCatalog, maxCatalog, endStartDate, endEndDate, endDate, startDate, day, goodsInfoItemNo, code, overday,type,catalog);

        return new PageImpl<>(pageable, warnDayListSum, warnDayList);

    }


    @Override
    public  List<InventoryBillLog> getBillLog(long billId) {
        return inventoryBillService.getBillLogById(billId);
    }

    @Override
    public int deleteWarnDay(long id) {
        return inventoryBillService.deleteWarnDay(id);
    }

    @Override
    public Inventory selectByGoodsInfoId(Long goodsInfoId) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryService.selectByPrimaryKey(new InventoryKey(enterpriseId, goodsInfoId));
    }

    @Override
    public int updateWarnDay(InventoryBillItemDetail inventoryBillItemDetail) {
        return inventoryBillService.updateWarnDay(inventoryBillItemDetail);
    }

    @Override
    public int editWarning(Long goodsInfoId, Integer goodsInfoWarning) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Inventory inventory = new Inventory();
        inventory.setGoodsId(goodsInfoId);
        inventory.setGoodsInfoWarning(goodsInfoWarning);
        inventory.setEnterpriseId(enterpriseId);
        return inventoryService.updateByPrimaryKeySelective(inventory);
    }




    @Override
    public Page<InventoryGoodsResult> getInventoryGoodsResult(String goodsName, String goodsNumber, Long brandId, Long thirdId, Integer goodsInfoType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectInventoryGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,null,null, pageable);
        int totalElements = inventoryMapper.countByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,null,null);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName, Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectInventoryGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,null,null, pageable);
        int totalElements = inventoryMapper.countByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId, goodsInfoType,null,null);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public InventoryGoodsResult getInventoryGoodsResultByPrimarykey(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        return inventoryMapper.selectInventoryGoodsResultByPrimarykey(key);
    }

    @Override
    public Map<String, Object> sonEnterprise() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return getMap(enterpriseId);
    }

    public Map<String, Object> getMap(long enterpriseId) {
        Enterprise info = enterpriseService.getEnterprise(enterpriseId);
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", info.getId());
        map.put("name", info.getEnterpriseName());
        map.put("end", function.getEnd());
        List<Enterprise> sons = enterpriseMapper.selectByParentId(enterpriseId);
        List<Map> sonsMaps = new ArrayList<>();
        for (Enterprise enterprise : sons) {
            sonsMaps.add(getMap(enterprise.getId()));
        }
        map.put("sons", sonsMaps);
        return map;
    }

    @Override
    public Page<InventoryGoodsResult> getGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectGoods(goodsName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countGoodsInfoId(goodsName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public void addInventory(long goodsInfoId, int inventory) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        inventoryService.addActuallyInventory(key, inventory);
    }

    @Override
    public void editInventory(long goodsInfoId, int inventory) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.editInventory(enterpriseId, goodsInfoId, inventory);
    }


    @Override
    public void editAvailable(long goodsInfoId, int available) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.setAvailable(enterpriseId, goodsInfoId, available);
    }

    @Override
    public void deleteGoods(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.deleteGood(enterpriseId, goodsInfoId);
    }

    @Override
    public void addGoods(Map<Long, Integer> goods) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        //创建入库单
        Long billId = inventoryBillService.createBill(enterpriseId, BillType.INVENTORY_INSTOCK, null, null, enterpriseId, null, "入库", goods);
        //操作库存，并记录库存变动
        InventoryBill bill = inventoryBillService.getBillById(billId);
        inventoryService.setGoods(enterpriseId, goods, bill.getCode());
    }

    @Override
    public void addGoodss(List<InventoryBillItem> goods) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        //创建入库单
        Long billId = inventoryBillService.createBills(enterpriseId, BillType.INVENTORY_INSTOCK, null, null, enterpriseId, null, "入库", goods);
        //操作库存，并记录库存变动
        InventoryBill bill = inventoryBillService.getBillById(billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
        inventoryService.setGoodss(enterpriseId, goods, bill.getCode());
    }

    @Override
    public void setWarning(int warning) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        enterpriseService.setInventoryForewarn(enterpriseId, warning);
    }

    @Override
    public int getWarning() {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        return function.getInventoryForewarn() == null ? 0 : function.getInventoryForewarn();
    }

    @Override
    public Page<InventoryGoodsResult> getWarningGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryService.getWarningGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, pageable);
        return inventoryGoodsResultPage;
    }

    @Override
    public InventoryGoodsResult getWarningGood(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        return inventoryMapper.selectWarningGood(key);
    }

    @Override
    public Page<InventoryGoodsResult> getGoodsAndInventory(Long enterpriseId, Long goodsInfoId, String goodsInfoName, Integer goodsInfoType, String goodsNumber, Long brandId, Long thirdId, Pageable pageable,String goodsInfoAdded) {
        return inventoryService.getGoodsInventory(enterpriseId, goodsInfoId, goodsInfoName, goodsInfoType, goodsNumber, brandId, thirdId, pageable,goodsInfoAdded);
    }

    @Override
    public Page<List<InventoryGoodsResult>> queryByInventory(Long enterpriseId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        Page<InventoryGoodsResult> resultPage = inventoryService.queryByInventory(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
        List<List<InventoryGoodsResult>> list = new ArrayList<>();
        long eId = -1L;
        List<InventoryGoodsResult> sonList = new ArrayList<>();
        for (InventoryGoodsResult result : resultPage.getContent()) {
            if (result.getEnterpriseId() == eId) {
                sonList.add(result);
            } else {
                if (sonList.size() != 0) {
                    list.add(sonList);
                }
                eId = result.getEnterpriseId();
                sonList = new ArrayList<>();
                sonList.add(result);
            }
        }
        list.add(sonList);
        return new PageImpl<>(pageable, resultPage.getTotalElements(), list);
    }

    @Override
    public Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        return inventoryService.queryByGoodsInfo(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
    }

    /*****************************************************************************************************************************/
    /*****************************************************请货，调拨，报损、报溢*****************************************************/
    /*****************************************************************************************************************************/

    /***/

    @Override
     public InventoryBill getBillById(long billId) {
        return inventoryBillService.getBillById(billId);
    }


    @Override
    public InventoryBill getBillByCode(long code) {
        return inventoryBillService.getBillByCode(code);
    }

    @Override
    public Page<InventoryBill> getCreatedBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getCreatedBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }

    @Override
    public Page<InventoryBill> getHandledBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getHandledBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }

    @Override
    public Page<InventoryBill> getUnHandleBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                                BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getUnHandleBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }
    @Override
    @Transactional
    public void createBills(BillType type, Long outId, Long inId, Long applyId, String reason,List<InventoryBillItem> sku) {
        Assert.notNull(type);
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        Long currentId = null;
        Long parentId = function.getParentId();
        Map<Long, Map<Long, Integer>> groupBythirdIdMap = new HashMap<>();
        Map<Long, Integer> skuSonMap = new HashMap<>();

        switch (type) {
            case INVENTORY_TRANSFER:
                if (parentId == null) {
                    outId = function.getId();
                }
                for (int i = 0; i < sku.size(); i++) {
                    long goodsInfoId = sku.get(i).getGoodsInfoId();
                    InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(outId, goodsInfoId));

                    if (inventoryGoodsResult == null || inventoryGoodsResult.getAvailable() < sku.get(i).getAmount()) {
                        throw new UserOperationException("调拨数量不能大于该商品的库存量！！");
                    }
                }
                if (parentId == null) {
                    for (int i = 0; i < sku.size(); i++) {
                        long goodsInfoId = sku.get(i).getGoodsInfoId();
                        InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(outId, goodsInfoId));
                        Long thirdId = inventoryGoodsResult.getThirdId();
                        if (groupBythirdIdMap.containsKey(thirdId)) {
                            skuSonMap = groupBythirdIdMap.get(thirdId);
                            skuSonMap.put(goodsInfoId, sku.get(i).getAmount());
                        } else {
                            Map<Long, Integer> thirdSonMap = new HashMap<>();
                            thirdSonMap.put(goodsInfoId, sku.get(i).getAmount());
                            groupBythirdIdMap.put(thirdId, thirdSonMap);
                        }
                    }
                }
                break;



        }
        Long billId = inventoryBillService.createBills(function.getId(), type, currentId, outId, inId, applyId, reason, sku);
        inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
   }

    /***
     * zx
     * @param type
     * @param outId
     * @param inId
     * @param applyId
     * @param reason
     * @param sku
     */
    @Override
    @Transactional
    public void createBill(BillType type, Long outId, Long inId, Long applyId, String reason, Map<Long, Integer> sku) {
        Assert.notNull(type);
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        Long currentId = null;
        Long parentId = function.getParentId();
        Map<Long, Map<Long, Integer>> groupBythirdIdMap = new HashMap<>();
        Map<Long, Integer> skuSonMap = new HashMap<>();
        switch (type) {
            case REPLENISHMENT:
                Assert.notNull(parentId, "当前账号无法创建此类型单据。");
                currentId = parentId;
                for (Long goodsInfoId : sku.keySet()) {
                    GoodsInfo goodsInfo = goodsInfoService.getById(goodsInfoId);
                    if (goodsInfo.getGoodsInfoPack() != null) {
                        if (new BigDecimal(sku.get(goodsInfoId)).divideAndRemainder(goodsInfo.getGoodsInfoPack())[1].compareTo(BigDecimal.ZERO) != 0) {
                            throw new UserOperationException("申请数量必须是装箱数的整数倍！");
                        }
                    }
                }
            case MORE_REPORT:
                Assert.notNull(parentId, "当前账号无法创建此类型单据。");
                currentId = parentId;
                break;
            case LESS_REPORT:
                Assert.notNull(parentId, "当前账号无法创建此类型单据。");
                currentId = parentId;
                for (Long goodsInfoId : sku.keySet()) {
                    InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(enterpriseId, goodsInfoId));
                    if (inventoryGoodsResult == null) {
                        throw new UserOperationException("有货品库存为0，无需报损！");
                    }
                    if (inventoryGoodsResult.getAvailable() < sku.get(goodsInfoId)) {
                        throw new UserOperationException("报损数量不能大于该商品库存量");
                    }
                }
                break;
            case INVENTORY_TRANSFER:
                if (parentId == null) {
                    outId = function.getId();
                }
                for (Long goodsInfoId : sku.keySet()) {
                    InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(outId, goodsInfoId));

                    if (inventoryGoodsResult == null || inventoryGoodsResult.getAvailable() < sku.get(goodsInfoId)) {
                        throw new UserOperationException("调拨数量不能大于该商品的库存量！！");
                    }
                }
	            // 2017/7/14 顶级账号创建调拨单不需要以供应商拆分
                /*if (parentId == null) {
                    for (Long goodsInfoId : sku.keySet()) {
                        InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(outId, goodsInfoId));
                        Long thirdId = inventoryGoodsResult.getThirdId();
                        if (groupBythirdIdMap.containsKey(thirdId)) {
                            skuSonMap = groupBythirdIdMap.get(thirdId);
                            skuSonMap.put(goodsInfoId, sku.get(goodsInfoId));
                        } else {
                            Map<Long, Integer> thirdSonMap = new HashMap<>();
                            thirdSonMap.put(goodsInfoId, sku.get(goodsInfoId));
                            groupBythirdIdMap.put(thirdId, thirdSonMap);
                        }
                    }
                }*/
                break;
            case INVENTORY_INSTOCK:

        }


        if (groupBythirdIdMap.size() > 0) {
            for (Long thirdId : groupBythirdIdMap.keySet()) {
                Long billId = inventoryBillService.createBill(function.getId(), type, currentId, outId, inId, applyId, reason, groupBythirdIdMap.get(thirdId));
                inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
            }
        } else {
            Long billId = inventoryBillService.createBill(function.getId(), type, currentId, outId, inId, applyId, reason, sku);
            inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
        }
        //将生成调拨单改为已完成状态
        if (applyId!=null){
           long enterId = currentLoginService.getCurrentLoginEnterpriseId();
            InventoryBill newInev=inventoryBillService.getBillById(applyId);
            newInev.setBillStatus(BillStatus.FINISHED);
            newInev.setCurrentId(null);
            inventoryBillService.updateByPrimaryKey(newInev);
            inventoryBillHistoryService.operate(enterpriseId, applyId);
        }



    }




    @Override
    public void createBillFromPurchaseBill(Long purchaseId, String reason) {


    }

    @Override
    @Transactional
    public void approveBill(long billId, String comment, Map<Long, Integer> amounts) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.approveBill(billId, enterpriseService.getTopEnterprise().getId(), amounts);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[审核]");
    }

    @Override
    public void agreeBill(long billId, String comment, Map<Long, Integer> sku, Long enterpriseId) {

    }

    /**
     * zx
     * @param billId  单据号
     * @param comment 流转意见
     * @param sku     商品SKU
     */
    @Override
    @Transactional
    public void agreeBill(long billId, String comment, Map<Long, Integer> sku) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.agreeBill(billId, currentLoginService.isPrimaryEnterprise(), sku,enterpriseId);
        InventoryBill newInev=inventoryBillService.getBillById(billId);
      if (newInev.getBillStatus()==BillStatus.CHECKED_NOT_TRANSFER  || newInev.getBillStatus()== BillStatus.CHECKED_NOT_PURCHASE){
          /*  inventoryBillHistoryService.operate(enterpriseId, billId);*/
          inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[同意]"); //记录审核流程
        }else{
          inventoryBillHistoryService.operate(enterpriseId, billId); //在已办事宜中添加一条记录
          inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[同意]");
      }
       /* inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[同意]");*/
    }

    @Override
    @Transactional
    public void terminateBill(long billId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
	    InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(billId);
	    if(bill.getBillStatus() != BillStatus.PARTIAL_RECEIPT) {
		    dataAccessService.enterpriseAcessBill(enterpriseId, billId);
	    }
        inventoryBillService.terminateBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[终止]");
    }

    @Override
    @Transactional
    public void finishBill(long billId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.finishBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[完成]");
    }


    @Override
    @Transactional
    public void backBill(long billId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.backBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[退回]");
    }

    @Override
    @Transactional
    public void submitBill(long billId, String comment, Map<Long, Integer> sku) {
        EnterpriseFunction enterpriseFunction = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = enterpriseFunction.getId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.submitBill(billId, enterpriseFunction.getParentId(), sku);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[提交]");
    }

    @Override
    @Transactional
    public void deliveryBill(long billId, String comment, Map<Long, Integer> sku) {
        Map<String, String> errorMap = new HashMap<>();
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        try {
            inventoryBillService.deliveryBill(billId, sku);
        } catch (UserOperationException e) {
            if (errorMap.size() < 100) {
                List<Long> errorList = (List<Long>) e.getExObj();
                for (Long id : errorList) {
                    GoodsInfo goodsInfo = goodsInfoService.getById(id);
                    errorMap.put(goodsInfo.getGoodsInfoName().concat("(").concat(goodsInfo.getGoodsInfoItemNo()).concat(")"), "库存不足");
                }

            }
            throw new UserOperationException(errorMap);
        }
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[发货]");
    }

    @Override
    public void receiptBill(long billId, String comment, Map<Long, Integer> sku) {

    }


    @Override
    @Transactional
    public void receiptBill(long billId, String comment, List<InventoryBillItem> sku) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        Boolean isHandled = inventoryBillService.receiptBill(billId, sku);
        if (isHandled) {
            inventoryBillHistoryService.operate(enterpriseId, billId);
            inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[收货]");
        } else {
	        inventoryBillHistoryService.operate(enterpriseId, billId);
            inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[部分收货]");
        }
    }


    @Override
    public void updateInventoryBill(Long billId, Boolean read) {
        InventoryBill bill = inventoryBillService.getBillById(billId);
        bill.setReadFlag(read);
        inventoryBillService.updateByPrimaryKeySelective(bill);
    }

    @Override
    public InventoryBill getBillDataFromReplementBill(Long billId) {
        Long outId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getBillDataFromReplementBill(outId, billId);
    }

    @Override
    public List<InventoryGoodsResult> getGoodsAndAvailable(Long enterpriseId, List<Long> goodsIds) {
        return inventoryService.selectGoodsAndAvailable(enterpriseId, goodsIds);
    }

    @Override
    public List<InventoryMergeGoods> getMergeBillByParentId(Long parentId) {
        List<Long> billIds = inventoryBillService.getBillIdByParentId(parentId);
        final int size = billIds.size();
        Long[] ids = billIds.toArray(new Long[size]);
        return inventoryBillService.getBillDetailByBillIds(ids);
    }


//    private List<InventoryBillItem> mergeBill(List<InventoryBill> bills) {
//        Map<Long, InventoryBillItem> goodsItem = new LinkedHashMap<>();
//        for (InventoryBill inventoryBill : bills) {
//            for (InventoryBillItem inventoryBillItem : inventoryBill.getItems()) {
//                Long goodsInfoId = inventoryBillItem.getGoodsInfoId();
//
//                boolean contains = goodsItem.containsKey(goodsInfoId);
//                if (contains) {
//                    InventoryBillItem thereInventoryBillItem = goodsItem.get(goodsInfoId);
//                    Integer checkedAmount = thereInventoryBillItem.getCheckedAmount() + inventoryBillItem.getCheckedAmount();
//                    Integer amount = thereInventoryBillItem.getAmount() + inventoryBillItem.getAmount();
//                    thereInventoryBillItem.setAmount(amount);
//                    thereInventoryBillItem.setCheckedAmount(checkedAmount);
//                    goodsItem.put(goodsInfoId, thereInventoryBillItem);
//                } else {
//                    goodsItem.put(goodsInfoId, inventoryBillItem);
//                }
//            }
//        }
//        Collection<InventoryBillItem> items = goodsItem.values();
//        List<InventoryBillItem> itemsList = new ArrayList<InventoryBillItem>(items);
//        return itemsList;
//    }

    @Override
    @Transactional
    public List<InventoryMergeGoods> getMergeBillByBillIds(Long[] billIds, Integer flag) {


        if (flag == 0) {
            return inventoryBillService.getBillDetailByBillIds(billIds);
        } else {
            return inventoryBillService.getBillSumByBillIds(billIds);
        }
//        List<InventoryBill> inventoryBillList = inventoryBillService.getBillDetailByBillIds(billIds);

//        Map<Long, List<InventoryBill>> inventoryBillMap = new HashMap<>();
//        List<InventoryBill> thereInventoryBill;
//
//        for (InventoryBill inventoryBill : inventoryBillList) {
//            Long creatorId = inventoryBill.getCreatorId();
//            if (inventoryBillMap.containsKey(creatorId)) {
//                thereInventoryBill = inventoryBillMap.get(creatorId);
//                thereInventoryBill.add(inventoryBill);
//            } else {
//                thereInventoryBill = new ArrayList<>();
//                thereInventoryBill.add(inventoryBill);
//            }
//            inventoryBillMap.put(creatorId, thereInventoryBill);
//
//        }
//
//
//        Map<String, List<InventoryBillItem>> goodsByEnterpriseIdMap = new HashMap<>();
//        for (Long creatorId : inventoryBillMap.keySet()) {
//            List<InventoryBill> bills = inventoryBillMap.get(creatorId);
//            List<InventoryBillItem> sonList = mergeBill(bills);
//            String enterpriseName = enterpriseService.getEnterprise(creatorId).getEnterpriseName();
//            goodsByEnterpriseIdMap.put(enterpriseName, sonList);
//        }
//
//        return goodsByEnterpriseIdMap;
    }

    /**
     * zx
     * @param status 补货单状态
     * @param billIds 合并补货单id
     * @param reason 原因
     * @return
     */

    @Override
    @Transactional
    public long createMergeBill(BillStatus status, Long[] billIds, String reason) {
        if (status == BillStatus.FINISHED || status == BillStatus.TERMINATED) {
            for (Long id : billIds) {
                InventoryBill bill = inventoryBillService.getBillById(id);
                if (bill.getParentId() != null) {
                    throw new UserOperationException("有单据合并过！");
                }
            }
            List<InventoryMergeGoods> inventoryMergeGoodses = inventoryBillService.getBillSumByBillIds(billIds);
            long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
            long billId = inventoryBillService.createMergeBill(enterpriseId, status, inventoryMergeGoodses, reason);
            for (long sonBillId : billIds) {
                InventoryBill inventoryBill = inventoryBillService.getBillById(sonBillId);
                inventoryBill.setBillId(sonBillId);
                inventoryBill.setCurrentId(null);
                if (status== BillStatus.FINISHED){ //只有是完成的按钮 才改成 已同意未采购的状态
                    //该处修改
                    inventoryBill.setBillStatus(BillStatus.CHECKED_NOT_PURCHASE);
                }else{
                    inventoryBill.setBillStatus(status);
                }
                inventoryBill.setParentId(billId);
                inventoryBillService.updateByPrimaryKey(inventoryBill);
              /*  inventoryBillHistoryService.operate(enterpriseId, billId);*/
            }
            InventoryBill inventoryBill = inventoryBillService.getBillById(billId);
            if (inventoryBill.getBillStatus()==BillStatus.TERMINATED){
                inventoryBill.setCurrentId(null);
            }else{
                inventoryBill.setCurrentId(enterpriseId);
            }
            inventoryBillService.updateByPrimaryKey(inventoryBill);
          /*  && inventoryBill.getBillStatus()!=BillStatus.TERMINATED*/
            if(inventoryBill.getBillStatus()!=BillStatus.CHECKED_NOT_PURCHASE ){
                inventoryBillHistoryService.operate(enterpriseId, billId);
            }
            inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
            return billId;
        } else {
            throw new UserOperationException("不能合并此类型单据！！");
        }
    }

    /**
     * zx
     * @param parentId 合并补货单id
     */

    @Override
    @Transactional
    public void createPurchaseBill(Long parentId) {
        long currentId = currentLoginService.getCurrentLoginEnterpriseId();
        if (currentId == 1) {
            List<InventoryMergeGoods> inventoryMergeGoodses = getMergeBillByParentId(parentId);
            if (inventoryPurchaseBillService.getPurchaseCountByBillId(parentId) > 0) {
                throw new UserOperationException("该补货单已创建采购单！！");
            } else {

                Map<Long, List<InventoryPurchaseBillItem>> groupByThirdMap = new HashMap<>();
                for (InventoryMergeGoods mergeGoods : inventoryMergeGoodses) {
                    Long enterpriseId = mergeGoods.getEnterpriseId();
                    //循环获取商品详情
                    for (InventoryBillItem inventoryBillItem : mergeGoods.getItems()) {
                        if (inventoryBillItem.getAmount() == null) {
                            throw new UserOperationException("数量不能为空！！");
                        }
                        List<InventoryPurchaseBillItem> purchaseBillItemList = new ArrayList<>();
                        InventoryPurchaseBillItem inventoryPurchaseBillItem = new InventoryPurchaseBillItem();
                        Long thirdId = inventoryBillItem.getInfo().getThirdId();
                        BigDecimal settlePrice = inventoryBillItem.getSettlePrice();
                        BigDecimal cacuSettlePrice = settlePrice == null ? BigDecimal.ZERO : settlePrice;
                        //根据thirdId分组
                        if (groupByThirdMap.containsKey(thirdId)) {
                            purchaseBillItemList = groupByThirdMap.get(thirdId);
                            inventoryPurchaseBillItem.setEnterpriseId(enterpriseId);
                            inventoryPurchaseBillItem.setGoodsinfoId(inventoryBillItem.getGoodsInfoId());
                            inventoryPurchaseBillItem.setAmount(inventoryBillItem.getCheckedAmount());
                            inventoryPurchaseBillItem.setSettlePrice(settlePrice);
                            inventoryPurchaseBillItem.setTotalPrice(cacuSettlePrice.multiply(new BigDecimal(inventoryBillItem.getAmount())));
                            purchaseBillItemList.add(inventoryPurchaseBillItem);
                            groupByThirdMap.put(thirdId, purchaseBillItemList);
                        } else {
                            inventoryPurchaseBillItem.setEnterpriseId(enterpriseId);
                            inventoryPurchaseBillItem.setGoodsinfoId(inventoryBillItem.getGoodsInfoId());
                            inventoryPurchaseBillItem.setAmount(inventoryBillItem.getCheckedAmount());
                            inventoryPurchaseBillItem.setSettlePrice(settlePrice);
                            inventoryPurchaseBillItem.setTotalPrice(cacuSettlePrice.multiply(new BigDecimal(inventoryBillItem.getAmount())));
                            purchaseBillItemList.add(inventoryPurchaseBillItem);
                            groupByThirdMap.put(thirdId, purchaseBillItemList);
                        }
                    }
                }
                inventoryPurchaseBillService.createPurchaseBill(groupByThirdMap, parentId, currentId, null);
               if(parentId!=null){
                   //修改顶级的合并补货单的状态
                   /* InventoryBill newInev=inventoryBillService.getBillById(parentId);*/
                    InventoryBill newInev=new InventoryBill();
                    newInev.setBillId(parentId);
                    newInev.setBillStatus(BillStatus.FINISHED);
                    newInev.setCurrentId(null); //未nuLL代办事宜不显示
                    inventoryBillService.updateByPrimaryKeySelective(newInev);
                    inventoryBillHistoryService.operate(currentId, parentId);
                    //修改网点的合并补货单下的子单的状态  一个顶级的合并补货单 下有多个网点的补货单
                    List<Long> childBillIds = inventoryBillService.getBillIdByParentId(parentId);
                    for(Long billId : childBillIds){
                        InventoryBill item = new InventoryBill();
                        item.setBillId(billId);
                        item.setBillStatus(BillStatus.FINISHED);
                        inventoryBillService.updateByPrimaryKeySelective(item);
                        inventoryBillHistoryService.operate(currentId, parentId);
                    }
               }
            }

        }
    }

    /**
     * zx
     * @param billId
     */

    @Override
    @Transactional
    public void createPurchaseBillByBillid(Long billId) {
        long currentId = currentLoginService.getCurrentLoginEnterpriseId();
        if (currentId == 1) {
            InventoryBill bill = getBillById(billId);
            if (inventoryPurchaseBillService.getPurchaseCountByBillId(billId) > 0) {
                throw new UserOperationException("该补货单已创建采购单！！");
            } else {
                Long enterpriseId = bill.getCreatorId();
                Map<Long, List<InventoryPurchaseBillItem>> groupByThirdMap = new HashMap<>();
                for (InventoryBillItem item : bill.getItems()) {
                    if (item.getAmount() == null) {
                        throw new UserOperationException("数量不能为空！！");
                    }
                    List<InventoryPurchaseBillItem> purchaseBillItemList = new ArrayList<>();
                    InventoryPurchaseBillItem inventoryPurchaseBillItem = new InventoryPurchaseBillItem();
                    Long thirdId = item.getInfo().getThirdId();
                    BigDecimal settlePrice = item.getSettlePrice();
                    BigDecimal cacuSettlePrice = settlePrice == null ? BigDecimal.ZERO : settlePrice;
                    if (groupByThirdMap.containsKey(thirdId)) {
                        purchaseBillItemList = groupByThirdMap.get(thirdId);
                        inventoryPurchaseBillItem.setEnterpriseId(enterpriseId);
                        inventoryPurchaseBillItem.setGoodsinfoId(item.getGoodsInfoId());
                        inventoryPurchaseBillItem.setAmount(item.getCheckedAmount());
                        inventoryPurchaseBillItem.setSettlePrice(settlePrice);
                        inventoryPurchaseBillItem.setTotalPrice(cacuSettlePrice.multiply(new BigDecimal(item.getAmount())));
                        purchaseBillItemList.add(inventoryPurchaseBillItem);
                        groupByThirdMap.put(thirdId, purchaseBillItemList);
                    } else {
                        inventoryPurchaseBillItem.setEnterpriseId(enterpriseId);
                        inventoryPurchaseBillItem.setGoodsinfoId(item.getGoodsInfoId());
                        inventoryPurchaseBillItem.setAmount(item.getCheckedAmount());
                        inventoryPurchaseBillItem.setSettlePrice(settlePrice);
                        inventoryPurchaseBillItem.setTotalPrice(cacuSettlePrice.multiply(new BigDecimal(item.getAmount())));
                        purchaseBillItemList.add(inventoryPurchaseBillItem);
                        groupByThirdMap.put(thirdId, purchaseBillItemList);
                    }
                }
                inventoryPurchaseBillService.createPurchaseBill(groupByThirdMap, billId, currentId, null);
                if (billId!=null){
                 /* long enterId = currentLoginService.getCurrentLoginEnterpriseId();*/
                    InventoryBill newInev=inventoryBillService.getBillById(billId);
                    newInev.setBillStatus(BillStatus.FINISHED);
                    newInev.setCurrentId(null);
                    inventoryBillService.updateByPrimaryKey(newInev);
                    inventoryBillHistoryService.operate(currentId, billId);
                }
            }

        }
    }

	//	按条件查询采购订单
	@Override
	public List<InventoryPurchaseBillItem> selectPurchaseBillListByCondition(@Param("code") Long code, @Param("status") PurchaseBillStatus status, @Param("start") Date start, @Param("end") Date end, @Param("thirdId") Long thirdId) {
		return inventoryPurchaseBillService.selectPurchaseBillListByCondition(code, status, start, end, thirdId);
	}

	//企业信息的缓存map key：机构编号 value:网点信息
    private static final Map<String, EnterpriseInfo> enterpriseInfomap = new ConcurrentHashMap<>();
    //商品信息的缓存map key：货品id value：货品信息
    private static final Map<String, GoodsManagerResult> goodsInfomap = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> parsePurchaseFile(MultipartFile file) throws IOException, InvalidFormatException {

        //采购单价的map，用于判断相同商品采购单价是否相同，key：货品编号 value：采购单价
        Map<String, BigDecimal> purchasePriceMap = new ConcurrentHashMap<>();

        List<List<String>> excelList = new ArrayList<>();
        List<PurchaseGoods> outList = new ArrayList<>();
        try {
            excelList = ExcelUtil.readExcel(file.getInputStream(), 1);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("文件类型不匹配！");
        }
        List<String> heads = excelList.get(0);
        List<List<String>> errorList = new ArrayList<>();
        checkHead(heads);

        excelList.set(0, heads);
        String thirdName = null;
        Long thirdId = null;
        for (int i = 1; i < excelList.size(); i++) {
            //错误信息的list，当大于100条的时候，停止记录错误信息
            List<String> error = new ArrayList<>();
            List<String> body = excelList.get(i);
            PurchaseGoods purchaseGoods = new PurchaseGoods();
            String accountName = body.get(ACCOUNT_SERIAL);

            //验证机构编号，同时将网店名称插入到传入的list中
            if (StringUtil.isBlank(accountName)) {
                if (error.size() < ERROR_LIMIT) {
                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(ACCOUNT_SERIAL + 1) + " " + "列" + "【" + ACCOUNT_NAME + "】" + "不能为空");
                }
            } else {
                EnterpriseInfo info = enterpriseInfomap.get(accountName);
                if (info == null) {
                    info = enterpriseInfoService.getEnterpriseInfoByOrganization(accountName);
                    if (info == null) {
                        if (error.size() < ERROR_LIMIT) {
                            error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(ACCOUNT_SERIAL + 1) + " " + "列" + "【" + ACCOUNT_NAME + "】" + "不存在");
                        }
                    } else {
                        enterpriseInfomap.put(accountName, info);
                    }
                }

                if (info != null) {
                    purchaseGoods.setAccountName(accountName);
                    purchaseGoods.setEnterpriseId(info.getEnterpriseId());
                    purchaseGoods.setEnterpriseName(info.getEnterpriseName());
                }
               /* body.add(info.getEnterpriseName());
                body.add(info.getEnterpriseId().toString());*/
            }
            String goodsInfoNumber = body.get(GOODS_NUMBER_SERIAL);
            String goodsInfoName = body.get(GOODS_NAME_SERIAL);
            if (StringUtil.isBlank(goodsInfoName)) {
                if (error.size() < ERROR_LIMIT) {
                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NAME_SERIAL + 1) + " " + "列" + "【" + GOODS_NAME + "】" + "不能为空");
                }
            } else {
                purchaseGoods.setGoodsInfoName(goodsInfoName);
            }
            if ((StringUtil.isBlank(goodsInfoNumber))) {
                if (error.size() < ERROR_LIMIT) {
                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NUMBER_SERIAL + 1) + " " + "列" + "【" + GOODS_NUMBER + "】" + "不能为空");
                }
            } else {
                GoodsManagerResult goodsInfo = goodsInfomap.get(goodsInfoNumber);
                if (goodsInfo == null) {
                    goodsInfo = goodsInfoService.getGoodsInfoByGoodsNumber(goodsInfoNumber);
                    if (goodsInfo == null) {
                        if (error.size() < ERROR_LIMIT) {
                            error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NUMBER_SERIAL + 1) + " " + "列" + "【" + GOODS_NUMBER + "】" + "不存在");
                        }
                    } else {
                        if (thirdId == null) {
                            thirdId = goodsInfo.getThirdId();
                        } else {
                            if (!thirdId.equals(goodsInfo.getThirdId())) {
                                if (error.size() < ERROR_LIMIT) {
                                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NUMBER_SERIAL + 1) + " " + "列" + "货品所属供应商不一致");
                                }
                            }
                        }
                        goodsInfomap.put(goodsInfoNumber, goodsInfo);
                    }
                }

                if (goodsInfo != null) {
                    purchaseGoods.setGoodsInfoNumber(goodsInfoNumber);
                    purchaseGoods.setGoodsInfoSpec(goodsInfo.getSpecString());
                    thirdName = goodsInfo.getThirdName();
                    purchaseGoods.setGoodsInfoId(goodsInfo.getGoodsInfoId());
                    if (!StringUtil.isBlank(goodsInfoNumber)) {
                        if (!goodsInfo.getGoodsInfoName().equals(goodsInfoName)) {
                            if (error.size() < ERROR_LIMIT) {
                                error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NUMBER_SERIAL + 1) + " " + "列" + "【" + GOODS_NUMBER + "】" + "和" +
                                        "第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NAME_SERIAL + 1) + " " + "列" + "【" + GOODS_NAME + "】" + "不匹配");
                            }
                        }
                    }
                    if(thirdId==null){
                        thirdId = goodsInfo.getThirdId();
                    }
                    if(goodsInfo.getThirdId() != thirdId){
                        if (!thirdId.equals(goodsInfo.getThirdId())) {
                            if (error.size() < ERROR_LIMIT) {
                                error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(GOODS_NUMBER_SERIAL + 1) + " " + "列" + "货品所属供应商不一致");
                            }
                        }
                    }
                }
                /*body.add(goodsInfo.getSpecString());*/

            }
            String purchasePrice = body.get(PURCHASE_PRICE_SERIAL);
            BigDecimal purPrice = BigDecimal.ZERO;
            if (StringUtil.isBlank(purchasePrice)) {
                if (error.size() < ERROR_LIMIT) {
                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_PRICE_SERIAL + 1) + " " + "列" + "【" + PURCHASE_PRICE + "】" + "不能为空");
                }
            } else {
                try {
                    purPrice = new BigDecimal(purchasePrice);
                    purchaseGoods.setPurchasePrice(purPrice);
                    if (!goodsInfoNumber.isEmpty()) {
                        BigDecimal exitPrice = purchasePriceMap.get(goodsInfoNumber);
                        if (exitPrice == null) {
                            purchasePriceMap.put(goodsInfoNumber, purPrice);
                        } else {
                            if (exitPrice.compareTo(purPrice) != 0) {
                                if (error.size() < ERROR_LIMIT) {
                                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_PRICE_SERIAL + 1) + " " + "列" + "【" + PURCHASE_PRICE + "】" + "不一致");
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    if (error.size() < ERROR_LIMIT) {
                        error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_PRICE_SERIAL + 1) + " " + "列" + "【" + PURCHASE_PRICE + "】" + "请填写正确的数字");
                    }
                }
            }

            String purchaseAmount = body.get(PURCHASE_AMOUNT_SERIAL);
            BigDecimal purAmount = BigDecimal.ZERO;
            if (StringUtil.isBlank(purchaseAmount)) {
                if (error.size() < ERROR_LIMIT) {
                    error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_AMOUNT_SERIAL + 1) + " " + "列" + "【" + PURCHASE_AMOUNT + "】" + "不能为空");
                }
            } else {
                Pattern pattern = Pattern.compile("[0-9]*");
                Matcher isNum = pattern.matcher(purchaseAmount);
                if (!isNum.matches()) {
                    if (error.size() < ERROR_LIMIT) {
                        error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_AMOUNT_SERIAL + 1) + " " + "列" + "【" + PURCHASE_AMOUNT + "】" + "请填写正整数");
                    }
                } else {
                    try {
                        purAmount = new BigDecimal(purchaseAmount);
                        purchaseGoods.setPurchaseAmount(purAmount);
                    } catch (NumberFormatException e) {
                        if (error.size() < ERROR_LIMIT) {
                            error.add("第" + i + "行错误：" + ExcelUtil.getcellColumnFlag(PURCHASE_AMOUNT_SERIAL + 1) + " " + "列" + "【" + PURCHASE_AMOUNT + "】" + "请填写正整数");
                        }
                    }
                }
            }
            BigDecimal purMoney = purPrice.multiply(purAmount);
            purchaseGoods.setPurchaseMoney(purPrice, purAmount);
            /* body.add(purMoney.toString());
            excelList.set(i, body);*/
            if (!error.isEmpty()) {
                errorList.add(error);
            }
            outList.add(purchaseGoods);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("head", heads);
        map.put("error", errorList);
        map.put("content", outList);
        map.put("thirdId", thirdId);
        map.put("thirdName", thirdName);
        return map;

    }


    @Override
    public Map<Long, Map<Long, PurchaseGoods>> getExcelData(Map<String, Object> map) {
        List<PurchaseGoods> contents = (List<PurchaseGoods>) map.get("content");
        Map<Long, Map<Long, PurchaseGoods>> map0 = new HashMap<>();
        for (PurchaseGoods goods : contents) {
            Map<Long, PurchaseGoods> innerMap;
            innerMap = map0.get(goods.getEnterpriseId());
            if (innerMap == null) {
                innerMap = new HashMap<>();
                map0.put(goods.getEnterpriseId(), innerMap);
            }
            PurchaseGoods originalGoods = innerMap.get(goods.getGoodsInfoId());
            PurchaseGoods tempGoods = null;
            if (originalGoods != null) {
                tempGoods = new PurchaseGoods();
                tempGoods.setPurchaseAmount(originalGoods.getPurchaseAmount());
                tempGoods.setPurchasePrice(originalGoods.getPurchasePrice());
                tempGoods.setAccountName(originalGoods.getAccountName());
                tempGoods.setEnterpriseId(originalGoods.getEnterpriseId());
                tempGoods.setEnterpriseName(originalGoods.getEnterpriseName());
                tempGoods.setGoodsInfoId(originalGoods.getGoodsInfoId());
                tempGoods.setGoodsInfoSpec(originalGoods.getGoodsInfoSpec());
                tempGoods.setGoodsInfoNumber(originalGoods.getGoodsInfoNumber());
                tempGoods.setGoodsInfoName(originalGoods.getGoodsInfoName());
            }
            if (tempGoods == null) {
                innerMap.put(goods.getGoodsInfoId(), goods);
            } else {
                BigDecimal purchaseAmount = tempGoods.getPurchaseAmount() == null ? BigDecimal.ZERO : tempGoods.getPurchaseAmount().add(goods.getPurchaseAmount() == null ? BigDecimal.ZERO : goods.getPurchaseAmount());
                tempGoods.setPurchaseAmount(purchaseAmount);
                innerMap.put(goods.getGoodsInfoId(), tempGoods);
            }
        }
        return map0;
    }

    @Override
    public Boolean judgePurchaseByBillId(Long billId) {
        if (inventoryPurchaseBillService.getPurchaseCountByBillId(billId) > 0) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void createPurchaseBIllByExcel(Map<String, Object> map, String marks) {
        long currentId = currentLoginService.getCurrentLoginEnterpriseId();
        Map<Long, List<InventoryPurchaseBillItem>> groupByThirdMap = new HashMap<>();
        List<InventoryPurchaseBillItem> bills = new ArrayList<>();
        List<PurchaseGoods> contents = (List<PurchaseGoods>) map.get("content");
        Long thirdId = (Long) map.get("thirdId");
        for (int i = 0; i < contents.size(); i++) {
            InventoryPurchaseBillItem billItem = new InventoryPurchaseBillItem();
            PurchaseGoods purchaseGoods = contents.get(i);
            //Key key=new Key(purchaseGoods.getEnterpriseId(),purchaseGoods.goodsInfoId);
            billItem.setAmount(purchaseGoods.getPurchaseAmount().intValue());
            billItem.setSettlePrice(purchaseGoods.getPurchasePrice());
            billItem.setEnterpriseId(purchaseGoods.getEnterpriseId());
            billItem.setGoodsinfoId(purchaseGoods.getGoodsInfoId());
            billItem.setTotalPrice(purchaseGoods.getPurchaseMoney());
            bills.add(billItem);
        }
        groupByThirdMap.put(thirdId, bills);
        inventoryPurchaseBillService.createPurchaseBill(groupByThirdMap, null, currentId, marks);
    }


    @Override
    public List<InventoryBillItem> getMergeBillByExce(List<PurchaseGoods> purchaseGoodses) {
        List<InventoryBillItem> inventoryBillItems = new ArrayList<>();
        Map<Long, InventoryBillItem> goodsItem = new LinkedHashMap<>();
        for (PurchaseGoods purchaseGoods : purchaseGoodses) {
            InventoryBillItem item = new InventoryBillItem();
            item.setGoodsInfoId(purchaseGoods.getGoodsInfoId());
            item.setAmount(purchaseGoods.getPurchaseAmount().intValue());
            InventoryBillItem.ItemGoodsInfo info = new InventoryBillItem.ItemGoodsInfo();
            info.setGoodsInfoId(purchaseGoods.getGoodsInfoId());
            info.setSettlePrice(purchaseGoods.getPurchasePrice());
            info.setGoodsInfoName(purchaseGoods.getGoodsInfoName());
            info.setSpecString(purchaseGoods.getGoodsInfoSpec());
            info.setGoodsInfoItemNo(purchaseGoods.getGoodsInfoNumber());
            item.setInfo(info);
            inventoryBillItems.add(item);
        }
        for (InventoryBillItem inventoryBillItem : inventoryBillItems) {
            Long goodsInfoId = inventoryBillItem.getGoodsInfoId();
            boolean contains = goodsItem.containsKey(goodsInfoId);
            if (contains) {
                InventoryBillItem thereInventoryBillItem = goodsItem.get(goodsInfoId);
                Integer amount = thereInventoryBillItem.getAmount() + inventoryBillItem.getAmount();
                thereInventoryBillItem.setAmount(amount);
                goodsItem.put(goodsInfoId, thereInventoryBillItem);
            } else {
                goodsItem.put(goodsInfoId, inventoryBillItem);
            }
        }
        Collection<InventoryBillItem> items = goodsItem.values();
        List<InventoryBillItem> itemsList = new ArrayList<InventoryBillItem>(items);
        return itemsList;
    }

    @Override
    public void confirmPurchaseBill(long purchaseId, String comment, String marks) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryPurchaseBillService.confirmPurchaseBill(purchaseId, marks);
        inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, comment, "[确认]");

    }

    @Override
    public void terminatePurchaseBill(long purchaseId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryPurchaseBillService.terminatePurchaseBill(purchaseId);
        inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, comment, "[终止]");

    }

    @Override
    public void pendingPurchaseBill(long purchaseId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();

        inventoryPurchaseBillService.pendingPurchaseBill(purchaseId);
        inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, comment, "[待商品入库]");

    }

    @Override
    public InventoryPurchaseBill getSumDetailByPrimaryKey(Long purchaseId) {
        return inventoryPurchaseBillService.getSumDetailByPrimaryKey(purchaseId);
    }

    @Override
    public InventoryPurchaseBill getPurchaseDetailByKey(Long purchaseId) {
        return inventoryPurchaseBillService.getPurchaseDetailByPrimaryKey(purchaseId);
    }

    private void checkHead(List<String> head) {
        if (head.size() != EXCEL_HEADS_LENGTH || !Objects.equals(head.get(ACCOUNT_SERIAL), ACCOUNT_NAME)
                || !Objects.equals(head.get(GOODS_NUMBER_SERIAL), GOODS_NUMBER)
                || !Objects.equals(head.get(GOODS_NAME_SERIAL), GOODS_NAME)
                || !Objects.equals(head.get(PURCHASE_PRICE_SERIAL), PURCHASE_PRICE)
                || !Objects.equals(head.get(PURCHASE_AMOUNT_SERIAL), PURCHASE_AMOUNT)
                ) {
            throw new UserOperationException("模板不匹配，请重新下载");
        }
    }


    @Override
    public void updateSettlePrice(Long purchaseId, Long goodsInfoId, BigDecimal settlePrice) {
        inventoryPurchaseBillService.updateSettlePrice(purchaseId, goodsInfoId, settlePrice);
    }

    @Override
    public void deleteByGoodsInfoId(@Param("purchaseId") Long purchaseId, @Param("goodsInfoIds") Long[] goodsInfoIds, @Param("itemIds") Long[] itemIds) {
        inventoryPurchaseBillService.deleteByGoodsInfoId(purchaseId, goodsInfoIds, itemIds);
    }

    @Override
    public void updateByPrimaryKey(@Param("itemId") Long itemId, @Param("amount") Integer amount) {
        inventoryPurchaseBillService.updateByPrimaryKey(itemId, amount);
    }

    @Override
    public Page<InventoryPurchaseBill> getPurchaseBillList(@Param("code") Long code, @Param("status") PurchaseBillStatus status, @Param("start") Date start, @Param("end") Date end,@Param("thirdId") Long thirdId, @Param("pageable") Pageable pageable) {
        return inventoryPurchaseBillService.getPurchaseBillList(code, status, start, end, thirdId, pageable);
    }

    public static class PurchaseGoods {


        public PurchaseGoods() {
        }

        private Long enterpriseId;
        private String accountName;
        private String enterpriseName;
        private Long goodsInfoId;
        private String goodsInfoName;
        private String goodsInfoSpec;
        private String goodsInfoNumber;
        private BigDecimal purchasePrice;
        private BigDecimal purchaseAmount;
        private BigDecimal purchaseMoney;

        public Long getEnterpriseId() {

            return enterpriseId;
        }

        public void setEnterpriseId(Long enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public Long getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(Long goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public String getGoodsInfoName() {
            return goodsInfoName;
        }

        public void setGoodsInfoName(String goodsInfoName) {
            this.goodsInfoName = goodsInfoName;
        }

        public String getGoodsInfoSpec() {
            return goodsInfoSpec;
        }

        public void setGoodsInfoSpec(String goodsInfoSpec) {
            this.goodsInfoSpec = goodsInfoSpec;
        }

        public String getGoodsInfoNumber() {
            return goodsInfoNumber;
        }

        public void setGoodsInfoNumber(String goodsInfoNumber) {
            this.goodsInfoNumber = goodsInfoNumber;
        }

        public BigDecimal getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
        }

        public BigDecimal getPurchaseMoney() {
            return purchaseMoney;
        }

        public void setPurchaseMoney(BigDecimal purchasePrice, BigDecimal purchaseAmount) {
            this.purchaseMoney = purchasePrice.multiply(purchaseAmount);
        }

        public BigDecimal getPurchaseAmount() {
            return purchaseAmount;
        }

        public void setPurchaseAmount(BigDecimal purchaseAmount) {
            this.purchaseAmount = purchaseAmount;
        }

        @Override
        public PurchaseGoods clone() {
            try {
                return (PurchaseGoods) super.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 创建采购入库单
     */
    @Override
    public void createStorageBill(Long purchaseId, String reason) {
        long creatorId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryPurchaseBill bill = getPurchaseDetailByKey(purchaseId);
        Map<Long, List<InventoryPurchaseBillItem>> itemMap = new HashMap<>();
        for (InventoryPurchaseBillItem item : bill.getItems()) {
            Long enterpriseId = item.getEnterpriseId();
            if (itemMap.containsKey(enterpriseId)) {
                List<InventoryPurchaseBillItem> itemList = itemMap.get(enterpriseId);
                itemList.add(item);
                itemMap.put(enterpriseId, itemList);
            } else {
                List<InventoryPurchaseBillItem> itemList = new ArrayList<>();
                itemList.add(item);
                itemMap.put(enterpriseId, itemList);
            }

        }
        if (itemMap.size() <= 0 || bill.getStatus() != PurchaseBillStatus.FINISHED) {
            throw new UserOperationException("该采购单不能进行此操作！");
        } else {
            inventoryBillService.createStorageBill(itemMap, purchaseId, creatorId, reason);
            inventoryPurchaseBillService.pendingPurchaseBill(purchaseId);
            inventoryPurchaseBillLogService.addPurchaseBillLog(creatorId, purchaseId, reason, "[待商品入库]");
        }
    }

    @Override
    public void formCreateStorageBill(Long enterpriseId, List<InventoryManageAPIController.StorageGoodsInfo> goodsInfos, String reason) {
        Map<Long, List<InventoryPurchaseBillItem>> listMap = new HashMap<>();
        List<InventoryPurchaseBillItem> list = new ArrayList<>();
        Long creatorId = currentLoginService.getCurrentLoginEnterpriseId();
        for (InventoryManageAPIController.StorageGoodsInfo goodsInfo : goodsInfos) {
            InventoryPurchaseBillItem item = new InventoryPurchaseBillItem();
            item.setGoodsinfoId(goodsInfo.getGoodsInfoId());
            item.setSumAmount(goodsInfo.getAmount());
            item.setSettlePrice(goodsInfo.getPurchasePrice());
            list.add(item);
        }
        listMap.put(enterpriseId, list);
        inventoryBillService.createStorageBill(listMap, null, creatorId, reason);

    }


    /**
     * 确认入库
     */
    @Override
    @Transactional
    public void confirmStorage(Long billId, List<InventoryBillItem> sku, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        receiptBill(billId, comment, sku);

        InventoryBill inventoryBill = inventoryBillService.getBillById(billId);
        Long purchaseId = inventoryBill.getPurchaseId();

        Integer amcount = 0;
        for (int i=0;i<sku.size();i++) {
            InventoryBillItem  Item = sku.get(i);
            amcount += Item.getAmount();
            inventoryPurchaseBillService.updateReceiptAmountByGoodsInfoId(Item.getAmount(), purchaseId, enterpriseId, Item.getGoodsInfoId());
        }

        InventoryPurchaseBill bill = inventoryPurchaseBillService.getByPrimaryKey(purchaseId);
        if (bill != null) {
            Integer recepietAmount = bill.getReceiptSumAmount() == null ? 0 : bill.getReceiptSumAmount();
            bill.setReceiptSumAmount(recepietAmount + amcount);
            if (bill.getPurchaseAmount().equals(bill.getReceiptSumAmount())) {
                bill.setStatus(PurchaseBillStatus.FOR_SETTLEMENT);
                inventoryPurchaseBillService.updateByPrimaryKeySelective(bill);
                inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, comment, "[待结算]");
            } else if (bill.getPurchaseAmount() < bill.getReceiptSumAmount()) {
                throw new UserOperationException("【本次入库数量】不得大于商品【入库数量】；");
            } else {
                inventoryPurchaseBillService.updateByPrimaryKeySelective(bill);
            }
        }
    }

    //结算采购订单
    @Override
    public void settlementPurchase(Long purchaseId, String comment) {
        InventoryPurchaseBill bill = inventoryPurchaseBillService.getByPrimaryKey(purchaseId);
        if (bill.getPurchaseAmount().equals(bill.getReceiptSumAmount())) {
            long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
            bill.setStatus(PurchaseBillStatus.COMPLETED);
            inventoryPurchaseBillService.updateByPrimaryKeySelective(bill);
            inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, comment, "[已完成]");
        } else {
            throw new UserOperationException("当前采购商品未完全入库！");
        }

    }

    @Override
    @Transactional
    public void forceFinishBill(Long purchaseId) {
        List<InventoryBill> billList = inventoryBillService.getInventoryBillByPurchaseId(purchaseId);
        InventoryPurchaseBill inventoryPurchaseBill = inventoryPurchaseBillService.getByPrimaryKey(purchaseId);
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        for (InventoryBill bill : billList) {
            Long billId = bill.getBillId();
            Long currentId = bill.getCurrentId();
            inventoryBillService.forceFinishBill(billId);
            if (currentId != null) {
                inventoryBillHistoryService.operate(currentId, billId);
            }
            inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[完成]");
        }
        inventoryPurchaseBill.setStatus(PurchaseBillStatus.COMPLETED);
        inventoryPurchaseBillService.updateByPrimaryKeySelective(inventoryPurchaseBill);
        inventoryPurchaseBillLogService.addPurchaseBillLog(enterpriseId, purchaseId, null, "[已完成]");

    }


}




