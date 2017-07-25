package com.ylife.chinapost.boss.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsBrand;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public interface GoodsBrandBossService {
	/**
	 * 商品品牌列表分页
	 * 查询条件
	 * @param brandName 品牌名
	 * @param brandNickname 品牌别名
	 * 分页参数
	 * @param pageable
	 * @return
	 */
	Page<GoodsBrand> queryBrandByNameOrBrandNickname(String brandName, String brandNickname, Pageable pageable);

	/**
	 * 添加商品品牌
	 * @param brandName 品牌名
	 * @param brandNickname 品牌别名
	 * @param brandLogo 品牌Logo
	 * @param brandSort 品牌排序级别
	 * @param userName 创建人
	 * @return 1成功 0失败
	 */
	int insertGoodsBrand(String brandName, String brandNickname, String brandLogo , Integer brandSort, String userName);

	/**
	 * 删除商品品牌
	 * @param brandId 品牌id
	 * @param deleteName 删除人
	 * @return 1成功 0失败
	 */
	int deleteGoodsBrand(Long brandId, String deleteName);

	/**
	 * 批量删除商品品牌
	 * @param list 品牌id列表
	 * @param deleteName 删除人
	 * @return 1成功 0失败
	 */
	int batchDeleteGoodsBrand(List<String> list,String deleteName);

	/**
	 * 修改商品品牌
	 * @param brandName 品牌名
	 * @param brandNickname 品牌别名
	 * @param brandLogo 品牌Logo
	 * @param brandSort 品牌排序级别
	 * @param editName 修改人
	 * @return 1成功 0失败
	 */
	int updateGoodsBrand(Long brandId, String brandName, String brandNickname, String brandLogo ,Integer brandSort, String editName);

	/**
	 * 查询商品品牌是否可以使用
	 * @return 0可添加 1以上不行
	 */
	int selectByBrandName(String brandName);

	/**
	 * 根据brandId查询商品品牌
	 * @param brandId 品牌id
	 * @return
	 */
	GoodsBrand queryBrandById(long brandId);


}
