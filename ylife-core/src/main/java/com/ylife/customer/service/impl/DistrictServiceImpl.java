/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.CustomDistrictMapper;
import com.ylife.customer.model.AddressUtil;
import com.ylife.customer.model.DistrictBean;
import com.ylife.customer.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区县信息Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:59:57
 * @version 1.0
 */
@Service("DistrictService")
public class DistrictServiceImpl implements DistrictService {
    /** 区县信息注解 */

    @Resource
    private CustomDistrictMapper customDistrictMapper;

    /**
     * 删除区县信息
     * 
     * @see
     * com.ysh.system.service.DistrictService#delDistrict(Long)
     */
    public int delDistrict(Long districtId) {
        return this.customDistrictMapper.deleteByPrimaryKey(districtId);
    }
    /**
     * 根据主键查询实体
     * 
     * @see
     * com.ysh.system.service.DistrictService#findDistrictByPrimaryKey(java
     * .lang.Long)
     */
    public DistrictBean findDistrictByPrimaryKey(Long districtId) {
        return this.customDistrictMapper.selectByPrimaryKey(districtId);
    }

    /**
     * 
     * 
     * @see
     * com.ysh.system.service.DistrictService#queryDistrictByCityId(java
     * .lang.Long)
     */
    public List<DistrictBean> queryDistrictByCityId(Long cityId) {
        return this.customDistrictMapper.queryDistrictListByCityId(cityId);
    }

    /**
     * 验证区县名称是否可用
     * 
     * @see
     * com.ysh.system.service.DistrictService#checkDistrictName(java.lang
     * .String)
     */
    public boolean checkDistrictName(String districtName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("districtName", districtName);
            return this.customDistrictMapper.queryDistrictByDistrictName(map) > 0 ? false
                    : true;
        } finally {
            map = null;
        }
    }

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @see
     * com.ysh.system.service.DistrictService#queryAddressNameByDistrictId
     * (java.lang.Long)
     */
    public AddressUtil queryAddressNameByDistrictId(Long districtId) {
        return this.customDistrictMapper.queryAddressNameByDistrictId(districtId);
    }

    /**
     * 根据城市id和县区名称查询所有县区
     *
     * @param cityId
     *            城市id
     * @param districtName
     *            县区名称
     * @return
     */
    @Override
    public List<Object> queryDistrictByCityIdAndDistName(Long cityId,
            String districtName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cityId", cityId);
        paramMap.put("districtName", districtName);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        return this.customDistrictMapper.queryDistrictListByPb(paramMap);
    }

}
