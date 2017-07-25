package com.ylife.chinapost.boss.service.impl;


import com.ylife.chinapost.boss.service.GoodsBrandBossService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.model.GoodsBrand;
import com.ylife.goods.service.GoodsBrandService;
import com.ylife.goods.service.GoodsService;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
@Service
public class GoodsBrandBossServiceImpl implements GoodsBrandBossService {

	//注入Service
	@Resource
	GoodsBrandService goodsBrandService;

	@Resource
	GoodsService goodsService;

	//查询商品列表并分页
	@Override
	public Page<GoodsBrand> queryBrandByNameOrBrandNickname(String brandName, String brandNickname, Pageable pageable) {
		return goodsBrandService.queryBrandByNameOrBrandNickname(brandName, brandNickname, pageable);
	}

	//添加商品品牌信息
	@Override
	public int insertGoodsBrand(String brandName, String brandNickname, String brandLogo ,Integer brandSort, String userName) {
		GoodsBrand goodsBrand = new GoodsBrand();
		goodsBrand.setBrandName(brandName);
		if(!StringUtil.isBlank(brandNickname)) {
			goodsBrand.setBrandNickname(brandNickname);
		}
		if(!StringUtil.isBlank(brandNickname)) {
			goodsBrand.setBrandLogo(brandLogo);
		}
		goodsBrand.setBrandSort(brandSort);
		goodsBrand.setBrandCreateName(userName);
		//删除标记
		goodsBrand.setBrandDelflag("0");
		goodsBrand.setBrandCreateTime(new Date());
		//商品类型为线上
		goodsBrand.setGoodsInfoType("1");
		return goodsBrandService.insertGoodsBrand(goodsBrand);
	}

	//删除商品品牌信息
	@Override
	public int deleteGoodsBrand(Long brandId, String deleteName) {
		if(goodsService.countGoodsByBrandId(brandId) > 0) {
			throw new UserOperationException("所选商品品牌存在商品，不能删除。");
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("brandId", brandId.toString());
		map.put("del_name", deleteName);
		return goodsBrandService.deleteGoodsBrand(map);

	}

	@Override
	public int batchDeleteGoodsBrand(List<String> list,String deleteName) {
		for(int i = 0; i < list.size();i ++)
		{
			if(goodsService.countGoodsByBrandId(Long.valueOf(list.get(i))) >0) {
				throw new UserOperationException("所选商品品牌存在商品，不能删除。");
			}
			Map<String, String> map = new HashMap<String, String>();
			String brandId = list.get(i);
			map.put("brandId", brandId);
			map.put("del_name", deleteName);
			goodsBrandService.deleteGoodsBrand(map);
		}
		return 0;
	}

	//修改商品品牌信息
	@Override
	public int updateGoodsBrand(Long brandId, String brandName, String brandNickname, String brandLogo ,Integer brandSort, String editName) {
		GoodsBrand goodsBrand = goodsBrandService.queryBrandById(brandId);
		Assert.notNull(goodsBrand);
		goodsBrand.setBrandName(brandName);
		if(!StringUtil.isBlank(brandNickname)) {
			goodsBrand.setBrandNickname(brandNickname);
		}
		if(!StringUtil.isBlank(brandLogo)) {
			goodsBrand.setBrandLogo(brandLogo);
		}
		goodsBrand.setBrandSort(brandSort);
		goodsBrand.setBrandModifiedName(editName);
		goodsBrand.setBrandModifiedTime(new Date());
		return goodsBrandService.updateGoodsBrad(goodsBrand);

	}

	//检查商品名是否重复
	@Override
	public int selectByBrandName(String brandName) {
		return goodsBrandService.selectByBrandName(brandName);
	}

	//根据brandId查询商品
	@Override
	public GoodsBrand queryBrandById(long brandId) {
		return goodsBrandService.queryBrandById(brandId);
	}
}
