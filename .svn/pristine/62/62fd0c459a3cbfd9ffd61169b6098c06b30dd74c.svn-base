package com.ylife.chinapost.service.impl;

import com.ylife.authority.mapper.AuthorityMapper;
import com.ylife.authority.mapper.pojo.AuthWithMAmountResult;
import com.ylife.authority.model.Authority;
import com.ylife.authority.model.ResourcePage;
import com.ylife.authority.service.AuthorityService;
import com.ylife.authority.service.ResourcePageService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.RoleManageService;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.mapper.pojo.ManagerWithAuthNameResult;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.exception.UserOperationException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XiaoBiaoGe on 2016/4/16.
 * <p/>
 * RoleManagerServiceImpl
 */
@Service
public class RoleManagerServiceImpl implements RoleManageService {

    @Resource
    private EnterpriseManagerService enterpriseManagerService;
    @Resource
    private AuthorityService authorityService;
    @Resource
    private ResourcePageService resourcePageService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private AuthorityMapper authorityMapper;
    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;


    @Override
    public List<AuthWithMAmountResult> selectAuthResultByEnterpriseId() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return authorityMapper.selectAuthResultByEnterpriseId(enterpriseId);
    }


    @Override
    public void addNewAuthority(String name, long[] pageIds, String remark) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        authorityService.addAuthority(enterpriseId, managerId, name, pageIds, remark);
    }


    @Override
    public void editAuthority(long authorityId, long[] pageIds, String name, String remark) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        authorityService.editAuthority(enterpriseId, managerId, authorityId, name, pageIds, remark);
    }

    @Override
    public void deleteAuthority(long authorityId) {
        authorityService.deleteAuthority(authorityId);
    }


    @Override
    public List<ManagerWithAuthNameResult> getMyManagerInfo() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseManagerMapper.selcetManagerResultByEnterpriseId(enterpriseId);
    }

    @Override
    public void addNewManager(String username, String password, String staffname, String photoImg, String cellphone, long authorityId) {
        if(authorityMapper.selectByPrimaryKey(authorityId)==null){
            throw new IllegalArgumentException("角色不存在");
        }
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        Long lastInsertLongId = enterpriseManagerService.addEnterpriseManager(enterpriseId, false, username, password, cellphone, photoImg, staffname, managerId);
        authorityService.setManagerAuthority(lastInsertLongId, authorityId);
    }

    @Override
    public void deleteManager(long managerId) {
        EnterpriseManager manager = enterpriseManagerMapper.selectByPrimaryKey(managerId);
        if (manager.getIsPrimary()) {
            throw new UserOperationException("主帐号无法删除。");
        }
        enterpriseManagerService.deleteEnterpriseManager(managerId);
    }

    @Override
    public void editManagerPassword(long managerId, String password) {
        EnterpriseManager manager = enterpriseManagerMapper.selectByPrimaryKey(managerId);
        if(manager!=null && manager.getUsername().equals(password)){
            throw new IllegalArgumentException("登录密码不能与帐号相同");
        }
        enterpriseManagerService.updatePassword(managerId, password);
    }

    //编辑员工账号
    @Override
    public void editManagerBuff(long managerId, String staffname, String cellphone, boolean nonDisabled, long authorityId) {
        enterpriseManagerService.editManagerBuff(managerId, staffname, cellphone, nonDisabled);
        EnterpriseManager manager = enterpriseManagerMapper.selectByPrimaryKey(managerId);
        if (!manager.getIsPrimary()) {
            authorityService.setManagerAuthority(managerId, authorityId);
        }
    }

    @Override
    public List<ResourcePage> getAuthorityPages(long authorityId) {
        return resourcePageService.getAuthorityPages(authorityId);
    }

    @Override
    public List<ResourcePage> getTopPages() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseManager manager = enterpriseManagerMapper.selectByEnterpriseIdAndIsPrimary(enterpriseId, true);
        Authority authority = authorityMapper.selectByManagerId(manager.getId());
        //TODO 修改为只取得top页面
        return resourcePageService.getAuthorityPages(authority.getId());
    }

    @Override
    public List<ResourcePage> getSonPages(long pageId) {
        return resourcePageService.getSonPages(pageId);
    }


}