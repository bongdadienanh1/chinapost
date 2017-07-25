package com.ylife.ucoin.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.ucoin.mapper.CustomerUcoinHistoryMapper;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.CustomerUcoinHistoryVo;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * CustomerUcoinHistoryServiceImpl
 */
@Service
public class CustomerUcoinHistoryServiceImpl implements CustomerUcoinHistoryService {

    @Resource
    private CustomerUcoinHistoryMapper customerUcoinHistoryMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;

    public static final Generator generator = IdGeneratorFactory.create("UCOIN_HISTORY");

    @Override
    public List<CustomerUcoinHistory> getHistory(long customerId) {
        return customerUcoinHistoryMapper.selectByCustomerId(customerId, null);
    }

    @Override
    public Page<CustomerUcoinHistory> getHistory(long customerId, Pageable pageable) {
        List<CustomerUcoinHistory> content = customerUcoinHistoryMapper.selectByCustomerId(customerId, pageable);
        int totalElements = customerUcoinHistoryMapper.countByCustomerId(customerId);
        return new PageImpl<>(pageable, totalElements, content);
    }

	@Override
	public Page<CustomerUcoinHistory> getMobileHistory(long customerId, Pageable pageable) {
		List<CustomerUcoinHistory> content = customerUcoinHistoryMapper.selectByMobileCustomerId(customerId, pageable);
		int totalElements = customerUcoinHistoryMapper.countByMobileCustomerId(customerId);
		return new PageImpl<>(pageable, totalElements, content);
	}

    @Override
    public Page<CustomerUcoinHistory> getHistory(long enterpriseId, Long code, HistoryType type, Date start, Date end, Pageable pageable) {
        List<CustomerUcoinHistory> histories = customerUcoinHistoryMapper.selectByEnterpriseIdAndCodeAndTypeAndCreateTime(enterpriseId, code, type, start, end, pageable);
        int totalElements = customerUcoinHistoryMapper.countByEnterpriseIdAndCodeAndTypeAndCreateTime(enterpriseId, code, type, start, end);
        return new PageImpl<>(pageable, totalElements, histories);
    }

    @Override
    public Page<CustomerUcoinHistory> getHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable) {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();
        List<CustomerUcoinHistory> histories = customerUcoinHistoryMapper.selectByEnterpriseIdAndTypeIdAndCreatTime(minCatalog, maxCatalog, typeId, start, end, pageable);
        int totalElements = customerUcoinHistoryMapper.countByEnterpriseIdAndTypeIdAndCreatTime(minCatalog, maxCatalog, typeId, start, end);
        return new PageImpl<>(pageable, totalElements, histories);
    }

    @Override
    public Page<CustomerUcoinHistory> getBatchHistories(long batchId, Pageable pageable) {

        List<CustomerUcoinHistory> list = customerUcoinHistoryMapper.selectByBatchId(batchId, pageable);
        int totalElements = customerUcoinHistoryMapper.countByBatchId(batchId);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public int historyCount(long customerId) {
        return customerUcoinHistoryMapper.countByCustomerId(customerId);
    }

    @Override
    public void addHistory(long customerId, Long enterpriseId, Long batchId, HistoryType type,Date businessTime, BigDecimal price, BigDecimal marketPrice, BigDecimal salesPrice,BigDecimal balancePrice, Integer typeId, String remark, Long orderId, String paramJson,BigDecimal paySettlePrice) {
        Assert.notNull(type);
        if (type == HistoryType.ENTERPRISE_GRAND || type == HistoryType.UCOIN_DEDUCT) {
            Assert.notNull(enterpriseId);
        }
        long code = generator.generate();
        CustomerUcoinHistory history = new CustomerUcoinHistory();
        history.setEnterpriseId(enterpriseId);
        history.setCustomerId(customerId);
        history.setBatchId(batchId);
        history.setType(type);
        history.setBusinessTime(businessTime);
        history.setPrice(price);
        history.setMarketPrice(marketPrice);
        history.setSalesPrice(salesPrice);
        history.setBalancePrice(balancePrice);
        history.setCreateTime(new Date());
        history.setTypeId(typeId);
        history.setRemark(remark);
        history.setOrderId(orderId);
        history.setParamJson(paramJson);
        history.setCode(code);
        history.setPaySettlePrice(paySettlePrice);
        customerUcoinHistoryMapper.insertSelective(history);
    }

    @Override
    public CustomerUcoinHistory selectByPrimaryKey(Long id) {
        return customerUcoinHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public CustomerUcoinHistory getHistoryBySerialNum(String serialNum) {
        return customerUcoinHistoryMapper.selectBySerialNum(serialNum);
    }

    @Override
    public CustomerUcoinHistoryVo selectDetailByPrimaryKey(Long id) {
        return customerUcoinHistoryMapper.selectDetailByPrimaryKey(id);
    }

	@Override
	public List<CustomerUcoinHistoryVo> selectDetailByCondition(Long customerId, Long batchId, HistoryType type, Long orderId) {
		return customerUcoinHistoryMapper.selectDetailByCondition(customerId, batchId, type, orderId);
	}

	@Override
    public Page<CustomerUcoinHistory> getEnterpriseSubsidy(Long code, Long enterpriseId, Date start, Date end, Pageable pageable) {
        List<CustomerUcoinHistory> customerUcoinHistoryList = customerUcoinHistoryMapper.selectEnterpriseSubsidy(code, enterpriseId, start, end, pageable);
        int totalElements = customerUcoinHistoryMapper.countEnterpriseSubsidy(code, enterpriseId, start, end);
        return new PageImpl<>(pageable, totalElements, customerUcoinHistoryList);
    }

    @Override
    public void insertByBatch(List<CustomerUcoinHistory> list) {
       customerUcoinHistoryMapper.insertByBatch(list);
    }

    @Override
    public Page<CustomerUcoinHistory> getcustomerBill(Long enterpriseId,String idCard,Pageable pageable){
        List<CustomerUcoinHistory> list = customerUcoinHistoryMapper.customerBill(enterpriseId, idCard, pageable);
        int totalElements=customerUcoinHistoryMapper.countCustomerBill(enterpriseId,idCard);
        return new PageImpl<>(pageable,totalElements,list);
    }
}