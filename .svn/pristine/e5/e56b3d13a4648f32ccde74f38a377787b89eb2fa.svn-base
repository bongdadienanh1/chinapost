package com.ylife.form.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.form.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormFuncMapper {


    /**
     * 根据时间获取报表信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param End
     * @return
     */
    FormTime selectFormByTime(@Param("maxCatalog") Long maxCatalog,
                              @Param("minCatalog") Long minCatalog,
                              @Param("start") Date start,
                              @Param("end") Date End);

    /**
     * 新增会员数数量
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param End
     * @return
     */
    Long selectNewCustomerByTime(@Param("maxCatalog") Long maxCatalog,
                                 @Param("minCatalog") Long minCatalog,
                                 @Param("start") Date start,
                                 @Param("end") Date End);

    /**
     * 按天获取报表信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param End
     * @return
     */
    List<FormTime> selectReportByDay(@Param("maxCatalog") Long maxCatalog,
                                     @Param("minCatalog") Long minCatalog,
                                     @Param("start") Date start,
                                     @Param("end") Date End);

    /**
     * 新增会员报表信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<FormTime> selectNewcustomerAmount(@Param("maxCatalog") Long maxCatalog,
                                           @Param("minCatalog") Long minCatalog,
                                           @Param("start") Date start,
                                           @Param("end") Date end);


    /**
     * 邮豆发放统计
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<UcoinGrandForm> selectUcoinGrand(@Param("idCard") String idCard,
                                          @Param("maxCatalog") Long maxCatalog,
                                          @Param("minCatalog") Long minCatalog,
                                          @Param("start") Date start,
                                          @Param("end") Date end,
                                          @Param("pageable") Pageable pageable);

    int countUcoinGrandForm(@Param("idCard") String idCard,
                            @Param("maxCatalog") Long maxCatalog,
                            @Param("minCatalog") Long minCatalog,
                            @Param("start") Date start,
                            @Param("end") Date end);

    /**
     * 邮豆发放详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<UcoinGrandForm> selectDetailUcoinGrand(@Param("idCard") String idCard,
                                                @Param("maxCatalog") Long maxCatalog,
                                                @Param("minCatalog") Long minCatalog,
                                                @Param("start") Date start,
                                                @Param("end") Date end,
                                                @Param("pageable") Pageable pageable);

    int countDetailUcoinGrand(@Param("idCard") String idCard,
                              @Param("maxCatalog") Long maxCatalog,
                              @Param("minCatalog") Long minCatalog,
                              @Param("start") Date start,
                              @Param("end") Date end);

    /**
     * 会员邮豆消耗
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<CustomerConsume> selectCustomerConsume(@Param("idCard") String idCard,
                                                @Param("maxCatalog") Long maxCatalog,
                                                @Param("minCatalog") Long minCatalog,
                                                @Param("start") Date start,
                                                @Param("end") Date end,
                                                @Param("pageable") Pageable pageable);

    int countCustomerConsume(@Param("idCard") String idCard,
                             @Param("maxCatalog") Long maxCatalog,
                             @Param("minCatalog") Long minCatalog,
                             @Param("start") Date start,
                             @Param("end") Date end);

    /**
     * 会员邮豆消费详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<CustomerConsume> selectDetailConsume(@Param("idCard") String idCard,
                                              @Param("maxCatalog") Long maxCatalog,
                                              @Param("minCatalog") Long minCatalog,
                                              @Param("start") Date start,
                                              @Param("end") Date end,
                                              @Param("pageable") Pageable pageable);

    int countDetailConsume(@Param("idCard") String idCard,
                           @Param("maxCatalog") Long maxCatalog,
                           @Param("minCatalog") Long minCatalog,
                           @Param("start") Date start,
                           @Param("end") Date end);

    /**
     * 新会员邮豆消费详情
     */
    List<CustomerConsume> selectDetailConsumeNew(@Param("enterpriseId") Long enterpriseId,
                                                 @Param("idCard") String idCard,
                                                 @Param("maxCatalog") Long maxCatalog,
                                                 @Param("minCatalog") Long minCatalog,
                                                 @Param("start") Date start,
                                                 @Param("end") Date end,
                                                 @Param("pageable") Pageable pageable);

    /**
     * 会员邮豆消耗统计()
     */
    int countDetailConsumeNew1(@Param("enterpriseId") Long enterpriseId,
                               @Param("idCard") String idCard,
                               @Param("maxCatalog") Long maxCatalog,
                               @Param("minCatalog") Long minCatalog,
                               @Param("start") Date start,
                               @Param("end") Date end);

    int countDetailConsumeNew2(@Param("enterpriseId") Long enterpriseId,
                               @Param("idCard") String idCard,
                               @Param("maxCatalog") Long maxCatalog,
                               @Param("minCatalog") Long minCatalog,
                               @Param("start") Date start,
                               @Param("end") Date end);

    /**
     * 会员邮豆退款详情
     *
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<CustomerConsume> selectDetailRefund(@Param("idCard") String idCard,
                                             @Param("maxCatalog") Long maxCatalog,
                                             @Param("minCatalog") Long minCatalog,
                                             @Param("start") Date start,
                                             @Param("end") Date end,
                                             @Param("pageable") Pageable pageable);

    int countDetailRefund(@Param("idCard") String idCard,
                          @Param("maxCatalog") Long maxCatalog,
                          @Param("minCatalog") Long minCatalog,
                          @Param("start") Date start,
                          @Param("end") Date end);

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
    List<FormFunc> selectNetData(@Param("maxCatalog") Long maxCatalog,
                                 @Param("minCatalog") Long minCatalog,
                                 @Param("start") Date start,
                                 @Param("end") Date end,
                                 @Param("pageable") Pageable pageable);

    int countNetData(@Param("maxCatalog") Long maxCatalog,
                     @Param("minCatalog") Long minCatalog,
                     @Param("start") Date start,
                     @Param("end") Date end);


    /***********************新的会员邮豆消耗统计************************/

    /**
     * 会员邮豆消耗统计，上级网点可查看下级信息
     *
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<CustomerConsume> selectCustomerUcoinConsumeByEnterpriseNew(@Param("maxCatalog") Long maxCatalog,
                                                                    @Param("minCatalog") Long minCatalog,
                                                                    @Param("idCard") String idCard,
                                                                    @Param("start") Date start,
                                                                    @Param("end") Date end,
                                                                    @Param("pageable") Pageable pageable);

    int countCustomerUcoinConsumeNew(@Param("idCard") String idCard,
                                     @Param("maxCatalog") Long maxCatalog,
                                     @Param("minCatalog") Long minCatalog);


    /**
     * 按照批次，获取邮豆发放的记录
     *
     * @param typeId
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    List<UcoinGrandForm> selectUcoinGrandByBatchCode(@Param("typeId") Integer typeId,
                                                     @Param("start") Date start,
                                                     @Param("end") Date end,
                                                     @Param("pageable") Pageable pageable);


    int countUcoinGrandByBatchCode(@Param("typeId") Integer type,
                                   @Param("start") Date start,
                                   @Param("end") Date end);

    /**
     * 查看邮豆发放的记录明细
     */
    List<UcoinGrandForm> selectDetailstUcoinGrand(@Param("batchcode") Long batchcode,
                                                  @Param("pageable") Pageable pageable);

    int countDetailstUcoinGrand(@Param("batchcode") Long batchcode);

    //财富统计报表,传入当前登录enterpriseId,统计该机构（所有下级数据和）财富报表
    WealthForm selectWealthFormByEnterpriseId(@Param("enterpriseId") Long enterpriseId,
                                              @Param("maxCatalog") Long maxCatalog,
                                              @Param("minCatalog") Long minCatalog,
                                              @Param("start") Date startTime,
                                              @Param("end") Date endTime);

    //会员基础数据明细表
    List<CustomerFormDetail> selectCustomerFormDetail(@Param("maxCatalog") Long maxCatalog,
                                                      @Param("minCatalog") Long minCatalog,
                                                      @Param("start") Date start,
                                                      @Param("end") Date end,
                                                      @Param("pageable") Pageable pageable);
    //会员基础数据明细条数
    int countCustomerFormDetail(@Param("maxCatalog") Long maxCatalog,
                                @Param("minCatalog") Long minCatalog,
                                @Param("start") Date start,
                                @Param("end") Date end);

    /**
     * 会员基础数据统计表
     * @param enterpriseId
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<CustomerFormStatistic> selectCustomerFormStatistic(@Param("enterpriseId") Long enterpriseId,
                                                            @Param("maxCatalog") Long maxCatalog,
                                                            @Param("minCatalog") Long minCatalog,
                                                            @Param("start") Date start,
                                                            @Param("end") Date end);


}
