package com.ylife.chinapost.boss.service;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;

/**
* ThirdPlatformBossService接口
* @author henry
* @date 2017-06-30 09:14:43
* @version <b>1.0.0</b>
*/
public interface ThirdPlatformBossService {

	/**
	* 查询全部ThirdPlatformEntity 不带分页
	* @author henry
	* @date 2017-06-30 09:14:43
	* @param thirdPlatformEntity
	* @return List
	*/
	List<ThirdPlatformEntity> queryAllThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

	/**
	* 查询全部ThirdPlatformEntity带分页
	* @author henry
	* @date 2017-06-30 09:14:43
	* @param thirdPlatformEntity
	* @param pb
	* @return String
	*/
	Page<ThirdPlatformEntity> queryListForPageThirdPlatform(ThirdPlatformEntity thirdPlatformEntity, Pageable pb);

	/**
	* 按主键查询ThirdPlatformEntity
	* @author henry
	* @date 2017-06-30 09:14:43
	* @param id
	* @return  ThirdPlatformEntity
	*/
	ThirdPlatformEntity queryById(Long id);

	/**
	* 添加ThirdPlatformEntity
	* @author henry
	* @date 2017-06-30 09:14:43
	* @param thirdPlatformEntity
	* @return boolean
	*/
	boolean addThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

	/**
	* 更新ThirdPlatformEntity
	* @author henry
	* @date 2017-06-30 09:14:43
	* @param thirdPlatformEntity
	* @return boolean
	*/
	boolean updateThirdPlatform(ThirdPlatformEntity thirdPlatformEntity);

	/**
	 * 检查第三方生成店家是否已存在
	 * @author henry
	 * @date 2017-06-30 09:14:43
	 * @param thirdPlatformEntity
	 * @return
	 */
	String checkThirdUser(ThirdPlatformEntity thirdPlatformEntity);

	/**
	*
	* @Title: exportThirdPlatform
	* @Description: 导出数据
	* @param thirdPlatformEntity
	* @param ids
	* @param response
	*/
	void exportThirdPlatform(ThirdPlatformEntity thirdPlatformEntity, String ids, HttpServletResponse response);

}
