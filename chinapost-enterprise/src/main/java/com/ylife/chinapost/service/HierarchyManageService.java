package com.ylife.chinapost.service;

import com.ylife.enterprise.model.EnterpriseInfo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/14.
 * 层级管理服务
 */
public interface HierarchyManageService {


    /**
     * 取得所有子企业。
     */
    List<EnterpriseInfo> getSonEnterpriseInfo();

    /**
     * 添加子企业。
     */
    void addSonEnterprise(String name, BigDecimal discountPct, boolean end, Boolean hasPermit, String username, String password, String organizationName);

    /**
     * 编辑子企业。
     */
    void editEnterprise(long enterpriseId, BigDecimal discountPct, Boolean end, Boolean hasPermit, String name, String organizationName);

    /**
     * 删除子企业。
     */
    void deleteEnterprise(long enterpriseId);

    /**
     * 编辑密码。
     */
    void editPassword(long enterpriseId, String password);

    /**
     * 编辑支付密码。
     */
    void editPayKey(long enterpriseId, String payKey);
}
