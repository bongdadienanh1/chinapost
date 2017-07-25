package com.ylife.chinapost.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.order.model.Order;
import com.ylife.product.model.GoodsInfo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/29.
 * 代客下单服务。
 */
public interface ValetOrderService {

    /**
     * 获取商品信息。
     */

    Page<GoodsManagerResult> getValetGoods(String goodsInfoName,
                                           BigDecimal lowPrice,
                                           BigDecimal highPrice,
                                           Pageable pageable);


    /**
     * 获取代客商城本网点商品
     * @param goodsInfoName
     * @param lowPrice
     * @param highPrice
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getValetGoodsByEnterpriseId(String goodsInfoName,
                                        BigDecimal lowPrice,
                                        BigDecimal highPrice,
                                        Pageable pageable);




    /**
     * 获取商品详情信息
     *
     * @param goodsInfoId 货品ID
     */
    GoodsInfo getValetGoodsByGoodsInfoId(long goodsInfoId);


    /**
     * 下单。
     *
     * @param customerId 客户ID
     * @param isOnline   订单类型
     * @param addressId  地址ID
     */
    Order placeOrder(long customerId, boolean isOnline, Long addressId,Map<Long,Integer> goodsInfo,String subsidyRemark,BigDecimal subsidyPrice);


    Order submmitAndPay(long customerId, boolean isOnline, Long addressId,Map<Long,Integer> goodsInfo,String subsidyRemark,BigDecimal subsidyPrice,String payKey);

    /**
     * 获取当前网点的信息。
     */
    EnterpriseInfo getEnterpriseInfo();


    /**
     * 获取用户信息。
     *
     * @param idCard 身份证号
     */
    ChinapostCustomer getCustomerInfo(String idCard);

    /**
     * 获取商品信息。
     *
     * @param goodsInfoId 商品ID
     */
    GoodsInfo getGoodsInfo(long goodsInfoId);


    /**
     * 获取所有客户地址。
     */
    List<CustomerAddress> getAddresses(long customerId);

    /**
     * 增加收货地址。
     *
     * @param customerId    客户ID
     * @param addressName   收货人姓名
     * @param addressDetail 地址明细
     * @param addressMoblie 联系手机
     * @param isDefault     是否默认 0不是默认，1默认
     * @param infoProvince  省份id
     * @param infoCity      城市id
     * @param infoCounty    乡镇id
     * @param infoStreet    街道id
     * @param addressZip    邮政编码
     * @param addressPhone  电话
     */
    CustomerAddress addAddress(long customerId, String addressName, String addressDetail, String addressMoblie, String isDefault,
                               String infoProvince, String infoCity, String infoCounty, String infoStreet, String addressZip, String addressPhone);

    /**
     * 删除收货地址。
     *
     * @param addressId 地址ID
     */
    void deleteAddress(long addressId);

    /**
     * 代客下单的支付订单。
     */
    Order payOrder(long orderCode,BigDecimal subsidyPrice,String payKey);


    /**
     * 根据提货码获取订单
     */
    Order getOrderByDeliveryCode(String deliveryCode);

    /**
     * 根据订单号获取订单
     */
    Order getOrderByCode(long code);

    /**
     * 根据提货码提货。
     */
    void pickUpOrder(String deliveryCode);

    /**
     * 是否免验证
     */
    boolean hasPermit();

    /**
     * 库存是否充足。
     */
    boolean hasInventory(long goodInfoId);


    /**
     * 根据身份证号获取用户余额
     *
     * @param idCard 身份证号
     */
    BigDecimal getCustomerBalance(String idCard);

}
