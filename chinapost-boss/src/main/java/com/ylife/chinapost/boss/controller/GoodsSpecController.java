package com.ylife.chinapost.boss.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsSpec;
import com.ylife.goods.service.GoodsSpecDetailService;
import com.ylife.goods.service.GoodsSpecService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping(value = "/GoodsSpecAdmin")
public class GoodsSpecController  {

	@Resource
	private GoodsSpecService goodsSpecService;

	@Resource
	private GoodsSpecDetailService goodsSpecDetailService;

	/**
	* GoodsSpecEntity List页面
	* @author henry
	* @date 2017-06-30 18:46:32
	*
	* */
	@RequestMapping(value = "/ListPage")
	public ModelAndView goodsSpecListPage(GoodsSpec goodsSpec,
										  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
										  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return new ModelAndView(returnPath("goods_spec"))
				.addObject("pageBean",goodsSpecService.queryListForPageGoodsSpec(goodsSpec, new Pageable(page,size)))
				.addObject("goodsSpec",goodsSpec).addObject("VERSION", Constants.JS_VERSION);
	}

	/**
	* 查询GoodsSpecEntity
	* @author henry
	* @date 2017-06-30 18:46:32
	* */
	@RequestMapping(value = "/queryById", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findGoodsSpecData(String specId) {
		return JSON.toJSONString(goodsSpecService.queryBySpecPrimaryKey(Long.parseLong(specId)));
	}

	/**
	* 保存或更新GoodsSpec
	* @author henry
	* @date 2017-06-30 18:46:32
	*
	* */
	@RequestMapping(value = "/saveGoodsSpec", produces = "application/text;charset=utf-8")
	public ModelAndView saveGoodsSpecData(GoodsSpec goodsSpec) {
		if(goodsSpec!=null){
			if(goodsSpec.getSpecId()!=null || (goodsSpec.getSpecIds() !=null && goodsSpec.getSpecIds().length > 0)){
				goodsSpecService.updateGoodsSpec(goodsSpec,"qmbbc");
			}else{
				goodsSpec.setGoodsInfoType("1");
				goodsSpecService.saveGoodsSpec(goodsSpec,"qmbbc");
			}
		}
		return new ModelAndView("redirect:ListPage?menuParentId=1");
	}

    /**
     * 检查数据是否存在
     * @param goodsSpec
     * @return
     */
    @RequestMapping(value = "/checkGoodsSpecName", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String checkGoodsSpecName(GoodsSpec goodsSpec){
        return goodsSpecService.checkSpecName(goodsSpec.getSpecName()) == true?"200":"该规格名称已被使用！！！";
    }
    /**
     * 根据商品规格id检查该规格下是否存在商品
     * @param specIds
     * @return
     */
    @RequestMapping(value = "/countGoodsBySpecId", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String countGoodsBySpecId(String specIds){
        return goodsSpecService.countGoodsBySpecId(specIds.split(",")) == 0 ? "200":"规格下有关联货品不能删除！！！";
    }

	/**
	 * 根据商品规格值id检查该规格值下是否被使用
	 * @param specDetailIds
	 * @return
	 */

	@RequestMapping(value = "/countGoodsBySpecDetailId", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String countGoodsBySpecDetailId(String specDetailIds){
		return goodsSpecDetailService.countGoodsBySpecDetailId(specDetailIds.split(",")) == 0 ? "200":"规格值下有关联货品不能删除！！！";
	}


	/**
	 * 规格值管理、修改规格值
	 * @param request
	 * @param goodsSpec
	 * @return
	 */
	@RequestMapping(value = "updateSpecDetail")
	public ModelAndView updateSpecDetail(HttpServletRequest request,GoodsSpec goodsSpec){
		// 获取规格id并保存
		String[] specDetailIds = request.getParameterValues("specDetailId");
		// 获取规格删除标记并保存
		String[] specDetailDelflag = request.getParameterValues("specDetailDelflag");
		// 获取规格名称并保存
		String[] specDetailName = request.getParameterValues("specDetailName");
		// 获取规格排序并保存
		String[] specDetailSort = request.getParameterValues("specDetailSort");

		this.goodsSpecService.updateGoodsSpec(goodsSpec, goodsSpecService.changeSpecDetail(specDetailIds, specDetailDelflag, specDetailName, null, null, specDetailSort), "qmbbc");

		return new ModelAndView("redirect:ListPage?menuParentId=1");
	}





	/**
	* 导出GoodsSpecEntity
	* @author henry
	* @date 2017-06-30 18:46:32
	* @return
	*/
	/*@RequestMapping(value = "/exportGoodsSpecExcel")
	public void exportGoodsSpecExcel(GoodsSpec goodsSpec,String ids,HttpServletResponse response) {
		goodsSpecService.exportGoodsSpec(goodsSpecEntity, ids, response);
	}*/
	/**
	* 定义
	*
	*/
	private final String MODULE_PATH = "/";

	//返回路径
	private String returnPath(String jspName) {
		return MODULE_PATH + jspName;
	}
}
