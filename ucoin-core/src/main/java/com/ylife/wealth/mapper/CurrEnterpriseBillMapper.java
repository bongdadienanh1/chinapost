package com.ylife.wealth.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.CurrEnterpriseBill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/8/23.
 * 最近账单mapper
 */
public interface CurrEnterpriseBillMapper {

    List<CurrEnterpriseBill> selectByEnterpriseId(Long enterpriseId);

    /**
     * 邮豆网点补贴
     * */
    List<CurrEnterpriseBill> selectByEnterpriseIdOrderSubsidy(@Param("code")Long code,
                                                              @Param("enterpriseId") Long enterpriseId,
                                                              @Param("start") Date start,
                                                              @Param("end") Date end,
                                                              @Param("pageable") Pageable pageable);

    int countByEnterpriseIdOrderSubsidy(@Param("code")Long code,
                                        @Param("enterpriseId") Long enterpriseId,
                                        @Param("start") Date start,
                                        @Param("end") Date end);
    /**
     * 订单退款返还
     * */
    List<CurrEnterpriseBill> selectByEnterpriseIdBackOrderSubsidy(@Param("code")Long code,
                                                                  @Param("enterpriseId") Long enterpriseId,
                                                                  @Param("start") Date start,
                                                                  @Param("end") Date end,
                                                                  @Param("pageable") Pageable pageable);

    int countByEnterpriseIdBackOrderSubsidy(@Param("code")Long code,
                                            @Param("enterpriseId") Long enterpriseId,
                                            @Param("start") Date start,
                                            @Param("end") Date end);

}
