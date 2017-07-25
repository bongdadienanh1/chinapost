package com.ylife.chinapost.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.OrderManageService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseUcoinHistoryMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseUcoinHistory;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.model.InventoryChangeHistory;
import com.ylife.inventory.model.InventoryChangeType;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryChangeService;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.mapper.CreditOrderMapper;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.ucoin.mapper.BackOrderUcoinHistoryMapper;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.ucoin.model.BackOrderUcoinHistory;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/2.
 * OrderManageServiceImpl
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private OrderService orderService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private CreditOrderMapper creditOrderMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private BackOrderUcoinHistoryMapper backOrderUcoinHistoryMapper;
    @Resource
    private InventoryChangeService inventoryChangeService;
    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private EnterpriseUcoinHistoryMapper enterpriseUcoinHistoryMapper;

    private static final Logger LOGGER = Logger.getLogger(OrderManageServiceImpl.class);
    private Parser parser = new SimpleParser();


    @Override
    public Page<Order> getOrders(Long orderNo,
                                 Long enterpriseId,
                                 OrderStatus status,
                                 Boolean selfPick,
                                 String username,
                                 String receiver,
                                 String contactPhone,
                                 Date createStart,
                                 Date createEnd,
                                 Date payStart,
                                 Date payEnd,
                                 Pageable pageable) {

        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseFunction function;
        Long minIndex = null;
        Long maxIndex = null;
        if (enterpriseId == null) {
            if (current.getParentId() != null) {
                minIndex = current.getMinCatalog();
                maxIndex = current.getMaxCatalog();
            }
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
            Assert.notNull(function, "企业不存在。");
            Assert.isTrue(function.getCatalog() >= current.getMinCatalog() && function.getCatalog() <= current.getMaxCatalog(), "无权获取此企业信息。");
            if (function.getParentId() != null) {
                minIndex = function.getMinCatalog();
                maxIndex = function.getMaxCatalog();
            }
        }
        Order model = new Order();
        if (status != null) {
            model.setOrderStatus(status);
        }
        model.setSelfPick(selfPick);
        model.setOrderCode(orderNo);
        //model.setShippingPhone(contactPhone);
        model.setShippingPerson(receiver);
        model.setShippingMobile(contactPhone);
	    model.setGoodsInfoType("0");
        return orderService.getOrder(model, maxIndex, minIndex, username, createStart, createEnd, payStart, payEnd, pageable);
    }

    @Override
    public Page<CreditOrder> getBackOrder(Long backOrderNo,
                                          Long enterpriseId,
                                          String phoneNo,
                                          Long orderNo,
                                          CreditOrderStatus status,
                                          Date start,
                                          Date end,
                                          Pageable pageable) {
        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseFunction function;
        Long minIndex = null;
        Long maxIndex = null;
        if (enterpriseId == null) {
            if (current.getParentId() != null) {
                minIndex = current.getMinCatalog();
                maxIndex = current.getMaxCatalog();
            }
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
            Assert.notNull(function, "企业不存在。");
            Assert.isTrue(function.getCatalog() >= current.getMinCatalog() && function.getCatalog() <= current.getMaxCatalog(), "无权获取此企业信息。");
            if (function.getParentId() != null) {
                minIndex = function.getMinCatalog();
                maxIndex = function.getMaxCatalog();
            }
        }
	    String goodsInfoType = "0";
        return creditOrderService.getBackOrder(backOrderNo, minIndex, maxIndex, phoneNo, orderNo, status, start, end, null, goodsInfoType, pageable);
    }

    @Override
    @Transactional
    public void reviewCreditOrder(long creditOrderNo, boolean agree, String message) {
        //1、非顶级企业审核退货单
        CreditOrder creditOrder = creditOrderMapper.selectByCreditOrderCode(creditOrderNo);
        List<BackOrderGoods> orderProductList = creditOrder.getBackOrderGoods();
        Long orderCode = creditOrder.getOrder().getOrderCode();
        Order order = orderMapper.selectByOrderCode(orderCode);
        Long enterpriseId = order.getEnterpriseId();
        if (order.getSelfPick()) {
            for (BackOrderGoods backOrderGoods : orderProductList) {
                Long goodsInfoId = backOrderGoods.getGoodsInfoId();
                int amount = backOrderGoods.getGoodsNum();
                InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
                //操作库存，记录库存变动
                inventoryService.addActuallyInventory(key, amount);
                inventoryService.addAvailableInventory(key, amount);
                InventoryChangeHistory changeHistory=new InventoryChangeHistory();
                changeHistory.setEnterpriseId(enterpriseId);
                changeHistory.setGoodsInfoId(goodsInfoId);
                changeHistory.setInventoryChangeAmount(amount);
                changeHistory.setInventoryChangeType(InventoryChangeType.ORDER_RETURNED);
                changeHistory.setBackOrderId(creditOrder.getBackOrderId());
                changeHistory.setCode(creditOrderNo);
                changeHistory.setChangeTime(new Date());
                changeHistory.setInventoryAfterChange(inventoryService.selectByPrimaryKeyForUpdate(new InventoryKey(enterpriseId,goodsInfoId)).getInventory());
                inventoryChangeService.insertSelective(changeHistory);
            }
        }
        creditOrderService.reviewCreditOrder(creditOrderNo, agree, currentLoginService.getCurrentLoginEnterpriseInfo().getEnterpriseName(), message);
    }

    @Override
    @Transactional
    public void receiveCreditOrder(long creditOrderNo, boolean receive, String enterpriseMsg, String customerMsg) {
        //1、非顶级企业退货单收货
        creditOrderService.receiveCreditOrder(creditOrderNo, receive, enterpriseMsg, customerMsg);
    }

    @Override
    @Transactional
    public void refund(long creditOrderNo, BigDecimal price, String msg,BigDecimal backSubsidyPrice) {

        Assert.notNull(price, "请填写退款金额！");
        if (!currentLoginService.isPrimaryEnterprise()) {
            throw new UserOperationException("非顶级账户无权操作。");
        }
        //顶级企业退款
        BigDecimal backCustomerPrice = price;
        creditOrderService.refund(creditOrderNo, price, currentLoginService.getCurrentLoginEnterpriseInfo().getEnterpriseName(), msg, backSubsidyPrice);
        CreditOrder creditOrder = creditOrderService.selectByCreditOrderCode(creditOrderNo);
        //3、退款给客户
        Long orderId = creditOrder.getOrderId();
        Order order = orderService.getOrderById(orderId);
        List<CustomerUcoin> list = parser.parseJSON(order.getCustomerUcoinJson(), new TypeToken<List<CustomerUcoin>>() {
        });
        //倒序list
        Collections.reverse(list);

        Map<Long, BigDecimal> map = new HashMap<>();
        for (CustomerUcoin customerUcoin : list) {
            //扣减记录中金额
            BackOrderUcoinHistory backOrderUcoinHistory = new BackOrderUcoinHistory();
            backOrderUcoinHistory.setCreateTime(new Date());
            backOrderUcoinHistory.setEnterpriseId(customerUcoin.getEnterpriseId());
            backOrderUcoinHistory.setCustomerId(creditOrder.getCustomerId());
            backOrderUcoinHistory.setBackOrderId(creditOrder.getBackOrderId());
            BigDecimal historyPrice = customerUcoin.getResePrice();

            if (price.compareTo(historyPrice) == 1) {
                customerUcoinService.grandUcoin(customerUcoin.getCustomerId(), customerUcoin.getEnterpriseId(), historyPrice, customerUcoin.getStartTime(), null, null, msg);
                //returnPrice=returnPrice.add(historyPrice);
                price = price.add(historyPrice.negate());
                backOrderUcoinHistory.setPrice(historyPrice);
                backOrderUcoinHistoryMapper.insert(backOrderUcoinHistory);
            } else {
                customerUcoinService.grandUcoin(customerUcoin.getCustomerId(), customerUcoin.getEnterpriseId(), price, customerUcoin.getStartTime(), null, null, msg);
                backOrderUcoinHistory.setPrice(price);
                backOrderUcoinHistoryMapper.insert(backOrderUcoinHistory);
                if (map.get(customerUcoin.getEnterpriseId()) == null) {
                    map.put(customerUcoin.getEnterpriseId(), price);
                } else {
                    map.put(customerUcoin.getEnterpriseId(), map.get(customerUcoin.getEnterpriseId()).add(price));
                }
                break;
            }

            if (map.get(customerUcoin.getEnterpriseId()) == null) {
                map.put(customerUcoin.getEnterpriseId(), customerUcoin.getResePrice());
            } else {
                map.put(customerUcoin.getEnterpriseId(), map.get(customerUcoin.getEnterpriseId()).add(customerUcoin.getResePrice()));
            }

           /* if (price.compareTo(BigDecimal.ZERO) != 1) {
                break;
            }*/
        }
        //退还网点补贴
        if (backSubsidyPrice != null && order.getEnterpriseId() != null) {
            enterpriseFunctionMapper.updateUndistributedPrice(order.getEnterpriseId(), backSubsidyPrice);
            EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(order.getEnterpriseId());
            EnterpriseUcoinHistory history=new EnterpriseUcoinHistory();
            history.setBalancePrice(function.getUndistributedPrice());
            history.setEnterpriseId(order.getEnterpriseId());
            history.setCreateTime(new Date());
            enterpriseUcoinHistoryMapper.insertSelective(history);
        }
        //计算商品总的结算价
        BigDecimal totalSettlePrice = BigDecimal.ZERO;
        for (OrderGoods goods : order.getOrderGoodsList()) {
            BigDecimal goodsInfoSettlePrice = goods.getGoodsInfoSettlePrice() == null ? BigDecimal.ZERO : goods.getGoodsInfoSettlePrice();
            totalSettlePrice = totalSettlePrice.add(goodsInfoSettlePrice.multiply(BigDecimal.valueOf(goods.getGoodsInfoNum())));
        }
        //4、退款日志
        if(backCustomerPrice.compareTo(BigDecimal.ZERO)==0){
            if(order.getIsValet()) {
                BigDecimal balancePrice = customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(creditOrder.getCustomerId(), order.getEnterpriseId());
                customerUcoinHistoryService.addHistory(creditOrder.getCustomerId(), order.getEnterpriseId(), null, HistoryType.UCOIN_REFUND, null, BigDecimal.ZERO, null, null, balancePrice, null, null, creditOrder.getOrderId(), null, totalSettlePrice);
            }else{
                Map<Long,BigDecimal> orderMap = new HashMap<>();
                for(CustomerUcoin item :list){
                    if(orderMap.get(item.getEnterpriseId())==null){
                        orderMap.put(item.getEnterpriseId(),item.getResePrice());
                    }else{
                        orderMap.put(item.getEnterpriseId(), orderMap.get(item.getEnterpriseId()).add(item.getResePrice()));
                    }
                }


                for (Map.Entry<Long, BigDecimal> entry : orderMap.entrySet()) {
                    BigDecimal enterpriseSettlePrice = new BigDecimal(entry.getValue().doubleValue() / order.getOrderPrice().doubleValue() * totalSettlePrice.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);

                    BigDecimal balancePrice = customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(creditOrder.getCustomerId(), entry.getKey());
                    customerUcoinHistoryService.addHistory(creditOrder.getCustomerId(), entry.getKey(), null, HistoryType.UCOIN_REFUND, null, entry.getValue(), null, null, balancePrice, null, null, creditOrder.getOrderId(), null, enterpriseSettlePrice);
                }
            }

        }else {
            for (Map.Entry<Long, BigDecimal> entry : map.entrySet()) {
                BigDecimal enterpriseSettlePrice = null;
                if (backCustomerPrice.compareTo(BigDecimal.ZERO) > 0) {
                    enterpriseSettlePrice = new BigDecimal(entry.getValue().doubleValue() / backCustomerPrice.doubleValue() * totalSettlePrice.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
                }
                BigDecimal balancePrice = customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(creditOrder.getCustomerId(), entry.getKey());
                customerUcoinHistoryService.addHistory(creditOrder.getCustomerId(), entry.getKey(), null, HistoryType.UCOIN_REFUND, null, entry.getValue(), null, null, balancePrice, null, null, creditOrder.getOrderId(), null, enterpriseSettlePrice);
            }
        }
    }

    @Override
    public CreditOrderStatus[] getStatuses() {
        return CreditOrderStatus.values();
    }

    @Override
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }

    @Override
    @Transactional
    public void statusToPayed(long orderCode) {
        Order order = orderService.getOrder(orderCode);
        Assert.notNull(order, "订单不存在。");
        long enterpriseId = order.getEnterpriseId();
        long goodsId = order.getOrderGoodsList().get(0).getGoodsInfoId();
        int count = (int) (long) (order.getOrderGoodsList().get(0).getGoodsInfoNum());
        orderService.payOrder(orderCode);
        inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, goodsId), -count);
    }

    @Override
    @Transactional
    public void cancelOrder(long orderCode) {
        Order order = orderService.getOrder(orderCode);
        Assert.notNull(order, "订单不存在。");
        long enterpriseId = order.getEnterpriseId();
        long goodsId = order.getOrderGoodsList().get(0).getGoodsInfoId();
        int count = (int) (long) (order.getOrderGoodsList().get(0).getGoodsInfoNum());
        orderService.cancelOrder(orderCode);
        inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsId), count);

    }

    @Override
    public void editPrice(long orderCode, BigDecimal price) {
        if (!currentLoginService.isPrimaryEnterprise()) {
            throw new UserOperationException("非顶级账户无权操作。");
        }
        orderService.editPrice(orderCode, price);
    }


    /**
     * 定时取消订单（代客下单未支付）
     */
    @Override
    public void cancelOrderTiming() {
        LOGGER.info("开始定时取消订单!!!!");
        List<Order> valetSubmitOrders = orderService.selectByValetSubmit();
        for (Order order : valetSubmitOrders) {
            cancelOrder(order.getOrderCode());
        }
        LOGGER.info("结束定时取消订单!!!!");
    }


    @Override
    @Transactional
    public void valetBackOrder(long orderNo, Map<Long, Integer> goodsIdMap, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay) {
       Long enterpriseId=currentLoginService.getCurrentLoginEnterpriseId();
        Order order = orderService.getOrder(orderNo);
        if (order.getOrderStatus() == OrderStatus.RECIEVED && order.getSelfPick() && order.getEnterpriseId().equals(currentLoginService.getCurrentLoginEnterpriseId())) {
            creditOrderService.createCreditOrder(orderNo, goodsIdMap, reason, credential, credentialDoc, remark, "2", true);
        }
        //代客退货，操作库存，并记录库存变化状态
        for(Long goodsInfoId:goodsIdMap.keySet()){
            inventoryService.addAvailableInventory(new InventoryKey(enterpriseId,goodsInfoId),goodsIdMap.get(goodsInfoId));
            inventoryService.addActuallyInventory(new InventoryKey(enterpriseId,goodsInfoId),goodsIdMap.get(goodsInfoId));
            InventoryChangeHistory changeHistory=new InventoryChangeHistory();
            changeHistory.setEnterpriseId(enterpriseId);
            changeHistory.setGoodsInfoId(goodsInfoId);
            changeHistory.setInventoryChangeAmount(goodsIdMap.get(goodsInfoId));
            changeHistory.setInventoryAfterChange(inventoryService.selectByPrimaryKey(new InventoryKey(enterpriseId, goodsInfoId)).getInventory());
            CreditOrder backOrder=creditOrderMapper.selectByOrderCode(orderNo);
            changeHistory.setCode(backOrder.getBackOrderCode());
            changeHistory.setInventoryChangeType(InventoryChangeType.ORDER_RETURNED);
            changeHistory.setChangeTime(new Date());
            changeHistory.setOrderId(order.getOrderId());
            inventoryChangeService.insertSelective(changeHistory);
        }

    }


    /**
     * 获取订单商品详情
     * */
    @Override
    public Order getOrderGoods(Long orderId) {
        return orderService.getOrderById(orderId);
    }


    @Override
    public List<BackOrderGoods> getBackOrderGoodsByOrderId(Long orderId) {
        return creditOrderService.getBackOrderByOrderId(orderId);
    }
}
