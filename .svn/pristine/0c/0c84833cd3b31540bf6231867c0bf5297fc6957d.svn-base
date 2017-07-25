package com.ylife.wealth.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseBatchGrand;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/25.
 * <p/>
 * 企业分配U宝服务。
 */
public interface EnterpriseBatchGrandService {

    /**
     * 添加新的批量分配记录。
     *
     * @param enterpriseId 企业ID
     * @param sendType     发送类型
     * @param remark       备注
     * @param fileName     文件名
     */
    long addBatchGrand(long enterpriseId, String sendType, String remark,int typeId,Long batchCode,String fileName);

    /**
     * 更新批量分配的金额和发放笔数。
     *
     * @param batchId 批量分配ID
     * @param amount  发放数量
     * @param count   发放笔数
     * @param managerId   操作者账号ID
     * @param managerName   操作者账号名称
     *
     */
    void updateFee(long batchId, BigDecimal amount, Integer count,Long managerId,String managerName);

    void updateFee(List<EnterpriseBatchGrand> grands);



    /**
     * 获取U宝批量发放记录。
     *
     * @param enterpriseId 企业ID
     * @param start        开始时间
     * @param end          结束时间
     * @param pageable     分页信息
     */
    Page<EnterpriseBatchGrand> getBatchGrands(Long code,long enterpriseId, Date start, Date end, Integer typeId,Pageable pageable);

    /**
     * 获取网点补贴
     *
     *  @param enterpriseId 企业ID
     * @param start        开始时间
     * @param end          结束时间
     * @param pageable     分页信息
     * */
    Page<CurrEnterpriseBill> getEnterpriseIdOrderSubsidy(Long code,long enterpriseId, Date start, Date end,Pageable pageable);

    /**
     * 获取订单退款返还
     *
     *  @param enterpriseId 企业ID
     * @param start        开始时间
     * @param end          结束时间
     * @param pageable     分页信息
     * */
    Page<CurrEnterpriseBill> getEnterpriseIdBackOrderSubsidy(Long code,long enterpriseId, Date start, Date end,Pageable pageable);
}
