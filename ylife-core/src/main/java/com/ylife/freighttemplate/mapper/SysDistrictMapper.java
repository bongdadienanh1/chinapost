package com.ylife.freighttemplate.mapper;

import com.ylife.freighttemplate.model.SysDistrict;

import java.util.List;

/**
 * 区县接口
 * 
 * @author ggn
 *
 */
public interface SysDistrictMapper {

    /**
     * 查询区县列表
     * 
     * @param freightCityId
     * @return List
     */
    List<SysDistrict> selectAllDistrictByCityId(Long freightCityId);

}
