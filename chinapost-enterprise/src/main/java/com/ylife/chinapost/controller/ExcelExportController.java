package com.ylife.chinapost.controller;

import com.google.gson.reflect.TypeToken;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.*;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.form.mapper.pojo.InventoryHsitoryByEnterpriseForm;
import com.ylife.form.model.*;
import com.ylife.form.service.InventoryHistoryFormService;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.*;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.system.model.*;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.utils.*;
import com.ylife.utils.DateUtil;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by XiaoBiaoGe on 2016/5/30.
 * Excel导出
 */
@Controller
@SecurityResource(parent = "/web/UCoinManager", primary = false)
@RequestMapping(value = "/web/api/exportExcel")
public class ExcelExportController {

    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private OrderService orderService;
    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private UcoinBillManageService ucoinBillManageService;
    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private BusinessTypeService businessTypeService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private CustomerManageService customerManageService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private SystemManageService systemManageService;
    @Resource
    private FormInfoService formInfoService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private InventoryHistoryFormService inventoryHistoryFormService;
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;


    private Parser parser = new SimpleParser();

    /**
     * 导出区域汇总对账单
     *
     * @param enterpriseId
     * @param start     开始日期
     * @param end       结束日期
     * @param statuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyAccount")
    public ModelAndView exportSupplyAccount(@RequestParam("enterpriseId") Long enterpriseId,
                                            @RequestParam(value="start",required = false) String start,
                                            @RequestParam(value="end",required = false) String end,
                                            @RequestParam(value = "statuss", required = false) List<PurchaseBillStatus> statuss) {
        Date startTime = null;
        Date endTime = null;
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }


        //1、导出的数据源并处理
        List<SupplyAccount> supplyAccountList = formInfoService.getSupplyAccountStatistic(enterpriseId,startTime,endTime,statuss);
        List<List<String>> outList = new ArrayList<>();
        for (SupplyAccount item : supplyAccountList) {
            List<String> sonList = new ArrayList<>();
            sonList.add(Constants.nullString(item.getEnterpriseName()));
            sonList.add(Constants.nullString(item.getPurchaseDate()));
            sonList.add(Constants.defaultToString(item.getBossFee()));
            sonList.add(Constants.defaultToString(item.getFlmFee()));
            sonList.add(Constants.defaultToString(item.getJlyFee()));
            sonList.add(Constants.defaultToString(item.getLwFee()));
            sonList.add(Constants.defaultToString(item.getNyFee()));
            sonList.add(Constants.defaultToString(item.getThFee()));
            sonList.add(Constants.defaultToString(item.getYhFee()));
            sonList.add(Constants.defaultToString(item.getYshFee()));
            sonList.add(Constants.defaultToString(item.getTotalFee()));
            outList.add(sonList);
        }

        //excel所有列
        List<String> headList = Arrays.asList("账号名称", "采购日期", "Boss", "南邮", "福临门", "金龙鱼", "铁好", "乐维", "远恒", "优生活","合计");


        //总列数
        int headSize = headList.size();
        //数据总行数
        int bodySize = supplyAccountList.size();
        String sheetName = "区域对账汇总表";
        HSSFWorkbook wb = new HSSFWorkbook();
        //2、导出excel
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 1, sheetName);

        //3、处理excel
        HSSFSheet sheet = wb.getSheet(sheetName);
        //3.1 合并第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, headSize - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("区域对账汇总表");

        //4、下载excel
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "区域对账汇总表.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 确认前采购订单导出明细
     * */
    @RequestMapping("/exportPurchaseDetailByUnconfirmed")
    @ResponseBody
    public ModelAndView exportPurchaseDetailBy(long purchaseId){


        InventoryPurchaseBill itemMap = inventoryManageService.getPurchaseDetailByKey(purchaseId);

        HSSFWorkbook wb=new HSSFWorkbook();

        String sheetName="采购单明细表";

        List<String> heads =Arrays.asList("序号","仓库名称","网点地址","仓库联系人","货品名称","货品规格","货品编号","采购单价","采购数量","采购金额");
        HSSFCellStyle headStyle = ExcelUtil.headStyle(wb);
        List<List<String>> bodyList=new ArrayList<>();

        List<InventoryPurchaseBillItem> items=itemMap.getItems();
        int i=1;
        BigDecimal purchaseMoney=BigDecimal.ZERO;

        Integer sumAmcount=0;

        Map<Long,Integer> addressMap=new LinkedHashMap<>();
        int ignoreRow=5;
        int starRow=6;
        for(InventoryPurchaseBillItem item:items){
            Long enterpriseId=item.getEnterpriseId();
            if (!addressMap.containsKey(enterpriseId)){
                addressMap.put(enterpriseId,starRow);
                starRow++;
            }else {
                addressMap.put(enterpriseId,starRow);
                starRow++;
            }
            List<String> sonList=new ArrayList<>();
            sonList.add(Constants.figureToString(i));
            i++;
            sonList.add(item.getEnterpriseName());
	        sonList.add(item.getAddress());
	        String linkman = item.getLinkman();
	        String linkMobile = item.getLinkMobile();
	        if(StringUtil.isBlank(linkman)) {
		        linkman = "";
	        }
	        if(StringUtil.isBlank(linkMobile)) {
		        linkMobile = "";
	        }
	        sonList.add(linkman+"\r\n"+linkMobile);
            sonList.add( item.getInfo().getGoodsInfoName());
            sonList.add(item.getInfo().getSpecString());
            sonList.add(item.getInfo().getGoodsInfoItemNo());
            sonList.add(Constants.figureToString(item.getSettlePrice()));
            sonList.add(Constants.figureToString(item.getSumAmount()));
            sumAmcount+=item.getSumAmount();
            sonList.add(Constants.figureToString(item.getTotalPrice()));
            purchaseMoney=item.getTotalPrice().add(purchaseMoney);
            bodyList.add(sonList);

        }

        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        HSSFSheet sheet=wb.getSheet(sheetName);
        HSSFRow row=sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue("采购单明细表");
        cell.setCellStyle(ExcelUtil.headStyle(wb));

        //第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, heads.size()-1);
        sheet.addMergedRegion(address);

        //第二行
        row=sheet.getRow(1);
        HSSFCell codeCell=row.getCell(0);
        codeCell.setCellValue("单据编号："+itemMap.getCode());
        CellRangeAddress code = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(code);
        codeCell.setCellStyle(headStyle);

        HSSFCell enterpriseCell = row.getCell(2);
        enterpriseCell.setCellValue("申请账号：南京区");
        CellRangeAddress enterprise = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(enterprise);
        enterpriseCell.setCellStyle(headStyle);

        HSSFCell timeCell = row.getCell(4);
        Date applyTime=itemMap.getCreateTime();
        String timeString=DateUtil.formatToString(applyTime,"yyyy-MM-dd");
        timeCell.setCellValue("申请日期："+timeString);
        CellRangeAddress time = new CellRangeAddress(1, 1, 4, 5);
        sheet.addMergedRegion(time);
        timeCell.setCellStyle(headStyle);

        HSSFCell thirdCell = row.getCell(6);
        thirdCell.setCellValue("供应商："+itemMap.getThirdName());
        CellRangeAddress thirdName = new CellRangeAddress(1, 1, 6, 9);
        sheet.addMergedRegion(thirdName);
        thirdCell.setCellStyle(headStyle);

        //第三行
        row=sheet.getRow(2);
        HSSFCell stateCell=row.getCell(0);
        stateCell.setCellValue("当前状态："+itemMap.getStatus().getName());
        CellRangeAddress state = new CellRangeAddress(2, 2, 0, 1);
        sheet.addMergedRegion(state);
        stateCell.setCellStyle(headStyle);

        HSSFCell marksCell=row.getCell(2);
        marksCell.setCellValue("备注："+Constants.nullString(itemMap.getMarks()));
        CellRangeAddress marks = new CellRangeAddress(2, 2, 2, heads.size()-1);
        sheet.addMergedRegion(marks);
        marksCell.setCellStyle(headStyle);


        //第四行
        row=sheet.getRow(3);
        HSSFCell sumCell=row.getCell(0);
        sumCell.setCellValue("采购总数："+Constants.figureToString(sumAmcount));
        CellRangeAddress sum = new CellRangeAddress(3, 3, 0, 1);
        sheet.addMergedRegion(sum);
        sumCell.setCellStyle(headStyle);

        HSSFCell sumPriceCell=row.getCell(2);
        sumPriceCell.setCellValue("采购总金额："+Constants.figureToString(purchaseMoney));
        CellRangeAddress sumPrice = new CellRangeAddress(3, 3, 2,3);
        sheet.addMergedRegion(sumPrice);
        sumPriceCell.setCellStyle(headStyle);

        row=sheet.getRow(4);
        CellRangeAddress fro = new CellRangeAddress(4, 4, 0, heads.size()-1);
        sheet.addMergedRegion(fro);

        int startRow = 6;
        for (Long id : addressMap.keySet()) {
            int endRow = addressMap.get(id);
            address = new CellRangeAddress(startRow, endRow, 1, 1);
            sheet.addMergedRegion(address);
	        address = new CellRangeAddress(startRow, endRow, 2, 2);
	        sheet.addMergedRegion(address);
	        address = new CellRangeAddress(startRow, endRow, 3, 3);
	        sheet.addMergedRegion(address);
            startRow = endRow+1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);

    }

