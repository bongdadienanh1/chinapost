package com.ylife.wealth.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.EnterpriseAllocation;
import com.ylife.wealth.model.EnterpriseBatchAllocation;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/13.
 * <p/>
 * 财富分配服务。
 */
public interface EnterpriseAllocationService {

    /**
     * 批量分配。
     *
     * @param enterpriseId 分配企业ID(发钱的老大)
     * @param managerId    操作者ID
     * @param allocations  分配信息
     * @param remark       备注
     */
    void batchAllocat(long enterpriseId, long managerId, Map<Long, BigDecimal> allocations, String remark);

    /**
     * 单个分配。
     *
     * @param enterpriseId 分配企业ID(发钱的老大)
     * @param managerId    操作者ID
     * @param sonId        被分配的企业ID(要钱的儿子)
     * @param amount       分配的U宝数量
     * @param remark       备注
     */
    void singleAllocat(long enterpriseId, long managerId, long sonId, BigDecimal amount, String remark);

    /**
     * 获取父企业给此企业分配的记录。
     *
     * @param enterpriseId 企业ID
     * @param code         单号
     * @param start        开始时间
     * @param end          结束时间
     * @param pageable     分页参数
     */
    Page<EnterpriseAllocation> getParentAllocations(long enterpriseId, Long code, Date start, Date end, Pageable pageable);

    /**
     * 根据批量分配记录ID获取分配记录。
     *
     * @param batchId  批量分配记录
     * @param pageable 分页数据
     */
    Page<EnterpriseAllocation> getAllocationByBatchId(long batchId, Pageable pageable);

    /**
     * 根据企业ID获取企业的分配记录。
     *
     * @param enterpriseId 企业ID
     * @param code         单号
     * @param start        开始时间
     * @param end          结束时间
     * @param pageable     分页信息
     */
    Page<EnterpriseBatchAllocation> getBatchAllocations(long enterpriseId, Long code, Date start, Date end, Pageable pageable);

}
