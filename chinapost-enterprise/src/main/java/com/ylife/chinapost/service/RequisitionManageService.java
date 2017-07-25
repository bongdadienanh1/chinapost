package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/19.
 * 请款单据。
 */
public interface RequisitionManageService {

    /**
     * 获取我的请求。
     */
    Page<EnterpriseRequisition> getMyRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable);

    /**
     * 我的待办。
     */
    Page<EnterpriseRequisition> getNotHandleRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable);

    /**
     * 我的已办。
     */
    Page<EnterpriseRequisition> getHandledRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable);

    /**
     * 通过。
     */
    void passRequisition(long requisitionId);


    /**
     * 拒绝。
     */
    void denyRequisition(long requisitionId);

    /**
     * 支付。
     */
    void payRequisition(long requisitionId, BigDecimal payFee, String paykey);

}
