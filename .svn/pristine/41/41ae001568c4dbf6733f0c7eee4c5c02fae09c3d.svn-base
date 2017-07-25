package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.OrderManageService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.*;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/4.
 * 订单管理控制器
 */
@Controller
@SecurityResource(parent = "/web/accountManager", primary = false)
@RequestMapping(value = "/web/api/ordermanage", produces = "application/json;charset=utf-8")
public class OrderManageAPIController {

    private Parser parser = new SimpleParser();
    @Resource
    private OrderManageService orderManageService;

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
    @ResponseBody
    public String getOrders(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                            @RequestParam(value = "orderNo", required = false) Long orderNo,
                            @RequestParam(value = "status", required = false) OrderStatus status,
                            @RequestParam(value = "selfPick", required = false) Boolean selfPick,
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


        Page<Order> page1 = orderManageService.getOrders(
                orderNo,
                enterpriseId,
                status,
                selfPick,
                username,
                receiver,
                contactPhone,
                createStartTime,
                createEndTime,
                payStartTime,
                payEndTime,
                new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
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
    @ResponseBody
    public String getBackOrders(@RequestParam(value = "backOrderNo", required = false) Long backOrderNo,
                                @RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                @RequestParam(value = "phoneNo", required = false) String phoneNo,
                                @RequestParam(value = "orderNo", required = false) Long orderNo,
                                @RequestParam(value = "status", required = false) CreditOrderStatus status,
                                @RequestParam(value = "start", required = false) String start,
                                @RequestParam(value = "end", required = false) String end,
                                @RequestParam(value = "page", required = false) int page,
                                @RequestParam(value = "size", required = false) int size) {
        phoneNo = Constants.nullOrNotBlank(phoneNo);
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }

        Page<CreditOrder> page1 = orderManageService.getBackOrder(backOrderNo,enterpriseId, phoneNo, orderNo, status, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(page1).toJson();
    }


    //审核退货
    @RequestMapping(value = "/reviewCreditOrder")
    @ResponseBody
    public String reviewCreditOrder(@RequestParam(value = "creditOrderNo") long creditOrderNo,
                                    @RequestParam(value = "agree") boolean agree,
                                    @RequestParam(value = "message", required = false) String message) {
        orderManageService.reviewCreditOrder(creditOrderNo, agree, message);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    //企业收货
    @RequestMapping(value = "/receiveCreditOrder")
    @ResponseBody
    public String receiveCreditOrder(@RequestParam(value = "creditOrderNo") long creditOrderNo,
                                     @RequestParam(value = "receive") boolean receive,
                                     @RequestParam(value = "enterpriseMsg", required = false) String enterpriseMsg,
                                     @RequestParam(value = "customerMsg", required = false) String customerMsg) {
        orderManageService.receiveCreditOrder(creditOrderNo, receive, enterpriseMsg, customerMsg);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    //给客户退款
    @RequestMapping(value = "/refund")
    @ResponseBody
    public String refund(@RequestParam(value = "creditOrderNo") long creditOrderNo,
                         @RequestParam(value = "price") BigDecimal price,
                         @RequestParam(value = "msg", required = false) String msg,
                         @RequestParam(value = "backSubsidyPrice", required = false) BigDecimal backSubsidyPrice
                         ) {
        orderManageService.refund(creditOrderNo, price, msg,backSubsidyPrice);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 修改状态(其实就是他妈的把订单改成已付款。江涛傻逼。)
     *
     * @param orderCode 订单号
     */
    @RequestMapping(value = "/editStatus")
    @ResponseBody
    public String changeToPayed(@RequestParam(value = "orderCode") long orderCode) {
        orderManageService.statusToPayed(orderCode);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 取消订单
     *
     * @param orderCode 订单号
     */
    @RequestMapping(value = "/cancelOrder")
    @ResponseBody
    public String cancelOrder(@RequestParam(value = "orderCode") long orderCode) {
        orderManageService.cancelOrder(orderCode);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改金额
     *
     * @param orderCode 订单号
     * @param price     金额
     */
    @RequestMapping(value = "/editPrice")
    @ResponseBody
    public String editPrice(@RequestParam(value = "orderCode") long orderCode, @RequestParam(value = "price") BigDecimal price) {
        Assert.isTrue(price.compareTo(BigDecimal.ZERO) >= 0, "金额必须大于等于0");
        orderManageService.editPrice(orderCode, price);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 代客退单
     */
    @RequestMapping(value = "/valetBackOrder")
    @ResponseBody
    public String valetBackOrder(@RequestParam("orderNo") long orderNo,
                                 @RequestParam("backGoodsId") String goodsIdJson,
                                 @RequestParam("reason") CreditOrderReason reason,
                                 @RequestParam("credential") CredentialType credential,
                                 @RequestParam("remark") String remark,
                                 @RequestParam(value = "backWay", required = false) String backWay) {
        Map<Long, Integer> goodsIdMap = parser.parseJSON(goodsIdJson, new TypeToken<Map<Long, Integer>>() {
        });
        orderManageService.valetBackOrder(orderNo, goodsIdMap, reason, credential, null, remark, backWay);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 获取订单商品详情
     * */

    @RequestMapping(value = "/getOrderGoods")
    @ResponseBody
    public String getOrderGoods(Long orderId){
        return new JsonResponseBean(orderManageService.getOrderGoods(orderId)).leave("orderGoodsList",
                                    "orderId",
                                    "goodsInfoNum",
                                    "goodsInfoName",
                                    "goodsImg",
                                    "goodsProductReleSpecVoList",
                                    "specValueRemark",
                                    "spec",
                                    "specName",
                                    "goodsSpecDetail",
                                    "specDetailName").toJson();
    }

    /**
     * 获取退单商品详情
     * */

    @RequestMapping(value = "/getBackOrderGoods")
    @ResponseBody
    public String getBackOrderGoods(long orderId){
        List<BackOrderGoods> list=orderManageService.getBackOrderGoodsByOrderId(orderId);
        return new JsonResponseBean(list).toJson();
    }
}
