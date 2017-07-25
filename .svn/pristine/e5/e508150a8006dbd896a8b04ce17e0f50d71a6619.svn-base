package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.ValetOrderService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.order.model.Order;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/30.
 * 代客下单。
 */
@Controller
@SecurityResource(parent = "/web/UserGet", primary = false)
@RequestMapping(value = "/web/api/valet", produces = "application/json;charset=utf-8")
public class ValetOrderAPIController {

    @Resource
    private ValetOrderService valetOrderService;
    @Resource
    private GoodsInfoService goodsInfoService;

    /**
     * 获取代客商城商品信息。
     *
     * @param currentEnterprise 是否本网点 true：显示本网点可用库存大于0的商品；false：显示商城所有集采商品和本网点可用的自购商品
     * @param goodsInfoName     商daike品名称
     * @param lowPrice          价格区间下限
     * @param highPrice         价格区间上限
     * @param page              页码
     * @param size              页大小
     */
    @RequestMapping(value = "/getGoods")
    @ResponseBody
    public String getVALEtGoods(@RequestParam(value = "currentEnterprise", required = false) Boolean currentEnterprise,
                                @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                @RequestParam(value = "lowPrice", required = false) BigDecimal lowPrice,
                                @RequestParam(value = "highPrice", required = false) BigDecimal highPrice,
                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        Page<GoodsManagerResult> goodsManagerResultPage;
        if (currentEnterprise != null && currentEnterprise) {
            goodsManagerResultPage = valetOrderService.getValetGoodsByEnterpriseId(goodsInfoName, lowPrice, highPrice, new Pageable(page, size));
        } else {
            goodsManagerResultPage = valetOrderService.getValetGoods(goodsInfoName, lowPrice, highPrice, new Pageable(page, size));
        }
        return new JsonResponseBean(goodsManagerResultPage).toJson();
    }

    @RequestMapping(value = "/getNetWorkGoods")
    @ResponseBody
    public String getValetGoodsByEnterpriseId(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                              @RequestParam(value = "lowPrice", required = false) BigDecimal lowPrice,
                                              @RequestParam(value = "highPrice", required = false) BigDecimal highPrice,
                                              @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                              @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        Page<GoodsManagerResult> goodsManagerResultPage = valetOrderService.getValetGoodsByEnterpriseId(goodsInfoName, lowPrice, highPrice, new Pageable(page, size));
        return new JsonResponseBean(goodsManagerResultPage).leave("goodsInfoId", "goodsInfoPreferPrice", "goodsInfoName",
                "goodsInfoImgId", "goodsImgId", "images", "imageInName", "imageThumName", "imageBigName", "imageArtworkName", "inventory", "available").toJson();

    }


    /**
     * 代客下单支付。
     *
     * @param customerId 客户ID
     * @param addressId  地址ID
     * @param goodsInfo  订单货品信息：货品ID和数量
     */
    @RequestMapping(value = "/payValetOrder", method = RequestMethod.POST)
    @ResponseBody
    public String payValetOrder(@RequestParam(value = "customerId") long customerId,
                                @RequestParam(value = "addressId", required = false) Long addressId,
                                @RequestParam(value = "goodsInfo") String goodsInfo,
                                @RequestParam(value = "subsidyPrice", required = false) BigDecimal subsidyPrice,
                                @RequestParam(value = "payKey", required = false) String payKey,
                                @RequestParam(value = "subsidyRemark", required = false) String subsidyRemark) {
        if (subsidyPrice == null) {
            subsidyRemark = null;
            payKey = null;
        } else {
            if (subsidyPrice.compareTo(BigDecimal.ZERO) == -1 ||
                    subsidyPrice.compareTo(BigDecimal.ZERO) == 0) {
                throw new UserOperationException("补贴金额错误，必须大于0 ！");
            }
        }
        Map<Long, Integer> goodsInfoMap = Constants.SIMPLE_PARSER.parseJSON(goodsInfo, new TypeToken<Map<Long, Integer>>() {
        });

        Order order = valetOrderService.submmitAndPay(customerId, false, addressId, goodsInfoMap, subsidyRemark, subsidyPrice, payKey);
        return new JsonResponseBean(order).toJson();
    }


    /**
     * 获取当前网点的信息。
     */
    @RequestMapping(value = "/getEnterpriseInfo")
    @ResponseBody
    public String getEnterpriseInfo() {
        EnterpriseInfo info = valetOrderService.getEnterpriseInfo();
        return new JsonResponseBean(info).leave("enterpriseName", "address").toJson();
    }


    /**
     * 获取用户信息。
     *
     * @param idCard 身份证号
     */
    @RequestMapping(value = "/getCustomerInfo")
    @ResponseBody
    public String getCustomerInfo(@RequestParam(value = "idCard") String idCard) {
        ChinapostCustomer info = valetOrderService.getCustomerInfo(idCard);
        return new JsonResponseBean(info).leave("id", "phoneNo", "fullname", "imgUrl", "contactPhone", "totalUcoin", "enterpriseUcoin", "isActive").toJson();
    }

