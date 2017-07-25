package com.ylife.ucoin.mapper;

import com.ylife.ucoin.model.CustomerUcoin;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
public interface CustomerUcoinMapper {

    int deleteByTime(@Param("start")Date start,@Param("end")Date end);

    int deleteByPrimaryKey(Long id);

    int insert(CustomerUcoin record);

    int insertSelective(CustomerUcoin record);

    CustomerUcoin selectByPrimaryKey(Long id);

    List<CustomerUcoin> selectByCustomerId(Long id);

    int updateByPrimaryKeySelective(CustomerUcoin record);

    int updateByPrimaryKey(CustomerUcoin record);

    int updatePriceByPrimaryKey(@Param("id") Long id, @Param("resePrice") BigDecimal resPrice);

    BigDecimal sumResePriceByCustomerId(Long customerId);

    BigDecimal sumResePriceByCustomerIdAndEnterpriseId(@Param("customerId")Long customerId,
                                                       @Param("enterpriseId")Long enterpriseId);

    /**
     * 加锁获取日期最早的U宝。
     */
    CustomerUcoin selectEarliestUcoinByCustomerIdForUpdate(Long customerId);


    /**
     * 加锁获取日期最早的大于0的U宝（C端使用）。
     */
    CustomerUcoin selectEarliestMoreZeroUcoinByCustomerIdForUpdate(Long customerId);

    /**
     * 加锁获取指定用户、企业的最早的U宝
     * @param map
     * @return
     */
    CustomerUcoin selectEarliestUcoinByCustomerIdAndEnterpriseIdForUpdate(Map<String,Object> map);

    /**
     * 新加锁获取日期最早的U宝。
     */
    List<CustomerUcoin> selectEarliestUcoinByCustomerIdForUpdateList(@Param("customerId")Long customerId,
                                                                     @Param("enterpriseId")Long enterpriseId);
}
