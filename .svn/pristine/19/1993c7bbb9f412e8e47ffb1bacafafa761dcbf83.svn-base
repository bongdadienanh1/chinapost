package com.ylife.enterprise.service;

import com.ylife.enterprise.model.EnterpriseInfo;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/27.
 * 企业信息服务
 */
public interface EnterpriseInfoService {


    /**
     * 根据父ID获取EnterpriseInfo列表。
     *
     * @param parentId 父企业ID
     */
    List<EnterpriseInfo> getEnterpriseInfos(long parentId);

    /**
     * 获取所有的机构名称
     * @return
     */
    List<EnterpriseInfo> getAllOrganization();

    /**
     * 根据机构名称获取info信息
     * @param organization
     * @return
     */
    EnterpriseInfo getEnterpriseInfoByOrganization(String organization);



    /**
     * 获取顶级企业信息。（不会获取用户名。）
     */
    EnterpriseInfo getTopEnterpriseInfo();


    /**
     * 根据ID获取企业信息。
     *
     * @param enterpriseId 企业ID
     */
    EnterpriseInfo getEnterpriseInfo(long enterpriseId);

    /**
     * 根据与用户的联系获取企业信息。
     *
     * @param customerId 用户ID
     */
    List<EnterpriseInfo> getCustomerLinkedEnterpriseInfo(long customerId);


    /**
     * 根据区域ID获取企业信息。
     *
     * @param districtId 区ID
     */
    List<EnterpriseInfo> getEnterpriseInfosByDistrictId(long districtId);
}
