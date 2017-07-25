package com.ylife.chinapost.boss.controller;

import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsProduct;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/GoodsInfoAdmin")
public class GoodsInfoController  {

	@Resource
	private GoodsProductService goodsProductService;
	@Resource
	private GoodsInfoService goodsInfoService;
	@Resource
	private ThirdPlatformBossService thirdPlatformBossService;

	/**
	* GoodsInfoEntity List页面
	* @author henry
	* @date 2017-06-30 16:48:34
	*
	* */
	@RequestMapping(value = "/ListPage")
	public ModelAndView goodsInfoListPage(GoodsProduct goodsProduct,
										  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
										  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		String auditStatus = goodsProduct.getAuditStatus();
		if(StringUtil.isBlank(auditStatus)){
			auditStatus = null;
		}
		Long thirdId = null;
		if(goodsProduct.getThirdId() != null && !"".equals(goodsProduct.getThirdId()+"")){
			thirdId = goodsProduct.getThirdId();
		}
		String goodsInfoAdded = goodsProduct.getGoodsInfoAdded();
		if(StringUtil.isBlank(goodsInfoAdded)){
			goodsInfoAdded = null;
		}
		Page<GoodsManagerResult> pageBean = goodsInfoService.getGoods(goodsProduct.getGoodsInfoName(),goodsProduct.getGoodsInfoItemNo(),goodsInfoAdded,null,null,thirdId,2,null,null,auditStatus,new Pageable(page,size));
		return new ModelAndView(returnPath("PlantGoodsList")).addObject("goodsProduct",goodsProduct)
				.addObject("pageBean",pageBean).addObject("platformList",thirdPlatformBossService.queryAllThirdPlatform(new ThirdPlatformEntity())).addObject("VERSION", Constants.JS_VERSION);
	}

	/**
	* 审核
	* @author henry
	* @date 2017-06-30 16:48:34
	*
	* */
	@RequestMapping(value = "/batchUpdateAuditStatus")
	public ModelAndView batchUpdateAuditStatus(HttpServletRequest request) {
        goodsProductService.batchUpdateAuditStatus(request);
		return new ModelAndView("redirect:ListPage?menuParentId=1");
	}

	/**
	* 导出GoodsInfoEntity
	* @author henry
	* @date 2017-06-30 16:48:34
	* @return
	*/
/*	@RequestMapping(value = "/exportGoodsInfoExcel")
	public void exportGoodsInfoExcel(GoodsInfoEntity goodsInfoEntity,String ids,HttpServletResponse response) {
		goodsInfoBossService.exportGoodsInfo(goodsInfoEntity, ids, response);
	}*/
	/**
	* 定义
	*
	*/
	private final String MODULE_PATH = "/thirdPlatform/";

	//返回路径
	private String returnPath(String jspName) {
		return MODULE_PATH + jspName;
	}
}
