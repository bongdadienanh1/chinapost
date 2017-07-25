package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.NewGrandService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.GrandException;
import com.ylife.exception.UserOperationException;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamValue;
import com.ylife.utils.Assert;
import com.ylife.utils.WebUtil;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/11.
 * U宝发放控制器
 */
@Controller
@SecurityResource(parent = "/web/UbaoSend", primary = false)
@RequestMapping(value = "/web/api/ucoingrand", produces = "application/json;charset=utf-8")
public class UcoinGrandAPIController {

    private static final String EXCEL_LIST_KEY = "_excel_list_key";
    private static final String EXCEL_LIST_TIME_KEY = "_excel_list_time_key";

    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private NewGrandService newGrandService;

    /**
     * 是否为新用户。
     *
     * @param idCard 身份证号
     */
    @RequestMapping("/isNew")
    @ResponseBody
    public String isNew(@RequestParam("idCard") String idCard) {
        Assert.hasLength(idCard);
        boolean isNew = ucoinGrandService.isNewCustomer(idCard);
        return isNew + "";
    }


    @RequestMapping(value = "/newParseFile", method = RequestMethod.POST)
    @ResponseBody
    public String parseFile(@RequestParam("file") MultipartFile file,
                            @RequestParam("type") Boolean type) throws IOException, InvalidFormatException {
        Map<String, Object> infoList = newGrandService.parseFile(file, type);
        long time = System.currentTimeMillis();
        HttpSession session = WebUtil.getCurrentSession();
        session.setAttribute(EXCEL_LIST_KEY, infoList);
        session.setAttribute(EXCEL_LIST_TIME_KEY, time);
        return new JsonResponseBean(time).toJson();
    }

