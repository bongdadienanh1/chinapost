package com.ylife.chinapost.boss.service;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.payorder.entity.PayOrderEntityVo;

/**
* PayOrderBossService接口
* @author henry
* @date 2017-07-05 16:59:50
* @version <b>1.0.0</b>
*/
public interface PayOrderBossService {

	/**
	* 查询全部PayOrderEntity 不带分页
	* @author henry
	* @date 2017-07-05 16:59:50
	* @param payOrderEntity
	* @return List
	*/
	List<PayOrderEntityVo> queryAllPayOrder(PayOrderEntity payOrderEntity);

	/**
	* 查询全部PayOrderEntity带分页
	* @author henry
	* @date 2017-07-05 16:59:50
	* @param payOrderEntity
	* @param pageable
	* @return String
	*/
	Page<PayOrderEntityVo> queryListForPagePayOrder(PayOrderEntity payOrderEntity, Pageable pageable);

	/**
	*
	* @Title: exportPayOrder
	* @Description: 导出数据
	* @param payOrderEntity
	* @param ids
	* @param response
	*/
	void exportPayOrder(PayOrderEntity payOrderEntity, String ids, HttpServletResponse response) throws IOException;

}

