package com.ylife.manager.service.impl;

import com.ylife.manager.mapper.ManagerMapper;
import com.ylife.manager.model.Manager;
import com.ylife.manager.service.ManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by suibian on 2017-06-12.
 */
@Service
public class ManagerServiceImp implements ManagerService {
    @Resource
    private ManagerMapper managerMapper;
    /**
     * 邮政boss登录
     * @return 0 用户存在 1用户不存在
     * */
    @Override
    public int loginManager(HttpServletRequest request, String name, String password) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",name);
        map.put("password",password);
        Integer num;
        Manager manager = managerMapper.selectCustomerByNamePwd(map);
        if(manager != null){
            //用户名密码存在
            num = 0;
            request.getSession().setAttribute("name", name);
            request.getSession().setAttribute("loginUserId", manager.getId());
            request.getSession().setAttribute("photoImg", manager.getPhotoImg());
        }else {
            //用户名密码不存在
            num = 1;
        }
        return num;
    }
}
