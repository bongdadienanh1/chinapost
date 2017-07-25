package com.ylife.chinapost.third.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.third.controller.utils.Constants;
import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.chinapost.third.service.OrderManageService;
import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserNotLoginException;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderStatus;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderStatus;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtilXssf;
import com.ylife.utils.StringUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/4.
 * 订单管理控制器
 */
@Controller
@RequestMapping(value = "/orderManage")
public class OrderManageAPIController {

    @Resource
    private OrderManageService orderManageService;

    @Resource
    private CurrentLoginService currentLoginService;

    @ExceptionHandler(UserNotLoginException.class)
    public String handlerUserNotLoginException() {
        return  "redirect:/login";
    }

    /**
     * 获取订单。
     *
     * @param orderNo      订单号
     * @param status       订单状态
     * @param username     用户名
     * @param receiver     收货人
     * @param contactPhone 联系电话
     * @param createStart  创建开始时间
     * @param createEnd    创建结束时间
     * @param payStart     支付开始时间
     * @param payEnd       支付结束时间
     * @param page         页数
     * @param size         页大小
     */
    @RequestMapping(value = "/getOrders")
    public String getOrders(HttpServletRequest request,
                            @RequestParam(value = "orderNo", required = false) Long orderNo,
                            @RequestParam(value = "status", required = false) OrderStatus status,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "receiver", required = false) String receiver,
                            @RequestParam(value = "contactPhone", required = false) String contactPhone,
                            @RequestParam(value = "createStart", required = false) String createStart,
                            @RequestParam(value = "createEnd", required = false) String createEnd,
                            @RequestParam(value = "payStart", required = false) String payStart,
                            @RequestParam(value = "payEnd", required = false) String payEnd,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        if (status != null && status == OrderStatus.WAIT_DELIVER) {
            status = OrderStatus.PAYED;
        } else if (status != null && status == OrderStatus.WAIT_PICKUP) {
            status = OrderStatus.PAYED;
        }
        username = Constants.nullOrNotBlank(username);
        receiver = Constants.nullOrNotBlank(receiver);
        contactPhone = Constants.nullOrNotBlank(contactPhone);

        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }


        Page<Order> pageBean = orderManageService.getOrders(
                orderNo,
                status,
                null,
                username,
                receiver,
                contactPhone,
                createStartTime,
                createEndTime,
                payStartTime,
                payEndTime,
                currentLoginService.getCurrentLoginThirdId(),
                "1",
                new Pageable(page, size));

        request.setAttribute("orderNo",orderNo);
        request.setAttribute("status",status);
        request.setAttribute("username",username);
        request.setAttribute("receiver",receiver);
        request.setAttribute("contactPhone",contactPhone);
        request.setAttribute("createStart",createStart);
        request.setAttribute("createEnd",createEnd);
        request.setAttribute("payStart",payStart);
        request.setAttribute("payEnd",payEnd);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("firstNav", '2');
        request.setAttribute("twoNav", '1');
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "thirdOrderList";
    }


    /**
     * 获取出库订单。
     *
     * @param orderNo      订单号
     * @param username     用户名
     * @param receiver     收货人
     * @param contactPhone 联系电话
     * @param createStart  创建开始时间
     * @param createEnd    创建结束时间
     * @param payStart     支付开始时间
     * @param payEnd       支付结束时间
     * @param page         页数
     * @param size         页大小
     */
    @RequestMapping(value = "/getDeliveryOrders")
    public String getDeliveryOrders(HttpServletRequest request,
                            @RequestParam(value = "orderNo", required = false) Long orderNo,
                            @RequestParam(value = "username", required = false) String username,
                            @RequestParam(value = "receiver", required = false) String receiver,
                            @RequestParam(value = "contactPhone", required = false) String contactPhone,
                            @RequestParam(value = "createStart", required = false) String createStart,
                            @RequestParam(value = "createEnd", required = false) String createEnd,
                            @RequestParam(value = "payStart", required = false) String payStart,
                            @RequestParam(value = "payEnd", required = false) String payEnd,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        username = Constants.nullOrNotBlank(username);
        receiver = Constants.nullOrNotBlank(receiver);
        contactPhone = Constants.nullOrNotBlank(contactPhone);

        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }


        Page<Order> pageBean = orderManageService.getOrders(
                orderNo,
                OrderStatus.PAYED,
                "2",
                username,
                receiver,
                contactPhone,
                createStartTime,
                createEndTime,
                payStartTime,
                payEndTime,
                currentLoginService.getCurrentLoginThirdId(),
                "1",
                new Pageable(page, size));

        request.setAttribute("orderNo",orderNo);
        request.setAttribute("username",username);
        request.setAttribute("receiver",receiver);
        request.setAttribute("contactPhone",contactPhone);
        request.setAttribute("createStart",createStart);
        request.setAttribute("createEnd",createEnd);
        request.setAttribute("payStart",payStart);
        request.setAttribute("payEnd",payEnd);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("firstNav", '2');
        request.setAttribute("twoNav", '3');
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "thirdStockOutList";
    }



    /**
     * 获取所有退货单。
     *
     * @param backOrderNo 退单号
     * @param phoneNo     手机号码
     * @param orderNo     订单号
     * @param status      状态
     * @param start       开始时间
     * @param end         结束时间
     */
    @RequestMapping(value = "/getBackOrders")
    public String getBackOrders(HttpServletRequest request,@RequestParam(value = "backOrderNo", required = false) Long backOrderNo,
                                @RequestParam(value = "phoneNo", required = false) String phoneNo,
                                @RequestParam(value = "orderNo", required = false) Long orderNo,
                                @RequestParam(value = "status", required = false) CreditOrderStatus status,
                                @RequestParam(value = "start", required = false) String start,
                                @RequestParam(value = "end", required = false) String end,
                                @RequestParam(value = "page", required = false,defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false,defaultValue = "10") int size) {
        phoneNo = Constants.nullOrNotBlank(phoneNo);
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_TIME_FORMAT);
        }

        Page<CreditOrder> pageBean = orderManageService.getBackOrder(backOrderNo, phoneNo, orderNo, status, startTime, endTime,currentLoginService.getCurrentLoginThirdId(),"1", new Pageable(page, size));
        request.setAttribute("orderNo",orderNo);
        request.setAttribute("status",status);
        request.setAttribute("backOrderNo",backOrderNo);
        request.setAttribute("phoneNo",phoneNo);
        request.setAttribute("start",start);
        request.setAttribute("end",end);
        request.setAttribute("pageBean",pageBean);
        request.setAttribute("firstNav", '2');
        request.setAttribute("twoNav", '2');
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return "thirdOrderBackList";
    }

    /**
     * 获取订单商品详情
     * */

    @RequestMapping(value = "/getOrderGoods",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getOrderGoods(Long orderId){
        return new JsonResponseBean(orderManageService.getOrderGoods(orderId)).toJson();
    }

    /**
     * 获取退单商品详情
     * */

    @RequestMapping(value = "/getBackOrderGoods", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getBackOrderGoods(Long backOrderCode){
        return new JsonResponseBean(orderManageService.getBackOrder(backOrderCode)).toJson();
    }


    /**
     * 导出订单
     * @param status
     * @param username
     * @param receiver
     * @param contactPhone
     * @param createStart
     * @param createEnd
     * @param payStart
     * @param payEnd
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/orderDown")
    public void exportOrderExcel(@RequestParam(value = "orderNo", required = false) Long orderNo,
                                 @RequestParam(value = "status", required = false) OrderStatus status,
                                 @RequestParam(value = "username", required = false) String username,
                                 @RequestParam(value = "shippingPerson", required = false) String receiver,
                                 @RequestParam(value = "contactPhone", required = false) String contactPhone,
                                 @RequestParam(value = "createStart", required = false) String createStart,
                                 @RequestParam(value = "createEnd", required = false) String createEnd,
                                 @RequestParam(value = "payStart", required = false) String payStart,
                                 @RequestParam(value = "payEnd", required = false) String payEnd,
                                 HttpServletResponse response
    ) throws IOException {
        orderManageService.exportOrderExcel(orderNo,status,username,receiver,contactPhone,createStart,createEnd,payStart,payEnd,response);
    }

    /**
     * 导出退单
     * @param backOrderNo
     * @param phoneNo
     * @param orderNo
     * @param status
     * @param start
     * @param end
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/backOrderDown")
    public void exportBackOrderExcel(String backOrderNo,String phoneNo,String orderNo,
                                     CreditOrderStatus status, String start, String end,HttpServletResponse response) throws IOException{
        orderManageService.exportBackOrder(backOrderNo, phoneNo, orderNo, status, start, end, null, response);
    }

    /**
     * 导入快递单模板导出
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/exportTemplate")
    public void exportTemplate(HttpServletResponse response) throws IOException{
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        String sheetName = "出库数据模板";
        List<String> heads = Arrays.asList("订单号", "收货手机号", "快递公司", "快递单号");
        wb = ExcelUtilXssf.excelExport(wb, heads, null, 0, 0, sheetName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wb", wb);
        String excelName = sheetName+".xlsx";
        try {
            excelName = URLEncoder.encode(excelName, "UTF8");
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
     * 导入快递单
     * @param request
     * @param file
     * @return
     * @throws IOException
     * @throws InvalidFormatException
     */
    @RequestMapping(value = "/importExpress", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String importExpress(HttpServletRequest request,MultipartFile file) throws IOException, InvalidFormatException {
        Map<String,Object> map = orderManageService.parseFile(request,file);
        return JSON.toJSONString(map);
    }

    /**
     * 订单状态变更
     * @param request
     * @param backOrderCode
     * @param whetherType
     * @return
     */
    @RequestMapping(value = "/orderStateChange")
    @ResponseBody
    public String orderStateChange(HttpServletRequest request,Long backOrderCode,String whetherType){
        try{
            orderManageService.orderStateChange(request,backOrderCode,whetherType);
            return JsonResponseBean.getSuccessResponse().toJson();
        }catch (Exception e){
            return JsonResponseBean.getErrorResponse(ErrorCode.USER_OPERATION_ERROR,e.getMessage()).toJson();
        }
    }
}
