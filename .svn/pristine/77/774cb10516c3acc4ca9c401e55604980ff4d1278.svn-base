package com.ylife.chinapost.service;

import com.ylife.chinapost.service.pojo.WealthManageResult;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseRequisition;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/12.
 * 财富管理service
 */
public interface WealthManageService {

    /**
     * 获取当前登录企业的总财富。(包含此企业下级节点的所有财富)
     */
    BigDecimal totalWealth();

    /**
     * 获取企业名称。
     */
    String getEnterpriseName();

    /**
     * 获取当前登录企业未分配总财富。(包含此企业下级节点的所有未分配财富)
     */
    BigDecimal totalNotAllocatWealth();

    /**
     * 获取当前登录企业已分配总财富。(包含此企业下级节点的所有已分配财富)
     */
    BigDecimal totalAllocatedWealth();

    /**
     * 获取本节点分配的U宝财富。
     */
    BigDecimal getUcoinWealth();

    /**
     * 发起申请。
     *
     * @param amount 申请金额
     * @param reason 申请原因
     */
    void launchRequisition(BigDecimal amount, String reason);

    /**
     * 财富分配。
     *
     * @param paykey      支付密码
     * @param allocations 分配信息MAP，key值为子公司ID,value为分配的金额。
     */
    void wealthAllocat(String paykey, Map<Long, BigDecimal> allocations);


    /**
     * 是否为顶级账号。
     */
    boolean isTopEnterprise();

    /**
     * 获取财富管理查询结果。
     */
    WealthManageResult getManangeResult();

    /**
     * 获取最近账单。
     */
    List<CurrEnterpriseBill> getCurrentBill();

    /**
     * 获取最近请款单。
     */
    List<EnterpriseRequisition> getCurrentReq();

}
