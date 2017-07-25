package com.ylife.address.mapper;

import com.ylife.address.model.City;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CityMapper {

    City selectByPrimaryKey(Long cityId);

    List<City> selectByProvinceId(Long provinceId);

    City selectByName(@Param("provinceId")Long provinceId,@Param("cityName")String cityName);
}