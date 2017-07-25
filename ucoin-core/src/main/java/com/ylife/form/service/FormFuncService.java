package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormFuncService {

    /**
     * 根据时间获取报表信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    FormTime getReportInfoByTime(Long maxCatalog, Long minCatalog, Date start, Date end);

    /**
     * 根据时间获取新增会员信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    Long getNewCustomerByTime(Long maxCatalog, Long minCatalog, Date start, Date end);


    /**
     * 每日邮豆发放报表
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<FormTime> getReportInfoEveryDay(Long maxCatalog, Long minCatalog, Date start, Date end);

    /**
     * 每日新增会员数量
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<FormTime> newCustomerAmount(Long maxCatalog, Long minCatalog, Date start, Date end);

    /**
     * 会员邮豆发放统计
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<UcoinGrandForm> getucoinGrandForm(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);


    /**
     * 会员邮豆发放详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<UcoinGrandForm> getDetailGrandForm(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);

    /**
     * 会员消耗邮豆统计
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerConsume> getCustomerConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);

    /**
     * 会员邮豆消耗详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerConsume> getDetailConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);

    /**
     * 新会员邮豆消耗详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerConsume> getDetailConsumeNew(Long enterpriseId, String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);


    /**
     * 网点基础数据统计
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<FormFunc> getNetdata(Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);

    //新的会员邮豆消耗统计
    Page<CustomerConsume> newGetCustomerConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable);

    //按发放批次查看邮豆发放/扣减记录
    Page<UcoinGrandForm> selectUcoinGrandByBatchCode(Integer type, Date start, Date end, Pageable pageable);

    //按发放批次查看邮豆发放/扣减记录详情
    Page<UcoinGrandForm> selectDetailUcoinGrandByBatchCode(Long batchCode, Pageable pageable);

    //财富统计报表,传入当前登录enterpriseId,统计该机构（所有下级数据和）财富报表
    WealthForm selectWealthFormByEnterpriseId(Long enterpriseId, Long maxCatalog, Long minCatalog, Date startTime, Date endTime);

}

