package com.ylife.customer.mapper;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Pageable;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/7.
 * 客户mapper
 */
public interface ChinapostCustomerMapper {

    List<ChinapostCustomer> selectAll();

    Long selectId(String idCardNo);

    ChinapostCustomer selectByIdCard(String idCardNo);

    ChinapostCustomer selectByPrimaryKey(long id);

    boolean checkPaykey(@Param("customerId") Long customerId, @Param("paykey") String paykey);

    boolean checkpassword(@Param("customerId") Long customerId, @Param("password") String password);

    void insertSelective(ChinapostCustomer customer);

    void updateByIdCardSelective(ChinapostCustomer customer);

    void updateByPrimaryKey(ChinapostCustomer customer);

    void updateByPrimaryKeySelective(ChinapostCustomer customer);

    List<ChinapostCustomer> selectByCustomerModelSelective(@Param("model") ChinapostCustomer model, @Param("pageable") Pageable pageable);

    Integer countByCustomerModelSelective(@Param("model") ChinapostCustomer model);

    ChinapostCustomer selectByUsernamePassword(@Param("username") String username, @Param("password") String password);

    /**
     * 验证手机号码登录
     * @param phoneNo
     * @param password
     * @return
     */
    ChinapostCustomer selectByPhonePassword(@Param("phoneNo") String phoneNo, @Param("password") String password);

    void updateByBatch(@Param("map") Map<Long, ChinapostCustomer> map);


}