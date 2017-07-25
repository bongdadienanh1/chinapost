/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageBean;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsBrand;
import java.util.List;
import java.util.Map;

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
     *
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
	 * 查询所有的商品品牌
	 * @param goodsInfoType
	 * @return 查询到的商品品牌的集合 {@link java.util.List}
	 *         {@link com.ysh.goods.bean.GoodsBrand}
	 */
	List<GoodsBrand> queryAllBrand(String goodsInfoType);

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
	 * 查询所有的商品品牌
	 * @param goodsInfoType
	 * @return 查询到的商品品牌的集合 {@link java.util.List}
	 *         {@link com.ysh.goods.bean.GoodsBrand}
	 */
	List<GoodsBrand> queryAllBrandList(String goodsInfoType);

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

	/**
	 * 获取当前库存货品列表的page
	 *
	 * @param brandName   商品名称
	 * @param brandNickname 货号
	 * @param pageable    分页信息
	 */
	Page<GoodsBrand> queryBrandByNameOrBrandNickname(String brandName, String brandNickname, Pageable pageable);

	/**
	 * 保存商品品牌信息
	 * @param goodsBrand
	 * @return 1成功，0失败
	 */
	int insertGoodsBrand(GoodsBrand goodsBrand);

	/**
	 * 删除商品品牌
	 * @param map
	 * @return 1成功，0失败
	 */
	int deleteGoodsBrand(Map<String, String> map);

	/**
	 * 修改商品品牌信息
	 * @param goodsBrand 商品品牌信息
	 * @return 1成功，0失败
	 */
	int updateGoodsBrad(GoodsBrand goodsBrand);

}