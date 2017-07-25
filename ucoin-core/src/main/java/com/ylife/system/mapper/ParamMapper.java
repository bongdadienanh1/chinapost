package com.ylife.system.mapper;


import com.ylife.system.model.Param;

import java.util.List;

public interface ParamMapper {

    int deleteByPrimaryKey(Integer paramId);

    int deleteByTypeId(Integer typeId);

    int insert(Param record);

    int insertSelective(Param record);

    Param selectByPrimaryKey(Integer paramId);

    Param selectByPrimaryKeyForUpdate(Integer paramId);

    int updateByPrimaryKeySelective(Param record);

    int updateByPrimaryKey(Param record);

    List<Param> selectByTypeId(Integer typeId);

    List<Param> selectByModel(Param model);

    List<Param> selectComputableParamByTypeId(Integer typeId);

    Param selectByTypeIdAndNameForUpdate(@org.apache.ibatis.annotations.Param("typeId") Integer typeId,
                                         @org.apache.ibatis.annotations.Param("name") String name);
}