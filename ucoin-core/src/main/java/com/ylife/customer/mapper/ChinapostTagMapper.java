package com.ylife.customer.mapper;


import com.ylife.customer.model.ChinapostTag;

import java.util.List;

public interface ChinapostTagMapper {

    int deleteByPrimaryKey(Integer tagId);

    int insert(ChinapostTag record);

    int insertSelective(ChinapostTag record);

    ChinapostTag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(ChinapostTag record);

    int updateByPrimaryKey(ChinapostTag record);

    List<ChinapostTag> selectAll();

    List<ChinapostTag> selectByCustomerId(Long customerId);

}