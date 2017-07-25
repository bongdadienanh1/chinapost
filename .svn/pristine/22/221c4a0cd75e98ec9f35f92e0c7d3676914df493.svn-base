package com.ylife.wealth.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseBatchAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnterpriseBatchAllocationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseBatchAllocation record);

    int insertSelective(EnterpriseBatchAllocation record);

    EnterpriseBatchAllocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseBatchAllocation record);

    int updateByPrimaryKey(EnterpriseBatchAllocation record);

    List<EnterpriseBatchAllocation> selectByOutIdAndCodeAndCreateDate(@Param("outId") Long outId,
                                                                      @Param("code") Long code,
                                                                      @Param("start") Date start,
                                                                      @Param("end") Date end,
                                                                      @Param("pageable") Pageable pageable);

    int countByOutIdAndCodeAndCreateDate(@Param("outId") Long outId,
                                         @Param("code") Long code,
                                         @Param("start") Date start,
                                         @Param("end") Date end);

}