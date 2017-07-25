package com.ylife.tempryUcoinUtil.impl;

import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.tempryUcoinUtil.RestoreData;
import com.ylife.ucoin.mapper.CustomerUcoinHistoryMapper;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.utils.DateUtil;
import com.ylife.wealth.mapper.EnterpriseBatchGrandMapper;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/24.
 */

@Service
public class RestoreDataImpl implements RestoreData {

    @Resource
    private CustomerUcoinHistoryMapper customerUcoinHistoryMapper;
    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private EnterpriseBatchGrandMapper enterpriseBatchGrandMapper;
    @Resource
    EnterpriseService enterpriseService;

    private static final String startTime="2016-09-23 00:00:00";
    private static final String endTime="2016-09-26 23:59:59";


    @Override
    @Transactional
    public void restoreDataByTime() {
        Date start= DateUtil.fromString(startTime, "yyyy-MM-dd hh:mm:ss");
        Date end=DateUtil.fromString(endTime,"yyyy-MM-dd hh:mm:ss");
        List<EnterpriseBatchGrand> bachGrandList = enterpriseBatchGrandMapper.selectByTime(start, end);
        for (EnterpriseBatchGrand enterpriseBatchGrand : bachGrandList) {
            enterpriseService.addUcoinAmount(enterpriseBatchGrand.getEnterpriseId(), enterpriseBatchGrand.getFee());
        }
        customerUcoinHistoryMapper.deleteBytime(start, end);
        customerUcoinMapper.deleteByTime(start, end);
        enterpriseBatchGrandMapper.deleteByTime(start, end);
    }
}
