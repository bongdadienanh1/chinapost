package com.ylife.deliveryCode.service;

/**
 * Created by Administrator on 2016/4/29.
 */
public interface DeliveryCodeService {

    /**
     * 获取提货码
     *
     * @return
     */
    public String getDeliveryCode();

    /**
     * 修改提货码状态（回收提货码）
     *
     * @param deliveryCode
     */
    public void recycleDeliveryCode(String deliveryCode);
}
