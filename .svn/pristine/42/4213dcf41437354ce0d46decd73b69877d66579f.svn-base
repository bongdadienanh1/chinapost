/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.system.mapper;


import com.ylife.data.page.Pageable;
import com.ylife.system.model.OperationLog;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 操作日志Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:27:46
 * @version 0.0.1
 */
public interface OperaLogMapper {
    /**
     * 添加操作日志
     * 
     * @param log
     *            日志对象 {@link OperationLog}
     * @return
     */
    int insertSelective(OperationLog log);

    /**
     * 操作日志总行数
     * @param enterpriseId
     * @param otStartTime
     * @param otEndTime
     * @return
     */
    int selectOperaSize(@Param("enterpriseId") Long enterpriseId,
                         @Param("otStartTime") Date otStartTime,
                         @Param("otEndTime") Date otEndTime);

    /**
     * 查询日志列表
     * @param otStartTime
     * @param otEndTime
     * @param pageable
     * @return
     */
    List<OperationLog> selectAllOpera(@Param("enterpriseId") Long enterpriseId,
                                      @Param("otStartTime") Date otStartTime,
                                      @Param("otEndTime") Date otEndTime,
                                      @Param("pageable") Pageable pageable);
}
