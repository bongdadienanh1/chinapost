package com.ylife.enterprise.mapper;

import com.ylife.enterprise.model.EnterpriseInfo;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/21.
 * 企业信息
 */
public interface EnterpriseInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseInfo record);

    int insertSelective(EnterpriseInfo record);

    /**
     * 取出了username字段。
     */
    EnterpriseInfo selectByPrimaryKey(Long id);

    /**
     * 根据parentId字段取出Info的list,此方法关联了manager表取出了username字段。
     */
    List<EnterpriseInfo> selectByParentId(Long parentId);

    /**
     * 获取parent_id字段为null的info,此方法未取出username信息。
     */
    EnterpriseInfo selectByParentIdIsNull();

    int updateByPrimaryKeySelective(EnterpriseInfo record);

    int updateByPrimaryKey(EnterpriseInfo record);

    /**
     * 右连接用户邮宝表，根据用户ID获取。
     */
    List<EnterpriseInfo> selectRightJoinUcoinByCustomerId(Long customerId);

    /**
     * 根据区ID获取企业信息。
     */
    List<EnterpriseInfo> selectByDistrictId(Long districtId);

    /**
     * 获取所有的机构名称
     * @return
     */
    List<EnterpriseInfo> selectAllOrganization();

    /**
     * 获取指定机构名称
     * @param accountName
     * @return
     */
    EnterpriseInfo selectInfoByOrganization(String accountName);
}
