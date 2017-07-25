package com.ylife.chinapost.boss.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.BackOrderBossService;
import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrderStatus;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/BackOrderAdmin")
public class BackOrderController  {

	@Resource
	private BackOrderBossService backOrderBossService;
	@Resource
	private ThirdPlatformBossService thirdPlatformBossService;

	/**
	* BackOrderEntity List页面
	* @author henry
	* @date 2017-07-02 17:33:16
	*
	* */
	@RequestMapping(value = "/ListPage")
	public ModelAndView backOrderListPage(String backOrderNo,String shippingMobile,String orderCode,
										  CreditOrderStatus status, String start, String end,String thirdId,
										  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
										  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return new ModelAndView(returnPath("thirdOrderBackList"))
				.addObject("backOrderNo",backOrderNo).addObject("shippingMobile",shippingMobile).addObject("orderCode",orderCode)
				.addObject("status",status).addObject("start",start).addObject("end",end).addObject("thirdId",thirdId)
				.addObject("pageBean",backOrderBossService.queryListForPageBackOrder(backOrderNo,shippingMobile,orderCode,status,start,end,thirdId,new Pageable(page,size)))
				.addObject("platformList",thirdPlatformBossService.queryAllThirdPlatform(new ThirdPlatformEntity())).addObject("VERSION", Constants.JS_VERSION);
	}

	/**
	* 查询BackOrderEntity
	* @author henry
	* @date 2017-07-02 17:33:16
	* */
	@RequestMapping(value = "/queryById", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findBackOrderData(String backOrderCode) {
		return JSON.toJSONString(backOrderBossService.queryById(Long.parseLong(backOrderCode)));
	}

	/**
	* 导出BackOrderEntity
	* @author henry
	* @date 2017-07-02 17:33:16
	* @return
	*/
	@RequestMapping(value = "/exportBackOrderExcel")
	public void exportBackOrderExcel(String backOrderNo,String shippingMobile,String orderCode,String thirdId,
									 CreditOrderStatus status, String start, String end,HttpServletResponse response) throws IOException{
		backOrderBossService.exportBackOrder(backOrderNo,shippingMobile,orderCode,status,start,end,thirdId,null, response);
	}
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
