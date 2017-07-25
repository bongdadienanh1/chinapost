package com.ylife.system.service;

import com.ylife.system.model.DeliveryInform;

import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface DeliveryInformService {

    /**
     * 添加发布日志
     * @param inform
     * @return
     */
    int addDeliveryInform(DeliveryInform inform);

    /**
     * 根据登录帐户获取发布日志信息
     * @param managerId
     * @return
     */
    DeliveryInform getDeliveryInformByManagerId(Long managerId);

    /**
     * 修改指定ID的发布信息为已读
     * @param id
     * @return
     */
    int updateDeliveryInformRead(Long id);

    /**
     * 批量添加发布日志
     * @param list
     * @return
     */
    int batchAdd(List<DeliveryInform> list);
}
