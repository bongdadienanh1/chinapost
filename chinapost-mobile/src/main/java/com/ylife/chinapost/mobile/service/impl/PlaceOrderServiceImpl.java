package com.ylife.chinapost.mobile.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.mobile.service.CurrentLoginService;
import com.ylife.chinapost.mobile.service.PlaceOrderService;
import com.ylife.common.model.ApiResult;
import com.ylife.common.model.OpenUtil;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.Renderer;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.json.json.SimpleRenderer;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderGoods;
import com.ylife.order.service.OrderService;
import com.ylife.payorder.entity.PayOrderEntity;
import com.ylife.payorder.service.PayOrderService;
import com.ylife.shoppingcart.mapper.ShoppingCartMapper;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.shoppingcart.service.ShoppingCartService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatform.mapper.ThirdPlatformMapper;
import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.ucoin.mapper.OrderUcoinHistoryMapper;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.model.OrderUcoinHistory;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/8.
 * PlaceOrderServiceImpl
 */
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Resource
    private OrderService orderService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderUcoinHistoryMapper orderUcoinHistoryMapper;
    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private PayOrderService payOrderService;
    @Resource
    private ThirdPlatformMapper thirdPlatformMapper;
    @Resource
    private ThirdPlatformInterfaceService thirdPlatformInterfaceService;

    Renderer renderer=new SimpleRenderer();

    Parser SIMPLE_PARSER = new SimpleParser();

    @Override
    @Transactional
    public String submitOrderAndPay(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId, String paykey) {
        List<Order> orders = submitOrder(addressId, shoppingCartId, selfPick, remark, enterpriseId,paykey);
        long[] orderCodes = new long[orders.size()];
        for (int i = 0; i < orderCodes.length; i++) {
            orderCodes[i] = orders.get(i).getOrderCode();
        }
        //付款
        payOrder(orderCodes, paykey);
        if (selfPick) {
            return new JsonResponseBean(getDeliveryCode(orders.get(0).getOrderCode())).toJson();
        } else {
            return JsonResponseBean.getSuccessResponse().toJson();
        }
    }

    @Override
    @Transactional
    public List<Order> submitOrder(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId,String paykey) {
        if (selfPick) {
            Assert.notNull(enterpriseId);
        }
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        if (!chinapostCustomerService.checkPaykey(customerId, paykey)) {
            throw new UserOperationException("支付密码错误！");
        }
        // 1、获取所有所有选中商品信息
        List<ShoppingCart> cartlist = shoppingCartMapper.shopCartListByIds(Arrays.asList(shoppingCartId));
        if (cartlist.size() == 0) {
            throw new UserOperationException("购物车为空。");
        }
        Map<Long, Integer> goodsInfoList = new HashMap<>();
        for (ShoppingCart item : cartlist) {
            long goodsInfoId = item.getGoodsInfoId();
            int goodsNum = item.getGoodsNum().intValue();
            goodsInfoList.put(goodsInfoId, goodsNum);
            //如果自提,修改可用库存。
            if (selfPick) {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsInfoId), -goodsNum);
            }
        }
        //2、提交订单
        List<Order> orders = orderService.submitOrder(customerId, addressId, goodsInfoList, selfPick, enterpriseId, null, false, remark,null,null);
        //3、修改所有购买商品为已经删除
        shoppingCartService.deleteShoppingCartByIds(shoppingCartId);
        return orders;
    }

    @Override
    @Transactional
    public void payOrder(long[] orderCodes, String paykey) {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        if (!chinapostCustomerService.checkPaykey(customerId, paykey)) {
            throw new UserOperationException("支付密码错误！");
        }
        for (long orderCode : orderCodes) {
            orderService.payOrder(orderCode);
            Order order = orderService.getOrder(orderCode);
            List<CustomerUcoin> customerUcoinList=customerUcoinService.ucoinPay(customerId, order.getOrderPrice(), null, order.getOrderId());
            Order orderUpdate=new Order();
            orderUpdate.setOrderCode(orderCode);
            orderUpdate.setCustomerUcoinJson(renderer.render(customerUcoinList));
            orderMapper.updateByOrderCodeSelective(orderUpdate);
            Map<Long,BigDecimal> map = new HashMap<>();
            for(CustomerUcoin customerUcoin:customerUcoinList) {
                OrderUcoinHistory orderUcoinHistory=new OrderUcoinHistory();
                orderUcoinHistory.setEnterpriseId(customerUcoin.getEnterpriseId());
                orderUcoinHistory.setCustomerId(customerUcoin.getCustomerId());
                orderUcoinHistory.setOrderId(order.getOrderId());
                orderUcoinHistory.setPrice(customerUcoin.getResePrice());
                orderUcoinHistory.setCreateTime(new Date());
                orderUcoinHistoryMapper.insertSelective(orderUcoinHistory);
                if(map.get(customerUcoin.getEnterpriseId())==null){
                    map.put(customerUcoin.getEnterpriseId(),customerUcoin.getResePrice());
                }else{
                    map.put(customerUcoin.getEnterpriseId(), map.get(customerUcoin.getEnterpriseId()).add(customerUcoin.getResePrice()));
                }
            }
            //计算商品总的结算价
            BigDecimal totalSettlePrice = BigDecimal.ZERO;
            for(OrderGoods goods:order.getOrderGoodsList()) {
                BigDecimal goodsInfoSettlePrice = goods.getGoodsInfoSettlePrice() == null ? BigDecimal.ZERO : goods.getGoodsInfoSettlePrice();
                BigDecimal currentGoodsInfoSettlePrice = goodsInfoSettlePrice.multiply(BigDecimal.valueOf(goods.getGoodsInfoNum()));
                BigDecimal currentGoodsInfoPrice = goods.getGoodsInfoPrice().multiply(BigDecimal.valueOf(goods.getGoodsInfoNum()));
                totalSettlePrice = totalSettlePrice.add(currentGoodsInfoSettlePrice);
                //保存付款明细
                for(CustomerUcoin customerUcoin:customerUcoinList) {
                    if(currentGoodsInfoPrice.equals(BigDecimal.ZERO)){
                        break;
                    }
                    if(customerUcoin.getResePrice().equals(BigDecimal.ZERO)){
                        continue;
                    }

                    PayOrderEntity payOrderEntity = new PayOrderEntity();
                    if(currentGoodsInfoPrice.compareTo(customerUcoin.getResePrice())>=0){
                        currentGoodsInfoPrice = currentGoodsInfoPrice.subtract(customerUcoin.getResePrice());
                        payOrderEntity.setPrice(customerUcoin.getResePrice());
                        payOrderEntity.setSettlePrice(new BigDecimal(customerUcoin.getResePrice().doubleValue() / goods.getGoodsInfoSumPrice().doubleValue() * currentGoodsInfoSettlePrice.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP));
                        customerUcoin.setResePrice(BigDecimal.ZERO);
                    }else {
                        customerUcoin.setResePrice(customerUcoin.getResePrice().subtract(currentGoodsInfoPrice));
                        payOrderEntity.setPrice(currentGoodsInfoPrice);
                        payOrderEntity.setSettlePrice(new BigDecimal(currentGoodsInfoPrice.doubleValue()/goods.getGoodsInfoSumPrice().doubleValue()*currentGoodsInfoSettlePrice.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP));
                        currentGoodsInfoPrice = BigDecimal.ZERO;
                    }

                    payOrderEntity.setCreateTime(DateUtil.formatToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
                    if(customerUcoin.getStartTime() !=null) {
                        payOrderEntity.setStartTime(DateUtil.formatToString(customerUcoin.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
                    }
                    if(customerUcoin.getEndTime() != null){
                        payOrderEntity.setEndTime(DateUtil.formatToString(customerUcoin.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
                    }
                    payOrderEntity.setGoodsInfoId(goods.getGoodsInfoId());
                    payOrderEntity.setGoodsInfoName(goods.getGoodsInfoName());
                    payOrderEntity.setThirdId(order.getBusiness().getBusinessId());
                    payOrderEntity.setThirdName(order.getBusiness().getBusinessName());
                    payOrderEntity.setEnterpriseId(customerUcoin.getEnterpriseId());
                    payOrderEntity.setOrderCode(orderCode);

                    payOrderService.addPayOrder(payOrderEntity);
                }
            }
            //增加会员邮豆消耗记录
            for(Map.Entry<Long,BigDecimal> entry:map.entrySet()){
                BigDecimal enterpriseSettlePrice = null;
                if(order.getOrderPrice().compareTo(BigDecimal.ZERO)>0){
                    enterpriseSettlePrice = new BigDecimal(entry.getValue().doubleValue()/order.getOrderPrice().doubleValue()*totalSettlePrice.doubleValue()).setScale(2,BigDecimal.ROUND_HALF_UP);
                }
                BigDecimal balancePrice = customerUcoinMapper.sumResePriceByCustomerIdAndEnterpriseId(customerId, entry.getKey());
                customerUcoinHistoryService.addHistory(customerId, entry.getKey(), null, HistoryType.UCOIN_CONSUME, null, entry.getValue(), null, null, balancePrice, null, null, order.getOrderId(), null, enterpriseSettlePrice);
            }

            //抛单
            if(!order.getIsValet() && order.getGoodsInfoType().equals("1")){
                ThirdPlatformEntity item = thirdPlatformMapper.queryByStoreId(order.getBusinessId());
                if(item.getPlatformType().equals("1")) {
                    syncOrder(order);
                }
            }
        }
    }

    /**
     * 邮政抛单
     *
     * @param order
     */
    @Override
    public void syncOrder(Order order) {
        //获取抛单信息
        ThirdPlatformInterfaceEntity item = thirdPlatformInterfaceService.queryByStoreId(order.getBusinessId());
        Assert.notNull(item);
        //结算价
        BigDecimal payPrice = BigDecimal.ZERO;
        for(OrderGoods  orderGoods:order.getOrderGoodsList()){
            payPrice = payPrice.add((orderGoods.getGoodsInfoSettlePrice()==null? BigDecimal.ZERO:orderGoods.getGoodsInfoSettlePrice()).multiply(BigDecimal.valueOf(orderGoods.getGoodsInfoNum())));
        }
        //抛单参数
        Map<String,String> map = new HashMap<>();
        String timestamp =DateUtil.formatToString(new Date(), "yyyyMMddHHmmss");
        map.put("merchantId",item.getMerchantId());
        map.put("timestamp",timestamp);
        map.put("sign", MD5Util.md5Hex(item.getMerchantId() + timestamp + item.getMerchantKey()));
        map.put("payPrice", String.valueOf(payPrice.multiply(BigDecimal.valueOf(100))));
        map.put("mobile",order.getShippingMobile());
        map.put("userName",order.getShippingPerson());
        map.put("address",order.getShippingProvince().concat(order.getShippingCity().concat(order.getShippingCounty().concat(order.getShippingAddress()))));
        map.put("orderCode", String.valueOf(order.getOrderCode()));
        map.put("orderGoodsList", JSON.toJSONString(order.getOrderGoodsList(), SerializerFeature.DisableCircularReferenceDetect));
        map.put("payTime",DateUtil.formatToString(order.getPayTime(), "yyyy-MM-dd HH:mm:ss"));

        //抛单
        ApiResult apiResult = SIMPLE_PARSER.parseJSON(HttpUtil.doPost(item.getPaymentNotice(), map), new TypeToken<ApiResult>() {
        });
        //验签
        if(OpenUtil.isCheck(apiResult.getMerchantId(), apiResult.getTimestamp(), apiResult.getSign()).getReturnCode()!=200){
            //TODO:失败
        }
    }

    @Override
    @Transactional
    public void cancelOrder(long orderCode) {
        //取消订单。
        orderService.cancelOrder(orderCode);
        //如果为自提，增加可用库存。
        Order order = orderService.getOrder(orderCode);
        if (order.getSelfPick()) {
            long enterpriseId = order.getEnterpriseId();
            for (OrderGoods goods : order.getOrderGoodsList()) {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goods.getGoodsInfoId()), goods.getGoodsInfoNum().intValue());
            }
        }
    }

    @Override
    @Transactional
    public void confirmOrder(long orderCode) {
        orderService.confirmOrder(orderCode);
    }

    @Override
    public String getDeliveryCode(long orderCode) {
        return orderService.getOrder(orderCode).getDeliveryCode();
    }

    @Override
    public Order getOrder(long orderCode) {
        return orderService.getOrder(orderCode);
    }

    @Override
    public boolean isPaykeySet() {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerId);
        return !StringUtil.isBlank(customer.getPaykey());
    }

    @Override
    public void setPaykey(String paykey) {
        Assert.hasLength(paykey);
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        chinapostCustomerService.setPaymentPassword(customerId, paykey.trim());
    }

    @Override
    public List<EnterpriseInfo> getSuggestEnterprise() {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        return enterpriseInfoService.getCustomerLinkedEnterpriseInfo(customerId);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseByDistrictId(long districtId) {
        return enterpriseInfoService.getEnterpriseInfosByDistrictId(districtId);
    }
}
