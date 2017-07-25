/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.model;

import com.ylife.order.model.OrderContainerRelation;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月21日 下午4:41:20
 * @version 0.0.1
 */
public class OrderInfoBean implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = -8366615821561317832L;
    /*
     *订单ID
      */
    private Long orderId;
    /*
     *订单编号
      */
    private String orderNo;
    /*
     *会员编号
      */
    private Long customerId;
    /*
     *商品总金额
      */
    private BigDecimal moneyPaid;
    /*
     *购买时间
      */
    private Date payTime;
    /**
     * 添加时间
     * */
    private Date addTime;
    /*
     *是否使用优惠劵
      */
    private String couponsDelfag;
    /*
     *商品名称
      */
    private String goodsName;
    /*
     *商品图片
      */
    private String goodsImg;
    /*
     *商品列表
      */
    private List<GoodsBean> goods = new ArrayList<GoodsBean>(0);
    /*
     *优惠劵列表
      */
    private List<CustomerCoupon> coupons = new ArrayList<CustomerCoupon>(0);
    /*
     *配送方式
      */
    private Long dmId;
    /*
     *配送方式
      */
    private ExpressBean express;
    /*
     *商品重量
      */
    private Long weight;
    /*
     *支付方式
      */
    private Long payId;
    /*
     *支付方式
      */
    private PayBean pay;
    /*
     *配送费用
      */
    private BigDecimal shippingFee;
    /*
     *订单总额
      */
    private BigDecimal orderAmount;
    /*
     *发货时间
      */
    private Date shippingTime;
    /**
     * 收货地址编号
     * 
     * @see #getAddressId()
     * @see #setAddressId(Long)
     */
    private Long addressId;
    /**
     * 商品编号
     * 
     * @see #getGoodsId()
     * @see #setGoodsId(Long)
     */
    private Long goodsId;
    /*
     *订单状态
      */
    private String orderStatus;
    /*
     *配送状态
      */
    private String shippingStatus;
    /**
     * 订单赠送积分
     * 
     * @see #getAcquireIntegral()
     * @see #setAcquireIntegral(Long)
     */
    private Long acquireIntegral;
    /**
     * 订单完成时间
     * 
     * @see #getSuccessTime()
     * @see #setSuccessTime(Date)
     */
    private Date successTime;

    /**
     * 收货地址
     * 
     * @see #getAddress()
     * @see #setAddress(CustomerAddress)
     */
    private CustomerAddress address;



    /**
     * 收货省
     */

    private String shippingProvince;

    /**
     * 收货市
     */

    private String shippingCity;

    /**
     * 收货区县
     */

    private String shippingCounty;

    /**
     * 收货详细地址
     */

    private String shippingAddress;

    /**
     * 收货人
     */

    private String shippingPerson;

    /**
     * 收货电话
     */

    private String shippingPhone;

    /**
     * 收货手机
     */

    private String shippingMobile;


    /*
     *发票类型
      */
    private String invoiceType;
    /*
     *发票抬头
      */
    private String invoiceTitle;
    /*
     *发票内容
      */
    private String invoiceContent;
    /*
     *取消时间
      */
    private Date cancelTime;
    /*
     *原因
      */
    private String cancelRemark;
    /*
     *所属商家
      */
    private Long businessId;

    /**
     * 晒单状态 0代表没完成订单商品的晒单 1代表已完成订单商品的晒单
     */

    private Long shareFlag;
    /**
     * 原始金额
     */

    private BigDecimal oldPrice;
    /**
     * 优惠金额
     */

    private BigDecimal prePrice;
    /**
     * 配送方式0快递配送1上面自提
     */

    private Long orderExpressType;
    /**
     * 订单方式0 pc 订单 1：手机订单 2：微信订单
     */

    private Long orderMType;

    /**
     * 积分金额
     */
    private BigDecimal jfPrice;

    /**
     * 物流订单
     */

    private List<OrderContainerRelation> expressno;

    /**
     * 其他金额
     * @return
     */
    private BigDecimal otherAmount;
    /**
     * UB金额
     * @return
     */
    private BigDecimal ucoinPrise;
    /**
     * 从哪个端进来的订单
     * 1表示订单UB c端
     * @return
     */
    private BigDecimal isUcoin;

    /**
     * 支付方式 0 货到付款 1在线支付
     */
    private String orderLinePay;

    /**
     * 订单出库状态
     */

    private String orderCargoStatus;

    /**
     * 提货码
     */
    private String deliveryCode;

    /**
     * enterpriseId
     * @return
     */
    private Long enterpriseId;

    /*自提标志（0表示未自提，1表示自提）*/
    private Boolean selfPick;

    /*是否为代客*/
    private Boolean isValet;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Long getAcquireIntegral() {
        return acquireIntegral;
    }

    public void setAcquireIntegral(Long acquireIntegral) {
        this.acquireIntegral = acquireIntegral;
    }
    /**
     * 获取成功时间
     * */
    public Date getSuccessTime() {
        if (this.successTime != null) {
            return new Date(this.successTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置成功时间
     * */
    public void setSuccessTime(Date successTime) {
        if (successTime != null) {
            Date tEmp = (Date) successTime.clone();
            if (tEmp != null) {
                this.successTime = tEmp;
            }
        }
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }
    /**
     * 获取取消时间
     * */
    public Date getCancelTime() {
        if (this.cancelTime != null) {
            return new Date(this.cancelTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置取消时间
     * */
    public void setCancelTime(Date cancelTime) {
        if (cancelTime != null) {
            Date tEmp = (Date) cancelTime.clone();
            if (tEmp != null) {
                this.cancelTime = tEmp;
            }
        }
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取添加时间
     * */
    public Date getAddTime() {
        if (this.addTime != null) {
            return new Date(this.addTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置添加时间
     * */
    public void setAddTime(Date addTime) {
        if (addTime != null) {
            Date tEmp = (Date) addTime.clone();
            if (tEmp != null) {
                this.addTime = tEmp;
            }
        }
    }

    public ExpressBean getExpress() {
        return express;
    }

    public void setExpress(ExpressBean express) {
        this.express = express;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public List<CustomerCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CustomerCoupon> coupons) {
        this.coupons = coupons;
    }
    /**
     * 获取购物时间
     * */
    public Date getShippingTime() {
        if (this.shippingTime != null) {
            return new Date(this.shippingTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置购物时间
     * */
    public void setShippingTime(Date shippingTime) {
        if (shippingTime != null) {
            Date tEmp = (Date) shippingTime.clone();
            if (tEmp != null) {
                this.shippingTime = tEmp;
            }
        }
    }

    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Long getDmId() {
        return dmId;
    }

    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }
        /*
         *获取商品名称
          */
    public String getGoodsName() {
        goodsName = goods.get(0).getGoodsName();
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
            /*
            *获取商品图片
             */
    public String getGoodsImg() {
        goodsImg = goods.get(0).getGoodsImg();
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }
    /**
     * 获取购买时间
     * */
    public Date getPayTime() {
        if (this.payTime != null) {
            return new Date(this.payTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置购买时间
     * */
    public void setPayTime(Date payTime) {
        if (payTime != null) {
            Date tEmp = (Date) payTime.clone();
            if (tEmp != null) {
                this.payTime = tEmp;
            }
        }
    }

    public String getCouponsDelfag() {
        return couponsDelfag;
    }

    public void setCouponsDelfag(String couponsDelfag) {
        this.couponsDelfag = couponsDelfag;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    public Long getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(Long shareFlag) {
        this.shareFlag = shareFlag;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal prePrice) {
        this.prePrice = prePrice;
    }

    public Long getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(Long orderExpressType) {
        this.orderExpressType = orderExpressType;
    }

    public Long getOrderMType() {
        return orderMType;
    }

    public void setOrderMType(Long orderMType) {
        this.orderMType = orderMType;
    }

    public BigDecimal getJfPrice() {
        return jfPrice;
    }

    public void setJfPrice(BigDecimal jfPrice) {
        this.jfPrice = jfPrice;
    }

    public List<OrderContainerRelation> getExpressno() {
        return expressno;
    }

    public void setExpressno(List<OrderContainerRelation> expressno) {
        this.expressno = expressno;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public BigDecimal getUcoinPrise() {
        return ucoinPrise;
    }

    public void setUcoinPrise(BigDecimal ucoinPrise) {
        this.ucoinPrise = ucoinPrise;
    }

    public BigDecimal getIsUcoin() {
        return isUcoin;
    }

    public void setIsUcoin(BigDecimal isUcoin) {
        this.isUcoin = isUcoin;
    }

    public String getOrderLinePay() {
        return orderLinePay;
    }

    public void setOrderLinePay(String orderLinePay) {
        this.orderLinePay = orderLinePay;
    }

    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Boolean getSelfPick() {
        return selfPick;
    }

    public void setSelfPick(Boolean selfPick) {
        this.selfPick = selfPick;
    }

    public Boolean getIsValet() {
        return isValet;
    }

    public void setIsValet(Boolean isValet) {
        this.isValet = isValet;
    }
}
