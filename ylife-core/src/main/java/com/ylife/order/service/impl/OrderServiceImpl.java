package com.ylife.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ylife.client.bean.ExpressNoVo;
import com.ylife.client.bean.OOrder;
import com.ylife.client.bean.OOrderAllInfo;
import com.ylife.client.bean.SyncStatus;
import com.ylife.client.mapper.SyncStatusMapper;
import com.ylife.client.service.OpenOrderService;
import com.ylife.client.service.PlatformClient;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.service.CustomerAddressService;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.deliveryCode.service.DeliveryCodeService;
import com.ylife.exception.RecordNotFoundException;
import com.ylife.exception.ServerIOException;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.mapper.GoodsProductMapper;
import com.ylife.goods.model.GoodsDetailBean;
import com.ylife.goods.model.GoodsImage;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.order.util.OrderServiceUtil;
import com.ylife.order.mapper.OrderContainerRelationMapper;
import com.ylife.order.mapper.OrderGoodsMapper;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.*;
import com.ylife.order.service.OrderService;
import com.ylife.shoppingcart.mapper.ShoppingCartMapper;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.shoppingcart.service.ShoppingCartService;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtilXssf;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * 订单支付service实现
 *
 * @author BOSS-WANG
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private CustomerAddressService addressService;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private GoodsProductMapper goodsProductMapper;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderGoodsMapper orderGoodsMapper;
    @Resource
    private GoodsProductService siteGoodsProductService;
    @Resource
    private DeliveryCodeService deliveryCodeService;
    @Resource
    private OpenOrderService openOrderService;
    @Resource
    private SyncStatusMapper syncStatusMapper;
    @Resource
    private OrderContainerRelationMapper orderContainerRelationMapper;


    private Generator generator = IdGeneratorFactory.create("ORDER");

    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    @Override
    public Order getOrderById(long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public Page<Order> getOrder(Order model, Long maxCatalog, Long minCatalog, String username, Date createStart, Date createEnd, Date payStart, Date payEnd, Pageable pageable) {
        List<Order> list = orderMapper.selectByModelSelective(model, maxCatalog, minCatalog, username, createStart, createEnd, payStart, payEnd,pageable);
        int totalElements = orderMapper.countByModelSelective(model, maxCatalog, minCatalog, username, createStart, createEnd, payStart, payEnd);
        return new PageImpl<>(pageable, totalElements, list);
    }

    /**
     * C端订单提交
     *
     * @param customerId     用户ID
     * @param addressId      地址ID
     * @param shoppingCartId 购物车
     * @param isSelfPickUp   是否自提
     * @param remark         买家备注
     * @param enterpriseId   自提节点ID
     */
    @Override
    @Transactional
    public List<Order> newsubmitOrder(long customerId, Long addressId, Long[] shoppingCartId, boolean isSelfPickUp, String remark, Long enterpriseId) {

        // 1、获取所有所有选中商品信息
        List<ShoppingCart> cartlist = shoppingCartMapper.shopCartListByIds(Arrays.asList(shoppingCartId));
        if (CollectionUtils.isEmpty(cartlist)) {
            return Collections.emptyList();
        }

        Map<Long, Integer> goodsInfoList = new HashMap<>();
        for (ShoppingCart item : cartlist) {
            goodsInfoList.put(item.getGoodsInfoId(), item.getGoodsNum().intValue());
        }
        //2、提交订单
        List<Order> maps = submitOrder(customerId, addressId, goodsInfoList, isSelfPickUp, enterpriseId, null, true, remark,null,null);
        //3、修改所有购买商品为已经删除
        shoppingCartService.deleteShoppingCartByIds(shoppingCartId);
        return maps;
    }

    /**
     * B端代客下单 保存订单
     *
     * @param customerId   用户ID
     * @param addressId    用户地址ID
     * @param goodsInfo    货品Id和数量
     * @param isSelfPickUp 是否自提订单
     * @param enterpriseId 自提节点ID
     */
    @Override
    @Transactional
    public List<Order> submitOrder(long customerId, Long addressId,Map<Long,Integer> goodsInfo, boolean isSelfPickUp, Long enterpriseId, String operator,String subsidyRemark,BigDecimal subsidyPrice) {
        return submitOrder(customerId, addressId, goodsInfo, isSelfPickUp, enterpriseId, operator, true, "",subsidyRemark,subsidyPrice);
    }

    /**
     * 保存订单
     *
     * @param customerId    用户ID
     * @param addressId     用户地址ID
     * @param goodsInfoList 购买的商品信息 key为货品Id，value为购买的数量
     * @param isSelfPickUp  自提标志(true代表自提，false代表不是自提)
     * @param enterpriseId  自提节点ID
     * @param isValet       是否为代客下单（true表示为代客下单，false表示自主下单）
     * @param remark        买家备注
     */
    public List<Order> submitOrder(long customerId, Long addressId, Map<Long, Integer> goodsInfoList, boolean isSelfPickUp, Long enterpriseId, String operator, boolean isValet, String remark,String subsidyRemark,BigDecimal subsidyPrice) {

        // 1、验证数据正确性
        Assert.notEmpty(goodsInfoList);

        //2、
        // 2.1、查询收提交订单货地址
        CustomerAddress ca = addressService.queryCustomerAddressById(addressId, customerId);

        if (ca == null && !isSelfPickUp) {
            throw new UserOperationException("用户收货地址不存在。");
        }
        //2.2 拆单
        Map<Long, List<OrderGoods>> thirdIdMap = new HashMap<>();
        for (Long key : goodsInfoList.keySet()) {
            // 查询商品详细
            GoodsDetailBean goodsDetailBean = siteGoodsProductService.queryDetailBeanByProductId(key, 0L, null);
            Assert.notNull(goodsDetailBean, "商品不存在或者未上架。");
//            //修改货品销售数量
//            GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(key);
//            Assert.notNull(goodsProduct, "商品不存在或者未上架。");
//            goodsProduct.setGoodsSaleNum(goodsProduct.getGoodsSaleNum() + goodsInfoList.get(key));
//            goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);

            //订单对应的货品
            OrderGoods og = new OrderGoods();
            og.setGoodsInfoNum(1L);
            og.setDelFlag("0");
            og.setBuyTime(new Date());
            og.setEvaluateFlag("0");
            // 设置商品原价
            og.setGoodsInfoOldPrice(goodsDetailBean.getProductVo().getGoodsInfoPreferPrice());
            og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
            // 数量
            og.setGoodsInfoNum(goodsInfoList.get(key).longValue());
            // 设置货品总价格（数量*价格）
            og.setGoodsInfoSumPrice(og.getGoodsInfoOldPrice().multiply(new BigDecimal(goodsInfoList.get(key))));
            // 设置货品Id
            og.setGoodsInfoId(key);
            // 设置商品ID
            og.setGoodsId(goodsDetailBean.getProductVo().getGoodsId());
            // 货品名称
            og.setGoodsInfoName(goodsDetailBean.getProductVo().getProductName());
            // 货品图片
            List<GoodsImage> goodsImageList = goodsDetailBean.getProductVo().getImageList();
            if (goodsImageList != null && goodsImageList.size() > 0) {
                og.setGoodsImg(goodsImageList.get(0).getImageThumName());
            }

            //自提不需要拆单，物流进行拆单
            Long thirdId;
            if (isSelfPickUp) {
                thirdId = enterpriseId;
            } else {
                thirdId = goodsDetailBean.getProductVo().getThirdId();
            }
            List<OrderGoods> orderGoods = thirdIdMap.get(thirdId);
            if (orderGoods != null) {
                orderGoods.add(og);
            } else {
                orderGoods = new ArrayList<>();
                orderGoods.add(og);
                thirdIdMap.put(thirdId, orderGoods);
            }

        }

        // 2.3 保存
        Long orderOldCode = generator.generate();
        List<Order> list = new ArrayList<>();
        for (Long thirdId : thirdIdMap.keySet()) {
            Order order = new Order();
            BigDecimal sumPrice = BigDecimal.ZERO;
            List<OrderGoods> orderGoodsList = thirdIdMap.get(thirdId);
            for (OrderGoods og : orderGoodsList) {
                sumPrice = sumPrice.add(og.getGoodsInfoSumPrice());
            }

            // 设置配送方式
            order.setOrderExpressType("0");

            // 子订单号
            long orderCode = generator.generate();
            order.setOrderCode(orderCode);
            order.setOrderOldCode(orderOldCode);
            // 插入订单商品
            order.setOrderGoodsList(orderGoodsList);

            // 发票信息String invoiceType,String invoiceTitle,
            order.setInvoiceType("0");

            // 订单删除标志
            order.setDelFlag("0");

            // 设置为手机订单 ，如果是微信，则设置为微信订单
            order.setOrderMType("2");
            // 订单状态
            order.setOrderStatus(OrderStatus.SUBMITED);
            // 总金额
            order.setOrderPrice(sumPrice);
            //其他金额
            order.setOtherAmount(sumPrice);
            // 原始总额
            order.setOrderOldPrice(sumPrice);
            // 总优惠金额
            order.setOrderPrePrice(BigDecimal.ZERO);
            order.setIsValet(isValet);          //是否代客
            order.setSelfPick(isSelfPickUp);    //是否自提
            order.setOrderCargoStatus("2");     //待出库
            if (isSelfPickUp) {     //如果为自提订单，运费0。
                order.setExpressPrice(BigDecimal.ZERO);
            } else {
                order.setExpressPrice(BigDecimal.valueOf(0).multiply(BigDecimal.valueOf(1250)));
                // 总金额
                order.setOrderPrice(sumPrice.add(BigDecimal.valueOf(0).multiply(BigDecimal.valueOf(1250))));
                //其他金额
                order.setOtherAmount(sumPrice.add(BigDecimal.valueOf(0).multiply(BigDecimal.valueOf(1250))));
                //商家
                order.setBusinessId(thirdId);
                //收货人信息
                order.setShoppingAddrId(addressId);
                order.setShippingProvince(ca.getProvince().getProvinceName());
                order.setShippingCity(ca.getCity().getCityName());
                order.setShippingCounty(ca.getDistrict().getDistrictName());
                order.setShippingAddress(ca.getAddressDetail());
                order.setShippingPerson(ca.getAddressName());
                order.setShippingPhone(ca.getAddressPhone());
                order.setShippingMobile(ca.getAddressMoblie());
                order.setShippingPostcode(ca.getAddressZip());
            }
            // 在线支付
            order.setOrderLinePay("1");
            // 支付方式
            order.setPayId(1L);
            order.setCustomerId(customerId);
            order.setCreateTime(new Date());
            //节点ID
            order.setEnterpriseId(enterpriseId);
            //买家备注
            order.setCustomerRemark(remark);
            order.setOperator(operator);

            order.setSubsidyPrice(subsidyPrice);
            order.setSubsidyRemark(subsidyRemark);
            //线上订单
            if(!isValet) {
                order.setGoodsInfoType("1");
            }

            // 插入订单主表
            int f = orderMapper.insertSelective(order);
            // 插叙返回的ID
            if (f == 1) {
                list.add(order);
                Long orderId = order.getOrderId();
                // 循环设置货品级联ID信息
                for (OrderGoods goods : orderGoodsList) {
                    goods.setOrderId(orderId);
                    // 插入货品
                    orderGoodsMapper.insertOrderGoodsInfo(goods);
                }
                //拣货、装箱
                if(!isValet) {
                    OrderContainerRelation orderContainerRelation = new OrderContainerRelation();
                    orderContainerRelation.setOrderId(order.getOrderId());
                    orderContainerRelationMapper.insertSelective(orderContainerRelation);
                }
            }
        }
        return list;
    }

    @Override
    public void pickUpOrder(long orderCode) {
        Order order = getOrder(orderCode);
        //修改订单状态
        orderMapper.updateOrderStatusByOrderCode(orderCode, OrderStatus.RECIEVED);
        //回收提货码
        deliveryCodeService.recycleDeliveryCode(order.getDeliveryCode());
        //将订单提货码置空
        orderMapper.updateDeliveryCodeToNullByOrderCode(orderCode);
        //同步C端自提点订单到优生活商城
        if (order.getSelfPick() && !order.getIsValet()) {
            syncConfirmOrder(orderCode);
        }
    }

    @Override
    public void payOrder(long orderCode) {
        Order order = getOrder(orderCode);
        //1、支付完成后修改订单状态
        Order model = new Order();
        model.setOrderId(order.getOrderId());
        model.setOrderStatus(OrderStatus.PAYED);
        model.setPayTime(new Date());
        model.setUcoinPayTime(new Date());
        model.setUcoinPrise(order.getOrderPrice());
        model.setOtherAmount(BigDecimal.ZERO);

        boolean finish = false;
        if (order.getSelfPick()) {
            //C端自提时，生成自提码
            if (!order.getIsValet()) {
                model.setDeliveryCode(deliveryCodeService.getDeliveryCode());
            } else {
                model.setOrderStatus(OrderStatus.RECIEVED);
                model.setGetGoodsTime(new Date());
                finish = true;
            }
        }
        orderMapper.updateByPrimaryKeySelective(model);

        //2、同步数据到优生活商城（B端自提单订单状态直接为交易完成，其余情况为已付款未发货）
        syncSubmitOrder(orderCode);
        if (finish) {
            syncConfirmOrder(orderCode);
        }

        //TODO:订单log
    }

    /**
     * 同步订单数据（邮宝支付完成调用）
     *
     * @param orderCode 订单ID
     */
    public void syncSubmitOrder(long orderCode) {
        Order order = getOrder(orderCode);
        //同步订单
        String url = "order/submitOrder.htm";
        Map<String, String> params = new HashMap<>();
        if (order.getSelfPick()) {//如果为自提，同步的买家信息为 C端用户个人中心的地址、联系电话
            params.put("addressName", order.getCustomerInfo().getFullName());
            params.put("addressMoblie", order.getCustomerInfo().getContactPhone());
        } else {//物流单，同步的买家信息为 收货人地址、联系电话
            params.put("addressName", order.getShippingPerson());
            params.put("addressMoblie", order.getShippingMobile());
            params.put("addressDetailInfo", order.getShippingAddress());
            params.put("addressProvice", order.getShippingProvince());
            params.put("addressCity", order.getShippingCity());
            params.put("addressCounty", order.getShippingCounty());
        }
        params.put("orderCode", order.getOrderCode() + "");
        params.put("orderPrice", order.getOrderPrice().toString());
        params.put("ucoinPrice", order.getUcoinPrise().toString());
        params.put("customerRemark", order.getCustomerRemark());
        params.put("expressPrice", order.getExpressPrice().toString());
        params.put("selfPick", order.getSelfPick() ? "1" : "0");
        if (order.getSelfPick() && order.getPickInfo() != null && order.getPickInfo().getPickAddress() != null) {
            params.put("selfPickName", order.getPickInfo().getEnterpriseName().concat("  ").concat(order.getPickInfo().getPickAddress()));
        }
        params.put("orderGoods", JSON.toJSONString(order.getOrderGoodsList(), SerializerFeature.DisableCircularReferenceDetect));
        if (order.getPayTime() != null) {
            params.put("payTime", DateUtil.formatToString(order.getPayTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (order.getUcoinPayTime() != null) {
            params.put("ucoinPayTime", DateUtil.formatToString(order.getUcoinPayTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (order.getPayName() != null) {
            params.put("payName", order.getPayName());
        }

        try {
            PlatformClient.post(url, params);
        } catch (IOException ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    @Override
    public Order getOrder(long orderCode) {
        Order order = orderMapper.selectByOrderCode(orderCode);
        if (order == null) {
            throw new RecordNotFoundException("订单不存在。");
        }
        return order;
    }

    /**
     * @return
     */
    @Transactional
    public boolean beginSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = syncStatusMapper.selectByPrimaryKey(syncGoodsProduct);
        // LOCK TABLES ysh_sync_type WRITE;
        if (syncStatus.getSyncstatus().equals("1")) {
            return true;
        } else {
            //修改的getGoodsItemNoList 为1
            syncStatus.setSyncstatus("1");
            syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
            return false;
        }
        //UNLOCK TABLES;
    }

    /**
     * @return
     */
    public boolean endSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = new SyncStatus();
        syncStatus.setSynctype(syncGoodsProduct);
        syncStatus.setSyncstatus("0");
        syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
        return true;
    }


    /**
     * 同步订单状态（邮宝已付款未发货的订单）
     */
    @Override
    public void updateOrderStatus() throws IOException {
        LOGGER.info("开始同步订单状态!!!!");
        if (!beginSynchronization("syncOrder")) {
            try {
                //1、查询已付款未发货、申请退款、申请退货的订单
                List<Order> orderList = orderMapper.selectByCondition();
                List<OOrder> oOrderList = new ArrayList<>();
                for (Order order : orderList) {
                    OOrder oOrder = new OOrder();
                    oOrder.setOrderCode(order.getOrderCode().toString());
                    oOrder.setOrderStatus(order.getOrderStatus().getCode());
                    oOrderList.add(oOrder);
                }
                List<OOrderAllInfo> orderDetailList =
                        openOrderService.updateOrderStatus(JSON.toJSONString(oOrderList, SerializerFeature.DisableCircularReferenceDetect));
                if (orderDetailList != null) {
                    for (OOrderAllInfo item : orderDetailList) {
                        item.setOrderStatus(OrderStatus.getName(item.getOrderStatus()));

                        if (item.getOrderStatus().equals("DELIVERED")) {//已发货的同步物流信息
                            if (item.getExpress() != null && item.getExpress().size() > 0) {
                                long orderId = 0L;
                                for (ExpressNoVo express : item.getExpress()) {
                                    OrderContainerRelation orderContainerRelation = new OrderContainerRelation();
                                    if (orderId == 0) {
                                        for (Order order : orderList) {
                                            if (order.getOrderCode().toString().equals(item.getOrderCode())) {
                                                orderId = order.getOrderId();
                                                break;
                                            }
                                        }
                                    }
                                    orderContainerRelation.setOrderId(orderId);
                                    orderContainerRelation.setExpressNo(express.getExpressNo());
                                    orderContainerRelation.setExpressName(express.getExpressName());
                                    orderContainerRelationMapper.insertSelective(orderContainerRelation);
                                }
                            }
                        }

                        //2、修改订单状态
                        orderMapper.updateStatusByOrderCode(item);
                    }

                }
            }catch (Exception e) {
                LOGGER.error("同步订单状态错误", e);
            }finally {
                endSynchronization("syncOrder");
                LOGGER.info("结束同步订单状态!!!!");
            }
        }
    }

    @Override
    public void backOrder(long orderCode) {
        Order order = getOrder(orderCode);
        //如果自提非代客，回收提货码
        if (order.getSelfPick() && !order.getIsValet()) {
            deliveryCodeService.recycleDeliveryCode(order.getDeliveryCode());
            orderMapper.updateDeliveryCodeToNullByOrderCode(orderCode);
        }
        Order model = new Order();
        model.setOrderCode(orderCode);
        model.setOrderStatus(OrderStatus.CANCELED);
        model.setOrderCancelTime(new Date());
        model.setDelFlag("1");
        orderMapper.updateByOrderCodeSelective(model);
    }

    /**
     * 同步订单数据（确认收货完成调用）
     *
     * @param orderCode 订单ID
     */
    public void syncConfirmOrder(long orderCode) {
        Order order = getOrder(orderCode);
        String url = "order/confirmOrder.htm";
        Map<String, String> params = new HashMap<>();
        params.put("orderCode", order.getOrderCode().toString());

        try {
            PlatformClient.post(url, params);
        } catch (IOException ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    @Override
    public void confirmOrder(long orderCode) {
        //1、确认收货后修改订单状态
        Order order = new Order();
        order.setOrderCode(orderCode);
        order.setOrderStatus(OrderStatus.RECIEVED);
        order.setGetGoodsTime(new Date());
        orderMapper.updateByOrderCodeSelective(order);
        //2、同步数据到优生活商城
        syncConfirmOrder(orderCode);
    }

    @Override
    public void cancelOrder(long orderCode) {
        Order order = getOrder(orderCode);
        //如果订单已付款或者发货。
        if (!order.getOrderStatus().equals(OrderStatus.SUBMITED)) {
            throw new UserOperationException("订单已付款或者发货，无法取消，请进行申请流程。");
        }
        Order model = new Order();
        model.setOrderCode(orderCode);
        model.setOrderStatus(OrderStatus.CANCELED);
        model.setOrderCancelTime(new Date());
        orderMapper.updateByOrderCodeSelective(model);
    }

    @Override
    public void editStatus(long orderCode, OrderStatus status) {
        Assert.notNull(status);
        Order model = new Order();
        model.setOrderCode(orderCode);
        model.setOrderStatus(status);
        if (status == OrderStatus.CANCELED) {
            model.setDelFlag("1");
        }
        orderMapper.updateByOrderCodeSelective(model);
    }

    @Override
    public void editPrice(long orderCode, BigDecimal price) {
        Assert.notNull(price);
        Order model = new Order();
        model.setOrderCode(orderCode);
        model.setOrderPrice(price);
        orderMapper.updateByOrderCodeSelective(model);
    }

    @Override
    public List<Order> selectByValetSubmit() {
        return orderMapper.selectByValetSubmit();
    }

    @Override
    public OrderRefund selectOrderPrimaryKey(long orderId) {
        return orderMapper.selectOrderPrimaryKey(orderId);
    }

    @Override
    public List<Order> getOrderByCodes(String orderCodes) {
        return orderMapper.getOrderByCodes(Arrays.asList(orderCodes.split(",")));
    }

    @Override
    public List<OrderContainerRelation> queryContainerRalation(Long orderId) {
        return orderContainerRelationMapper.selectListByOrderIds(orderId);
    }

    @Override
    public void updateThirdSendOrderGoods(Long[] relationIds, String expressNo, String expressName) {
        // 更新运货单
        for (int i = 0; i < relationIds.length; i++) {
            OrderContainerRelation relation = new OrderContainerRelation();
            // 设置包裹id
            relation.setRelationId(relationIds[i]);
            relation.setExpressName(expressName);
            // 设置运货单
            relation.setExpressNo(expressNo);
            orderContainerRelationMapper.updateRelation(relation);
        }
    }

    @Override
    public int updateOrderStatus(Long orderId, OrderStatus status, Long thirdId) {
        Order order = new Order();
        // 设置订单id
        order.setOrderId(orderId);
        // 设置订单状态
        order.setOrderStatus(status);
        // 设置发货时间
        if(OrderStatus.DELIVERED.equals(status)){
            order.setSendExpressTime(new Date());
        }
        if (null != thirdId && 0 != thirdId) {
            order.setBusinessId(thirdId);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int orderPriceChange(Long orderId, BigDecimal orderPrice) {
        Order order = new Order();
        // 设置订单id
        order.setOrderId(orderId);
        // 设置订单价格
        order.setOrderPrice(orderPrice);
        // 设置订单其他金额
        order.setOtherAmount(orderPrice);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 导出订单
     * @param model
     * @param maxCatalog
     * @param minCatalog
     * @param username
     * @param createStart
     * @param createEnd
     * @param payStart
     * @param payEnd
     * @param pageable
     * @param response
     * @throws IOException
     */
    @Override
    public void exportOrderExcel(Order model, Long maxCatalog, Long minCatalog, String username, Date createStart, Date createEnd, Date payStart, Date payEnd, Pageable pageable,HttpServletResponse response) throws IOException {
        // 获取订单信息
        List<Order> orderList = orderMapper.selectByModelSelective(model, maxCatalog, minCatalog, username, createStart, createEnd, payStart, payEnd,pageable);
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        String sheetName = "订单表-明细表";
        List<String> heads = Arrays.asList("订单号", "下单用户身份证号", "下单用户姓名", "下单时间",
                "商品名称", "购买数量", "订单状态",
                "收件人姓名", "收件人电话", "收件人地址","快递公司",
                "快递单号", "订单交易金额(邮豆)","订单结算金额(元)","供应商");

        List<List<String>> bodyList = OrderServiceUtil.getOrderBodyList(orderList);
        int ignoreRow = 0;
        wb = ExcelUtilXssf.excelExport(wb, heads, bodyList, 0, ignoreRow, sheetName);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wb", wb);
        String excelName = sheetName+".xlsx";
        try {
            excelName = URLEncoder.encode(excelName, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
        OutputStream ouputStream = response.getOutputStream();
        wb.write(ouputStream);
        ouputStream.flush();
        ouputStream.close();
    }
}
