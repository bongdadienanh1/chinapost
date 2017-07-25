package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.UcoinBillManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.wealth.model.EnterpriseAllocation;
import com.ylife.wealth.model.EnterpriseBatchAllocation;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import com.ylife.wealth.service.EnterpriseAllocationService;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/25.
 * UcoinBillManageServiceImpl
 */
@Service
public class UcoinBillManageServiceImpl implements UcoinBillManageService {

    @Resource
    private EnterpriseAllocationService enterpriseAllocationService;
    @Resource
    private EnterpriseBatchGrandService enterpriseBatchGrandService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private CurrentLoginService currentLoginService;

    @Override
    public Page<EnterpriseAllocation> getParentAllocations(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseAllocationService.getParentAllocations(enterpriseId, code, start, end, pageable);
    }

    @Override
    public Page<EnterpriseAllocation> getAllocationByBatchId(long batchId, Pageable pageable) {
        return enterpriseAllocationService.getAllocationByBatchId(batchId, pageable);
    }

    @Override
    public Page<EnterpriseBatchAllocation> getMyAllocations(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseAllocationService.getBatchAllocations(enterpriseId, code, start, end, pageable);
    }

    @Override
    public Page<EnterpriseBatchGrand> getGrands(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseBatchGrandService.getBatchGrands(code, enterpriseId, start, end,-100,pageable);
    }

    @Override
    public Page<CustomerUcoinHistory> getDeducts(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return customerUcoinHistoryService.getHistory(enterpriseId, code, HistoryType.UCOIN_DEDUCT, start, end, pageable);
    }

    @Override
    public Page<CustomerUcoinHistory> getGrandHistoryByBatchId(long batchId, Pageable pageable) {
        return customerUcoinHistoryService.getBatchHistories(batchId, pageable);
    }

    @Override
    public Page<CustomerUcoinHistory> getEnterpriseSubsidy(Long code,Date start, Date end, Pageable pageable) {
        Long enterpriseId=currentLoginService.getCurrentLoginEnterpriseId();
        return customerUcoinHistoryService.getEnterpriseSubsidy(code,enterpriseId,start,end,pageable);
    }
}
