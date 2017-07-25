package com.ylife.system.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.system.model.BusinessTypeHistory;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BusinessTypeHistoryMapper {

    int deleteByPrimaryKey(Long historyId);

    int insert(BusinessTypeHistory record);

    int insertSelective(BusinessTypeHistory record);

    BusinessTypeHistory selectByPrimaryKey(Long historyId);

    List<BusinessTypeHistory> selectByTypeIdAndTime(@Param("typeId") Integer typeId,
                                                    @Param("startTime") Date startTime,
                                                    @Param("endTime") Date endTime,
                                                    @Param("pageable") Pageable pageable);

    int countByTypeIdAndTime(@Param("typeId") Integer typeId,
                             @Param("startTime") Date startTime,
                             @Param("endTime") Date endTime);

    int updateByPrimaryKeySelective(BusinessTypeHistory record);

    int updateByPrimaryKey(BusinessTypeHistory record);
}