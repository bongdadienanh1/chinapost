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

    /**
     *zx
     * 要在该处验证密码
     * @param username 用户名 18位是身份证号码 11位是手机号码
     * @param password
     */
    @Override
    public void login(String username, String password) {
        ChinapostCustomer customer;
        String str="^\\d{11}$";
        if (username.matches(str)){
            customer = chinapostCustomerMapper.selectByPhonePassword(username,password);
        }else{ //身份证号码
            customer = chinapostCustomerMapper.selectByUsernamePassword(username,password);
        }
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
