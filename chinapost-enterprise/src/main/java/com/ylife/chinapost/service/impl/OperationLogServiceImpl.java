package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.OperationLogService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.OperationLog;
import com.ylife.system.service.OperaLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/12.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Resource
    private OperaLogService operaLogService;


    /**
     * 添加日志信息
     *
     * @param opEnterpriseName
     * @param opName
     * @param opIp
     * @param opCode
     * @param opContent
     * @param enterpriseId
     * @param managerId
     * @return
     */
    @Override
    public int addOperationLog(String opEnterpriseName, String opName, String opIp, String opCode, String opContent, Long enterpriseId, Long managerId) {
        OperationLog operationLog = new OperationLog();
        operationLog.setOpEnterpriseName(opEnterpriseName);
        operationLog.setOpName(opName);
        operationLog.setOpIp(opIp);
        operationLog.setOpCode(opCode);
        operationLog.setOpContent(opContent);
        operationLog.setEnterpriseId(enterpriseId);
        operationLog.setManagerId(managerId);
        operationLog.setOpTime(new Date());
        return operaLogService.addOperaLog(operationLog);
    }

    /**
     * 查询日志信息
     *
     * @param enterpriseId
     * @param otStart
     * @param otEnd
     * @param pageable
     * @return
     */
    @Override
    public Page<OperationLog> getOperationLogList(Long enterpriseId, Date otStart, Date otEnd, Pageable pageable) {
        return operaLogService.getOperaLogList(enterpriseId,otStart,otEnd,pageable);
    }
}
