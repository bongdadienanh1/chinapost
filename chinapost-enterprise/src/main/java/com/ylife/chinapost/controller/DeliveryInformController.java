package com.ylife.chinapost.controller;

import com.ylife.chinapost.service.DeliveryInformationService;
import com.ylife.data.json.message.JsonResponseBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/12/9.
 */
@Controller
@RequestMapping(value="/web/deliveryInform", produces = "application/json;charset=utf-8")
public class DeliveryInformController {
    @Resource
    private DeliveryInformationService deliveryInformationService;

    /**
     * 添加发布信息
     */
    @RequestMapping("/addDeliveryInform")
    @ResponseBody
    public String addDeliveryInform(String deliveryVersion,String deliveryInform) {
        deliveryInformationService.addDeliveryInform(deliveryVersion,deliveryInform);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改发布信息为已读
     */
    @RequestMapping("/updateDeliveryInformRead")
    @ResponseBody
    public String updateDeliveryInformRead(long id) {
        deliveryInformationService.updateDeliveryInformRead(id);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 根据登录帐号ID获取发布信息
     */
    @RequestMapping("/getDeliveryInformByManagerId")
    @ResponseBody
    public String getDeliveryInformByManagerId() {
        return new JsonResponseBean(deliveryInformationService.getDeliveryInformByManagerId()).toJson();
    }
}
