package com.ylife.enterprise.mapper;


import com.ylife.enterprise.mapper.pojo.ManagerWithAuthNameResult;
import com.ylife.enterprise.model.EnterpriseManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EnterpriseManagerMapper {

    int deleteByPrimaryKey(Long id);

    int deleteByEnterpriseId(Long enterpriseId);

    int insert(EnterpriseManager record);

    int insertSelective(EnterpriseManager record);

    EnterpriseManager selectByPrimaryKey(Long id);

    EnterpriseManager selectByUsername(String username);

    EnterpriseManager selectByEnterpriseIdAndIsPrimary(@Param("enterpriseId") Long enterpriseId, @Param("isPrimary") Boolean isPrimary);

    int updateByPrimaryKeySelective(EnterpriseManager record);

    int updateByPrimaryKey(EnterpriseManager record);

    boolean checkPassword(@Param("managerId") Long managerId, @Param("password") String password);

    /**
     * 获取所有管理员
     *
     * @param enterpriseId 企业ID
     */
    List<EnterpriseManager> selectByEnterpriseId(Long enterpriseId);

    /**
     * 获取管理员manager的所有权限authoritys
     *
     * @param enterpriseId 企业ID
     */
    List<ManagerWithAuthNameResult> selcetManagerResultByEnterpriseId(Long enterpriseId);

    /**
     * 修改管理员密码
     *
     * @param id       管理员ID
     * @param password 密码
     */
    int updatePasswordByManagerId(@Param("id") Long id, @Param("password") String password);

    /**
     * 编辑管理员
     *
     * @param id          管理员ID
     * @param staffname   姓名
     * @param cellphone   手机号
     * @param nonDisabled 是否禁用
     */
    int updateManagerBuff(@Param("id") Long id,
                          @Param("staffname") String staffname,
                          @Param("cellphone") String cellphone,
                          @Param("nonDisabled") Boolean nonDisabled);


    /**
     * 获取所有管理员
     *
     */
    List<EnterpriseManager> selectAllManager();

}