package com.ylife.customer.mapper;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Pageable;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/29.
 * 带有u宝总额的客户信息。
 */
public interface CustomerWithUcoinInfoMapper {

    ChinapostCustomer selectByPrimaryKey(Long id);

    ChinapostCustomer selectByIdCard(String idcardNo);

    ChinapostCustomer selectByIdCardAndEnterpriseId(@Param("idcardNo") String idcardNo, @Param("enterpriseId") Long enterpriseId);

    List<ChinapostCustomer> selectByModelSelective(@Param("model") ChinapostCustomer model,
                                                   @Param("tagName") String tagName,
                                                   @Param("enterpriseId") Long enterpriseId,
                                                   @Param("minIndex") Long minIndex,
                                                   @Param("maxIndex") Long maxIndex,
                                                   @Param("pageable") Pageable pageable);

    Integer countByModelSelective(@Param("model") ChinapostCustomer model,
                                  @Param("tagName") String tagName,
                                  @Param("minIndex") Long minIndex,
                                  @Param("maxIndex") Long maxIndex);

}
