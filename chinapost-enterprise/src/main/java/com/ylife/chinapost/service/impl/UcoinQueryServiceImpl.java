package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.UcoinQueryService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerWithUcoinInfoService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.exception.RecordNotFoundException;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/4/16.
 * UcoinQueryServiceImpl
 */
@Service
public class UcoinQueryServiceImpl implements UcoinQueryService {

    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private CustomerWithUcoinInfoService customerWithUcoinInfoService;

    @Override
    public ChinapostCustomer getCustomer(String idCard) {
        return customerWithUcoinInfoService.getInfo(idCard);
    }

    @Override
    public ChinapostCustomer getCustomer(long customerId) {
        return chinapostCustomerService.getCustomer(customerId);
    }

    @Override
    public Page<CustomerUcoinHistory> getCustomerUcoinHistories(long customerId, Pageable pageable) {
        String img = enterpriseInfoService.getTopEnterpriseInfo().getImgUrl();
        Page<CustomerUcoinHistory> page = customerUcoinHistoryService.getHistory(customerId, pageable);
        for (CustomerUcoinHistory history : page.getContent()) {
            CustomerUcoinHistory.EnterpriseInfo info = history.getEnterpriseInfo();
            if (info != null) {
                info.setEnterpriseImg(img);
            }
        }
        return page;
    }

    @Override
    public BigDecimal getCustomerUcoinBalance(long customerId) {
        return customerUcoinService.getUcoinBalance(customerId);
    }

    @Override
    public Long getCustomerId(String idCard) {
        ChinapostCustomer customer = getCustomer(idCard);
        if (customer == null) {
            throw new RecordNotFoundException("客户未找到。");
        }
        return customer.getId();
    }
}
