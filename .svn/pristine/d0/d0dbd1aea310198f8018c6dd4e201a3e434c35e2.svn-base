package com.ylife.ucoin.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/1.
 * 用户U宝历史
 */
public class CustomerUcoinHistory {

    private Long id;

    private Long enterpriseId;

    private Long customerId;

    private Long batchId;

    private HistoryType type;

    private BigDecimal price;

    private BigDecimal salesPrice;

    private BigDecimal marketPrice;

    private BigDecimal balancePrice;

    private Date businessTime;

    private Date createTime;

    private Long orderId;

    private Integer typeId;

    private String remark;

    private Long code;
    //流水号
    private String serialNum;

    private String paramJson;

    private String typeName;

    private Boolean isValet;

    private CustomerInfo customerInfo;

    private EnterpriseInfo enterpriseInfo;

    private OrderInfo orderInfo;

    private BigDecimal paySettlePrice;

	/*订单编号*/
	private Long orderCode;

	/*退单编号*/
	private Long backCode;

    public Boolean getIsValet() {
        return isValet;
    }

    public void setIsValet(Boolean isValet) {
        this.isValet = isValet;
    }

    public BigDecimal getBalancePrice() {
        return balancePrice;
    }

    public void setBalancePrice(BigDecimal balancePrice) {
        this.balancePrice = balancePrice;
    }

    public Date getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(Date businessTime) {
        this.businessTime = businessTime;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    private Map<String,String> paramMap;

    public Map<String, String> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String> paramMap) {
        this.paramMap = paramMap;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public HistoryType getType() {
        return type;
    }

    public void setType(HistoryType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getParamJson() {
        return paramJson;
    }

    public void setParamJson(String paramJson) {
        this.paramJson = paramJson;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public EnterpriseInfo getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public BigDecimal getPaySettlePrice() {
        return paySettlePrice;
    }

    public void setPaySettlePrice(BigDecimal paySettlePrice) {
        this.paySettlePrice = paySettlePrice;
    }

	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public Long getBackCode() {
		return backCode;
	}

	public void setBackCode(Long backCode) {
		this.backCode = backCode;
	}

	public static class OrderInfo{

        private Long orderId;
        private Long orderCode;
        private String orderStatus;

        private Boolean isValet;

        public Boolean getIsValet() {
            return isValet;
        }

        public void setIsValet(Boolean isValet) {
            this.isValet = isValet;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public Long getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(Long orderCode) {
            this.orderCode = orderCode;
        }
    }


    public static class CustomerInfo {

        private Long customerId;

        private String idCard;

        private String name;

        private Long contactPhone;

        public Long getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(Long contactPhone) {
            this.contactPhone = contactPhone;
        }

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class EnterpriseInfo {

        private Long enterpriseId;

        private String enterpriseName;

        private String enterpriseImg;

        public EnterpriseInfo() {
        }

        public Long getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(Long enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }

        public String getEnterpriseImg() {
            return enterpriseImg;
        }

        public void setEnterpriseImg(String enterpriseImg) {
            this.enterpriseImg = enterpriseImg;
        }
    }




}
