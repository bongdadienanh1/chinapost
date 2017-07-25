/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageBean;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.mapper.GoodsBrandMapper;
import com.ylife.goods.model.GoodsBrand;
import com.ylife.goods.model.ValueUtil;
import com.ylife.goods.service.GoodsBrandService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌service实现类
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月16日 下午8:36:16
 */
@Service
public class GoodsBrandServiceImpl implements GoodsBrandService {

    // 注入GoodsBrandMapper
    @Resource
    private GoodsBrandMapper goodsBrandMapper;


    /**
     * 根据PageBean 查询分页列表
     *
     * @param pageBean
     * @return 查询到的列表封装到PageBean中
     */
    public PageBean queryByPageBean(PageBean pageBean) {
        // 查询总行数
        pageBean.setRows(this.goodsBrandMapper.queryTotalCount());
        // 定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            // 设置列表
            pageBean.setList(this.goodsBrandMapper.queryByPageBean(map));
        } finally {
            // 参数置空
            map = null;
        }
        // 返回pageBean
        return pageBean;
    }

    /**
     * 根据Id查询商品品牌
     *
     * @param brandId 商品品牌ID {@link java.lang.Long}
     * @return
     */
    public GoodsBrand queryBrandById(Long brandId) {
        // 根据Id查询商品品牌
        return this.goodsBrandMapper.selectByPrimaryKey(brandId);
    }

    /**
     * 查询所有的商品品牌
     * @param goodsInfoType
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *
     */
    public List<GoodsBrand> queryAllBrand(String goodsInfoType) {
        // 查询所有的商品品牌
        return this.goodsBrandMapper.queryAllBrand(goodsInfoType);
    }

	/**
	 * 查询所有的商品品牌
	 *
	 * @return 查询到的商品品牌的集合 {@link java.util.List}
	 *
	 */
	public List<GoodsBrand> queryAllBrand() {
		// 查询所有的商品品牌
		return queryAllBrand(null);
	}

    /**
     * 根君名称查询商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     *
     */
    @Override
    public List<GoodsBrand> queryallbrandbyName(String brandName) {
        // 根君名称查询商品品牌
        return this.goodsBrandMapper.queryallbrandbyName(brandName);
    }

	/**
     * 查询所有品牌
     *
     * @return List
     */
    public List<GoodsBrand> queryAllBrandList() {
        // 查询所有品牌
        return queryAllBrandList(null);
    }

	/**
	 * 查询所有的商品品牌
	 * @param goodsInfoType
	 * @return 查询到的商品品牌的集合 {@link java.util.List}
	 *
	 */
	public List<GoodsBrand> queryAllBrandList(String goodsInfoType) {
		// 查询所有的商品品牌
		return this.goodsBrandMapper.queryAllBrandList(goodsInfoType);
	}

    /**
     * 验证品牌名称是否可用
     *
     * @param brandNmae 待验证的品牌名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkBrandName(String brandNmae) {
        // 验证品牌名称是否可用
        return this.goodsBrandMapper.selectByBrandName(brandNmae) > 0 ? false
                : true;
    }

    /**
     * 验证品牌名称，不可重复添加
     *
     * @param brandName 待验证的品牌名称
     * @return 返回查询条数，若=0则可以添加，反之不可添加
     */
    @Override
    public int selectByBrandName(String brandName) {
        // 验证品牌名称，不可重复添加
        return goodsBrandMapper.selectByBrandName(brandName);
    }

	/**
	 * 根据名称和别名查询商品品牌
	 * @param brandName   商品名称
	 * @param brandNickname 货号
	 * @param pageable    分页信息
	 * @return 查询到的商品品牌的集合分页
	 *
	 */
	@Override
	public Page<GoodsBrand> queryBrandByNameOrBrandNickname(String brandName, String brandNickname, Pageable pageable) {
		List<GoodsBrand> list = goodsBrandMapper.queryBrandByNameOrNickname(brandName, brandNickname, pageable);
		int totalElements = goodsBrandMapper.countByNameOrNickname(brandName, brandNickname);
		return new PageImpl<>(pageable, totalElements, list);
	}

	/**
	 * 添加商品品牌
	 * @param goodsBrand   商品品牌信息
	 * @return 1成功 0失败
	 *
	 */
	@Override
	public int insertGoodsBrand(GoodsBrand goodsBrand) {
		if(0 == this.selectByBrandName(goodsBrand.getBrandName())  ){
			return goodsBrandMapper.insertSelective(goodsBrand);
		}
		return 0;
	}

	/**
	 * 删除商品品牌
	 * @param map 商品id和删除人
	 * @return 操作条数
	 *
	 */
	@Override
	public int deleteGoodsBrand(Map<String, String> map) {
		// 根据主键删除商品品牌
		if(goodsBrandMapper.selectByPrimaryKey(Long.valueOf(map.get("brandId"))) != null)
		{
			return this.goodsBrandMapper.deleteByPrimaryKey(map);
		}
		return 0;
	}

	/**
	 * 修改商品品牌
	 * @param goodsBrand 商品品牌
	 * @return 1成功，0失败
	 *
	 */
	@Override
	public int updateGoodsBrad(GoodsBrand goodsBrand) {
		if (goodsBrandMapper.selectByPrimaryKey(goodsBrand.getBrandId()) != null) {
			return goodsBrandMapper.updateByPrimaryKey(goodsBrand);
		}
		return 0;
	}

}
