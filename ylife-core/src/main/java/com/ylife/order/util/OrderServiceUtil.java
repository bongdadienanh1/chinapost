package com.ylife.order.util;

import com.ylife.order.model.Order;
import com.ylife.order.model.OrderContainerRelation;
import com.ylife.order.model.OrderGoods;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * OrderServiceUtil.
 *
 * @Author henry .
 * @Create 2017-07-07 12:05.
 */
public class OrderServiceUtil {

    public static String nullOrNotBlank(String arg) {
        if (StringUtil.isBlank(arg))
            return null;
        else
            return arg;
    }

    /**
     * 获取导出订单需要的bodyList
     * @param orderList
     * @return
     */
    public static List<List<String>> getOrderBodyList(List<Order> orderList){
        List<List<String>> bodyList = new ArrayList<List<String>>();
        for (Order order : orderList) {
            List<String> sonList = new ArrayList<String>();
            sonList.add(String.valueOf(order.getOrderCode()));
            sonList.add(order.getCustomerInfo().getUsername());
            sonList.add(order.getCustomerInfo().getFullName());
            Date createTime = order.getCreateTime();
            sonList.add(order.getCreateTime() == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            //Date payTime = order.getPayTime();
            //sonList.add(order.getPayTime() == null ? "" : DateUtil.formatToString(payTime, "yyyy/MM/dd HH:mm"));
            List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
            String goodsNameString = "";
            Long amount = 0L;
            BigDecimal orderSettlePrice = BigDecimal.ZERO;
            for (OrderGoods orderGoods : orderGoodsList) {
                String goodsName = orderGoods.getGoodsInfoName() + "(" + orderGoods.getGoodsInfoNum() + ")";
                goodsNameString += goodsName + ",";
                amount += orderGoods.getGoodsInfoNum();
                orderSettlePrice = orderSettlePrice.add(orderGoods.getGoodsInfoSettlePrice() == null ? BigDecimal.ZERO : orderGoods.getGoodsInfoSettlePrice().multiply(BigDecimal.valueOf(orderGoods.getGoodsInfoNum())));
            }
            goodsNameString = goodsNameString.substring(0, goodsNameString.length() - 1);
            sonList.add(goodsNameString);
            sonList.add(String.valueOf(amount));
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            //String orderPrice = decimalFormat.format(order.getOrderPrice());
            //sonList.add(orderPrice);
            sonList.add(order.getOrderStatus().toString());
            sonList.add(order.getShippingPerson());
            sonList.add(order.getShippingMobile());
            String province = order.getShippingProvince();
            String city = order.getShippingCity();
            String district = order.getShippingCounty();
            String detailAddress = order.getShippingAddress();
            String address = province + city + district + detailAddress;
            sonList.add(address);
            List<OrderContainerRelation> orderContainerRelationList = order.getOrderContainerRelations();
            if (orderContainerRelationList.size() != 0) {
                OrderContainerRelation orderContainerRelation = orderContainerRelationList.get(0);
                sonList.add(orderContainerRelation.getExpressName());
                sonList.add(orderContainerRelation.getExpressNo());
            } else {
                sonList.add("");
                sonList.add("");
            }
            String orderPrice1 = decimalFormat.format(order.getOrderPrice().subtract(order.getSubsidyPrice() == null ? BigDecimal.ZERO : order.getSubsidyPrice()).doubleValue());
            sonList.add(orderPrice1);
            sonList.add(String.valueOf(orderSettlePrice));
            sonList.add(order.getBusiness().getBusinessName());
            bodyList.add(sonList);
        }
        return bodyList;
    }
}
