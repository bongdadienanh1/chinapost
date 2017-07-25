package com.ylife.chinapost.mobile.service.impl;

import com.ylife.chinapost.mobile.service.CurrentLoginService;
import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.exception.UserNotLoginException;
import com.ylife.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/5/8.
 * CurrentLoginServiceImpl
 */
@Service
public class CurrentLoginServiceImpl implements CurrentLoginService {
    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;

    @Override
    public Long getCurrentLoginCustomerId() {
        Long customerId = (Long) WebUtil.getCurrentSession().getAttribute("customerId");
        if (customerId == null) {
            throw new UserNotLoginException("用户未登录。");
        }
        return customerId;
    }

    @Override
    public ChinapostCustomer getCurrentLoginCustomer() {
        return chinapostCustomerMapper.selectByPrimaryKey(getCurrentLoginCustomerId());
    }

    @Override
    public String getCurrentLoginUsername() {
        return null;
    }
}
