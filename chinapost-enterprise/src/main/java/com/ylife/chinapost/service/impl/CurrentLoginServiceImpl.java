package com.ylife.chinapost.service.impl;

import com.ylife.authority.mapper.AuthorityMapper;
import com.ylife.authority.mapper.ResourcePageMapper;
import com.ylife.authority.model.Authority;
import com.ylife.authority.model.ResourcePage;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseInfoMapper;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.exception.UserNotLoginException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/8.
 * CurrentLoginServiceImpl
 */
@Service
public class CurrentLoginServiceImpl implements CurrentLoginService {

    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private ResourcePageMapper resourcePageMapper;
    @Resource
    private AuthorityMapper authorityMapper;

    @Override
    public Long getCurrentLoginEnterpriseId() {
        return getCurrentLoginEnterpriseManager().getEnterpriseId();
    }

    @Override
    public Enterprise getCurrentLoginEnterprise() {
        return enterpriseMapper.selectByPrimaryKey(getCurrentLoginEnterpriseId());
    }

    @Override
    public EnterpriseInfo getCurrentLoginEnterpriseInfo() {
        return enterpriseInfoMapper.selectByPrimaryKey(getCurrentLoginEnterpriseId());
    }

    @Override
    public EnterpriseFunction getCurrentLoginEnterpriseFunc() {
        return enterpriseFunctionMapper.selectByPrimaryKey(getCurrentLoginEnterpriseId());
    }

    @Override
    public EnterpriseManager getCurrentLoginEnterpriseManager() {
        EnterpriseManager manager = enterpriseManagerMapper.selectByUsername(getCurrentLoginUsername());
        if (manager == null) {
            throw new UserNotLoginException();
        }
        return manager;
    }

    @Override
    public Long getCurrentLoginEnterpriseManagerId() {
        return getCurrentLoginEnterpriseManager().getId();
    }

    @Override
    public String getCurrentLoginUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken || authentication == null) {
            throw new UserNotLoginException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userDetails.getUsername();
    }

    @Override
    public boolean isPrimaryEnterprise() {
        return getCurrentLoginEnterprise().getParentId() == null;
    }

    @Override
    public boolean isPrimaryManager() {
        return getCurrentLoginEnterpriseManager().getIsPrimary();
    }

    @Override
    public List<ResourcePage> getCurrentPages() {
        long managerId = getCurrentLoginEnterpriseManagerId();
        Authority authority = authorityMapper.selectByManagerId(managerId);
        if (authority == null) {
            return new ArrayList<>();
        }
        return resourcePageMapper.selectTopByAuthorityId(authority.getId());
    }

    @Override
    public List<Long> getNodeId(List<Long> ids, Long id) {
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(id);
        Long parenId=function.getParentId();
        if(parenId==1){
            return ids ;
        }else {
            ids.add(parenId);
            return getNodeId(ids,parenId);
        }
    }

    @Override
    public List<ResourcePage> getFormCurrentPages() {
        long managerId = getCurrentLoginEnterpriseManagerId();
        Authority authority = authorityMapper.selectByManagerId(managerId);
        if (authority == null) {
            return new ArrayList<>();
        }
        List<ResourcePage> resourcePageList = resourcePageMapper.selectFormByAuthorityId(authority.getId());
        List<ResourcePage> topList = new ArrayList<>();
        for(ResourcePage page :resourcePageList){
            if(page.getParentId()==14){
                topList.add(page);
            }
        }
        for(ResourcePage page :topList){
            List<ResourcePage> secondList = new ArrayList<>();
            for(ResourcePage item : resourcePageList){
                if(page.getId().equals(item.getParentId())){
                    secondList.add(item);
                }
            }
            page.setSecondList(secondList);

        }
        return topList;
    }
}
