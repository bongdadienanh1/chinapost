package com.ylife.chinapost.security.service.impl;

import com.ylife.authority.mapper.AuthorityMapper;
import com.ylife.authority.mapper.AuthorityResoucePageMapper;
import com.ylife.authority.mapper.ManagerAuthorityMapper;
import com.ylife.authority.mapper.ResourcePageMapper;
import com.ylife.authority.model.Authority;
import com.ylife.authority.model.AuthorityPageKey;
import com.ylife.authority.model.ManagerAuthorityKey;
import com.ylife.authority.model.ResourcePage;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.chinapost.security.service.EnterpriseSecurityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/9.
 */
@Service
public class EnterpriseSecurityServiceImpl implements EnterpriseSecurityService {

    @Resource
    private AuthorityResoucePageMapper authorityResoucePageMapper;
    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;
    @Resource
    private ManagerAuthorityMapper managerAuthorityMapper;
    @Resource
    private ResourcePageMapper resourcePageMapper;
    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public EnterpriseManager findByLoginName(String username) {
        return enterpriseManagerMapper.selectByUsername(username);
    }

    @Override
    public Long getAuthorityId(Long managerId) {
        ManagerAuthorityKey key = managerAuthorityMapper.selectByManagerId(managerId);
        if (key == null) {
            return null;
        }
        return key.getAuthorityId();
    }

    @Override
    public boolean check(long authorityId, long resourceId) {
        return authorityResoucePageMapper.exist(new AuthorityPageKey(authorityId, resourceId));
    }

    /**
     * 获取所有资源
     *
     * @return
     */
    @Override
    public List<ResourcePage> getAllResourcePage() {
        return resourcePageMapper.selectAllResourcePage();
    }

    /**
     * 获取所有权限
     *
     * @return
     */
    @Override
    public List<Authority> getAllAuthority() {
        return authorityMapper.selectAllAuthority();
    }

    /**
     * 根据资源获取权限
     *
     * @param pageId 资源ID
     * @return
     */
    @Override
    public List<Long> getAuthorityIdByPageId(Long pageId) {
        return authorityResoucePageMapper.selectAuthorityIdByPageId(pageId);
    }

    /**
     * 根据资源获取权限及上级权限
     *
     * @param pageId 资源ID
     * @return
     */
    @Override
    public List<Long> getAuthorityIdAndParentByPageId(Long pageId) {
        return authorityResoucePageMapper.selectAuthorityIdAndParentByPageId(pageId);
    }

    /**
     * 根据url根据上级的Url
     *
     * @param url
     * @return
     */
    @Override
    public ResourcePage getParentUrlByUrl(String url) {
        return resourcePageMapper.selectParentUrlByUrl(url);
    }


}
