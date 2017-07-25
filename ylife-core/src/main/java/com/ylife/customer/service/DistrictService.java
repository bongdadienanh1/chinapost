/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.customer.service;

import com.ylife.customer.model.AddressUtil;
import com.ylife.customer.model.DistrictBean;
import com.ylife.data.page.PageBean;

import java.util.List;

/**
 * 区县Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:53:45
 * @version 1.0
 */
public interface DistrictService {

    /**
     * 根据主键查询实体
     * 
     * @param districtId
     *            区县的主键ID
     * @return 查询到的实体
     */
    DistrictBean findDistrictByPrimaryKey(Long districtId);

    /**
     * 根据城市信息查询所有的区县信息
     * 
     * @param cityId
     *            城市信息的ID
     * @return 查询到的区县信息的集合
     */
    List<DistrictBean> queryDistrictByCityId(Long cityId);

    /**
     * 验证区县名称是否可用
     * 
     * @param districtName
     *            待验证的区县名称
     * @return 验证的结果 可用返回true 否则返回false
     */
    boolean checkDistrictName(String districtName);

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @param districtId
     * @return
     */
    AddressUtil queryAddressNameByDistrictId(Long districtId);

    /**
     * 根据城市id和县区名称查询所有县区
     * @param cityId 城市id
     * @param districtName 县区名称
     * @return
     */
    List<Object> queryDistrictByCityIdAndDistName(Long cityId, String districtName);
}
