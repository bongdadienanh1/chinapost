/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.system.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.OperationLog;

import java.util.Date;

/**
 * 操作日志Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:25:13
 * @version 0.0.1
 */
public interface OperaLogService {

    /**
     * 添加操作日志
     * 
     * @param log
     *            日志对象 {@link OperationLog}
     * @return int
     */
    int addOperaLog(OperationLog log);


    /**
     * 查询操作日志
     * @param enterpriseId
     * @param otStartTime
     * @param otEndTime
     * @param pageable
     * @return
     */
    Page<OperationLog> getOperaLogList(Long enterpriseId,Date otStartTime,Date otEndTime,Pageable pageable);
}
