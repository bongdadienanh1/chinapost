package com.ylife.form.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.FormFuncMapper;
import com.ylife.form.model.CustomerFormDetail;
import com.ylife.form.model.CustomerFormStatistic;
import com.ylife.form.service.CustomerFormService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2017/1/17.
 */
@Service
public class CustomerFormServiceImpl implements CustomerFormService{

    @Resource
    private FormFuncMapper formFuncMapper;


    @Override
    public Page<CustomerFormDetail> getCustomerFormByEnterpriseId(Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable) {
        List<CustomerFormDetail> customerFormDetails=formFuncMapper.selectCustomerFormDetail(maxCatalog,minCatalog,start,end,pageable);
       int totalElements=formFuncMapper.countCustomerFormDetail(maxCatalog,minCatalog,start,end);
        return new PageImpl<>(pageable,totalElements,customerFormDetails);
    }


    @Override
    public List<CustomerFormStatistic> getCustomerFormStatisticByenterpriseId(Long enterpriseId, Long maxCatalog, Long minCatalog, Date start, Date end) {
        return formFuncMapper.selectCustomerFormStatistic(enterpriseId,maxCatalog,minCatalog,start,end);
    }
}
