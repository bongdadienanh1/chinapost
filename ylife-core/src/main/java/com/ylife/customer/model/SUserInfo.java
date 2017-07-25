package com.ylife.customer.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/11/10.
 */
public class SUserInfo implements Serializable {
    /**
     * customer 类
     */
    private Customer customer;
    //customerId
    private Long customerId;
    //存放微信的openid
    private String openid;
    // 存放用户绑定信息
    private Long threePartIsband;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getThreePartIsband() {
        return threePartIsband;
    }

    public void setThreePartIsband(Long threePartIsband) {
        this.threePartIsband = threePartIsband;
    }




}
