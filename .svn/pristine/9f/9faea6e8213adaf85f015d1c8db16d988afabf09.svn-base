package com.ylife.payorder.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
* PayOrderEntityVo
* @author henry
* @date 2017-07-05 16:59:50
* @version <b>1.0.0</b>
*/
public class PayOrderEntityVo implements java.io.Serializable {
	/**
	 * 订单code
	 */
	private Long orderCode;
	/**
	 * 邮豆金额
	 */
	private BigDecimal price;
	/**
	 * 结算价
	 */
	private BigDecimal settlePrice;
	/**
	 * 货品名称
	 */
	private String goodsInfoName;
	/**
	 * 供应商名称
	 */
	private String thirdName;

	/**
	 * 订单创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String orderCreateTime;
	/**
	 * 订单支付时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String payTime;
	/**
	 * 网点名称
	 */
	private String enterpriseName;
	/**
	 * 订单状态
	 */
	private OrderStatus orderStatus;

	/**
	 * 设置orderCode
	 *
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * 获得orderCode
	 *
	 * @return the orderCode
	 */
	public Long getOrderCode() {
		return orderCode;
	}


	/**
	 * 设置price
	 *
	 * @param price the price to set
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	/**
	 * 获得price
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * 设置settlePrice
	 *
	 * @param settlePrice the settlePrice to set
	 */
	public void setSettlePrice(BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

	/**
	 * 获得settlePrice
	 *
	 * @return the settlePrice
	 */
	public BigDecimal getSettlePrice() {
		return settlePrice;
	}


	/**
	 * 设置goodsInfoName
	 *
	 * @param goodsInfoName the goodsInfoName to set
	 */
	public void setGoodsInfoName(String goodsInfoName) {
		this.goodsInfoName = goodsInfoName;
	}

	/**
	 * 获得goodsInfoName
	 *
	 * @return the goodsInfoName
	 */
	public String getGoodsInfoName() {
		return goodsInfoName;
	}

	/**
	 * 设置thirdName
	 *
	 * @param thirdName the thirdName to set
	 */
	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	/**
	 * 获得thirdName
	 *
	 * @return the thirdName
	 */
	public String getThirdName() {
		return thirdName;
	}

	public String getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(String orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
}

