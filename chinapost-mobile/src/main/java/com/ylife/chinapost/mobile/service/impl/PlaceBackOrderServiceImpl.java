package com.ylife.chinapost.mobile.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.mobile.service.PlaceBackOrderService;
import com.ylife.common.model.ApiResult;
import com.ylife.common.model.OpenUtil;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.CredentialType;
import com.ylife.order.model.CreditOrderReason;
import com.ylife.order.model.Order;
import com.ylife.order.service.CreditOrderService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatform.mapper.ThirdPlatformMapper;
import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import com.ylife.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/18.
 * PlaceBackOrderServiceImpl
 */
@Service
public class PlaceBackOrderServiceImpl implements PlaceBackOrderService {
    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private ThirdPlatformMapper thirdPlatformMapper;
    @Resource
    private ThirdPlatformInterfaceService thirdPlatformInterfaceService;
    @Resource
    private OrderMapper orderMapper;

    public static final Parser SIMPLE_PARSER = new SimpleParser();

    @Override
    @Transactional
    public void applyBackMoney(long orderNo, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark) {

        //1、提交退款申请
        creditOrderService.createRefundOrder(orderNo, reason, credential, credentialDoc, remark);

        Order order = orderMapper.selectByOrderCode(orderNo);
        //抛单
        if(!order.getIsValet() && order.getGoodsInfoType().equals("1")){
            ThirdPlatformEntity item = thirdPlatformMapper.queryByStoreId(order.getBusinessId());
            if(item.getPlatformType().equals("1")) {
                syncBackOrder(order,"");
            }
        }
    }

    @Override
    @Transactional
    public void applyBackGoods(long orderNo, String backgoodsJson, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay) {
        Map<Long, Integer> valueMap = SIMPLE_PARSER.parseJSON(backgoodsJson, new TypeToken<Map<Long, Integer>>() {
        });
        //1、提交退货申请
        creditOrderService.createCreditOrder(orderNo, valueMap, reason, credential, credentialDoc, remark, backWay, false);

        Order order = orderMapper.selectByOrderCode(orderNo);
        //抛单
        if(!order.getIsValet() && order.getGoodsInfoType().equals("1")){
            ThirdPlatformEntity item = thirdPlatformMapper.queryByStoreId(order.getBusinessId());
            if(item.getPlatformType().equals("1")) {
                syncBackOrder(order,backgoodsJson);
            }
        }
    }

    @Override
    @Transactional
    public void saveBackOrderGeneral(String wlname, String wlno, long orderNo) {
        //1、填写物流信息,修改退单状态为商家待收货
        creditOrderService.editDeliveryNo(orderNo, wlno, wlname);

        Order order = orderMapper.selectByOrderCode(orderNo);
        //抛单
        if(!order.getIsValet() && order.getGoodsInfoType().equals("1")){
            ThirdPlatformEntity item = thirdPlatformMapper.queryByStoreId(order.getBusinessId());
            if(item.getPlatformType().equals("1")) {
                syncBackOrderGeneral(order, wlname, wlno);
            }
        }
    }

    /**
     * 邮政退单流程
     *
     * @param order
     */
    @Override
    public void syncBackOrder(Order order,String backgoodsJson) {

        //获取抛单信息
        ThirdPlatformInterfaceEntity item = thirdPlatformInterfaceService.queryByStoreId(order.getBusinessId());
        Assert.notNull(item);
        //抛单参数
        Map<String,String> map = new HashMap<>();
        String timestamp = DateUtil.formatToString(new Date(), "yyyyMMddHHmmss");
        map.put("merchantId",item.getMerchantId());
        map.put("timestamp",timestamp);
        map.put("sign", MD5Util.md5Hex(item.getMerchantId() + timestamp + item.getMerchantKey()));
        map.put("orderCode", String.valueOf(order.getOrderCode()));
        if(!StringUtil.isBlank(backgoodsJson)) {
            map.put("backOrderGoodsList", backgoodsJson);
        }

        //抛单
        ApiResult apiResult = SIMPLE_PARSER.parseJSON(HttpUtil.doPost(item.getPaymentNotice(), map), new TypeToken<ApiResult>() {
        });
        //验签
        if(OpenUtil.isCheck(apiResult.getMerchantId(), apiResult.getTimestamp(), apiResult.getSign()).getReturnCode()!=200){
            //TODO:失败
        }
    }

    /**
     * 邮政抛退单物流信息
     *
     * @param order
     * @param wlname
     * @param wlno
     */
    @Override
    public void syncBackOrderGeneral(Order order, String wlname, String wlno) {
        //获取抛单信息
        ThirdPlatformInterfaceEntity item = thirdPlatformInterfaceService.queryByStoreId(order.getBusinessId());
        Assert.notNull(item);
        //抛单参数
        Map<String,String> map = new HashMap<>();
        String timestamp = DateUtil.formatToString(new Date(), "yyyyMMddHHmmss");
        map.put("merchantId",item.getMerchantId());
        map.put("timestamp",timestamp);
        map.put("sign", MD5Util.md5Hex(item.getMerchantId() + timestamp + item.getMerchantKey()));
        map.put("orderCode", String.valueOf(order.getOrderCode()));
        map.put("expressName", wlname);
        map.put("wayBillNo", wlno);

        //抛单
        ApiResult apiResult = SIMPLE_PARSER.parseJSON(HttpUtil.doPost(item.getPaymentNotice(), map), new TypeToken<ApiResult>() {
        });
        //验签
        if(OpenUtil.isCheck(apiResult.getMerchantId(), apiResult.getTimestamp(), apiResult.getSign()).getReturnCode()!=200){
            //TODO:失败
        }
    }
}
