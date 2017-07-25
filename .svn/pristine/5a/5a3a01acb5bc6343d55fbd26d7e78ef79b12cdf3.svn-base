/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.goods.mapper.ProductWareMapper;
import com.ylife.goods.model.ProductWare;
import com.ylife.goods.model.WareHouse;
import com.ylife.goods.service.ProductWareService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 货品分仓
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午7:52:25
 * @version 1.0
 */
@Service("ProductWareService")
public class ProductWareServiceImpl implements
        ProductWareService {
    @Resource
    private ProductWareMapper productWareMapper;

    /**
     * 查询仓库信息
     *
     * @param did
     * @return
     */
    @Override
    public WareHouse findWare(Long did) {
        return productWareMapper.findWare(did);
    }

    /**
     * 保存关联信息
     *
     * @param productId
     *            货品ID
     * @param productStocks
     *            货品库存数组
     * @param prouctPrices
     *            货品库存价格
     * @return 插入的价格
     */
    @Transactional
    public int calcProductWare(Long productId, Long[] productStocks,
            BigDecimal[] prouctPrices, Long[] wareId) {
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        //定义一个对象
        ProductWare productWare = null;
        if (null != wareId && wareId.length > 0) {
            for (int i = 0; i < wareId.length; i++) {
                map.put("wareId", wareId[i]);
                map.put("productId", productId);
                //执行一个查询方法
                if (this.productWareMapper.queryCountByProductIdAndWareId(map) > 0) {
                    productWare = new ProductWare();
                    productWare.setWareId(wareId[i]);
                    productWare.setProductId(productId);
                    productWare.setId(this.productWareMapper
                            .queryIdByProductIdAndWareId(map));
                    productWare.setDelFlag("0");
                    productWare.setWarePrice(prouctPrices[i]);
                    productWare.setWareStock(productStocks[i]);
                    //执行修改方法
                    this.productWareMapper
                            .updateByPrimaryKeySelective(productWare);
                } else {
                    //new一个实体类对象
                    productWare = new ProductWare();
                    productWare.setWareId(wareId[i]);
                    productWare.setProductId(productId);
                    productWare.setDelFlag("0");
                    productWare.setWarePrice(prouctPrices[i]);
                    productWare.setWareStock(productStocks[i]);
                    //执行添加操作
                    this.productWareMapper.insertSelective(productWare);
                }
            }
        }
        //打印日志
        //LOGGER.info(ValueUtil.CALCPRODUCTWARE);
        return 0;
    }

    /**
     * 根据货品ID和地区ID查询关联记录
     *
     * @param productId
     *            货品ID
     * @param distinctId
     *            地区ID
     * @return 查询到的记录
     */
    public ProductWare queryProductWareByProductIdAndDistinctId(Long productId,
            Long distinctId) {
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            //设置参数
            map.put("productId", productId);
            map.put("distinctId", distinctId);
            //执行查询方法
            return this.productWareMapper
                    .queryProductWareByProductIdAndDistinctId(map);
        } finally {
            map = null;
        }
    }
}
