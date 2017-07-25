package com.ylife.order.mapper;

import com.ylife.client.bean.OOrderAllInfo;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderRefund;
import com.ylife.order.model.OrderStatus;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/5/1.
 * 订单Mapper
 */
public interface OrderMapper {


    /**
     * 根据模型查询。
     */
    List<Order> selectByModelSelective(@Param("model") Order model,
                                       @Param("maxCatalog")Long maxCatalog,
                                       @Param("minCatalog")Long minCatalog,
                                       @Param("idCard") String idCard,
                                       @Param("createStart") Date createStart,
                                       @Param("createEnd") Date createEnd,
                                       @Param("payStart") Date payStart,
                                       @Param("payEnd") Date payEnd,
                                       @Param("pageable") Pageable pageable);

    /**
     * 根据模型查询。
     */
    int countByModelSelective(@Param("model") Order model,
                              @Param("maxCatalog")Long maxCatalog,
                              @Param("minCatalog")Long minCatalog,
                              @Param("idCard") String idCard,
                              @Param("createStart") Date createStart,
                              @Param("createEnd") Date createEnd,
                              @Param("payStart") Date payStart,
                              @Param("payEnd") Date payEnd);

    int insertSelective(Order order);

    Order selectByOrderCode(Long orderCode);

    Order selectByDeliveryCode(String deliveryCode);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order order);

    int updateByOrderCodeSelective(Order order);

    /**
     * 更新一段时间未付款的订单。
     *
     * @param second 未付款至今秒数
     * @param remark 订单取消备注
     */
    void updateStatusToCancelByTime(@Param("second") Integer second, @Param("remark") String remark);

    /**
     * 根据OrderCode清除提货码。
     *
     * @param orderCode 订单号
     */
    int updateDeliveryCodeToNullByOrderCode(Long orderCode);

    int updateOrderStatusByOrderCode(@Param("orderCode") Long orderCode, @Param("status") OrderStatus status);

    /**
     * 根据总订单号查询订单信息
     *
     * @param orderOldCode 总订单号
     */
    List<Order> selectByOrderOldCode(Long orderOldCode);


    /**
     * 查询需要同步的订单（订单状态为已付款未发货、申请退货、申请退款）
     */
    List<Order> selectByCondition();

    /**
     * （同步下来需要修改的订单）修改订单状态
     * @param list
     */
    int updateStatusByOrderCode(OOrderAllInfo list);

    /**
     * 定时取消订单（代客下单未支付）
     *
     * @return
     */
    List<Order> selectByValetSubmit();

    /**
     * 查询订单账单信息
     * @param orderId
     * @return
     * */
    OrderRefund selectOrderPrimaryKey(long orderId);

    /**
     * 根据orderIds 字符串查询
     *
     * @param orderCodes
     * @return 订单数据
     */
    List<Order> getOrderByCodes(List<String> orderCodes);
}
