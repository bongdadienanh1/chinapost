package com.ylife.wealth.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseRequisition;
import com.ylife.wealth.model.RequisitionStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface EnterpriseRequisitionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseRequisition record);

    int insertSelective(EnterpriseRequisition record);

    EnterpriseRequisition selectByPrimaryKey(Long id);

    EnterpriseRequisition selectByPrimaryKeyForUpdate(Long id);

    EnterpriseRequisition selectByCode(Long code);

    int updateByPrimaryKeySelective(EnterpriseRequisition record);

    int updateByPrimaryKey(EnterpriseRequisition record);

    int updateStatusByPrimaryKey(@Param("id") Long id, @Param("status") RequisitionStatus status);

    List<EnterpriseRequisition> selectByStatusAndEnterpriseId(@Param("enterpriseId") Long enterpriseId,
                                                              @Param("statuses") RequisitionStatus[] statuses,
                                                              @Param("start") Date start,
                                                              @Param("end") Date end,
                                                              @Param("pageable") Pageable pageable);

    int countByStatusAndEnterpriseId(@Param("enterpriseId") Long enterpriseId,
                                     @Param("statuses") RequisitionStatus[] statuses,
                                     @Param("start") Date start,
                                     @Param("end") Date end);


    List<EnterpriseRequisition> selectByStatusAndParentId(@Param("parentId") Long parentId,

                                                          @Param("statuses") RequisitionStatus[] statuses,
                                                          @Param("start") Date start,
                                                          @Param("end") Date end,
                                                          @Param("pageable") Pageable pageable);

    int countByStatusAndParentId(@Param("parentId") Long parentId,

                                 @Param("statuses") RequisitionStatus[] statuses,
                                 @Param("start") Date start,
                                 @Param("end") Date end);

}