  /*  */

    /**
     * 获取商品信息。
     *
     * @param goodsInfoId 商品ID
     */
    @RequestMapping(value = "/getGoodsInfo")
    @ResponseBody
    public String getGoodsInfo(@RequestParam(value = "goodsInfoId") long goodsInfoId) {
        GoodsInfo info = valetOrderService.getGoodsInfo(goodsInfoId);
        return new JsonResponseBean(info).toJson();
    }


    /**
     * 获取所有客户地址。
     *
     * @param customerId 客户ID
     */
    @RequestMapping(value = "/getAddresses")
    @ResponseBody
    public String getAddresses(@RequestParam(value = "customerId") long customerId) {
        List<CustomerAddress> addresses = valetOrderService.getAddresses(customerId);
        return new JsonResponseBean(addresses).leave("addressId", "addressName", "addressDetail",
                "addressMoblie", "infoProvince", "infoCity", "infoCounty", "infoStreet", "isDefault", "district", "districtName", "province", "provinceName", "city", "cityName").toJson();
    }

    /**
     * 增加收货地址。
     *
     * @param customerId    客户ID
     * @param addressName   收货人姓名
     * @param addressDetail 地址明细
     * @param addressMoblie 联系手机
     * @param isDefault     是否默认 0不是默认，1默认
     * @param infoProvince  省份id
     * @param infoCity      城市id
     * @param infoCounty    乡镇id
     * @param infoStreet    街道id
     * @param addressZip    邮政编码
     * @param addressPhone  电话
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    @ResponseBody
    public String addAddress(@RequestParam(value = "customerId") long customerId,
                             @RequestParam(value = "addressName") String addressName,
                             @RequestParam(value = "addressDetail") String addressDetail,
                             @RequestParam(value = "addressMoblie") String addressMoblie,
                             @RequestParam(value = "isDefault") String isDefault,
                             @RequestParam(value = "infoProvince") String infoProvince,
                             @RequestParam(value = "infoCity") String infoCity,
                             @RequestParam(value = "infoCounty") String infoCounty,
                             @RequestParam(value = "infoStreet") String infoStreet,
                             @RequestParam(value = "addressZip", required = false) String addressZip,
                             @RequestParam(value = "addressPhone") String addressPhone) {
        Assert.hasLength(addressName);
        Assert.hasLength(addressDetail);
        Assert.hasLength(addressMoblie);
        Assert.hasLength(isDefault);
        Assert.hasLength(infoProvince);
        Assert.hasLength(infoCity);
        Assert.hasLength(infoCounty);
        Assert.hasLength(addressZip);
        CustomerAddress address = valetOrderService.addAddress(customerId, addressName, addressDetail, addressMoblie, isDefault, infoProvince, infoCity, infoCounty, infoStreet, addressZip, addressPhone);
        return new JsonResponseBean(address).toJson();
    }

    /**
     * 删除收货地址
     */
    @RequestMapping(value = "deleteShippingAddress")
    @ResponseBody
    public String deleteShippingAddress(@RequestParam(value = "addressId") Long addressId) {
        valetOrderService.deleteAddress(addressId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 是否免验证。
     */
    @RequestMapping(value = "/hasPermit")
    @ResponseBody
    public String hasPermit() {
        boolean hasPermit = valetOrderService.hasPermit();
        return new JsonResponseBean(hasPermit).toJson();
    }


    /**
     * 根据提货码获取订单
     *
     * @param deliveryCode 提货码
     */
    @RequestMapping(value = "/getByDeliveryCode")
    @ResponseBody
    public String getOrderByDeliveryCode(@RequestParam(value = "deliveryCode") String deliveryCode) {
        Assert.hasLength(deliveryCode);
        Order order = valetOrderService.getOrderByDeliveryCode(deliveryCode);
        return new JsonResponseBean(order).toJson();
    }

    /**
     * 根据订单ID提货。
     *
     * @param deliveryCode 订单号
     */
    @RequestMapping(value = "/pickUpOrder")
    @ResponseBody
    public String pickUpOrder(@RequestParam(value = "deliveryCode") String deliveryCode) {
        valetOrderService.pickUpOrder(deliveryCode);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 根据身份证号获取用户余额
     *
     * @param idCard 身份证号
     */
    @RequestMapping(value = "/getCustomerBalance")
    @ResponseBody
    public String getCustomerBalance(@RequestParam(value = "idCard") String idCard) {
        BigDecimal balance = valetOrderService.getCustomerBalance(idCard);
        return new JsonResponseBean(balance).toJson();
    }

}
