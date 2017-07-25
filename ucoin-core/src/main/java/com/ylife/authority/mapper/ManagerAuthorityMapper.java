package com.ylife.authority.mapper;

import com.ylife.authority.model.ManagerAuthorityKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/10.
 */
public interface ManagerAuthorityMapper {

    void deleteByPrimaryKey(ManagerAuthorityKey key);

    void deleteByManagerId(Long managerId);

    void insert(ManagerAuthorityKey key);

    boolean exist(ManagerAuthorityKey key);

    ManagerAuthorityKey selectByManagerId(Long managerId);

    List<ManagerAuthorityKey> selectByAuthorityId(Long authorityId);

    ManagerAuthorityKey selectByManagerIdForUpdate(Long managerId);

    int updateAuthorityIdByManagerId(@Param("managerId")Long managerId,
                                     @Param("authorityId")Long authorityId);

    int countByEnterpriseId(long enterpriseId);

}
