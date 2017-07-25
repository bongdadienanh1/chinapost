package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.chinapost.service.impl.InventoryManageServiceImpl;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.mapper.pojo.InventoryMergeGoods;
import com.ylife.inventory.model.*;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import com.ylife.utils.WebUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by XiaoBiaoGe on 2016/4/23.
 * 库存API控制器
 */
@Controller
@SecurityResource(parent = "/web/InventoryManager", primary = false)
@RequestMapping(value = "/web/api/inventory", produces = "application/json;charset=utf-8")
public class InventoryManageAPIController {

    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private EnterpriseService enterpriseService;

    private static final String EXCEL_LIST_KEY = "_excel_list_key";
    private static final String EXCEL_LIST_TIME_KEY = "_excel_list_time_key";
    //过期查询时间
    @RequestMapping("/warn")
    @ResponseBody
    public String warn(
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "endStartDate", required = false) String endStartDate1,
            @RequestParam(value = "endEndDate", required = false) String endEndDate1,
            @RequestParam(value = "endDate", required = false) String endDate1,
            @RequestParam(value = "startDate", required = false)  String startDate1,
            @RequestParam(value = "overday", required = false) String overday,
            @RequestParam(value = "day", required = false) Integer day,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
       Long codeL=null;
       if(!StringUtil.isBlank(code)){
      codeL=Long.parseLong(code);
       }
        Date endStartDate = null;
        if (!StringUtil.isBlank(endStartDate1)) {
            endStartDate = DateUtil.getMorning(DateUtil.fromString(endStartDate1, Constants.DEFAULT_DAY_FORMAT));
        }
        Date endEndDate = null;
        if (!StringUtil.isBlank(endEndDate1)) {
            endEndDate = DateUtil.getNight(DateUtil.fromString(endEndDate1, Constants.DEFAULT_DAY_FORMAT));
        }
        Date endDate = null;
        if (!StringUtil.isBlank(endDate1)) {
            endDate = DateUtil.getNight(DateUtil.fromString(endDate1, Constants.DEFAULT_DAY_FORMAT));
        }
        Date startDate = null;
        if (!StringUtil.isBlank(startDate1)) {
            startDate = DateUtil.getMorning(DateUtil.fromString(startDate1, Constants.DEFAULT_DAY_FORMAT));
        }
        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        long minCatalog = current.getMinCatalog();
        long maxCatalog = current.getMaxCatalog();
        long catalog = current.getCatalog();
        Page<WarnDay> warnDayPage = inventoryManageService.selectWarnDay(minCatalog, maxCatalog, endStartDate, endEndDate, endDate, startDate, day, goodsInfoItemNo, codeL, new Pageable(page, size), overday,type,catalog);
        return new JsonResponseBean(warnDayPage).toJson();
    }

    @RequestMapping("/delectWarn")
    @ResponseBody
    public String delectWarn(@RequestParam(value = "id", required = false) long id) {
        inventoryManageService.deleteWarnDay(id);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/updateWarnDay")
    @ResponseBody
    public String updateWarnDay( InventoryBillItemDetail inventoryBillItemDetail) {
        inventoryManageService.updateWarnDay(inventoryBillItemDetail);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
    @RequestMapping("/getSonEnterprise")
    @ResponseBody
    public String getSonEnterprise() {
        Map<String, Object> map = inventoryManageService.sonEnterprise();
        return new JsonResponseBean(map).toJson();
    }


    @RequestMapping("/getInventoryGoods")
    @ResponseBody
    public String getInventoryGoods(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                    @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
                                    @RequestParam(value = "brandId", required = false) Long brandId,
                                    @RequestParam(value = "thirdId", required = false) Long thirdId,
                                    @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                    @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getInventoryGoodsResult(goodsInfoName, goodsNumber, brandId, thirdId, goodsInfoType, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResultPage).toJson();
    }

    @RequestMapping("/getOutInventoryGoods")
    @ResponseBody
    public String getOutInventoryGoods(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                       @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                       @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                       @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
                                       @RequestParam(value = "brandId", required = false) Long brandId,
                                       @RequestParam(value = "thirdId", required = false) Long thirdId,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getInventoryGoodsResultBYEnterpriseId(enterpriseId, goodsInfoName, goodsInfoType, goodsNumber, brandId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResultPage).toJson();
    }


    //商品入库
    @RequestMapping("/addInventoryGoods")
    @ResponseBody
    public String addinventoryGoods(@RequestParam("goodsJson") String goodsJson) {
        Map<Long, Integer> map = Constants.SIMPLE_PARSER.parseJSON(goodsJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.addGoods(map);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    @RequestMapping("/addInventory")
    @ResponseBody
    public String addInventory(@RequestParam("goodsInfoId") long goodsInfoId, @RequestParam("inventory") int inventory) {
        Assert.isTrue(inventory > 0, "增加的库存必须大于0。");
        inventoryManageService.addInventory(goodsInfoId, inventory);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping(value = "/editInventory", method = RequestMethod.POST)
    @ResponseBody
    public String editInventory(@RequestParam("goodsInfoId") long goodsId, @RequestParam("inventory") int inventory) {
        Assert.isTrue(inventory >= 0, "增加的库存必须大于等于0。");

        inventoryManageService.editInventory(goodsId, inventory);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    @RequestMapping("/deleteInventory")
    @ResponseBody
    public String deleteInventory(@RequestParam("goodsInfoId") long goodsId) {
        inventoryManageService.deleteGoods(goodsId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    //获取库存商品
    @RequestMapping("/getGoods")
    @ResponseBody
    public String getGoods(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                           @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
                           @RequestParam(value = "brandId", required = false) Long brandId,
                           @RequestParam(value = "thirdId", required = false) Long thirdId,
                           @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                           @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getGoods(goodsInfoName, goodsNumber, brandId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResultPage).toJson();
    }


    //获取预警货品
    @RequestMapping("/getWarningGoods")
    @ResponseBody
    public String getWarninggoods(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                  @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
                                  @RequestParam(value = "brandId", required = false) Long brandId,
                                  @RequestParam(value = "thirdId", required = false) Long thirdId,
                                  @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getWarningGoods(goodsInfoName, goodsNumber, brandId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResultPage).toJson();
    }


    //设置预警值
    @RequestMapping("/setWarning")
    @ResponseBody
    public String setWarning(@RequestParam("warning") Integer warning, @RequestParam("goodsInfoId") Long goodsInfoId) {
        inventoryManageService.editWarning(goodsInfoId, warning);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    //获取预警值
    @RequestMapping("/getWarning")
    @ResponseBody
    public String getWarning(@RequestParam("goodsInfoId") Long goodsInfoId) {
        Inventory inventory = inventoryManageService.selectByGoodsInfoId(goodsInfoId);
        Integer warning = inventory.getGoodsInfoWarning();
        return new JsonResponseBean(warning).toJson();
    }


    //获取子节点信息
    @RequestMapping("/getSonEnterpriseInfo")
    @ResponseBody
    public String getSonEnterpriseInfo(@RequestParam("enterpriseId") Long enterpriseId) {
        List<Enterprise> enterprises = enterpriseService.getEnterprises(enterpriseId);
        return new JsonResponseBean(enterprises).toJson();
    }


    //按照库存查询
    @RequestMapping("/getByInventory")
    @ResponseBody
    public String getByInventory(@RequestParam("enterpriseId") long enterpriseId,
                                 @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                 @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                 @RequestParam(value = "brandId", required = false) Long brandId,
                                 @RequestParam(value = "thirdId", required = false) Long thirdId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        Page<List<InventoryGoodsResult>> inventoryGoodsResults = inventoryManageService.queryByInventory(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResults).toJson();
    }


    //按照商品查询
    @RequestMapping("/getByGoodsInfo")
    @ResponseBody
    public String getByGoodsInfo(@RequestParam("enterpriseId") long enterpriseId,
                                 @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                 @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                 @RequestParam(value = "brandId", required = false) Long brandId,
                                 @RequestParam(value = "thirdId", required = false) Long thirdId,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        Page<InventoryGoodsResult> inventoryGoodsResults = inventoryManageService.queryByGoodsInfo(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(inventoryGoodsResults).toJson();
    }


    //获取补货申请的货品页面，带本网点库存
    @RequestMapping("/getGoodsInventory")
    @ResponseBody
    public String getGoodsInventory(
            @RequestParam(value = "goodsInfoId", required = false) Long goodsInfoId,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
            @RequestParam(value = "goodsInfoType") Integer goodsInfoType,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "thirdId", required = false) Long thirdId,
            @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getGoodsAndInventory(enterpriseId, goodsInfoId, goodsInfoName, goodsInfoType, goodsNumber, brandId, thirdId, new Pageable(page, size), goodsInfoAdded);
        return new JsonResponseBean(inventoryGoodsResultPage).toJson();
    }

    /*****************************************************************************************************************************/
    /*****************************************************请货，调拨，报损、报溢*****************************************************/
    /*****************************************************************************************************************************/

    /**
     * 根据id获取单据。
     *
     * @param billId 单据ID
     */
    @RequestMapping("/getBillById")
    @ResponseBody
    public String getBillById(@RequestParam(value = "billId") long billId) {
        InventoryBill bill = inventoryManageService.getBillById(billId);
        return new JsonResponseBean(bill).toJson();
    }

    /**
     * 根据code获取单据。
     *
     * @param code 单据编号
     */
    @RequestMapping("/getBillByCode")
    @ResponseBody
    public String getBillByCode(@RequestParam(value = "code") long code) {
        InventoryBill bill = inventoryManageService.getBillByCode(code);
        return new JsonResponseBean(bill).toJson();
    }

    /**
     * 获取创建的单据。
     */
    @RequestMapping("/getCreatedBills")
    @ResponseBody
    public String getCreatedBills(@RequestParam(value = "code", required = false) Long code,
                                  @RequestParam(value = "creatorName", required = false) String creatorName,
                                  @RequestParam(value = "start", required = false) String start,
                                  @RequestParam(value = "end", required = false) String end,
                                  @RequestParam(value = "billStatus", required = false) BillStatus billStatus,
                                  @RequestParam(value = "billType", required = false) BillType billType,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        creatorName = Constants.nullOrNotBlank(creatorName);
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        Date startTime=null;
        Date endTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        } else {
            startTime = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.getNight(DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT));
        } else {
            endTime = DateUtil.getNight(new Date());
        }
        Pageable pageable = new Pageable(page, size);
        Page<InventoryBill> page1 = inventoryManageService.getCreatedBills(code, creatorName, startTime, endTime, billStatus, billType, pageable);
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取已处理的单据。
     */
    @RequestMapping("/getHandledBills")
    @ResponseBody
    public String getHandledBills(@RequestParam(value = "code", required = false) Long code,
                                  @RequestParam(value = "creatorName", required = false) String creatorName,
                                  @RequestParam(value = "start", required = false) String start,
                                  @RequestParam(value = "end", required = false) String end,
                                  @RequestParam(value = "billStatus", required = false) BillStatus billStatus,
                                  @RequestParam(value = "billType", required = false) BillType billType,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        creatorName = Constants.nullOrNotBlank(creatorName);
        Pageable pageable = new Pageable(page, size);
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        Date startTime=null;
        Date endTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        } else {
            startTime = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.getNight(DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT));
        } else {
            endTime = DateUtil.getNight(new Date());
        }
        Page<InventoryBill> page1 = inventoryManageService.getHandledBills(code, creatorName, startTime, endTime, billStatus, billType, pageable);
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取待处理单据。
     */
    @RequestMapping("/getUnHandleBills")
    @ResponseBody
    public String getUnHandleBills(@RequestParam(value = "code", required = false) Long code,
                                   @RequestParam(value = "creatorName", required = false) String creatorName,
                                   @RequestParam(value = "start", required = false) String start,
                                   @RequestParam(value = "end", required = false) String end,
                                   @RequestParam(value = "billStatus", required = false) BillStatus billStatus,
                                   @RequestParam(value = "billType", required = false) BillType billType,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                   @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        creatorName = Constants.nullOrNotBlank(creatorName);
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        Date startTime=null;
        Date endTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        } else {
            startTime = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.getNight(DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT));
        } else {
            endTime = DateUtil.getNight(new Date());
        }
        Pageable pageable = new Pageable(page, size);
        Page<InventoryBill> page1 = inventoryManageService.getUnHandleBills(code, creatorName, startTime, endTime, billStatus, billType, pageable);
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取待处理补货单据。
     */
    @RequestMapping("/getRepairUnHandleBills")
    @ResponseBody
    public String getRepairUnHandleBills(@RequestParam(value = "code", required = false) Long code,
                                         @RequestParam(value = "creatorName", required = false) String creatorName,
                                         @RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end,
                                         @RequestParam(value = "billStatus", required = false) BillStatus billStatus,
                                         @RequestParam(value = "billType", required = false) BillType billType) {
        creatorName = Constants.nullOrNotBlank(creatorName);
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        Date startTime=null;
        Date endTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        } else {
            startTime = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.getNight(DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT));
        } else {
            endTime = DateUtil.getNight(new Date());
        }
        Page<InventoryBill> page1 = inventoryManageService.getUnHandleBills(code, creatorName, startTime, endTime, billStatus, billType, null);
        return new JsonResponseBean(page1).toJson();
    }

    /**
     * 获取合并单据的详情(没有生成补货单)
     *
     * @param flag    0为获取详情，1为获取汇总
     * @param billIds
     */
    @RequestMapping("/getMergeBillByBillIds")
    @ResponseBody
    public String getMergeBillByBillIds(String billIds, Integer flag) {
        Assert.hasLength(billIds, "billIds参数不能为空。");
        Long[] ids = Constants.SIMPLE_PARSER.parseJSON(billIds, new TypeToken<Long[]>() {
        });
        Assert.notEmpty(ids, "billIds不能为空。");
        Assert.isTrue(ids.length != 1, "billIds数量不能为1。");
        List<InventoryMergeGoods> inventoryBillList = inventoryManageService.getMergeBillByBillIds(ids, flag);
        return new JsonResponseBean(inventoryBillList).toJson();
    }

    /**zx
     * 创建合并后的补货单
     *
     * @param status  补货单状态
     * @param billIds 合并补货单id
     * @param reason  原因
     */
    @RequestMapping("/createMergeBill")
    @ResponseBody
    public String createMergeBill(BillStatus status, String billIds, String reason) {
        Assert.notNull(status);
        Assert.hasLength(billIds, "billIds参数不能为空。");
        Long[] ids = Constants.SIMPLE_PARSER.parseJSON(billIds, new TypeToken<Long[]>() {
        });
        Assert.notEmpty(ids, "billIds不能为空。");
        Assert.isTrue(ids.length != 1, "billIds数量不能为1。");
        long billId = inventoryManageService.createMergeBill(status, ids, reason);
        return new JsonResponseBean(billId).toJson();
    }

    /**
     * 获取汇总补货单
     *
     * @param billId 合并补货单id
     */
    @RequestMapping("/getMergeBillSumByBillId")
    @ResponseBody
    public String getMergeBillSumByBillId(long billId) {
        InventoryBill inventoryBill = inventoryManageService.getBillById(billId);
        return new JsonResponseBean(inventoryBill).toJson();
    }

    /**
     * 获取汇总补货单明细
     *
     * @param billId 合并补货单id
     */
    @RequestMapping("/getMergeBillByParentId")
    @ResponseBody
    public String getMergeBillByParentId(long billId) {
        List<InventoryMergeGoods> mergeGoodsList = inventoryManageService.getMergeBillByParentId(billId);
        return new JsonResponseBean(mergeGoodsList).toJson();
    }

    /**zx
     * 创建单据
     *
     * @param type    单据类型
     * @param outId   出库点ID
     * @param inId    入库点ID
     * @param reason  申请原因
     * @param skuJson 商品SKU
     */
    @RequestMapping("/createBill")
    @ResponseBody
    public String createBill(@RequestParam(value = "type") BillType type,
                             @RequestParam(value = "outId", required = false) Long outId,
                             @RequestParam(value = "inId", required = false) Long inId,
                             @RequestParam(value = "billId", required = false) Long billId,
                             @RequestParam(value = "reason") String reason,
                             @RequestParam(value = "skuJson") String skuJson) {
        Map<Long, Integer> sku = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.createBill(type, outId, inId, billId, reason, sku);
        return JsonResponseBean.getSuccessResponse().toJson();
    }






    /**
     * 收货
     *
     * @param billId  ID
     * @param comment 流转意见
     */
    @RequestMapping("/receiptBill")
    @ResponseBody
    public String receiptBill(@RequestParam(value = "billId") long billId,
                              @RequestParam(value = "comment", required = false) String comment,
                              @RequestParam(value = "skuJson", required = true) String skuJson) {
        List<InventoryBillItem> list = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<List<InventoryBillItem>>() {
        });
        inventoryManageService.receiptBill(billId, comment, list);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    //自购商品入库
    @RequestMapping("/addGoods")
    @ResponseBody
    public String addGoods(@RequestParam("goodsJson") String goodsJson) {

        List<InventoryBillItem> list = Constants.SIMPLE_PARSER.parseJSON(goodsJson, new TypeToken<List<InventoryBillItem>>() {
        });


        inventoryManageService.addGoodss(list);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
    /**
     * 批准单据
     *
     * @param billId      ID
     * @param comment     流转意见
     * @param amountsJson 审核数量表
     */
    @RequestMapping("/approveBill")
    @ResponseBody
    public String approveBill(@RequestParam(value = "billId") long billId,
                              @RequestParam(value = "comment", required = false) String comment,
                              @RequestParam(value = "amountsJson") String amountsJson) {
        Map<Long, Integer> amounts = Constants.SIMPLE_PARSER.parseJSON(amountsJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.approveBill(billId, comment, amounts);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * zx
     * 同意单据
     *
     * @param billId  ID
     * @param comment 流转意见
     * @param skuJson 商品SKU
     */
    @RequestMapping("/agreeBill")
    @ResponseBody
    public String agreeBill(@RequestParam(value = "billId") long billId,
                            @RequestParam(value = "comment", required = false) String comment,
                            @RequestParam(value = "skuJson", required = false) String skuJson) {
        Map<Long, Integer> sku = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.agreeBill(billId, comment, sku);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 终止单据
     *
     * @param billId  ID
     * @param comment 流转意见
     */
    @RequestMapping("/terminateBill")
    @ResponseBody
    public String terminateBill(@RequestParam(value = "billId") long billId,
                                @RequestParam(value = "comment", required = false) String comment) {
        inventoryManageService.terminateBill(billId, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 完成单据
     *
     * @param billId ID
     */
    @RequestMapping("/finishBill")
    @ResponseBody
    public String finishBill(@RequestParam(value = "billId") long billId) {
        inventoryManageService.finishBill(billId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 退回单据
     *
     * @param billId  ID
     * @param comment 流转意见
     */
    @RequestMapping("/backBill")
    @ResponseBody
    public String backBill(@RequestParam(value = "billId") long billId,
                           @RequestParam(value = "comment", required = false) String comment) {
        inventoryManageService.backBill(billId, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 提交单据（报损报溢单经过退回，修改后重新提交。）
     *
     * @param billId  单据ID
     * @param comment 流转意见
     * @param skuJson 货品SKU
     */
    @RequestMapping("/submitBill")
    @ResponseBody
    public String submitBill(@RequestParam(value = "billId") long billId,
                             @RequestParam(value = "comment", required = false) String comment,
                             @RequestParam(value = "skuJson", required = false) String skuJson) {
        Map<Long, Integer> sku = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.submitBill(billId, comment, sku);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 发货
     *
     * @param billId  ID
     * @param comment 流转意见
     * @param skuJson 商品SKU
     */
    @RequestMapping("/deliveryBill")
    @ResponseBody
    public String deliveryBill(@RequestParam(value = "billId") long billId,
                               @RequestParam(value = "comment", required = false) String comment,
                               @RequestParam(value = "skuJson") String skuJson) {
        Map<Long, Integer> sku = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<Map<Long, Integer>>() {
        });
        inventoryManageService.deliveryBill(billId, comment, sku);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    @RequestMapping("/setBillRead")
    @ResponseBody
    public String setBillRead(@RequestParam(value = "billId") long billId,
                              @RequestParam(value = "readFlag", required = false) Boolean read) {
        inventoryManageService.updateInventoryBill(billId, read);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 从补货申请单立即创建调拨单，根据补货申请单id，获取调拨单相应数据
     *
     * @param billId
     * @return
     */
    @RequestMapping("/getCreatBillDataFromReplementBill")
    @ResponseBody
    public String getCreatBillDataFromReplementBill(@RequestParam(value = "billId") long billId) {
        InventoryBill bill = inventoryManageService.getBillDataFromReplementBill(billId);
        return new JsonResponseBean(bill).toJson();
    }


    /**
     * 获取商品信息和该商品在某网点的可用
     *
     * @param enterpriseId
     * @param goodsJson
     * @return
     */
    @RequestMapping("/getGoodsAndInventory")
    @ResponseBody
    public String getCreatBillDataFromReplementBill(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                                    @RequestParam(value = "goodsJson") String goodsJson) {
        List<Long> goodsIds = Constants.SIMPLE_PARSER.parseJSON(goodsJson, new TypeToken<List<Long>>() {
        });
        List<InventoryGoodsResult> goodsResultList = inventoryManageService.getGoodsAndAvailable(enterpriseId, goodsIds);
        return new JsonResponseBean(goodsResultList).toJson();
    }

    /**
     * 根据供应商创建采购单
     *
     * @param parentId 合并补货单id
     */
    @RequestMapping("/createPurchaseBill")
    @ResponseBody
    public String createPurchaseBill(long parentId) {
//        Assert.hasLength(marks, "原因不能为空。");
        inventoryManageService.createPurchaseBill(parentId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**zx
     * 补货单创建采购单
     * */
    @RequestMapping("/createPurchaseBillByBillid")
    @ResponseBody
    public String createPurchaseBillByBillid(long billId){
        inventoryManageService.createPurchaseBillByBillid(billId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 获取采购单汇总详情
     **/
    @RequestMapping("/getSumDetailByPrimaryKey")
    @ResponseBody
    public String getSumDetailByPrimaryKey(long purchaseId) {
        InventoryPurchaseBill inventoryPurchaseBills = inventoryManageService.getSumDetailByPrimaryKey(purchaseId);
        return new JsonResponseBean(inventoryPurchaseBills).toJson();
    }

    /**
     * 获取采购单明细
     **/
    @RequestMapping("/getPurchaseDetailByKey")
    @ResponseBody
    public String getPurchaseDetailByKey(long purchaseId) {
        InventoryPurchaseBill itemMap = inventoryManageService.getPurchaseDetailByKey(purchaseId);
        return new JsonResponseBean(itemMap).toJson();
    }

    /**
     * 修改货品单价
     */
    @RequestMapping("/updateSettlePrice")
    @ResponseBody
    public String updateSettlePrice(long purchaseId, long goodsInfoId, BigDecimal settlePrice) {
        Assert.notNull(settlePrice);
        inventoryManageService.updateSettlePrice(purchaseId, goodsInfoId, settlePrice);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 删除采购单商品
     */
    @RequestMapping("/deleteByGoodsInfoId")
    @ResponseBody
    public String deleteByGoodsInfoId(@Param("purchaseId") long purchaseId, @Param("goodsInfoIds") String goodsInfoIds, @Param("itemIds") String itemIds) {
        if (goodsInfoIds != null) {
            Long[] goodsIds = Constants.SIMPLE_PARSER.parseJSON(goodsInfoIds, new TypeToken<Long[]>() {
            });
            inventoryManageService.deleteByGoodsInfoId(purchaseId, goodsIds, null);
        } else if (itemIds != null) {
            Long[] ids = Constants.SIMPLE_PARSER.parseJSON(itemIds, new TypeToken<Long[]>() {
            });
            inventoryManageService.deleteByGoodsInfoId(purchaseId, null, ids);
        } else {
            throw new UserOperationException("删除货品不能为空！");
        }
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改网点单个商品明细
     */
    @RequestMapping("/updateByItemId")
    @ResponseBody
    public String updateByItemId(@Param("itemId") long itemId, @Param("amount") Integer amount) {
        Assert.notNull(amount);
        inventoryManageService.updateByPrimaryKey(itemId, amount);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 获取采购单列表。
     */
    @RequestMapping("/getPurchaseBillList")
    @ResponseBody
    public String getPurchaseBillList(@RequestParam(value = "code", required = false) Long code,
                                      @RequestParam(value = "start", required = false) String start,
                                      @RequestParam(value = "end", required = false) String end,
                                      @RequestParam(value = "billStatus", required = false) PurchaseBillStatus billStatus,
                                      @RequestParam(value = "thirdId", required = false) Long thirdId,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        Date startTime=null;
        Date endTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        } else {
            startTime = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.getNight(DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT));
        } else {
            endTime = DateUtil.getNight(new Date());
        }
        Pageable pageable = new Pageable(page, size);
        Page<InventoryPurchaseBill> page1 = inventoryManageService.getPurchaseBillList(code, billStatus, startTime, endTime,thirdId, pageable);
        return new JsonResponseBean(page1).toJson();
    }

    @RequestMapping(value = "/parsePurchaseExcel", method = RequestMethod.POST)
    @ResponseBody
    public String parsePurchaseExcel(MultipartFile file) throws IOException, InvalidFormatException {
        Map<String, Object> infoList = inventoryManageService.parsePurchaseFile(file);
        long time = System.currentTimeMillis();
        HttpSession session = WebUtil.getCurrentSession();
        session.setAttribute(EXCEL_LIST_KEY, infoList);
        session.setAttribute(EXCEL_LIST_TIME_KEY, time);
        return new JsonResponseBean(time).toJson();
    }

    /**
     * 获取excel导入详情
     */
    @RequestMapping(value = "/getExcelData", method = RequestMethod.POST)
    @ResponseBody
    public String getExcelData(long key) {
        HttpSession session = WebUtil.getCurrentSession();
        Long currentKey = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (currentKey == null || currentKey != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<String, Object> infoList = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
        Map<Long, Map<Long, InventoryManageServiceImpl.PurchaseGoods>> mapMap = inventoryManageService.getExcelData(infoList);
        Map<String, Object> outList = new HashMap<>();
        outList.put("error", infoList.get("error"));
        outList.put("content", mapMap);
        outList.put("thirdId", infoList.get("thirdId"));
        outList.put("thirdName", infoList.get("thirdName"));

        return new JsonResponseBean(outList).toJson();
    }

    /**
     * 获取excel导入汇总
     */
    @RequestMapping("/getMergeBillByExce")
    @ResponseBody
    public String getMergeBillByExce(long key) {
        HttpSession session = WebUtil.getCurrentSession();
        Long currentKey = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (currentKey == null || currentKey != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<String, Object> infoList = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
        List<InventoryManageServiceImpl.PurchaseGoods> purchaseGoodses = (List<InventoryManageServiceImpl.PurchaseGoods>) infoList.get("content");
        List<InventoryBillItem> items = inventoryManageService.getMergeBillByExce(purchaseGoodses);
        return new JsonResponseBean(items).toJson();
    }


    @RequestMapping("/createPurchaseBIllByExcel")
    @ResponseBody
    public String createPurchaseBIllByExcel(long key,String marks) {
        HttpSession session = WebUtil.getCurrentSession();
        Long currentKey = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (currentKey == null || currentKey != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<String, Object> infoList = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
        inventoryManageService.createPurchaseBIllByExcel(infoList,marks);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 确认采购订单
     */
    @RequestMapping("/confirmPurchaseBill")
    @ResponseBody
    public String confirmPurchaseBill(long purchaseId, String comment,String marks) {
        inventoryManageService.confirmPurchaseBill(purchaseId, comment,marks);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 终止采购订单
     */
    @RequestMapping("/terminatePurchaseBill")
    @ResponseBody
    public String terminatePurchaseBill(long purchaseId, String comment) {
        inventoryManageService.terminatePurchaseBill(purchaseId, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 根据采购订单生成采购入库单
     */
    @RequestMapping("/createStorageBill")
    @ResponseBody
    public String createStorageBill(Long purchaseId) {
//        Assert.hasLength(reason, "原因不能为空。");
        inventoryManageService.createStorageBill(purchaseId, null);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 采购单待商品入库
     */
    @RequestMapping("/pendingPurchaseBill")
    @ResponseBody
    public String pendingPurchaseBill(long purchaseId, String comment) {
        inventoryManageService.pendingPurchaseBill(purchaseId, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改采购单收货数量
     */
    @RequestMapping("/confirmStorage")
    @ResponseBody
    public String confirmStorage(@RequestParam(value = "billId") long billId,
                                 @RequestParam(value = "comment", required = false) String comment,
                                 @RequestParam(value = "skuJson", required = true) String skuJson) {
        List<InventoryBillItem> list = Constants.SIMPLE_PARSER.parseJSON(skuJson, new TypeToken<List<InventoryBillItem>>() {
        });
        inventoryManageService.confirmStorage(billId, list, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 采购单结算
     */
    @RequestMapping("/settlementPurchase")
    @ResponseBody
    public String settlementPurchase(long purchaseId, String comment) {
        inventoryManageService.settlementPurchase(purchaseId, comment);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 采购单强制结算
     */
    @RequestMapping("/forceFinishBill")
    @ResponseBody
    public String forceFinishBill(long purchaseId) {
        inventoryManageService.forceFinishBill(purchaseId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 顶级生成入库单
     */
    @RequestMapping("/formCreateStorageBill")
    @ResponseBody
    public String formCreateStorageBill(Long enterpriseId,
                                        @RequestParam(value = "skuJson", required = true) String goodsInfo,
                                        String reason) {
//        Assert.hasLength(reason, "原因不能为空。");
        List<StorageGoodsInfo> infoList = Constants.SIMPLE_PARSER.parseJSON(goodsInfo, new TypeToken<List<StorageGoodsInfo>>() {
        });
        inventoryManageService.formCreateStorageBill(enterpriseId, infoList, reason);
        return JsonResponseBean.getSuccessResponse().toJson();
    }



    public static class StorageGoodsInfo {

        private Long goodsInfoId;

        private Integer amount;

        private BigDecimal purchasePrice;

        public Long getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(Long goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public BigDecimal getPurchasePrice() {
            return purchasePrice;
        }

        public void setPurchasePrice(BigDecimal purchasePrice) {
            this.purchasePrice = purchasePrice;
        }
    }
}
