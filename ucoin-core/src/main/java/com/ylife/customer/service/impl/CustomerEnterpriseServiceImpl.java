package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.CustomerEnterpriseMapper;
import com.ylife.customer.model.CustomerEnterpriseKey;
import com.ylife.customer.service.CustomerEnterpriseService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/8/1.
 * CustomerEnterpriseServiceImpl
 */
@Service
public class CustomerEnterpriseServiceImpl implements CustomerEnterpriseService {

    @Resource
    private CustomerEnterpriseMapper customerEnterpriseMapper;

    @Override
    public void addRelation(long customerId, long enterpriseId) {
        try {
            customerEnterpriseMapper.insert(new CustomerEnterpriseKey(customerId, enterpriseId));
        } catch (DuplicateKeyException e) {
            //do nothing;
        }
    }



}
