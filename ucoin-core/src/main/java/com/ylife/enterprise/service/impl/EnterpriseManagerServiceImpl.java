package com.ylife.enterprise.service.impl;

import com.ylife.authority.mapper.ManagerAuthorityMapper;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.exception.UserExistException;
import com.ylife.exception.UserOperationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/11.
 * <p/>
 * EnterpriseManagerServiceImpl
 */
@Service
public class EnterpriseManagerServiceImpl implements EnterpriseManagerService {

    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;
    @Resource
    private ManagerAuthorityMapper managerAuthorityMapper;

    @Override
    public long addEnterpriseManager(Long enterpriseId,
                                     Boolean isPrimary,
                                     String username,
                                     String password,
                                     String cellphone,
                                     String photoImg,
                                     String staffname,
                                     Long managerId) {

        EnterpriseManager manager = new EnterpriseManager();
        manager.setEnterpriseId(enterpriseId);
        manager.setIsPrimary(isPrimary);
        manager.setUsername(username);
        manager.setPassword(password);
        manager.setCellphone(cellphone);
        manager.setPhotoImg(photoImg);
        manager.setStaffname(staffname);
        manager.setManagerId(managerId);
        Date now = new Date();
        manager.setCreateTime(now);
        manager.setModTime(now);
        manager.setIsDelete(false);
        try {
            enterpriseManagerMapper.insertSelective(manager);
        } catch (DuplicateKeyException e) {
            throw new UserExistException();
        }
        return manager.getId();
    }

    @Override
    public EnterpriseManager getPrimaryManager(long enterpriseId) {
        return enterpriseManagerMapper.selectByEnterpriseIdAndIsPrimary(enterpriseId, true);
    }

    @Override
    public void deleteEnterpriseManager(long managerId) {
        EnterpriseManager manager = enterpriseManagerMapper.selectByPrimaryKey(managerId);
        if (manager.getIsPrimary() == null || !manager.getIsPrimary()) {
            enterpriseManagerMapper.deleteByPrimaryKey(managerId);
            managerAuthorityMapper.deleteByManagerId(managerId);

        } else {
            throw new UserOperationException("管理员账号无法删除！");
        }
    }

    @Override
    public void updatePassword(long managerId, String password) {
        enterpriseManagerMapper.updatePasswordByManagerId(managerId, password);
    }

    @Override
    public boolean checkPassword(long managerId, String password) {
        return enterpriseManagerMapper.checkPassword(managerId, password);
    }

    @Override
    public void editManagerBuff(long managerId, String staffname, String cellphone, boolean nonDisabled) {
        enterpriseManagerMapper.updateManagerBuff(managerId, staffname, cellphone, nonDisabled);
    }

    @Override
    public List<EnterpriseManager> getManagerByEnterpriseId(Long enterpriseId) {
        return enterpriseManagerMapper.selectByEnterpriseId(enterpriseId);
    }

    /**
     * 获取所有管理员
     */
    @Override
    public List<EnterpriseManager> getAllManager() {
        return enterpriseManagerMapper.selectAllManager();
    }
}
