package com.ylife.chinapost.boss.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BackOrderBossService.
 *
 * @Author henry .
 * @Create 2017-07-03 9:35.
 */
public interface BackOrderBossService {
    /**
     * 查询全部 CreditOrder 带分页
     * @author henry
     * @date 2017-07-03 9:35.
     * @param backOrderNo
     * @param shippingMobile
     * @param orderCode
     * @param status
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CreditOrder> queryListForPageBackOrder(String backOrderNo,String shippingMobile,String orderCode,CreditOrderStatus status,
                                            String start, String end,String thirdId,Pageable pageable);


    /**
     * 按主键查询 CreditOrder
     * @author henry
     * @date 2017-07-06 09:14:43
     * @param backOrderCode
     * @return  CreditOrder
     */
    CreditOrder queryById(Long backOrderCode);

    /**
     * 退单导出
     * @param backOrderNo
     * @param shippingMobile
     * @param orderCode
     * @param status
     * @param start
     * @param end
     * @param pageable
     * @param response
     * @throws IOException
     */
    void exportBackOrder(String backOrderNo,String shippingMobile,String orderCode,
                         CreditOrderStatus status, String start, String end,String thirdId,Pageable pageable,HttpServletResponse response) throws IOException;
}
