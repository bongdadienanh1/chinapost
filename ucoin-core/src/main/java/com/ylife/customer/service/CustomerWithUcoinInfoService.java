package com.ylife.customer.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;

/**
 * Created by InThEnd on 2016/4/29.
 * 用户邮豆信息。
 */
public interface CustomerWithUcoinInfoService {

    /**
     * 根据身份证号获取用户信息。
     *
     * @param idCardNo 身份证号
     */
    ChinapostCustomer getInfo(String idCardNo);

    /**
     * 根据身份证号和企业ID获取用户信息(包括用户邮豆总额和网点邮豆总额)。
     *
     * @param idCardNo     身份证号
     * @param enterpriseId 企业ID
     */
    ChinapostCustomer getInfo(String idCardNo, long enterpriseId);

    /**
     * 根据身份证号获取用户信息。
     *
     * @param customerId 客户ID
     */
    ChinapostCustomer getInfo(Long customerId);


    /**
     * 根据模型提供的信息获取用户信息。
     *
     * @param queryModel 查询模型
     * @param tagName    标签名称
     * @param maxIndex   最大索引
     * @param minIndex   最小索引
     * @param pageable   分页信息
     */
    Page<ChinapostCustomer> getInfos(ChinapostCustomer queryModel, String tagName, long enterpriseId, Long minIndex, Long maxIndex, Pageable pageable);

}
