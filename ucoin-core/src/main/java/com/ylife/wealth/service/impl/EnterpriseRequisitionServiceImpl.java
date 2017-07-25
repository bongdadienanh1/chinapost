package com.ylife.wealth.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.wealth.mapper.EnterpriseRequisitionMapper;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;
import com.ylife.wealth.service.EnterpriseAllocationService;
import com.ylife.wealth.service.EnterpriseRequisitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/13.
 * EnterpriseRequisitionServiceImpl
 */
@Service
public class EnterpriseRequisitionServiceImpl implements EnterpriseRequisitionService {

    @Resource
    private EnterpriseRequisitionMapper enterpriseRequisitionMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private EnterpriseAllocationService enterpriseAllocationService;

    private Generator generator = IdGeneratorFactory.create("ENTERPRISE_REQUISITION");

    @Override
    public void launchRequisition(long enterpriseId, BigDecimal amount, String remark) {
        Date now = new Date();
        long code = generator.generate();
        Enterprise enterprise = enterpriseMapper.selectByPrimaryKey(enterpriseId);
        EnterpriseRequisition enterpriseRequisition = new EnterpriseRequisition();
        enterpriseRequisition.setCreateTime(now);
        enterpriseRequisition.setCode(code);
        enterpriseRequisition.setEnterpriseId(enterpriseId);
        enterpriseRequisition.setFee(amount);
        enterpriseRequisition.setParentId(enterprise.getParentId());
        enterpriseRequisition.setRemark(remark);
        enterpriseRequisition.setStatus(RequisitionStatus.APPLIED);
        enterpriseRequisitionMapper.insertSelective(enterpriseRequisition);
    }

    @Override
    @Transactional
    public void passRequisition(long requisitionId) {
        EnterpriseRequisition enterpriseRequisition = enterpriseRequisitionMapper.selectByPrimaryKeyForUpdate(requisitionId);
        if (enterpriseRequisition.getStatus() == RequisitionStatus.APPLIED) {
            enterpriseRequisitionMapper.updateStatusByPrimaryKey(requisitionId, RequisitionStatus.PASSED);
        }
    }

    @Override
    @Transactional
    public void denyRequisition(long requisitionId) {
        EnterpriseRequisition enterpriseRequisition = enterpriseRequisitionMapper.selectByPrimaryKeyForUpdate(requisitionId);
        if (enterpriseRequisition.getStatus() == RequisitionStatus.APPLIED) {
            enterpriseRequisitionMapper.updateStatusByPrimaryKey(requisitionId, RequisitionStatus.DENIED);
        }
    }

    @Override
    @Transactional
    public void payRequisition(long requisitionId, BigDecimal payFee, long managerId) {
        EnterpriseRequisition req = enterpriseRequisitionMapper.selectByPrimaryKeyForUpdate(requisitionId);
        if (req.getStatus() == RequisitionStatus.PASSED) {
            enterpriseAllocationService.singleAllocat(req.getParentId(), managerId, req.getEnterpriseId(), payFee, req.getRemark());
            EnterpriseRequisition model = new EnterpriseRequisition();
            model.setId(requisitionId);
            model.setPayFee(payFee);
            model.setStatus(RequisitionStatus.PAYED);
            enterpriseRequisitionMapper.updateByPrimaryKeySelective(model);
        }
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, Date start, Date end, Pageable pageable) {
        RequisitionStatus[] statuses = {};
        return getEnterpriseRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, RequisitionStatus status, Date start, Date end, Pageable pageable) {
        RequisitionStatus[] statuses = {status};
        return getEnterpriseRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRequisitions(long enterpriseId, RequisitionStatus[] statuses, Date start, Date end, Pageable pageable) {
        List<EnterpriseRequisition> list = enterpriseRequisitionMapper.selectByStatusAndEnterpriseId(enterpriseId, statuses, start, end, pageable);
        int totalElements = enterpriseRequisitionMapper.countByStatusAndEnterpriseId(enterpriseId, statuses, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, Date start, Date end, Pageable pageable) {
        RequisitionStatus[] statuses = {};
        return getEnterpriseRecivedRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, RequisitionStatus status, Date start, Date end, Pageable pageable) {
        RequisitionStatus[] statuses = {status};
        return getEnterpriseRecivedRequisitions(enterpriseId, statuses, start, end, pageable);
    }

    @Override
    public Page<EnterpriseRequisition> getEnterpriseRecivedRequisitions(long enterpriseId, RequisitionStatus[] statuses, Date start, Date end, Pageable pageable) {
        List<EnterpriseRequisition> list = enterpriseRequisitionMapper.selectByStatusAndParentId(enterpriseId, statuses, start, end, pageable);
        int totalElements = enterpriseRequisitionMapper.countByStatusAndParentId(enterpriseId, statuses, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public EnterpriseRequisition getEnterpriseRequisition(long code) {
        return enterpriseRequisitionMapper.selectByCode(code);
    }
}
