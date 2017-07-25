/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.order.service;

import com.ylife.data.page.PageBean;
import com.ylife.order.model.BackOrder;
import com.ylife.order.model.BackOrderGeneral;

import java.io.IOException;
import java.util.List;

/**
 * @author ggn 退单信息
 */
@Deprecated
public interface BackOrderService {

    /**
     * 退单信息列表
     *
     * @param pb
     * @param bkOrder
     * @return PageBean
     */
    PageBean backOrderList(PageBean pb, BackOrder bkOrder, String startTime,
                           String endTime);

    /**
     * 退单信息列表
     *
     * @param pb
     * @param bkOrder
     * @return PageBean
     */
    PageBean backOrderListOrder(PageBean pb, BackOrder bkOrder, String startTime,
                                String endTime);

    /**
     * 修改退单列表newcustomerId根据customerId
     *
     * @param newCustomerId 新的customerId
     * @param customerId    要修改的会员ID
     * @return
     */
    int updateBackOrderCustomerIdByCId(Long newCustomerId, Long customerId);

    /**
     * 查询BackOrderdetail
     *
     * @param backOrderId
     * @return BackOrder
     */
    BackOrder detail(Long backOrderId);

    /**
     * 查询退单信息根据退单编号（包括订单信息）
     *
     * @param backOrderId
     * @return BackOrder
     */
    BackOrder selectBackOrderByBackOrderId(Long backOrderId);

    /**
     * 退单详细信息
     *
     * @param backOrderId
     * @return
     */
    BackOrder backdetail(Long backOrderId, Long orderId);

    /**
     * 当用户退单,后台同意后将把订单完成的积分奖励扣除掉 会员积分列表将增加一条扣除积分信息 会员消费列表将增加一条订单退掉的信息.
     *
     * @param orderId
     * @return
     */
    int reducePointOrderBack(Long orderId);

    /**
     * 修改退单审核状态
     *
     * @param backId
     * @param backCheck
     * @return
     */
    int modifyBackOrderByCheck(Long backId, String backCheck);

    /**
     * 修改退单信息
     *
     * @param backOrder
     * @return
     */
    int modifyBackBeanCheck(BackOrder backOrder);

    /**
     * 修改第三方后台退单信息
     *
     * @param backOrder
     * @return
     */
    int modifyThirdBackBeanCheck(BackOrder backOrder);

    /**
     * 查询第三方退单数量
     *
     * @param thirdId 第三方ID {@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBackOrderCountBuy(Long thirdId);

    /**
     * 查询第三方退单数量(已买)
     *
     * @param customerId customerID{@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBackOrderCount(Long customerId);

    /**
     * 插入退单信息
     *
     * @param backOrder
     * @return
     */
    int insertBackOrderInfo(BackOrder backOrder);

    /**
     * 根据订单编号查找退单信息
     *
     * @param orderNo
     * @return
     */
    BackOrder queryBackOrderByOrderCode(String orderNo);


    /**
     * 根据订单编号和退货/退款标志查找退单信息
     *
     * @param orderNo
     * @param isBack
     * @return
     */
    BackOrder queryBackOrderByOrderCodeAndIsback(String orderNo, String isBack);

    /**
     * 根据退单号获取物流号
     *
     * @param backOrderId
     * @return
     */
    BackOrderGeneral queryBackOrderGeneral(Long backOrderId);

    /**
     * 退款成功后退回库存
     *
     * @param orderId
     * @param backOrderId
     * @return
     */
    Integer addStockOrderBack(Long orderId, Long backOrderId);

    /**
     * 验证是否已经退款退货
     *
     * @param orderCode
     * @param backCheck
     */
    int validateBackOrder(String orderCode, String backCheck);

    /**
     * 导出退单
     *
     * @param backOrder
     */
    List<BackOrder> queryAllBackOrderList(BackOrder backOrder);

    /**
     * 同步退单数据（用户提交退单完成调用）
     *
     * @param orderCode 订单号
     */
    void opensubmitBackOrder(String orderCode) throws Exception;

    /**
     * 同步退单状态
     */
    void updateBackOrderStatus() throws IOException;
}
