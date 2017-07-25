package com.ylife.inventory.model;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryPurchaseBillItem {
    private Long itemId;

    private Long purchaseId;

    private Long enterpriseId;

    private Long goodsinfoId;

    private Integer amount;

    private BigDecimal settlePrice;

    private BigDecimal totalPrice;

    private Integer receiptAmount;

    private ItemGoodsInfo info;

    //合并后的商品总数
    private Integer sumAmount;

    private String enterpriseName;

	private String address;

	private String linkman;

	private String linkMobile;

	private String accountName;

	private String parentName;

	private Long code;

	private PurchaseBillStatus status;

	private String thirdName;

	private String goodsInfoName;

	private Date createTime;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getGoodsinfoId() {
        return goodsinfoId;
    }

    public void setGoodsinfoId(Long goodsinfoId) {
        this.goodsinfoId = goodsinfoId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(BigDecimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public ItemGoodsInfo getInfo() {
        return info;
    }

    public void setInfo(ItemGoodsInfo info) {
        this.info = info;
    }

    public Integer getSumAmount() {
        return sumAmount;
    }

    public void setSumAmount(Integer sumAmount) {
        this.sumAmount = sumAmount;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getLinkMobile() {
		return linkMobile;
	}

	public void setLinkMobile(String linkMobile) {
		this.linkMobile = linkMobile;
	}

	public Integer getReceiptAmount() {
        return receiptAmount;
    }

    public void setReceiptAmount(Integer receiptAmount) {
        this.receiptAmount = receiptAmount;
    }

    public static class ItemGoodsInfo {
        private Long goodsInfoId;

        private String goodsInfoName;

        private String goodsSpecification;
        //货品规格值
        private String goodsSpecValue;
        //规格字符串
        private String specString;

        private String goodsInfoItemNo;

        private Integer goodsInfoType;

        private Integer inventory;

        private Integer available;


        public Integer getGoodsInfoType() {
            return goodsInfoType;
        }

        public void setGoodsInfoType(Integer goodsInfoType) {
            this.goodsInfoType = goodsInfoType;
        }

        public Long getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(Long goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public Integer getInventory() {
            return inventory;
        }

        public void setInventory(Integer inventory) {
            this.inventory = inventory;
        }

        public Integer getAvailable() {
            return available;
        }

        public void setAvailable(Integer available) {
            this.available = available;
        }

        public String getGoodsInfoName() {
            return goodsInfoName;
        }

        public void setGoodsInfoName(String goodsInfoName) {
            this.goodsInfoName = goodsInfoName;
        }


        public String getGoodsSpecification() {
            return goodsSpecification;
        }

        public void setGoodsSpecification(String goodsSpecification) {
            this.goodsSpecification = goodsSpecification;
        }

        public String getGoodsSpecValue() {
            return goodsSpecValue;
        }

        public void setGoodsSpecValue(String goodsSpecValue) {
            this.goodsSpecValue = goodsSpecValue;
        }

        public String getSpecString() {
            return specString;
        }

        public void setSpecString(String specString) {
            this.specString = specString;
        }

        public String getGoodsInfoItemNo() {
            return goodsInfoItemNo;
        }

        public void setGoodsInfoItemNo(String goodsInfoItemNo) {
            this.goodsInfoItemNo = goodsInfoItemNo;
        }
    }

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public PurchaseBillStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseBillStatus status) {
		this.status = status;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getGoodsInfoName() {
		return goodsInfoName;
	}

	public void setGoodsInfoName(String goodsInfoName) {
		this.goodsInfoName = goodsInfoName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}