package com.ylife.enterprise.service.impl;

import com.ylife.enterprise.mapper.EnterpriseInfoMapper;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/27.
 * EnterpriseInfoServiceImpl
 */
@Service
public class EnterpriseInfoServiceImpl implements EnterpriseInfoService {

    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;

    @Override
    public List<EnterpriseInfo> getEnterpriseInfos(long parentId) {
        return enterpriseInfoMapper.selectByParentId(parentId);
    }

    /**
     * 获取所有的机构名称
     *
     * @return
     */
    @Override
    public List<EnterpriseInfo> getAllOrganization() {
        return enterpriseInfoMapper.selectAllOrganization();
    }

    /**
     * 根据机构名称获取info信息
     *
     * @param organization
     * @return
     */
    @Override
    public EnterpriseInfo getEnterpriseInfoByOrganization(String organization) {
        return enterpriseInfoMapper.selectInfoByOrganization(organization);
    }

    @Override
    public EnterpriseInfo getTopEnterpriseInfo() {
        return enterpriseInfoMapper.selectByParentIdIsNull();
    }

    @Override
    public EnterpriseInfo getEnterpriseInfo(long enterpriseId) {
        return enterpriseInfoMapper.selectByPrimaryKey(enterpriseId);
    }

    @Override
    public List<EnterpriseInfo> getCustomerLinkedEnterpriseInfo(long customerId) {
        return enterpriseInfoMapper.selectRightJoinUcoinByCustomerId(customerId);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseInfosByDistrictId(long districtId) {
        return enterpriseInfoMapper.selectByDistrictId(districtId);
    }
}
