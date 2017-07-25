/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.customer.mapper.CustomerPointLevelMapper;
import com.ylife.customer.model.CustomerPointLevel;
import com.ylife.customer.service.PointLevelServiceMapper;
import com.ylife.data.page.PageBean;
import org.springframework.stereotype.Service;

/**
 * 会员等级功能接口
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2013年12月19日 下午3:43:10
 */
@Service("pointLevelServiceMapper")
public class PointLevelServiceMapperImpl implements PointLevelServiceMapper {
    private CustomerPointLevelMapper customerPointLevelMapper;

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#selectAllPointLevel
     * (com.ysh.util.PageBean)
     */
    @Override
    public PageBean selectAllPointLevel(PageBean pageBean) {
        Map<String, Integer> paramMap;
        pageBean.setRows(customerPointLevelMapper.selectAllCount());
        paramMap = new HashMap<>();
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        pageBean.setList(customerPointLevelMapper
                .selectPointLevelByLimit(paramMap));
        return pageBean;
    }

    /*
     * 
     * 
     * @see com.ysh.customer.service.PointLevelServiceMapper#addPointLevel
     * (com.ysh.customer.bean.CustomerPointLevel)
     */
    @Override
    public int addPointLevel(CustomerPointLevel customerPointLevel) {
        if ("1".equals(customerPointLevel.getIsDefault())) {
            cancelBeforeDefault();
        }
        return customerPointLevelMapper.insertSelective(customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#selectPointLevelById
     * (java.lang.Long)
     */
    @Override
    public CustomerPointLevel selectPointLevelById(Long pointLevelId) {
        return customerPointLevelMapper.selectByPrimaryKey(pointLevelId);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#updatePointLevel
     * (com.ysh.customer.bean.CustomerPointLevel)
     */
    @Override
    public int updatePointLevel(CustomerPointLevel customerPointLevel) {
        if ("1".equals(customerPointLevel.getIsDefault())) {
            cancelBeforeDefault();
        } else {
            //查询数据库状态为1的是否还有记录，如果没有记录就不允许修改这个默认状态
            int count = this.customerPointLevelMapper.queryIsDefaultCount();
            if (count <= 1) {
                customerPointLevel.setIsDefault("1");
            }
        }
        return customerPointLevelMapper
                .updateByPrimaryKeySelective(customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#deletePointLevel
     * (java.lang.String[])
     */
    @Override
    public int deletePointLevel(String[] pointLevelIds) {
        Integer count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("pointLevelIds", pointLevelIds);
            count = customerPointLevelMapper.deletePointLevelByIds(paramMap);
        } finally {
            paramMap = null;
        }
        return count;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#selectPointLevelByName
     * (java.lang.String)
     */
    @Override
    public Long selectPointLevelByName(String pointLevelName) {
        return customerPointLevelMapper.selectByPrimaryName(pointLevelName);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#selectAllPointLevel
     * ()
     */
    @Override
    public List<CustomerPointLevel> selectAllPointLevel() {
        return customerPointLevelMapper.selectAllPointLevel();
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#selectDefaultPointLevel
     * ()
     */
    @Override
    public Long selectDefaultPointLevel() {
        return customerPointLevelMapper.selectDefaultPointLevel();
    }

    /*
     * 
     * 
     * @see
     * com.ysh.customer.service.PointLevelServiceMapper#cancelBeforeDefault
     * ()
     */
    @Override
    public int cancelBeforeDefault() {
        return customerPointLevelMapper.cancelBeforeDefault();
    }

    public CustomerPointLevelMapper getCustomerPointLevelMapper() {
        return customerPointLevelMapper;
    }

    @Resource(name = "customerPointLevelMapper")
    public void setCustomerPointLevelMapper(
            CustomerPointLevelMapper customerPointLevelMapper) {
        this.customerPointLevelMapper = customerPointLevelMapper;
    }

}
