package com.ylife.wealth.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseAllocation;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnterpriseAllocationMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseAllocation record);

    int insertSelective(EnterpriseAllocation record);

    EnterpriseAllocation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseAllocation record);

    int updateByPrimaryKey(EnterpriseAllocation record);

    List<EnterpriseAllocation> selectByInIdAndCodeAndCreateDate(@Param("inId") Long inId,
                                                                @Param("code") Long code,
                                                                @Param("start") Date start,
                                                                @Param("end") Date end,
                                                                @Param("pageable") Pageable pageable);

    int countByInIdAndCodeAndCreateDate(@Param("inId") Long inId,
                                        @Param("code") Long code,
                                        @Param("start") Date start,
                                        @Param("end") Date end);

    List<EnterpriseAllocation> selectByBatchId(@Param("batchId") Long batchId,
                                               @Param("pageable") Pageable pageable);

    int countByBatchId(@Param("batchId") Long batchId);

}