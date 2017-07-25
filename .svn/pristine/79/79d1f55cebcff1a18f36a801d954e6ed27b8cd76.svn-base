package com.ylife.chinapost.mobile.service;

import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.order.model.Order;

import java.util.List;

/**
 * Created by InThEnd on 2016/5/8.
 * 下单接口
 */
public interface PlaceOrderService {

    String submitOrderAndPay(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId, String paykey);

    /**
     * 提交订单
     *
     * @param addressId      地址ID
     * @param shoppingCartId 购物车ID
     * @param selfPick       是否自提
     * @param remark         备注
     * @param enterpriseId   企业ID
     */
    List<Order> submitOrder(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId);

    /**
     * 支付订单
     *
     * @param orderCodes 订单号
     * @param paykey     支付密码
     */
    void payOrder(long[] orderCodes, String paykey);

    /**
     * 取消订单
     *
     * @param orderCode 订单号
     */
    void cancelOrder(long orderCode);

    /**
     * 确认收货
     *
     * @param orderCode 订单号
     */
    void confirmOrder(long orderCode);

    /**
     * 获取提货码。
     *
     * @param orderCode 订单号
     */
    String getDeliveryCode(long orderCode);

    /**
     * 获取订单信息
     * @param orderCode 订单号
     * @return
     */
    Order getOrder(long orderCode);

    /**
     * 支付密码是否设置。
     */
    boolean isPaykeySet();

    /**
     * 设置支付密码
     *
     * @param paykey 支付密码
     */
    void setPaykey(String paykey);

    /**
     * 获取推荐企业信息
     */
    List<EnterpriseInfo> getSuggestEnterprise();

    /**
     * 获取推荐企业信息
     */
    List<EnterpriseInfo> getEnterpriseByDistrictId(long districtId);
}
