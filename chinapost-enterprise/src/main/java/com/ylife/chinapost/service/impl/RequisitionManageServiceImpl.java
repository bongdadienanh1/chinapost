package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.RequisitionManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;
import com.ylife.wealth.service.EnterpriseRequisitionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/19.
 * RequisitionManageServiceImpl
 */
@Service
public class RequisitionManageServiceImpl implements RequisitionManageService {
    @Resource
    private EnterpriseRequisitionService enterpriseRequisitionService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseService enterpriseService;

    @Override
    public Page<EnterpriseRequisition> getMyRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (code != null) {
            return getOneRequisitionPage(enterpriseId, code, status, pageable);
        }
        if (status != null)
            return enterpriseRequisitionService.getEnterpriseRequisitions(enterpriseId, status, start, end, pageable);
        return enterpriseRequisitionService.getEnterpriseRequisitions(enterpriseId, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getNotHandleRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (code != null) {
            return getOneRequisitionPage(enterpriseId, code, status, pageable);
        }
        if (status != null)
            return enterpriseRequisitionService.getEnterpriseRecivedRequisitions(enterpriseId, status, start, end, pageable);
        RequisitionStatus[] statuses = {RequisitionStatus.APPLIED, RequisitionStatus.PASSED};
        return enterpriseRequisitionService.getEnterpriseRecivedRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getHandledRequisition(Long code, RequisitionStatus status, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (code != null) {
            return getOneRequisitionPage(enterpriseId, code, status, pageable);
        }
        if (status != null)
            return enterpriseRequisitionService.getEnterpriseRecivedRequisitions(enterpriseId, status, start, end, pageable);
        RequisitionStatus[] statuses = {RequisitionStatus.DENIED, RequisitionStatus.PAYED};
        return enterpriseRequisitionService.getEnterpriseRecivedRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public void passRequisition(long requisitionId) {
        enterpriseRequisitionService.passRequisition(requisitionId);
    }

    @Override
    public void denyRequisition(long requisitionId) {
        enterpriseRequisitionService.denyRequisition(requisitionId);
    }

    @Override
    public void payRequisition(long requisitionId, BigDecimal payFee, String paykey) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new UserOperationException("支付密码错误。");
        }
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        enterpriseRequisitionService.payRequisition(requisitionId, payFee, managerId);
    }

    private Page<EnterpriseRequisition> getOneRequisitionPage(long enterpriseId, long code, RequisitionStatus status, Pageable pageable) {
        EnterpriseRequisition requisition = enterpriseRequisitionService.getEnterpriseRequisition(code);
        if (requisition == null || (status != null && requisition.getStatus() != status)
                || (requisition.getEnterpriseId() != enterpriseId && requisition.getParentId() != enterpriseId)) {
            return new PageImpl<>(pageable, 0, new ArrayList<EnterpriseRequisition>());
        }
        ArrayList<EnterpriseRequisition> list = new ArrayList<>();
        list.add(requisition);
        return new PageImpl<>(pageable, 1, list);
    }
}
