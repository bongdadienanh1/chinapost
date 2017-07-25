package com.ylife.chinapost.boss.service.impl;

import com.ylife.chinapost.boss.service.ManagerOuterService;
import com.ylife.manager.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by suibian on 2017-06-12.
 */
@Service
public class ManagerOuterServiceImp implements ManagerOuterService {
    @Resource
    private ManagerService managerService;

    /**
     *邮政boss登录
     */
    public int ifManager(HttpServletRequest request, String name, String password) {
        Integer num = 2;
        if(!"".equals(name) && !"".equals(password)){
            num = managerService.loginManager(request,name,password);
        }
        return num;
    }
}
