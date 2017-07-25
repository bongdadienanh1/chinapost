package com.ylife.chinapost.mobile.service;

import com.ylife.order.model.CredentialType;
import com.ylife.order.model.CreditOrderReason;

/**
 * Created by Administrator on 2016/5/18.
 * 退单接口
 */
public interface PlaceBackOrderService {
    /**
     * 申请退款
     *
     * @param orderNo    订单号
     * @param reason     原因
     * @param credential 凭证
     * @param credentialDoc 上传的凭证
     * @param remark        问题说明
     */
    void applyBackMoney(long orderNo, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark);

    /**
     * 申请退货
     *
     * @param orderNo    订单号
     * @param backgoodsJson 要退单的订单商品ID列表({goodsInfoId:goodsNum})
     * @param reason     原因
     * @param credential 凭证
     * @param credentialDoc 上传的凭证
     * @param remark        问题说明
     * @param backWay       退回方式
     */
    void applyBackGoods(long orderNo,String backgoodsJson,CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark,String backWay);


    /**
     * 填写物流信息
     * @param orderNo 订单号
     * @param wlname 快递公司
     * @param wlno 快递单号
     * @return
     */
    void saveBackOrderGeneral(String wlname, String wlno, long orderNo);
}
