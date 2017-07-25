package com.ylife.enterprise.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/21.
 * 企业功能性模型
 */
public class EnterpriseFunction {

    private Long id;

    private Long parentId;

    private Long storeId;

    private String paykey;

    private BigDecimal undistributedPrice;

    private BigDecimal price;

    private BigDecimal ucoinPrice;

    private BigDecimal ucoincodePrice;

    private Date accountTime;

    private Integer inventoryForewarn;

    private Boolean isDelete;

    private Boolean hasPermit;

    private BigDecimal discountPct;

    private Long catalog;

    private Boolean end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getPaykey() {
        return paykey;
    }

    public void setPaykey(String paykey) {
        this.paykey = paykey;
    }

    public BigDecimal getUndistributedPrice() {
        return undistributedPrice;
    }

    public void setUndistributedPrice(BigDecimal undistributedPrice) {
        this.undistributedPrice = undistributedPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getUcoinPrice() {
        return ucoinPrice;
    }

    public void setUcoinPrice(BigDecimal ucoinPrice) {
        this.ucoinPrice = ucoinPrice;
    }

    public BigDecimal getUcoincodePrice() {
        return ucoincodePrice;
    }

    public void setUcoincodePrice(BigDecimal ucoincodePrice) {
        this.ucoincodePrice = ucoincodePrice;
    }

    public Date getAccountTime() {
        return accountTime;
    }

    public void setAccountTime(Date accountTime) {
        this.accountTime = accountTime;
    }

    public Integer getInventoryForewarn() {
        return inventoryForewarn;
    }

    public void setInventoryForewarn(Integer inventoryForewarn) {
        this.inventoryForewarn = inventoryForewarn;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Boolean getHasPermit() {
        return hasPermit;
    }

    public void setHasPermit(Boolean hasPermit) {
        this.hasPermit = hasPermit;
    }

    public BigDecimal getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(BigDecimal discountPct) {
        this.discountPct = discountPct;
    }

    public Long getCatalog() {
        return catalog;
    }

    public void setCatalog(Long catalog) {
        this.catalog = catalog;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    public long getMinCatalog() {
        return catalog;
    }

    public long getMaxCatalog() {
        return catalog + tail(catalog) - 1;
    }

    /**
     * 尾(yi)巴。
     */
    public static long tail(long catalog) {
        if (catalog == 0) {
            return 0;
        }
        long tail = 1;
        while (catalog % (tail * 100) == 0) {
            tail *= 100;
        }
        return tail;
    }
}
