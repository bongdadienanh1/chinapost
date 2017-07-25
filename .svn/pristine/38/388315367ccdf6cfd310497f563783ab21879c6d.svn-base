package com.ylife.wealth.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/12.
 * 申请单服务。
 */
public interface EnterpriseRequisitionService {

    /**
     * 发起申请。
     *
     * @param enterpriseId 发起申请的企业ID
     * @param amount       申请的U宝数量
     * @param remark       备注
     */
    void launchRequisition(long enterpriseId, BigDecimal amount, String remark);

    /**
     * 通过申请。
     *
     * @param requisitionId 申请单ID
     */
    void passRequisition(long requisitionId);

    /**
     * 拒绝申请。
     *
     * @param requisitionId 申请单ID
     */
    void denyRequisition(long requisitionId);

    /**
     * 支付申请单。
     *
     * @param requisitionId 申请单ID
     * @param managerId     操作人ID
     */
    void payRequisition(long requisitionId, BigDecimal payFee, long managerId);

    /**
     * 获取企业的申请记录(时间倒序排列)。
     *
     * @param enterpriseId 发起申请的企业ID
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, Date start, Date end, Pageable pageable);

    /**
     * 获取企业的申请记录(时间倒序排列)。
     *
     * @param enterpriseId 发起申请的企业ID
     * @param status       申请状态
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, RequisitionStatus status, Date start, Date end, Pageable pageable);

    /**
     * 获取企业的申请记录(时间倒序排列)。
     *
     * @param enterpriseId 发起申请的企业ID
     * @param statuses     申请状态
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, RequisitionStatus[] statuses, Date start, Date end, Pageable pageable);


    /**
     * 获取企业收到的（下级企业）申请记录(时间倒序排列)。
     *
     * @param enterpriseId 收到申请的企业ID
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, Date start, Date end, Pageable pageable);

    /**
     * 获取企业收到的（下级企业）申请记录(时间倒序排列)。
     *
     * @param enterpriseId 收到申请的企业ID
     * @param status       申请状态
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, RequisitionStatus status, Date start, Date end, Pageable pageable);

    /**
     * 获取企业收到的（下级企业）申请记录(时间倒序排列)。
     *
     * @param enterpriseId 收到申请的企业ID
     * @param statuses     申请状态
     * @param start        开始日期
     * @param end          结束日期
     * @param pageable     分页信息
     */
    Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, RequisitionStatus[] statuses, Date start, Date end, Pageable pageable);


    /**
     * 根据单号获取申请记录。
     *
     * @param code 申请单号
     */
    EnterpriseRequisition getEnterpriseRequisition(long code);

}
