/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.system.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.system.mapper.OperaLogMapper;
import com.ylife.system.model.OperationLog;
import com.ylife.system.service.OperaLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class OperaLogServiceImpl implements OperaLogService {
    @Resource
    private OperaLogMapper operaLogMapper;

    /**
     * 插入操作日志
     * @param log
     *            日志对象 {@link OperationLog}
     * @return
     */
    @Override
    public int addOperaLog(OperationLog log) {
        return operaLogMapper.insertSelective(log);
    }

    /**
     * 查询操作日志
     *
     * @param enterpriseId
     * @param otStartTime
     * @param otEndTime
     * @param pageable
     * @return
     */
    @Override
    public Page<OperationLog> getOperaLogList(Long enterpriseId, Date otStartTime, Date otEndTime, Pageable pageable) {
        List<OperationLog> operationLogs = operaLogMapper.selectAllOpera(enterpriseId, otStartTime, otEndTime, pageable);
        int totalElements = operaLogMapper.selectOperaSize(enterpriseId, otStartTime, otEndTime);
        return new PageImpl<>(pageable, totalElements, operationLogs);
    }
}
