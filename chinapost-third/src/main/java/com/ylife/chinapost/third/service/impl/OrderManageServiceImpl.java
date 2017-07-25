package com.ylife.chinapost.third.service.impl;

import com.ylife.chinapost.third.controller.utils.Constants;
import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.chinapost.third.service.OrderManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.mapper.CreditOrderLogMapper;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.payorder.service.PayOrderService;
import com.ylife.ucoin.mapper.BackOrderUcoinHistoryMapper;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.ucoin.model.BackOrderUcoinHistory;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/2.
 * OrderManageServiceImpl
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private OrderService orderService;
    @Resource
    private CreditOrderLogMapper creditOrderLogMapper;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private BackOrderUcoinHistoryMapper backOrderUcoinHistoryMapper;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;

    public Page<Order> getOrders(Long orderNo,
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
                                 Pageable pageable) {

        Order model = new Order();
        if (status != null) {
            model.setOrderStatus(status);
        }
        model.setOrderCode(orderNo);
        model.setShippingPerson(receiver);
        model.setShippingMobile(contactPhone);
        model.setBusinessId(businessId);
        model.setGoodsInfoType(goodsInfoType);
        model.setOrderCargoStatus(orderCargoStatus);
        return orderService.getOrder(model, null, null, username, createStart, createEnd, payStart, payEnd, pageable);
    }

    public Page<CreditOrder> getBackOrder(Long backOrderNo,
                                          String phoneNo,
                                          Long orderNo,
                                          CreditOrderStatus status,
                                          Date start,
                                          Date end,
                                          Long businessId,
                                          String goodsInfoType,
                                          Pageable pageable) {
        return creditOrderService.getBackOrder(backOrderNo, null, null, phoneNo, orderNo, status, start, end, businessId, goodsInfoType, pageable);
    }

    /**
     * 获取订单商品详情
     * */
    public Order getOrderGoods(Long orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * 根据退单编号获取退单信息
     * @param backOrderCode
     * @return
     */
    public CreditOrder getBackOrder(Long backOrderCode){
       return creditOrderService.selectByCreditOrderCode(backOrderCode);
    }

    /**
     * 导入快递单 导入excel解析
     * @param file
     * @return
     */
    public Map<String, Object> parseFile(HttpServletRequest request, MultipartFile file)  throws IOException, InvalidFormatException {
        List<List<String>> excelList;
        excelList = ExcelUtil.readExcel(file.getInputStream(), 1);

        Map<String, Object> map = new HashMap<String, Object>();
        StringBuilder message = new StringBuilder();//失败返回的记录
        StringBuilder msg = new StringBuilder();//之前导入的记录
        StringBuilder sb = new StringBuilder();//未批量出库导入的记录
        Long record = 0L;//成功的条数

        //订单号集合
        String orderCodeStr="";
        for (int row = 0; row < excelList.size(); row++) {
            String orderCode = excelList.get(row).get(0).trim(); // 订单号
            orderCodeStr += orderCode.concat(",");
        }
        List<Order> orderList =  orderService.getOrderByCodes(orderCodeStr);

        // 拿取登陆管理员的名字
        Long thirdId = null;
        if(request.getSession().getAttribute("loginStoreId") != null){
            thirdId = Long.parseLong(request.getSession().getAttribute("loginStoreId").toString());
        }

        for (int i = 0; i < excelList.size(); i++) {
            String orderCode = excelList.get(i).get(0).trim(); // 订单号
            String customerMobile = excelList.get(i).get(1).trim(); //收货人手机
            String expressName = excelList.get(i).get(2).trim(); //快递公司
            String expressNo = excelList.get(i).get(3).trim(); //快递单号
            boolean flag = false;
            try{
                for (Order listVo : orderList) {
                    if (listVo.getOrderCargoStatus().equals("3") && listVo.getOrderStatus().equals(OrderStatus.DELIVERED) && listVo.getOrderCode().toString().equals(orderCode) && listVo.getShippingMobile().equals(customerMobile)) {
                        flag = true;
                        msg.append(orderCode.concat(","));
                        break;
                    }
                    if (listVo.getOrderCargoStatus().equals("0") && listVo.getOrderStatus().equals(OrderStatus.PAYED) && listVo.getOrderCode().toString().equals(orderCode) && listVo.getShippingMobile().equals(customerMobile)) {
                        flag = true;
                        sb.append(orderCode.concat(","));
                        break;
                    }
                    if (listVo.getOrderCargoStatus().equals("2") && listVo.getOrderStatus().equals(OrderStatus.PAYED) && listVo.getOrderCode().toString().equals(orderCode) && listVo.getShippingMobile().equals(customerMobile)) {
                        // 查询包裹是否存在
                        List<OrderContainerRelation> relations = orderService.queryContainerRalation(listVo.getOrderId());
                        if(relations.size() == 1){
                            // 更新运货单
                            orderService.updateThirdSendOrderGoods(new Long[]{relations.get(0).getRelationId()}, expressNo, expressName);
                            // 修改订单状态
                            orderService.updateOrderStatus(listVo.getOrderId(), OrderStatus.DELIVERED, thirdId);
                        }
                        record++;//成功导入累加
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    message.append(orderCode.concat(","));
                }
            }catch (Exception e){
                map.put("message", "导入格式不正确，请检查格式！");
                return  map;
            }

        }
        String resultMessage = "导入数量"+excelList.size()+"条，成功导入"+record+"条。\n";
        if(!message.toString().equals("")){
            resultMessage+="其中未导入的如下："+message.toString()+"请检查订单号与手机号正确性及一致性！\n";
        }
        if(!sb.toString().equals("")){
            resultMessage+="需要先批量出库再导入快递单的如下："+sb.toString()+"\n";
        }
        if(!msg.toString().equals("")){
            resultMessage+="已出库（之前导入的，无需导入）的如下："+msg.toString();
        }
        map.put("message", resultMessage);
        return map;
    }

    /**
     * 订单状态变更
     * @param request
     * @param backOrderCode
     * @param whetherType
     * @return
     */
    @Transactional
    public int orderStateChange(HttpServletRequest request,Long backOrderCode,String whetherType) {
        // 记录日志对象
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        // 拿取登陆管理员的名字
        Long thirdId = null;
        if(request.getSession().getAttribute("loginStoreId") != null){
            thirdId = Long.parseLong(request.getSession().getAttribute("loginStoreId").toString());
        }

        String thirdUserName = "";
        if(request.getSession().getAttribute("thirdUserName") != null){
            thirdUserName = request.getSession().getAttribute("thirdUserName").toString();
        }

        // 拿取退单详情
        CreditOrder creditOrder = creditOrderService.selectByCreditOrderCode(backOrderCode);
        OrderStatus status = null;
        CreditOrderStatus creditOrderStatus = null;
        if("1".equals(whetherType)){ //同意（退款、退货）
            if (Long.valueOf(creditOrder.getIsBack()) == 1) {
                // 同意退货
                status = OrderStatus.RETURN_WAIT_DELIVER_INFO;
                creditOrderStatus = CreditOrderStatus.RETURN_WAIT_DELIVER_INFO;
                creditOrderLog.setLogStr("同意退货（操作：" + thirdUserName + "）");
            }else if(Long.valueOf(creditOrder.getIsBack()) == 2){
                // 同意退款
                refund(backOrderCode,BigDecimal.ZERO,"");
                status = OrderStatus.REFUND_FINISHED;
                creditOrderStatus = CreditOrderStatus.REFUND_FINISHED;
                creditOrderLog.setLogStr("同意退款（操作：" + thirdUserName + "）");
            }
        }else if("2".equals(whetherType)){ // 拒绝（退款、退货）
            if (Long.valueOf(creditOrder.getIsBack()) == 1) {
                // 拒绝退货
                status = OrderStatus.RETURN_DENIED;
                creditOrderStatus = CreditOrderStatus.RETURN_DENIED;
                creditOrderLog.setLogStr("拒绝退货（操作：" + thirdUserName + "）");
            }else if(Long.valueOf(creditOrder.getIsBack()) == 2){
                // 拒绝退款
                status = OrderStatus.PAYED;
                creditOrderStatus = CreditOrderStatus.REFUND_DENIED;
                creditOrderLog.setLogStr("拒绝退款（操作：" + thirdUserName + "）");
            }
        }else if("3".equals(whetherType)){ // 确认收货
            status = OrderStatus.RETURN_WAIT_REFUND;
            creditOrderStatus = CreditOrderStatus.RETURN_WAIT_REFUND;
            creditOrderLog.setLogStr("确认收货（操作：" + thirdUserName + "）");
        }else if("4".equals(whetherType)){ // 拒绝收货
            status = OrderStatus.RETURN_DELIVER_FAILURE;
            creditOrderStatus = CreditOrderStatus.RETURN_DELIVER_FAILURE;
            creditOrderLog.setLogStr("拒绝收货（操作：" + thirdUserName + "）");
        }else if("5".equals(whetherType)){ // 退货完成 同意退款
            refund(backOrderCode,BigDecimal.ZERO,"");
            status = OrderStatus.RETURN_FINISHED;
            creditOrderStatus = CreditOrderStatus.RETURN_FINISHED;
            creditOrderLog.setLogStr("退货完成，同意退款（操作：" + thirdUserName + "）");
        }
        boolean orderFlag = orderService.updateOrderStatus(creditOrder.getOrderId(),status,thirdId)> 0?true:false;
        boolean creditFlag = creditOrderService.updateBackOrderStatus(creditOrder.getBackOrderId(),creditOrderStatus,thirdUserName)> 0?true:false;
        // 日志数据
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
        creditOrderLogMapper.insertSelective(creditOrderLog);
        if(orderFlag && creditFlag){
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 订单价格变更
     * @param orderId
     * @param orderPrice
     * @return
     */
    public int orderPriceChange(Long orderId, BigDecimal orderPrice) {
        return orderService.orderPriceChange(orderId,orderPrice);
    }

    /**
     * 退款。
     *
     * @param creditOrderNo    退货单号
     * @param price            退款金额
     * @param msg              客户留言
     */
    @Transactional
    public void refund(long creditOrderNo, BigDecimal price, String msg) {

        //获取退单信息
        CreditOrder creditOrder = getBackOrder(creditOrderNo);
        //获取退单对应的订单付款明细
        PayOrderEntity item = new PayOrderEntity();
        item.setOrderCode(creditOrder.getOrder().getOrderCode());
        List<PayOrderEntity> payOrderEntityList = payOrderService.queryAllPayOrder(item);
        //按网点分类算结算价
        Map<Long,BigDecimal> priceMap = new HashMap<Long, BigDecimal>();
        Map<Long,BigDecimal> settleMap = new HashMap<Long, BigDecimal>();
        //退款
        if(creditOrder.getIsBack().equals("1")){//同意退货
            for(BackOrderGoods backOrderGoods:creditOrder.getBackOrderGoods()){
                BigDecimal backOrderPrice = backOrderGoods.getGoodsPrice().multiply(BigDecimal.valueOf(backOrderGoods.getGoodsNum()));
                BigDecimal backOrderSettlePrice = backOrderGoods.getGoodsInfoSettlePrice().multiply(BigDecimal.valueOf(backOrderGoods.getGoodsNum()));
                for(PayOrderEntity payOrder:payOrderEntityList) {
                    if(backOrderPrice.equals(BigDecimal.ZERO)){
                        break;
                    }
                    if(payOrder.getPrice().equals(BigDecimal.ZERO)){
                        continue;
                    }

                    if(backOrderGoods.getGoodsInfoId().equals(payOrder.getGoodsInfoId())){
                        if(backOrderPrice.compareTo(payOrder.getPrice())>=0){
                            //退款金额
                            price = price.add(payOrder.getPrice());
                            //退款给用户
                            customerUcoinService.grandUcoin(creditOrder.getCustomerId(), payOrder.getEnterpriseId(), payOrder.getPrice(),
                                    payOrder.getStartTime() == null ? null : DateUtil.fromString(payOrder.getStartTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                                    payOrder.getEndTime() == null ? null : DateUtil.fromString(payOrder.getEndTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                                    "", "退货退款");

                            //按网点分类算结算价
                            if(priceMap.get(payOrder.getEnterpriseId())==null){
                                priceMap.put(payOrder.getEnterpriseId(), payOrder.getPrice());
                                settleMap.put(payOrder.getEnterpriseId(), payOrder.getSettlePrice());
                            }else {
                                priceMap.put(payOrder.getEnterpriseId(), priceMap.get(payOrder.getEnterpriseId()).add(payOrder.getPrice()));
                                settleMap.put(payOrder.getEnterpriseId(), settleMap.get(payOrder.getEnterpriseId()).add(payOrder.getSettlePrice()));
                            }


                            //扣减记录中金额
                            BackOrderUcoinHistory backOrderUcoinHistory = new BackOrderUcoinHistory();
                            backOrderUcoinHistory.setCreateTime(new Date());
                            backOrderUcoinHistory.setEnterpriseId(payOrder.getEnterpriseId());
                            backOrderUcoinHistory.setCustomerId(creditOrder.getCustomerId());
                            backOrderUcoinHistory.setBackOrderId(creditOrder.getBackOrderId());
                            backOrderUcoinHistory.setPrice(payOrder.getPrice());
                            backOrderUcoinHistoryMapper.insert(backOrderUcoinHistory);

                            //修改邮豆金额
                            backOrderPrice = backOrderPrice.subtract(payOrder.getPrice());
                            backOrderSettlePrice = backOrderSettlePrice.subtract(payOrder.getSettlePrice());

                            //退款添加到付款流程表
                            payOrder.setPrice(payOrder.getPrice().negate());
                            payOrder.setSettlePrice(payOrder.getSettlePrice().negate());
                            payOrderService.addPayOrder(payOrder);

                            //修改结算金额
                            payOrder.setPrice(BigDecimal.ZERO);

                        }else{
                            //退款金额
                            price = price.add(backOrderPrice);

                            //退款给用户
                            customerUcoinService.grandUcoin(creditOrder.getCustomerId(), payOrder.getEnterpriseId(), backOrderPrice,
                                    payOrder.getStartTime() == null ? null : DateUtil.fromString(payOrder.getStartTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                                    payOrder.getEndTime() == null ? null : DateUtil.fromString(payOrder.getEndTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                                    "", "退货退款");

                            //按网点分类算结算价
                            if(priceMap.get(payOrder.getEnterpriseId())==null){
                                priceMap.put(payOrder.getEnterpriseId(), backOrderPrice);
                                settleMap.put(payOrder.getEnterpriseId(), backOrderSettlePrice);
                            }else {
                                priceMap.put(payOrder.getEnterpriseId(), priceMap.get(payOrder.getEnterpriseId()).add(backOrderPrice));
                                settleMap.put(payOrder.getEnterpriseId(), settleMap.get(payOrder.getEnterpriseId()).add(backOrderSettlePrice));
                            }


                            //扣减记录中金额
                            BackOrderUcoinHistory backOrderUcoinHistory = new BackOrderUcoinHistory();
                            backOrderUcoinHistory.setCreateTime(new Date());
                            backOrderUcoinHistory.setEnterpriseId(payOrder.getEnterpriseId());
                            backOrderUcoinHistory.setCustomerId(creditOrder.getCustomerId());
                            backOrderUcoinHistory.setBackOrderId(creditOrder.getBackOrderId());
                            backOrderUcoinHistory.setPrice(backOrderPrice);
                            backOrderUcoinHistoryMapper.insert(backOrderUcoinHistory);

                            //退款添加到付款流程表
                            payOrder.setPrice(backOrderPrice.negate());
                            payOrder.setSettlePrice(backOrderSettlePrice.negate());
                            payOrderService.addPayOrder(payOrder);

                            //修改邮豆/结算金额
                            payOrder.setPrice(payOrder.getPrice().subtract(backOrderPrice));
                            backOrderPrice = BigDecimal.ZERO;
                        }
                    }
                }
            }

        }else if(creditOrder.getIsBack().equals("2")){//同意退款
            for(PayOrderEntity payOrder:payOrderEntityList) {
                //退款金额
                price = price.add(payOrder.getPrice());
                //按网点分类算结算价
                if(priceMap.get(payOrder.getEnterpriseId())==null){
                    priceMap.put(payOrder.getEnterpriseId(), payOrder.getPrice());
                    settleMap.put(payOrder.getEnterpriseId(), payOrder.getSettlePrice());
                }else{
                    priceMap.put(payOrder.getEnterpriseId(), priceMap.get(payOrder.getEnterpriseId()).add(payOrder.getPrice()));
                    settleMap.put(payOrder.getEnterpriseId(), settleMap.get(payOrder.getEnterpriseId()).add(payOrder.getSettlePrice()));
                }

                //退款给用户
                customerUcoinService.grandUcoin(creditOrder.getCustomerId(), payOrder.getEnterpriseId(), payOrder.getPrice(),
                        payOrder.getStartTime() == null ? null : DateUtil.fromString(payOrder.getStartTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                        payOrder.getEndTime() == null ? null : DateUtil.fromString(payOrder.getEndTime(), Constants.DEFAULT_DATE_TIME_FORMAT),
                        "", "退款");

                //扣减记录中金额
                BackOrderUcoinHistory backOrderUcoinHistory = new BackOrderUcoinHistory();
                backOrderUcoinHistory.setCreateTime(new Date());
                backOrderUcoinHistory.setEnterpriseId(payOrder.getEnterpriseId());
                backOrderUcoinHistory.setCustomerId(creditOrder.getCustomerId());
                backOrderUcoinHistory.setBackOrderId(creditOrder.getBackOrderId());
                backOrderUcoinHistory.setPrice(payOrder.getPrice());
                backOrderUcoinHistoryMapper.insert(backOrderUcoinHistory);

                //退款添加到付款流程表
                payOrder.setPrice(payOrder.getPrice().negate());
                payOrder.setSettlePrice(payOrder.getSettlePrice().negate());
                payOrderService.addPayOrder(payOrder);
            }
        }


        //退款
        creditOrderService.refund(creditOrderNo, price,currentLoginService.getCurrentStoreName(), "线上订单退单", null);

        //用户退款历史表
        for (Map.Entry<Long, BigDecimal> entry : settleMap.entrySet()) {
            BigDecimal balancePrice = customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(creditOrder.getCustomerId(), entry.getKey());
            customerUcoinHistoryService.addHistory(creditOrder.getCustomerId(), entry.getKey(), null, HistoryType.UCOIN_REFUND, null, priceMap.get(entry.getKey()), null, null, balancePrice, null, null, creditOrder.getOrderId(), null, entry.getValue());
        }
    }

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
    public void exportOrderExcel(Long orderNo,OrderStatus status, String username, String receiver, String contactPhone, String createStart, String createEnd, String payStart, String payEnd, HttpServletResponse response) throws IOException {
        Order model = new Order();
        if (status != null) {
            model.setOrderStatus(status);
        }
        if (status != null && status == OrderStatus.WAIT_DELIVER) {
            model.setOrderStatus( OrderStatus.PAYED);
        } else if (status != null && status == OrderStatus.WAIT_PICKUP) {
            model.setOrderStatus( OrderStatus.PAYED);
        }
        username = Constants.nullOrNotBlank(username);
        model.setShippingPerson(Constants.nullOrNotBlank(receiver));
        model.setShippingMobile(Constants.nullOrNotBlank(contactPhone));
        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        model.setOrderCode(orderNo);
        model.setBusinessId(currentLoginService.getCurrentLoginThirdId());
        model.setGoodsInfoType("1");
        model.setOrderCargoStatus(null);
        orderService.exportOrderExcel(model,null,null,username,createStartTime, createEndTime, payStartTime, payEndTime,null,response);
    }

    /**
     * 退单导出
     *
     * @param backOrderNo
     * @param shippingMobile
     * @param orderCode
     * @param status
     * @param start
     * @param end
     * @param pageable
     * @param response
     * @throws java.io.IOException
     */
    public void exportBackOrder(String backOrderNo, String shippingMobile, String orderCode, CreditOrderStatus status, String start, String end, Pageable pageable, HttpServletResponse response) throws IOException {

        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_TIME_FORMAT);
        }
        Long backOrderCode = null;
        if (!StringUtil.isBlank(backOrderNo)) {
            backOrderCode = Long.parseLong(backOrderNo);
        }
        Long orderNo = null;
        if (!StringUtil.isBlank(orderCode)) {
            orderNo = Long.parseLong(orderCode);
        }
        creditOrderService.exportBackOrder(backOrderCode,null,null,shippingMobile,orderNo,status,startTime,endTime,currentLoginService.getCurrentLoginThirdId(),"1",pageable,response);
    }
}