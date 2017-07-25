package com.ylife.chinapost.service;

/**
 * Created by InThEnd on 2016/6/16.
 * 访问控制服务。
 */
public interface DataAccessService {

    /**
     * 企业访问单据。
     */
    public void enterpriseAcessBill(long enterpriseId, long billId);

}
