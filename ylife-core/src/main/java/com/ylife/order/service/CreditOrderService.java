package com.ylife.order.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/3.
 * 退单服务。
 */
public interface CreditOrderService {

    /**
     * 从订单中创建新的退货单。
     *
     * @param orderNo       订单号
     * @param goodsIdMap    要退单的订单商品Map,key:货品id，value：对应货品数量
     * @param reason        原因
     * @param credential    凭证类型
     * @param credentialDoc 上传的凭证
     * @param remark        问题说明
     * @param backWay       退回方式
     * @param valet         用户自主退货或者是代客退货
     */
    void createCreditOrder(long orderNo, Map<Long, Integer> goodsIdMap, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay, boolean valet);

    /**
     * 从订单中创建新的退款单。
     *
     * @param orderNo       订单号
     * @param reason        原因
     * @param credential    凭证
     * @param credentialDoc 上传的凭证
     * @param remark        问题说明
     */
    void createRefundOrder(long orderNo,
                           CreditOrderReason reason,
                           CredentialType credential,
                           String credentialDoc,
                           String remark);

    /**
     * 非顶级企业审核退货单/退款单。
     *
     * @param creditOrderNo 退货单号
     * @param agree         是否同意
     * @param operator      操作者
     * @param message       给客户留言
     */
    void reviewCreditOrder(long creditOrderNo, boolean agree, String operator, String message);

    /**
     * 填写运单号。
     *
     * @param orderNo      订单号
     * @param deliveryNo   退货物流单号
     * @param logisticName 物流公司
     */
    void editDeliveryNo(long orderNo, String deliveryNo, String logisticName);

    /**
     * 非顶级企业退货单收货。
     *
     * @param creditOrderNo 退货单号
     * @param receive       是否收货
     * @param enterpriseMsg 给商城留言
     * @param customerMsg   给客户留言
     */
    void receiveCreditOrder(long creditOrderNo, boolean receive, String enterpriseMsg, String customerMsg);

    /**
     * 顶级企业退款。
     *
     * @param creditOrderNo 退货单号
     * @param price         退款数量
     * @param operator      操作者
     * @param msg           客户留言
     */
    void refund(long creditOrderNo, BigDecimal price, String operator, String msg,BigDecimal backSubsidyPrice);

    /**
     * 获取退单。
     */
    Page<CreditOrder> getCreditOrders(Pageable pageable);

    /**
     * 获取所有退货单。
     *
     * @param backOrderNo 退单号
     * @param phoneNo     手机号码
     * @param orderNo     订单号
     * @param status      状态
     * @param start       开始时间
     * @param end         结束时间
     */
    Page<CreditOrder> getBackOrder(Long backOrderNo,
                                   Long minCatalog,
                                   Long maxCatalog,
                                   String phoneNo,
                                   Long orderNo,
                                   CreditOrderStatus status,
                                   Date start,
                                   Date end,
                                   Pageable pageable);
    /**
     * 获取所有退货单。
     *
     * @param backOrderNo 退单号
     * @param phoneNo     手机号码
     * @param orderNo     订单号
     * @param status      状态
     * @param start       开始时间
     * @param end         结束时间
     * @param goodsInfoType 订单类型
     */
    Page<CreditOrder> getBackOrder(Long backOrderNo,
                                   Long minCatalog,
                                   Long maxCatalog,
                                   String phoneNo,
                                   Long orderNo,
                                   CreditOrderStatus status,
                                   Date start,
                                   Date end,
                                   Long businessId,
                                   String goodsInfoType,
                                   Pageable pageable);


    /**
     * 同步退单数据（用户提交退单完成调用）
     *
     * @param orderCode 订单号
     */
    void syncCreateCreditOrder(long orderCode);

    /**
     * 同步退单状态
     */
    void updateBackOrderStatus() throws IOException;

    /**
     * 审核退款订单
     *
     * @param backOrderCode 退单号
     * @param status        1同意退货退款 2同意退款 3拒绝退款
     * @param backRemark    平台退款留言
     */
    void syncRefundsOrder(long backOrderCode, String status, String backRemark);

    /**
     * 自提单退款/退货退款成功操作
     *
     * @param backOrderCode 订单号
     */
    void syncRefundsForSelfPick(long backOrderCode);


    /**
     * 同步顾客填写退单物流
     *
     * @param backOrderCode 退单号
     * @param logisticsName 物流公司
     * @param logisticsNo   物流单号
     */
    void syncBackOrderGeneral(long backOrderCode, String logisticsName, String logisticsNo);

    /**
     * 根据退单号查询退单信息
     *
     * @param backOrderCode 退单号
     */
    CreditOrder selectByCreditOrderCode(long backOrderCode);

    /**
     * 根据退单号查询退单信息
     *
     * @param orderCode 订单号
     */
    CreditOrder selectByOrderCode(long orderCode);

    /**
     * 查询退单账单信息
     * @param backOrderId
     * @return
     * */
    BackOrderRefund selectBackOrderRefundByKey(long backOrderId);

    /**
     * 查询退单商品详情
     * */
    List<BackOrderGoods> getBackOrderByOrderId(long orderId);

    /**
     *
     * @param backOrderId
     * @param status
     * @param authorName
     * @return
     */
    int updateBackOrderStatus(Long backOrderId,CreditOrderStatus status,String authorName);

    /**
     * 退单导出
     * @param backOrderNo
     * @param minCatalog
     * @param maxCatalog
     * @param phoneNo
     * @param orderNo
     * @param status
     * @param start
     * @param end
     * @param businessId
     * @param goodsInfoType
     * @param pageable
     * @param response
     * @throws IOException
     */
    void exportBackOrder(Long backOrderNo,
                         Long minCatalog,
                         Long maxCatalog,
                         String phoneNo,
                         Long orderNo,
                         CreditOrderStatus status,
                         Date start, Date end,
                         Long businessId,
                         String goodsInfoType,
                         Pageable pageable,
                         HttpServletResponse response) throws IOException;
}