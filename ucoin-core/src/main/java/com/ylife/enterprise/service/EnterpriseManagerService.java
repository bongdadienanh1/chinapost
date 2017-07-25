package com.ylife.enterprise.service;

import com.ylife.enterprise.model.EnterpriseManager;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/10.
 * 企业管理员服务
 */
public interface EnterpriseManagerService {

    /**
     * 添加新的企业管理员。
     *
     * @param enterpriseId 企业ID
     * @param isPrimary    是否为主账号
     * @param username     用户名
     * @param password     密码
     * @param cellphone    手机号
     * @param photoImg     头像
     * @param staffname    真实姓名
     * @param managerId    创建者ID
     * @return 返回添加的管理员ID
     */
    long addEnterpriseManager(Long enterpriseId,
                              Boolean isPrimary,
                              String username,
                              String password,
                              String cellphone,
                              String photoImg,
                              String staffname,
                              Long managerId);

    /**
     * 获取管理员主账号
     *
     * @param enterpriseId 企业ID
     */
    EnterpriseManager getPrimaryManager(long enterpriseId);

    /**
     * 删除管理员
     *
     * @param managerId 管理员ID
     */
    void deleteEnterpriseManager(long managerId);

    /**
     * 修改密码
     *
     * @param managerId 管理员ID
     * @param password  密码
     */
    void updatePassword(long managerId, String password);

    /**
     * 校验密码。
     *
     * @param managerId 管理员ID
     * @param password  密码
     */
    boolean checkPassword(long managerId, String password);

    /**
     * 是否冻结管理员账号
     *
     * @param managerId   管理员ID
     * @param staffname   真实姓名
     * @param cellphone   联系电话
     * @param nonDisabled 是否冻结
     */
    void editManagerBuff(long managerId, String staffname, String cellphone, boolean nonDisabled);

    /**
     * 获取所有管理员
     *
     * @param enterpriseId 企业ID
     */
    List<EnterpriseManager> getManagerByEnterpriseId(Long enterpriseId);


    /**
     * 获取所有管理员
     *
     */
    List<EnterpriseManager> getAllManager();
}
