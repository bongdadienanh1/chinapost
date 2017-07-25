package com.ylife.system.mapper;


import com.ylife.system.model.ParamValue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParamValueMapper {

    int deleteByPrimaryKey(Integer paramValueId);

    int deleteByTypeId(Integer typeId);

    int insert(ParamValue record);

    int insertSelective(ParamValue record);

    ParamValue selectByPrimaryKey(Integer paramValueId);

    ParamValue selectByPrimaryKeyForUpdate(Integer paramValueId);

    int updateByPrimaryKeySelective(ParamValue record);

    int updateByPrimaryKey(ParamValue record);

    List<ParamValue> selectByParamId(Integer paramId);

    List<ParamValue> selectByModel(ParamValue model);

    ParamValue selectByParamIdAndNameForUpdate(@Param("paramId") Integer paramId, @Param("name") String name);

    int deleteByParamId(Integer paramId);

    ParamValue selectByValueNameAndId(@Param("paramId") int paramId, @Param("paramValueName") String paramValueName);
}