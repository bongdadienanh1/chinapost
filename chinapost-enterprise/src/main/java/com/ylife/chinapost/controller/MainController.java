package com.ylife.chinapost.controller;

import com.google.gson.reflect.TypeToken;
import com.ylife.authority.mapper.pojo.AuthWithMAmountResult;
import com.ylife.authority.model.ResourcePage;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.*;
import com.ylife.chinapost.service.pojo.WealthManageResult;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.ChinapostTag;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.ChinapostTagService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.pojo.ManagerWithAuthNameResult;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.exception.UserNotLoginException;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.BillType;
import com.ylife.inventory.model.InventoryBill;
import com.ylife.inventory.model.InventoryBillLog;
import com.ylife.order.model.*;
import com.ylife.product.model.GoodsInfo;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.system.model.BusinessType;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.wealth.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/5.
 * Main
 */
@Controller
@RequestMapping("/web")
public class MainController {

    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private WealthManageService wealthManageService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private HierarchyManageService hierarchyManageService;
    @Resource
    private UcoinQueryService ucoinQueryService;
    @Resource
    private RoleManageService roleManageService;
    @Resource
    private RequisitionManageService requisitionManageService;
    @Resource
    private CustomerManageService customerManageService;
    @Resource
    private AccountCenterService accountCenterService;
    @Resource
    private InventoryManageService inventoryGoodsService;
    @Resource
    private UcoinBillManageService ucoinBillManageService;
    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private ValetOrderService valetOrderService;
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private ChinapostTagService chinapostTagService;
    @Resource
    private SystemManageService systemManageService;
    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private NewGrandService newGrandService;

    @ExceptionHandler(UserNotLoginException.class)
    public String handlerUserNotLoginException() {
        return "redirect:/web/logError";
    }


    /**
     * 跳转main页面
     */
    @RequestMapping("/main")
    public String goToMain(HttpServletRequest request) {
        EnterpriseManager enterpriseManager = accountCenterService.getEnterpriseManagerInfo();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        EnterpriseInfo enterprise = accountCenterService.getTopEnterpriseInfo();
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("enterpriseManager", enterpriseManager);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterprise", enterprise);
        return "main";
    }

    /**
     * 跳转LogError页面
     */
    @RequestMapping("/logError")
    public String goToLogError(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "logError";
    }

    /**
     * 跳转text页面
     */
    @RequestMapping("/text")
    public String goTotext(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "text";
    }


    /**
     * 跳转mainleft页面
     */
    @RequestMapping("/mainleft")
    public String goToMainLeft(HttpServletRequest request) {
        List<ResourcePage> currentPages = currentLoginService.getCurrentPages();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        BigDecimal myUCoin = wealthManageService.getUcoinWealth();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        WealthManageResult result = wealthManageService.getManangeResult();
        BigDecimal wealth_undo = result.getUnAllocatedWealth();
        List<WealthManageResult.SonWealth> sonWealthResult = result.getSonWealths();
        request.setAttribute("SonWealthResult", sonWealthResult);
        request.setAttribute("wealth_undo", wealth_undo);
        request.setAttribute("currentPages", currentPages);
        request.setAttribute("myUCoin", myUCoin);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "mainleft";
    }

    /**
     * 跳转mainright页面
     */
    @RequestMapping("/mainright")
    public String goToMainRight(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("enterpriseId", currentLoginService.getCurrentLoginEnterprise().getId());
        request.setAttribute("accountName", currentLoginService.getCurrentLoginEnterprise().getAccountName());
        request.setAttribute("enterpriseName", currentLoginService.getCurrentLoginEnterprise().getEnterpriseName());
        if (currentLoginService.isPrimaryEnterprise()) {
            request.setAttribute("isTop", 1);
        } else {
            request.setAttribute("isTop", 0);
        }
        if (currentLoginService.getCurrentLoginEnterpriseFunc().getEnd()) {
            request.setAttribute("isEnd", 1);
        } else {
            request.setAttribute("isEnd", 0);
        }
        return "mainright";
    }

