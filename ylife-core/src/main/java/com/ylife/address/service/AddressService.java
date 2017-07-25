package com.ylife.address.service;

import com.ylife.address.model.City;
import com.ylife.address.model.District;
import com.ylife.address.model.Province;
import com.ylife.address.model.Street;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/23.
 * 地址服务
 */
public interface AddressService {

    /**
     * 获取所有省份。
     */
    List<Province> getProvinces();

    /**
     * 根据省份ID获取城市。
     *
     * @param provinceId 省份id
     */
    List<City> getCities(long provinceId);

    /**
     * 根据城市ID获取区域
     *
     * @param cityId 城市ID
     */
    List<District> getDistricts(long cityId);

    /**
     * 根据区域ID获取街道
     *
     * @param districtId 区域ID
     */
    List<Street> getStreets(long districtId);

    /**
     * 获取地址字符串
     *
     * @param provinceId    省ID
     * @param cityId        市ID
     * @param districtId    区ID
     * @param addressDetail 地址详情
     */
    String getAddressString(Long provinceId, Long cityId, Long districtId, String addressDetail);

    Province getProvinceByName(String provinceName);

    City getCityByName(Long provinceId,String cityName);

    District getDistrictByName(Long cityId,String districtName);
}
