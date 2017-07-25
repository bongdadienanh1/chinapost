package com.ylife.deliveryCode.service.impl;

import com.ylife.deliveryCode.mapper.DeliveryCodeMapper;
import com.ylife.deliveryCode.model.DeliveryCode;
import com.ylife.deliveryCode.service.DeliveryCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2016/5/7.
 */
@Service
public class DeliveryCodeServiceImpl implements DeliveryCodeService {

    @Resource
    private DeliveryCodeMapper deliveryCodeMapper;

    /**
     * 获取提货码
     *
     * @return
     */
    public String getDeliveryCode() {
        DeliveryCode code = deliveryCodeMapper.selectRandomAbleDeliveryCode();
        DecimalFormat df5 = new DecimalFormat("00000");
        DecimalFormat df3 = new DecimalFormat("000");
        String str2 = df5.format(code.getDeliveryId());
        String str = df3.format((int) (Math.random() * 1000));
        //修改自提码状态
        code.setDeliveryStatus("1");
        deliveryCodeMapper.updateByPrimaryKeySelective(code);
        return str2 + str;
    }

    /**
     * 修改提货码状态（回收提货码）
     *
     * @param deliveryCode
     */
    public void recycleDeliveryCode(String deliveryCode) {
        DeliveryCode code = deliveryCodeMapper.selectByPrimaryKey(Long.parseLong(deliveryCode.substring(0, 5)));
        code.setDeliveryStatus("0");
        deliveryCodeMapper.updateByPrimaryKeySelective(code);
    }
}
