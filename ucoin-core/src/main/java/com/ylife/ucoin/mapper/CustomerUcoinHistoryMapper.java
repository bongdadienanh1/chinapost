package com.ylife.ucoin.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.CustomerUcoinHistoryVo;
import com.ylife.ucoin.model.HistoryType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/8.
 * CustomerUcoinHistoryMapper
 */
public interface CustomerUcoinHistoryMapper {

    int deleteBytime(@Param("start") Date start, @Param("end") Date end);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerUcoinHistory record);

    int insertSelective(CustomerUcoinHistory record);

    CustomerUcoinHistory selectByPrimaryKey(Long id);

    CustomerUcoinHistory selectBySerialNum(String serialNum);

    CustomerUcoinHistoryVo selectDetailByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerUcoinHistory record);

    int updateByPrimaryKey(CustomerUcoinHistory record);

    List<CustomerUcoinHistory> selectByCustomerId(@Param("customerId") Long customerId, @Param("pageable") Pageable pageable);

    int countByCustomerId(Long customerId);

    List<CustomerUcoinHistory> selectByBatchId(@Param("batchId") Long batchId, @Param("pageable") Pageable pageable);

    int countByBatchId(@Param("batchId") Long batchId);

    List<CustomerUcoinHistory> selectByEnterpriseIdAndCodeAndTypeAndCreateTime(@Param("enterpriseId") Long enterpriseId,
                                                                               @Param("code") Long code,
                                                                               @Param("type") HistoryType type,
                                                                               @Param("start") Date start,
                                                                               @Param("end") Date end,
                                                                               @Param("pageable") Pageable pageable);

    int countByEnterpriseIdAndCodeAndTypeAndCreateTime(@Param("enterpriseId") Long enterpriseId,
                                                       @Param("code") Long code,
                                                       @Param("type") HistoryType type,
                                                       @Param("start") Date start,
                                                       @Param("end") Date end);

    List<CustomerUcoinHistory> selectByEnterpriseIdAndTypeIdAndCreatTime(@Param("minCatalog") Long minCatalog,
                                                                         @Param("maxCatalog") Long maxCatalog,
                                                                         @Param("typeId") Integer typeId,
                                                                         @Param("start") Date start,
                                                                         @Param("end") Date end,
                                                                         @Param("pageable") Pageable pageable);

    int countByEnterpriseIdAndTypeIdAndCreatTime(@Param("minCatalog") Long minCatalog,
                                                 @Param("maxCatalog") Long maxCatalog,
                                                 @Param("typeId") Integer typeId,
                                                 @Param("start") Date start,
                                                 @Param("end") Date end);

    List<Map<String, Object>> ucoinStatistical();


    List<CustomerUcoinHistory> selectEnterpriseSubsidy(@Param("code") Long code,
                                                       @Param("enterpriseId") Long enterpriseId,
                                                       @Param("start") Date start,
                                                       @Param("end") Date end,
                                                       @Param("pageable") Pageable pageable);

    int countEnterpriseSubsidy(@Param("code") Long code,
                               @Param("enterpriseId") Long enterpriseId,
                               @Param("start") Date start,
                               @Param("end") Date end);

    void insertByBatch(@Param("list") List<CustomerUcoinHistory> list);


    List<CustomerUcoinHistory> customerBill(@Param("enterpriseId") Long enterpriseId,
                                            @Param("idCard") String idCard,
                                            @Param("pageable") Pageable pageable);

    int countCustomerBill(@Param("enterpriseId") Long enterpriseId,
                          @Param("idCard") String idCard);

	List<CustomerUcoinHistoryVo> selectDetailByCondition(@Param("customerId") Long customerId,
	                                                     @Param("batchId")  Long batchId,
	                                                     @Param("type")  HistoryType type,
	                                                     @Param("orderId")  Long orderId);

	List<CustomerUcoinHistory> selectByMobileCustomerId(@Param("customerId") Long customerId, @Param("pageable") Pageable pageable);


	int countByMobileCustomerId(@Param("customerId") Long customerId);
}