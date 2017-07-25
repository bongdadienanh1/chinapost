package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.CustomerFormDetail;
import com.ylife.form.model.CustomerFormStatistic;

import java.util.Date;
import java.util.List;


/**
 * Created by XiaoBiaoGe on 2017/1/17.
 */
public interface CustomerFormService {


    /**
     * 会员基础数据明细表
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerFormDetail> getCustomerFormByEnterpriseId(Long maxCatalog,Long minCatalog,Date start,Date end,Pageable pageable);

    /**
     * 会员基础数据统计表
     * @param enterpriseId
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<CustomerFormStatistic> getCustomerFormStatisticByenterpriseId(Long enterpriseId,Long maxCatalog,Long minCatalog,Date start,Date end);


}
