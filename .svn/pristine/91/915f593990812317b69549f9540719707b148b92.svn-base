package com.ylife.chinapost.boss.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.chinapost.boss.service.ThirdPlatformBossService;
import com.ylife.data.page.Pageable;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/ThirdPlatformAdmin")
public class ThirdPlatformController  {

	@Resource(name="thirdPlatformBossService")
	private ThirdPlatformBossService thirdPlatformBossService;

	@Resource(name = "thirdPlatformInterfaceService")
	private ThirdPlatformInterfaceService thirdPlatformInterfaceService;
	/**
	* ThirdPlatformEntity List页面
	* @author henry
	* @date 2017-06-30 09:14:43
	*
	* */
	@RequestMapping(value = "/ListPage")
	public ModelAndView thirdPlatformListPage(ThirdPlatformEntity thirdPlatformEntity,
											  @RequestParam(value = "page", required = false, defaultValue = "1") int page,
											  @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return new ModelAndView(returnPath("thirdPlatformList"))
				.addObject("pageBean",thirdPlatformBossService.queryListForPageThirdPlatform(thirdPlatformEntity,new Pageable(page,size)))
				.addObject("thirdPlatformEntity",thirdPlatformEntity).addObject("VERSION", Constants.JS_VERSION);
	}

	/**
	* 查询ThirdPlatformEntity
	* @author henry
	* @date 2017-06-30 09:14:43
	* */
	@RequestMapping(value = "/queryById", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findThirdPlatformData(String id) {
		return JSON.toJSONString(thirdPlatformBossService.queryById(Long.parseLong(id)));
	}

	/**
	* 保存或更新ThirdPlatform
	* @author henry
	* @date 2017-06-30 09:14:43
	*
	* */
	@RequestMapping(value = "/saveThirdPlatform")
	public ModelAndView saveThirdPlatformData(ThirdPlatformEntity thirdPlatformEntity) {
		if(thirdPlatformEntity!=null){
			if(thirdPlatformEntity.getId()!=null || (thirdPlatformEntity.getIds()!=null && thirdPlatformEntity.getIds().length > 0)){
				thirdPlatformBossService.updateThirdPlatform(thirdPlatformEntity);
			}else{
				thirdPlatformBossService.addThirdPlatform(thirdPlatformEntity);
			}
		}
		return new ModelAndView("redirect:ListPage?menuParentId=1");
	}

	@RequestMapping(value = "/checkThirdUser", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String checkThirdUser(ThirdPlatformEntity thirdPlatformEntity){
		return thirdPlatformBossService.checkThirdUser(thirdPlatformEntity);
	}

	/**
	* 导出ThirdPlatformEntity
	* @author henry
	* @date 2017-06-30 09:14:43
	* @return
	*/
	@RequestMapping(value = "/exportThirdPlatformExcel")
	public void exportThirdPlatformExcel(ThirdPlatformEntity thirdPlatformEntity,String ids,HttpServletResponse response) {
		thirdPlatformBossService.exportThirdPlatform(thirdPlatformEntity, ids, response);
	}

	/**
	 * 查询ThirdPlatformInterfaceEntity
	 * @author henry
	 * @date 2017-07-19 10:41:20
	 * */
	@RequestMapping(value = "/findThirdPlatformInterfaceData", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String findThirdPlatformInterfaceData(String thirdId) {
		return JSON.toJSONString(thirdPlatformInterfaceService.queryByThirdId(Long.parseLong(thirdId)));
	}

	/**
	 * 保存或更新ThirdPlatformInterface
	 * @author henry
	 * @date 2017-07-19 10:41:20
	 *
	 * */
	@RequestMapping(value = "/saveThirdPlatformInterface", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String saveThirdPlatformInterfaceData(ThirdPlatformInterfaceEntity thirdPlatformInterfaceEntity) {
		int result = 0;
		if(thirdPlatformInterfaceEntity!=null && thirdPlatformInterfaceEntity.getId()!=null){
			result = thirdPlatformInterfaceService.updateThirdPlatformInterface(thirdPlatformInterfaceEntity);
		}else {
			result = thirdPlatformInterfaceService.addThirdPlatformInterface(thirdPlatformInterfaceEntity);
		}
		return String.valueOf(result);
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
