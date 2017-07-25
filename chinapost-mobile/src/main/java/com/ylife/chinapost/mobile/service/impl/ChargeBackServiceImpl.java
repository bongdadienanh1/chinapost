package com.ylife.chinapost.mobile.service.impl;

import com.ylife.chinapost.mobile.service.ChargeBackService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.order.mapper.CreditOrderMapper;
import com.ylife.order.mapper.LogisticsMapper;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderReason;
import com.ylife.order.model.Logistics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/5/10.
 * ChargeBackServiceImpl
 */
@Service
public class ChargeBackServiceImpl implements ChargeBackService {

    @Resource
    private CreditOrderMapper creditOrderMapper;
    @Resource
    private LogisticsMapper logisticsMapper;


    @Override
    public Page<CreditOrder> getCreditOrdersByCustomerId(long customerId, Date start, Date end, Pageable pageable) {
        CreditOrder model = new CreditOrder();

        model.setCustomerId(customerId);
        List<CreditOrder> creditOrderList = creditOrderMapper.selectByModelSelectiveAndEnterpriseIdAndCreateDate(model, null, null, null, start, end, pageable);
        int totalElements = creditOrderMapper.countByModelSelectiveAndEnterpriseIdAndCreateDate(model, null, null, null, start, end);
        return new PageImpl<>(pageable, totalElements, creditOrderList);
    }

    @Override
    public void applyCredit(long orderNo, CreditOrderReason reason, String credential, String remark) {
        //TODO creditOrderService.createCreditOrder(orderNo, reason, credential, remark);
    }

    @Override
    public void applyRefund(long orderNo, CreditOrderReason reason, String credential, String remark) {
        //TODO creditOrderService.createRefundOrder(orderNo, reason, credential, remark);
    }

    @Override
    public void fillLogistic(Long creditOrderNo, String logisticName, String logisticNo) {
        Logistics logistics = new Logistics();
        logistics.setNpLogisticsName(logisticName);
        logisticsMapper.insertSelective(logistics);
        //creditOrderService.editDeliveryNo(creditOrderNo, logisticNo, logisticName);
    }
}
