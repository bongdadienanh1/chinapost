/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.PageBean;
import com.ylife.goods.model.GoodsBrand;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品品牌service层接口
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月16日 下午7:50:05
 * @version 1.0
 */
public interface GoodsBrandService {

    /**
     * 根据PageBean 查询分页列表
     * 
     * @param pageBean
     *            分页辅助类 {@link com.ysh.goods.util.PageBean}
     * @return 查询到的列表封装到PageBean中
     */
    PageBean queryByPageBean(PageBean pageBean);

    /**
     * 根据Id查询商品品牌
     * 
     * @param brandId
     *            商品品牌ID {@link java.lang.Long}
     * @return {@link com.ysh.goods.bean.GoodsBrand}
     */
    GoodsBrand queryBrandById(Long brandId);

    /**
     * 查询所有的商品品牌
     * 
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryAllBrand();

    /**
     * 根君名称查询商品品牌
     * 
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryallbrandbyName(String brandName);

    /**
     * 查询所有品牌
     * 
     * @return List
     */
    List<GoodsBrand> queryAllBrandList();

    /**
     * 验证品牌名称是否可用
     * 
     * @param brandNmae
     *            待验证的品牌名称
     * @return 可用返回true 不可用返回false
     */
    boolean checkBrandName(String brandNmae);

    /**
     * 验证品牌名称，不可重复添加
     *
     * @param brandName
     *            待验证的品牌名称
     * @return 返回查询条数，若=0则可以添加，反之不可添加
     */
    int selectByBrandName(String brandName);
}
