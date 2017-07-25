package com.ylife.address.mapper;

import com.ylife.address.model.District;
import com.ylife.utils.StringUtil;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {

    District selectByPrimaryKey(Long districtId);

    List<District> selectByCityId(Long cityId);

    District selectByName(@Param("cityId")Long cityId,@Param("districtName")String districtName);

}