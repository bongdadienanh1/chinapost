package com.ylife.payorder.service.impl;


import com.ylife.data.page.Pageable;
import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.payorder.entity.PayOrderEntityVo;
import com.ylife.payorder.mapper.PayOrderMapper;
import com.ylife.payorder.service.PayOrderService;
import com.ylife.utils.MapUtil;
import com.ylife.utils.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PayOrderServiceImpl implements PayOrderService {
	/**
	* 日志
	* */
	public static final MyLogger LOGGER = new MyLogger(PayOrderServiceImpl.class);

	@Resource
	private PayOrderMapper payOrderMapper;

	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#queryAllPayOrderVo(PayOrderEntity payOrderEntity)
	* @param payOrderEntity
	* @return List<PayOrderEntity>
    */
    public List<PayOrderEntityVo> queryAllPayOrderVo(PayOrderEntity payOrderEntity) {
    	return payOrderMapper.queryAllPayOrderVo(payOrderEntity);
    }
    /**
    *
    * @author henry
    * @date 2017-07-05 16:59:50
    * @see PayOrderService#queryAllPayOrderVo(PayOrderEntity payOrderEntity)
    * @param payOrderEntity
    * @return List<PayOrderEntity>
	*/
	public List<PayOrderEntityVo> queryListForPagePayOrderVo(PayOrderEntity payOrderEntity, Pageable pageable) {
		Map<String,Object> paramMap = MapUtil.getParamsMap(payOrderEntity);
		if(pageable != null){
			paramMap.put("startRowNum", pageable.getIndex());
			paramMap.put("pageSize", pageable.getLength());
		}
		return payOrderMapper.queryListForPagePayOrderVo(paramMap);
	}
	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#queryCountPayOrderVo(PayOrderEntity payOrderEntity)
	* @param payOrderEntity
	* @return int
	*/
	public int queryCountPayOrderVo(PayOrderEntity payOrderEntity) {
		return payOrderMapper.queryCountPayOrderVo(payOrderEntity);
	}

	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#queryById(Long id)
	* @param id
	* @return
	*/
	public PayOrderEntity queryById(Long id) {
		return payOrderMapper.queryById(id);
	}
		
	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#queryByIds(List<String> ids)
	* @param ids
	* @return List<PayOrderEntity>
	*/
	public List<PayOrderEntity> queryByIds(List<String> ids) {
		return payOrderMapper.queryByIds(ids);
	}

	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#addPayOrder(PayOrderEntity payOrderEntity)
	* @param payOrderEntity
	* @return int
	*/
	public int addPayOrder(PayOrderEntity payOrderEntity) {
		int i = 0;
		try{
			i = payOrderMapper.addPayOrder(payOrderEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}

	/**
	*
	* @author henry
	* @date 2017-07-05 16:59:50
	* @see PayOrderService#updatePayOrder(PayOrderEntity payOrderEntity)
	* @param payOrderEntity
	* @return int
	*/
	public int updatePayOrder(PayOrderEntity payOrderEntity) {
		int i = 0;
		try{
			i =  payOrderMapper.updatePayOrder(payOrderEntity);
		}catch(Exception e){
			LOGGER.error(e.getMessage());
		}
		return i;
	}

    /**
     * 查询所有PayOrderEntity
     *
     * @param payOrderEntity
     * @return
     */
    @Override
    public List<PayOrderEntity> queryAllPayOrder(PayOrderEntity payOrderEntity) {
        return payOrderMapper.queryAllPayOrder(payOrderEntity);
    }
}

