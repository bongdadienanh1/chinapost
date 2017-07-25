package com.ylife.client.mapper;

import com.ylife.client.bean.OpenLog;

public interface OpenLogMapper {
    int deleteByPrimaryKey(Long logId);

    int insert(OpenLog record);

    int insertSelective(OpenLog record);

    OpenLog selectByPrimaryKey(Long logId);

    int updateByPrimaryKeySelective(OpenLog record);

    int updateByPrimaryKey(OpenLog record);
}