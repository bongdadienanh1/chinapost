package com.ylife.client.bean;

/**
 * 商品库存
 * @author jack chen
 * @version 2.0
 * @since 15/9/7
 */
public class OGoodsProductWare {

    /**
     * 仓库名称
     */
    private String wareName;

    /**
     * 仓库价格
     */
    private String warePrice;

    /**
     * 货品id
     */
    private Long productId;

    /**
     * 货品库存
     */
    private Long waretock;

    /**
     * 主键id
     */
    private Long id;

    /**
     * 仓库名称
     */
    public String getWareName() {
        return wareName;
    }

    public OGoodsProductWare setWareName(String wareName) {
        this.wareName = wareName;
        return this;
    }

    /**
     * 仓库价格
     */
    public String getWarePrice() {
        return warePrice;
    }

    public OGoodsProductWare setWarePrice(String warePrice) {
        this.warePrice = warePrice;
        return this;
    }

    /**
     * 货品id
     */
    public Long getProductId() {
        return productId;
    }

    public OGoodsProductWare setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    /**
     * 货品库存
     */
    public Long getWaretock() {
        return waretock;
    }

    public OGoodsProductWare setWaretock(Long waretock) {
        this.waretock = waretock;
        return this;
    }

    /**
     * 主键id
     */
    public Long getId() {
        return id;
    }

    public OGoodsProductWare setId(Long id) {
        this.id = id;
        return this;
    }
}
