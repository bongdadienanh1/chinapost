package com.ylife.chinapost.third.service.impl;

import com.ylife.chinapost.third.service.OpenInterfaceService;
import com.ylife.chinapost.third.service.OrderManageService;
import com.ylife.common.model.ApiResult;
import com.ylife.common.model.OpenUtil;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * OpenInterfaceServiceImpl.
 *
 * @Author henry .
 * @Create 2017-07-19 16:58.
 */
@Service
public class OpenInterfaceServiceImpl implements OpenInterfaceService{

    @Resource
    private OrderService orderService;
    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private OrderManageService orderManageService;

    /**
     * 商户订单状态通知接口实现
     * @param paramMap
     * @return
     */
    public ApiResult deliveryOrderStatus(Map<String, Object> paramMap) {
        ApiResult apiResult = new ApiResult();
        // 检查参数
        checkParam(apiResult, paramMap);
        // 参数错误
        if (apiResult.getReturnCode() != 200) {
            return apiResult;
        }
        // 验签
        apiResult = OpenUtil.isCheck(paramMap.get("merchantId").toString(), paramMap.get("timestamp").toString(), paramMap.get("sign").toString());
        if (apiResult.getReturnCode() != 200) {
            return apiResult;
        }
        // 检查订单是否存在
        checkOrderCode(apiResult, paramMap);
        return apiResult;
    }

    /**
     * 检查订单号是否存在
     * @param apiResult
     * @param paramMap
     */
    @Transactional
    private void checkOrderCode(ApiResult apiResult ,Map<String, Object> paramMap){
        try {
            Long orderCode = Long.parseLong(paramMap.get("orderCode").toString());
            String orderStatus = paramMap.get("orderStatus").toString();
            if ("S".equals(orderStatus)){
                Order order = orderService.getOrder(orderCode);
                // 录入快递信息  查询包裹是否存在
                List<OrderContainerRelation> relations = orderService.queryContainerRalation(order.getOrderId());
                if(relations.size() == 1){
                    // 更新运货单
                    orderService.updateThirdSendOrderGoods(new Long[]{relations.get(0).getRelationId()},
                            paramMap.get("wayBillNo").toString(), paramMap.get("expressName").toString());
                    // 修改订单状态
                    orderService.updateOrderStatus(order.getOrderId(),OrderStatus.DELIVERED,apiResult.getThirdId());
                }
            }else{
                CreditOrder creditOrder = creditOrderService.selectByOrderCode(orderCode);
                OrderStatus status = null;
                CreditOrderStatus creditOrderStatus = null;
                if("U".equals(orderStatus)){ //同意（退款、退货）
                    if (Long.valueOf(creditOrder.getIsBack()) == 1) {
                        // 同意退货
                        status = OrderStatus.RETURN_WAIT_DELIVER_INFO;
                        creditOrderStatus = CreditOrderStatus.RETURN_WAIT_DELIVER_INFO;
                    }else if(Long.valueOf(creditOrder.getIsBack()) == 2){
                        // 同意退款
                        orderManageService.refund(creditOrder.getBackOrderCode(), BigDecimal.ZERO,"");
                        status = OrderStatus.REFUND_FINISHED;
                        creditOrderStatus = CreditOrderStatus.REFUND_FINISHED;
                    }
                }else if("F".equals(orderStatus)){ // 拒绝（退款、退货）
                    if (Long.valueOf(creditOrder.getIsBack()) == 1) {
                        // 拒绝退货
                        status = OrderStatus.RETURN_DENIED;
                        creditOrderStatus = CreditOrderStatus.RETURN_DENIED;
                    }else if(Long.valueOf(creditOrder.getIsBack()) == 2){
                        // 拒绝退款
                        status = OrderStatus.PAYED;
                        creditOrderStatus = CreditOrderStatus.REFUND_DENIED;
                    }
                }else if ("C".equals(orderStatus)){
                    orderManageService.refund(creditOrder.getBackOrderCode(), BigDecimal.ZERO,"");
                    status = OrderStatus.REFUND_FINISHED;
                    creditOrderStatus = CreditOrderStatus.REFUND_FINISHED;
                }
                orderService.updateOrderStatus(creditOrder.getOrderId(),status,apiResult.getThirdId());
                creditOrderService.updateBackOrderStatus(creditOrder.getBackOrderId(),creditOrderStatus,"账户编号："+apiResult.getThirdId());
            }

        }catch (Exception e){
            getApiResult(apiResult,205,"订单号异常！");
        }

    }

    /**
     * 检查参数是否正常
     * @param apiResult
     * @param paramMap
     * @return
     */
    private void checkParam(ApiResult apiResult ,Map<String, Object> paramMap){
        String [] arry = {"merchantId","timestamp","sign","orderCode","orderStatus"};
        for (int i = 0; i<arry.length;i++){
            if(paramMap.get(arry[i]) == null || paramMap.get(arry[i]).equals("")){
                getApiResult(apiResult,205,"传递参数："+arry[i]+"_无数据或数据为空");
            }
        }
    }

    /**
     * 消息实体
     * @param apiResult
     * @param returnCode
     * @param message
     * @return
     */
    private void getApiResult(ApiResult apiResult,Integer returnCode,String message){
        apiResult.setReturnCode(returnCode);
        apiResult.setMessage(message);
    }
}
