package com.ylife.enterprise.mapper;


import com.ylife.enterprise.model.Enterprise;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface EnterpriseMapper {

    int deleteByPrimaryKey(Long id);

    Enterprise selectByPrimaryKey(Long id);

    Enterprise selectByParentIdIsNull();

    List<Enterprise> selectByParentId(Long id);


    boolean checkPaykey(@Param("id") Long id, @Param("paykey") String paykey);

    /**
     * 根据主键更新ucoin_price字段值。
     *
     * @param id
     * @param amount
     */
    int updateUcoinPriceByPrimaryKey(@Param("id") Long id, @Param("amount") BigDecimal amount);

    void updateUcoinPriceByBach(List<Object> enterpriseUpdatePriceList);


    /**
     * 根据主键更新undistributed_price字段值。
     *
     * @param id
     * @param amount
     */
    int updateUndistributedPriceByPrimaryKey(@Param("id") Long id, @Param("amount") BigDecimal amount);


    /**
     * 根据主键获取ucoin_price字段值。
     */
    BigDecimal selectUcoinPriceByPrimaryKey(Long id);

    /**
     * 根据主键获取undistributed_price字段值。
     */
    BigDecimal selectUndistributedPriceByPrimaryKey(Long id);
}