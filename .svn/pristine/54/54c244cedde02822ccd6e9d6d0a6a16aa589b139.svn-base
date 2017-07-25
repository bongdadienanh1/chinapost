package com.ylife.chinapost.mobile.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderReason;

import java.util.Date;

/**
 * Created by InThEnd on 2016/5/10.
 * 退单服务
 */
public interface ChargeBackService {

    /**
     * 获取会员账号所有退单
     *
     * @param customerId
     * @param pageable
     */
    Page<CreditOrder> getCreditOrdersByCustomerId(long customerId, Date start, Date end, Pageable pageable);


    /**
     * 申请退货
     *
     * @param orderNo
     * @param reason
     * @param credential
     */
    void applyCredit(long orderNo, CreditOrderReason reason, String credential, String remark);

    /**
     * 申请退款
     *
     * @param orderNo
     * @param reason
     * @param credential
     */
    void applyRefund(long orderNo, CreditOrderReason reason, String credential, String remark);

    /**
     * 填写物流单号
     *
     * @param creditOrderNo 退单号
     * @param logisticName  物流公司名称
     * @param logisticNo    物流单号
     */
    void fillLogistic(Long creditOrderNo, String logisticName, String logisticNo);


}
