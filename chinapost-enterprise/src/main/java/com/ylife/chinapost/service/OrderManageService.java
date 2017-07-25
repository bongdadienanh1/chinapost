package com.ylife.chinapost.service;

import com.ylife.client.bean.*;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/2.
 * 订单管理。
 */
public interface OrderManageService {

    /**
     * 获取订单。
     *
     * @param orderNo      订单号
     * @param status       订单状态
     * @param selfPick     是否自提
     * @param username     用户名
     * @param receiver     收货人
     * @param contactPhone 联系电话
     * @param createStart  创建开始时间
     * @param createEnd    创建结束时间
     * @param payStart     支付开始时间
     * @param payEnd       支付结束时间
     * @param pageable     分页信息
     */
    Page<Order> getOrders(Long orderNo,
                          Long enterpriseid,
                          OrderStatus status,
                          Boolean selfPick,
                          String username,
                          String receiver,
                          String contactPhone,
                          Date createStart,
                          Date createEnd,
                          Date payStart,
                          Date payEnd,
                          Pageable pageable);

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
    Page<CreditOrder> getBackOrder(Long backOrderNo,
                                   Long enterpriseId,
                                   String phoneNo,
                                   Long orderNo,
                                   CreditOrderStatus status,
                                   Date start,
                                   Date end,
                                   Pageable pageable);

    /**
     * 企业审核退货单/退款单。
     *
     * @param creditOrderNo 退货单号
     * @param agree         是否同意
     * @param message       给客户留言
     */
    void reviewCreditOrder(long creditOrderNo, boolean agree, String message);

    /**
     * 企业退货单收货。
     *
     * @param creditOrderNo 退货单号
     * @param receive       是否收货
     * @param enterpriseMsg 给商城留言
     * @param customerMsg   给客户留言
     */
    void receiveCreditOrder(long creditOrderNo, boolean receive, String enterpriseMsg, String customerMsg);

    /**
     * 退款。
     *
     * @param creditOrderNo 退货单号
     * @param price         退款数量
     * @param msg           客户留言
     */
    void refund(long creditOrderNo, BigDecimal price, String msg,BigDecimal backSubsidyPrice);

    /**
     * 获取所有状态
     */
    CreditOrderStatus[] getStatuses();

    /**
     * 获取所有状态
     */
    OrderStatus[] getOrderStatuses();

    /**
     * 修改状态为已付款而且他妈的不扣用户的钱？！
     *
     * @param orderCode 订单号
     */
    void statusToPayed(long orderCode);

    /**
     * 取消订单
     *
     * @param orderCode 订单号
     */
    void cancelOrder(long orderCode);

    /**
     * 修改金额
     *
     * @param orderCode 订单号
     * @param price     金额
     */
    void editPrice(long orderCode, BigDecimal price);


    /**
     * 定时取消订单（代客下单未支付）
     */
    void cancelOrderTiming();

    /**
     * 代客退货
     *
     * @param orderNo       订单号
     * @param reason        退单原因
     * @param credential    申请凭据
     * @param credentialDoc 上传的凭据
     * @param remark        问题说明
     * @param backWay       商品返回方式
     */
    void valetBackOrder(long orderNo,
                        Map<Long, Integer> goodsIdMap,
                        CreditOrderReason reason,
                        CredentialType credential,
                        String credentialDoc,
                        String remark,
                        String backWay);

    /**
     * 根据订单id获取商品详情
     * @param orderId 订单Id
     * */
    Order getOrderGoods(Long orderId);


    List<BackOrderGoods> getBackOrderGoodsByOrderId(Long orderId);



}
