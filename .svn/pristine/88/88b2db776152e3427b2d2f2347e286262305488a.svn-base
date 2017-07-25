package com.ylife.chinapost.third.service.impl;

import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.exception.UserNotLoginException;
import com.ylife.utils.WebUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * third端 当前登录用户
 */
@Service
public class CurrentLoginServiceImpl implements CurrentLoginService {

    /**
     * 获取当前登录用户的ID
     *
     * @return
     */
    @Override
    public Long getCurrentLoginThirdId() {
        Long customerId = (Long) WebUtil.getCurrentSession().getAttribute("loginStoreId");
        if (customerId == null) {
            throw new UserNotLoginException("用户未登录。");
        }
        return customerId;
    }

    /**
     * 获取当前登录用户的店铺名称
     *
     * @return
     */
    @Override
    public String getCurrentStoreName() {
        String storeName = (String) WebUtil.getCurrentSession().getAttribute("loginStoreName");
        if (StringUtils.isBlank(storeName)) {
            throw new UserNotLoginException("用户未登录。");
        }
        return storeName;
    }
}
