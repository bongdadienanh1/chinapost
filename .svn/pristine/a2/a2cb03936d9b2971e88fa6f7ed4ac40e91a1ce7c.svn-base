package com.ylife.chinapost.boss.controller;

import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.PayOrderBossService;
import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping(value = "/PayOrderAdmin")
public class PayOrderController  {

	@Resource(name="payOrderBossService")
	private PayOrderBossService payOrderBossService;
	@Resource
	private ThirdPlatformBossService thirdPlatformBossService;
	@Resource
	private EnterpriseInfoService enterpriseInfoService;

	/**
	* PayOrderEntity List页面
	* @author henry
	* @date 2017-07-05 16:59:50
	*
	* */
	@RequestMapping(value = "/reconciliationList")
	public ModelAndView payOrderListPage(PayOrderEntity payOrderEntity,
										 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
										 @RequestParam(value = "size", required = false, defaultValue = "10") int size) {

		return new ModelAndView(returnPath("reconciliationList"))
				.addObject("pageBean",payOrderBossService.queryListForPagePayOrder(payOrderEntity,new Pageable(page,size)))
				.addObject("payOrderEntity",payOrderEntity).addObject("platformList",thirdPlatformBossService.queryAllThirdPlatform(new ThirdPlatformEntity()))
				.addObject("enterpriseInfoList",enterpriseInfoService.getAllOrganization()).addObject("VERSION", Constants.JS_VERSION);
	}
	/**
	* 导出PayOrderEntity
	* @author henry
	* @date 2017-07-05 16:59:50
	* @return
	*/
	@RequestMapping(value = "/exportPayOrderExcel")
	public void exportPayOrderExcel(PayOrderEntity payOrderEntity,String ids,HttpServletResponse response)  throws IOException {
		payOrderBossService.exportPayOrder(payOrderEntity, ids, response);
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
