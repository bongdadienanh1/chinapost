package com.ylife.chinapost.third.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderStatus;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderStatus;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/2.
 * 订单管理。
 */
public interface OrderManageService {

    /**
     * 获取订单。
     *
     * @param orderNo      订单号
     * @param status       订单状态
     * @param orderCargoStatus     出库状态
     * @param username     用户名
     * @param receiver     收货人
     * @param contactPhone 联系电话
     * @param createStart  创建开始时间
     * @param createEnd    创建结束时间
     * @param payStart     支付开始时间
     * @param payEnd       支付结束时间
     * @param pageable     分页信息
     */
    Page<Order> getOrders(Long orderNo,
                          OrderStatus status,
                          String  orderCargoStatus,
                          String username,
                          String receiver,
                          String contactPhone,
                          Date createStart,
                          Date createEnd,
                          Date payStart,
                          Date payEnd,
                          Long businessId,
                          String goodsInfoType,
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
     */
    Page<CreditOrder> getBackOrder(Long backOrderNo,
                                   String phoneNo,
                                   Long orderNo,
                                   CreditOrderStatus status,
                                   Date start,
                                   Date end,
                                   Long businessId,
                                   String goodsInfoType,
                                   Pageable pageable);

    /**
     * 退款。
     *
     * @param creditOrderNo 退货单号
     * @param price         退款金额
     * @param msg           客户留言
     */
    void refund(long creditOrderNo, BigDecimal price, String msg);

    /**
     * 根据订单id获取商品详情
     * @param orderId 订单Id
     * */
    Order getOrderGoods(Long orderId);


    /**
     * 导入快递单 导入excel解析
     * @param file
     * @return
     */
    Map<String,Object> parseFile(HttpServletRequest request, MultipartFile file) throws IOException, InvalidFormatException;

    /**
     * 退款、退货状态变更
     * @param backOrderCode
     * @param whetherType
     * @return
     */
    int orderStateChange(HttpServletRequest request,Long backOrderCode,String whetherType);

    /**
     * 订单价格变更
     * @param orderId
     * @param orderPrice
     * @return
     */
    int orderPriceChange(Long orderId,BigDecimal orderPrice);

    /**
     * 根据退单编号获取退单信息
     * @param backOrderCode
     * @return
     */
    CreditOrder getBackOrder(Long backOrderCode);


    /**
     * 导出订单
     * @param status
     * @param username
     * @param receiver
     * @param contactPhone
     * @param createStart
     * @param createEnd
     * @param payStart
     * @param payEnd
     * @param response
     * @throws IOException
     */
    void exportOrderExcel(Long orderNo,OrderStatus status,String username,String receiver,
                          String contactPhone,String createStart,String createEnd,String payStart,String payEnd,
                          HttpServletResponse response) throws IOException;


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
                         CreditOrderStatus status, String start, String end,Pageable pageable,HttpServletResponse response) throws IOException;

}