    /**
     * 确认前采购订单导出汇总
     * */
    @RequestMapping("/exportPurchaseSumByUnconfirmed")
    @ResponseBody
    public ModelAndView exportPurchaseSumByUnconfirmed(long purchaseId){


        InventoryPurchaseBill itemMap = inventoryManageService.getSumDetailByPrimaryKey(purchaseId);

        HSSFWorkbook wb=new HSSFWorkbook();

        String sheetName="采购单汇总表";

        List<String> heads =Arrays.asList("序号","货品名称","货品规格","货品编号","采购单价","采购数量","采购金额");
        List<List<String>> bodyList=new ArrayList<>();

        List<InventoryPurchaseBillItem> items=itemMap.getItems();
        int i=1;
        BigDecimal purchaseMoney=BigDecimal.ZERO;

        Integer sumAmcount=0;

        int ignoreRow=5;
        for(InventoryPurchaseBillItem item:items){
            int sumAmount;
            if(item.getSumAmount()==null){
                 sumAmount = 0;
            }else {
                sumAmount= item.getSumAmount();
            }

            List<String> sonList=new ArrayList<>();
            sonList.add(Constants.figureToString(i));
            i++;
            sonList.add( item.getInfo().getGoodsInfoName());
            sonList.add(item.getInfo().getSpecString());
            sonList.add(item.getInfo().getGoodsInfoItemNo());
            sonList.add(Constants.figureToString(item.getSettlePrice()));
            sonList.add(Constants.figureToString(sumAmount));
            sumAmcount+=sumAmount;
            sonList.add(Constants.figureToString(item.getSettlePrice().multiply(new BigDecimal(sumAmount))));
            purchaseMoney=item.getSettlePrice().multiply(new BigDecimal(sumAmount)).add(purchaseMoney);
            bodyList.add(sonList);
        }

        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        HSSFSheet sheet=wb.getSheet(sheetName);
        HSSFRow row=sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue("采购订单汇总表");
        cell.setCellStyle(ExcelUtil.headStyle(wb));

        //第一行
	    CellRangeAddress address = new CellRangeAddress(0, 0, 0, heads.size()-1);
	    sheet.addMergedRegion(address);

        //第二行
        row=sheet.getRow(1);
        HSSFCell codeCell=row.getCell(0);
        codeCell.setCellValue("单据编号："+itemMap.getCode());
        CellRangeAddress code = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(code);

        HSSFCell enterpriseCell = row.getCell(2);
        enterpriseCell.setCellValue("申请账号：南京区");
        CellRangeAddress enterprise = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(enterprise);

        HSSFCell timeCell = row.getCell(4);
        Date applyTime=itemMap.getCreateTime();
        String timeString=DateUtil.formatToString(applyTime,"yyyy-MM-dd");
        timeCell.setCellValue("申请日期："+timeString);
        CellRangeAddress time = new CellRangeAddress(1, 1, 4, 5);
        sheet.addMergedRegion(time);

        HSSFCell thirdCell = row.getCell(6);
        thirdCell.setCellValue("供应商："+itemMap.getThirdName());
        CellRangeAddress thirdName = new CellRangeAddress(1, 1, 6, 6);
        sheet.addMergedRegion(thirdName);

        //第三行
        row=sheet.getRow(2);
        HSSFCell stateCell=row.getCell(0);
        stateCell.setCellValue("当前状态："+itemMap.getStatus().getName());
        CellRangeAddress state = new CellRangeAddress(2, 2, 0, 1);
        sheet.addMergedRegion(state);

        HSSFCell marksCell=row.getCell(2);
        marksCell.setCellValue("备注："+Constants.nullString(itemMap.getMarks()));
        CellRangeAddress marks = new CellRangeAddress(2, 2, 2, heads.size()-1);
        sheet.addMergedRegion(marks);


        //第四行
        row=sheet.getRow(3);
        HSSFCell sumCell=row.getCell(0);
        sumCell.setCellValue("采购总数："+Constants.figureToString(sumAmcount));
        CellRangeAddress sum = new CellRangeAddress(3, 3, 0, 1);
        sheet.addMergedRegion(sum);

        HSSFCell sumPriceCell=row.getCell(2);
        sumPriceCell.setCellValue("采购总金额："+Constants.figureToString(purchaseMoney));
        CellRangeAddress sumPrice = new CellRangeAddress(3, 3, 2,3);
        sheet.addMergedRegion(sumPrice);

        row=sheet.getRow(4);
        CellRangeAddress fro = new CellRangeAddress(4, 4, 0, heads.size()-1);
        sheet.addMergedRegion(fro);


        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);

    }


    /**
     * 确认后采购订单导出明细
     * */
    @RequestMapping("/exportPurchaseDetailByConfirm")
    @ResponseBody
    public ModelAndView exportPurchaseDetailByConfirm(long purchaseId){


        InventoryPurchaseBill itemMap = inventoryManageService.getPurchaseDetailByKey(purchaseId);

        HSSFWorkbook wb=new HSSFWorkbook();

        String sheetName="采购单明细表";

        List<String> heads =Arrays.asList("序号","仓库名称","网点地址","仓库联系人","货品名称","货品规格","货品编号","采购单价","采购数量","采购金额","入库数量","入库金额");
        List<List<String>> bodyList=new ArrayList<>();

        List<InventoryPurchaseBillItem> items=itemMap.getItems();
        int i=1;
        BigDecimal purchaseMoney=BigDecimal.ZERO;
        BigDecimal StorageMoney=BigDecimal.ZERO;
        Integer sumAmcount=0;
        Integer sumStorage = 0;

        Map<Long,Integer> addressMap=new LinkedHashMap<>();
        int ignoreRow=5;
        int starRow=6;
        for(InventoryPurchaseBillItem item:items){
            Long enterpriseId=item.getEnterpriseId();
            if (!addressMap.containsKey(enterpriseId)){
                addressMap.put(enterpriseId,starRow);
                starRow++;
            }else {
                addressMap.put(enterpriseId,starRow);
                starRow++;

            }
            int receiptAmount = 0;
            if (item.getReceiptAmount()==null){
                receiptAmount=0;
            }else {
                receiptAmount=item.getReceiptAmount();
            }

            int sumAmount;
            if(item.getSumAmount()==null){
                sumAmount = 0;
            }else {
                sumAmount= item.getSumAmount();
            }
            List<String> sonList=new ArrayList<>();
            sonList.add(Constants.figureToString(i));
            i++;
            sonList.add(item.getEnterpriseName());
	        sonList.add(item.getAddress());
	        String linkman = item.getLinkman();
	        String linkMobile = item.getLinkMobile();
	        if(StringUtil.isBlank(linkman)) {
		        linkman = "";
	        }
	        if(StringUtil.isBlank(linkMobile)) {
		        linkMobile = "";
	        }
	        sonList.add(linkman+"\r\n"+linkMobile);
            sonList.add( item.getInfo().getGoodsInfoName());
            sonList.add(item.getInfo().getSpecString());
            sonList.add(item.getInfo().getGoodsInfoItemNo());
            sonList.add(Constants.figureToString(item.getSettlePrice()));
            sonList.add(Constants.figureToString(sumAmount));
            sumAmcount+=sumAmount;
            sonList.add(Constants.figureToString(item.getTotalPrice()));
            purchaseMoney=item.getTotalPrice().add(purchaseMoney);
            sonList.add(Constants.figureToString(receiptAmount));
            sumStorage+=receiptAmount;
            sonList.add(Constants.figureToString(item.getSettlePrice().multiply(new BigDecimal(receiptAmount))));
            StorageMoney = StorageMoney.add(item.getSettlePrice().multiply(new BigDecimal(receiptAmount)));
            bodyList.add(sonList);

        }

        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        HSSFSheet sheet=wb.getSheet(sheetName);
        HSSFRow row=sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue("采购单明细表");
        cell.setCellStyle(ExcelUtil.headStyle(wb));

        //第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, heads.size()-1);
        sheet.addMergedRegion(address);

        //第二行
        row=sheet.getRow(1);
        HSSFCell codeCell=row.getCell(0);
        codeCell.setCellValue("单据编号："+itemMap.getCode());
        CellRangeAddress code = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(code);

        HSSFCell enterpriseCell = row.getCell(2);
        enterpriseCell.setCellValue("申请账号：南京区");
        CellRangeAddress enterprise = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(enterprise);

        HSSFCell timeCell = row.getCell(4);
        Date applyTime=itemMap.getCreateTime();
        String timeString=DateUtil.formatToString(applyTime,"yyyy-MM-dd");
        timeCell.setCellValue("申请日期："+timeString);
        CellRangeAddress time = new CellRangeAddress(1, 1, 4, 5);
        sheet.addMergedRegion(time);

        HSSFCell thirdCell = row.getCell(6);
        thirdCell.setCellValue("供应商："+itemMap.getThirdName());
        CellRangeAddress thirdName = new CellRangeAddress(1, 1, 6, 7);
        sheet.addMergedRegion(thirdName);

        HSSFCell stateCell=row.getCell(8);
        stateCell.setCellValue("当前状态："+itemMap.getStatus().getName());
        CellRangeAddress state = new CellRangeAddress(1, 1, 8, 11);
        sheet.addMergedRegion(state);

        row=sheet.getRow(2);
        HSSFCell marksCell=row.getCell(0);
        marksCell.setCellValue("备注："+Constants.nullString(itemMap.getMarks()));
        CellRangeAddress marks = new CellRangeAddress(2, 2, 0, heads.size()-1);
        sheet.addMergedRegion(marks);


        //第四行
        row=sheet.getRow(3);
        HSSFCell sumCell=row.getCell(0);
        sumCell.setCellValue("采购总数："+Constants.figureToString(sumAmcount));
        CellRangeAddress sum = new CellRangeAddress(3, 3, 0, 1);
        sheet.addMergedRegion(sum);

        HSSFCell sumPriceCell=row.getCell(2);
        sumPriceCell.setCellValue("采购总金额："+Constants.figureToString(purchaseMoney));
        CellRangeAddress sumPrice = new CellRangeAddress(3, 3, 2,3);
        sheet.addMergedRegion(sumPrice);

        HSSFCell sumStorageCell=row.getCell(4);
        sumStorageCell.setCellValue("入库数量："+Constants.figureToString(sumStorage));
        CellRangeAddress sumStorageAmount = new CellRangeAddress(3, 3, 4,5);
        sheet.addMergedRegion(sumStorageAmount);

        HSSFCell StorageMoneyCell=row.getCell(6);
        StorageMoneyCell.setCellValue("入库金额："+Constants.figureToString(StorageMoney));
        CellRangeAddress StorageMoneyPrice = new CellRangeAddress(3, 3, 6,7);
        sheet.addMergedRegion(StorageMoneyPrice);

        row=sheet.getRow(4);
        CellRangeAddress fro = new CellRangeAddress(4, 4, 0, heads.size()-1);
        sheet.addMergedRegion(fro);

        int startRow = 6;
        for (Long id : addressMap.keySet()) {
            int endRow = addressMap.get(id);
            address = new CellRangeAddress(startRow, endRow, 1, 1);
	        sheet.addMergedRegion(address);
	        address = new CellRangeAddress(startRow, endRow, 2, 2);
	        sheet.addMergedRegion(address);
	        address = new CellRangeAddress(startRow, endRow, 3, 3);
	        sheet.addMergedRegion(address);
            startRow = endRow + 1;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);

    }


    /**
     * 确认后采购订单导出汇总
     * */
    @RequestMapping("/exportPurchaseSumByConfirm")
    @ResponseBody
    public ModelAndView exportPurchaseSumByConfirm(long purchaseId){


        InventoryPurchaseBill itemMap = inventoryManageService.getSumDetailByPrimaryKey(purchaseId);

        HSSFWorkbook wb=new HSSFWorkbook();

        String sheetName="采购单汇总表";

        List<String> heads =Arrays.asList("序号","货品名称","货品规格","货品编号","采购单价","采购数量","采购金额","入库数量","入库金额");
        List<List<String>> bodyList=new ArrayList<>();

        List<InventoryPurchaseBillItem> items=itemMap.getItems();
        int i=1;
        BigDecimal purchaseMoney=BigDecimal.ZERO;
        BigDecimal StorageMoney=BigDecimal.ZERO;
        Integer sumAmcount=0;
        Integer sumStorage = 0;

        Map<Long,Integer> addressMap=new LinkedHashMap<>();
        int ignoreRow=5;

        for(InventoryPurchaseBillItem item:items){

            int receiptAmount = 0;
            if (item.getReceiptAmount()==null){
                receiptAmount=0;
            }else {
                receiptAmount=item.getReceiptAmount();
            }
            int sumAmount;
            if(item.getSumAmount()==null){
                sumAmount = 0;
            }else {
                sumAmount= item.getSumAmount();
            }
            List<String> sonList=new ArrayList<>();
            sonList.add(Constants.figureToString(i));
            i++;
            sonList.add( item.getInfo().getGoodsInfoName());
            sonList.add(item.getInfo().getSpecString());
            sonList.add(item.getInfo().getGoodsInfoItemNo());
            sonList.add(Constants.figureToString(item.getSettlePrice()));
            sonList.add(Constants.figureToString(sumAmount));
            sumAmcount+=sumAmount;
            sonList.add(Constants.figureToString(item.getSettlePrice().multiply(new BigDecimal(sumAmount))));
            purchaseMoney=item.getSettlePrice().multiply(new BigDecimal(sumAmount)).add(purchaseMoney);
            sonList.add(Constants.figureToString(receiptAmount));
            sumStorage+=receiptAmount;
            sonList.add(Constants.figureToString(item.getSettlePrice().multiply(new BigDecimal(receiptAmount))));
            StorageMoney = StorageMoney.add(item.getSettlePrice().multiply(new BigDecimal(receiptAmount)));
            bodyList.add(sonList);

        }

        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        HSSFSheet sheet=wb.getSheet(sheetName);
        HSSFRow row=sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue("采购单汇总表");
        cell.setCellStyle(ExcelUtil.headStyle(wb));

        //第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, heads.size()-1);
        sheet.addMergedRegion(address);

        //第二行
        row=sheet.getRow(1);
        HSSFCell codeCell=row.getCell(0);
        codeCell.setCellValue("单据编号："+itemMap.getCode());
        CellRangeAddress code = new CellRangeAddress(1, 1, 0, 1);
        sheet.addMergedRegion(code);

        HSSFCell enterpriseCell = row.getCell(2);
        enterpriseCell.setCellValue("申请账号：南京区");
        CellRangeAddress enterprise = new CellRangeAddress(1, 1, 2, 3);
        sheet.addMergedRegion(enterprise);

        HSSFCell timeCell = row.getCell(4);
        Date applyTime=itemMap.getCreateTime();
        String timeString=DateUtil.formatToString(applyTime,"yyyy-MM-dd");
        timeCell.setCellValue("申请日期："+timeString);
        CellRangeAddress time = new CellRangeAddress(1, 1, 4, 5);
        sheet.addMergedRegion(time);

        HSSFCell thirdCell = row.getCell(6);
        thirdCell.setCellValue("供应商："+itemMap.getThirdName());
        CellRangeAddress thirdName = new CellRangeAddress(1, 1, 6, 7);
        sheet.addMergedRegion(thirdName);

        HSSFCell stateCell=row.getCell(8);
        stateCell.setCellValue("当前状态："+itemMap.getStatus().getName());
        CellRangeAddress state = new CellRangeAddress(1, 1, 8, 8);
        sheet.addMergedRegion(state);

        row=sheet.getRow(2);
        HSSFCell marksCell=row.getCell(0);
        marksCell.setCellValue("备注："+Constants.nullString(itemMap.getMarks()));
        CellRangeAddress marks = new CellRangeAddress(2, 2, 0, heads.size()-1);
        sheet.addMergedRegion(marks);


        //第四行
        row=sheet.getRow(3);
        HSSFCell sumCell=row.getCell(0);
        sumCell.setCellValue("采购总数："+Constants.figureToString(sumAmcount));
        CellRangeAddress sum = new CellRangeAddress(3, 3, 0, 1);
        sheet.addMergedRegion(sum);

        HSSFCell sumPriceCell=row.getCell(2);
        sumPriceCell.setCellValue("采购总金额："+Constants.figureToString(purchaseMoney));
        CellRangeAddress sumPrice = new CellRangeAddress(3, 3, 2,3);
        sheet.addMergedRegion(sumPrice);

        HSSFCell sumStorageCell=row.getCell(4);
        sumStorageCell.setCellValue("入库数量："+Constants.figureToString(sumStorage));
        CellRangeAddress sumStorageAmount = new CellRangeAddress(3, 3, 4,5);
        sheet.addMergedRegion(sumStorageAmount);

        HSSFCell StorageMoneyCell=row.getCell(6);
        StorageMoneyCell.setCellValue("入库金额："+Constants.figureToString(StorageMoney));
        CellRangeAddress StorageMoneyPrice = new CellRangeAddress(3, 3, 6,7);
        sheet.addMergedRegion(StorageMoneyPrice);

        row=sheet.getRow(4);
        CellRangeAddress fro = new CellRangeAddress(4, 4, 0, heads.size()-1);
        sheet.addMergedRegion(fro);

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);

    }

	/**
	 * 按条件导出采购订单
	 * @param code 单据编号
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param billStatus 单据状态
	 * @param thirdId 第三方id
	 * @return
	 * */
	@SecurityResource(parent = "/web/InventoryManager", primary = false)
	@RequestMapping(value = "/exportPurchaseByCondition", method = RequestMethod.GET)
	public ModelAndView exportPurchaseByCondition(@RequestParam(value = "code", required = false) Long code,
	                                              @RequestParam(value = "start", required = false) String start,
	                                              @RequestParam(value = "end", required = false) String end,
	                                              @RequestParam(value = "billStatus", required = false) PurchaseBillStatus billStatus,
	                                              @RequestParam(value = "thirdId", required = false) Long thirdId){

		start=Constants.nullOrNotBlank(start);
		end=Constants.nullOrNotBlank(end);
		Date startTime = null;
		Date endTime = null;
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

		List<InventoryPurchaseBillItem> items = inventoryManageService.selectPurchaseBillListByCondition(code, billStatus, startTime, endTime, thirdId);

		HSSFWorkbook wb=new HSSFWorkbook();

		String sheetName="采购订单";

		List<String> heads =Arrays.asList("机构号","上级名称","网点名称","联系人","联系方式","联系地址","单据编号","单据状态","货品名称","单价","数量","金额","供应商","申请日期");
		List<List<String>> bodyList=new ArrayList<>();

		// 添加数据进body
		for(InventoryPurchaseBillItem item:items){
			List<String> sonList=new ArrayList<>();
			sonList.add(Constants.nullString(item.getAccountName()));
			sonList.add(Constants.nullString(item.getParentName()));
			sonList.add(item.getEnterpriseName());
			sonList.add(Constants.nullString(item.getLinkman()));
			sonList.add(Constants.nullString(item.getLinkMobile()));
			sonList.add(item.getAddress());
			sonList.add(item.getCode().toString());
			String status = null;
			PurchaseBillStatus purchaseBillStatus = item.getStatus();
			switch (purchaseBillStatus) {
				case TO_CONFIRM:
					status = "待确认";
					break;
				case TERMINATED:
					status = "已终止";
					break;
				case FINISHED:
					status = "已确认";
					break;
				case COMMODITY_STORAGE:
					status = "待商品入库";
					break;
				case FOR_SETTLEMENT:
					status = "待结算";
					break;
				case COMPLETED:
					status = "已完成";
					break;
			}
			sonList.add(status);
			sonList.add(item.getGoodsInfoName());
			sonList.add(Constants.figureToString(item.getSettlePrice()));
			sonList.add(Constants.figureToString(item.getAmount()));
			sonList.add(Constants.figureToString(item.getTotalPrice()));
			sonList.add(item.getThirdName());
			sonList.add(DateUtil.formatToString(item.getCreateTime(), Constants.DEFAULT_DATE_FORMAT));
			bodyList.add(sonList);
		}
		//创建Excel
		wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, 1, sheetName);

		HSSFSheet sheet=wb.getSheet(sheetName);
		HSSFRow row=sheet.getRow(0);
		HSSFCell cell=row.getCell(0);
		cell.setCellValue("采购订单");
		cell.setCellStyle(ExcelUtil.headStyle(wb));

		//第一行格式
		CellRangeAddress address = new CellRangeAddress(0, 0, 0, heads.size()-1);
		sheet.addMergedRegion(address);

		//设置名称格式及返回窗口
		Map<String, Object> map = new HashMap<>();
		map.put("wb", wb);
		String excelName = sheetName + ".xls";
		String finalFileName = null;
		try {
			finalFileName = URLEncoder.encode(excelName,"UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		map.put("fileName", finalFileName);
		ViewExcel viewExcel = new ViewExcel();
		return new ModelAndView(viewExcel, map);

	}






	//档excel导出数据超出63225条时候，HSSFWORKBOOK超出数据量上限,用XSSFWorkBook替代原工作薄，

    /**
     * 导出会员基础数据表的明细
     *
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/bigExportCustomerFormDetail")
    @ResponseBody
    public void bigExportCustomerFormDetail(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                            @RequestParam(value = "startTime", required = false) String startTime,
                                            @RequestParam(value = "endTime", required = false) String endTime,
                                            HttpServletResponse response,HttpServletRequest request) throws IOException {
        if (enterpriseId == null) enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        startTime = Constants.nullOrNotBlank(startTime);
        endTime = Constants.nullOrNotBlank(endTime);
        Date start = null;
        Date end = null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }

        //数据源
        Page<CustomerFormDetail> customerFormDetailPage = formInfoService.getCustomerFormByEnterpriseId(enterpriseId, start, end, null);
        List<CustomerFormDetail> customerFormDetailList = customerFormDetailPage.getContent();
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        String sheetName = "会员基础数据表-明细表";
        List<String> heads = Arrays.asList("上级名称", "网点名称", "姓名", "身份证号", "用户属性", "上期结余", "当前结余", "发放次数",
                "发放邮豆", "扣减次数", "扣减邮豆", "线下订单兑换次数", "线下订单兑换邮豆", "线下订单补贴邮豆", "线上兑换次数", "线上兑换邮豆", "兑换结算金额(元)", "退单次数", "退单结算金额(元)");
        List<List<String>> bodyList = new ArrayList<>();
        //记录合并位置的map:key-网点id，value-相同网点结束位置
        Map<Long, Integer> addressMap = new HashMap<>();
        int ignoreRow = 0;
        int sRow = ignoreRow + 1;
        int columeWidth = heads.size();
        for (CustomerFormDetail customerFormDetail : customerFormDetailList) {
            Long currentId = customerFormDetail.getCurrentId();
            if (!addressMap.containsKey(currentId)) {
                sRow++;
                addressMap.put(currentId, sRow);
            } else {
                sRow++;
                addressMap.put(currentId, sRow);
            }
            List<String> sonList = new ArrayList<>();
            sonList.add(customerFormDetail.getParentName());
            sonList.add(customerFormDetail.getCurrentName());
            sonList.add(customerFormDetail.getFullName());
            sonList.add(customerFormDetail.getIdCardNo());
            sonList.add(customerFormDetail.getCustomerRef().equals("old")?"老用户":"新用户");
            sonList.add(customerFormDetail.getLastPrice()==null?"0.00": Constants.figureToString(customerFormDetail.getLastPrice()));
            sonList.add(customerFormDetail.getCurrentPrice()==null?"0.00": Constants.figureToString(customerFormDetail.getCurrentPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getGrandFrequency()));
            sonList.add(customerFormDetail.getGrandPrice()==null?"0.00": Constants.figureToString(customerFormDetail.getGrandPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getDecutFrequency()));
            sonList.add(customerFormDetail.getDecutPrice()==null?"0.00": Constants.figureToString(customerFormDetail.getDecutPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeFrequencyOffline()));
            sonList.add(customerFormDetail.getConsumePriceOffline()==null?"0.00": Constants.figureToString(customerFormDetail.getConsumePriceOffline()));
            sonList.add(customerFormDetail.getConsumeSubsidyPriceOffline()==null?"0.00": Constants.figureToString(customerFormDetail.getConsumeSubsidyPriceOffline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeFrequencyOnline()));
            sonList.add(customerFormDetail.getConsumePriceOnline()==null?"0.00": Constants.figureToString(customerFormDetail.getConsumePriceOnline()));
            sonList.add(customerFormDetail.getConsumeSettlePrice()==null?"0.00": Constants.figureToString(customerFormDetail.getConsumeSettlePrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getRefundFrequency()));
            sonList.add(customerFormDetail.getRefundSettlePrice()==null?"0.00": Constants.figureToString(customerFormDetail.getRefundSettlePrice()));
            bodyList.add(sonList);
        }

        wb = ExcelUtilXssf.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);

        //给一个傻逼表头
//       Sheet sheet = wb.getSheet(sheetName);
//        CellRangeAddress address = new CellRangeAddress(0, 0, 0, columeWidth - 1);
//        sheet.addMergedRegion(address);
//       Row row = sheet.createRow(0);
//       Cell cell = row.createCell(0);
//        cell.setCellValue(sheetName);
//        //对正表表头做他么的分组
//        address = new CellRangeAddress(1, 1, 0, 6);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 1, 7, 8);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 1, 9, 10);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 1, 11, 13);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 1, 14, 15);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 1, 17, 18);
//        sheet.addMergedRegion(address);
//        address = new CellRangeAddress(1, 2, 16, 16);
//        sheet.addMergedRegion(address);
//        row = sheet.getRow(1);
//        cell = row.getCell(0);
//        cell.setCellValue("用户基本情况");
//        cell = row.getCell(7);
//        cell.setCellValue("发放情况");
//        cell = row.getCell(9);
//        cell.setCellValue("扣减情况");
//        cell = row.getCell(11);
//        cell.setCellValue("线下订单");
//        cell = row.getCell(14);
//        cell.setCellValue("线上订单");
//        cell = row.getCell(17);
//        cell.setCellValue("退单");
//        int startRow = 3;
//        for (Long id : addressMap.keySet()) {
//            int endRow = addressMap.get(id);
//            address = new CellRangeAddress(startRow, endRow, 0, 0);
//            sheet.addMergedRegion(address);
//            address = new CellRangeAddress(startRow, endRow, 1, 1);
//            sheet.addMergedRegion(address);
//            startRow = endRow + 1;
//        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName+".xlsx";
        try {
            excelName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }

    /**
     * 设置下载文件中文件的名称
     *
     * @param filename
     * @param request
     * @return
     */
    public static String encodeFilename(String filename, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE
         * 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1;
         * zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (agent.contains("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = org.springframework.util.StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = org.springframework.util.StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (agent.contains("Mozilla")))
                return MimeUtility.encodeText(filename, "UTF-8", "B");
            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }

    /**
     * 导出会员基础数据表的明细
     *
     * @param enterpriseId
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/exportCustomerFormDetail")
    @ResponseBody
    public ModelAndView exportCustomerFormDetail(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                                 @RequestParam(value = "startTime", required = false) String startTime,
                                                 @RequestParam(value = "endTime", required = false) String endTime) {
        if (enterpriseId == null) enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        startTime = Constants.nullOrNotBlank(startTime);
        endTime = Constants.nullOrNotBlank(endTime);
        Date start = null;
        Date end = null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }

        //数据源
        Page<CustomerFormDetail> customerFormDetailPage = formInfoService.getCustomerFormByEnterpriseId(enterpriseId, start, end, null);
        List<CustomerFormDetail> customerFormDetailList = customerFormDetailPage.getContent();

        HSSFWorkbook wb = new HSSFWorkbook();
        String sheetName = "会员基础数据表-明细表";
        List<String> heads = Arrays.asList("上级名称", "网点名称", "姓名", "身份证号", "用户属性", "上期结余", "当前结余", "发放次数",
                "发放邮豆", "扣减次数", "扣减邮豆", "兑换次数", "兑换邮豆", "补贴邮豆", "兑换次数", "兑换邮豆", "兑换结算金额(元)", "退单次数", "退单结算金额(元)");
        List<List<String>> bodyList = new ArrayList<>();
        //记录合并位置的map:key-网点id，value-相同网点结束位置
        Map<Long, Integer> addressMap = new HashMap<>();
        int ignoreRow = 2;
        int sRow=ignoreRow+1;
        int columeWidth = heads.size();
        for (CustomerFormDetail customerFormDetail : customerFormDetailList) {
            Long currentId = customerFormDetail.getCurrentId();
            if (!addressMap.containsKey(currentId)) {
                sRow++;
                addressMap.put(currentId, sRow);
            } else {
                sRow++;
                addressMap.put(currentId, sRow);
            }
            List<String> sonList = new ArrayList<>();
            sonList.add(customerFormDetail.getParentName());
            sonList.add(customerFormDetail.getCurrentName());
            sonList.add(customerFormDetail.getFullName());
            sonList.add(customerFormDetail.getIdCardNo());
            sonList.add(customerFormDetail.getCustomerRef());
            sonList.add(Constants.figureToString(customerFormDetail.getLastPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getCurrentPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getGrandFrequency()));
            sonList.add(Constants.figureToString(customerFormDetail.getGrandPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getDecutFrequency()));
            sonList.add(Constants.figureToString(customerFormDetail.getDecutPrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeFrequencyOffline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumePriceOffline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeSubsidyPriceOffline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeFrequencyOnline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumePriceOnline()));
            sonList.add(Constants.figureToString(customerFormDetail.getConsumeSettlePrice()));
            sonList.add(Constants.figureToString(customerFormDetail.getRefundFrequency()));
            sonList.add(Constants.figureToString(customerFormDetail.getRefundSettlePrice()));
            bodyList.add(sonList);
        }

        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);

        //给一个傻逼表头
        HSSFSheet sheet = wb.getSheet(sheetName);
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, columeWidth - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue(sheetName);

        //对正表表头做他么的分组
        address = new CellRangeAddress(1, 1, 0, 6);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 1, 7, 8);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 1, 9, 10);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 1, 11, 13);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 1, 14, 15);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 1, 17, 18);
        sheet.addMergedRegion(address);
        address = new CellRangeAddress(1, 2, 16, 16);
        sheet.addMergedRegion(address);
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("用户基本情况");
        cell = row.getCell(7);
        cell.setCellValue("发放情况");
        cell = row.getCell(9);
        cell.setCellValue("扣减情况");
        cell = row.getCell(11);
        cell.setCellValue("线下订单");
        cell = row.getCell(14);
        cell.setCellValue("线上订单");
        cell = row.getCell(17);
        cell.setCellValue("退单");
        int startRow = 3;
        for (Long id : addressMap.keySet()) {
            int endRow = addressMap.get(id);
            address = new CellRangeAddress(startRow, endRow, 0, 0);
            sheet.addMergedRegion(address);
            address = new CellRangeAddress(startRow, endRow, 1, 1);
            sheet.addMergedRegion(address);
            startRow = endRow + 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 导出会员基础数据统计表
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/exportCustomerFormStatistic")
    @ResponseBody
    public ModelAndView exportCustomerFormStatistic(@RequestParam(value = "startTime") String startTime,
                                                    @RequestParam(value = "endTime") String endTime) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        startTime = Constants.nullOrNotBlank(startTime);
        endTime = Constants.nullOrNotBlank(endTime);
        Date start = null;
        Date end = null;
        if (startTime != null) {
            start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        } else {
            start = DateUtil.fromString("1970-01-01", Constants.DEFAULT_DAY_FORMAT);
        }
        if (endTime != null) {
            end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        } else {
            end = DateUtil.getNight(new Date());
        }

        HSSFWorkbook wb = new HSSFWorkbook();
        String sheetName = "会员基础数据-统计表";
        List<String> heads = Arrays.asList("机构名称", "用户属性", "用户数", "发放用户数", "发放邮豆",
                "扣减用户数", "扣减邮豆", "兑换用户数","兑换邮豆","补贴用户数", "补贴邮豆", "兑换结算金额（元）",
                "退单结算金额（元）", "会员邮豆余额");
        int columnWidth = heads.size();
        int ignoreRow=1;

        //数据源
        List<CustomerFormStatistic> customerFormStatisticList = outList(enterpriseId, start, end);
        List<List<String>> bodyList = new ArrayList<>();
        for (CustomerFormStatistic data : customerFormStatisticList) {
            List<String> sonList = new ArrayList<>();
            sonList.add(data.getEnterpriseName());
            sonList.add(data.getCustomerRef().equals("old")?"老用户":"新用户");
            sonList.add(data.getCustomerAmount()==null?"0":Constants.figureToString(data.getCustomerAmount()));
            sonList.add(data.getGrandCustomerAmount()==null?"0":Constants.figureToString(data.getGrandCustomerAmount()));
            sonList.add(data.getGrandPrice()==null?"0.00":Constants.figureToString(data.getGrandPrice()));
            sonList.add(data.getDecutCustomerAmount()== null?"0":Constants.figureToString(data.getDecutCustomerAmount()));
            sonList.add(data.getDecutPrice()==null?"0.00":Constants.figureToString(data.getDecutPrice()));
            sonList.add(data.getConsumeCustomerAmount()== null?"0":Constants.figureToString(data.getConsumeCustomerAmount()));
            sonList.add(data.getConsumePrice()==null?"0.00":Constants.figureToString(data.getConsumePrice()));
            sonList.add(data.getConsumeSubsidyCustomerAmount() ==null?"0":Constants.figureToString(data.getConsumeSubsidyCustomerAmount()));
            sonList.add(data.getConsumeSubsidyPrice()==null?"0.00":Constants.figureToString(data.getConsumeSubsidyPrice()));
            sonList.add(data.getConsumeSettlePrice()==null?"0.00":Constants.figureToString(data.getConsumeSettlePrice()));
            sonList.add(data.getRefundSettlePrice()==null?"0.00":Constants.figureToString(data.getRefundSettlePrice()));
            sonList.add(data.getCurrentPrice()==null?"0.00":Constants.figureToString(data.getCurrentPrice()));
            bodyList.add(sonList);
        }
        wb = ExcelUtil.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        HSSFSheet sheet=wb.getSheet(sheetName);
        CellRangeAddress address=new CellRangeAddress(0,0,0,columnWidth-1);
        sheet.addMergedRegion(address);
        HSSFRow row=sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue(sheetName);
        for (int sRow = 2; sRow < bodyList.size() + 1; sRow += 2) {
            address = new CellRangeAddress(sRow,sRow + 1,0,0);
            sheet.addMergedRegion(address);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    private List<CustomerFormStatistic> outList(Long enterpriseId, Date start, Date end) {
        List<CustomerFormStatistic> outList = new ArrayList<>();
        List<CustomerFormStatistic> currents = formInfoService.getCustomerFormStatisticByenterpriseId(enterpriseId, start, end);
        for (int i = 0; i < currents.size(); i++) {
            outList.add(currents.get(i));
        }
        EnterpriseFunction function = enterpriseFunctionService.get(enterpriseId);
        if (!function.getEnd()) {
            List<EnterpriseFunction> enterpriseFunctionList = enterpriseFunctionService.getByParentId(enterpriseId);
            for (EnterpriseFunction enterpriseFunction : enterpriseFunctionList) {
                List<CustomerFormStatistic> sonList = outList(enterpriseFunction.getId(), start, end);
                for (CustomerFormStatistic customerFormStatistic : sonList) {
                    outList.add(customerFormStatistic);
                }
            }
        }
        return outList;
    }


    /**
     * 财富统计报表导出
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @RequestMapping(value = "/exportWealthForm")
    public ModelAndView exportWealthForm(@RequestParam(value = "startTime", required = false) String startTime,
                                         @RequestParam(value = "endTime", required = false) String endTime) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Assert.notNull(enterpriseId, "请选定机构");
        startTime = Constants.nullOrNotBlank(startTime);
        endTime = Constants.nullOrNotBlank(endTime);
        Date start = null;
        Date end = null;
        if (startTime != null) start = DateUtil.fromString(startTime, Constants.DEFAULT_DAY_FORMAT);
        if (endTime != null) end = DateUtil.getNight(DateUtil.fromString(endTime, Constants.DEFAULT_DAY_FORMAT));
        HSSFWorkbook wb = new HSSFWorkbook();
        String sheetName = "财富统计报表";
        List<String> headList = Arrays.asList("机构名称", "累计预拨", "发放邮豆总额", "扣减邮豆总额", "补贴邮豆总额", "退还邮豆总额", "剩余邮豆总额", "剩余现金价值（元）");
        List<List<String>> outList = new ArrayList<>();
        //数据源
        List<WealthForm> wealthFormList = new ArrayList<>();
        //获取该节点及以下所有节点的id
        List<Long> ids = enterpriseFunctionService.getAllNode(enterpriseId);
        for (Long id : ids) {
            WealthForm wealthForm = formInfoService.getWealthFormByEnterpriseId(id, start, end);
            wealthForm.setRemainCash(wealthForm.getRemainUdou());
            wealthFormList.add(wealthForm);
        }

        for (WealthForm outWealthForm : wealthFormList) {
            List<String> sonList = new ArrayList<>();
            sonList.add(outWealthForm.getEnterpriseName());
            sonList.add(outWealthForm.getAppropriation()==null?"0.00": Constants.figureToString(outWealthForm.getAppropriation()));
            sonList.add(outWealthForm.getGrandUdou()==null?"0.00": Constants.figureToString(outWealthForm.getGrandUdou()));
            sonList.add(outWealthForm.getDecutUdou()==null?"0.00": Constants.figureToString(outWealthForm.getDecutUdou()));
            sonList.add(outWealthForm.getSubsidyUdou()==null?"0.00": Constants.figureToString(outWealthForm.getSubsidyUdou()));
            sonList.add(outWealthForm.getBackUdou()==null?"0.00": Constants.figureToString(outWealthForm.getBackUdou()));
            sonList.add(outWealthForm.getRemainUdou()==null?"0.00": Constants.figureToString(outWealthForm.getRemainUdou()));
            sonList.add(outWealthForm.getRemainCash()==null?"0.00": Constants.figureToString(outWealthForm.getRemainCash()));
            outList.add(sonList);
        }

        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 1, sheetName);

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName + ".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    /**
     * 导出网点供货明细
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param goodsInfoType
     * @param billStatuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyStatistical")
    public ModelAndView exportSupplyStatistical(@RequestParam("enterpriseId") Long enterpriseId,
                                                @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                                @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }


        Page<SupplyForm> supplyDetaqils = formInfoService.getSupplyStatistical(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, goodsInfoType, billStatuss, null);

        List<SupplyForm> supplyForms = supplyDetaqils.getContent();
        List<List<String>> outList = new ArrayList<>();
        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        for (SupplyForm item : supplyForms) {
            if (addressMap.get(item.getEnterpriseId()) == null) {
                addressMap.put(item.getEnterpriseId(), s);
            }
            s++;
            addressMap.put(item.getEnterpriseId(), s);
            List<String> sonList = new ArrayList<>();
            sonList.add(Constants.nullString(item.getEnterpriseName()) + "\r\n（合计金额：" + Constants.figureToString(item.getTotalPrice()) + "）");
            sonList.add(Constants.nullString(item.getThirdName()));
            sonList.add(Constants.figureToString(item.getPrice()));
            outList.add(sonList);
        }

        //excel所有列
        List<String> headList = Arrays.asList("网点名称", "供应商名称", "金额（元）");


        //总列数
        int headSize = headList.size();
        //数据总行数
        int bodySize = supplyForms.size();
        String sheetName = "网点供货统计表";
        HSSFWorkbook wb = new HSSFWorkbook();
        //2、导出excel
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        //3、处理excel
        HSSFSheet sheet = wb.getSheet(sheetName);
        //3.1 合并第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, headSize - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("网点供货统计表");
        //3.2 处理第二行
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("订货日期：" + Constants.nullString(orderStartTime) + "-" + Constants.nullString(orderEndTime) +
                "备货日期：" + Constants.nullString(readyStartTime) + "-" + Constants.nullString(readyEndTime) +
                "入库日期：" + Constants.nullString(warehouseStartTime) + "-" + Constants.nullString(warehouseEndTime));
        //3.3 合并处理第一列
        int i = 3;
        for (Long key : addressMap.keySet()) {
            row = sheet.getRow(i);
            cell = row.getCell(0);
            cell.setCellStyle(cellStyle);
            address = new CellRangeAddress(i, addressMap.get(key) - 1, 0, 0);
            sheet.addMergedRegion(address);
            i = addressMap.get(key);
        }

        //3.4 处理最后一行总计
        address = new CellRangeAddress(supplyForms.size() + 2, supplyForms.size() + 2, 0, 1);
        sheet.addMergedRegion(address);
        row = sheet.getRow(outList.size() + 2);
        cell = row.getCell(0);
        cell.setCellValue("总计金额");
        cell = row.getCell(2);
        if (supplyForms.get(supplyForms.size() - 1).getTotalPrice() == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(supplyForms.get(supplyForms.size() - 1).getTotalPrice().toString());
        }


        //4、下载excel
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "网点供货统计表.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 网点入库明细表
     *
     * @param enterpriseId
     * @param start     创建入库单开始日期
     * @param end       创建入库单结束日期
     * @param goodsInfoType
     * @param billStatuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyDetails")
    public ModelAndView exportSupplyDetails(@RequestParam("enterpriseId") Long enterpriseId,
                                            @RequestParam(value="start",required = false) String start,
                                            @RequestParam(value="end",required = false) String end,
                                            @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                            @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss,
                                            @RequestParam(value = "thirdId",required = false) Long thirdId,
                                            @RequestParam(value = "thirdEnterpriseId",required = false) Long thirdEnterpriseId) {
        Date startTime = null;
        Date endTime = null;
        start=Constants.nullOrNotBlank(start);
        end=Constants.nullOrNotBlank(end);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }


        //1、导出的数据源并处理
        Page<SupplyForm> supplyDetaqils = formInfoService.getSupplyDetails(enterpriseId, startTime, endTime,goodsInfoType, billStatuss, null,thirdId,thirdEnterpriseId);
        List<SupplyForm> supplyForms = supplyDetaqils.getContent();
        List<List<String>> outList = new ArrayList<>();
        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        for (SupplyForm item : supplyForms) {
            if (addressMap.get(item.getEnterpriseId()) == null) {
                addressMap.put(item.getEnterpriseId(), s);
            }
            s++;
            addressMap.put(item.getEnterpriseId(), s);
            List<String> sonList = new ArrayList<>();
            sonList.add(Constants.nullString(item.getEnterpriseName()));
            sonList.add(Constants.nullString(item.getThirdName()));
            sonList.add(String.valueOf(item.getCode()));
            if (item.getCreateTime() == null) {
                sonList.add("");
            } else {
                sonList.add(DateUtil.formatToString(item.getCreateTime(), "yyyy-MM-dd"));
            }
            sonList.add(Constants.nullString(item.getGoodsInfoName()));
            sonList.add(Constants.nullString(item.getGoodsInfoItemNo()));
            if (StringUtils.isBlank(Constants.figureToString(item.getGoodsInfoType()))) {
                sonList.add("");
            } else {
                sonList.add(item.getGoodsInfoType() == 0 ? "集采商品" : "自购商品");
            }
            sonList.add(Constants.defaultToString(item.getSettlePrice()));
            sonList.add(Constants.amountToString(item.getDeliveryAmount()));
            sonList.add(Constants.amountToString(item.getReceiptAmount()));
            sonList.add(Constants.defaultToString(item.getPrice()));
            if(item.getBillStatus()==BillStatus.PARTIAL_RECEIPT){
                sonList.add("部分入库");
            }else {
                sonList.add(Constants.nullString(item.getBillStatus().getName()));
            }
            sonList.add(Constants.nullString(item.getLinkMan()));
            sonList.add(Constants.nullString(item.getLinkMobile()));
            sonList.add(Constants.nullString(item.getAddress()));
            outList.add(sonList);
        }

        //excel所有列
        List<String> headList = Arrays.asList("网点名称", "供应商名称", "单据编号", "发货日期", "货品名称", "货品编号", "商品类型", "采购单价", "采购数量", "入库数量","入库金额","状态", "收货人", "收货人联系电话", "收货地址");


        //总列数
        int headSize = headList.size();
        //数据总行数
        int bodySize = supplyForms.size();
        String sheetName = "入库明细表";
        HSSFWorkbook wb = new HSSFWorkbook();
        //2、导出excel
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);

        //3、处理excel
        HSSFSheet sheet = wb.getSheet(sheetName);
        //3.1 合并第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, headSize - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("入库明细表");
        //3.2 处理第二行
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("发货日期：" + Constants.nullString(start) + "-" + Constants.nullString(end));
        //3.3 合并处理第一列
        int i = 3;
        for (Long key : addressMap.keySet()) {
            address = new CellRangeAddress(i, addressMap.get(key) - 1, 0, 0);
            sheet.addMergedRegion(address);
            i = addressMap.get(key);
        }

        //4、下载excel
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "入库明细表.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    /**
     * 网点供货明细表
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param goodsInfoType
     * @param billStatuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyDetailsForm")
    public ModelAndView exportSupplyDetailsForm(@RequestParam("enterpriseId") Long enterpriseId,
                                            @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                            @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                            @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                            @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                            @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                            @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                            @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                            @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }


        //1、导出的数据源并处理
        Page<SupplyForm> supplyDetaqils = formInfoService.getSupplyDetailsForm(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, goodsInfoType, billStatuss, null);
        List<SupplyForm> supplyForms = supplyDetaqils.getContent();
        List<List<String>> outList = new ArrayList<>();
        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        for (SupplyForm item : supplyForms) {
            if (addressMap.get(item.getEnterpriseId()) == null) {
                addressMap.put(item.getEnterpriseId(), s);
            }
            s++;
            addressMap.put(item.getEnterpriseId(), s);
            List<String> sonList = new ArrayList<>();
            sonList.add(Constants.nullString(item.getEnterpriseName()));
            sonList.add(Constants.nullString(item.getThirdName()));
            sonList.add(String.valueOf(item.getCode()));
            if (item.getApplyTime() == null) {
                sonList.add("");
            } else {
                sonList.add(DateUtil.formatToString(item.getApplyTime(), "yyyy-MM-dd"));
            }
            if (item.getStockTime() == null) {
                sonList.add("");
            } else {
                sonList.add(DateUtil.formatToString(item.getStockTime(), "yyyy-MM-dd"));
            }
            if (item.getRecipientTime() == null) {
                sonList.add("");
            } else {
                sonList.add(DateUtil.formatToString(item.getRecipientTime(), "yyyy-MM-dd"));
            }
            sonList.add(Constants.nullString(item.getGoodsInfoName()));
            sonList.add(Constants.nullString(item.getGoodsInfoItemNo()));
            if (StringUtils.isBlank(Constants.figureToString(item.getGoodsInfoType()))) {
                sonList.add("");
            } else {
                sonList.add(item.getGoodsInfoType() == 0 ? "集采商品" : "自购商品");
            }
            sonList.add(Constants.amountToString(item.getDeliveryAmount()));
            sonList.add(Constants.defaultToString(item.getSettlePrice()));
            sonList.add(Constants.defaultToString(item.getPrice()));
            sonList.add(Constants.nullString(item.getLinkMan()));
            sonList.add(Constants.nullString(item.getLinkMobile()));
            sonList.add(Constants.nullString(item.getAddress()));
            sonList.add(Constants.nullString(item.getBillStatus().getName()));
            outList.add(sonList);
        }

        //excel所有列
        List<String> headList = Arrays.asList("网点名称", "供应商名称", "单据编号", "定货日期", "备货日期", "入库日期", "货品名称", "货品编号", "商品类型", "发货数量", "货品结算单价（元）", "金额（元）", "收货人", "收货人联系电话", "收货地址", "状态");


        //总列数
        int headSize = headList.size();
        //数据总行数
        int bodySize = supplyForms.size();
        String sheetName = "网点供货明细表";
        HSSFWorkbook wb = new HSSFWorkbook();
        //2、导出excel
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);

        //3、处理excel
        HSSFSheet sheet = wb.getSheet(sheetName);
        //3.1 合并第一行
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, headSize - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("网点供货明细表");
        //3.2 处理第二行
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("订货日期：" + Constants.nullString(orderStartTime) + "-" + Constants.nullString(orderEndTime) +
                "备货日期：" + Constants.nullString(readyStartTime) + "-" + Constants.nullString(readyEndTime) +
                "入库日期：" + Constants.nullString(warehouseStartTime) + "-" + Constants.nullString(warehouseEndTime));
        //3.3 合并处理第一列
        int i = 3;
        for (Long key : addressMap.keySet()) {
            address = new CellRangeAddress(i, addressMap.get(key) - 1, 0, 0);
            sheet.addMergedRegion(address);
            i = addressMap.get(key);
        }

        //4、下载excel
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "网点供货明细表.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 查询供应商供货信息,以供应商为查询条件，按照网点分组
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param billStatuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyByThirdAndEnterprise")
    @ResponseBody
    public ModelAndView exportSupplyByThirdAndEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                         @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                         @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                         @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                         @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                         @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                         @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                         @RequestParam(value = "thirdId", required = false) Long thirdId,
                                                         @RequestParam(value = "thirdEnterpriseId", required = false) Long thirdEnterpriseId,
                                                         @RequestParam(value = "thirdName", required = false) String thirdName,
                                                         @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }

        Page<SupplyForm> supplyFormPage = formInfoService.getSupplyByThirdAndEnterprise(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, thirdId, thirdEnterpriseId, billStatuss, null);
        List<SupplyForm> supplyForms = supplyFormPage.getContent();
        List<String> headList = Arrays.asList("网点名称", "货品名称", "货品编号", "商品类型", "发货数量", "货品结算单价（元）", "金额（元）", "收货人", "收货人联系电话", "收货地址");
        List<List<String>> outList = new ArrayList<>();

        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        Long eId = -1l;
        for (SupplyForm supplyForm : supplyForms) {
            List<String> sonList = new ArrayList<>();
            Long id = supplyForm.getEnterpriseId();
            s++;
            if (id != eId) {
                eId = id;
            }
            addressMap.put(id, s);
            sonList.add(Constants.nullString(supplyForm.getEnterpriseName()));
            sonList.add(Constants.nullString(supplyForm.getGoodsInfoName()));
            sonList.add(Constants.nullString(supplyForm.getGoodsInfoItemNo()));
            String goodsType;
            if (supplyForm.getGoodsInfoType() == null) {
                goodsType = "";
            } else {
                goodsType = supplyForm.getGoodsInfoType() == 0 ? "集采商品" : "自购商品";
            }
            sonList.add(goodsType);
            sonList.add(Constants.figureToString(supplyForm.getDeliveryAmount()));
            String settlePrice = supplyForm.getSettlePrice() == null ? "" : supplyForm.getSettlePrice().toEngineeringString();
            sonList.add(settlePrice);
            sonList.add(supplyForm.getPrice() == null ? "" : supplyForm.getPrice().toString());
            sonList.add(supplyForm.getLinkMan() == null ? "" : supplyForm.getLinkMan());
            sonList.add(supplyForm.getLinkMobile() == null ? "" : supplyForm.getLinkMobile());
            sonList.add(supplyForm.getAddress() == null ? "" : supplyForm.getAddress());
            outList.add(sonList);
        }


        String sheetName = "供应商发货汇总表-按网点";
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);


        HSSFSheet sheet = wb.getSheet(sheetName);
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, 9);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue(sheetName);

        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("供应商名称：" + thirdName);

        address = new CellRangeAddress(1, 1, 2, 9);
        sheet.addMergedRegion(address);
        cell = row.getCell(3);
        cell.setCellValue("订货日期：" + Constants.nullString(orderStartTime) + "-" + Constants.nullString(orderEndTime) +
                "备货日期：" + Constants.nullString(readyStartTime) + "-" + Constants.nullString(readyEndTime) +
                "入库日期：" + Constants.nullString(warehouseStartTime) + "-" + Constants.nullString(warehouseEndTime));

        int startRow = 3;
        for (Long key : addressMap.keySet()) {
            int endRow = addressMap.get(key) - 1;
            address = new CellRangeAddress(startRow, endRow, 0, 0);
            sheet.addMergedRegion(address);
            startRow = endRow + 1;
        }

        address = new CellRangeAddress(supplyForms.size() + 2, supplyForms.size() + 2, 0, 5);
        sheet.addMergedRegion(address);
        row = sheet.getRow(outList.size() + 2);
        cell = row.getCell(0);
        cell.setCellValue("总计金额");
        cell = row.getCell(6);
        if (supplyForms.get(supplyForms.size() - 1).getTotalPrice() == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(supplyForms.get(supplyForms.size() - 1).getTotalPrice().toString());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "供应商发货汇总表-按网点.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);

    }


    /**
     * 查询供应商供货信息，以供应商为查询条件，按照货品分组
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param billStatuss
     * @return
     */
    @RequestMapping(value = "/exportSupplyByThirdAndGoods")
    @ResponseBody
    public ModelAndView exportSupplyByThirdAndGoods(@RequestParam("enterpriseId") Long enterpriseId,
                                                    @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                    @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                    @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                    @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                    @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                    @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                    @RequestParam(value = "thirdId", required = false) Long thirdId,
                                                    @RequestParam(value = "thirdEnterpriseId", required = false) Long thirdEnterpriseId,
                                                    @RequestParam(value = "thirdName", required = false) String thirdName,
                                                    @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }

        Page<SupplyForm> supplyFormPage = formInfoService.getSupplyByThirdAndGoods(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, thirdId, thirdEnterpriseId, billStatuss, null);
        List<SupplyForm> forms = supplyFormPage.getContent();

        List<String> headList = Arrays.asList("货品名称", "货品编号", "商品类型", "发货数量", "货品结算单价（元）", "金额（元）");
        List<List<String>> outList = new ArrayList<>();

        for (SupplyForm supplyForm : forms) {
            List<String> sonList = new ArrayList<>();
            sonList.add(Constants.nullString(supplyForm.getGoodsInfoName()));
            sonList.add(Constants.nullString(supplyForm.getGoodsInfoItemNo()));
            String goodsType;
            if (supplyForm.getGoodsInfoType() == null) {
                goodsType = "";
            } else {
                goodsType = supplyForm.getGoodsInfoType() == 0 ? "集采商品" : "自购商品";
            }
            sonList.add(goodsType);
            sonList.add(Constants.figureToString(supplyForm.getDeliveryAmount()));
            String settlePrice = supplyForm.getSettlePrice() == null ? "" : supplyForm.getSettlePrice().toEngineeringString();
            sonList.add(settlePrice);
            sonList.add(supplyForm.getPrice() == null ? "" : supplyForm.getPrice().toString());
            outList.add(sonList);
        }


        String sheetName = "供应商发货汇总表-按货品";
        int headSize = headList.size();
        int bodySize = forms.size();
        HSSFWorkbook wb = new HSSFWorkbook();
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);

        HSSFSheet sheet = wb.getSheet(sheetName);
        CellRangeAddress address = new CellRangeAddress(0, 0, 0, headSize - 1);
        sheet.addMergedRegion(address);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue(sheetName);

        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("供应商名称：" + thirdName);

        address = new CellRangeAddress(1, 1, headSize - 1, headSize - 1);
        sheet.addMergedRegion(address);
        cell = row.getCell(headSize - 1);
        cell.setCellValue("订货日期：" + Constants.nullString(orderStartTime) + "-" + Constants.nullString(orderEndTime) +
                "备货日期：" + Constants.nullString(readyStartTime) + "-" + Constants.nullString(readyEndTime) +
                "入库日期：" + Constants.nullString(warehouseStartTime) + "-" + Constants.nullString(warehouseEndTime));
        address = new CellRangeAddress(bodySize + 2, bodySize + 2, 0, headSize - 2);
        sheet.addMergedRegion(address);
        row = sheet.getRow(bodySize + 2);
        cell = row.getCell(0);
        cell.setCellValue("合计金额");

        cell = row.getCell(headSize - 1);
        if (forms.get(forms.size() - 1).getTotalPrice() == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(forms.get(forms.size() - 1).getTotalPrice().toString());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "供应商发货汇总表-按货品.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 查询网点供货明细
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param goodsInfoType
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyStatistical")
    @ResponseBody
    public String getSupplyStatistical(@RequestParam("enterpriseId") Long enterpriseId,
                                       @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                       @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                       @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                       @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                       @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                       @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                       @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType,
                                       @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }


        Page<SupplyForm> supplyDetaqils = formInfoService.getSupplyStatistical(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, goodsInfoType, billStatuss, new Pageable(page, size));
        return new JsonResponseBean(supplyDetaqils).toJson();
    }


    /**
     * 纯手动煞笔excel，后期将用工具类彻底优化，优化思路：标准化excel--插入数据--对单元格格式进行处理
     *
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     * @param goodsInfoType
     * @return
     */
    @RequestMapping("/exportInventoryCheck")
    public ModelAndView exportInventoryCheck(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                             @RequestParam(value = "goodsInfoItemNo", required = false) String goodsNumber,
                                             @RequestParam(value = "brandId", required = false) Long brandId,
                                             @RequestParam(value = "thirdId", required = false) Long thirdId,
                                             @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsNumber = Constants.nullOrNotBlank(goodsNumber);
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(enterpriseId);
        Page<InventoryGoodsResult> inventoryGoodsResultPage = inventoryManageService.getInventoryGoodsResult(goodsInfoName, goodsNumber, brandId, thirdId, goodsInfoType, null);
        List<InventoryGoodsResult> inventoryGoodsResultList = inventoryGoodsResultPage.getContent();
        //创建工作薄
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle bodyStyle=ExcelUtil.bodyStyle(wb);

        List<String> headList = Arrays.asList("序号", "存货编号", "存货名称", "货品规格", "账面数量","可用库存", "实盘数量", "盘盈+（亏-）数量", "备注");
        String sheetName="库存盘点表";
        List<List<String>> outList = new ArrayList<>();
        int serialNum=1;
        int inventory=0;
        int available=0;
        int goodsSize = inventoryGoodsResultList.size();
        for(InventoryGoodsResult goodsResult:inventoryGoodsResultList){
            List<String> sonList=new ArrayList<>();
            sonList.add(Constants.figureToString(serialNum));
            serialNum++;
            sonList.add(goodsResult.getGoodsNumber());
            sonList.add(goodsResult.getGoodsInfoName());
            sonList.add(goodsResult.getSpecString());
            sonList.add(Constants.figureToString(goodsResult.getInventory()));
            inventory+=goodsResult.getInventory();
            sonList.add(Constants.figureToString(goodsResult.getAvailable()));
            available+=goodsResult.getAvailable();
            outList.add(sonList);
        }
        wb=ExcelUtil.excelExport(wb,headList,outList,0,2,sheetName);
        HSSFSheet sheet = wb.getSheet(sheetName);

        //合并单元格
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, 8);
        sheet.addMergedRegion(cellAddress);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell=row.getCell(0);
        cell.setCellValue("库存盘点表");

        row = sheet.getRow(1);
        cellAddress = new CellRangeAddress(1, 1, 0, 3);
        sheet.addMergedRegion(cellAddress);
        cell = row.createCell(0);
        cell.setCellValue("盘点单位：" + enterpriseInfo.getEnterpriseName());
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        cell.setCellStyle(style);

        cellAddress = new CellRangeAddress(1, 1, 4, 8);
        sheet.addMergedRegion(cellAddress);
        cell = row.getCell(4);
        Date now = new Date();
        String timeString = DateUtil.formatToString(now, "yyyy-MM-dd HH:mm:ss");
        cell.setCellValue("盘点时间：" + timeString);
        HSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        cell.setCellStyle(style2);


        //合并倒数第二行
        int exitRowNumber = goodsSize + 3;
        HSSFRow addRow = sheet.createRow(exitRowNumber);
        for (int i = 0; i <= 8; i++) {
            cell = addRow.createCell(i);
            cell.setCellStyle(bodyStyle);
        }
        cellAddress = new CellRangeAddress(exitRowNumber, exitRowNumber, 0, 2);
        sheet.addMergedRegion(cellAddress);
        addRow = sheet.getRow(exitRowNumber);
        cell = addRow.getCell(0);
        cell.setCellValue("合计");

        cell = addRow.getCell(4);
        cell.setCellValue(inventory);
        cell=addRow.getCell(5);
        cell.setCellValue(available);


        //合并最后一行
        exitRowNumber = exitRowNumber + 1;
        row = sheet.createRow(exitRowNumber);
        for (int i = 0; i <=8; i++) {
            cell = row.createCell(i);
            cell.setCellStyle(bodyStyle);
        }
        cellAddress = new CellRangeAddress(exitRowNumber, exitRowNumber, 0, 1);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(exitRowNumber);
        cell = row.getCell(0);
        cell.setCellValue("负责人：");

        cellAddress = new CellRangeAddress(exitRowNumber, exitRowNumber, 2, 3);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(exitRowNumber);
        cell = row.getCell(2);
        cell.setCellValue("监盘：");

        cellAddress = new CellRangeAddress(exitRowNumber, exitRowNumber, 4, 6);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(exitRowNumber);
        cell = row.getCell(4);
        cell.setCellValue("保管：");

        cellAddress = new CellRangeAddress(exitRowNumber, exitRowNumber, 7, 8);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(exitRowNumber);
        cell = row.getCell(7);
        cell.setCellValue("制表：");


        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName+".xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 库存变动详情，按网点查询导出
     *
     * @param enterpriseId
     * @param date
     * @return
     */
    @RequestMapping(value = "/exportInventoryHistoryByEnterprise")
    public ModelAndView exportInventoryHistoryByEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                           @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                           @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                                                           @RequestParam("date") String date) {
        Assert.notNull(date, "选择日期");
        Date queryDate = DateUtil.fromString(date, "yyyy-MM");
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        Page<InventoryHsitoryByEnterpriseForm> resultPage = formInfoService.getInventoryHistoryFormByEnterprise(enterpriseId, null, null, queryDate, null);
        List<InventoryHsitoryByEnterpriseForm> list = resultPage.getContent();


        List<String> headList = Arrays.asList("仓库", "货品名称", "货品规格", "货品编号", "上月结存",
                "本月新增", "商品入库", "调入", "退货", "本月消耗",
                "调出", "兑换", "损溢数量", "报损", "报溢", "本月结余");
        List<List<String>> outList = new ArrayList<>();

        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        for (InventoryHsitoryByEnterpriseForm inventoryHsitoryByEnterpriseForm : list) {
            List<InventoryHistoryForm> historyForms = inventoryHsitoryByEnterpriseForm.getFormList();
            InventoryHistoryForm totalHistoryForm = inventoryHsitoryByEnterpriseForm.getInventoryHistoryForm();
            historyForms.add(totalHistoryForm);
            s += historyForms.size();
            addressMap.put(totalHistoryForm.getEnterpriseId(), s);
            for (InventoryHistoryForm historyForm : historyForms) {
                List<String> sonList = new ArrayList<>();
                sonList.add(historyForm.getEnterpriseName());
                sonList.add(historyForm.getGoodsInfoName());
                sonList.add(historyForm.getGoodsSpecString());
                sonList.add(historyForm.getGoodsInfoItemNo());
                sonList.add(Constants.figureToString(historyForm.getLastMonthInventory()));
                sonList.add(Constants.figureToString(historyForm.getIncreaseAmount()));
                sonList.add(Constants.figureToString(historyForm.getInstockAmount()));
                sonList.add(Constants.figureToString(historyForm.getTransferIn()));
                sonList.add(Constants.figureToString(historyForm.getOrderBack()));
                sonList.add(Constants.figureToString(historyForm.getConsumeAmount()));
                sonList.add(Constants.figureToString(historyForm.getTransferOut()));
                sonList.add(Constants.figureToString(historyForm.getOrderConsume()));
                sonList.add(Constants.figureToString(historyForm.getGainsAmount()));
                sonList.add(Constants.figureToString(historyForm.getLessReport()));
                sonList.add(Constants.figureToString(historyForm.getMoreReport()));
                sonList.add(Constants.figureToString(historyForm.getInventoryAmount()));
                outList.add(sonList);
            }
        }


        HSSFWorkbook wb = new HSSFWorkbook();
        String sheetName = "库存详情表（按仓库查询）";
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);
        HSSFCellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle bodyStyle = wb.createCellStyle();
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);


        HSSFSheet sheet = wb.getSheet(sheetName);
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue("库存详情表（按仓库查询）");
        cell.setCellStyle(headStyle);


        cellAddress = new CellRangeAddress(1, 1, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("库存时间:" + date);
        cell.setCellStyle(bodyStyle);

        for (Long key : addressMap.keySet()) {
            int rowStart = addressMap.get(key) - 1;
            int rowEnd = addressMap.get(key) - 1;
            cellAddress = new CellRangeAddress(rowStart, rowEnd, 1, 3);
            sheet.addMergedRegion(cellAddress);
            row = sheet.getRow(rowStart);
            cell = row.getCell(1);
            cell.setCellValue("合计");
        }

        int startRow = 3;
        for (Long key : addressMap.keySet()) {
            int endRow = addressMap.get(key) - 1;
            cellAddress = new CellRangeAddress(startRow, endRow, 0, 0);
            sheet.addMergedRegion(cellAddress);
            startRow = endRow + 1;

        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "库存详情表（按仓库查询）.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 库存变动详情，按网点查询中导出搜索货品以及其所在网点
     *
     * @param enterpriseId
     * @param date
     * @return
     */
    @RequestMapping(value = "/exportHistoryGoodsByEnterprise")
    public ModelAndView exportHistoryGoodsByEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                       @RequestParam(value = "goodsInfoName") String goodsInfoName,
                                                       @RequestParam(value = "goodsInfoItemNo") String goodsInfoItemNo,
                                                       @RequestParam("date") String date) {
        Assert.notNull(date, "选择日期");
        Date queryDate = DateUtil.fromString(date, "yyyy-MM");
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);

        List<InventoryHsitoryByEnterpriseForm> list = formInfoService.getHistoryGoodsByEnterprise(enterpriseId, goodsInfoName, goodsInfoItemNo, queryDate, null);
        List<String> headList = Arrays.asList("仓库", "货品名称", "货品规格", "货品编号", "上月结存",
                "本月新增", "商品入库", "调入", "退货", "本月消耗",
                "调出", "兑换", "损溢数量", "报损", "报溢", "本月结余");
        List<List<String>> outList = new ArrayList<>();

        //记录相同网点数据结束时所在行
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        //数据起始行
        int s = 3;
        for (InventoryHsitoryByEnterpriseForm inventoryHsitoryByEnterpriseForm : list) {
            List<InventoryHistoryForm> historyForms = inventoryHsitoryByEnterpriseForm.getFormList();
            s += historyForms.size();
            addressMap.put(inventoryHsitoryByEnterpriseForm.getEnterpriseId(), s);
            for (InventoryHistoryForm historyForm : historyForms) {
                List<String> sonList = new ArrayList<>();
                sonList.add(historyForm.getEnterpriseName());
                sonList.add(historyForm.getGoodsInfoName());
                sonList.add(historyForm.getGoodsSpecString());
                sonList.add(historyForm.getGoodsInfoItemNo());
                sonList.add(Constants.figureToString(historyForm.getLastMonthInventory()));
                sonList.add(Constants.figureToString(historyForm.getIncreaseAmount()));
                sonList.add(Constants.figureToString(historyForm.getInstockAmount()));
                sonList.add(Constants.figureToString(historyForm.getTransferIn()));
                sonList.add(Constants.figureToString(historyForm.getOrderBack()));
                sonList.add(Constants.figureToString(historyForm.getConsumeAmount()));
                sonList.add(Constants.figureToString(historyForm.getTransferOut()));
                sonList.add(Constants.figureToString(historyForm.getOrderConsume()));
                sonList.add(Constants.figureToString(historyForm.getGainsAmount()));
                sonList.add(Constants.figureToString(historyForm.getLessReport()));
                sonList.add(Constants.figureToString(historyForm.getMoreReport()));
                sonList.add(Constants.figureToString(historyForm.getInventoryAmount()));
                outList.add(sonList);
            }
        }


        HSSFWorkbook wb = new HSSFWorkbook();
        String sheetName = "库存详情表（按仓库查询）";
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);
        HSSFCellStyle headStyle = wb.createCellStyle();
        headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle bodyStyle = wb.createCellStyle();
        bodyStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        headStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFSheet sheet = wb.getSheet(sheetName);
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellStyle(headStyle);
        cell.setCellValue("库存详情表（按仓库查询）");
        cell.setCellStyle(headStyle);


        cellAddress = new CellRangeAddress(1, 1, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("库存时间:" + date);
        cell.setCellStyle(bodyStyle);
        int startRow = 3;
        for (Long key : addressMap.keySet()) {
            int endRow = addressMap.get(key) - 1;
            cellAddress = new CellRangeAddress(startRow, endRow, 0, 0);
            sheet.addMergedRegion(cellAddress);
            startRow = endRow + 1;

        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "库存详情表（按仓库查询）.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 库存变动详情，按商品查询导出
     *
     * @param enterpriseId
     * @param date
     * @return
     */
    @RequestMapping(value = "/exportInventoryHistoryByGoodsInfo")
    public ModelAndView exportInventoryHistoryByGoodsInfo(@RequestParam("enterpriseId") Long enterpriseId,
                                                          @RequestParam("goodsInfoName") String goodsInfoName,
                                                          @RequestParam("goodsInfoItemNo") String goodsInfoItemNo,
                                                          @RequestParam("date") String date) {
        Assert.notNull(date, "选择日期");
        Date queryDate = DateUtil.fromString(date, "yyyy-MM");
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);

        Page<InventoryHistoryForm> resultPage = formInfoService.getInventoryHistoryFormByGoodsInfo(enterpriseId, goodsInfoName, goodsInfoItemNo, queryDate, null);
        HSSFWorkbook wb = new HSSFWorkbook();
        List<InventoryHistoryForm> historyList = resultPage.getContent();
        InventoryHistoryForm inventoryHistoryForm = inventoryHistoryFormService.sumInventoryHistoryByEnterprise(historyList);
        historyList.add(inventoryHistoryForm);

        List<String> headList = Arrays.asList("货品名称", "货品规格", "货品编号", "上月结存",
                "本月新增", "商品入库", "调入", "退货", "本月消耗",
                "调出", "兑换", "损溢数量", "报损", "报溢", "本月结余");
        List<List<String>> outList = new ArrayList<>();
        for (InventoryHistoryForm historyForm : historyList) {
            List<String> sonList = new ArrayList<>();
            sonList.add(historyForm.getGoodsInfoName());
            sonList.add(historyForm.getGoodsSpecString());
            sonList.add(historyForm.getGoodsInfoItemNo());
            sonList.add(Constants.figureToString(historyForm.getLastMonthInventory()));
            sonList.add(Constants.figureToString(historyForm.getIncreaseAmount()));
            sonList.add(Constants.figureToString(historyForm.getInstockAmount()));
            sonList.add(Constants.figureToString(historyForm.getTransferIn()));
            sonList.add(Constants.figureToString(historyForm.getOrderBack()));
            sonList.add(Constants.figureToString(historyForm.getConsumeAmount()));
            sonList.add(Constants.figureToString(historyForm.getTransferOut()));
            sonList.add(Constants.figureToString(historyForm.getOrderConsume()));
            sonList.add(Constants.figureToString(historyForm.getGainsAmount()));
            sonList.add(Constants.figureToString(historyForm.getLessReport()));
            sonList.add(Constants.figureToString(historyForm.getMoreReport()));
            sonList.add(Constants.figureToString(historyForm.getInventoryAmount()));
            outList.add(sonList);
        }
        String sheetName = "库存详情表（按货品查询）";
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 2, sheetName);
        HSSFSheet sheet = wb.getSheet(sheetName);

        //大表头
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("库存详情表（按货品查询）");
        //第二行记录时间
        cellAddress = new CellRangeAddress(1, 1, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(1);
        cell = row.getCell(0);
        cell.setCellValue("库存时间:" + date);

        //合并最后一行
        cellAddress = new CellRangeAddress(outList.size() + 2, outList.size() + 2, 0, 2);
        sheet.addMergedRegion(cellAddress);
        row = sheet.getRow(outList.size() + 2);
        cell = row.getCell(0);
        cell.setCellValue("合计");

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "库存详情表（按货品查询）.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    /**
     * 库存变动记录excel
     *
     * @param enterpriseId
     * @param start
     * @param end
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @return
     */
    @RequestMapping(value = "/exportInventoryChangeByEnterprise")
    public ModelAndView exportInventoryChangeByEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                          @RequestParam("start") String start,
                                                          @RequestParam("end") String end,
                                                          @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                          @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo) {
        Date startTime = null;
        Date endTime = null;
        start = Constants.nullOrNotBlank(start);
        end = Constants.nullOrNotBlank(end);
        goodsInfoItemNo = StringUtil.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<InventoryChangeHistoryForm> inventoryChangeFormPage = formInfoService.getInventoryChangeForm(enterpriseId, startTime, endTime, goodsInfoName, goodsInfoItemNo, null);

        ///生成标准表格并插入数据
        List<InventoryChangeHistoryForm> inventoryChangeHistoryForms = inventoryChangeFormPage.getContent();
        HSSFWorkbook wb = new HSSFWorkbook();

        //第二行表头
        List<String> headList = Arrays.asList("网点名称", "时间", "货品名称", "货品规格",
                "货品编号", "变动原因", "单据编号", "库存变动数量", "库存余量");
        List<List<String>> outList = new ArrayList<>();

        Long eId = -1l;
        int startNo = 1;
        //标记相同网点结束时的行数
        Map<Long, Integer> addressMap = new LinkedHashMap<>();
        for (InventoryChangeHistoryForm changeHistory : inventoryChangeHistoryForms) {
            if (changeHistory.getEnterpriseId().equals(eId)) {
                startNo++;
                addressMap.put(eId, startNo);
            } else {
                startNo++;
                eId = changeHistory.getEnterpriseId();
                addressMap.put(eId, startNo);
            }
            List<String> sonList = new ArrayList<>();
            String accountName = changeHistory.getAccountName();
            accountName = accountName == null ? "" : accountName;
            String enterpriseName = changeHistory.getEnterpriseName();
            enterpriseName = enterpriseName == null ? "" : enterpriseName;
            sonList.add(enterpriseName + "(" + accountName + ")");
            String date = DateUtil.formatToString(changeHistory.getChangeTime(), "yyyy-MM-dd HH:mm:ss");
            sonList.add(date);
            sonList.add(changeHistory.getGoodsInfoName());
            sonList.add(changeHistory.getGoodsSpecString());
            sonList.add(changeHistory.getGoodsInfoItemNo());
            InventoryChangeType type = changeHistory.getInventoryChangeType();
            sonList.add(type.name);
            Long code = changeHistory.getCode();
            String codeString = code == null ? "" : code.toString();
            sonList.add(codeString);
            Integer changeAmount = changeHistory.getInventoryChangeAmount();
            changeAmount = changeAmount == null ? 0 : changeAmount;
            switch (type) {
                case GOODSINFO_TRANSFER_OUT:
                case GOODSINFO_LESS_REPORT:
                case ORDER_CONSUME:
                    changeAmount = -1 * changeAmount;

            }
            sonList.add(changeAmount.toString());

            Integer afterChange = changeHistory.getInventoryAfterChange();
            sonList.add(Constants.figureToString(afterChange));
            outList.add(sonList);
        }
        wb = ExcelUtil.excelExport(wb, headList, outList, 0, 1, "库存变动记录");


        //处理表格格式--合并单元格
        HSSFSheet sheet = wb.getSheet("库存变动记录");
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, headList.size() - 1);
        sheet.addMergedRegion(cellAddress);
        HSSFRow row = sheet.getRow(0);
        HSSFCell cell = row.getCell(0);
        cell.setCellValue("库存变动记录");
        //合并起始行
        int s = 2;
        for (Long key : addressMap.keySet()) {
            int sameAmount = addressMap.get(key);
            cellAddress = new CellRangeAddress(s, sameAmount, 0, 0);
            sheet.addMergedRegion(cellAddress);
            s = sameAmount + 1;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = "库存变动记录表.xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    @RequestMapping("/exportBillByBillId")
    public ModelAndView exportBillByBillId(@RequestParam(value = "billId") long billId) {
        //取出调拨单、调拨单内商品列表
        InventoryBill bill = inventoryManageService.getBillById(billId);
        List<InventoryBillItem> goodsList = bill.getItems();
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        sheet.setColumnWidth(0, 24 * 256);
        sheet.setColumnWidth(1, 24 * 256);
        sheet.setColumnWidth(2, 24 * 256);
        sheet.setColumnWidth(3, 24 * 256);
        sheet.setColumnWidth(4, 24 * 256);
        sheet.setColumnWidth(5, 24 * 256);
        sheet.setColumnWidth(6, 24 * 256);
        sheet.setColumnWidth(7, 24 * 256);
        HSSFCell cell;

        HSSFCellStyle headStyle = ExcelUtil.headStyle(wb);
        HSSFCellStyle bodyStyle = ExcelUtil.bodyStyle(wb);

        //总的调拨数量
        Integer totalCheckAmount = 0;
        //总金额
        BigDecimal totalMoney = BigDecimal.ZERO;
        //第六行放空，第七行表头
        HSSFRow seventhRow = sheet.createRow(6);
        List<String> headList = Arrays.asList("序号", "供货商名称", "货品名称", "货品规格", "货品编号", "结算单价（元）", "调拨数量", "金额（元）");
        for (int i = 0; i < headList.size(); i++) {
            cell = seventhRow.createCell(i);
            cell.setCellValue(headList.get(i));
            cell.setCellStyle(headStyle);
        }
        //商品列表
        for (int i = 0; i < goodsList.size(); i++) {
            InventoryBillItem inventoryBillItem = goodsList.get(i);
            InventoryBillItem.ItemGoodsInfo goodsInfo = inventoryBillItem.getInfo();
            BigDecimal settlePrice = goodsInfo.getSettlePrice();
            settlePrice = settlePrice == null ? BigDecimal.ZERO : settlePrice;
            Integer amount = inventoryBillItem.getAmount();
            amount = amount == null ? 0 : amount;
            totalCheckAmount = totalCheckAmount + amount;
            BigDecimal money = settlePrice.multiply(new BigDecimal(amount));
            totalMoney = totalMoney.add(money);
            HSSFRow bodyRow = sheet.createRow(i + 7);
            HSSFCell bodyCell = bodyRow.createCell(0);
            bodyCell.setCellStyle(bodyStyle);
            bodyCell.setCellValue(i + 1);
            bodyCell = bodyRow.createCell(1);
            bodyCell.setCellValue(goodsInfo.getThirdName());
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(2);
            bodyCell.setCellValue(goodsInfo.getGoodsInfoName());
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(3);
            String spec = goodsInfo.getSpecString();
            spec = spec == null ? "" : spec;
            bodyCell.setCellValue(spec);
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(4);
            bodyCell.setCellValue(goodsInfo.getGoodsInfoItemNo());
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(5);
            bodyCell.setCellValue(settlePrice.doubleValue());
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(6);
            bodyCell.setCellValue(amount);
            bodyCell.setCellStyle(bodyStyle);
            bodyCell = bodyRow.createCell(7);
            bodyCell.setCellValue(money.doubleValue());
            bodyCell.setCellStyle(bodyStyle);
        }

        //第一行表头
        HSSFRow headRow = sheet.createRow(0);
        //合并单元格
        CellRangeAddress cellAddress = new CellRangeAddress(0, 0, 0, 7);
        sheet.addMergedRegion(cellAddress);
        headRow = sheet.getRow(0);
        cell = headRow.createCell(0);

        cell.setCellValue("调拨单");
        cell.setCellStyle(headStyle);

        //第二行表头信息
        HSSFRow secondRow = sheet.createRow(1);
        CellRangeAddress billAddress = new CellRangeAddress(1, 1, 0, 1);
        CellRangeAddress applyAddress = new CellRangeAddress(1, 1, 2, 3);
        CellRangeAddress createAddress = new CellRangeAddress(1, 1, 4, 5);
        CellRangeAddress stausAddress = new CellRangeAddress(1, 1, 6, 7);
        sheet.addMergedRegion(billAddress);
        sheet.addMergedRegion(applyAddress);
        sheet.addMergedRegion(createAddress);
        sheet.addMergedRegion(stausAddress);
        secondRow = sheet.getRow(1);
        cell = secondRow.createCell(0);
        cell.setCellValue("单据编号" + ":" + bill.getCode());
        cell = secondRow.createCell(2);
        String applyName = bill.getTansferInfo().getCreatorName();
        applyName = applyName == null ? "" : applyName;
        cell.setCellValue("申请账号" + ":" + applyName);
        cell = secondRow.createCell(4);
        Date createTime = bill.getCreateTime();
        String time = DateUtil.formatToString(createTime, "yyyy-MM-dd");
        cell.setCellValue("创建日期" + ":" + time);
        cell = secondRow.createCell(6);
        BillStatus billStatus = bill.getBillStatus();
        cell.setCellValue("当前状态" + ":" + billStatus.getName());

        //第三行表头信息
        HSSFRow thirdRow = sheet.createRow(2);
        CellRangeAddress outNameAddress = new CellRangeAddress(2, 2, 0, 1);
        CellRangeAddress inNameAddress = new CellRangeAddress(2, 2, 2, 3);
        CellRangeAddress inAddressAddress = new CellRangeAddress(2, 2, 4, 5);
        CellRangeAddress inManAddress = new CellRangeAddress(2, 2, 6, 7);
        sheet.addMergedRegion(outNameAddress);
        sheet.addMergedRegion(inNameAddress);
        sheet.addMergedRegion(inAddressAddress);
        sheet.addMergedRegion(inManAddress);
        thirdRow = sheet.getRow(2);
        cell = thirdRow.createCell(0);
        cell.setCellValue("调出仓库" + ":" + bill.getTansferInfo().getOutName());
        cell = thirdRow.createCell(2);
        cell.setCellValue("收货网点" + ":" + bill.getTansferInfo().getInName());
        cell = thirdRow.createCell(4);
        String address = bill.getTansferInfo().getConsignAddress();
        address = address == null ? "" : address;
        cell.setCellValue("收货地址" + ":" + address);
        cell = thirdRow.createCell(6);
        String linkMan = bill.getTansferInfo().getConsignMan();
        linkMan = linkMan == null ? "" : linkMan;
        cell.setCellValue("收货人" + ":" + linkMan);

        //第四行表头信息
        HSSFRow fourthRow = sheet.createRow(3);
        CellRangeAddress linkManAddress = new CellRangeAddress(3, 3, 0, 1);
        CellRangeAddress totalAmountAddress = new CellRangeAddress(3, 3, 2, 3);
        CellRangeAddress totalPriceAddress = new CellRangeAddress(3, 3, 4, 5);
        sheet.addMergedRegion(linkManAddress);
        sheet.addMergedRegion(totalAmountAddress);
        sheet.addMergedRegion(totalPriceAddress);
        fourthRow = sheet.getRow(3);
        cell = fourthRow.createCell(0);
        String mobile = bill.getTansferInfo().getConsignMobile();
        mobile = mobile == null ? "" : mobile;
        cell.setCellValue("收货人联系电话" + ":" + mobile);
        cell = fourthRow.createCell(2);
        cell.setCellValue("合计数量" + ":" + totalCheckAmount);
        cell = fourthRow.createCell(4);
        cell.setCellValue("合计金额（元）" + ":" + totalMoney);

        //第五行表头信息
        HSSFRow fifthRow = sheet.createRow(4);
        CellRangeAddress remarkAddress = new CellRangeAddress(4, 4, 0, 7);
        sheet.addMergedRegion(remarkAddress);
        fifthRow = sheet.getRow(4);
        cell = fifthRow.createCell(0);
        String reason = bill.getReason();
        reason = reason == null ? "" : reason;
        cell.setCellValue("备注:" + reason);

        //第六行置空
        HSSFRow sixthRow = sheet.createRow(5);
        CellRangeAddress sixAddress = new CellRangeAddress(5, 5, 0, 7);
        sheet.addMergedRegion(sixAddress);


        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        Long code = bill.getCode();
        String excelName = "导出调拨单" + code.toString() + "." + "xls";
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);

    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/ucoinGrandFormDown", method = RequestMethod.GET)
    public ModelAndView exportUcoinGrandForm(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                             @RequestParam(value = "idCard", required = false) String idCard,
                                             @RequestParam(value = "start", required = false) String start,
                                             @RequestParam(value = "end", required = false) String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard = StringUtil.nullOrNotBlank(idCard);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc = formInfoService.getUcoingrandReport(enterpriseId, startTime, endTime, idCard, null);
        List<UcoinGrandForm> ucoinGrandForms = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("grandAmount", "邮豆发放笔数");
        map.put("marketPrice", "营销邮豆总金额");
        map.put("salePrice", "促销邮豆总金额");
        map.put("price", "发放总金额");
        wb = ExcelUtil.excelExport(map, ucoinGrandForms);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员邮豆发放报表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/ucoinGrandDetailDown", method = RequestMethod.GET)
    public ModelAndView exportUcoinGrandDetail(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                               @RequestParam(value = "idCard") String idCard,
                                               @RequestParam(value = "start", required = false) String start,
                                               @RequestParam(value = "end", required = false) String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard, "身份证号必填！");
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc = formInfoService.getDetailGrandInfo(enterpriseId, startTime, endTime, idCard, null);
        List<UcoinGrandForm> formFuncList = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("createTime", "发放时间");
        map.put("grandEnterprise", "发放网点");
        map.put("marketPrice", "营销邮豆总金额");
        map.put("salePrice", "促销邮豆总金额");
        map.put("price", "发放总金额");
        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员邮豆发放明细报表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    @Deprecated
    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/customerConsumeDown", method = RequestMethod.GET)
    public ModelAndView exportCustomerConsume(@RequestParam(value = "idCard", required = false) String idCard,
                                              @RequestParam(value = "start", required = false) String start,
                                              @RequestParam(value = "end", required = false) String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard = StringUtil.nullOrNotBlank(idCard);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFunc = formInfoService.getCustomerconsumeReport(idCard, startTime, endTime, null);
        List<CustomerConsume> formFuncList = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("orderAmount", "产生订单数");
        map.put("totalConsumePrice", "订单总金额");
        map.put("backAmount", "退单成功数");
        map.put("totalRefundPrice", "退单总金额");
        map.put("resePrice", "当前账户余额");
        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员邮豆消耗报表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    /**
     * 新的导出excel(会员邮豆消耗统计报表)
     *
     * @param idCard
     * @param start
     * @param end
     * @return
     */
    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/customerConsumeDownNew", method = RequestMethod.GET)
    public ModelAndView exportCustomerConsumeNew(@RequestParam(value = "idCard", required = false) String idCard,
                                                 @RequestParam(value = "enterpriseId") Long enterpriseId,
                                                 @RequestParam(value = "start", required = false) String start,
                                                 @RequestParam(value = "end", required = false) String end) {
        Assert.notNull(enterpriseId, "请选择要查询的网点！");
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard = StringUtil.nullOrNotBlank(idCard);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFunc = formInfoService.getCustomerConsumeReportNew(idCard, enterpriseId, startTime, endTime, null);
        List<CustomerConsume> formFuncList = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("valetAmount", "线下订单数");
        map.put("totalValetPrice", "线下消耗邮豆");
        map.put("onlineAmount", "线上订单数");
        map.put("totalOnlinePrice", "线上消耗邮豆");
        map.put("backValetAmount", "线下退单数");
        map.put("totalBackValetPrice", "线下退款邮豆");
        map.put("backOnlineAmount", "线上退单数");
        map.put("totalBackOnlinePrice", "线上退款邮豆");
        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员邮豆消耗报表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/consumeDetailDown", method = RequestMethod.GET)
    public ModelAndView exportConsumeDetail(@RequestParam(value = "idCard") String idCard,
                                            @RequestParam(value = "start", required = false) String start,
                                            @RequestParam(value = "end", required = false) String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard, "身份证号必填");
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
        }
        Page<CustomerConsume> formFunc = formInfoService.getDetailConsume(idCard, startTime, endTime, null);
        List<CustomerConsume> formFuncList = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("type", "单据类型");
        map.put("createTime", "时间");
        map.put("orderId", "单据编号");
        map.put("orderPrice", "订单金额");
        map.put("backPrice", "退单金额");
        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("邮豆消耗明细.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    /**
     * 新的会员导出订单记录报表（查看详情）
     */
    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/consumeDetailDownNew", method = RequestMethod.GET)
    public ModelAndView exportConsumeDetailNew(@RequestParam(value = "idCard", required = false) String idCard,
                                               @RequestParam(value = "start", required = false) String start,
                                               @RequestParam(value = "end", required = false) String end) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard, "身份证号必填");
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFunc = formInfoService.getDetailConsumeNew(enterpriseId, idCard, startTime, endTime, null);
        List<CustomerConsume> formFuncList = formFunc.getContent();

        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("idCard", "身份证号");
        map.put("fullname", "姓名");
        map.put("typeNew", "单据类型");
        map.put("createTime", "时间");
        map.put("billCode", "订单/退单号");
        map.put("valetOnlinePrice", "消耗/退款邮豆");

        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员订单记录.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/netDataDown", method = RequestMethod.GET)
    public ModelAndView exportNetData(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                      @RequestParam(value = "start", required = false) String start,
                                      @RequestParam(value = "end", required = false) String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime = null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<FormFunc> formFunc = formInfoService.getBaseReport(enterpriseId, startTime, endTime, null);
        List<FormFunc> formFuncList = formFunc.getContent();
        Workbook wb;
        Map<String, String> map = new LinkedHashMap<>();
        map.put("grandEnterprise", "网点名称");
        map.put("newCustomerAmount", "新增会员数");
        map.put("expenditure", "邮豆发放笔数");
        map.put("totalMarketPrice", "营销邮豆发放金额");
        map.put("totalSalePrice", "促销邮豆发放金额");
        map.put("totalPrice", "发放总金额");
        wb = ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("网点基础数据统计报表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    /**
     * 集采商品导出
     *
     * @param goodsInfoIds
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param onlineShow
     * @param valetShow
     * @param goodsInfoType
     * @return
     */
    @SecurityResource(parent = "/web/itemManager", primary = false)
    @RequestMapping(value = "/goodsManagerDown", method = RequestMethod.GET)
    public ModelAndView exportExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "typeId", required = false) Long typeId,
            @RequestParam(value = "thirdId", required = false) Long thirdId,
            @RequestParam(value = "onlineShow", required = false) Boolean onlineShow,
            @RequestParam(value = "valetShow", required = false) Boolean valetShow,
            @RequestParam(value = "goodsInfoType", required = false) Integer goodsInfoType) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("specString", "货品规格");
        map.put("goodsInfoItemNo", "货品编号");
        map.put("goodsInfoPreferPrice", "商城价");
        map.put("goodsInfoSettlePrice", "结算价");
        map.put("goodsInfoAdded", "是否上架");
        map.put("goodsInfoTypeName", "货品类型");
        map.put("goodsBrand", "货品品牌");
        map.put("thirdName", "货品所属商家");
        map.put("onlineShow", "线上商城显示");
        map.put("valetShow", "代客商城显示");

        Workbook wb;
        if (goodsInfoIds.equals("all")) {
            Page<GoodsManagerResult> page = goodsManageService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, goodsInfoType, onlineShow, valetShow, null);
            List<GoodsManagerResult> list = page.getContent();

            wb = ExcelUtil.excelExport(map, list);
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            List<GoodsManagerResult> goodsManagerResults = new ArrayList<>();
            for (long id : ids) {
                GoodsManagerResult goodsManagerResult = goodsManageService.getByGoodsInfoId(id);
                goodsManagerResults.add(goodsManagerResult);
            }
            wb = ExcelUtil.excelExport(map, goodsManagerResults);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("商品列表.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    /**
     * 我的自购商品导出
     *
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param onlineShow
     * @return
     */
    @RequestMapping("/mySettleGoodsDown")
    public ModelAndView exportMySettleGoods(@RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
                                            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                                            @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                                            @RequestParam(value = "brandId", required = false) Long brandId,
                                            @RequestParam(value = "typeId", required = false) Long typeId,
                                            @RequestParam(value = "thirdId", required = false) Long thirdId,
                                            @RequestParam(value = "onlineShow", required = false) Boolean onlineShow,
                                            @RequestParam(value = "valetShow", required = false) Boolean valetShow) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("specString", "货品规格");
        map.put("goodsInfoItemNo", "货品编号");
        map.put("goodsInfoPreferPrice", "商城价");
        map.put("goodsInfoSettlePrice", "结算价");
        map.put("goodsInfoAdded", "是否上架");
        map.put("goodsInfoTypeName", "货品类型");
        map.put("goodsBrand", "货品品牌");
        map.put("enterpriseName", "货品所属商家");
        Workbook wb;
        List<GoodsManagerResult> list = new ArrayList<>();
        if (goodsInfoIds.equals("all")) {
            Page<GoodsManagerResult> page = goodsManageService.getSettleGoods(onlineShow, valetShow, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, null);
            list = page.getContent();
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            for (long id : ids) {
                GoodsManagerResult goodsManagerResult = goodsManageService.getByGoodsInfoId(id);
                list.add(goodsManagerResult);
            }
        }
        wb = ExcelUtil.excelExport(map, list);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("我的自购商品.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    /**
     * 自购商品导出
     *
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @return
     */
    @RequestMapping("/SettleGoodsDown")
    public ModelAndView exportSettleGoods(@RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
                                          @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                          @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                          @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                                          @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                                          @RequestParam(value = "brandId", required = false) Long brandId,
                                          @RequestParam(value = "typeId", required = false) Long typeId,
                                          @RequestParam(value = "thirdId", required = false) Long thirdId
    ) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("specString", "货品规格");
        map.put("goodsInfoItemNo", "货品编号");
        map.put("goodsInfoPreferPrice", "商城价");
        map.put("goodsInfoSettlePrice", "结算价");
        map.put("goodsInfoAdded", "是否上架");
        map.put("goodsInfoTypeName", "货品类型");
        map.put("goodsBrand", "货品品牌");
        map.put("enterpriseName", "货品所属商家");
        Workbook wb;
        List<GoodsManagerResult> list = new ArrayList<>();
        if (goodsInfoIds.equals("all")) {
            Page<GoodsManagerResult> page = goodsManageService.getSettleGoodsByEnterpriseId(enterpriseId, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, null);
            list = page.getContent();
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            for (long id : ids) {
                GoodsManagerResult goodsManagerResult = goodsManageService.getByGoodsInfoId(id);
                list.add(goodsManagerResult);
            }
        }
        wb = ExcelUtil.excelExport(map, list);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("自购商品.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);

    }


    @SecurityResource(parent = "/web/businessType", primary = false)
    @RequestMapping(value = "/systemChangeHistory", method = RequestMethod.GET)
    public ModelAndView exportSystemChangeHistory(@RequestParam(value = "typeId", required = false) Integer typeId,
                                                  @RequestParam(value = "start", required = false) String start,
                                                  @RequestParam(value = "end", required = false) String end
    ) {
        Date startTime = null;
        Date endTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<BusinessTypeHistory> page = systemManageService.getHistories(typeId, startTime, endTime, null);
        List<BusinessTypeHistory> list = page.getContent();
        Map<String, String> map = new LinkedHashMap<>();
        map.put("date", "时间");
        map.put("typeName", "业务类型");
        map.put("beforeEx", "变动前计算公式");
        map.put("afterEx", "变动后计算公式");
        map.put("operation", "变动说明");
        map.put("note", "备注");
        map.put("operator", "操作者账号");
        Workbook wb;
        wb = ExcelUtil.excelExport(map, list);

        Map<String, Object> outMaqp = new HashMap<>();
        outMaqp.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("营销邮豆计算公式变更记录.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        outMaqp.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, outMaqp);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/inventoryGoodsDown", method = RequestMethod.GET)
    public ModelAndView exportInventoryExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        HSSFWorkbook wb = new HSSFWorkbook();
        List<String> headList = Arrays.asList("货品名称", "货品品牌", "货品规格", "货品编号", "商品类型", "可用库存", "库存总量", "货品所属商家", "预警值");
        List<InventoryGoodsResult> list = new ArrayList<>();
        List<List<String>> outList = new ArrayList<>();
        if (goodsInfoIds.equals("all")) {
            Page<InventoryGoodsResult> page = inventoryManageService.getInventoryGoodsResult(goodsInfoName, goodsInfoItemNo, brandId, thirdId, null, null);
            list = page.getContent();
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            for (long id : ids) {
                InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getInventoryGoodsResultByPrimarykey(id);
                list.add(inventoryGoodsResult);
            }
        }
        for (InventoryGoodsResult goodsInfo : list) {
            List<String> sonList = new ArrayList<>();
            sonList.add(goodsInfo.getGoodsInfoName());
            sonList.add(goodsInfo.getGoodsBrand());
            sonList.add(goodsInfo.getSpecString());
            sonList.add(goodsInfo.getGoodsNumber());
            Integer type = goodsInfo.getGoodsInfoType();
            String typeString;
            if (type == null) {
                typeString = "";
            } else {
                typeString = type == 0 ? "集采商品" : "自购商品";
            }
            sonList.add(typeString);
            sonList.add(Constants.figureToString(goodsInfo.getAvailable()));
            sonList.add(Constants.figureToString(goodsInfo.getInventory()));
            sonList.add(goodsInfo.getGoodsMerchants());
            sonList.add(Constants.figureToString(goodsInfo.getGoodsInfoWarning()));
            outList.add(sonList);
        }
        wb = ExcelUtil.excelExport(headList, outList);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("库存货品.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/inventoryWarningGoodsDown", method = RequestMethod.GET)
    public ModelAndView exportInventoryWarningExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "thirdId", required = false) Long thirdId
            ) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("goodsBrand", "货品品牌");
        map.put("specString", "货品规格");
        map.put("goodsNumber", "货品编号");
        map.put("available", "可用库存");
        map.put("inventory", "库存总量");
        map.put("goodsInfoWarning", "预警值");
        map.put("goodsMerchants", "货品所属商家");
        Workbook wb;
        if (goodsInfoIds.equals("all")) {

            Page<InventoryGoodsResult> page = inventoryManageService.getWarningGoods(goodsInfoName, goodsInfoItemNo, brandId, thirdId, null);
            List<InventoryGoodsResult> list = page.getContent();
            wb = ExcelUtil.excelExport(map, list);
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            List<InventoryGoodsResult> inventoryGoodsResults = new ArrayList<>();
            for (long id : ids) {
                 InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getWarningGood(id);
                inventoryGoodsResults.add(inventoryGoodsResult);
            }
            wb = ExcelUtil.excelExport(map, inventoryGoodsResults);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("库存预警货品.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/accountManager", primary = false)
    @RequestMapping(value = "/orderDown", method = RequestMethod.GET)
    public void exportOrderExcel(@RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                 @RequestParam(value = "orderNoString", required = false) String orderNoString,
                                 @RequestParam(value = "status", required = false) OrderStatus status,
                                 @RequestParam(value = "selfPick", required = false) Boolean selfPick,
                                 @RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "receiver", required = false) String receiver,
                                 @RequestParam(value = "contactPhone", required = false) String contactPhone,
                                 @RequestParam(value = "createStart", required = false) String createStart,
                                 @RequestParam(value = "createEnd", required = false) String createEnd,
                                 @RequestParam(value = "payStart", required = false) String payStart,
                                 @RequestParam(value = "payEnd", required = false) String payEnd,
                                 HttpServletResponse response, HttpServletRequest request
    ) throws IOException {
        if (status != null && status == OrderStatus.WAIT_DELIVER) {
            status = OrderStatus.PAYED;
            selfPick = false;
        } else if (status != null && status == OrderStatus.WAIT_PICKUP) {
            status = OrderStatus.PAYED;
            selfPick = true;
        }
        username = Constants.nullOrNotBlank(username);
        receiver = Constants.nullOrNotBlank(receiver);
        contactPhone = Constants.nullOrNotBlank(contactPhone);
        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, Constants.DEFAULT_DATE_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, Constants.DEFAULT_DATE_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, Constants.DEFAULT_DATE_FORMAT);
        }
        if(orderNoString==null||orderNoString==""){
            orderNoString="all";
        };
        List<Order> orderList = new ArrayList<>();
        if (orderNoString.equals("all")) {
            Page<Order> orderPage = orderManageService.getOrders(null, enterpriseId, status, selfPick, username, receiver, contactPhone, createStartTime, createEndTime, payStartTime, payEndTime, null);
            orderList = orderPage.getContent();
        } else {
                Long[] orderNos = parser.parseJSON(orderNoString, new TypeToken<Long[]>() {
                });
                for (Long orderNo : orderNos) {
                    Page<Order> orderPage = orderManageService.getOrders(orderNo, enterpriseId, status, selfPick, username, receiver, contactPhone, createStartTime, createEndTime, payStartTime, payEndTime, null);
                    Order order = orderPage.getContent().get(0);
                    orderList.add(order);
                }
        }
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        String sheetName = "订单表-明细表";
        List<String> heads = Arrays.asList("订单号", "下单用户身份证号", "下单用户姓名", "下单时间", "付款时间",
                "商品名称", "购买数量", "订单金额", "订单状态", "配送方式", "商家",
                "收件人姓名", "收件人电话", "收件人地址", "自提网点", "快递公司",
                "快递单号", "用户支付金额", "网点补贴金额");
        List<List<String>> bodyList = new ArrayList<>();
        
        int ignoreRow = 0;        
        for (Order order : orderList) {
            List<String> sonList = new ArrayList<>();
            Long id = order.getEnterpriseId();
            sonList.add(String.valueOf(order.getOrderCode()));
            sonList.add(order.getCustomerInfo().getUsername());
            sonList.add(order.getCustomerInfo().getFullName());
            Date createTime = order.getCreateTime();
            sonList.add(order.getCreateTime() == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            Date payTime = order.getPayTime();
            sonList.add(order.getPayTime() == null ? "" : DateUtil.formatToString(payTime, "yyyy/MM/dd HH:mm"));
            List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
            String goodsNameString = "";
            Long amount = 0L;
            for (OrderGoods orderGoods : orderGoodsList) {
                String goodsName = orderGoods.getGoodsInfoName() + "(" + orderGoods.getGoodsInfoNum() + ")";
                goodsNameString += goodsName + ",";
                amount += orderGoods.getGoodsInfoNum();
            }
            goodsNameString = goodsNameString.substring(0, goodsNameString.length() - 1);
            sonList.add(goodsNameString);
            sonList.add(String.valueOf(amount));
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String orderPrice = decimalFormat.format(order.getOrderPrice());
            sonList.add(orderPrice);
            if (order.getSelfPick()) {

                OrderStatus orderStatus = order.getOrderStatus();
                if (orderStatus.equals(OrderStatus.PAYED)) {
                    sonList.add("待提货");
                } else {
                    sonList.add(order.getOrderStatus().toString());
                }
                sonList.add("用户自提");
                sonList.add(id == null || enterpriseInfoService.getEnterpriseInfo(id) == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
                sonList.add(order.getCustomerInfo().getFullName());
                sonList.add(order.getCustomerInfo().getContactPhone());
                sonList.add("");
                sonList.add(id == null || enterpriseInfoService.getEnterpriseInfo(id) == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getAccountName());
                sonList.add("");
                sonList.add("");

            }else{
                sonList.add(order.getOrderStatus().toString());
                sonList.add("快递配送");
                sonList.add(order.getBusiness().getBusinessName());
                sonList.add(order.getShippingPerson());
                sonList.add(order.getShippingMobile());
                String province = order.getShippingProvince();
                String city = order.getShippingCity();
                String district = order.getShippingCounty();
                String detailAddress = order.getShippingAddress();
                String address = province + city + district + detailAddress;
                sonList.add(address);
                sonList.add("");
                List<OrderContainerRelation> orderContainerRelationList = order.getOrderContainerRelations();
                if (orderContainerRelationList.size() != 0) {
                    OrderContainerRelation orderContainerRelation = orderContainerRelationList.get(0);

                    sonList.add(orderContainerRelation.getExpressName());

                    sonList.add(orderContainerRelation.getExpressNo());
                } else {

                    sonList.add("");

                    sonList.add("");

                }
            }
            String orderPrice1 = decimalFormat.format(order.getOrderPrice().subtract(order.getSubsidyPrice() == null ? BigDecimal.ZERO : order.getSubsidyPrice()).doubleValue());
            sonList.add(orderPrice1);

            String SubsidyPrice = decimalFormat.format(order.getSubsidyPrice() == null ? BigDecimal.ZERO : order.getSubsidyPrice());
            sonList.add(SubsidyPrice);
            bodyList.add(sonList);
        }

        wb = ExcelUtilXssf.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String excelName = sheetName+".xlsx";
        try {
            excelName = URLEncoder.encode(excelName,"UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//        excelName = encodeFilename(excelName, request);// 处理中文文件名
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();

    }

    @SecurityResource(parent = "/web/accountManager", primary = false)
    @RequestMapping(value = "/backOrderDown", method = RequestMethod.GET)
    public ModelAndView exportBackOrderExcel(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                             @RequestParam(value = "backOrderNo", required = false) String backOrderNos,
                                             @RequestParam(value = "phoneNo", required = false) String phoneNo,
                                             @RequestParam(value = "orderNo", required = false) Long orderNo,
                                             @RequestParam(value = "status", required = false) CreditOrderStatus status,
                                             @RequestParam(value = "start", required = false) String start,
                                             @RequestParam(value = "end", required = false) String end) {
        phoneNo = Constants.nullOrNotBlank(phoneNo);
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFCellStyle style = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style);
        sheet.setDefaultColumnStyle(1, style);
        sheet.setDefaultColumnStyle(2, style);
        sheet.setDefaultColumnStyle(8, style);
        sheet.setDefaultColumnStyle(9, style);
        //构造表头
        HSSFRow headRow = sheet.createRow(0);
        List<String> headList = Arrays.asList("退单号", "订单号", "下单用户身份证号", "下单用户姓名", "订单付款时间",
                "退单申请时间", "退货商品名称", "退货商品数量", "订单金额", "退单金额",
                "订单快递", "订单快递单号", "收件人姓名", "收件人电话", "商家", "退单状态",
                "退单原因", "问题说明");
        for (int i = 0; i < headList.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(headList.get(i));
        }
        if(backOrderNos==null||backOrderNos==""){
            backOrderNos="all";
        };
        //填充表格内容
        List<CreditOrder> creditOrderList = new ArrayList<>();
        if (backOrderNos.equals("all")) {
            Page<CreditOrder> creditOrderPage = orderManageService.getBackOrder(null, enterpriseId, phoneNo, orderNo, status, startTime, endTime, null);
            creditOrderList = creditOrderPage.getContent();
        } else {
            Long[] crederOrderNo = parser.parseJSON(backOrderNos, new TypeToken<Long[]>() {
            });
            for (Long backOrderNo : crederOrderNo) {
                CreditOrder backOrder = creditOrderService.selectByCreditOrderCode(backOrderNo);
                creditOrderList.add(backOrder);
            }
        }

        for (int i = 0; i < creditOrderList.size(); i++) {
            HSSFRow backOrderRow = sheet.createRow(i + 1);
            CreditOrder creditOrder = creditOrderList.get(i);
            CreditOrder.Business business = creditOrder.getBusiness();
            CreditOrder.OriginalOrder originalOrder = creditOrder.getOrder();
            Long orderCode = originalOrder.getOrderCode();
            Order order = orderService.getOrder(orderCode);
            Order.CustomerInfo customerInfo = order.getCustomerInfo();

            HSSFCell cell = backOrderRow.createCell(0);
            cell.setCellValue(creditOrder.getBackOrderCode() + "");
            cell = backOrderRow.createCell(1);
            cell.setCellValue(orderCode + "");
            cell = backOrderRow.createCell(2);
            cell.setCellValue(customerInfo.getUsername());
            cell = backOrderRow.createCell(3);
            cell.setCellValue(customerInfo.getFullName());
            cell = backOrderRow.createCell(4);
            Date payTime = order.getPayTime();
            DateUtil.formatToString(payTime, "yyyy/MM/dd HH/mm");
            cell.setCellValue(payTime == null ? "" : DateUtil.formatToString(payTime, "yyyy/MM/dd HH:mm"));
            cell = backOrderRow.createCell(5);
            Date backTime = creditOrder.getBackTime();
            cell.setCellValue(DateUtil.formatToString(backTime, "yyyy/MM/dd HH:mm"));
            cell = backOrderRow.createCell(6);
            List<BackOrderGoods> backOrderGoodses = creditOrder.getBackOrderGoods();
            String backGoodsName = " ";
            Long backAmount = 0L;
            for (BackOrderGoods backOrderGoods : backOrderGoodses) {
                String goodsINfoName = backOrderGoods.getGoodsInfoName() + "(" + backOrderGoods.getGoodsNum() + ")";
                backGoodsName += goodsINfoName + ",";
                backAmount += backOrderGoods.getGoodsNum();
            }

            backGoodsName = backGoodsName.substring(0, backGoodsName.length() - 1);
            cell.setCellValue(backGoodsName);
            cell = backOrderRow.createCell(7);
            cell.setCellValue(backAmount);
            cell = backOrderRow.createCell(8);
            DecimalFormat decimalFormat = new DecimalFormat();
            String orderPrice = decimalFormat.format(originalOrder.getOrderPrice());
            cell.setCellValue(orderPrice);
            cell = backOrderRow.createCell(9);
            String backPrice = decimalFormat.format(creditOrder.getBackPrice());
            cell.setCellValue(backPrice);
            if (!originalOrder.getSelfPick()) {
                List<OrderContainerRelation> orderContainerRelationList = order.getOrderContainerRelations();
                if (orderContainerRelationList.size() != 0) {
                    OrderContainerRelation orderContainerRelation = orderContainerRelationList.get(0);
                    cell = backOrderRow.createCell(10);
                    cell.setCellValue(orderContainerRelation.getExpressName());
                    cell = backOrderRow.createCell(11);
                    cell.setCellValue(orderContainerRelation.getExpressNo());
                } else {
                    cell = backOrderRow.createCell(10);
                    cell.setCellValue("");
                    cell = backOrderRow.createCell(11);
                    cell.setCellValue("");
                }
              /*      cell = backOrderRow.createCell(10);
                cell.setCellValue("订单快递");
                cell = backOrderRow.createCell(11);
                cell.setCellValue("订单快递单号");*/
                cell = backOrderRow.createCell(12);
                cell.setCellValue(creditOrder.getBackCollectPerson());
                cell = backOrderRow.createCell(13);
                cell.setCellValue(creditOrder.getBackCollectPhone());
                cell = backOrderRow.createCell(14);
                cell.setCellValue(business.getBusinessName());
            } else {
                cell = backOrderRow.createCell(10);
                cell.setCellValue("");
                cell = backOrderRow.createCell(11);
                cell.setCellValue("");
                cell = backOrderRow.createCell(12);
                cell.setCellValue(customerInfo.getFullName());
                cell = backOrderRow.createCell(13);
                cell.setCellValue(customerInfo.getContactPhone());
                cell = backOrderRow.createCell(14);
                Long id = order.getEnterpriseId();
                cell.setCellValue(id == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
            }
            cell = backOrderRow.createCell(15);
            cell.setCellValue(creditOrder.getBackCheck().toString());
            cell = backOrderRow.createCell(16);
            cell.setCellValue(creditOrder.getBackReason().getTag());
            cell = backOrderRow.createCell(17);
            cell.setCellValue(creditOrder.getBackRemark());
        }

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("退单.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/BillManager", primary = false)
    @RequestMapping(value = "/myGrandDown", method = RequestMethod.GET)
    public ModelAndView exportGrandExcel(@RequestParam(value = "code", required = false) String tradeCodes,
                                         @RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("code", "交易单号");
        map.put("fee", "发放邮宝金额");
        map.put("sendType", "交易类型");
        Workbook wb;
        if (tradeCodes.equals("all")) {
            Page<EnterpriseBatchGrand> page = ucoinBillManageService.getGrands(null, startTime, endTime, null);
            List<EnterpriseBatchGrand> list = page.getContent();
            wb = ExcelUtil.excelExport(map, list);
        } else {
            Long[] codes = parser.parseJSON(tradeCodes, new TypeToken<Long[]>() {
            });
            List<EnterpriseBatchGrand> enterpriseBatchGrands = new ArrayList<>();
            for (long code : codes) {
                Page<EnterpriseBatchGrand> page = ucoinBillManageService.getGrands(code, startTime, endTime, null);
                enterpriseBatchGrands = page.getContent();
                //EnterpriseBatchGrand enterpriseBatchGrand=enterpriseBatchGrandMapper;
                //enterpriseBatchGrands.add(enterpriseBatchGrand);
            }
            wb = ExcelUtil.excelExport(map, enterpriseBatchGrands);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("账单管理.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/grandHistory", method = RequestMethod.GET)
    public ModelAndView grandHistory(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                     @RequestParam(value = "typeId") Integer typeId,
                                     @RequestParam(value = "start") String start,
                                     @RequestParam(value = "end") String end) {

        Date startTime = null;
        Date endTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //构造表头
        Page<CustomerUcoinHistory> customerUcoinHistoryPage = ucoinGrandService.getGrandHistory(enterpriseId, typeId, startTime, endTime, null);
        List<CustomerUcoinHistory> customerUcoinHistoryList = customerUcoinHistoryPage.getContent();
        List<String> jsonKey = new ArrayList<>();
        for (CustomerUcoinHistory customerUcoinHistory : customerUcoinHistoryList) {
            String paramjson = customerUcoinHistory.getParamJson();
            Map<String, String> map = parser.parseJSON(paramjson, new TypeToken<Map<String, String>>() {
            });
            if (map != null) {
                for (String key : map.keySet()) {
                    if (!jsonKey.contains(key)) {
                        jsonKey.add(key);
                    }
                }
            }
        }

        HSSFCell cell = row.createCell(0);
        sheet.setColumnWidth(0, 24 * 256);
        cell.setCellValue("网点名称");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        sheet.setColumnWidth(1, 30 * 256);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell = row.createCell(2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("营销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("促销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("邮豆总金额");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        sheet.setColumnWidth(7, 30 * 256);
        cell.setCellValue("邮豆发放时间");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("业务类型");
        cell.setCellStyle(style);
        for (int i = 0; i < jsonKey.size(); i++) {
            cell = row.createCell(i + 9);
            cell.setCellValue(jsonKey.get(i));
            cell.setCellStyle(style);
        }

    /*    //key：网点id；value:该网点发放记录总条数
        Map<Long, Integer> keyMap = new LinkedHashMap<>();
        Long eId = -1l;
        int k = 0;*/
        //填充表格内容
        for (int j = 0; j < customerUcoinHistoryList.size(); j++) {
            row = sheet.createRow(j + 1);
            CustomerUcoinHistory customerUcoinHistory = customerUcoinHistoryList.get(j);
            ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerUcoinHistory.getCustomerId());
            cell = row.createCell(0);
            Long id = customerUcoinHistory.getEnterpriseId();
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell = row.createCell(1);
            cell.setCellValue(customer.getIdcardNo());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            row.createCell(2).setCellValue(customer.getFullname());
            BusinessType businessType = businessTypeService.getByTypeId(customerUcoinHistory.getTypeId());
            row.createCell(3).setCellValue(customerUcoinHistory.getMarketPrice().toString());
            Assert.notNull(customerUcoinHistory.getSalesPrice());
            row.createCell(4).setCellValue(customerUcoinHistory.getSalesPrice().toString());
            row.createCell(5).setCellValue(customerUcoinHistory.getPrice().toString());
            Date createTime = customerUcoinHistory.getCreateTime();
            String remark = customerUcoinHistory.getRemark();
            remark = remark == null ? "" : remark;
            row.createCell(6).setCellValue(remark);
            row.createCell(7).setCellValue(createTime == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            row.createCell(8).setCellValue("市局统一发放");
          /*  if (customerUcoinHistory.getEnterpriseId() == eId) {
                k++;
                keyMap.put(eId, k);
            } else {
                eId = customerUcoinHistory.getEnterpriseId();
                k++;
                keyMap.put(eId, k);
            }*/
            String paramJson = customerUcoinHistory.getParamJson();
            Map<String, String> map = parser.parseJSON(paramJson, new TypeToken<Map<String, String>>() {
            });
            if (map == null) {
                map = new HashMap<>();
            }
            for (int i = 0; i < jsonKey.size(); i++) {
                String value = map.get(jsonKey.get(i));
                /*int paramValueId=Integer.valueOf(map.get(jsonKey.get(i))).intValue();
                ParamValue paramValue=paramValueService.getParamValue(paramValueId);
                String value=paramValue.getParamValueName();*/
                if (value == null) {
                    value = "";
                }
                row.createCell(i + 9).setCellValue(value);
            }
        }

    /*    //合并网点名单元格
        int s = 1;
        for (Long id : keyMap.keySet()) {
            int amount = keyMap.get(id);
            CellRangeAddress cellAddress = new CellRangeAddress(s, amount, 0, 0);
            sheet.addMergedRegion(cellAddress);
            row = sheet.getRow(s);
            cell = row.createCell(0);
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell.setCellStyle(style);
            s = amount + 1;
        }*/


        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("邮宝发放记录.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/newGrandHistory", method = RequestMethod.GET)
    public ModelAndView newGrandHistory(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                        @RequestParam(value = "typeId") Integer typeId,
                                        @RequestParam(value = "start") String start,
                                        @RequestParam(value = "end") String end) {

        Date startTime = null;
        Date endTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime = DateUtil.getMorning(startTime);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime = DateUtil.getNight(endTime);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //构造表头
        Page<CustomerUcoinHistory> customerUcoinHistoryPage = ucoinGrandService.getGrandHistory(enterpriseId, typeId, startTime, endTime, null);
        List<CustomerUcoinHistory> customerUcoinHistoryList = customerUcoinHistoryPage.getContent();

        HSSFCell cell = row.createCell(0);
        sheet.setColumnWidth(0, 24 * 256);
        cell.setCellValue("网点名称");
        cell.setCellStyle(style);
        cell = row.createCell(1);
        sheet.setColumnWidth(1, 30 * 256);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell = row.createCell(2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("营销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("促销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("邮豆总金额");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        sheet.setColumnWidth(7, 30 * 256);
        cell.setCellValue("邮豆发放时间");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("业务类型");
        cell.setCellStyle(style);

        //填充表格内容
        for (int j = 0; j < customerUcoinHistoryList.size(); j++) {
            row = sheet.createRow(j + 1);
            CustomerUcoinHistory customerUcoinHistory = customerUcoinHistoryList.get(j);
            CustomerUcoinHistory.CustomerInfo customerInfo = customerUcoinHistory.getCustomerInfo();
            //ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerUcoinHistory.getCustomerId());
            cell = row.createCell(0);
            //Long id = customerUcoinHistory.getEnterpriseId();
            //EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            CustomerUcoinHistory.EnterpriseInfo enterpriseInfo = customerUcoinHistory.getEnterpriseInfo();
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell = row.createCell(1);
            cell.setCellValue(customerInfo.getIdCard());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            row.createCell(2).setCellValue(customerInfo.getName());
            //BusinessType businessType = businessTypeService.getByTypeId(customerUcoinHistory.getTypeId());
            row.createCell(3).setCellValue(customerUcoinHistory.getMarketPrice().toString());
            Assert.notNull(customerUcoinHistory.getSalesPrice());
            row.createCell(4).setCellValue(customerUcoinHistory.getSalesPrice().toString());
            row.createCell(5).setCellValue(customerUcoinHistory.getPrice().toString());
            Date createTime = customerUcoinHistory.getCreateTime();
            String remark = customerUcoinHistory.getRemark();
            remark = remark == null ? "" : remark;
            row.createCell(6).setCellValue(remark);
            row.createCell(7).setCellValue(createTime == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            row.createCell(8).setCellValue("市局统一发放");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("邮宝发放记录.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }


    @SecurityResource(parent = "/web/memberManager", primary = false)
    @RequestMapping(value = "/customerInfoDown", method = RequestMethod.GET)
    public ModelAndView exportCustomerInfoExcel(@RequestParam(value = "idCard", required = false) String idCard,
                                                @RequestParam(value = "linkPhone", required = false) String linkPhone,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                @RequestParam(value = "isPhoneChecked", required = false) Boolean isPhoneChecked,
                                                @RequestParam(value = "managerNo", required = false) String managerNo,
                                                @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                                @RequestParam(value = "tag", required = false) String tag) {

        idCard = StringUtil.nullOrNotBlank(idCard);
        linkPhone = StringUtil.nullOrNotBlank(linkPhone);
        name = StringUtil.nullOrNotBlank(name);
        managerNo = StringUtil.nullOrNotBlank(managerNo);
        List<String> heads = new ArrayList<>();
        heads.add("身份证号");
        heads.add("姓名");
        heads.add("性别");
        heads.add("联系地址");
        heads.add("联系电话");
        heads.add("客户经理号");
        heads.add("账户邮豆余额");
        heads.add("账号状态");
        heads.add("账号创建的网点名称");
        heads.add("账号创建者账号");
        heads.add("创建时间");

        Workbook wb;
        Page<ChinapostCustomer> page = customerManageService.getCustomers(idCard, linkPhone, name, isActive,
                isPhoneChecked, managerNo, tag, enterpriseId, null);
        List<ChinapostCustomer> sonList = page.getContent();

        List<List<String>> outList = new ArrayList<>();
        for (ChinapostCustomer customer : sonList) {
            List<String> content = new ArrayList<>();
            content.add(customer.getIdcardNo());
            content.add(customer.getFullname());
            String idCardNo = customer.getIdcardNo();

            try {
                if (idCardNo.length() == 18) {
                    String male = idCardNo.substring(16, 17);
                    content.add(Integer.parseInt(male) % 2 == 0 ? "女" : "男");
                } else {
                    content.add("");
                }
            } catch (NumberFormatException e) {
                content.add("");
            }


            content.add(customer.getContactAddr());
            content.add(customer.getContactPhone());
            content.add(customer.getManagerNo());
            content.add(customer.getTotalUcoin().toString());
            content.add(customer.getIsActive() ? "已激活" : "未激活");
            content.add(customer.getEnterpriseName());
            content.add(customer.getUsername());
            Date createTime = customer.getCreateTime();
            content.add(createTime == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            outList.add(content);
        }

        wb = ExcelUtil.excelExport(heads, outList);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("会员信息.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/downByGoodsInfo", method = RequestMethod.GET)
    public ModelAndView exportInventoryGoodsByGoodsInfo(@RequestParam("enterpriseId") long enterpriseId,
                                                        @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                        @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                                        @RequestParam(value = "brandId", required = false) Long brandId,
                                                        @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        List<String> heads = new ArrayList<>();
        heads.add("货品名称");
        heads.add("货品规格");
        heads.add("货品编号");
        heads.add("商品类型");
        heads.add("库存");
        heads.add("可用库存");
        heads.add("品牌");
        heads.add("所属商家");
        Workbook wb;
        Page<InventoryGoodsResult> inventoryGoodsResults = inventoryManageService.queryByGoodsInfo(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, null);
        List<InventoryGoodsResult> sonList = inventoryGoodsResults.getContent();
        List<List<String>> outList = new ArrayList<>();
        for (InventoryGoodsResult model : sonList) {
            List<String> content = new ArrayList<>();
            content.add(model.getGoodsInfoName());
            content.add(model.getSpecString());
            content.add(model.getGoodsNumber());
            Integer goodsType = model.getGoodsInfoType();
            String type;
            if (goodsType == null) {
                type = "";
            } else if (goodsType == 0) {
                type = "集采";
            } else {
                type = "自购";
            }
            content.add(type);
            content.add(model.getSumInventory());
            content.add(model.getSumAvailable());
            content.add(model.getGoodsBrand());
            content.add(model.getGoodsMerchants());
            outList.add(content);
        }
        wb = ExcelUtil.excelExport(heads, outList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);

        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("根据货品查询库存.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/downByInventory", method = RequestMethod.GET)
    public ModelAndView exportInventoryGoodsByInventory(@RequestParam("enterpriseId") long enterpriseId,
                                                        @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                        @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                                        @RequestParam(value = "brandId", required = false) Long brandId,
                                                        @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        //style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        HSSFCellStyle headStyle = ExcelUtil.headStyle(wb);

        //构造表头
        //第一行
        HSSFCell cell = row.createCell(0);
        sheet.setColumnWidth(0, 20 * 256);
        cell.setCellValue("网点名称");
        cell.setCellStyle(style);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell = row.createCell(1);
        cell.setCellValue("货品名称");
        sheet.setColumnWidth(1, 40 * 256);
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("货品规格");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("货品编号");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("库存");
        sheet.setColumnWidth(4, 10 * 256);
        cell.setCellStyle(style);
        cell = row.createCell(5);
        sheet.setColumnWidth(5, 10 * 256);
        cell.setCellValue("可用库存");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("商家");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("品牌");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("商品类型");
        cell.setCellStyle(style);
        //填充表格内容
        Page<InventoryGoodsResult> page = inventoryService.queryByInventory(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, null);
        List<InventoryGoodsResult> sonList = page.getContent();
        //key：网点id；value：网点商品数量
        Map<Long, Integer> map = new LinkedHashMap<>();
        Long eId = -1L;
        int j = 0;
        for (int i = 0; i < sonList.size(); i++) {
            InventoryGoodsResult model = sonList.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(1);
            cell.setCellValue(model.getGoodsInfoName());
            cell = row.createCell(2);
            cell.setCellValue(model.getSpecString());
            cell = row.createCell(3);
            cell.setCellValue(model.getGoodsNumber());
            cell = row.createCell(4);
            cell.setCellValue(model.getInventory());
            cell = row.createCell(5);
            cell.setCellValue(model.getAvailable());
            cell = row.createCell(6);
            cell.setCellValue(model.getGoodsMerchants());
            cell = row.createCell(7);
            cell.setCellValue(model.getGoodsBrand());
            cell = row.createCell(8);
            Integer goodsType = model.getGoodsInfoType();
            String type;
            if (goodsType == null) {
                type = "";
            } else if (goodsType == 0) {
                type = "集采";
            } else {
                type = "自购";
            }
            cell.setCellValue(type);
            if (model.getEnterpriseId().equals(eId)) {
                j++;
                map.put(eId, j);
            } else {
                eId = model.getEnterpriseId();
                j++;
                map.put(eId, j);
            }
        }

        //合并网点名单元格
        int s = 1;
        for (Long id : map.keySet()) {
            int amount = map.get(id);
            CellRangeAddress cellAddress = new CellRangeAddress(s, amount, 0, 0);
            sheet.addMergedRegion(cellAddress);
            row = sheet.getRow(s);
            cell = row.createCell(0);
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell.setCellStyle(headStyle);
            s = amount + 1;
        }


        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        String finalFileName = null;
        try {
            finalFileName = URLEncoder.encode("根据仓库下载库存货品.xls","UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        map1.put("fileName", finalFileName);
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    @RequestMapping(value = "/templateDown", method = RequestMethod.GET)
    public ModelAndView ExportTemplateExcel(Integer typeId) {

        HSSFWorkbook wb = new HSSFWorkbook();
        //创建主表
        HSSFSheet templateSheet = wb.createSheet("模板");
     /*   //创建省市数据源表
        HSSFSheet provinceSheet = wb.createSheet("省市信息表");
        //创建市区数据源表
        HSSFSheet citySheet = wb.createSheet("市区信息表");*/
        //创建参数信息表
        HSSFSheet paramSheet = wb.createSheet("参数信息表");

     /*   //获取省份信息
        List<Province> provinces = addressService.getProvinces();
        int rowNumber = 0;
        for (int i = 0; i < provinces.size(); i++) {
            HSSFRow provinceRow = provinceSheet.createRow(i);
            HSSFCell provinceCell = provinceRow.createCell(0);
            provinceCell.setCellValue(provinces.get(i).getProvinceName());
            //根据省份获取相应市
            List<City> cities = addressService.getCities(provinces.get(i).getProvinceId());
            //为市创建省份名称
            String provincName = provinces.get(i).getProvinceName();
            createName(wb, provincName, "省市信息表!$B$" + (i + 1) + ":$" + getcellColumnFlag(cities.size() + 1) + "$" + (i + 1));
            for (int j = 0; j < cities.size(); j++) {
                HSSFCell cityCell = provinceRow.createCell(j + 1);
                cityCell.setCellValue(cities.get(j).getCityName());
                HSSFRow cityRow = citySheet.createRow(rowNumber);
                HSSFCell cell = cityRow.createCell(0);
                cell.setCellValue(cities.get(j).getCityName());
                //根据市获取相应区
                List<District> districts = addressService.getDistricts(cities.get(j).getCityId());
                //为区创建市名称
                String cityName = cities.get(j).getCityName();
                cityName = cityName.equals(provincName) ? cityName + "市级" : cityName;
                createName(wb, cityName, "市区信息表!$B$" + (rowNumber + 1) + ":$" + getcellColumnFlag(districts.size() + 1) + "$" + (rowNumber + 1));
                rowNumber++;
                for (int k = 0; k < districts.size(); k++) {
                    HSSFCell districtCell = cityRow.createCell(k + 1);
                    districtCell.setCellValue(districts.get(k).getDistrictName());
                }
            }
        }*/

        HSSFRow row = templateSheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        HSSFCellStyle style2 = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style2.setDataFormat(format.getFormat("@"));
        templateSheet.setDefaultColumnStyle(0, style2);
        cell = row.createCell(1);
        cell.setCellValue("省");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("市");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("区");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("详细地址");
        cell.setCellStyle(style);
        templateSheet.setDefaultColumnStyle(4, style2);
        cell = row.createCell(5);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        templateSheet.setDefaultColumnStyle(6, style2);

        cell = row.createCell(7);
        cell.setCellValue("促销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
       /* //给省设置数据有效性
        createName(wb, "province", "省市信息表!$A$1:$A$34");
        HSSFDataValidation valiProvince = setDataValidation("province", 1, 1000, 1, 1);
        templateSheet.addValidationData(valiProvince);
        //市及区设置有效性
        for (int i = 2; i < 1000; i++) {
            HSSFDataValidation validCity = setDataValidation("INDIRECT(B" + i + ")", (i - 1), (i - 1), 2, 2);
            templateSheet.addValidationData(validCity);
            HSSFDataValidation validDistrict = setDataValidation("INDIRECT(C" + i + ")", (i - 1), (i - 1), 3, 3);
            templateSheet.addValidationData(validDistrict);
        }
*/
        //给excel动态增加参数列，同时将参数信息插入参数信息表
        BusinessType businessType = businessTypeService.getDetails(typeId);
       /* cell=row.createCell(9);
        cell.setCellValue("业务类型");*/

        List<Param> params = businessType.getParams();
        //调整列宽
        for (int i = 0; i < params.size() + 9; i++) {
            templateSheet.setColumnWidth(i, 14 * 256);
        }
        for (int i = 0; i < params.size(); i++) {
            cell = row.createCell(i + 9);
            String paramName = params.get(i).getParamName();
            cell.setCellValue(paramName);
            cell.setCellStyle(style);
            //参数信息表插入数据
            HSSFRow paramRow = paramSheet.createRow(i);
            HSSFCell paramCell = paramRow.createCell(0);
            paramCell.setCellValue(paramName);
            List<ParamValue> paramValues = params.get(i).getParamValues();
            if (params.get(i).getParamType() == ParamType.TYPE_ENUM) {
                createName(wb, "_" + paramName, "参数信息表!$B$" + (i + 1) + ":$" + getcellColumnFlag(paramValues.size() + 1) + "$" + (i + 1));
                //选项参数的有效性
                HSSFDataValidation validParam = setDataValidation("_" + paramName, 1, 1000, i + 9, i + 9);
                templateSheet.addValidationData(validParam);
            }
            for (int j = 0; j < paramValues.size(); j++) {
                paramCell = paramRow.createCell(j + 1);
                paramCell.setCellValue(paramValues.get(j).getParamValueName());
            }

        }
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        map.put("fileName", businessType.getTypeName() + ".xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    /**
     * 查询供应商供货信息,以供应商为查询条件，按照网点分组
     *
     * @param enterpriseId
     * @param orderStartTime
     * @param orderEndTime
     * @param readyStartTime
     * @param readyEndTime
     * @param warehouseStartTime
     * @param warehouseEndTime
     * @param billStatuss
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/getSupplyByThirdAndEnterprise")
    @ResponseBody
    public String getSupplyByThirdAndEnterprise(@RequestParam("enterpriseId") Long enterpriseId,
                                                @RequestParam(value = "orderStartTime", required = false) String orderStartTime,
                                                @RequestParam(value = "orderEndTime", required = false) String orderEndTime,
                                                @RequestParam(value = "readyStartTime", required = false) String readyStartTime,
                                                @RequestParam(value = "readyEndTime", required = false) String readyEndTime,
                                                @RequestParam(value = "warehouseStartTime", required = false) String warehouseStartTime,
                                                @RequestParam(value = "warehouseEndTime", required = false) String warehouseEndTime,
                                                @RequestParam(value = "thirdId", required = false) Long thirdId,
                                                @RequestParam(value = "thirdEnterpriseId", required = false) Long thirdEnterpriseId,
                                                @RequestParam(value = "billStatuss", required = false) List<BillStatus> billStatuss,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        Date orderStartT = null;
        Date orderEndT = null;
        orderStartTime = Constants.nullOrNotBlank(orderStartTime);
        orderEndTime = Constants.nullOrNotBlank(orderEndTime);
        if (orderStartTime != null) {
            orderStartT = DateUtil.fromString(orderStartTime, Constants.DEFAULT_DAY_FORMAT);
            orderStartT = DateUtil.getMorning(orderStartT);
        }
        if (orderEndTime != null) {
            orderEndT = DateUtil.fromString(orderEndTime, Constants.DEFAULT_DAY_FORMAT);
            orderEndT = DateUtil.getNight(orderEndT);
        }

        Date readyStartT = null;
        Date readyEndT = null;
        readyStartTime = Constants.nullOrNotBlank(readyStartTime);
        readyEndTime = Constants.nullOrNotBlank(readyEndTime);
        if (readyStartTime != null) {
            readyStartT = DateUtil.fromString(readyStartTime, Constants.DEFAULT_DAY_FORMAT);
            readyStartT = DateUtil.getMorning(readyStartT);
        }
        if (readyEndTime != null) {
            readyEndT = DateUtil.fromString(readyEndTime, Constants.DEFAULT_DAY_FORMAT);
            readyEndT = DateUtil.getNight(readyEndT);
        }

        Date warehouseStartT = null;
        Date warehouseEndT = null;
        warehouseStartTime = Constants.nullOrNotBlank(warehouseStartTime);
        warehouseEndTime = Constants.nullOrNotBlank(warehouseEndTime);
        if (warehouseStartTime != null) {
            warehouseStartT = DateUtil.fromString(warehouseStartTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseStartT = DateUtil.getMorning(warehouseStartT);
        }
        if (warehouseEndTime != null) {
            warehouseEndT = DateUtil.fromString(warehouseEndTime, Constants.DEFAULT_DAY_FORMAT);
            warehouseEndT = DateUtil.getNight(warehouseEndT);
        }
        Page<SupplyForm> supplyFormPage = formInfoService.getSupplyByThirdAndEnterprise(enterpriseId, orderStartT, orderEndT, readyStartT, readyEndT, warehouseStartT, warehouseEndT, thirdId, thirdEnterpriseId, billStatuss, new Pageable(page, size));
        return new JsonResponseBean(supplyFormPage).toJson();
    }


    /**
     * 名称管理器
     */
    public static HSSFName createName(HSSFWorkbook wb, String name, String expression) {
        HSSFName refer = wb.createName();
        refer.setRefersToFormula(expression);
        refer.setNameName(name);
        return refer;
    }

    /**
     * 数据有效性
     */
    public static HSSFDataValidation setDataValidation(String name, int firstRow, int endRow, int firstCol, int endCol) {
        //加载下拉列表内容
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(name);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        org.apache.poi.ss.util.CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
        // 数据有效性对象
        return new HSSFDataValidation(regions, constraint);
    }

    //根据数据量查询所在列
    private String getcellColumnFlag(int num) {
        String columFiled = "";
        int chuNum;
        int yuNum;
        if (num >= 1 && num <= 26) {
            columFiled = this.doHandle(num);
        } else {
            chuNum = num / 26;
            yuNum = num % 26;

            columFiled += this.doHandle(chuNum);
            columFiled += this.doHandle(yuNum);
        }
        return columFiled;
    }

    private String doHandle(final int num) {
        String[] charArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"
                , "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return charArr[num - 1];
    }
}