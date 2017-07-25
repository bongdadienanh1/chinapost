package com.ylife.wealth.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.mapper.CurrEnterpriseBillMapper;
import com.ylife.wealth.mapper.EnterpriseBatchGrandMapper;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/25.
 * <p/>
 * EnterpriseBatchGrandServiceImpl
 */
@Service
public class EnterpriseBatchGrandServiceImpl implements EnterpriseBatchGrandService {

    @Resource
    private EnterpriseBatchGrandMapper enterpriseBatchGrandMapper;

    @Resource
    private CurrEnterpriseBillMapper currEnterpriseBillMapper;

    private Generator generator = IdGeneratorFactory.create("ENTERPRISE_GRAND");

    @Override
    public long addBatchGrand(long enterpriseId, String sendType, String remark,int typeId,Long batchCode,String fileName) {
        long code = generator.generate();
        EnterpriseBatchGrand grand = new EnterpriseBatchGrand();
        grand.setCode(code);
        grand.setEnterpriseId(enterpriseId);
        grand.setSendType(sendType);
        grand.setRemark(remark);
        grand.setTypeId(typeId);
        grand.setCreateTime(new Date());
        grand.setBatchCode(batchCode);
        grand.setFileName(fileName);
        enterpriseBatchGrandMapper.insertSelective(grand);
        return grand.getId();
    }

    @Override
    public void updateFee(long batchId, BigDecimal amount, Integer count,Long managerId,String managerName) {
        EnterpriseBatchGrand grand = new EnterpriseBatchGrand();
        grand.setId(batchId);
        grand.setFee(amount);
        grand.setUcoinCount(count);
        grand.setManagerId(managerId);
        grand.setManagerName(managerName);
        enterpriseBatchGrandMapper.updateByPrimaryKeySelective(grand);
    }


    @Override
    public void updateFee(List<EnterpriseBatchGrand> grands) {
        enterpriseBatchGrandMapper.updateByList(grands);
    }

    @Override
    public Page<EnterpriseBatchGrand> getBatchGrands(Long code,long enterpriseId, Date start, Date end,Integer typeId, Pageable pageable) {
        List<EnterpriseBatchGrand> list = enterpriseBatchGrandMapper.selectByEnterpriseIdAndCreateDate(code,enterpriseId, start, end,typeId, pageable);
        int totalElements = enterpriseBatchGrandMapper.countByEnterpriseIdAndCreateDate(code,enterpriseId, start, end,typeId);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public Page<CurrEnterpriseBill> getEnterpriseIdOrderSubsidy(Long code, long enterpriseId, Date start, Date end, Pageable pageable) {
        List<CurrEnterpriseBill> orderList = currEnterpriseBillMapper.selectByEnterpriseIdOrderSubsidy(code,enterpriseId,start,end,pageable);
        int totalElements = currEnterpriseBillMapper.countByEnterpriseIdOrderSubsidy(code,enterpriseId,start,end);
        return new PageImpl<>(pageable, totalElements, orderList);
    }

    @Override
    public Page<CurrEnterpriseBill> getEnterpriseIdBackOrderSubsidy(Long code, long enterpriseId, Date start, Date end, Pageable pageable) {
        List<CurrEnterpriseBill> backOrderList = currEnterpriseBillMapper.selectByEnterpriseIdBackOrderSubsidy(code,enterpriseId,start,end,pageable);
        int totalElements = currEnterpriseBillMapper.countByEnterpriseIdBackOrderSubsidy(code,enterpriseId,start,end);
        return new PageImpl<>(pageable, totalElements, backOrderList);
    }

}
