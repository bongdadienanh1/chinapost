package com.ylife.chinapost.boss.controller;

import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.GoodsBrandBossService;
import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsBrand;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 * 商品品牌管理
 */
@Controller
@RequestMapping(value = "/GoodsBrand")
public class GoodsBrandController {

	//注入Service
	@Resource
	GoodsBrandBossService goodsBrandBossService;

	private final String MODULE_PATH = "/";

	//管理员用户名
	public static final String NAME = "qmbbc";

	//请求路径
	private String returnPath(String jspName) {
		return MODULE_PATH+ jspName;
	}

	/**
	 * 获取商品品牌
	 * 查询条件
	 * @param brandName 品牌名称
	 * @param brandNickname 品牌别名
	 * 分页参数
	 * @param page 页码
	 * @param size 页码尺寸
	 * @return
	 */
	@RequestMapping(value = "/getGoodsBrand")
	public ModelAndView getGoodsBrands(@RequestParam(value = "brandName", required = false) String brandName,
	                             @RequestParam(value = "brandNickname", required = false) String brandNickname,
	                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
	                             @RequestParam(value = "size", required = false, defaultValue = "10") int size){
		brandName = Constants.nullOrNotBlank(brandName);
		brandNickname = Constants.nullOrNotBlank(brandNickname);
		Page<GoodsBrand> pageList = goodsBrandBossService.queryBrandByNameOrBrandNickname(brandName, brandNickname, new Pageable(page, size));
		return new ModelAndView(returnPath("GoodsBrand")).addObject("pageBean", pageList).addObject("brandName",brandName).addObject("brandNickname",brandNickname);
	}

	/**
	 * 添加商品品牌
	 * @param brandName 品牌名
	 * @param brandNickname 品牌别名
	 * @param brandLogo 品牌Logo
	 * @param brandSort 品牌排序级别
	 * @return
	 */
	@RequestMapping(value = "/saveGoodsBrand", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String saveGoodsBrand(@RequestParam(value = "brandName") String brandName,
	                             @RequestParam(value = "brandNickname", required = false) String brandNickname,
	                             @RequestParam(value = "brandLogo", required = false) String brandLogo,
	                             @RequestParam(value = "brandSort") Integer brandSort){
		Assert.notNull(brandName,"品牌名称不能为空");
		Assert.notNull(brandSort,"品牌排序不能为空");
		goodsBrandBossService.insertGoodsBrand(brandName,brandNickname, brandLogo, brandSort, NAME);
		return new JsonResponseBean().toJson();
	}

	/**
	 * 批量删除商品品牌
	 * @param brandIds 品牌id
	 * @return
	 */
	@RequestMapping(value = "/deleteGoodsBrand", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteGoodsBrand(@RequestParam(value = "brandIds") String brandIds){
		List list=java.util.Arrays.asList(brandIds.split(","));
		goodsBrandBossService.batchDeleteGoodsBrand(list,NAME);
		return new JsonResponseBean().toJson();
	}

	/**
	 * 删除一条商品品牌
	 * @param brandId 品牌id
	 * @return
	 */
	@RequestMapping(value = "/deleteOneGoodsBrand", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String deleteOneGoodsBrand(@RequestParam("brandId") String brandId){
		goodsBrandBossService.deleteGoodsBrand(Long.valueOf(brandId), NAME);
		return new JsonResponseBean().toJson();
	}

	/**
	 * 修改商品品牌信息
	 * @param brandId 品牌id
	 * @param brandName 品牌名
	 * @param brandNickname 品牌别名
	 * @param brandLogo 品牌Logo
	 * @param brandSort 品牌排序级别
	 * @return
	 */
	@RequestMapping(value = "/editGoodsBrand", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String editGoodsBrand(@RequestParam(value = "brandId") Long brandId,
	                             @RequestParam(value = "brandName") String brandName,
	                             @RequestParam(value = "brandNickname", required = false) String brandNickname,
	                             @RequestParam(value = "brandLogo", required = false) String brandLogo,
	                             @RequestParam(value = "brandSort") Integer brandSort){
		goodsBrandBossService.updateGoodsBrand(brandId, brandName, brandNickname, brandLogo, brandSort, NAME);
		return new JsonResponseBean().toJson();
	}


	/**
	 * 检查品牌是否存在
	 * @param brandName 品牌名
	 * @return
	 */
	@RequestMapping(value = "/selectByBrandName", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String selectByBrandName(@RequestParam(value = "brandName") String brandName) {
		return new JsonResponseBean(goodsBrandBossService.selectByBrandName(brandName)).toJson();
	}


	/**
	 * 商品品牌编辑时根据brandId获取商品品牌
	 * @param brandId 品牌id
	 * @return
	 * */
	@RequestMapping(value = "/queryBrandById", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findGoodsBrand(String brandId) {
		return new JsonResponseBean(goodsBrandBossService.queryBrandById(Long.parseLong(brandId))).toJson();
	}


}
