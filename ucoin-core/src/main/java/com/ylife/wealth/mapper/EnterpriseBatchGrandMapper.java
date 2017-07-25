package com.ylife.wealth.mapper;


import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnterpriseBatchGrandMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseBatchGrand record);

    int insertSelective(EnterpriseBatchGrand record);

    EnterpriseBatchGrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseBatchGrand record);

    int updateByList(List<EnterpriseBatchGrand> record);

    int updateByPrimaryKey(EnterpriseBatchGrand record);

    List<EnterpriseBatchGrand> selectByEnterpriseIdAndCreateDate(@Param("code")Long code,
                                                                 @Param("enterpriseId") Long enterpriseId,
                                                                 @Param("start") Date start,
                                                                 @Param("end") Date end,
                                                                 @Param("typeId")int typeId,
                                                                 @Param("pageable") Pageable pageable);

    int countByEnterpriseIdAndCreateDate(@Param("code")Long code,
                                         @Param("enterpriseId") Long enterpriseId,
                                         @Param("start") Date start,
                                         @Param("end") Date end,
                                         @Param("typeId")int typeId);


    List<EnterpriseBatchGrand> selectByTime(@Param("start")Date start,
                                           @Param("end")Date end);

    int deleteByTime(@Param("start")Date start,
                     @Param("end")Date end);

}