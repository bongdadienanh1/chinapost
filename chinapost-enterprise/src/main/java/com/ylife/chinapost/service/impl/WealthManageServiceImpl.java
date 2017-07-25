package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.WealthManageService;
import com.ylife.chinapost.service.pojo.WealthManageResult;
import com.ylife.chinapost.service.pojo.WealthManageResult.SonWealth;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.AuthorizationException;
import com.ylife.wealth.mapper.CurrEnterpriseBillMapper;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.service.EnterpriseAllocationService;
import com.ylife.wealth.service.EnterpriseRequisitionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/12.
 * WealthManageServiceImpl
 */
@Service
@Transactional
public class WealthManageServiceImpl implements WealthManageService {

    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private EnterpriseRequisitionService enterpriseRequisitionService;
    @Resource
    private EnterpriseAllocationService enterpriseAllocationService;
    @Resource
    private CurrEnterpriseBillMapper currEnterpriseBillMapper;

    @Override
    public BigDecimal totalWealth() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseService.getTotalWealth(enterpriseId);
    }

    @Override
    public String getEnterpriseName() {
        return currentLoginService.getCurrentLoginEnterprise().getEnterpriseName();
    }

    @Override
    public BigDecimal totalNotAllocatWealth() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseService.getTotalNotAllocatWealth(enterpriseId);
    }

    @Override
    public BigDecimal totalAllocatedWealth() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return totalWealth().subtract(enterpriseService.getTotalNotAllocatWealth(enterpriseId));
    }

    @Override
    public BigDecimal getUcoinWealth() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseService.getHoldWealth(enterpriseId);
    }

    @Override
    public void launchRequisition(BigDecimal amount, String reason) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        enterpriseRequisitionService.launchRequisition(enterpriseId, amount, reason);
    }

    @Override
    public void wealthAllocat(String paykey, Map<Long, BigDecimal> allocations) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new AuthorizationException("支付密码错误。");
        }
        enterpriseAllocationService.batchAllocat(enterpriseId, managerId, allocations, null);
    }

    @Override
    public boolean isTopEnterprise() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseService.isTop(enterpriseId);
    }

    @Override
    public WealthManageResult getManangeResult() {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseInfo info = currentLoginService.getCurrentLoginEnterpriseInfo();
        WealthManageResult result = new WealthManageResult();

        boolean isTop = function.getParentId() == null;
        BigDecimal totalWealth;
        BigDecimal myWealth = function.getUcoinPrice();
        BigDecimal allocatedWealth;
        BigDecimal unAllocatedWealth = function.getUndistributedPrice();
        BigDecimal sonsTotalWealth = BigDecimal.ZERO;

        List<Enterprise> enterpriseList = enterpriseService.getEnterprises(function.getId());
        List<SonWealth> sonWealths = new ArrayList<>();
        for (Enterprise enterprise : enterpriseList) {
            BigDecimal sonTotalWealth = enterpriseService.getTotalWealth(enterprise.getId());
            sonsTotalWealth = sonsTotalWealth.add(sonTotalWealth);
            SonWealth wealth = new SonWealth(enterprise.getId(), enterprise.getEnterpriseName(), sonTotalWealth);
            sonWealths.add(wealth);
        }
        allocatedWealth = sonsTotalWealth.add(myWealth);
        totalWealth = allocatedWealth.add(unAllocatedWealth);
        result.setTop(isTop);
        result.setEnterpriseId(function.getId());
        result.setEnterpriseName(info.getEnterpriseName());
        result.setAccountName(info.getAccountName());
        result.setTotalWealth(totalWealth);
        result.setMyWealth(myWealth);
        result.setAllocatedWealth(allocatedWealth);
        result.setUnAllocatedWealth(unAllocatedWealth);
        result.setSonsTotalWealth(sonsTotalWealth);
        result.setSonWealths(sonWealths);
        return result;
    }

    @Override
    public List<CurrEnterpriseBill> getCurrentBill() {
        return currEnterpriseBillMapper.selectByEnterpriseId(currentLoginService.getCurrentLoginEnterpriseId());
    }

    @Override
    public List<EnterpriseRequisition> getCurrentReq() {
        return enterpriseRequisitionService.getEnterpriseRequisitions(currentLoginService.getCurrentLoginEnterpriseId(), null, null, new Pageable(1, 15)).getContent();
    }
}
