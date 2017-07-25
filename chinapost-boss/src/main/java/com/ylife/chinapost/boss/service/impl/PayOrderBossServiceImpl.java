package com.ylife.chinapost.boss.service.impl;


import com.ylife.chinapost.boss.service.PayOrderBossService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.payorder.entity.PayOrderEntityVo;
import com.ylife.utils.ExcelUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.ylife.payorder.service.PayOrderService;
import com.ylife.payorder.entity.PayOrderEntity;

@Service(value="payOrderBossService")
public class PayOrderBossServiceImpl implements PayOrderBossService {

	@Resource
	private PayOrderService payOrderService;


	/**
	*
	* @author  henry
	* @date 2017-07-05 16:59:50
	* @see com.ylife.chinapost.boss.service.PayOrderBossService#queryAllPayOrder(PayOrderEntity payOrderEntity)
	* @param payOrderEntity
	* @return List
	*/
	public List<PayOrderEntityVo> queryAllPayOrder(PayOrderEntity payOrderEntity) {
		return payOrderService.queryAllPayOrderVo(payOrderEntity);
	}
	/**
	*
	* @author  henry
	* @date 2017-07-05 16:59:50
	* @see com.ylife.chinapost.boss.service.PayOrderBossService#queryListForPagePayOrder(PayOrderEntity payOrderEntity, Pageable pageable)
	* @param payOrderEntity
	* @param pageable
	* @return PageBean
	*/
	public Page<PayOrderEntityVo> queryListForPagePayOrder(PayOrderEntity payOrderEntity, Pageable pageable) {
		List<PayOrderEntityVo> list = payOrderService.queryListForPagePayOrderVo(payOrderEntity,pageable);
		int totalElements = payOrderService.queryCountPayOrderVo(payOrderEntity);
		return new PageImpl<PayOrderEntityVo>(pageable, totalElements, list);
	}

    /**
    *
    * @author  henry
    * @date 2017-07-05 16:59:50
    * @see com.ylife.chinapost.boss.service.PayOrderBossService#exportPayOrder(PayOrderEntity payOrderEntity,String ids, HttpServletResponse response)
    * @param payOrderEntity
    * @param ids
    * @param response
    */
	public void exportPayOrder(PayOrderEntity payOrderEntity,String ids, HttpServletResponse response) throws IOException{
        List<Object> list = new ArrayList<Object>();
		list.addAll(payOrderService.queryAllPayOrderVo(payOrderEntity));
        String[] titleTag = {"订单编号","订单状态","邮豆金额(邮豆)","下单时间","供应商","网点","网点结算价(元)"};
        String[] fieldsArr = {"orderCode","orderStatus","price","orderCreateTime","thirdName","enterpriseName","settlePrice"};
		ExcelUtil.createReport(list,"对账表",titleTag,fieldsArr,"对账表",response);
	}
}