    /**
     * 跳转reimburse页面
     */
    @RequestMapping("/reimburse")
    public String goToreimburse(HttpServletRequest request, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "status", required = false) String status,@RequestParam(value = "goodsInfoType", required = false) String goodsInfoType) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("status", status);
        request.setAttribute("goodsInfoType", goodsInfoType);
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getGoods(null, null, null, null, new Pageable(1, 100));
        request.setAttribute("totalElements", inventoryGoodsResult.getTotalElements());

        if (id != null && !id.equals("")) {
            InventoryBill inventorybill = inventoryManageService.getBillById(Integer.parseInt(id));
            List<InventoryBillLog> inventoryBillLog = inventoryManageService.getBillLog(Integer.parseInt(id));
            request.setAttribute("inventoryBillLog", inventoryBillLog);
            request.setAttribute("inventorybill", inventorybill);
        }

        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "reimburse";
    }


    /**
     * 跳转storage页面
     */
    @RequestMapping("/storage")
    public String goTostorage(HttpServletRequest request, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "status", required = false) String status) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("status", status);
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getGoods(null, null, null, null, new Pageable(1, 100));
        request.setAttribute("totalElements", inventoryGoodsResult.getTotalElements());
        if (id != null && !id.equals("")) {
            InventoryBill inventorybill = inventoryManageService.getBillById(Integer.parseInt(id));
            List<InventoryBillLog> inventoryBillLog = inventoryManageService.getBillLog(Integer.parseInt(id));
            request.setAttribute("inventoryBillLog", inventoryBillLog);
            request.setAttribute("inventorybill", inventorybill);
        }

        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "storage";
    }


    /**
     * 跳转replenishment页面
     */
    @RequestMapping("/replenishment")
    public String goToreplenishment(HttpServletRequest request, @RequestParam(value = "id", required = false) String id, @RequestParam(value = "status", required = false) String status, @RequestParam(value = "mega", required = false) String mega) {
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getGoods(null, null, null, null, new Pageable(1, 100));
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (id != null && !id.equals("")) {
            Boolean isRead = inventoryManageService.judgePurchaseByBillId(new Long(id));
            request.setAttribute("isRead", isRead);
        }
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("totalElements", inventoryGoodsResult.getTotalElements());
        request.setAttribute("status", status);
        request.setAttribute("mega", mega);
        request.setAttribute("id", id);
        if (id != null && !id.equals("")) {
            InventoryBill inventorybill = inventoryManageService.getBillById(Integer.parseInt(id));
            List<InventoryBillLog> inventoryBillLog = inventoryManageService.getBillLog(Integer.parseInt(id));
            request.setAttribute("inventoryBillLog", inventoryBillLog);
            request.setAttribute("inventorybill", inventorybill);
        }

        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "replenishment";
    }


    /**
     * 跳转replenishmentMega页面
     */
    @RequestMapping("/replenishmentMega")
    public String goToreplenishmentMega(HttpServletRequest request, @RequestParam(value = "list", required = true) String list) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("list", list);
        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "replenishmentMega";
    }

    /**
     * 跳转replenishmentMega页面
     */
    @RequestMapping("/replenishmentMega2")
    public String goToreplenishmentMega2(HttpServletRequest request, @RequestParam(value = "parentId", required = true) Long parentId,String status) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        Boolean isRead = inventoryManageService.judgePurchaseByBillId(parentId);
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("isRead",isRead);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("parentId", parentId);
        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("status",status);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "replenishmentMega2";
    }


    /**
     * 跳转purchaseOrder页面
     */
    @RequestMapping("/purchaseOrder")
    public String goTopurchaseOrder(HttpServletRequest request, @RequestParam(value = "purchaseId", required = false) String purchaseId) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("purchaseId", purchaseId);
        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "purchaseOrder";
    }


    /**
     * 跳转allocation页面
     */
    @RequestMapping("/allocation")
    public String goToallocation(HttpServletRequest request,
                                 @RequestParam(value = "billId", required = false) Long billId,
                                 @RequestParam(value = "id", required = false) Long id,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "isOld", required = false) Boolean isOld) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        EnterpriseInfo enterprise = accountCenterService.getTopEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("status", status);
        request.setAttribute("isOld", isOld);
        request.setAttribute("billId", billId);
        if (id != null) {
            InventoryBill inventorybill = inventoryManageService.getBillById(id);
            List<InventoryBillLog> inventoryBillLog = inventoryManageService.getBillLog(id);
            request.setAttribute("inventorybill", inventorybill);
            request.setAttribute("inventoryBillLog",inventoryBillLog);
        }

        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "allocation";
    }

    /**
     * 跳转inStorage页面
     */
    @RequestMapping("/inStorage")
    public String goToinStorage(HttpServletRequest request,
                                 @RequestParam(value = "billId", required = false) Long billId,
                                 @RequestParam(value = "purchaseId", required = false) Long purchaseId,
                                 @RequestParam(value = "status", required = false) String status,
                                 @RequestParam(value = "isOld", required = false) Boolean isOld) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        EnterpriseInfo enterprise = accountCenterService.getTopEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("status", status);
        request.setAttribute("isOld", isOld);
        request.setAttribute("billId", billId);
        request.setAttribute("purchaseId", purchaseId);
        if (purchaseId != null) {
            InventoryBill inventorybill = inventoryManageService.getBillById(purchaseId);
            List<InventoryBillLog> inventoryBillLog = inventoryManageService.getBillLog(purchaseId);
            request.setAttribute("inventorybill", inventorybill);
            request.setAttribute("inventoryBillLog", inventoryBillLog);
        }

        request.setAttribute("isTop", currentLoginService.isPrimaryEnterprise());
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "inStorage";
    }



    /**
     * 跳转UbaoSend页面
     */
    @RequestMapping("/UbaoSend")
    @SecurityResource(name = "邮豆发放", description = "", priority = 7)
    public String goToUbaoSend(HttpServletRequest request) {
        List<BusinessType> businessType = ucoinGrandService.getBusinessType();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("enterprise", currentLoginService.getCurrentLoginEnterprise());
        request.setAttribute("enterpriseId", currentLoginService.getCurrentLoginEnterpriseId());
        request.setAttribute("businessType", businessType);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("isEnd", isEnd);
        //含业务类型
        //return "UbaoSend";
        return "piliangdaoru";
    }

    /**
     * 跳转sendReduceRecode页面
     */
    @RequestMapping("/sendReduceRecode")
    public String goToSendReduceRecode(HttpServletRequest request) {
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("enterprise", currentLoginService.getCurrentLoginEnterprise());
        request.setAttribute("enterpriseId", currentLoginService.getCurrentLoginEnterpriseId());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("isEnd", isEnd);
        //含业务类型
        //return "UbaoSend";
        return "sendReduceRecode";
    }

    /**
     * 跳转piliangdaoru页面
     */
    @RequestMapping("/piliangdaoru")
    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    public String goToPLDR(HttpServletRequest request) {
        List<BusinessType> businessTypes = ucoinGrandService.getBusinessType();

        request.setAttribute("businessTypes", businessTypes);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "piliangdaoru";
    }

    /**
     * 跳转oldUser页面
     */
    @RequestMapping("/oldUser")
    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    public String goToOldUser(@RequestParam(value = "idCard", required = true) String idCard, HttpServletRequest request) {
        ChinapostCustomer customer = ucoinGrandService.getCustomer(idCard);
        List<BusinessType> businessType = ucoinGrandService.getBusinessType();

        request.setAttribute("enterpriseId", currentLoginService.getCurrentLoginEnterpriseId());
        request.setAttribute("businessType", businessType);
        if (customer.getTags().size() > 0) {
            request.setAttribute("ChinapostTag", customer.getTags());
        }
        request.setAttribute("idCardNo", customer.getIdcardNo());
        request.setAttribute("FullName", customer.getFullname());
        request.setAttribute("PhoneNo", customer.getContactPhone());
        request.setAttribute("UCoin", customer.getTotalUcoin());
        request.setAttribute("ContactAddr", customer.getContactAddr());
        request.setAttribute("EnterpriseUcoin", customer.getEnterpriseUcoin());
        request.setAttribute("ManagerNo", customer.getManagerNo());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("imgUrl", customer.getImgUrl());
        request.setAttribute("customer", customer);
        return "oldUser";
    }

    /**
     * 跳转newUser页面
     */
    @RequestMapping("/newUser")
    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    public String goToNewUser(@RequestParam(value = "idCard", required = true) String idCard, HttpServletRequest request) {
        request.setAttribute("idCard", idCard);
        List<BusinessType> businessType = ucoinGrandService.getBusinessType();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();

        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterpriseId", currentLoginService.getCurrentLoginEnterpriseId());
        request.setAttribute("businessType", businessType);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "newUser";
    }

    /**
     * 跳转list页面
     */
    @RequestMapping("/list")
    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    public String goToList(
//        @RequestParam(value = "typeId", required = false) long typeId,
            HttpServletRequest request,
            @RequestParam(value = "key", required = true) String key,
            @RequestParam(value = "business_type", required = true) String business_type) {
        request.setAttribute("key", key);

        request.setAttribute("business_type", business_type);
//        request.setAttribute("typeId", typeId);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "list";
    }

    /**
     * 跳转UCoinManager页面
     */
    @RequestMapping("/UCoinManager")
    @SecurityResource(name = "财富管理", description = "", priority = 6)
    public String goToUCoinManager(HttpServletRequest request) {
        WealthManageResult result = wealthManageService.getManangeResult();
        BigDecimal allUcoin = result.getUnAllocatedWealth();
        Boolean flag = currentLoginService.isPrimaryEnterprise();
        BigDecimal wealth_total = result.getTotalWealth();
        BigDecimal wealth_do = result.getAllocatedWealth();
        BigDecimal wealth_undo = result.getUnAllocatedWealth();

        List<WealthManageResult.SonWealth> sonWealthResult = result.getSonWealths();
        Long enterpriseId = result.getEnterpriseId();
        String enterpriseName = result.getEnterpriseName();
        BigDecimal myUCoin = wealthManageService.getUcoinWealth();
        EnterpriseManager enterpriseManager = accountCenterService.getEnterpriseManagerInfo();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();

        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("isEnd", isEnd);


        request.setAttribute("enterpriseManager", enterpriseManager);
        request.setAttribute("allUcoin", allUcoin);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("wealth_total", wealth_total);
        if (flag) {
            request.setAttribute("flag", "true");
        } else {
            request.setAttribute("flag", "false");
        }
        request.setAttribute("wealth_do", wealth_do);
        request.setAttribute("wealth_undo", wealth_undo);
        request.setAttribute("SonWealthResult", sonWealthResult);
        request.setAttribute("SonTotalWealth", result.getSonsTotalWealth());
        request.setAttribute("enterpriseId", enterpriseId);
        request.setAttribute("enterpriseName", enterpriseName);
        request.setAttribute("myUCoin", myUCoin);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "UCoinManager";
    }

    /**
     * 跳转UCoinManager页面
     */
    @RequestMapping("/hiemanager")
    @SecurityResource(name = "层级管理", description = "", priority = 2)
    public String goToHieManager(HttpServletRequest request) {
        List<EnterpriseInfo> enterprises = hierarchyManageService.getSonEnterpriseInfo();
        request.setAttribute("enterprises", enterprises);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "hieManager";
    }

    /**
     * 跳转inventoryInquiry页面
     */
    @RequestMapping("/inventoryInquiry")
    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    public String goToinventoryInquiry(HttpServletRequest request) {
        List<EnterpriseInfo> enterprises = hierarchyManageService.getSonEnterpriseInfo();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterprises", enterprises);

        //enterpriseId
        request.setAttribute("enterprise", currentLoginService.getCurrentLoginEnterprise());
        boolean flag = currentLoginService.isPrimaryEnterprise();
        if (flag) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        request.setAttribute("isEnd", currentLoginService.getCurrentLoginEnterpriseFunc().getEnd());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "inventoryInquiry";
    }

    /**
     * 跳转login页面
     */
    @RequestMapping("/login.htm")
    public String goToLogin(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "login";
    }

    /**
     * 跳转businessType页面
     */
    @RequestMapping("/businessType")
    @SecurityResource(name = "系统设置", description = "", priority = 14)
    public String goTobusinessType(@RequestParam(value = "action", required = false) String action, HttpServletRequest request) {
        List<BusinessType> allType = systemManageService.getAll();

        if (action == null) {
            request.setAttribute("action", "none");
        } else {
            request.setAttribute("action", action);
        }
        request.setAttribute("allType", allType);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "businessType";
    }


    /**
     * 跳转businessChangeHis页面
     */
    @RequestMapping("/businessChangeHis")
    @SecurityResource(parent = "/web/businessType", primary = false)
    public String goTobusinessChangeHis(@RequestParam(value = "action", required = false) String action, HttpServletRequest request) {
        List<BusinessType> businessTypes = ucoinGrandService.getBusinessType();

        request.setAttribute("businessTypes", businessTypes);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "businessChangeHis";
    }


    /**
     * 跳转companyAccount页面
     */
    @RequestMapping("/companyAccount")
    @SecurityResource(parent = "/web/Account", primary = false)
    public String goToCompanyAccount(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getTopEnterpriseInfo();
        Boolean flag = currentLoginService.isPrimaryEnterprise();

        if (flag) {
            request.setAttribute("flag", "true");
        } else {
            request.setAttribute("flag", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "companyAccount";
    }

    /**
     * 跳转formCenter页面
     */
    @RequestMapping("/formCenter")
    @SecurityResource(name = "报表中心", description = "", priority = 14)
    public String goToformCenter(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        List<ResourcePage> currentPages = currentLoginService.getFormCurrentPages();
        request.setAttribute("currentPages",currentPages);
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "formCenter";
    }

    /**
     * 跳转memberUbaoSendForm页面
     */
    @RequestMapping("/memberUbaoSendForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTomemberUbaoSendForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberUbaoSendForm";
    }

    /**
     * 跳转memberUbaoReduceForm页面
     */
    @RequestMapping("/memberUbaoReduceForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTomemberUbaoReduceForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberUbaoReduceForm";
    }

    /**
     * 跳转ubaoSendForm页面
     */
    @RequestMapping("/ubaoSendForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToubaoSendForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        List<BusinessType> businessTypes = ucoinGrandService.getBusinessType();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("businessTypes", businessTypes);
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "ubaoSendForm";
    }

    /**
     * 跳转BaseDataform页面
     */
    @RequestMapping("/BaseDataform")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToBaseDataform(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "BaseDataform";
    }

    /**
     * 跳转memberBaseDataform页面
     */
    @RequestMapping("/memberBaseDataform")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTomemberBaseDataform(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberBaseDataform";
    }

    /**
     * 跳转wealthDetailForm页面
     */
    @RequestMapping("/wealthDetailForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTowealthDetailForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "wealthDetailForm";
    }


    /**
     * 跳转logManager页面
     */
    @RequestMapping("/logManager")
    public String goTologManager(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "logManager";
    }


    /**
     * 跳转inventoryDetailForm页面
     */
    @RequestMapping("/inventoryDetailForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToinventoryDetailForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "inventoryDetailForm";
    }

    /**
     * 跳转inventoryChangeDetailForm页面
     */
    @RequestMapping("/inventoryChangeDetailForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToinventoryChangeDetailForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "inventoryChangeDetailForm";
    }

    /**
     * 跳转memberCheck页面
     */
    @RequestMapping("/memberCheck")
    public String goTomemberCheck(HttpServletRequest request) {
        EnterpriseManager enterpriseManager = accountCenterService.getEnterpriseManagerInfo();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        EnterpriseInfo enterprise = accountCenterService.getTopEnterpriseInfo();
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("enterpriseManager", enterpriseManager);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("enterprise", enterprise);
        return "memberCheck";
    }

    /**
     * 跳转setLog页面
     */
    @RequestMapping("/setLog")
    public String goTosetLog(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "setLog";
    }

    /**
     * 跳转supplyDetailForm页面
     */
    @RequestMapping("/supplyDetailForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTosupplyDetailForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "supplyDetailForm";
    }

    /**
     * 跳转supplyDetailForm页面
     */
    @RequestMapping("/supplyDetailFormThrid")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToSupplyDetailFormThrid(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "detailFormThrid";
    }

    /**
     * 跳转supplyCountForm页面
     */
    @RequestMapping("/supplyCountForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTosupplyCountForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "supplyCountForm";
    }

    /**
     * 跳转checkForm页面
     */
    @RequestMapping("/checkForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goToCheckForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "checkForm";
    }

    /**
     * 跳转deliveryDetailForm页面
     */
    @RequestMapping("/deliveryDetailForm")
    @SecurityResource(parent = "/web/formCenter", primary = false)
    public String goTodeliveryDetailForm(HttpServletRequest request) {
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        Boolean isTop = currentLoginService.isPrimaryEnterprise();
        if (isTop) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprise", enterprise);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "deliveryDetailForm";
    }

    /**
     * 跳转Account页面
     */
    @RequestMapping("/Account")
    @SecurityResource(name = "账号中心", description = "", priority = 1)
    public String goToAccount(HttpServletRequest request) {
        EnterpriseManager enterpriseManager = accountCenterService.getEnterpriseManagerInfo();
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        EnterpriseFunction enterprisefunction = currentLoginService.getCurrentLoginEnterpriseFunc();
        boolean flag = currentLoginService.isPrimaryEnterprise();
        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("hasPaykey", accountCenterService.hasPayKey());
        if (flag) {
            request.setAttribute("isEmp", "true");
        } else {
            request.setAttribute("isEmp", "false");
        }
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("enterprisefunction", enterprisefunction);
        request.setAttribute("enterpriseManager", enterpriseManager);
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "Account";
    }

    /**
     * 跳转Ubao_Search页面
     */
    @RequestMapping("/Ubao_Search")
    public String goToUbaoSearch(@RequestParam(value = "idCard", required = true) String idCard, HttpServletRequest request) {
        Long userId = ucoinQueryService.getCustomerId(idCard);
        ChinapostCustomer customer = ucoinQueryService.getCustomer(idCard);
        request.setAttribute("idCard", idCard);
        request.setAttribute("userId", userId);
        request.setAttribute("user", customer);
        request.setAttribute("imgUrl", customer.getImgUrl());
        request.setAttribute("customer", customer);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "Ubao_Search";
    }


    /**
     * 跳转memberCheckDetail页面
     */
    @RequestMapping("/memberCheckDetail")
    public String goTomemberCheckDetail(@RequestParam(value = "idCard", required = true) String idCard, HttpServletRequest request) {
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("idCard", idCard);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberCheckDetail";
    }



    /**
     * 跳转Ubao_List页面
     */
    @RequestMapping("/UbaoList")
    public String goToUbaoList(@RequestParam(value = "userId", required = true) Long userId, HttpServletRequest request) {
        Page<CustomerUcoinHistory> page = ucoinQueryService.getCustomerUcoinHistories(userId, new Pageable(1, 5));
        List<CustomerUcoinHistory> uHistory = page.getContent();
        request.setAttribute("userId", userId);
        request.setAttribute("uHistory", uHistory);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "UbaoList";
    }

    /**
     * 跳转UbaoGet页面
     */
    @RequestMapping("/UserGet")
    @SecurityResource(name = "用户提货", description = "", priority = 8)
    public String goToUserGet(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "UserGet";
    }

    /**
     * 跳转voucherManager页面
     */
    @RequestMapping("/voucherManager")
    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    public String goTovoucherManager(HttpServletRequest request) {
        boolean flag = currentLoginService.isPrimaryEnterprise();
        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("isTop", flag);
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("billType", BillType.values());
        request.setAttribute("billStatus", BillStatus.values());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "voucherManager";
    }

    /**
     * 跳转megeReplishment页面
     */
    @RequestMapping("/megeReplishment")
    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    public String goTomegeReplishment(HttpServletRequest request) {
        boolean flag = currentLoginService.isPrimaryEnterprise();
        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        request.setAttribute("isTop", flag);
        request.setAttribute("isEnd", isEnd);
        request.setAttribute("billType", BillType.values());
        request.setAttribute("billStatus", BillStatus.values());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "megeReplishment";
    }


    /**
     * 跳转UserGetList页面
     */
    @RequestMapping(value = "/UserGetList")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToUserGetList(HttpServletRequest request, @RequestParam(value = "deliveryCode", required = true) String deliveryCode) {
        Order order = valetOrderService.getOrderByDeliveryCode(deliveryCode);
        request.setAttribute("order", order);
        //判断是否为本网点
        boolean flag = false;
        long orderEId = order.getEnterpriseId();
        long currentEId = currentLoginService.getCurrentLoginEnterpriseId();
        if (orderEId == currentEId) {
            System.out.println(1);
            flag = true;
        }
        request.setAttribute("flag", flag);
        System.out.println(flag);
        //获取用户信息
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(order.getCustomerId());
        request.setAttribute("customer", customer);

        EnterpriseInfo enterpriseInfo = currentLoginService.getCurrentLoginEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("imgUrl", customer.getImgUrl());
        return "UserGetList";
    }

    /**
     * 跳转UserGetListDetail页面
     */
    @RequestMapping(value = "/UserGetListDetail")
    @SecurityResource(parent = "/web/UserGetList", primary = false)
    public String goToUserGetListDetail(HttpServletRequest request, @RequestParam(value = "code", required = true) Long deliveryCode) {
        Order order = valetOrderService.getOrderByCode(deliveryCode);
        request.setAttribute("order", order);
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(order.getCustomerId());
        request.setAttribute("customer", customer);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("imgUrl", customer.getImgUrl());
        return "UserGetListDetail";
    }

    /**
     * 跳转UserList页面
     */
    @RequestMapping("/UserList")
    public String goToUserList(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "UserList";
    }

    /**
     * 跳转shopListCart页面
     */
    @RequestMapping("/shopListCart")
    public String goToshopListCart(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "shopListCart";
    }


    /**
     * 跳转BillManager页面
     */
    @RequestMapping("/BillManager")
    @SecurityResource(name = "账单管理", description = "", priority = 11)
    public String goToBillManager(HttpServletRequest request, @RequestParam(value = "action", required = false) String tabNumber) {

        Page<EnterpriseAllocation> total_elements_up = ucoinBillManageService.getParentAllocations(null, null, null, new Pageable(1, 100));
        Page<EnterpriseBatchAllocation> total_elements_allocation = ucoinBillManageService.getMyAllocations(null, null, null, new Pageable(1, 100));
//        Page<EnterpriseBatchGrand> total_elements_send = ucoinBillManageService.getGrands(null, null, null, new Pageable(1, 100));
//        Page<CustomerUcoinHistory> total_elements_reduce = ucoinBillManageService.getDeducts(null, null, null, new Pageable(1, 100));
        Page<EnterpriseBatchGrand> total_elements_send = newGrandService.getMyGrand(null, null, null, new Pageable(1, 100));
        Page<EnterpriseBatchGrand> total_elements_reduce = newGrandService.getMyDecut(null, null, null, new Pageable(1, 100));
        Boolean isTop = currentLoginService.isPrimaryEnterprise();
        String enterpriseName = currentLoginService.getCurrentLoginEnterprise().getEnterpriseName();
        if (isTop) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        if (tabNumber == null) {
            tabNumber = "0";
        }
        request.setAttribute("tabNumber", tabNumber);
        request.setAttribute("enterpriseName", enterpriseName);
        request.setAttribute("total_elements_up", total_elements_up.getTotalElements());
        request.setAttribute("total_elements_allocation", total_elements_allocation.getTotalElements());
        request.setAttribute("total_elements_send", total_elements_send.getTotalElements());
        request.setAttribute("total_elements_reduce", total_elements_reduce.getTotalElements());
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "BillManager";
    }

    /**
     * 跳转InverntoryManager页面
     */
    @RequestMapping("/InventoryManager")
    @SecurityResource(name = "库存管理", description = "", priority = 12)
    public String goToInventoryManager(HttpServletRequest request) {
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getInventoryGoodsResult(null,null, null, null, null, new Pageable(1, 100));
        List<GoodsManagerResult> brandResult = goodsManageService.selectBrand();
        List<GoodsManagerResult> typeResult = goodsManageService.selectType();
        List<GoodsManagerResult> thirdNameResult = goodsManageService.selectThirdName(null);
        int warningNo = inventoryGoodsService.getWarning();
        boolean flag = currentLoginService.isPrimaryEnterprise();

        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();

        request.setAttribute("warningNo", warningNo);
        request.setAttribute("total_elements", inventoryGoodsResult.getTotalElements());
        request.setAttribute("brandResult", brandResult);
        request.setAttribute("typeResult", typeResult);
        request.setAttribute("thirdNameResult", thirdNameResult);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("enterprise", currentLoginService.getCurrentLoginEnterprise());
        if (flag) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }


        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);

        request.setAttribute("isEnd", isEnd);
        if (!isEnd && !flag) {
            return "inventoryInquiry";
        } else {
            return "InventoryManager";
        }
    }

    /**
     * 跳转addInverntor页面
     */
    @RequestMapping("/AddInventory")
    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    public String goToAddInventor(HttpServletRequest request) {
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getGoods(null, null, null, null, new Pageable(1, 100));
        List<GoodsManagerResult> brandResult = goodsManageService.selectBrand();
        List<GoodsManagerResult> thirdNameResult = goodsManageService.selectThirdName(null);


        request.setAttribute("totalElements", inventoryGoodsResult.getTotalElements());
        request.setAttribute("brandResult", brandResult);
        request.setAttribute("thirdNameResult", thirdNameResult);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "AddInventory";
    }

    /**
     * 跳转addgoods页面
     */
    @RequestMapping("/Addgoods")
    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    public String goToAddgoods(HttpServletRequest request) {
        Page<InventoryGoodsResult> inventoryGoodsResult = inventoryGoodsService.getGoods(null, null, null, null, new Pageable(1, 100));
        List<GoodsManagerResult> brandResult = goodsManageService.selectBrand();
        List<GoodsManagerResult> thirdNameResult = goodsManageService.selectThirdName(null);


        request.setAttribute("totalElements", inventoryGoodsResult.getTotalElements());
        request.setAttribute("brandResult", brandResult);
        request.setAttribute("thirdNameResult", thirdNameResult);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "Addgoods";
    }

    /**
     * 跳转roleManager页面
     */
    @RequestMapping("/RoleManager")
    @SecurityResource(name = "角色管理", description = "", priority = 3)
    public String goToRoleManager(HttpServletRequest request) {
        List<AuthWithMAmountResult> authWithMAmountResults = roleManageService.selectAuthResultByEnterpriseId();
        List<ResourcePage> resourcePages = currentLoginService.getCurrentPages();
        List<ResourcePage> topPages = currentLoginService.getCurrentPages();
        Map<String, Map<String,List<ResourcePage>>> pagesMap = new HashMap<>();
        for (ResourcePage resourcePage : topPages) {
            String parentPage = resourcePage.getId() + "," + resourcePage.getDesignation();
            pagesMap.put(parentPage, null);
            //2016.7.26    权限逻辑修改暂时注释
            if(resourcePage.getDesignation().equals("报表中心")) {
                Map<String,List<ResourcePage>> map = new HashMap<>();
                for(ResourcePage item : roleManageService.getSonPages(resourcePage.getId())){
                   map.put(item.getId()+","+item.getDesignation(),roleManageService.getSonPages(item.getId()));
                }
                pagesMap.put(parentPage, map);
            }
        }
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        request.setAttribute("pagesMap", pagesMap);
        request.setAttribute("resourcePages", resourcePages);
        request.setAttribute("authWithMAmountResults", authWithMAmountResults);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "RoleManager";
    }


    /**
     * 跳转employeeManager页面
     */
    @RequestMapping("/employeeManager")
    @SecurityResource(parent = "/web/RoleManager", primary = false)
    public String goToemployeeManager(HttpServletRequest request) {
        List<ManagerWithAuthNameResult> managerWithAuthNameResults = roleManageService.getMyManagerInfo();
        List<AuthWithMAmountResult> authWithMAmountResults = roleManageService.selectAuthResultByEnterpriseId();

        request.setAttribute("managerAuthorityResults", managerWithAuthNameResults);
        request.setAttribute("authWithMAmountResults", authWithMAmountResults);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "employeeManager";
    }

    /**
     * 跳转goodsManager页面
     */
    @RequestMapping("/goodsManager")
    public String goToGoodsManager(HttpServletRequest request) {
        //List<GoodsManagerResult> brandResults = goodsManageService.selectBrand();
        //List<GoodsManagerResult> typeResults = goodsManageService.selectType();
        //List<GoodsManagerResult> thirdNameResults = goodsManageService.getThirdName();

//        request.setAttribute("brandResults",brandResults);
//        request.setAttribute("typeResults",typeResults);
//        request.setAttribute("thirdNameResults",thirdNameResults);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "goodsManager";
    }

    /**
     * 跳转itemList页面
     */
    @RequestMapping("/itemList")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToitemList(HttpServletRequest request) {
        boolean flag = currentLoginService.isPrimaryEnterprise();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        if (flag) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "itemList";
    }

    /**
     * 跳转olderManager页面
     */
    @RequestMapping("/olderManager")
    public String goToolderManager(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "olderManager";
    }


    /**
     * 跳转itemListDetail页面
     */
    @RequestMapping("/itemListDetail")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToitemListDetail(@RequestParam(value = "id", required = true) Long id,@RequestParam(value = "topShow", required = true) Boolean topShow, HttpServletRequest request) {
        GoodsInfo goodInfo = valetOrderService.getValetGoodsByGoodsInfoId(id);
        request.setAttribute("goodInfo", goodInfo);
        request.setAttribute("id", id);
        request.setAttribute("topShow", topShow);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "itemListDetail";
    }

    /**
     * 跳转updateSelfItems页面
     */
    @RequestMapping("/updateSelfItems")
    @SecurityResource(parent = "/web/itemManager", primary = false)
    public String goToupdateSelfItems(@RequestParam(value = "id", required = true) Long id,HttpServletRequest request) {
        GoodsInfo goodsInfo = goodsManageService.getGoodsInfoById(id);
        request.setAttribute("goodInfo", goodsInfo);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("id", id);
        return "updateSelfItems";
    }

    /**
     * 跳转selfPickItmUpload页面
     */
    @RequestMapping("/selfPickItmUpload")
    @SecurityResource(parent = "/web/itemManager", primary = false)
    public String goToselfPickItmUpload(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "selfPickItmUpload";
    }



    /**
     * 跳转UCoinCount页面
     */
    @RequestMapping("/UCoinCount")
    public String goToUCoinCount(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "UCoinCount";
    }

    /**
     * 跳转Outlets页面
     */
    @RequestMapping("/Outlets")
    @SecurityResource(name = "网点请货", description = "", priority = 13)
    public String goToOutlets(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "Outlets";
    }


    /**
     * 跳转accountManager页面
     */
    @RequestMapping("/accountManager")
    @SecurityResource(name = "订单管理", description = "", priority = 9)
    public String goToaccountManager(HttpServletRequest request) {

        OrderStatus[] orderStatuses = orderManageService.getOrderStatuses();

        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();

        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("credential", CredentialType.values());
        request.setAttribute("backReason", CreditOrderReason.values());

        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", 1);
        } else {
            request.setAttribute("isEnd", 0);
        }
        Boolean flag = currentLoginService.isPrimaryEnterprise();
        request.setAttribute("isTop", flag);


        request.setAttribute("orderStatus", orderStatuses);
        request.setAttribute("VERSION", Constants.JS_VERSION);

        if (flag) {
            return "accountManager";
        } else {
            return "accountManagerUntop";
        }
    }

    /**
     * 根据是否是顶级账号，跳转退单页面
     */
    @RequestMapping("/accountReturn")
    @SecurityResource(parent = "/web/accountManager", primary = false)
    public String goToaccountReturn(HttpServletRequest request) {
        Boolean flag = currentLoginService.isPrimaryEnterprise();
        CreditOrderStatus[] status = orderManageService.getStatuses();

        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);

        request.setAttribute("status", status);
        request.setAttribute("VERSION", Constants.JS_VERSION);

        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", 1);
        } else {
            request.setAttribute("isEnd", 0);
        }

        if (flag) {
            request.setAttribute("isTop", true);
            return "accountReturn";
        } else {
            request.setAttribute("isTop", false);
            return "accountReturnUntop";
        }
    }

    /**
     * 跳转Requisition页面
     */
    @RequestMapping("/Requisition")
    @SecurityResource(name = "请款单据", description = "", priority = 10)
    public String goToRequisition(HttpServletRequest request) {
        Page<EnterpriseRequisition> enterpriseRequisitionPage_myRequest = requisitionManageService.getMyRequisition(null, null, null, null, new Pageable(1, 100));
        request.setAttribute("total_elements_myRequest", enterpriseRequisitionPage_myRequest.getTotalElements());

        Page<EnterpriseRequisition> enterpriseRequisitionPage_myRequestTodo = requisitionManageService.getNotHandleRequisition(null, null, null, null, new Pageable(1, 100));
        request.setAttribute("total_elements_myRequest_todo", enterpriseRequisitionPage_myRequestTodo.getTotalElements());

        Page<EnterpriseRequisition> enterpriseRequisitionPage_myRequestDone = requisitionManageService.getHandledRequisition(null, null, null, null, new Pageable(1, 100));
        request.setAttribute("total_elements_myRequest_done", enterpriseRequisitionPage_myRequestDone.getTotalElements());

        Boolean flag = currentLoginService.isPrimaryEnterprise();
        if (flag) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }

        Map<String, String> statusMap = new HashMap<>();

        for (RequisitionStatus status : RequisitionStatus.values()) {
            statusMap.put(status.name(), status.getName());
        }

        request.setAttribute("statusMap", statusMap);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "requisition";
    }

    /**
     * 跳转memberManager页面
     */
    @RequestMapping("/memberManager")
    @SecurityResource(name = "会员管理", description = "", priority = 4)
    public String goToMemberManager(HttpServletRequest request) {
        Page<ChinapostCustomer> page = customerManageService.getCustomers(null, null, null, null, null, null, null, null, new Pageable(1, 100));
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);

        request.setAttribute("total_elements_memberManager", page.getTotalElements());
        request.setAttribute("total_pages_memberManager", page.getTotalPages());

        boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", 1);
        } else {
            request.setAttribute("isEnd", 0);
        }
        Boolean flag = currentLoginService.isPrimaryEnterprise();
        if (flag) {
            request.setAttribute("isTop", 1);
        } else {
            request.setAttribute("isTop", 0);
        }


        List<ChinapostTag> tagList = chinapostTagService.getTags();
        request.setAttribute("tagList", tagList);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberManager";
    }

    /**
     * 跳转itemManager页面
     */
    @RequestMapping("/itemManager")
    @SecurityResource(name = "商品管理", description = "", priority = 5)
    public String goToitemManager(HttpServletRequest request) {
        List<GoodsManagerResult> brandResult = goodsManageService.selectBrand();
        List<GoodsManagerResult> typeResult = goodsManageService.selectType();
        List<GoodsManagerResult> thirdNameResult = goodsManageService.selectThirdName(null);
        Boolean isTop = currentLoginService.isPrimaryEnterprise();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        EnterpriseInfo enterprise = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterprise", enterprise);
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        if (isTop) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        request.setAttribute("brandResult", brandResult);
        request.setAttribute("typeResult", typeResult);
        request.setAttribute("thirdNameResult", thirdNameResult);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "itemManager";
    }

    /**
     * 跳转memberSpending页面
     */
    @RequestMapping("/memberSpending")
    public String goToMemberSpending(HttpServletRequest request) {
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "memberSpending";
    }

    /**
     * 跳转order_sure页面
     */
    @RequestMapping("/OrderSure")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToOrderSure(@RequestParam(value = "idsJson", required = true) String idsJson, HttpServletRequest request) {
        Map<Long, Integer> skus = Constants.SIMPLE_PARSER.parseJSON(idsJson, new TypeToken<Map<Long, Integer>>() {
        });
        Boolean hasValet = true;
        List<Map> infos = new ArrayList<>();
        for (long id : skus.keySet()) {
            GoodsInfo goodInfo = valetOrderService.getGoodsInfo(id);
            Map<String, Object> info = new HashMap<>();
            if (!valetOrderService.hasInventory(id)) {
                hasValet = false;
            }
            info.put("goodsInfoId", goodInfo.getGoodsInfoId());
            info.put("goodsInfoImgId", goodInfo.getGoodsInfoImgId());
            info.put("goodsInfoName", goodInfo.getGoodsInfoName());
            info.put("goodsInfoPreferPrice", goodInfo.getGoodsInfoPreferPrice());
            info.put("getAvailable", goodInfo.getAvailable());
            info.put("count", skus.get(id));
            infos.add(info);
        }
        EnterpriseFunction enterprisefunction = currentLoginService.getCurrentLoginEnterpriseFunc();
        request.setAttribute("enterprisefunction", enterprisefunction);
        EnterpriseInfo enterpriseInfo = accountCenterService.getEnterpriseInfo();
        request.setAttribute("enterpriseInfo", enterpriseInfo);
        request.setAttribute("goodsInfos", infos);
        request.setAttribute("hasValet", hasValet);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "order_sure";
    }

    /**
     * 跳转payConfirm页面
     */
    @RequestMapping("/payConfirm")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToConfirm(@RequestParam(value = "idCard", required = true) String idCard, @RequestParam(value = "customerId", required = true) Long customerId, @RequestParam(value = "sumNum", required = true) String sumNum, @RequestParam(value = "orderPrice", required = true) String orderPrice, HttpServletRequest request) {
        boolean judge = valetOrderService.hasPermit();
        String contactPhone = chinapostCustomerService.getCustomer(idCard).getPhoneNo();
        if (judge) {
            request.setAttribute("judge", "block");
        } else {
            request.setAttribute("judge", "none");
        }
        BigDecimal orderP = new BigDecimal(orderPrice);
        orderP = orderP.setScale(2, BigDecimal.ROUND_HALF_UP);
        EnterpriseFunction enterprisefunction = currentLoginService.getCurrentLoginEnterpriseFunc();
        request.setAttribute("enterprisefunction", enterprisefunction);
        request.setAttribute("orderPrice", orderP);
        request.setAttribute("idCard", idCard);
        request.setAttribute("customerId", customerId);
        request.setAttribute("sumNum", sumNum);
        request.setAttribute("VERSION", Constants.JS_VERSION);
        request.setAttribute("contactPhone", contactPhone);
        return "payConfirm";
    }

    /**
     * 跳转payConfirm页面
     */
    @RequestMapping("/maturityWarning")
    @SecurityResource(parent = "/web/UserGet", primary = false)
    public String goToConfirm( HttpServletRequest request) {
        Boolean isTop = currentLoginService.isPrimaryEnterprise();
        Boolean isEnd = currentLoginService.getCurrentLoginEnterpriseFunc().getEnd();
        if (isEnd) {
            request.setAttribute("isEnd", "true");
        } else {
            request.setAttribute("isEnd", "false");
        }
        if (isTop) {
            request.setAttribute("isTop", "true");
        } else {
            request.setAttribute("isTop", "false");
        }
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "maturityWarning";
    }
}

