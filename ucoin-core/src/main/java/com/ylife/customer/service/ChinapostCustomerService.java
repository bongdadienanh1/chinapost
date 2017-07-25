package com.ylife.customer.service;

import com.ylife.customer.model.ChinapostCustomer;

import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/5.
 * <p/>
 * 用户相关服务。
 */
public interface ChinapostCustomerService {

    /**
     * 根据身份证号获取用户。
     *
     * @param idCardNo 身份证号
     */
    ChinapostCustomer getCustomer(String idCardNo);

    /**
     * 根据身份证号获取用户。
     *
     * @param customerId 客户ID
     */
    ChinapostCustomer getCustomer(Long customerId);

    /**
     * 用户是否存在。
     *
     * @param idCardNo 身份证号
     */
    boolean isExist(String idCardNo);


    /**
     * 新增用户。
     *
     * @param idCard      身份证号
     * @param name        姓名
     * @param linkPhone   联系电话
     * @param provinceId  省ID
     * @param cityId      市ID
     * @param districtId  区ID
     * @param addr        详细地址
     * @param image       头像
     * @param managerNo   客户经理号
     * @param tags        标签
     * @param remark      备注
     * @param contactAddr 联系地址
     */
    long addCustomer(String idCard,
                     String name,
                     String linkPhone,
                     Long provinceId,
                     Long cityId,
                     Long districtId,
                     String addr,
                     String image,
                     String managerNo,
                     Long enterpriseId,
                     Long creatorId,
                     List<Integer> tags,
                     String remark,
                     String contactAddr);

    /**
     * 编辑用户。
     *
     * @param id          客户ID
     * @param name        姓名
     * @param linkPhone   联系电话
     * @param provinceId  省ID
     * @param cityId      市ID
     * @param districtId  区ID
     * @param addr        详细地址
     * @param managerNo   客户经理号
     * @param tags        标签
     * @param remark      备注
     * @param contactAddr 联系地址
     * @param imgUrl      头像
     */
    void editCustomer(long id,
                      String name,
                      String linkPhone,
                      Long provinceId,
                      Long cityId,
                      Long districtId,
                      String addr,
                      String managerNo,
                      List<Integer> tags,
                      String remark,
                      String contactAddr,
                      String imgUrl);

    /**
     * 激活用户。
     *
     * @param idCardNo 身份证号
     * @param password 密码
     */
    void activateCustomer(String idCardNo, String password);

    /**
     * 激活用户。
     *
     * @param id       用户ID
     * @param password 密码
     */
    void activateCustomer(long id, String password);

    /**
     * 设置支付密码。
     *
     * @param idCardNo 身份证号
     * @param paykey   支付密码
     */
    void setPaymentPassword(String idCardNo, String paykey);

    /**
     * 设置登录密码。
     *
     * @param id       用户ID
     * @param password 密码
     */
    void setLoginPassword(long id, String password);


    /**
     * 设置支付密码。
     *
     * @param id     用户ID
     * @param paykey 支付密码
     */
    void setPaymentPassword(long id, String paykey);

    /**
     * 验证支付密码
     */
    boolean checkPaykey(long customerId, String paykey);

    /**
     * 验证登录密码
     */
    boolean checkpassword(long customerId, String password);

    /**
     * 修改登录密码
     *
     * @param customerId
     * @param password
     */
    void updatePassword(long customerId, String password);

    void updateByBatch(Map<Long,ChinapostCustomer> map);

    /**zx
     * 修改性别，联系电话
     */
    void updateMaleAndPhone(Long id,boolean male,String contactPhone,String phoneNo);

}
