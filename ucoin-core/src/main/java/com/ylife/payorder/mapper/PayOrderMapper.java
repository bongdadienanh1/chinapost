package com.ylife.payorder.mapper;

import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.payorder.entity.PayOrderEntityVo;

import java.util.List;
import java.util.Map;

public interface PayOrderMapper{
    /**
    * 查询全部PayOrderEntity 不带分页
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param payOrderEntity
    * @return List<PayOrderEntity>
    */
    List<PayOrderEntityVo> queryAllPayOrderVo(PayOrderEntity payOrderEntity);

    /**
    * 查询全部PayOrderEntity 带分页
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param paramMap
    * @return List<Object>
    */
    List<PayOrderEntityVo> queryListForPagePayOrderVo(Map<String, Object> paramMap);

    /**
    * 查询总数PayOrderEntity
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param payOrderEntity
    * @return
    */
    int queryCountPayOrderVo(PayOrderEntity payOrderEntity);

    /**
    * 按主键查询PayOrderEntity
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param id
    * @return  PayOrderEntity
    */
    PayOrderEntity queryById(Long id);

    /**
    *
    * @Title: queryByIds
    * @Description: 根据主键id批量查询数据
    * @param ids
    * @return
    * @throws
    */
    List<PayOrderEntity> queryByIds(List<String> ids);

    /**
    * 插入PayOrderEntity
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param payOrderEntity
    * @return int
    */
    int addPayOrder(PayOrderEntity payOrderEntity);

    /**
    * 更新PayOrderEntity
    * @author henry
    * @date 2017-07-05 16:59:50
    * @param payOrderEntity
    * @return int
    */
    int updatePayOrder(PayOrderEntity payOrderEntity);


    /**
     * 查询所有的PayOrderEntity
     * @param payOrderEntity
     * @return
     */
    List<PayOrderEntity> queryAllPayOrder(PayOrderEntity payOrderEntity);
}