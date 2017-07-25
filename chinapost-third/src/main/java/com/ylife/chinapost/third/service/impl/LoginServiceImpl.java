package com.ylife.chinapost.third.service.impl;

import com.ylife.chinapost.third.service.LoginService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatform.service.ThirdPlatformService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by suibian on 2017-06-30.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private ThirdPlatformService thirdPlatformService;
    /**
     * 账号密码登录third
     * @param userName 用户名
     * @param password 密码
     * @return 200登录成功 201账号或密码不存在
     * */
    public Integer checkLogin(HttpServletRequest request,String userName, String password) {
        ThirdPlatformEntity platformEntity = thirdPlatformService.queryByUserNameAndPwd(userName,password);
        if(platformEntity != null){
            request.getSession().setAttribute("loginStoreName", platformEntity.getStoreName());
            request.getSession().setAttribute("loginStoreId", platformEntity.getStoreId());
            request.getSession().setAttribute("thirdUserName", userName);
            return 200;
        }else {
            return 201;
        }
    }
}
