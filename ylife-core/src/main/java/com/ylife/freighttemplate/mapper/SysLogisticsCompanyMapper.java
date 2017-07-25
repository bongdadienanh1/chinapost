package com.ylife.freighttemplate.mapper;

import com.ylife.freighttemplate.model.SysLogisticsCompany;

import java.util.List;

/**
 * 查询物流公司 2014-102-17
 * 
 * @author ggn
 *
 */
public interface SysLogisticsCompanyMapper {

    /**
     * 查询物流公司信息根据Id
     * 
     * @param logComId
     * @return SysLogisticsCompany
     */
    SysLogisticsCompany selectCompanyById(Long logComId);

    /**
     * 查询所有快递公司
     * 
     * @return List
     */
    List<SysLogisticsCompany> selectAllCompnay();

}
