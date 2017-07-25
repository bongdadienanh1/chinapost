/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.order.mapper;

import com.ylife.goods.model.GoodsProductVo;
import com.ylife.order.model.BackOrder;
import com.ylife.order.model.BackOrderGeneral;
import com.ylife.order.model.Order;

import java.util.List;
import java.util.Map;

/**
 * @author ggn 退单信息接口
 * @see com.ylife.order.mapper.CreditOrderMapper
 */
@Deprecated
public interface BackOrderMapper {
    /**
     * 根据店铺ID差选店铺下面的退单数据的数量
     *
     * @param map
     * @return
     */

    int searchBackOrderThirdCount(Map<String, Object> map);

    /**
     * 修改退单列表customerId
     *
     * @param map
     * @return
     */
    int updateBackOrderCustomerIdByCId(Map<String, Object> map);

    /**
     * 根据店铺信息查询当前店铺下面的退单信息
     *
     * @param map
     * @return
     */
    List<Object> searchBackOrderLisThird(Map<String, Object> map);

    /**
     * 查询退单明细
     *
     * @param backOrderId
     * @return BackOrder
     */
    BackOrder selectBackOrderDetail_new(Long backOrderId);

    /**
     * 删除订单里面退货成功的 货品
     *
     * @param goodsInfoId 货品Id
     * @return
     */
    int deleteBackGoodsById(Long goodsInfoId);

    /**
     * 根据Id获取单个商品信息
     *
     * @param goodsInfoId
     * @return
     */
    GoodsProductVo selectGoodsById(Long goodsInfoId);

    /**
     * 获取退单数据总条数
     *
     * @param paramMap
     * @return
     */
    int searchBackOrderCountnew(Map<String, Object> paramMap);

    /**
     * 查询所有退单
     *
     * @param paramMap
     * @return
     */
    List<Object> searchBackOrderListnew(Map<String, Object> paramMap);

    /**
     * 根据退单ID 获取退单物流信息
     *
     * @param backOrderId
     * @return
     */
    BackOrderGeneral selectGeneralByBackOrderId(Long backOrderId);

    /**
     * 查询退单列表
     *
     * @param paramMap
     * @return int
     */
    int searchBackOrderCount(Map<String, Object> paramMap);

    /**
     * 查询订单总数
     *
     * @param paramMap
     * @return List
     */
    List<Object> searchBackOrderList(Map<String, Object> paramMap);

    /**
     * 查询退单明细
     *
     * @param backOrderId
     * @return BackOrder
     */
    BackOrder selectBackOrderDetail(Long backOrderId);

    /**
     * 按主键修改订单审核
     *
     * @param backOrder
     * @return
     */
    int updateByPrimaryKeySelective(BackOrder backOrder);

    /**
     * 按主键修改第三方订单审核
     *
     * @param backOrder
     * @return
     */
    int updateByThirdId(BackOrder backOrder);

    /**
     * 查询退单的数量
     *
     * @param map 封装参数 {@link java.util.Map}
     * @return 查询到的退单数量 {@link Integer}
     */
    int queryBackOrderCount(Map<String, Object> map);

    /**
     * 订单号查询订单那信息
     *
     * @return
     */
    Order selectOrderOne(String orderCode);

    /**
     * 更改交易记录(Order)的状态
     *
     * @param order
     * @return
     */
    int updateOrder(Order order);

    /**
     * 根据ID获取退单对象
     *
     * @param backOrderId
     * @return
     */
    BackOrder selectbackOrderOne(Long backOrderId);

    /**
     * 查询退单的数量（已买）
     *
     * @param map 封装参数 {@link java.util.Map}
     * @return 查询到的退单数量 {@link Integer}
     */
    int queryBackOrderCountBuy(Map<String, Object> map);

    /**
     * 插入退单信息
     *
     * @param backOrder
     * @return
     */
    int insertSelective(BackOrder backOrder);

    /**
     * 获取退单信息和退货/退款标志根据订单编号
     *
     * @param map
     * @return
     */
    BackOrder queryBackOrderByOrderCodeAndIsback(Map<String, Object> map);

    /**
     * 获取退单信息根据订单编号
     *
     * @return
     */
    BackOrder queryBackOrderByOrderCode(String orderNo);

    /**
     * 获取退单信息根据订单编号根据退单id
     *
     * @param backOrderId
     * @return
     */
    BackOrder selectBackOrderByBackOrderId(Long backOrderId);

    /**
     * 根据退单号查询物流
     *
     * @param backOrderId
     * @return
     */
    BackOrderGeneral queryBackOrderGeneral(Long backOrderId);

    /**
     * 验证是否已经退款退货
     *
     * @param map
     */
    int validateBackOrder(Map<String, Object> map);

    /**
     * 导出退单
     *
     * @param paramMap
     */
    List<BackOrder> queryAllBackOrderList(Map<String, Object> paramMap);

    /**
     * 查询退单
     * @return
     */
    List<BackOrder> selectBackOrderListByCondition();


    /**
     * 根据退单号批量修改退单状态
     * @param list
     * @return
     */
    int updateStatusByBackOrderCode(List<com.ylife.client.bean.BackOrder> list);
}
