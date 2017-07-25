package com.ylife.address.mapper;

import com.ylife.address.model.Street;

import java.util.List;

public interface StreetMapper {

    Street selectByPrimaryKey(Long streetId);

    List<Street> selectByDistrictId(Long districtId);

}