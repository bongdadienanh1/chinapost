package com.ylife.wealth.service.impl;

import com.ylife.acess.service.DataSafetyAcessService;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseUcoinHistoryMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseUcoinHistory;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.utils.Assert;
import com.ylife.wealth.mapper.EnterpriseAllocationMapper;
import com.ylife.wealth.mapper.EnterpriseBatchAllocationMapper;
import com.ylife.wealth.model.EnterpriseAllocation;
import com.ylife.wealth.model.EnterpriseBatchAllocation;
import com.ylife.wealth.service.EnterpriseAllocationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/13.
 * EnterpriseAllocationServiceImpl
 */
@Service
public class EnterpriseAllocationServiceImpl implements EnterpriseAllocationService {

    @Resource
    private DataSafetyAcessService dataSafetyAcessService;
    @Resource
    private EnterpriseBatchAllocationMapper enterpriseBatchAllocationMapper;
    @Resource
    private EnterpriseAllocationMapper enterpriseAllocationMapper;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;
    @Resource
    private EnterpriseUcoinHistoryMapper enterpriseUcoinHistoryMapper;

    private Generator generator = IdGeneratorFactory.create("ENTERPRISE_ALLOCATION");

    @Override
    @Transactional
    public void batchAllocat(long enterpriseId, long managerId, Map<Long, BigDecimal> allocations, String remark) {
        dataSafetyAcessService.enterpriseAcessSonEnterprise(enterpriseId, allocations.keySet());

        Date now = new Date();
        BigDecimal totalAmount = BigDecimal.ZERO;
        long code = generator.generate();

        //处理batchAllocation
        EnterpriseBatchAllocation enterpriseBatchAllocation = new EnterpriseBatchAllocation();
        enterpriseBatchAllocationMapper.insertSelective(enterpriseBatchAllocation);
        Long batchId = enterpriseBatchAllocation.getId();

        for (long sonId : allocations.keySet()) {
            //处理allocation
            BigDecimal amount = allocations.get(sonId);
            Assert.isTrue(amount.compareTo(BigDecimal.ZERO) >= 0, "分配数量必须大于等于0");
            doAllocat(enterpriseId, sonId, amount, batchId, code, now);
            totalAmount = totalAmount.add(amount);
        }

        EnterpriseFunction outFunction=enterpriseFunctionService.get(enterpriseId);
        EnterpriseUcoinHistory outHistory=new EnterpriseUcoinHistory();
        outHistory.setEnterpriseId(enterpriseId);
        outHistory.setBalancePrice(outFunction.getUndistributedPrice());
        outHistory.setCreateTime(new Date());
        enterpriseUcoinHistoryMapper.insertSelective(outHistory);
        doBatchAllocat(enterpriseBatchAllocation, code, enterpriseId, managerId, totalAmount, allocations.size(), batchId, remark, now);
    }

    @Override
    @Transactional
    public void singleAllocat(long enterpriseId, long managerId, long sonId, BigDecimal amount, String remark) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) > 0, "分配金额必须大于0");
        dataSafetyAcessService.enterpriseAcessSonEnterprise(enterpriseId, sonId);
        Date now = new Date();
        long code = generator.generate();
        //处理batchAllocation
        EnterpriseBatchAllocation enterpriseBatchAllocation = new EnterpriseBatchAllocation();
        enterpriseBatchAllocationMapper.insertSelective(enterpriseBatchAllocation);
        Long batchId = enterpriseBatchAllocation.getId();
        doAllocat(enterpriseId, sonId, amount, batchId, code, now);

        doBatchAllocat(enterpriseBatchAllocation, code, enterpriseId, managerId, amount, 1, batchId, remark, now);
    }

    @Override
    public Page<EnterpriseAllocation> getParentAllocations(long enterpriseId, Long code, Date start, Date end, Pageable pageable) {
        List<EnterpriseAllocation> list = enterpriseAllocationMapper.selectByInIdAndCodeAndCreateDate(enterpriseId, code, start, end, pageable);
        int totalElements = enterpriseAllocationMapper.countByInIdAndCodeAndCreateDate(enterpriseId, code, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public Page<EnterpriseAllocation> getAllocationByBatchId(long batchId, Pageable pageable) {
        List<EnterpriseAllocation> list = enterpriseAllocationMapper.selectByBatchId(batchId, pageable);
        int totalElements = enterpriseAllocationMapper.countByBatchId(batchId);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public Page<EnterpriseBatchAllocation> getBatchAllocations(long enterpriseId, Long code, Date start, Date end, Pageable pageable) {
        List<EnterpriseBatchAllocation> list = enterpriseBatchAllocationMapper.selectByOutIdAndCodeAndCreateDate(enterpriseId, code, start, end, pageable);
        int totalElements = enterpriseBatchAllocationMapper.countByOutIdAndCodeAndCreateDate(enterpriseId, code, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }

    private void doAllocat(long enterpriseId, long sonId, BigDecimal amount, long batchId, long code, Date time) {
        EnterpriseAllocation enterpriseAllocation = new EnterpriseAllocation();
        enterpriseAllocation.setCreateTime(time);
        enterpriseAllocation.setFee(amount);
        enterpriseAllocation.setOutId(enterpriseId);
        enterpriseAllocation.setInId(sonId);
        enterpriseAllocation.setBatchId(batchId);
        enterpriseAllocation.setCode(code);
        enterpriseAllocationMapper.insertSelective(enterpriseAllocation);
        //款项转账
        enterpriseService.transferUcoin(enterpriseId, sonId, amount);
        EnterpriseFunction inFunction=enterpriseFunctionService.get(sonId);
        EnterpriseUcoinHistory inHistory=new EnterpriseUcoinHistory();
        inHistory.setEnterpriseId(sonId);
        inHistory.setBalancePrice(inFunction.getUndistributedPrice());
        inHistory.setCreateTime(new Date());
        enterpriseUcoinHistoryMapper.insertSelective(inHistory);




    }

    private void doBatchAllocat(EnterpriseBatchAllocation enterpriseBatchAllocation, long code, long enterpriseId, long managerId, BigDecimal totalAmount, int count, long batchId, String remark, Date time) {
        enterpriseBatchAllocation.setId(batchId);
        enterpriseBatchAllocation.setCreateTime(time);
        enterpriseBatchAllocation.setCode(code);
        enterpriseBatchAllocation.setManagerId(managerId);
        enterpriseBatchAllocation.setOutId(enterpriseId);
        enterpriseBatchAllocation.setCount(count);
        enterpriseBatchAllocation.setRemark(remark);
        enterpriseBatchAllocation.setFee(totalAmount);
        enterpriseBatchAllocationMapper.updateByPrimaryKeySelective(enterpriseBatchAllocation);
    }

}
