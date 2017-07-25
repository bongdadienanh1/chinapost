package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.ValetOrderService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerAddressService;
import com.ylife.customer.service.CustomerWithUcoinInfoService;
import com.ylife.data.json.json.Renderer;
import com.ylife.data.json.json.SimpleRenderer;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseUcoinHistoryMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseUcoinHistory;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.RecordNotFoundException;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryChangeHistory;
import com.ylife.inventory.model.InventoryChangeType;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryChangeService;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderGoods;
import com.ylife.order.service.OrderService;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.ucoin.mapper.OrderUcoinHistoryMapper;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.OrderUcoinHistory;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/29.
 * ValetOrderServiceImpl
 */
@Service
public class ValetOrderServiceImpl implements ValetOrderService {


    //折扣金额提醒系数
    private static final BigDecimal SUBSIDY_COEFFICIENT = new BigDecimal(0.75);

    @Resource
    private OrderService orderService;
    @Resource
    private CustomerAddressService customerAddressService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private CustomerWithUcoinInfoService customerWithUcoinInfoService;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private OrderUcoinHistoryMapper orderUcoinHistoryMapper;
    @Resource
    private InventoryChangeService inventoryChangeService;
    @Resource
    private EnterpriseUcoinHistoryMapper enterpriseUcoinHistoryMapper;
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;

    Renderer renderer=new SimpleRenderer();


    @Override
    public Page<GoodsManagerResult> getValetGoods(String goodsInfoName, BigDecimal lowPrice, BigDecimal highPrice, Pageable pageable) {


        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        List<Long> enterpriseIds=new ArrayList<>();
        enterpriseIds.add(enterpriseId);
        enterpriseIds=currentLoginService.getNodeId(enterpriseIds,enterpriseId);

        List<GoodsManagerResult> goodsManagerResults = goodsInfoMapper.selectValetGoods(enterpriseId, enterpriseIds,goodsInfoName, lowPrice, highPrice, pageable);
        int totalElements = goodsInfoMapper.countValeGoodsResult(enterpriseId,enterpriseIds,goodsInfoName, lowPrice, highPrice);
        return new PageImpl<>(pageable, totalElements, goodsManagerResults);
    }


