package com.ylife.authority.service.impl;

import com.ylife.authority.mapper.AuthorityMapper;
import com.ylife.authority.mapper.AuthorityResoucePageMapper;
import com.ylife.authority.mapper.ManagerAuthorityMapper;
import com.ylife.authority.model.Authority;
import com.ylife.authority.model.AuthorityPageKey;
import com.ylife.authority.model.ManagerAuthorityKey;
import com.ylife.authority.service.AuthorityService;
import com.ylife.exception.UserOperationException;
import com.ylife.utils.Assert;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/9.
 * AuthorityServiceImpl
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {
    @Resource
    private AuthorityMapper authorityMapper;
    @Resource
    private AuthorityResoucePageMapper authorityResoucePageMapper;
    @Resource
    private ManagerAuthorityMapper managerAuthorityMapper;

    @Override
    public void addAuthority(long enterpriseId, long managerId, String authorityName, long[] pageIds, String remark) {
        Date now = new Date();
        Authority authority = new Authority();
        authority.setEnterpriseId(enterpriseId);
        authority.setCharacterization(remark);
        authority.setCreatorId(managerId);
        authority.setCreateTime(now);
        authority.setModTime(now);
        authority.setDesignation(authorityName);
        try {
            authorityMapper.insertSelective(authority);
        } catch (DuplicateKeyException e) {
            throw new UserOperationException("角色名称不能重复！");
        }

        Long athorityId = authority.getId();
        setAuthorityPages(athorityId, pageIds);
    }

    @Override
    public void editAuthority(long enterpriseId, long managerId, long authorityId, String authorityName, long[] pageIds, String remark) {
        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        Assert.notNull(authority, "权限不存在。");
        Assert.isTrue(!Authority.isAdmin(authority), "基础权限无法编辑。");
        Authority model = new Authority();
        model.setEnterpriseId(enterpriseId);
        model.setCharacterization(remark);
        model.setCreatorId(managerId);
        model.setDesignation(authorityName);
        model.setId(authorityId);
        authorityMapper.updateByPrimaryKeySelective(model);
        setAuthorityPages(authorityId, pageIds);
    }

    @Override
    public void deleteAuthority(long authorityId) {
        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        List<ManagerAuthorityKey> key = managerAuthorityMapper.selectByAuthorityId(authorityId);
        for(ManagerAuthorityKey managerAuthorityKey:key) {
            if (managerAuthorityKey.getManagerId() != null) {
                throw new UserOperationException("该权限下仍有员工，不能删除！");
            }
        }
        Assert.notNull(authority, "权限不存在。");
        Assert.isTrue(!Authority.isAdmin(authority), "基础权限无法删除。");
        authorityMapper.deleteByPrimaryKey(authorityId);
        authorityResoucePageMapper.deleteByAuthorityId(authorityId);
    }

    @Override
    public void setAuthorityPages(long authorityId, long[] pageIds) {
        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        Assert.notNull(authority, "权限不存在。");
        Assert.isTrue(!Authority.isAdmin(authority), "基础权限无法编辑。");
        List<AuthorityPageKey> authorityPageKeys = new ArrayList<>();
        for (long pageId : pageIds) {
            AuthorityPageKey authorityPageKey = new AuthorityPageKey();
            authorityPageKey.setAuthorityId(authorityId);
            authorityPageKey.setPageId(pageId);
            authorityPageKeys.add(authorityPageKey);
        }
        authorityResoucePageMapper.deleteByAuthorityId(authorityId);
        authorityResoucePageMapper.batchInsert(authorityPageKeys);
    }

    @Override
    public void addAuthorityPage(long authorityId, long pageId) {
        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        Assert.notNull(authority, "权限不存在。");
        Assert.isTrue(!Authority.isAdmin(authority), "基础权限无法编辑。");
        if (!authorityResoucePageMapper.exist(new AuthorityPageKey(authorityId, pageId))) {
            authorityResoucePageMapper.insert(new AuthorityPageKey(authorityId, pageId));
        }
    }

    @Override
    public void deleteAuthorityPage(long authorityId, long pageId) {
        Authority authority = authorityMapper.selectByPrimaryKey(authorityId);
        Assert.notNull(authority, "权限不存在。");
        Assert.isTrue(!Authority.isAdmin(authority), "基础权限无法编辑。");
        authorityResoucePageMapper.deleteByPrimaryKey(new AuthorityPageKey(authorityId, pageId));
    }

    @Override
    public List<Authority> getEnterpriseAuthorities(long enterpriseId) {
        return authorityMapper.selectByEnterpriseId(enterpriseId);
    }

    @Override
    public Authority getManagerAuthority(long managerId) {
        return authorityMapper.selectByManagerId(managerId);
    }

    @Override
    @Transactional
    public void setManagerAuthority(long managerId, long authorityId) {
        if (managerAuthorityMapper.selectByManagerIdForUpdate(managerId) == null) {
            managerAuthorityMapper.insert(new ManagerAuthorityKey(managerId, authorityId));
        } else {
            managerAuthorityMapper.updateAuthorityIdByManagerId(managerId, authorityId);
        }
    }

    @Override
    public void deleteManagerAuthority(long managerId, long authorityId) {
        managerAuthorityMapper.deleteByPrimaryKey(new ManagerAuthorityKey(managerId, authorityId));
    }

    @Override
    public Authority getAdmin() {
        return authorityMapper.selectByEnterpriseIdAndName(0L, Authority.ADMIN_NAME);
    }

    @Override
    public Authority getSecondAdmin() {
        return authorityMapper.selectByEnterpriseIdAndName(0L, Authority.SECOND_NAME);
    }

    /**
     * 中间级权限
     *
     * @return
     */
    @Override
    public Authority getNotAdmin() {
        return authorityMapper.selectByEnterpriseIdAndName(0L,Authority.NOT_NAME);
    }
}
