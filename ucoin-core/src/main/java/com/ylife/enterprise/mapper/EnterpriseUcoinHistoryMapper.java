package com.ylife.enterprise.mapper;

import com.ylife.enterprise.model.EnterpriseUcoinHistory;

public interface EnterpriseUcoinHistoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(EnterpriseUcoinHistory record);

    int insertSelective(EnterpriseUcoinHistory record);

    EnterpriseUcoinHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(EnterpriseUcoinHistory record);

    int updateByPrimaryKey(EnterpriseUcoinHistory record);
}