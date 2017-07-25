package com.ylife.payorder.entity;

import java.math.BigDecimal;
import com.alibaba.fastjson.annotation.JSONField;

/**
* PayOrderEntity实体
* @author henry
* @date 2017-07-05 16:59:50
* @version <b>1.0.0</b>
*/
public class PayOrderEntity implements java.io.Serializable {
	/**
	 * id
	 */
	private Long id;
	/**
	 * 订单code
	 */
	private Long orderCode;
	/**
	 * 货品ID
	 */
	private Long goodsInfoId;
	/**
	 * 供应商ID
	 */
	private Long thirdId;
	/**
	 * 网点ID
	 */
	private Long enterpriseId;
	/**
	 * 邮豆金额
	 */
	private BigDecimal price;
	/**
	 * 结算价
	 */
	private BigDecimal settlePrice;
	/**
	 * 开始时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String startTime;
	/**
	 * 结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String endTime;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String createTime;
	/**
	 * 货品名称
	 */
	private String goodsInfoName;
	/**
	 * 供应商名称
	 */
	private String thirdName;

	/** -------------------附加字段------------------------- */
	/**
	 * 订单创建时间
	 */
	private String orderCreateTime;
	/**
	 * 订单支付时间
	 */
	private String payTime;
	/**
	 * 网点名称
	 */
	private String enterpriseName;
	/**
	 * 查询开始时间
	 */
	private String orderPayStartTime;
	/**
	 * 查询结束时间
	 */
	private String orderPayEndTime;

	/**
	 * 设置id
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获得id
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

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
	 * 设置goodsInfoId
	 *
	 * @param goodsInfoId the goodsInfoId to set
	 */
	public void setGoodsInfoId(Long goodsInfoId) {
		this.goodsInfoId = goodsInfoId;
	}

	/**
	 * 获得goodsInfoId
	 *
	 * @return the goodsInfoId
	 */
	public Long getGoodsInfoId() {
		return goodsInfoId;
	}

	/**
	 * 设置thirdId
	 *
	 * @param thirdId the thirdId to set
	 */
	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;
	}

	/**
	 * 获得thirdId
	 *
	 * @return the thirdId
	 */
	public Long getThirdId() {
		return thirdId;
	}

	/**
	 * 设置enterpriseId
	 *
	 * @param enterpriseId the enterpriseId to set
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	/**
	 * 获得enterpriseId
	 *
	 * @return the enterpriseId
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
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
	 * 设置startTime
	 *
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * 获得startTime
	 *
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 设置endTime
	 *
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 获得endTime
	 *
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 设置createTime
	 *
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获得createTime
	 *
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
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

	public String getOrderPayStartTime() {
		return orderPayStartTime;
	}

	public void setOrderPayStartTime(String orderPayStartTime) {
		this.orderPayStartTime = orderPayStartTime;
	}

	public String getOrderPayEndTime() {
		return orderPayEndTime;
	}

	public void setOrderPayEndTime(String orderPayEndTime) {
		this.orderPayEndTime = orderPayEndTime;
	}
}

