package com.ylife.order.util;

import com.ylife.order.model.BackOrderGoods;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderContainerRelation;
import com.ylife.utils.DateUtil;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * BackOrderServiceUtil.
 *
 * @Author henry .
 * @Create 2017-07-07 13:05.
 */
public class BackOrderServiceUtil {
    /**
     * 获取退单需要使用数据的 sonList
     * @param creditOrder
     * @param order
     * @return
     */
    public static List<String> getBackOrderBodyList(CreditOrder creditOrder, Order order){
        List<String> sonList = new ArrayList<String>();
        sonList.add(String.valueOf(creditOrder.getBackOrderCode()));
        sonList.add(String.valueOf(creditOrder.getOrder().getOrderCode()));
        sonList.add(order.getCustomerInfo().getUsername());
        sonList.add(order.getCustomerInfo().getFullName());
        sonList.add(order.getPayTime() == null ? "" : DateUtil.formatToString(order.getPayTime(), "yyyy/MM/dd HH:mm"));
        sonList.add(DateUtil.formatToString(creditOrder.getBackTime(), "yyyy/MM/dd HH:mm"));
        List<BackOrderGoods> backOrderGoodses = creditOrder.getBackOrderGoods();
        String backGoodsName = "";
        Long backAmount = 0L;
        for (BackOrderGoods backOrderGoods : backOrderGoodses) {
            String goodsINfoName = backOrderGoods.getGoodsInfoName() + "(" + backOrderGoods.getGoodsNum() + ")";
            backGoodsName += goodsINfoName + ",";
            backAmount += backOrderGoods.getGoodsNum();
        }
        sonList.add(backGoodsName.substring(0, backGoodsName.length() - 1));
        sonList.add(String.valueOf(backAmount));
        DecimalFormat decimalFormat = new DecimalFormat();
        sonList.add(decimalFormat.format(order.getOrderPrice()));
        sonList.add(decimalFormat.format(creditOrder.getBackPrice()));
        List<OrderContainerRelation> orderContainerRelationList = order.getOrderContainerRelations();
        if (orderContainerRelationList.size() != 0) {
            OrderContainerRelation orderContainerRelation = orderContainerRelationList.get(0);
            sonList.add(orderContainerRelation.getExpressName());
            sonList.add(orderContainerRelation.getExpressNo());
        } else {
            sonList.add("");
            sonList.add("");
        }
        sonList.add(creditOrder.getBackCollectPerson());
        sonList.add(creditOrder.getBackCollectPhone());
        sonList.add(creditOrder.getBusiness().getBusinessName());
        sonList.add(creditOrder.getBackCheck().toString());
        sonList.add(creditOrder.getBackReason().getTag());
        sonList.add(creditOrder.getBackRemark());
        return sonList;
    }
}
