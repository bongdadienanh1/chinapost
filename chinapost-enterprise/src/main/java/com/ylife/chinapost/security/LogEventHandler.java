package com.ylife.chinapost.security;

import com.ylife.chinapost.service.OperationLogService;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.security.event.EventHandler;
import com.ylife.security.event.SecurityEvent;
import com.ylife.utils.IPUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by InThEnd on 2016/12/15.
 * LogEventHandler
 */
@Component
public class LogEventHandler implements EventHandler {

    @Resource
    private OperationLogService operationLogService;

    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;

    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @Override
    public void handler(SecurityEvent event) {
        EnterpriseManager enterpriseManager = enterpriseManagerMapper.selectByUsername(event.getUsername());
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(enterpriseManager.getEnterpriseId());
        switch (event.getEventType()) {
            case LOGIN_SUCCESS:
                //写登录日志
                operationLogService.addOperationLog(enterpriseInfo.getEnterpriseName(),event.getUsername(), IPUtil.getIpAddr((HttpServletRequest)event.getRequest()),"登录","",enterpriseInfo.getEnterpriseId(),enterpriseManager.getId());
                break;
            case LOGOUT_SUCCESS:
                //写登出日志
                operationLogService.addOperationLog(enterpriseInfo.getEnterpriseName(),event.getUsername(), IPUtil.getIpAddr((HttpServletRequest)event.getRequest()),"退出","",enterpriseInfo.getEnterpriseId(),enterpriseManager.getId());
                break;
        }
    }
}
