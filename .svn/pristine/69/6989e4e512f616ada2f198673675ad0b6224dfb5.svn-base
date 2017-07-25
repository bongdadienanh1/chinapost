package com.ylife.system.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.BusinessTypeHistory;

import java.util.Date;

/**
 * Created by InThEnd on 2016/7/27.
 * 业务类型历史服务。
 */
public interface BusinessTypeHistoryService {

    /**
     * 添加历史记录。
     *
     * @param typeId    业务类型ID
     * @param typeName  业务类型名称
     * @param operation 操作
     * @param note      备注
     * @param beforeEx  操作之前公式
     * @param afterEx   操作之后公式
     * @param operator  操作人账号
     */
    void addHistory(int typeId, String typeName, String operation, String note, String beforeEx, String afterEx, String operator);

    /**
     * 获取历史记录。
     *
     * @param typeId    业务类型ID
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pageable  分页数据
     */
    Page<BusinessTypeHistory> get(Integer typeId, Date startTime, Date endTime, Pageable pageable);
}
