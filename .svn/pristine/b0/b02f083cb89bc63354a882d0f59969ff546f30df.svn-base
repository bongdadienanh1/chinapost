package com.ylife.chinapost.service.impl;

import com.ylife.address.service.AddressService;
import com.ylife.chinapost.service.AccountCenterService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.enterprise.mapper.EnterpriseInfoHistoryMapper;
import com.ylife.enterprise.mapper.EnterpriseInfoMapper;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseInfoHistory;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseManagerService;
import com.ylife.exception.UserOperationException;
import com.ylife.utils.StringUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/16.
 * AccountCenterServiceImpl
 */
@Service
public class AccountCenterServiceImpl implements AccountCenterService {

    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private EnterpriseManagerService enterpriseManagerService;
    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;
    @Resource
    private AddressService addressService;
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;
    @Resource
    private EnterpriseInfoHistoryMapper enterpriseInfoHistoryMapper;

    @Override
    public EnterpriseInfo getEnterpriseInfo() {
        return enterpriseInfoService.getEnterpriseInfo(currentLoginService.getCurrentLoginEnterpriseId());
    }

    @Override
    public EnterpriseManager getEnterpriseManagerInfo() {
        return currentLoginService.getCurrentLoginEnterpriseManager();
    }

    @Override
    public EnterpriseInfo getTopEnterpriseInfo() {
        return enterpriseInfoService.getTopEnterpriseInfo();
    }

    @Override
    public void editEnterPriseInfo(String enterpriseName, String profile, String imgUrl, String registerAddress, String linkman,
                                   Long provinceId, Long cityId, Long districtId, String addrDetail, String mobile) {
        EnterpriseInfo model = new EnterpriseInfo();
        long enterpriseId = enterpriseInfoService.getTopEnterpriseInfo().getEnterpriseId();
        String address;
        if (provinceId == null || cityId == null || districtId == null || addrDetail == null) {
            provinceId = null;
            cityId = null;
            districtId = null;
            addrDetail = null;
            address = null;
        } else {
            address = addressService.getAddressString(provinceId, cityId, districtId, addrDetail);
        }
        model.setEnterpriseId(enterpriseId);
        model.setEnterpriseName(enterpriseName);
        model.setProfile(profile);
        model.setImgUrl(imgUrl);
        model.setRegisterAddress(registerAddress);
        model.setLinkman(linkman);
        model.setProvinceId(provinceId);
        model.setCityId(cityId);
        model.setDistrictId(districtId);
        model.setAddrDetail(addrDetail);
        model.setAddress(address);
        model.setMobile(mobile);
        enterpriseInfoMapper.updateByPrimaryKeySelective(model);

         }

    @Override
    public void editAccountInfo(String accountName, Long provinceId, Long cityId, Long districtId, String addrDetail, String linkman, String linkMobile) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseInfo model = new EnterpriseInfo();
        String address;
        if (provinceId == null || cityId == null || districtId == null || addrDetail == null) {
            provinceId = null;
            cityId = null;
            districtId = null;
            addrDetail = null;
            address = null;
        } else {
            address = addressService.getAddressString(provinceId, cityId, districtId, addrDetail);
        }
        model.setEnterpriseId(enterpriseId);
        model.setAccountName(accountName);
        model.setProvinceId(provinceId);
        model.setCityId(cityId);
        model.setDistrictId(districtId);
        model.setAddrDetail(addrDetail);
        model.setAddress(address);
        model.setLinkman(linkman);
        model.setLinkMobile(linkMobile);
        enterpriseInfoMapper.updateByPrimaryKeySelective(model);

        EnterpriseInfo enterpriseInfo=enterpriseInfoMapper.selectByPrimaryKey(enterpriseId);
        EnterpriseInfoHistory historyModel=new EnterpriseInfoHistory();
        historyModel.setEnterpriseId(enterpriseId);
        historyModel.setLinkMan(enterpriseInfo.getLinkman());
        historyModel.setCreateTime(new Date());
        historyModel.setLinkAddress(enterpriseInfo.getAddress());
        historyModel.setLinkMoblie(enterpriseInfo.getLinkMobile());
        enterpriseInfoHistoryMapper.insertSelective(historyModel);

    }

    @Override
    public void editPassword(String prePwd, String password) {
        long managerId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        if (!enterpriseManagerService.checkPassword(managerId, prePwd)) {
            throw new UserOperationException("原密码错误。");
        }
        enterpriseManagerService.updatePassword(managerId, password);
    }

    @Override
    public boolean hasPayKey() {
        return !StringUtil.isBlank(enterpriseFunctionService.get(currentLoginService.getCurrentLoginEnterpriseId()).getPaykey());
    }

    @Override
    public void editPayKey(String prePK, String payKey) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (hasPayKey()) {
            if (!enterpriseFunctionService.checkPayKey(enterpriseId, prePK)) {
                throw new UserOperationException("原密码错误。");
            }
        }
        enterpriseFunctionService.updatePaykey(enterpriseId, payKey);
    }

    /**
     * 修改机构标识
     *
     * @param identification 机构标识
     */
    @Override
    public void editIdentification(String identification) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseInfo info = new EnterpriseInfo();
        info.setEnterpriseId(enterpriseId);
        info.setIdentification(identification);
        try {
            enterpriseInfoMapper.updateByPrimaryKeySelective(info);
        }catch (DuplicateKeyException ex){
            throw new UserOperationException("机构标识已存在。");
        }
    }
}
