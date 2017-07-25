package com.ylife.system.service.impl;

import com.ylife.system.mapper.DeliveryInformMapper;
import com.ylife.system.model.DeliveryInform;
import com.ylife.system.service.DeliveryInformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
@Service
public class DeliveryInformServiceImpl implements DeliveryInformService {

    @Resource
    private DeliveryInformMapper deliveryInformMapper;

    /**
     * 添加发布日志
     *
     * @param inform
     * @return
     */
    @Override
    public int addDeliveryInform(DeliveryInform inform) {
        return deliveryInformMapper.insertSelective(inform);
    }

    /**
     * 根据登录帐户获取发布日志信息
     *
     * @param managerId
     * @return
     */
    @Override
    public DeliveryInform getDeliveryInformByManagerId(Long managerId) {
        return deliveryInformMapper.selectByManagerId(managerId);
    }

    /**
     * 修改指定ID的发布信息为已读
     *
     * @param id
     * @return
     */
    @Override
    public int updateDeliveryInformRead(Long id) {
        return deliveryInformMapper.updateReadById(id);
    }

    /**
     * 批量添加发布日志
     *
     * @param list
     * @return
     */
    @Override
    public int batchAdd(List<DeliveryInform> list) {
        return deliveryInformMapper.batchInsert(list);
    }
}
