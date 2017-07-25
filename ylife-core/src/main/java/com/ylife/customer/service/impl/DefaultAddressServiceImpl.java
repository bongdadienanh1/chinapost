/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.ProvinceDefaultMapper;
import com.ylife.customer.model.ProvinceDefault;
import com.ylife.customer.service.DefaultAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 默认地址service
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月10日13:13:11
 *
 */
@Service("DefaultAddressService")
public class DefaultAddressServiceImpl implements DefaultAddressService {

    /**
     * spring注解
     */
    @Resource(name = "provinceDefaultMapper")
    private ProvinceDefaultMapper mapper;

    /*
     * 插入默认地址
     * 
     * @see
     * com.ysh.system.service.DefaultAddressService#insertAddressDefaultService
     * (com.ysh.system.bean.ProvinceDefault)
     */
    @Override
    @Transactional
    public int insertAddressDefaultService(Long districtId) {
        ProvinceDefault provinceDefault = new ProvinceDefault();
        provinceDefault.setDistrictId(districtId);
        provinceDefault.setCreateTime(new Date());
        provinceDefault.setDeFalg("0");
        // 清空默认地址
        mapper.deleteAllDefault();
        return mapper.insertSelective(provinceDefault);
    }

    /*
     * 获取默认地址id
     * 
     * @see
     * com.ysh.system.service.DefaultAddressService#getDefaultIdService()
     */
    @Override
    public Long getDefaultIdService() {
        return mapper.selectDefaultId();
    }
}
