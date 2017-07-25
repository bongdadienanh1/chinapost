package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.OperationLog;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/9.
 */
public interface OperationLogService {

    /**
     * 添加日志信息
     * @return
     */
    int addOperationLog(String opEnterpriseName, String opName,String opIp,String opCode,String opContent,Long enterpriseId,Long managerId);


    /**
     * 查询日志信息
     * @param enterpriseId
     * @param otStart
     * @param otEnd
     * @param pageable
     * @return
     */
    Page<OperationLog> getOperationLogList(Long enterpriseId,Date otStart,Date otEnd,Pageable pageable);
}
