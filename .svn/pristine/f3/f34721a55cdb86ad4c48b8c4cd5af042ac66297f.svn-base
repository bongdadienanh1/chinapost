package com.ylife.chinapost.service;

import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;

/**
 * Created by InThEnd on 2016/4/16.
 * 帐号中心。
 */
public interface AccountCenterService {

    EnterpriseInfo getEnterpriseInfo();

    EnterpriseManager getEnterpriseManagerInfo();

    EnterpriseInfo getTopEnterpriseInfo();

    void editEnterPriseInfo(String enterpriseName, String profile, String imgUrl, String registerAddress, String linkman,
                            Long provinceId, Long cityId, Long districtId, String addrDetail, String mobile);

    void editAccountInfo(String accountName, Long provinceId, Long cityId, Long districtId, String addrDetail, String linkman, String linkMobile);

    void editPassword(String prePwd, String password);

    /**
     * 是否存在支付密码
     */
    boolean hasPayKey();

    void editPayKey(String prePK, String payKey);

    /**
     * 修改机构标识
     * @param identification 机构标识
     */
    void editIdentification(String identification);
}
