package com.ylife.chinapost.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * 会员管理服务。
 */
public interface CustomerManageService {


    /**
     * 获取客户的分页数据。
     * 所有的参数都是可选的，都为空则返回所有用户。
     *
     * @param idCard         身份证号
     * @param linkPhone      联系电话
     * @param name           姓名
     * @param isActive       是否激活
     * @param isPhoneChecked 手机号是否验证
     * @param managerNo      客户经理号
     * @param tagName        标签名称
     * @param enterpriseId   企业ID
     * @param pageable       分页信息
     */
    Page<ChinapostCustomer> getCustomers(String idCard,
                                         String linkPhone,
                                         String name,
                                         Boolean isActive,
                                         Boolean isPhoneChecked,
                                         String managerNo,
                                         String tagName,
                                         Long enterpriseId,
                                         Pageable pageable);

    /**
     * U宝扣减。
     *
     * @param customerId 客户ID
     * @param amount     扣减金额
     */
    void ucoinDeduct(long customerId, BigDecimal amount, String paykey, String remark);

    /**
     * 激活用户。
     *
     * @param customerId 用户ID
     * @param password   用户密码
     */
    void activeCustomer(long customerId, String password);

    /**
     * 增加新用户。
     *
     * @param idCard     身份证号
     * @param name       姓名
     * @param linkPhone  联系电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @param addr       详细地址
     * @param image      头像
     * @param managerNo  客户经理号
     * @param tags       标签
     * @param remark     备注
     */
    void newCustomer(String idCard,
                     String name,
                     String linkPhone,
                     long provinceId,
                     long cityId,
                     long districtId,
                     String addr,
                     String image,
                     String managerNo,
                     List<Integer> tags,
                     String remark);

    /**
     * 编辑新用户。
     *
     * @param id         用户ID
     * @param name       姓名
     * @param linkPhone  联系电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @param addr       详细地址
     * @param managerNo  客户经理号
     * @param tags       标签
     * @param remark     备注
     * @param imgUrl     用户头像
     */
    void editCustomer(long id,
                      String name,
                      String linkPhone,
                      long provinceId,
                      long cityId,
                      long districtId,
                      String addr,
                      String managerNo,
                      List<Integer> tags,
                      String remark,
                      String imgUrl);

    /**
     * 重置密码。
     *
     * @param customerId 用户ID
     * @param password   用户密码
     */
    void resetPassword(long customerId, String password);

    /**
     * 重置支付密码。
     *
     * @param customerId 用户ID
     * @param paykey     支付密码
     */
    void resetPaykey(long customerId, String paykey);

    /**
     * U宝扣减（可扣成负数）,网点扣为负值，会员本网点邮豆也扣成负值
     */
    void ucoinDeductNew(long customerId,BigDecimal amount,Date businessTime,String paykey,String remark);

}
