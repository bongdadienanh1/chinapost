package com.ylife.inventory.model;

public class Inventory extends InventoryKey {
    //库存
    private Integer inventory;
    //可用库存
    private Integer available;

    private  Integer goodsInfoWarning;


    public Inventory() {
    }

    public Inventory(InventoryKey key, Integer inventory, Integer available,Integer goodsInfoWarning) {
        super(key.getEnterpriseId(), key.getGoodsId());
        this.inventory = inventory;
        this.available = available;
        this.goodsInfoWarning=goodsInfoWarning;

    }
    
    public Integer getGoodsInfoWarning() {
        return goodsInfoWarning;
    }

    public void setGoodsInfoWarning(Integer goodsInfoWarning) {
        this.goodsInfoWarning = goodsInfoWarning;
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
}