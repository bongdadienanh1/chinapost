package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.DeliveryInformationService;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.system.model.DeliveryInform;
import com.ylife.system.service.DeliveryInformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/12/9.
 */
@Service
public class DeliveryInformationServiceImpl implements DeliveryInformationService {

    @Resource
    private DeliveryInformService deliveryInformService;

    @Resource
    private CurrentLoginService currentLoginService;

    @Resource
    private EnterpriseManagerService enterpriseManagerService;

    /**
     * 添加发布信息
     *
     * @param deliveryVersion 发布版本号
     * @param deliveryInform  发布信息
     * @return
     */
    @Override
    public int addDeliveryInform(String deliveryVersion, String deliveryInform) {
        List<EnterpriseManager> managerList = enterpriseManagerService.getAllManager();
        List<DeliveryInform> deliveryInforms = new ArrayList<>();
        for(EnterpriseManager manager : managerList){
            DeliveryInform item = new DeliveryInform();
            item.setDeliveryVersion(deliveryVersion);
            item.setDeliveryInform(deliveryInform);
            item.setDeliveryTime(new Date());
            item.setIsRead(false);
            item.setManagerId(manager.getId());
            deliveryInforms.add(item);
        }
        return deliveryInformService.batchAdd(deliveryInforms);
    }

    /**
     * 根据登录帐号ID获取发布信息
     *
     * @return
     */
    @Override
    public DeliveryInform getDeliveryInformByManagerId() {
        return deliveryInformService.getDeliveryInformByManagerId(currentLoginService.getCurrentLoginEnterpriseManagerId());
    }

    /**
     * 修改发布信息为已读
     *
     * @param id
     * @return
     */
    @Override
    public int updateDeliveryInformRead(long id) {
        return deliveryInformService.updateDeliveryInformRead(id);
    }
}
