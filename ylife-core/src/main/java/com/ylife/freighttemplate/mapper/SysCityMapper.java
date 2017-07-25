package com.ylife.freighttemplate.mapper;

import com.ylife.freighttemplate.model.SysCity;

import java.util.List;

/**
 * 城市mapper 2014-12-17
 * 
 * @author ggn
 *
 */
public interface SysCityMapper {

    /**
     * 查询城市名称
     * 
     * @param valueOf
     * @return SysCity
     */
    SysCity selectCityById(Long valueOf);

    /**
     * 查询城市列表
     * 
     * @param freightProvinceId
     * @return List
     */
    List<SysCity> selectAllCityByProvinceId(Long freightProvinceId);

}
