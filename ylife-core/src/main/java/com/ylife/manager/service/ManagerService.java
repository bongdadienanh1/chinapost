package com.ylife.manager.service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by suibian on 2017-06-12.
 */
public interface ManagerService {
    int loginManager(HttpServletRequest request, String name, String password);
}
