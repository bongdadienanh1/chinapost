package com.ylife.chinapost.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;

import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/4/16.
 * 邮宝查询服务
 */
public interface UcoinQueryService {

    /**
     * 获取客户信息。
     */
    ChinapostCustomer getCustomer(String idCard);

    /**
     * 获取客户信息。
     */
    ChinapostCustomer getCustomer(long customerId);

    /**
     * 获取客户的邮宝记录。
     */
    Page<CustomerUcoinHistory> getCustomerUcoinHistories(long customerId, Pageable pageable);

    /**
     * 获取客户的邮宝余额。
     */
    BigDecimal getCustomerUcoinBalance(long customerId);

    /**
     * 获取用户ID。
     */
    Long getCustomerId(String idCard);
}
