package com.ylife.chinapost.service.impl;

import com.ylife.cache.annotation.lifecircle.CacheCreate;
import com.ylife.cache.annotation.lifecircle.CacheRead;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.HierarchyManageService;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/14.
 * HierarchyManagerServiceImpl
 */
@Service
public class HierarchyManageServiceImpl implements HierarchyManageService {

    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private EnterpriseManagerService enterpriseManagerService;

    @Override
    public List<EnterpriseInfo> getSonEnterpriseInfo() {
        return enterpriseInfoService.getEnterpriseInfos(currentLoginService.getCurrentLoginEnterpriseId());
    }

    @Override
    public void addSonEnterprise(String name, BigDecimal discountPct, boolean end, Boolean hasPermit, String username, String password, String organizationName) {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        if (function.getEnd()) {
            throw new UserOperationException("此节点为网点，无法创建子账号。");
        }
        if (hasPermit == null) {
            hasPermit = false;
        }
        if (end && StringUtil.isBlank(organizationName)) {
            throw new UserOperationException("网点的机构编号不能为空。");
        }
        enterpriseService.addEnterprise(function.getId(), discountPct, end, hasPermit, name, username, password, organizationName);
    }

    @Override
    public void editEnterprise(long enterpriseId, BigDecimal discountPct, Boolean end, Boolean hasPermit, String name, String organizationName) {
        if (end && StringUtil.isBlank(organizationName)) {
            throw new UserOperationException("网点的机构编号不能为空。");
        }
        enterpriseService.editEnterprise(enterpriseId, discountPct, end, hasPermit, name, null, organizationName);
    }

    @Override
    public void deleteEnterprise(long enterpriseId) {
        enterpriseService.deleteEnterprise(enterpriseId);
    }

    @Override
    public void editPassword(long enterpriseId, String password) {
        EnterpriseManager manager = enterpriseManagerService.getPrimaryManager(enterpriseId);
        if (manager != null) {
            if (manager.getUsername().equals(password)) {
                throw new IllegalArgumentException("登录密码不能与帐号相同");
            }
            enterpriseManagerService.updatePassword(manager.getId(), password);
        }
    }

    @Override
    public void editPayKey(long enterpriseId, String payKey) {
        enterpriseService.editEnterprise(enterpriseId, null, null, null, null, payKey, null);
    }
}
