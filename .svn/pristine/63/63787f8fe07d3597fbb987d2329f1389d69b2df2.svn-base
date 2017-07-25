package com.ylife.chinapost.mobile.controller;

import com.ylife.chinapost.mobile.util.LoginUtil;
import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.shoppingcart.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/6.
 */
@Controller
public class ShoppingCartController {

    @Resource
    private ShoppingCartService shoppingCartService;


    /**
     * 查询购物车
     *
     * @return ModelAndView
     */
    @RequestMapping("myshoppingmcart")
    public String shoppingCart(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> shopMap = shoppingCartService.shopCartMap(request, response,1L);
        request.setAttribute("shopMap",shopMap);
        return "cart";
    }

    /**
     * 添加购物车的控制器
     *
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @param response
     *            response
     * @param distinctId
     *            distinctId
     * @return
     */
    @RequestMapping(value = "/addproducttoshopcar",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addProductToShopCar(HttpServletRequest request, HttpServletResponse response,long goodsNum, long productId,Long distinctId){
        ShoppingCart shoppingCart = new ShoppingCart();
        try {
            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            shoppingCartService.addShoppingCart(shoppingCart, request, response);
            return JsonResponseBean.getSuccessResponse().toJson();
        }catch (UnsupportedEncodingException e) {
            return JsonResponseBean.getErrorResponse(ErrorCode.AUTHENTICATE_FAILURE,"加入购物车失败").toJson();
        }
    }

    /**
     * 立即加入购物车
     *
     * @param goodsNum
     *            购买货品的数量
     * @param productId
     *            货品ID
     * @param request
     *            请求对象
     * @return true 添加成功 false 添加失败
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/addproducttoshopmcarl")
    public String addProductToShopCarL(Long goodsNum, Long productId, HttpServletRequest request, Long distinctId, HttpServletResponse response)
            throws UnsupportedEncodingException {
        ShoppingCart shoppingCart = new ShoppingCart();
        try {

            shoppingCart.setDistinctId(null == distinctId ? 1103L : distinctId);
            shoppingCart.setGoodsInfoId(productId);
            shoppingCart.setGoodsNum(goodsNum);
            Long custId = (Long) request.getSession().getAttribute("customerId");
            if (!LoginUtil.checkLoginStatus(request)) {
                return "redirect:/login?url=productdetail?productId="+ productId;
            }
            shoppingCart.setCustomerId(custId);
            Long shopping = shoppingCartService.selectLastId(shoppingCart, response, request);
            if (shopping == 0) {
                return "redirect:/myshoppingmcart";
            } else {
                return "redirect:/suborder?box="+shopping;
            }
        } finally {
            shoppingCart = null;
        }
    }

    /**
     * 修改购物数量
     *
     * @param shoppingCartId
     * @param num
     * @return int
     */
    @RequestMapping(value="changeshoppingmcartbyid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String changeShoppingCartById(Long shoppingCartId, Long num) {
        shoppingCartService.changeShoppingCartById(shoppingCartId, num);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 删除购物车商品
     *
     * @param request
     * @return int
     */
    @RequestMapping(value="delshoppingcartbyid",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String delShoppingCartById(Long shoppingCartId, Long goodsInfoId, HttpServletRequest request, HttpServletResponse response) {
        shoppingCartService.delShoppingCartById(shoppingCartId, goodsInfoId, request, response);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 移动端查询购物车商品数量
     * 移动端商品详情页使用
     *
     * @author houyichang 2015/10/21
     *
     * */
    @RequestMapping(value="myshoppingmcartNum",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String shoppingCartNum(HttpServletRequest request) {
        return new JsonResponseBean(shoppingCartService.shopCartMapMobile(request,1L)).toJson();
    }
}
