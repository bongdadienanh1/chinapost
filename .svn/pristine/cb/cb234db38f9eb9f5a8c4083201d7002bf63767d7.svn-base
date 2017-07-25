package com.ylife.chinapost.mobile.service.impl;

import com.ylife.chinapost.mobile.service.AccountService;
import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.exception.AuthorizationException;
import com.ylife.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by InThEnd on 2016/5/9.
 * AccountServiceImpl
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;

    @Override
    public void login(String username, String password) {
        ChinapostCustomer customer = chinapostCustomerMapper.selectByUsernamePassword(username, password);
        if (customer == null) {
            throw new AuthorizationException("用户名不存在或者密码错误！");
        }
        ChinapostCustomer item = new ChinapostCustomer();
        item.setId(customer.getId());
        item.setLastLoginTime(new Date());
        chinapostCustomerMapper.updateByPrimaryKeySelective(item);
        WebUtil.getCurrentSession().setAttribute("customerId", customer.getId());
    }
}
