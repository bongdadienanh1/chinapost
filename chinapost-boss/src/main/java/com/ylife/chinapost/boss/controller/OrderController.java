package com.ylife.chinapost.boss.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.OrderBossService;
import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.Order;
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
@RequestMapping(value = "/OrderAdmin")
public class OrderController  {

	@Resource
	private OrderBossService orderBossService;
	@Resource
	private ThirdPlatformBossService thirdPlatformBossService;

	/**
	* OrderEntity List页面
	* @author henry
	* @date 2017-07-01 15:38:16
	*
	* */
	@RequestMapping(value = "/ListPage")
	public ModelAndView orderListPage(Order order,
									  String createStart,String createEnd, String payStart,String payEnd,
									  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
									  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return new ModelAndView(returnPath("thirdOrderList"))
				.addObject("order",order).addObject("createStart",createStart).addObject("createEnd",createEnd)
				.addObject("payStart",payStart).addObject("payEnd",payEnd)
				.addObject("pageBean",orderBossService.queryListForPageOrder(order,new Pageable(page,size),createStart,createEnd,payStart,payEnd))
				.addObject("platformList",thirdPlatformBossService.queryAllThirdPlatform(new ThirdPlatformEntity())).addObject("VERSION", Constants.JS_VERSION);
	}

	/**
	* 查询OrderEntity
	* @author henry
	* @date 2017-07-01 15:38:16
	* */
	@RequestMapping(value = "/queryById", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findOrderData(String orderId) {
		return JSON.toJSONString(orderBossService.queryById(Long.parseLong(orderId)));
	}


	/**
	 * 导出订单
	 * @param createStart
	 * @param createEnd
	 * @param payStart
	 * @param payEnd
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/exportOrderExcel")
	public void exportOrderExcel(Order order,
								 String createStart,String createEnd, String payStart,String payEnd,
								 HttpServletResponse response
	) throws IOException {
		orderBossService.exportOrderExcel(order,null,createStart,createEnd,payStart,payEnd,response);
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