    @Override
    public Page<GoodsManagerResult> getValetGoodsByEnterpriseId(String goodsInfoName, BigDecimal lowPrice, BigDecimal highPrice, Pageable pageable) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        List<GoodsManagerResult> goodsManagerResults = goodsInfoMapper.selectNetWorkGoods(enterpriseId,null, goodsInfoName, lowPrice, highPrice, pageable);
        int totalElements = goodsInfoMapper.countNetWorkGoods(enterpriseId,null, goodsInfoName, lowPrice, highPrice);
        return new PageImpl<>(pageable, totalElements, goodsManagerResults);
    }

    @Override
    public GoodsInfo getValetGoodsByGoodsInfoId(long goodsInfoId) {
        return goodsInfoMapper.selectByPrimaryKey(goodsInfoId);
    }

    @Override
    @Transactional
    public Order  placeOrder(long customerId, boolean isOnline, Long addressId,Map<Long,Integer> goodsInfo,String subsidyRemark,BigDecimal subsidyPrice) {
        //Assert.isTrue(count > 0, "数量必须大于0");
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerId);
        Assert.notNull(customer, "用户不存在。");
        if (!customer.getIsActive() && isOnline) {
            throw new UserOperationException("未激活用户无法下物流单。");
        }
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        if (!function.getHasPermit()) {
            throw new UserOperationException("此网点无代客下单免验证权限。");
        }
        List<Order> orders = orderService.submitOrder(customerId, addressId, goodsInfo, !isOnline, enterpriseId, currentLoginService.getCurrentLoginUsername(),true,"",subsidyRemark,subsidyPrice);
        if(addressId==null){
            for(Order order:orders){
                order.setShippingMobile(customer.getContactPhone());
                order.setShippingPerson(customer.getFullname());
                orderMapper.updateByPrimaryKeySelective(order);
            }
        }

        //代客自提验证库存，代客物流不验证库存

        List<GoodsManagerResult> errorList=new ArrayList<>();

        for (Long goodsId : goodsInfo.keySet())
            if (!isOnline) {

                try {
                    inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsId), -goodsInfo.get(goodsId));
                } catch (UserOperationException e) {
                    inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsId), goodsInfo.get(goodsId));
                    GoodsManagerResult goodsManagerResult=goodsInfoMapper.selectNetWorkGoods(enterpriseId,goodsId,null,null,null,null).get(0);
                    errorList.add(goodsManagerResult);
                }
            }

        if (errorList.size()>0){
            throw new UserOperationException(errorList);
        }
        return orders.get(0);
    }

    @Override
    @Transactional
    public Order submmitAndPay(long customerId, boolean isOnline, Long addressId,Map<Long,Integer> goodsInfo,String subsidyRemark,BigDecimal subsidyPrice,String payKey) {
        Order order=placeOrder(customerId,isOnline,addressId,goodsInfo,subsidyRemark,subsidyPrice);
        Long orderCode=order.getOrderCode();
        return payOrder(orderCode,subsidyPrice,payKey);
    }

    @Override
    public EnterpriseInfo getEnterpriseInfo() {
        return currentLoginService.getCurrentLoginEnterpriseInfo();
    }

    @Override
    public ChinapostCustomer getCustomerInfo(String idCard) {
        return customerWithUcoinInfoService.getInfo(idCard, currentLoginService.getCurrentLoginEnterpriseId());
    }


    @Override
    public GoodsInfo getGoodsInfo(long goodsInfoId) {
      Long enterpriseId= currentLoginService.getCurrentLoginEnterpriseId();
        return goodsInfoMapper.selectByGoodsINfoIdAndEnterpriseId(goodsInfoId,enterpriseId);
    }


    @Override
    public List<CustomerAddress> getAddresses(long customerId) {
        return customerAddressService.queryCustomerAddress(customerId);
    }

    @Override
    public CustomerAddress addAddress(long customerId, String addressName, String addressDetail, String addressMoblie, String isDefault,
                                      String infoProvince, String infoCity, String infoCounty, String infoStreet, String addressZip, String addressPhone) {
        //如果新添加地址设置为默认了，就把用户之前添加的地址设置为不是默认
        if (("1").equals(isDefault)) {
            // 地址列表
            List<CustomerAddress> addresses = customerAddressService.queryCustomerAddress(customerId);
            for (CustomerAddress item : addresses) {
                item.setIsDefault("0");
                customerAddressService.updateAddress(item, customerId);
            }
        }
        if (StringUtil.isBlank(isDefault)) {
            isDefault = "0";
        }
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setCustomerId(customerId);
        customerAddress.setAddressName(addressName);
        customerAddress.setAddressDetail(addressDetail);
        customerAddress.setAddressMoblie(addressMoblie);
        customerAddress.setIsDefault(isDefault);
        customerAddress.setInfoProvince(infoProvince);
        customerAddress.setInfoCity(infoCity);
        customerAddress.setInfoCounty(infoCounty);
        customerAddress.setInfoStreet(infoStreet);
        customerAddress.setAddressZip(addressZip);
        customerAddress.setAddressPhone(addressPhone);
        customerAddressService.addAddress(customerAddress, customerId);
        return customerAddress;
    }

    @Override
    public void deleteAddress(long addressId) {
        customerAddressService.deleteAddress(addressId);
    }

    @Override
    @Transactional
    public Order payOrder(long orderCode, BigDecimal subsidyPrice, String payKey) {
        Order order = orderService.getOrder(orderCode);
        long enterpriseId = order.getEnterpriseId();

        EnterpriseFunction function=enterpriseService.getEnterpriseFunction(enterpriseId);
        if(!function.getHasPermit()){
            throw new UserOperationException("当前账户没有免验证权限，请开启！");
        }

        long customerId = order.getCustomerId();
        BigDecimal orderPrice = order.getOrderPrice();
        BigDecimal customerPrice=orderPrice;
        if (subsidyPrice!=null && subsidyPrice.compareTo(BigDecimal.ZERO) == 1) {
            if (subsidyPrice.compareTo(orderPrice) == 1) {
                throw new UserOperationException("补贴金额高于订单金额！");
            }
            enterpriseService.addUcoinAmount(enterpriseId, subsidyPrice.negate());
            function=enterpriseFunctionService.get(enterpriseId);
            EnterpriseUcoinHistory history=new EnterpriseUcoinHistory();
            history.setBalancePrice(function.getUndistributedPrice());
            history.setEnterpriseId(enterpriseId);
            history.setCreateTime(new Date());
            enterpriseUcoinHistoryMapper.insertSelective(history);
            customerPrice = orderPrice.subtract(subsidyPrice);
        }

        orderService.payOrder(orderCode);
        for (OrderGoods orderGoods : order.getOrderGoodsList()) {
            InventoryChangeHistory changeHistory=new InventoryChangeHistory();
            inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, orderGoods.getGoodsInfoId()), -orderGoods.getGoodsInfoNum().intValue());
            changeHistory.setChangeTime(new Date());
            changeHistory.setCode(orderCode);
            changeHistory.setInventoryChangeAmount(orderGoods.getGoodsInfoNum().intValue());
            changeHistory.setInventoryChangeType(InventoryChangeType.ORDER_CONSUME);
            changeHistory.setEnterpriseId(enterpriseId);
            changeHistory.setGoodsInfoId(orderGoods.getGoodsInfoId());
            changeHistory.setInventoryAfterChange(inventoryService.getInventory(enterpriseId,orderGoods.getGoodsInfoId()).getInventory());
            inventoryChangeService.insertSelective(changeHistory);
        }

        //计算商品总的结算价
        BigDecimal totalSettlePrice = BigDecimal.ZERO;
        for(OrderGoods goods:order.getOrderGoodsList()){
            BigDecimal goodsInfoSettlePrice = goods.getGoodsInfoSettlePrice()==null?BigDecimal.ZERO:goods.getGoodsInfoSettlePrice();
            totalSettlePrice = totalSettlePrice.add(goodsInfoSettlePrice.multiply(BigDecimal.valueOf(goods.getGoodsInfoNum())));
        }

        List<CustomerUcoin> customerUcoinList = customerUcoinService.ucoinPay(customerId, customerPrice, null, order.getOrderId(), enterpriseId,totalSettlePrice);

        Order orderUpdate = new Order();
        orderUpdate.setOrderCode(orderCode);
        orderUpdate.setCustomerUcoinJson(renderer.render(customerUcoinList));
        orderMapper.updateByOrderCodeSelective(orderUpdate);

        for (CustomerUcoin customerUcoin : customerUcoinList) {
            OrderUcoinHistory orderUcoinHistory = new OrderUcoinHistory();
            orderUcoinHistory.setEnterpriseId(customerUcoin.getEnterpriseId());
            orderUcoinHistory.setCustomerId(customerUcoin.getCustomerId());
            orderUcoinHistory.setOrderId(order.getOrderId());
            orderUcoinHistory.setPrice(customerUcoin.getResePrice());
            orderUcoinHistory.setCreateTime(new Date());
            orderUcoinHistoryMapper.insertSelective(orderUcoinHistory);
        }
        return orderMapper.selectByOrderCode(orderCode);
    }

    @Override
    public Order getOrderByDeliveryCode(String deliveryCode) {
        Order order = orderMapper.selectByDeliveryCode(deliveryCode);
        if (order == null) {
            throw new RecordNotFoundException("订单不存在。");
        }
        return order;
    }

    @Override
    public Order getOrderByCode(long code) {
        return orderService.getOrder(code);
    }

    @Override
    @Transactional
    public void pickUpOrder(String deliveryCode) {
        Order order = getOrderByDeliveryCode(deliveryCode);
        if (order == null) {
            throw new RecordNotFoundException("订单不存在。");
        }
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (!order.getEnterpriseId().equals(enterpriseId)) {
            throw new RecordNotFoundException("该提货码不属于该网点！");
        }
        long orderCode = order.getOrderCode();
        //提货
        orderService.pickUpOrder(orderCode);
        //修改库存
        Order order2 = orderService.getOrder(orderCode);
        if (order2 != null) {
            for (OrderGoods orderGoods : order.getOrderGoodsList()) {
                Long inventoryEnterpriseId=order.getOrderId();
                Long goodInfoId=orderGoods.getGoodsInfoId();
                InventoryChangeHistory changeHistory=new InventoryChangeHistory();
                inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, goodInfoId), -orderGoods.getGoodsInfoNum().intValue());
                changeHistory.setEnterpriseId(enterpriseId);
                changeHistory.setGoodsInfoId(goodInfoId);
                changeHistory.setInventoryChangeAmount(orderGoods.getGoodsInfoNum().intValue());
                changeHistory.setInventoryChangeType(InventoryChangeType.ORDER_CONSUME);
                changeHistory.setOrderId(order.getOrderId());
                changeHistory.setCode(orderCode);
                changeHistory.setChangeTime(new Date());
                changeHistory.setInventoryAfterChange( inventoryService.selectByPrimaryKeyForUpdate(new InventoryKey(enterpriseId,goodInfoId)).getInventory());
                inventoryChangeService.insertSelective(changeHistory);
            }
        }
    }

    @Override
    public boolean hasPermit() {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        return function.getHasPermit();
    }

    @Override
    public boolean hasInventory(long goodInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Inventory inventory = inventoryService.getInventory(enterpriseId, goodInfoId);
        return inventory != null && inventory.getAvailable() > 0;
    }

    @Override
    public BigDecimal getCustomerBalance(String idCard) {
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(idCard);
        if (customer == null) {
            return BigDecimal.ZERO;
        }
        return customerUcoinService.getUcoinBalance(customer.getId());
    }
}
