package com.ylife.authority.mapper;

import com.ylife.authority.mapper.pojo.AuthWithMAmountResult;
import com.ylife.authority.model.Authority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long id);

    Authority selectByManagerId(Long managerId);

    List<Authority> selectByEnterpriseId(Long enterpriseId);

    Authority selectByEnterpriseIdAndName(@Param("enterpriseId") Long enterpriseId, @Param("name") String name);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

    List<AuthWithMAmountResult> selectAuthResultByEnterpriseId(Long enterpriseId);

    List<Authority> selectAllAuthority();

}