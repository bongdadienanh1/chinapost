package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.wealth.model.EnterpriseAllocation;
import com.ylife.wealth.model.EnterpriseBatchAllocation;
import com.ylife.wealth.model.EnterpriseBatchGrand;

import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * <p/>
 * 账单管理(企业客户上下级之间的U宝分配记录和发放记录)。
 */
public interface UcoinBillManageService {

    /**
     * 获取父企业给本企业分配的记录。
     */
    Page<EnterpriseAllocation> getParentAllocations(Long code, Date start, Date end, Pageable pageable);


    /**
     * 根据批量ID获取分配记录。
     */
    Page<EnterpriseAllocation> getAllocationByBatchId(long batchId, Pageable pageable);

    /**
     * 获取我的分配记录。
     */
    Page<EnterpriseBatchAllocation> getMyAllocations(Long code, Date start, Date end, Pageable pageable);

    /**
     * 获取我的邮宝发放记录。
     */
    Page<EnterpriseBatchGrand> getGrands(Long code, Date start, Date end, Pageable pageable);

    /**
     * 获取我的邮宝扣减记录。
     */
    Page<CustomerUcoinHistory> getDeducts(Long code, Date start, Date end, Pageable pageable);

    /**
     * 根据批量获取记录。
     *
     * @param batchId 批量ID
     */
    Page<CustomerUcoinHistory> getGrandHistoryByBatchId(long batchId, Pageable pageable);


    /**
     * 获取当前网点代课下单补贴信息
     * @param code
     * @param enterpriseId
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerUcoinHistory> getEnterpriseSubsidy(Long code,Date start,Date end,Pageable pageable);

}
