/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ylife.customer.model.Customer;
import com.ylife.customer.model.CustomerStatisticVo;
import com.ylife.customer.model.CityBean;
import com.ylife.customer.model.CustomerAllInfo;
import com.ylife.customer.model.DistrictBean;
import com.ylife.customer.model.OrderInfoBean;
import com.ylife.customer.model.ProvinceBean;
import com.ylife.customer.model.StreetBean;

/**
 * 会员底层Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月16日 下午6:48:35
 * @version 0.0.1
 */
@Repository
public interface CustomerMapper {

    /***
     * 根据主键获取单个的会员信息
     *
     * @param customerId
     *            会员ID
     * @return
     */
    Customer getCustomerByCusId(Long customerId);

    /**
     * 根据主键获取会员
     * 
     * @param customerId
     *            会员编号
     * @return 会员 com.ysh.customer.bean.Customer
     *         {@link com.ylife.customer.model.Customer}
     */
    CustomerAllInfo selectByPrimaryKey(Long customerId);

    /**
     * 插入会员信息
     * 
     * @see  com.ylife.customer.model.Customer
     *      {@link com.ylife.customer.model.Customer}
     * @see  com.ylife.customer.model.CustomerInfo
     *      {@link com.ylife.customer.model.CustomerInfo}
     */
    int insertSelective(CustomerAllInfo customerinfo);

    /**
     * 根据会员编号删除会员
     * 
     * @param customerId
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int deleteCustomerById(Long customerId);

    /**
     * 根据会员编号修改会员
     * 
     * @param record
     *            会员编号
     * @return java.lang.Integer {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(CustomerAllInfo record);


    /**
     * 修改密码
     * @param map
     * @return
     */
    int updateCustomerPassword(Map<String, Object> map);


    /**
     * 修改用户头像
     * @param map
     * @return
     */
    int updateImgByPrimaryKeySelective(Map<String, Object> map);

    /**
     * 按条件查询会员
     * 
     * @param customerAllInfo
     *            {@link com.ylife.customer.model.CustomerAllInfo}
     * @return
     */
    List<Object> selectCustmerByAllInfo(CustomerAllInfo customerAllInfo);

    /**
     * 按条件查询会员 返回数量
     * 
     * @param customerAllInfo
     *            {@link com.ylife.customer.model.CustomerAllInfo}
     * @return
     */
    Long selectCustmerSize(CustomerAllInfo customerAllInfo);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    Customer selectCustomerByUserName(String userName);

    /**
     * 验证手机存在性
     *
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    Long checkMobileExist(String mobile);

    /**
     * 验证邮箱存在性
     * 
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    Long checkEmailExist(String email);

    /**
     * 查询所有订单
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    List<Object> queryAllMyOrders(Map<String, Object> paramMap);

    /**
     * 查询所有订单数量
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    Long searchTotalCount(Map<String, Object> paramMap);

    /**
     * 查询该会员下面的 所有退单信息
     *
     * @param paramMap
     *            查询订单条件
     * @return
     */
    List<Object> queryAllMyBackOrders(Map<String, Object> paramMap);

    /**
     * 获取当前会员 退单的数据条数 zhanghl
     * @param map
     * @return
     */
    Long searchTotalCountBack(Map<String,Object> map);

    /**
     * 获取订单信息
     * @param paramMap
     * @return
     */
    OrderInfoBean queryOrderByParamMap(Map<String, Object> paramMap);

}