    @RequestMapping(value = "/getExcelData", method = RequestMethod.POST)
    @ResponseBody
    public String getExcelData(@RequestParam("key") long key, @RequestParam("page") int page, @RequestParam("size") int size) {
        HttpSession session = WebUtil.getCurrentSession();
        Long key2 = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (key2 == null || key2 != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<String, Object> infoList = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
        Map<String, Object> infoList2 = new HashMap<>();
        infoList2.put("error", infoList.get("error"));
        infoList2.put("head", infoList.get("head"));
        List<Object> objects = (List<Object>) infoList.get("content");
        int start = page * size - size;
        int end = page * size;
        if (end >= objects.size()) {
            end = objects.size() - 1;
        }

        List<Object> data = new ArrayList<>();
        for (int i = start + 1; i < end + 1; i++) {
            Object obj = objects.get(i);
            if (obj != null) {
                data.add(obj);
            }
        }
        Page<Object> page1 = new PageImpl<>(new Pageable(page, size), objects.size() - 1, data);
        infoList2.put("content", page1);
        return new JsonResponseBean(infoList2).toJson();
    }


     @RequestMapping(value = "/checkBalance",method = RequestMethod.POST)
     @ResponseBody
     public String CheckBalance(@RequestParam("key")long key,
                                @RequestParam("type")Boolean typ){

         HttpSession session=WebUtil.getCurrentSession();
         Long key2 = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
         if (key2 == null || key2 != key) {
             session.removeAttribute(EXCEL_LIST_TIME_KEY);
             session.removeAttribute(EXCEL_LIST_KEY);
             throw new UserOperationException("key值错误，请重新导入Excel.");
         }
         Map<String, Object> excelAndErrorInfo = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
         try {
             newGrandService.checkBalance(excelAndErrorInfo,typ);
         } catch (GrandException e) {
             throw new GrandException(e.getExObj());
         }
         return new JsonResponseBean().toJson();
     }



    @RequestMapping(value = "/newBatchGrand", method = RequestMethod.POST)
    @ResponseBody
    public String newBatchGrand(@RequestParam("key") long key,
                                @RequestParam("paykey") String paykey,
                                @RequestParam("type") Boolean type) {
        HttpSession session = WebUtil.getCurrentSession();
        Long key2 = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (key2 == null || key2 != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<String, Object> excelAndErrorInfo = (Map<String, Object>) session.getAttribute(EXCEL_LIST_KEY);
        try {
            newGrandService.batchGrand(excelAndErrorInfo, paykey, type);
        } catch (GrandException e) {
            throw new GrandException(e.getExObj());
        }
        session.removeAttribute(EXCEL_LIST_TIME_KEY);
        session.removeAttribute(EXCEL_LIST_KEY);
        return new JsonResponseBean().toJson();
    }


    /**
     * 解析前端上传的Excel返回前端JSON。
     *
     * @param file 上传的文件
     */
    @RequestMapping(value = "/parseExcel", method = RequestMethod.POST)
    @ResponseBody
    public String parseFile(@RequestParam("file") MultipartFile file,
                            @RequestParam("typeId") int typeId) throws IOException, InvalidFormatException {
        Map<Integer, List<List<String>>> infoList = ucoinGrandService.parseFile(file, typeId);
        long time = System.currentTimeMillis();
        HttpSession session = WebUtil.getCurrentSession();
        session.setAttribute(EXCEL_LIST_KEY, infoList);
        session.setAttribute(EXCEL_LIST_TIME_KEY, time);
        Map<String, Object> map = new HashMap<>();
        map.put("key", time);
        map.put("excel", infoList);
        return new JsonResponseBean(map).toJson();
    }

    /**
     * 获取用户信息。
     */
    @RequestMapping(value = "/customerInfo", method = RequestMethod.GET)
    @ResponseBody
    public String getCustomerInfo(@RequestParam("idCard") String idCard) {
        Assert.hasLength(idCard);
        ChinapostCustomer customer = ucoinGrandService.getCustomer(idCard);
        return new JsonResponseBean(customer).skip("id", "idcardNo", "phoneNo", "fullname", "contactAddr", "ucoin", "managerNo").toJson();
    }


    /**
     * 单个发放
     *
     * @param typeId     业务类型ID
     * @param paykey     支付密码
     * @param valuesJson 参数json
     */
    @RequestMapping(value = "/singleGrand", method = RequestMethod.POST)
    @ResponseBody
    public String singleGrand(@RequestParam("typeId") int typeId,
                              @RequestParam("paykey") String paykey,
                              @RequestParam("valuesJson") String valuesJson) {
        Map<String, String> valueMap = Constants.SIMPLE_PARSER.parseJSON(valuesJson, new TypeToken<Map<String, String>>() {
        });
        ucoinGrandService.singleGrand(typeId, paykey, null, valueMap);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    @RequestMapping(value = "/ucoinCompute", method = RequestMethod.POST)
    @ResponseBody
    public String ucoinCompute(@RequestParam("typeId") int typeId,
                               @RequestParam("valuesJson") String valuesJson) {
        Map<String, String> valueMap = Constants.SIMPLE_PARSER.parseJSON(valuesJson, new TypeToken<Map<String, String>>() {
        });
        BigDecimal marketPrice = ucoinGrandService.computeUcoin(typeId, valueMap);
        DecimalFormat df = new DecimalFormat("#.00");
        marketPrice = new BigDecimal(df.format(marketPrice));
        return new JsonResponseBean(marketPrice).toJson();
    }

    /**
     * 批量发放。
     *
     * @param paykey 支付密码
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/batchGrand", method = RequestMethod.POST)
    @ResponseBody
    public String batchGrand(@RequestParam("typeId") int typeId,
                             @RequestParam("key") long key,
                             @RequestParam("paykey") String paykey) {
        HttpSession session = WebUtil.getCurrentSession();
        Long key2 = (Long) session.getAttribute(EXCEL_LIST_TIME_KEY);
        if (key2 == null || key2 != key) {
            session.removeAttribute(EXCEL_LIST_TIME_KEY);
            session.removeAttribute(EXCEL_LIST_KEY);
            throw new UserOperationException("key值错误，请重新导入Excel.");
        }
        Map<Integer, List<List<String>>> excelAndErrorInfo = (Map<Integer, List<List<String>>>) session.getAttribute(EXCEL_LIST_KEY);
        ucoinGrandService.batchGrand(typeId, excelAndErrorInfo, paykey);
        session.removeAttribute(EXCEL_LIST_TIME_KEY);
        session.removeAttribute(EXCEL_LIST_KEY);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 获取参数列表。
     *
     * @param typeId 业务类型ID
     */
    @RequestMapping(value = "/getParam", method = RequestMethod.POST)
    @ResponseBody
    public String getParam(@RequestParam("typeId") Integer typeId) {
        List<Param> paramList = ucoinGrandService.getParam(typeId);
        return new JsonResponseBean(paramList).toJson();
    }

    /**
     * 获取参数值列表。
     *
     * @param paramId 参数ID
     */
    @RequestMapping(value = "/getParamValue", method = RequestMethod.POST)
    @ResponseBody
    public String getParamValue(@RequestParam("paramId") Integer paramId) {
        List<ParamValue> paramValueList = ucoinGrandService.getParamValues(paramId);
        return new JsonResponseBean(paramValueList).toJson();
    }






}
