package com.ylife.chinapost.service;

import com.ylife.system.model.DeliveryInform;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface DeliveryInformationService {

    /**
     * 添加发布信息
     * @param deliveryVersion 发布版本号
     * @param deliveryInform 发布信息
     * @return
     */
    int addDeliveryInform(String deliveryVersion,String deliveryInform);

    /**
     * 根据登录帐号ID获取发布信息
     * @return
     */
    DeliveryInform getDeliveryInformByManagerId();

    /**
     * 修改发布信息为已读
     * @param id
     * @return
     */
    int updateDeliveryInformRead(long id);
}
