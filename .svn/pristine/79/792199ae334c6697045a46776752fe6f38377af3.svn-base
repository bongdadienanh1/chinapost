package com.ylife.chinapost.mobile.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.mobile.service.PlaceBackOrderService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.order.model.CredentialType;
import com.ylife.order.model.CreditOrderReason;
import com.ylife.order.service.CreditOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/18.
 * PlaceBackOrderServiceImpl
 */
@Service
public class PlaceBackOrderServiceImpl implements PlaceBackOrderService {
    @Resource
    private CreditOrderService creditOrderService;

    public static final Parser SIMPLE_PARSER = new SimpleParser();

    @Override
    @Transactional
    public void applyBackMoney(long orderNo, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark) {

        //1、提交退款申请
        creditOrderService.createRefundOrder(orderNo, reason, credential, credentialDoc, remark);
    }

    @Override
    @Transactional
    public void applyBackGoods(long orderNo, String backgoodsJson, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay) {
        Map<Long, Integer> valueMap = SIMPLE_PARSER.parseJSON(backgoodsJson, new TypeToken<Map<Long, Integer>>() {
        });
        //1、提交退货申请
        creditOrderService.createCreditOrder(orderNo, valueMap, reason, credential, credentialDoc, remark, backWay, false);
    }

    @Override
    @Transactional
    public void saveBackOrderGeneral(String wlname, String wlno, long orderNo) {
        //1、填写物流信息,修改退单状态为商家待收货
        creditOrderService.editDeliveryNo(orderNo, wlno, wlname);
    }
}
