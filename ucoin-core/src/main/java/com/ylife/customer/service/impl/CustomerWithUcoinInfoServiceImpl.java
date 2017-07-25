package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.CustomerWithUcoinInfoMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.CustomerWithUcoinInfoService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/29.
 * 用户包含邮宝信息服务。
 */
@Service
public class CustomerWithUcoinInfoServiceImpl implements CustomerWithUcoinInfoService {

    @Resource
    private CustomerWithUcoinInfoMapper customerWithUcoinInfoMapper;

    @Override
    public ChinapostCustomer getInfo(String idCardNo) {
        return customerWithUcoinInfoMapper.selectByIdCard(idCardNo);
    }

    @Override
    public ChinapostCustomer getInfo(String idCardNo, long enterpriseId) {
        return customerWithUcoinInfoMapper.selectByIdCardAndEnterpriseId(idCardNo, enterpriseId);
    }

    @Override
    public ChinapostCustomer getInfo(Long customerId) {
        return customerWithUcoinInfoMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public Page<ChinapostCustomer> getInfos(ChinapostCustomer queryModel, String tagName, long enterpriseId, Long minIndex, Long maxIndex, Pageable pageable) {
        List<ChinapostCustomer> list;
        int totalElements = customerWithUcoinInfoMapper.countByModelSelective(queryModel, tagName, minIndex, maxIndex);
        if (totalElements == 0)
            list = new ArrayList<>();
        else
            list = customerWithUcoinInfoMapper.selectByModelSelective(queryModel, tagName, enterpriseId, minIndex, maxIndex, pageable);
        return new PageImpl<>(pageable, totalElements, list);
    }


}
