/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service;

import com.ylife.customer.model.*;
import com.ylife.data.page.PageBean;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 会员服务处理接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月14日 下午2:57:09
 * @version 0.0.1
 */
public interface CustomerService {

    /**
     * 添加会员
     * @param allinfo
     * @return
     */
    int addCustomer(CustomerAllInfo allinfo);

    /**
     * 修改会员
     * @param allinfo
     * @return
     */
    int updateCustomer(CustomerAllInfo allinfo);

    /**
     * 检查会员名是否存在
     * @param customerName
     * @return true表示存在 false不存在
     */
    boolean checkUsernameExitOrNot(String customerName);

    /**
     * 根据会员编号查找对应的默认收货地址
     * @param customerId
     * @return
     */
    CustomerAddress queryDefaultAddr(Long customerId);

    /**
     * 查询单个会员信息 详细
     * 
     * @param customerId
     *            用户ID
     * @return
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 根据会员编号修改会员
     * 
     * @param
     *
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(CustomerAllInfo record);

    /**
     * 根据用户名查询用户信息
     * 
     * @param userName
     * @return
     */
    Customer selectCustomerByUserName(String userName);

    /**
     * 验证邮箱存在性
     * 
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    Long checkEmailExist(String email);

    /**
     * 验证手机存在性
     * 
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    Long checkMobileExist(String mobile);


    /**
     * 查询所有订单
     *
     * @param paramMap
     *            查询订单条件
     * @return PageBean
     */
    PageBean queryAllMyOrders(Map<String, Object> paramMap, PageBean pb);

    /**
     * 查询当前会员的退单信息
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    PageBean queryAllBackMyOrders(Map<String, Object> paramMap, PageBean pb);

    /**
     * 查询退单总数
     * @param map
     * @return
     */
    Long searchTotalCountBack(Map<String,Object> map);

    /**
     * 查询订单总数
     * @param map
     * @return
     */
    Long searchTotalCount(Map<String,Object> map);

    /**
     * 获取订单信息
     * @return
     */
    OrderInfoBean queryOrderByParamMap(Long orderId, Long customerId);
}
