package com.ylife.chinapost.mobile.controller;

import com.ylife.chinapost.mobile.service.PlaceOrderService;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.service.CustomerAddressService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.order.model.OrderAddress;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.shoppingcart.service.ShoppingCartService;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
@Controller
public class OrderController {

    @Resource
    private ShoppingCartService shoppingCartService;

    @Resource
    private CustomerAddressService addressService;

    @Resource
    private PlaceOrderService placeOrderService;

    /**
     * 跳转到订单确认页
     *
     * @param box     购物车id
     * @param enterpriseId 企业ID
     * @param addressName 企业地址
     * @param enterpriseName 企业名称
     * @param unInventoryGoodsInfo 库存提示
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping("/suborder")
    public String subOrder(Long[] box, HttpServletRequest request,Long enterpriseId,String addressName,String enterpriseName,String unInventoryGoodsInfo) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        CustomerAddress customerAddress = null;
        // 查询收货地址
        customerAddress = addressService.queryDefaultAddr(customerId);
        if (null == customerAddress) {
            // 查询收货地址
            customerAddress = addressService.selectByCIdFirst(customerId);
        }
        if (box != null) {
            request.getSession().setAttribute("box", box);
        } else {
            box = (Long[]) request.getSession().getAttribute("box");
        }
        //已提交订单，后退box为null
        if (box==null) {
            return "redirect:/myshoppingmcart";
        }

        Map<String, Object> map = shoppingCartService.newsubOrder(request, box, customerAddress, 0L);
        // 获取购物车列表
        List<ShoppingCart> shoplist = (List<ShoppingCart>) map.get("shoplist");
        if (CollectionUtils.isEmpty(shoplist)) {
            return "redirect:/myshoppingmcart";
        }
        request.setAttribute("map", map);
        request.setAttribute("customer", customerAddress);
        request.setAttribute("orderAddress", GetOrderAddress(customerAddress));
        request.setAttribute("enterpriseId",enterpriseId);
        request.setAttribute("addressName",addressName);
        request.setAttribute("enterpriseName",enterpriseName);
        request.setAttribute("unInventoryGoodsInfo",unInventoryGoodsInfo);
        return "newsuborder";
    }

    /**
     * 根据customerAddress获取订单地址
     * @param customerAddress
     * @return
     */
    private OrderAddress GetOrderAddress(CustomerAddress customerAddress){
        if(customerAddress==null){
            return null;
        }
        OrderAddress orderAddressNew = new OrderAddress();
        orderAddressNew.setAddressId(customerAddress.getAddressId());
        orderAddressNew.setAddressDetailInfo(customerAddress.getAddressDetail());
        orderAddressNew.setAddressName(customerAddress.getAddressName());
        orderAddressNew.setAddressPhone(customerAddress.getAddressMoblie());
        String addressDetail="";
        if (customerAddress.getProvince() != null) {
            addressDetail += customerAddress.getProvince().getProvinceName();
        }
        if (customerAddress.getCity() != null) {
            addressDetail += customerAddress.getCity().getCityName();
        }
        if (customerAddress.getDistrict() != null) {
            addressDetail += customerAddress.getDistrict().getDistrictName();
        }
        orderAddressNew.setAddressDetail(addressDetail);

        return orderAddressNew;
    }

    /**
     * 提交订单
     *
     * @param addressId
     * @param shoppingCartId
     * @param selfPick
     * @param remark
     * @param enterpriseId
     * @param paykey
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/submitorder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitOrder(HttpServletRequest request,Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId, String paykey) {
        request.getSession().setAttribute("box", null);
        // 保存订单并付款
        return placeOrderService.submitOrderAndPay(addressId,shoppingCartId,selfPick,remark,enterpriseId,paykey);
    }

    /**
     * 跳转到付款成功页面
     * @param request
     * @param deliveryCode 提货码
     * @return
     */
    @RequestMapping(value = "/paysuccess")
    public String paysuccess(HttpServletRequest request,String deliveryCode) {
        if(!StringUtil.isBlank(deliveryCode)) {
            request.setAttribute("deliveryCode", deliveryCode);
        }
        return "pay_success_new";
    }

    /**
     * 跳转到支付失败页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/payerror")
    public String payerror(HttpServletRequest request) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login";
        }
        return "pay_error";
    }

    /**
     * 确认收货
     * @param orderCode 订单号
     * @return
     */
    @RequestMapping(value="/comfirmofgoods")
    public String comfirmofgoods(long orderCode){
        placeOrderService.confirmOrder(orderCode);
        return "redirect:/myorder";
    }

    /**
     * 取消订单
     * @param orderCode 订单号
     * @return
     */
    @RequestMapping(value="/cancelOrder")
    public String cancelOrder(long orderCode){
        placeOrderService.cancelOrder(orderCode);
        return "redirect:/myorder";
    }

    /**
     * 支付
     * @param orderCode 订单号
     * @param paykey    支付密码
     * @return
     */
    @RequestMapping(value="/payOrder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String payOrder(long orderCode,String paykey) {
        placeOrderService.payOrder(new long[]{orderCode}, paykey);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 查看自提码
     * @param orderCode
     * @return
     */
    @RequestMapping(value = "/getDeliveryCode", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getDeliveryCode(long orderCode){
        return new JsonResponseBean(placeOrderService.getOrder(orderCode)).toJson();
    }

    /**
     * 获取用户使用过的自提点(不包含顶级帐号)
     * @return
     */
    @RequestMapping(value = "/getSuggestEnterprise", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getSuggestEnterprise() {
        return new JsonResponseBean(placeOrderService.getSuggestEnterprise()).toJson();
    }

    /**
     * 获取指定地区的自提点(不包含顶级帐号)
     * @return
     */
    @RequestMapping(value = "/getEnterpriseByDistrictId", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getEnterpriseByDistrictId(long districtId) {
        return new JsonResponseBean(placeOrderService.getEnterpriseByDistrictId(districtId)).toJson();
    }

}
