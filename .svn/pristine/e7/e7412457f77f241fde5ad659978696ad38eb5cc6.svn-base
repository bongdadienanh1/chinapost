/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.shoppingcart.service.impl;


import com.ylife.customer.model.AddressUtil;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.service.DefaultAddressService;
import com.ylife.customer.service.DistrictService;
import com.ylife.data.page.PageBean;
import com.ylife.freighttemplate.mapper.FreightExpressMapper;
import com.ylife.freighttemplate.mapper.FreightTemplateMapper;
import com.ylife.freighttemplate.model.FreightExpress;
import com.ylife.freighttemplate.model.FreightExpressAll;
import com.ylife.freighttemplate.model.FreightTemplate;
import com.ylife.goods.mapper.GoodsProductMapper;
import com.ylife.goods.mapper.ProductWareMapper;
import com.ylife.goods.model.GoodsDetailBean;
import com.ylife.goods.model.GoodsProduct;
import com.ylife.goods.model.GoodsProductVo;
import com.ylife.goods.model.ProductWare;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.goods.service.ProductWareService;
import com.ylife.order.mapper.MarketingMapper;
import com.ylife.order.mapper.OrderUtil;
import com.ylife.order.model.Marketing;
import com.ylife.order.service.MarketingService;
import com.ylife.order.service.OrderService;
import com.ylife.shoppingcart.mapper.ShoppingCartMapper;
import com.ylife.shoppingcart.model.*;
import com.ylife.shoppingcart.service.ShoppingCartService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author ggn
 */
@Service("ShoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private static final String CUSTOMERID = "customerId";
    private static final String NPSTORE_MID = "_npstore_mId";
    private static final String NPSTORE_SHOPCAR = "_npstore_shopcar";
    private static final String CODE001 = "110012";
    private static final String NPSTORE_SHOPSTATUS = "_npstore_shopstatus";
    private static final String DISTINCTID = "distinctId";
    private static final String MARKETINGID = "marketingId";
    private static final String GOODSID = "goodsId";
    private static final String STOCK = "stock";
    private static final String BOSSSUMPRICE = "bossSumPrice";
    private static final String SUMPRICE = "sumPrice";

    /**
     * 110012
     */
    private static final String PROINFO = "110012";
    /**
     * thirds
     */
    private static final String THIRDS = "thirds";
    /**
     * marketinglist
     */
    private static final String MARKETINGLIST = "marketinglist";
    /**
     * shoplist
     */
    private static final String SHOPLIST = "shoplist";

    @Resource
    private GoodsProductService goodsProductService;

    @Resource
    private FreightTemplateMapper freightTemplateMapper;

    @Resource
    private GoodsProductMapper goodsProductMapper;

    // 货品库存表
    @Resource
    private ProductWareMapper productWareMapper;
    private String num = "";

    @Resource
    private ProductWareService productWareService;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private OrderService orderser;

    @Resource
    private DefaultAddressService addressService;

    @Resource
    private DistrictService districtService;

    @Resource
    private FreightExpressMapper freightExpressMapper;

    @Resource
    private MarketingService marketService;

    @Resource
    private MarketingMapper marketingMapper;
    /**
     * 删除购物车商品
     *
     * @param shoppingCartId
     * @param goodsInfoId
     * @param shoppingCartId
     * @return int
     */
    @Override
    public int delShoppingCartById(Long shoppingCartId, Long goodsInfoId, HttpServletRequest request, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (customerId != null) {
            return shoppingCartMapper.delShoppingCartById(shoppingCartId);
        } else {
            // 调用cookie删除接
            try {
                delCookShopCar(goodsInfoId, request, response);
            } catch (UnsupportedEncodingException e) {
//                Customer cust = (Customer) request.getSession().getAttribute("cust");
//                OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            }
            return 1;
        }
    }


    /**
     * 修改购买数量
     *
     * @param shoppingCartId
     * @param num
     * @return int
     */
    @Override
    public int changeShoppingCartById(Long shoppingCartId, Long num) {
        Long numNew = num;
        ShoppingCart sc = new ShoppingCart();
        sc.setShoppingCartId(shoppingCartId);
        if (numNew != null && numNew == 0L) {
            numNew = 1L;
        }
        sc.setGoodsNum(numNew);
        return shoppingCartMapper.changeShoppingCartById(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     *            购物车id
     * @param marketingId
     *            单品优惠分组id
     * @param marketingActivityId
     *            活动优惠分组id
     * @return int
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId) {
        Long marketingIdNew = marketingId;
        Long marketingActivityIdNew =  marketingActivityId;
        ShoppingCart sc = new ShoppingCart();

        sc.setShoppingCartId(shoppingCartId);
        // 如果单品优惠id为0，则为不选中单品优惠
        if (marketingIdNew == 0) {
            marketingIdNew = null;
        }
        sc.setMarketingId(marketingIdNew);
        // 如果活动优惠id为0，则为不选活动优惠
        if (marketingActivityIdNew == 0) {
            marketingActivityIdNew = null;
        }
        sc.setMarketingActivityId(marketingActivityIdNew);
        return shoppingCartMapper.changeShoppingCartMarket(sc);
    }

    /**
     * 修改优惠
     *
     * @param shoppingCartId
     *            购物车id
     * @param marketingId
     *            单品优惠分组id
     * @param marketingActivityId
     *            活动优惠分组id
     * @return int
     */
    @Override
    public int changeShoppingCartMarket(Long shoppingCartId, Long marketingId, Long marketingActivityId, HttpServletRequest request, HttpServletResponse response) {
        Long marketingIdNew = marketingId;
        Long marketingActivityIdNew = marketingActivityId;
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);

        // 判断用户是否登录
        if (customerId != null) {
            ShoppingCart sc = new ShoppingCart();

            sc.setShoppingCartId(shoppingCartId);
            // 如果单品优惠id为0，则为不选中单品优惠
            if (marketingIdNew!=null&&marketingIdNew == 0) {
                marketingIdNew = null;
            }
            sc.setMarketingId(marketingIdNew);
            // 如果活动优惠id为0，则为不选活动优惠
            if (marketingActivityIdNew!=null&&marketingActivityIdNew == 0) {
                marketingActivityIdNew = null;
            }
            sc.setMarketingActivityId(marketingActivityIdNew);
            return shoppingCartMapper.changeShoppingCartMarket(sc);
        } else {
            Cookie[] cookies = request.getCookies();

            StringBuilder newMid = new StringBuilder();

            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                            String[] mIds = cookie.getValue().split("-");
                            // 取得所有cookie
                            for (int j = 0; j < mIds.length; j++) {
                                String[] mid = mIds[j].split("e");
                                // 判断是否该商品
                                if (mid[0] != null) {
                                    if (mid[0].equals(shoppingCartId.toString())) {
                                        newMid.append(shoppingCartId);
                                        newMid.append("e");
                                        newMid.append(marketingIdNew);
                                        newMid.append("e");
                                        newMid.append(marketingActivityIdNew);
                                        newMid.append("e");
                                        newMid.append("1");
                                        newMid.append("-");
                                    } else {
                                        newMid.append(mIds[j]);
                                        newMid.append("-");
                                    }
                                }
                        }
                    }
                }
                Cookie cookie = new Cookie(NPSTORE_MID, newMid.toString());
                cookie.setMaxAge(15 * 24 * 3600);
                cookie.setPath("/");
                response.addCookie(cookie);
            }
            return 0;
        }

    }

    /**
     * 查询购物车购买的商品信息
     *
     * @param request
     * @param box
     * @return List
     */
    @Override
    public List<ShoppingCart> searchByProduct(HttpServletRequest request, Long[] box) {
        List<Long> list = new ArrayList<Long>();
        if (box != null && box.length != 0) {
            for (Long bo : box) {
                list.add(bo);
            }
        }
        List<ShoppingCart> shoplist = shoppingCartMapper.shoppingCartListByIds(list);
        if (shoplist != null && !shoplist.isEmpty()) {

            for (int i = 0; i < shoplist.size(); i++) {
                // 判断是否是套装
                if (shoplist.get(i).getFitId() == null) {
                    // 如果是套装，设置商品
                    shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId(),null));
                }
            }

        }
        return shoplist;

    }

    /**
     * 添加购物车
     *
     * @param shoppingCart
     * @return int
     * @throws UnsupportedEncodingException
     */
    @Override
    @Transactional
    public int addShoppingCart(ShoppingCart shoppingCart, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        if (null != custId) {
            Map<String,Object> map = new HashMap<>();
            map.put("customerId",custId);
            int sum = shoppingCartMapper.selectSumByCustomerId(map);
            if (sum >= 20) {
                return -1;
            }
            shoppingCart.setCustomerId(custId);
            shoppingCart.setDelFlag("0");
            shoppingCart.setShoppingCartTime(new Date());
            int count = shoppingCartMapper.selectCountByReady(shoppingCart);

            if (count == 0) {
                return shoppingCartMapper.addShoppingCart(shoppingCart);
            } else {
                ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
                // 限购
                if (sc.getGoodsNum() == 99) {
                    return 0;
                }
                return shoppingCartMapper.updateShoppingCart(shoppingCart);
            }
        } else {
            num = "";
            Cookie[] cookies = request.getCookies();
            String oldCar = "";
            String mId = "";
            Cookie cook;
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName())) {
                        oldCar = URLDecoder.decode(cookie.getValue(),"utf-8");
                        if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-") != -1) {
                            num = oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-")
                                    + oldCar.substring(oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-"), oldCar.length() - 1).indexOf("&"));
                            num = num.substring(num.indexOf("-") + 1, num.length());
                            oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e", "");
                            if (oldCar.indexOf("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId()) != -1) {
                                oldCar = oldCar.replace("," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId(), "");

                            }
                        }
                    }
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                            String[] mIds = cookie.getValue().split("-");
                            // 取得所有cookie
                            for (int j = 0; j < mIds.length; j++) {
                                String[] mid = mIds[j].split("e");
                                // 判断是否该商品
                                if (mid[0] != null && "".equals(mIds[0]) && !mid[0].equals(shoppingCart.getGoodsInfoId().toString())) {
                                        mId = cookie.getValue();
                                }
                        }
                    }

                }
            }
            if (!"".equals(num)) {
                num = String.valueOf(Long.parseLong(num) + shoppingCart.getGoodsNum());
            } else {
                num = String.valueOf(shoppingCart.getGoodsNum());
            }
            oldCar += "," + shoppingCart.getGoodsInfoId() + "-" + num + "&" + shoppingCart.getDistinctId() + "e";
            GoodsDetailBean goodsDetailBean = null;
            if (shoppingCart.getFitId() == null) {
                goodsDetailBean = goodsProductService.queryDetailBeanByProductId(shoppingCart.getGoodsInfoId(), Long.parseLong("0"),null);

            }
            cook = new Cookie(NPSTORE_SHOPCAR, URLEncoder.encode(oldCar, "utf-8"));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTORE_MID, mId);
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            return 1;
        }
    }

    /**
     * 加载cookie中的购物车信息
     *
     * @return 加载好的列表
     * @throws UnsupportedEncodingException
     */
    public List<ShopCarUtil> loadCookShopCar(HttpServletRequest request) throws UnsupportedEncodingException {
        List<ShopCarUtil> list = new ArrayList<ShopCarUtil>();
        Cookie[] cookies = request.getCookies();
        String oldCar = "";
        String[] cars = null;
        String[] car = null;
        String[] car2 = null;
        ShopCarUtil carUtil = null;
        boolean checkExists = false;
        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {

                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                            oldCar = URLDecoder.decode(cookie.getValue(), "utf-8");
                            oldCar = oldCar.substring(1, oldCar.length());
                            oldCar = oldCar.substring(0, oldCar.length() - 1);
                            cars = oldCar.split("e,");
                            if (null != cars && cars.length > 0) {
                                for (int i = 0; i < cars.length; i++) {
                                    car = cars[i].split("-");
                                    carUtil = new ShopCarUtil();

                                    /* 如果符合套装的规则,说明是套装,否则就是货品 */
                                    if (car[0].length() > 6 && CODE001.equals(car[0].substring(0, 6))) {
                                        carUtil.setFitId(Long.parseLong(car[0].substring(6, car[0].length())));
                                        carUtil.setProductId(Long.parseLong(car[0]));
                                    } else {
                                        carUtil.setProductId(Long.parseLong(car[0]));
                                        for (Cookie cook : cookies) {
                                            // 设置优惠
                                            if (cook != null && NPSTORE_MID.equals(cook.getName()) && cook.getValue() != null && !"".equals(cook.getValue())) {
                                                    String[] mIds = cook.getValue().split("-");
                                                    // 取得所有cookie
                                                    for (int j = 0; j < mIds.length; j++) {
                                                        String[] mid = mIds[j].split("e");
                                                        // 判断是否该商品
                                                        if (mid[0] != null && car[0].equals(mid[0])) {
                                                            if(mid[1]!=null&&!"null".equals(mid[1])){
                                                                carUtil.setMarketId(Long.parseLong(mid[1]));
                                                            }

                                                                carUtil.setMarketActiveId(Long.parseLong(mid[2]));
                                                                carUtil.setStatus(Long.parseLong(mid[3]));
                                                        }
                                                }
                                        }
                                    }
                                    car2 = car[1].split("&");
                                    carUtil.setGoodsNum(Integer.parseInt(car2[0]));
                                    carUtil.setDistinctId(Long.parseLong(car2[1]));
                                    for (int j = 0; j < list.size(); j++) {
                                        if (list.get(j).getProductId().equals(carUtil.getProductId())) {
                                            checkExists = true;
                                        }
                                    }
                                    if (!checkExists) {
                                        list.add(carUtil);
                                        checkExists = false;
                                    }
                                }
                            }
                        }

                    }

                }
            }
            return list;
        } finally {
            list = null;
            cookies = null;
            oldCar = null;
            cars = null;
            car = null;
        }
    }

    /**
     * 删除cookie中的购物车数据
     *
     * @param request
     *            请求对象
     * @param response
     *            相应对象
     * @return 删除的数量
     * @throws UnsupportedEncodingException
     */
    public int delCookShopCar(Long productId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Integer count = 0;
        Cookie[] cookies = request.getCookies();
        String oldCar = "";
        String[] cars = null;
        String[] car = null;
        Cookie cook;
        String newMid = "";
        StringBuilder bufOldCar = new StringBuilder();
        StringBuilder bufNewMid = new StringBuilder();

        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && NPSTORE_SHOPCAR.equals(cookie.getName())) {
                        oldCar = URLDecoder.decode(cookie.getValue(), "utf-8");
                        if (oldCar.indexOf("," + productId + "-") != -1) {
                            oldCar = oldCar.substring(1, oldCar.length());
                            oldCar = oldCar.substring(0, oldCar.length() - 1);
                            cars = oldCar.split("e,");
                            oldCar = "";
                            for (int j = 0; j < cars.length; j++) {
                                car = cars[j].split("-");
                                if (!car[0].equals(productId.toString())) {
                                    bufOldCar.append(oldCar);
                                    bufOldCar.append(",");
                                    bufOldCar.append(car[0]);
                                    bufOldCar.append("-");
                                    bufOldCar.append(car[1]);
                                    bufOldCar.append("e");
                                    oldCar += bufOldCar.toString();
                                }
                            }
                        }
                    }
                    if (cookie != null && NPSTORE_MID.equals(cookie.getName()) && cookie.getValue() != null && !"".equals(cookie.getValue())) {
                            String[] mIds = cookie.getValue().split("-");
                            // 取得所有cookie
                            for (int j = 0; j < mIds.length; j++) {
                                String[] mid = mIds[j].split("e");
                                // 判断是否该商品
                                if (mid[0] != null && !mid[0].equals(productId.toString())) {
                                        bufNewMid.append(mIds[j]);
                                        bufNewMid.append("-");
                                        newMid += bufNewMid.toString();
                                }
                        }
                    }

                }
            }
            cook = new Cookie(NPSTORE_SHOPCAR, URLEncoder.encode(oldCar, "utf-8"));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath("/");
            response.addCookie(cook);
            Cookie cookie = new Cookie(NPSTORE_MID, URLEncoder.encode(newMid, "utf-8"));
            cookie.setMaxAge(15 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            return count;
        } finally {
            cook = null;
            cars = null;
            car = null;
            cookies = null;
            oldCar = null;
        }
    }

    /*
     * 
     * @see com.ysh.site.shoppingcart.service.ShoppingCartService#
     * deleteShoppingCartByIds(javax.servlet.http .HttpServletRequest,
     * java.lang.Long[])
     */
    @Override
    public int deleteShoppingCartByIds(Long[] shoppingCartId) {
        List<Long> list = new ArrayList<Long>();
        if (shoppingCartId.length != 0) {
            for (Long bo : shoppingCartId) {
                list.add(bo);
            }
        }
        return shoppingCartMapper.deleteShoppingCartByIds(list);
    }

    /**
     * 从cook添加到购物车
     *
     * @param request
     * @return int
     */
    @Override
    public int loadCoodeShopping(HttpServletRequest request) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        List<ShopCarUtil> list = null;
        try {
            list = loadCookShopCar(request);
        } catch (UnsupportedEncodingException e) {
        }
        if (list != null && !list.isEmpty()) {
            for (ShopCarUtil su : list) {
                ShoppingCart shoppingCart = new ShoppingCart();
                if (su.getFitId() == null) {
                    shoppingCart.setGoodsInfoId(su.getProductId());
                } else {
                    // 设置套装商品id
                    shoppingCart.setGoodsInfoId(Long.parseLong(CODE001 + su.getFitId()));
                    // 设置套装id
                    shoppingCart.setFitId(su.getFitId());
                }
                shoppingCart.setCustomerId(custId);
                shoppingCart.setDelFlag("0");
                shoppingCart.setShoppingCartTime(new Date());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                shoppingCart.setMarketingId(su.getMarketId());
                shoppingCart.setMarketingActivityId(su.getMarketActiveId());
                shoppingCart.setGoodsNum(Long.valueOf(su.getGoodsNum()));
                int count = shoppingCartMapper.selectCountByReady(shoppingCart);
                if (count == 0) {
                    shoppingCartMapper.addShoppingCart(shoppingCart);
                }
            }

        }

        return 1;
    }

    /**
     * 修改要选中的订单优惠
     *
     * @param cart
     * @return 结果
     */
    @Override
    public int changeShoppingCartOrderMarket(ShoppingCart cart) {
        shoppingCartMapper.changeShoppingCartOrderMarket(cart);
        return 0;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Override
    /**
     * 查询购物车内容
     * @return
     */
    public PageBean selectShoppingCart(HttpServletRequest request, ShoppingCartWareUtil cartWareUtil, PageBean pb, HttpServletResponse response) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 如果已经登录
        if (customerId != null) {
            pb.setPageSize(20);
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, customerId);
            Integer totalCount = shoppingCartMapper.shoppingCartCount(paramMap);
            pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));
            paramMap.put("startRowNum", pb.getStartRowNum());
            paramMap.put("endRowNum", pb.getEndRowNum());
            List<Object> shoplist = shoppingCartMapper.shoppingCart(paramMap);
            if (shoplist != null && !shoplist.isEmpty()) {
                for (int i = 0; i < shoplist.size(); i++) {
                    try {
                        try {
                            // 进入此处标示该购物车为商品，执行商品方法
                            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    cartWareUtil.getDistrictId(),null);
                            if (goodsDetailBean == null) {
                                continue;
                            }
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                        } catch (Exception e) {
                            shoppingCartMapper.delShoppingCartById(((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                        }
                    } catch (Exception e) {
                        // 如果商品已经从数据库中删除，则把该商品从购物车内删除
                        shoppingCartMapper.delShoppingCartById(((ShoppingCart) shoplist.get(i)).getShoppingCartId());
                    }
                }
            }
            pb.setList(shoplist);

        } else {
            // 如果未登录状态获取购物车
            try {
                List<ShopCarUtil> list = loadCookShopCar(request);
                if (list != null && !list.isEmpty()) {
                    pb.setPageSize(5);

                    Integer totalCount = 1;// list.size();

                    pb.setRows(Integer.parseInt(totalCount == null ? 0 + "" : totalCount + ""));

                    List<Object> shoplist = new ArrayList<Object>();
                    for (int i = 0; i < list.size(); i++) {// list.size();
                        ShoppingCart sc = new ShoppingCart();
                        sc.setGoodsInfoId(list.get(i).getProductId());
                        sc.setMarketingId(list.get(i).getMarketId());
                        sc.setMarketingActivityId(list.get(i).getMarketActiveId());
                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setDistinctId(list.get(i).getDistinctId());
                        sc.setShoppingCartId(Long.valueOf(i));
                        shoplist.add(sc);
                    }

                    if (shoplist != null && !shoplist.isEmpty()) {
                        for (int i = 0; i < shoplist.size(); i++) {
                            Object sessionStatus = request.getSession().getAttribute(NPSTORE_SHOPSTATUS);
                            boolean bool = true;
                            if (sessionStatus != null) {

                                String[] status = (sessionStatus.toString()).split("-");
                                for (String str : status) {
                                    String[] shoppingStatus = str.split("e");
                                    if (shoppingStatus[1].equals(((ShoppingCart) shoplist.get(i)).getShoppingCartId().toString())) {
                                        ((ShoppingCart) shoplist.get(i)).setShoppingStatus(shoppingStatus[0]);
                                        bool = false;
                                    }
                                }
                            } else {
                                sessionStatus = "1" + "e" + ((ShoppingCart) shoplist.get(i)).getShoppingCartId() + "-";
                                ((ShoppingCart) shoplist.get(i)).setShoppingStatus("1");
                            }

                            if (bool) {
                                sessionStatus = "1" + "e" + ((ShoppingCart) shoplist.get(i)).getShoppingCartId() + "-" + sessionStatus;
                                ((ShoppingCart) shoplist.get(i)).setShoppingStatus("1");
                            }
                            request.getSession().setAttribute(NPSTORE_SHOPSTATUS, sessionStatus);
                            GoodsDetailBean goodsDetailBean = goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    cartWareUtil.getDistrictId(),null);
                            if (goodsDetailBean == null) {
                                continue;
                            }
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsDetailBean);
                        }

                    }
                    pb.setList(shoplist);

                }

            } catch (UnsupportedEncodingException e) {
            }
        }
        return pb;
    }

    /**
     * 获取商品库存
     */
    public void getGoodsStock(ShoppingCartWareUtil cartWareUtil, List<Object> shoplist, int i) {
        // 判断是否存在
        // 查询库存
        ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(
                ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().getGoodsInfoId(), cartWareUtil.getDistrictId());

        if (productWare != null) {
            // 设置商品库存
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(productWare.getWareStock());
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
        } else {
            // 如果没有，则设置库存为0
            ((ShoppingCart) shoplist.get(i)).getGoodsDetailBean().getProductVo().setGoodsInfoStock(0L);
        }
    }

    /**
     * 查询省名称和地区id
     *
     * @param request
     * @return
     */
    @Override
    public ShoppingCartWareUtil selectPNameByParam(HttpServletRequest request) {
        Long dId = null;
        // 获取地区id
        if (request.getSession().getAttribute(DISTINCTID) != null && !"".equals(request.getSession().getAttribute(DISTINCTID))) {
            String obj = request.getSession().getAttribute(DISTINCTID).toString();
            dId = Long.parseLong(obj);
        }
        ShoppingCartWareUtil wareUtil = new ShoppingCartWareUtil();

        // 如果地区id为空，则设置为建邺区
        if (dId == null) {
            dId = addressService.getDefaultIdService();
            if (dId == null) {
                dId = 1103L;
                wareUtil.setProvinceName("江苏");
                wareUtil.setCityName("南京");
                wareUtil.setDistinctName("建邺区");
            } else {
                AddressUtil addressUtil = districtService.queryAddressNameByDistrictId(dId);
                wareUtil.setProvinceName(addressUtil.getProvinceName());
                wareUtil.setCityName(addressUtil.getCityName());
                wareUtil.setDistinctName(addressUtil.getDistrictName());
            }
            wareUtil.setDistrictId(dId);
        } else {
            wareUtil.setDistrictId(dId);
            // 省
            String provinceName = request.getSession().getAttribute("chProvince").toString();
            wareUtil.setProvinceName(provinceName);

            // 市
            String chCity = request.getSession().getAttribute("chCity").toString();
            wareUtil.setCityName(chCity);
            // 地址
            String chDistinct = request.getSession().getAttribute("chDistinct").toString();
            wareUtil.setDistinctName(chDistinct);
        }

        // 区id
        return wareUtil;
    }

    /**
     * 更改商品选中状态
     *
     * @param shoppingId
     *            购物车id
     * @param status
     *            要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatus(Long shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        // 获取当前选中状态的session
        Object obj = request.getSession().getAttribute(NPSTORE_SHOPSTATUS);
        String newStr = "";
        StringBuilder buf = new StringBuilder();

        // 判断session是否有值
        if (obj != null) {
            // 获取所有的购物车状态
            String[] strs = (obj.toString()).split("-");
            for (String str : strs) {
                // 根据标示分割字符，获取这个购物车商品内的id和状态;
                String[] shopStatus = str.split("e");
                if (shopStatus[1].equals(shoppingId.toString())) {
                    buf.append(status);
                    buf.append("e");
                    buf.append(shoppingId);
                    buf.append("-");
                    buf.append(newStr);
                } else {
                    buf.append(str);
                    buf.append("-");
                }
                newStr += buf.toString();
            }
        }
        request.getSession().setAttribute(NPSTORE_SHOPSTATUS, newStr);

        return status;
    }

    /**
     * 批量更改商品选中状态
     *
     * @param shoppingId
     *            购物车id
     * @param status
     *            要修改的状态
     * @param request
     * @param response
     * @return
     */
    @Override
    public String changeShopStatusByParam(Long[] shoppingId, String status, HttpServletRequest request, HttpServletResponse response) {
        String newStr = "";
        StringBuilder buf = new StringBuilder();
        for (Long id : shoppingId) {
            buf.append(status);
            buf.append("e");
            buf.append(id);
            buf.append("-");
            newStr += buf.toString();
        }
        request.getSession().setAttribute(NPSTORE_SHOPSTATUS, newStr);
        return status;
    }


    /**
     * 得到各个商家的金额
     *
     * @param businessId
     * @param shopdata
     * @return
     */
    @Override
    public Map<String, Object> getEveryThirdPriceMap(Long businessId, List<ShoppingCart> shopdata, Long distinctId) {
        Map<String, Object> paramMap = new HashMap<>();
        // 1表示不同地区存在库存 0则表示库存不足直接跳到购物车
        paramMap.put(STOCK, "1");
        List<ShoppingCart> shoplist = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(shopdata)) {

            for (int i = 0; i < shopdata.size(); i++) {
                if (businessId.equals(shopdata.get(i).getThirdId())) {
                    shoplist.add(shopdata.get(i));
                }
            }
        }
        // 交易总金额
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        // 原始总金额
        BigDecimal sumOldPrice = BigDecimal.valueOf(0);
        // 优惠金额
        BigDecimal prePrice = BigDecimal.valueOf(0);
        // 中间变量
        BigDecimal flag = BigDecimal.ZERO;
        // boss总金额
        BigDecimal bossSumPrice = BigDecimal.ZERO;
        ProductWare productWare;
        Map<String, Object> para = new HashMap<>();
        if (CollectionUtils.isNotEmpty(shoplist)) {
            Long goodssum = 0L;
            BigDecimal goodsprice = BigDecimal.ZERO;
            BigDecimal totalprice = BigDecimal.ZERO;

            for (int v = 0; v < shoplist.size(); v++) {

                if (shoplist.get(v).getFitId() == null) {

                    // 货品价格
                    goodsprice = shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice();
                    //存在已下架的货品
                  if("0".equals(shoplist.get(v).getGoodsDetailBean().getProductVo().getGoodsInfoAdded())) {
                      paramMap.put(STOCK, "0");
                  }
                    // 是否抹掉分角
                    String discountFlag = "";
                    DecimalFormat myformat = null;
                    // 抹掉分
                    if ("1".equals(discountFlag)) {
                        myformat = new DecimalFormat("0.0");
                    }else if ("2".equals(discountFlag)) {
                        myformat = new DecimalFormat("0");
                    } else {
                        myformat = new DecimalFormat("0.00");
                    }
                    goodsprice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsprice)));
                    // 处理货品的价格()
                    shoplist.get(v).getGoodsDetailBean().getProductVo().setGoodsInfoPreferPrice(goodsprice);
                    // 货品购买件数
                    goodssum = shoplist.get(v).getGoodsNum();
                    // 计算boss价格页面计算用
                    if (shoplist.get(v).getThirdId() == 0) {
                        if(shoplist.get(v).getSubjectId()!=null && shoplist.get(v).getSubjectId()>0) {
                            bossSumPrice = bossSumPrice.add(BigDecimal.valueOf(Double.valueOf(myformat.format(shoplist.get(v).getSubjectGoodsPrice()))).multiply(BigDecimal.valueOf(goodssum)));
                        }else{
                            bossSumPrice = bossSumPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                        }
                    }
                    // 计算原始总金额
                    if(shoplist.get(v).getSubjectId()!=null && shoplist.get(v).getSubjectId()>0){
                        sumOldPrice = sumOldPrice.add(BigDecimal.valueOf(Double.valueOf(myformat.format(shoplist.get(v).getSubjectGoodsPrice()))).multiply(BigDecimal.valueOf(goodssum)));
                        flag = flag.add(BigDecimal.valueOf(Double.valueOf(myformat.format(shoplist.get(v).getSubjectGoodsPrice()))).multiply(BigDecimal.valueOf(goodssum)));
                    }else {
                        sumOldPrice = sumOldPrice.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                        flag = flag.add(goodsprice.multiply(BigDecimal.valueOf(goodssum)));
                    }
                }

            }
            List<ShoppingCart> cartList = null;
            // 根据第三方id分组得到新的购物车集合
            cartList = new ArrayList<>();
            for (ShoppingCart sc : shoplist) {
                if (sc.getFitId() == null) {
                    cartList.add(sc);

                }
            }
        }
        paramMap.put("sumOldPrice", sumOldPrice);
        paramMap.put(BOSSSUMPRICE, bossSumPrice);
        paramMap.put(SUMPRICE, flag);
        return paramMap;
    }

    /**
     * 新购物车结算
     *
     * @param request
     * @param box
     * @return
     */
    @Override
    public Map<String, Object> newsubOrder(HttpServletRequest request, Long[] box, CustomerAddress customerAddress,Long typeId) {
        if(customerAddress==null || customerAddress.getInfoCounty()==null || customerAddress.getInfoCity() == null){
            customerAddress=new CustomerAddress();
            customerAddress.setInfoCounty("1103");
            customerAddress.setInfoCity("74");
        }
        Map<String, Object> cartMap = new HashMap<String, Object>();
        // 查询所有购物车商品
        List<ShoppingCart> shoplist;
        List<StoreTemp> thirdstore = new LinkedList<>();
        //购物车信息
        shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList(box));
        ShoppingCartWareUtil wareUtil= new ShoppingCartWareUtil();
        wareUtil.setDistrictId(Long.parseLong(customerAddress.getInfoCounty()));
        //查询购物车内商品的详细信息
        selectShoppingCartDetail(shoplist);
        cartMap.put(SHOPLIST, shoplist);

        // 查询购物车有哪些商家的商品
        List<StoreTemp> storeList = shoppingCartMapper.selectStoreTempByshopcartIds(Arrays.asList(box));
        storeList.addAll(thirdstore);
        // 把套装中重复的第三方id去掉
        for (int j = 0; j < storeList.size(); j++) {
            for (int k = storeList.size() - 1; k > j; k--) {
                if (storeList.get(k).getThirdId().equals(storeList.get(j).getThirdId())) {
                    storeList.remove(j);
                }

            }
            // 为购物车的第三方id重新排序,boss商城排前面
            if (storeList.get(j).getThirdId() == 0) {
                StoreTemp ste = storeList.get(0);
                storeList.set(0, storeList.get(j));
                storeList.set(j, ste);
            }
        }

        // 订单总金额
        BigDecimal sumOldPrice = BigDecimal.valueOf(0);
        // 订单总金额
        BigDecimal sumPrice = BigDecimal.valueOf(0);
        for (StoreTemp st : storeList) {
                Map<String,Object> price=getEveryThirdPriceMap(st.getThirdId(),shoplist,Long.parseLong(customerAddress.getInfoCounty()));
            st.setSumPrice((BigDecimal)price.get("sumPrice"));
            sumOldPrice=((BigDecimal)price.get("sumOldPrice")).add(sumOldPrice);
            sumPrice=((BigDecimal)price.get("sumPrice")).add(sumPrice);
        }
        cartMap.put("storeList",storeList);
        //订单总金额
        cartMap.put("sumOldPrice",sumOldPrice);
        cartMap.put("sumPrice",sumPrice);
        cartMap.put(THIRDS, storeList);
        // 存储运费
        Map<String, Object> fPrice = new HashMap<>();
        fPrice.put("freightmoney", 5*1250);
        cartMap.put("fPrice", fPrice);
        return cartMap;
    }

    /**
     * 根据所选择的商品进入订单确认查询
     *
     * @param request
     * @param box
     * @return List
     */
    @Override
    public Map<String, Object> subOrder(HttpServletRequest request, Long[] box, Long[] marketingId, Long[] thirdId, ShoppingCartWareUtil wareUtil) {

        // 用来存放数据
        Map<String, Object> paramMap = new HashMap<String, Object>();

        List<Long> list = new ArrayList<Long>();
//        List<ParamIds> infoIds = new ArrayList<ParamIds>();
        // 购物车id数量不为0
        if (box.length != 0) {
            for (Long bo : box) {
                list.add(bo);
            }
        }
        List<ShoppingCart> shoplist = searchByProduct(request, box);
        // 存放第三方店铺标示
        List<Object> thirdIds = new ArrayList<Object>();

        // 添加第三方id
        if (shoplist != null && !shoplist.isEmpty()) {
            for (ShoppingCart sc : shoplist) {
                if (sc.getFitId() == null) {
                    thirdIds.add(sc.getGoodsDetailBean().getProductVo().getThirdId());
                } else {
                    thirdIds.add((long) 0);
                }
                if (sc.getFitId() == null) {
                    // 判断是否包邮
                    Long baoyou = marketService.queryByCreatimeMarketings(sc.getGoodsInfoId(), 6L, sc.getGoodsDetailBean().getProductVo().getGoods().getCatId(), sc
                            .getGoodsDetailBean().getBrand().getBrandId());
                    if (baoyou != 0) {
                        sc.setIsBaoyou("1");
                    } else {
                        sc.setIsBaoyou("0");
                    }
                }
            }
            // 去除重复第三方id
            for (int q = 0; q < thirdIds.size() - 1; q++) {
                for (int p = thirdIds.size() - 1; p > q; p--) {
                    if (thirdIds.get(p).equals(thirdIds.get(q))) {
                        thirdIds.remove(p);
                    }
                }

            }
        }
        // 记录店铺名称
        Map<Object, String> thirdName = new HashMap<Object, String>();
        List<Long> goodsInfoIds = new ArrayList<Long>();
        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId(), null));

                for (int j = 0; j < thirdIds.size(); j++) {
                    if (shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdId().equals(thirdIds.get(j))) {
                        thirdName.put(thirdIds.get(j), shoplist.get(i).getGoodsDetailBean().getProductVo().getThirdName());
                    }
                }
                goodsInfoIds.add(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());

            }
        }
        List<OrderUtil> orderMarketings = new ArrayList<OrderUtil>();
        // 根据每个商家加入商家信息
        for (int i = 0; i < thirdIds.size(); i++) {
            OrderUtil orderUtil = new OrderUtil();
            Object obj = thirdIds.get(i);
            if (obj == null) {
                obj = 0;
            }
            // 存放商家信息
            orderUtil.setThirdId(Long.parseLong(obj.toString()));
            if ("0".equals(obj.toString())) {
                orderUtil.setInfoRealname("BOSS");
            } else {
                orderUtil.setInfoRealname(thirdName.get(thirdIds.get(i)));
            }
            // 判断商品数量是否大于0，不包含套装
            if (!goodsInfoIds.isEmpty()) {

                List<Marketing> marketings = marketService.queryOrderMarketingByGoodsId(goodsInfoIds, (Long) thirdIds.get(i));
                orderUtil.setMarketings(marketings);
            }

            orderMarketings.add(orderUtil);
        }
        paramMap.put(SHOPLIST, shoplist);
        // 商品的店铺标示
        paramMap.put("thirdIds", thirdIds);
        paramMap.put("orderMarketings", orderMarketings);

        return paramMap;
    }

    /**
     * 查询最后加入的商品id
     *
     * @param response
     * @param request
     * @return
     */
    @Override
    public Long selectLastId(ShoppingCart shoppingCart, HttpServletResponse response, HttpServletRequest request) {
        try {
            ShoppingCart sc = shoppingCartMapper.selectShopingByParam(shoppingCart);
            if (sc != null) {
                shoppingCartMapper.delShoppingCartById(sc.getShoppingCartId());
            }
            long count = addShoppingCart(shoppingCart, request, response);
            if (count == 0) {
                return count;
            }
        } catch (UnsupportedEncodingException e) {
        }
        return shoppingCart.getShoppingCartId();
    }

    /**
     * 计算全国价格
     *
     * @param motheds
     * @param fe
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreight(String motheds, FreightExpress fe, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(fe.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = fe.getExpressPostageplus().multiply(new BigDecimal(a));
                price = fe.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(fe.getExpressStart().toString());
                BigDecimal temp = fe.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(fe.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = fe.getExpressPostageplus().multiply(a);
                price = fe.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(fe.getExpressStart()));
                BigDecimal temp = fe.getExpressPostageplus().multiply(a.divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        }
    }

    /**
     * 计算其他地区价格
     *
     * @param motheds
     * @param frall
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreightAll(String motheds, FreightExpressAll frall, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(frall.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = frall.getExpressPostageplus().multiply(new BigDecimal(a));
                price = frall.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(frall.getExpressStart().toString());
                BigDecimal temp = frall.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(frall.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = frall.getExpressPostageplus().multiply(a);
                price = frall.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(frall.getExpressStart()));
                BigDecimal temp = frall.getExpressPostageplus().multiply(a.divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        }

    }

    /**
     * 得到各个商家的运费
     *
     * @param thirdId
     * @param cityId
     * @param cartList
     * @return
     */

    @Override
    public BigDecimal getEverythirdExpressPrice(Long thirdId, Long cityId, List<ShoppingCart> cartList) {
        // 查询物流模板信息 根据thirdId 查询默认的模板
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("freightIsDefault", "1");
        paramMap.put("freightThirdId", thirdId);
        BigDecimal freightmoney = BigDecimal.ZERO;
        Integer goodsnum = 0;
        BigDecimal goodsweight = BigDecimal.valueOf(0);

        for (ShoppingCart sc : cartList) {
            if (sc.getThirdId().equals(thirdId)) {
                // 判断是否是套装
                if (sc.getFitId() == null) {
                    // 如果是普通商品，执行普通商品的方法
                    GoodsProductVo goodsProduct = goodsProductService.queryProductByProductId(sc.getGoodsInfoId());
                    goodsweight = goodsweight.add(goodsProduct.getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                    goodsnum += Integer.parseInt(sc.getGoodsNum().toString());

                } else {
                    // 套装运费计算
                    // 获取此套装下的所有货品
                    List<GoodsProductVo> goodsProducts = goodsProductMapper.queryDetailByGroupId(sc.getFitId());
                    // 遍历套装下的商品
                    for (int j = 0; j < goodsProducts.size(); j++) {

                        goodsweight = goodsweight.add(goodsProducts.get(j).getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                        goodsnum += Integer.parseInt(sc.getGoodsNum().toString());

                    }

                }
            }

        }
        // 获取默认模板
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);

        if (ft != null) {
            // 查询默认全国设置
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());

            // 如果全国设置不为空
            if (fe != null && !fe.isEmpty()) {
                for (int i = 0; i < fe.size(); i++) {

                    // 获取其他地区设置
                    List<FreightExpressAll> fall = fe.get(i).getFreightExpressAll();
                    // 其他地区设置不为空
                    if (fall != null && !fall.isEmpty()) {
                        for (int j = 0; j < fall.size(); j++) {
                            // 获取其他地区设置
                            String area = fall.get(j).getExpressArea();
                            String[] cityIds = area.split(",");
                            // 标识
                            int flag = 0;
                            // 判断是否存在此城市单独设置
                            for (String ciId : cityIds) {
                                if (ciId.equals(cityId.toString())) {
                                    flag = 1;
                                    break;
                                } else {
                                    continue;
                                }

                            }
                            // 有设置其他城市运费，并且包含城市的收货城市
                            if (flag == 1) {
                                freightmoney = computeFreightAll(ft.getFreightMethods(), fall.get(j), goodsnum, goodsweight);
                                break;
                                // 有设置其他城市运费，没有城市的收货城市
                            } else {
                                // 如果上述判断没有返回return
                                // 那么去默认全国设置
                                freightmoney = computeFreight(ft.getFreightMethods(), fe.get(i), goodsnum, goodsweight);
                                continue;
                            }

                        }
                    } else {
                        // 如果默认其他地区没有
                        freightmoney = computeFreight(ft.getFreightMethods(), fe.get(i), goodsnum, goodsweight);
                    }

                }

            }

        }

        return freightmoney;
    }

    /**
     * 参数是需要运费购物车id 第三方id根据第三方来分组得到各个商家的运费-
     *
     * @param cityId
     * @return
     */

    @Override
    public Map<String, Object> getNewExpressPrice(Long cityId, List<Long> cartIds) {

        List<ShoppingCart> shopdata = shoppingCartMapper.shopCartListByIds(cartIds);
        // List<ShoppingCart> shopthird = new ArrayList<>();
        Map<Long, Object> thirdMap = new HashMap<>();
        BigDecimal freightmoney = BigDecimal.ZERO;
        // boss运费为上门自提用
        BigDecimal bossfreight = BigDecimal.ZERO;
        // 得到没有包邮或者没有达到条件的购物车集合
        List<ShoppingCart> cartList = getNobaoyouShoppingcarts(shopdata);
        if (CollectionUtils.isNotEmpty(cartList)) {

            // 商家id集合
            for (ShoppingCart sh : cartList) {
                if (sh.getThirdId() != null) {
                    thirdMap.put(sh.getThirdId(), "");

                } else {
                    if (sh.getFitId() != null) {
                    }
                }
            }
            for (Long thirdId : thirdMap.keySet()) {
                freightmoney = freightmoney.add(getEverythirdExpressPrice(thirdId, cityId, cartList));
                if (thirdId == 0) {
                    bossfreight = bossfreight.add(getEverythirdExpressPrice(thirdId, cityId, cartList));
                }
            }

        }
        Map<String, Object> freightMap = new HashMap<>();
        // 总运费
        freightMap.put("freightmoney", freightmoney);
        // boss平台运费
        freightMap.put("bossfreight", bossfreight);
        return freightMap;

    }

    /**
     * 根据购物车里面的货品查询是否存在包邮的促销活动 返回list集合,不包邮的购物车
     *
     * @param cartList
     * @return
     * @author jiaodongzhi
     */
    @Override
    public List<ShoppingCart> getNobaoyouShoppingcarts(List<ShoppingCart> cartList) {
        // 不包邮购物车或者参与包邮活动但是没有达到条件
        List<ShoppingCart> nobaoyou = new ArrayList<>();
        Marketing marketing = null;
        if (CollectionUtils.isNotEmpty(cartList)) {
            Map<String, Object> map = new HashMap<>();
            // 参与包邮,生成新的购物车
            List<ShoppingCart> baoyou = new ArrayList<>();
            for (int i = 0; i < cartList.size(); i++) {
                if (cartList.get(i) != null && cartList.get(i).getGoodsInfoId() != null) {
                    // 从购物车里得到商品id,和包邮促销类型重新从数据库查询,防止当前(包邮促销)已经过期;
                    map.put(GOODSID, cartList.get(i).getGoodsInfoId());
                    map.put("codeType", "12");
                    marketing = marketingMapper.queryMarketingByGoodIdAndtype(map);
                    // 包邮
                    if (null != marketing) {
                        cartList.get(i).setMarketing(marketing);
                        baoyou.add(cartList.get(i));
                    } else {
                        // 购物车里的包邮促销过期或者没有参与包邮促销
                        nobaoyou.add(cartList.get(i));
                    }

                }
            }
            // 存放第三方id
            Map<Long, String> groups = new HashMap<Long, String>();
            // 根据第三方分组购物车 boss商品第三方id为0
            List<List<ShoppingCart>> shopThirdList = new ArrayList<>();
            Long thirdId = null;
            if (CollectionUtils.isNotEmpty(baoyou)) {
                for (ShoppingCart pd : baoyou) {
                    thirdId = pd.getThirdId();
                    groups.put(thirdId, "");
                }
                List<ShoppingCart> scart = null;
                if (groups != null && !groups.isEmpty()) {
                    for (Long rawTypeId : groups.keySet()) {
                        scart = new ArrayList<>();
                        for (ShoppingCart sc : baoyou) {

                            if (rawTypeId.equals(sc.getThirdId())) {
                                // 根据thirdId分组
                                scart.add(sc);
                            }
                        }
                        shopThirdList.add(scart);
                    }
                }
                BigDecimal aftermoney = BigDecimal.valueOf(0);
                Long countgoods = 0L;
                List<ShoppingCart> shop = new ArrayList<>();
                for (int m = 0; m < shopThirdList.size(); m++) {
                    // 按third分组判断是否包邮
                    baoyou = shopThirdList.get(m);
                    ShoppingCart sc = new ShoppingCart();
                    for (int j = 0; j < baoyou.size(); j++) {
                        GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(baoyou.get(j).getGoodsInfoId());
                        BigDecimal goodsMoney = goodsProduct.getGoodsInfoPreferPrice();
                        Map<String, Object> mapGoods = new HashMap<String, Object>();
                        // 从购物车里得到促销id重新从数据库查询,防止当前(折扣促销)已经过期;
                        Marketing mark = marketingMapper.marketingDetail(baoyou.get(j).getMarketingId());
                        // 参与折扣促销
                        if (null != mark) {
                            mapGoods.put(MARKETINGID, mark.getMarketingId());
                            mapGoods.put(GOODSID, baoyou.get(j).getGoodsInfoId());
                        }

                        countgoods = baoyou.get(j).getGoodsNum();
                        // 购物车里商品的总价格
                        aftermoney = aftermoney.add(goodsMoney.multiply(BigDecimal.valueOf(countgoods)));
                        sc.setMarketgoodsPrice(aftermoney);
                        sc.setThirdId(baoyou.get(j).getThirdId());
                        shop.add(sc);
                    }
                    // 分组后的价格

                    // Map<String, Object> app = new HashMap<>();
                    for (int k = 0; k < baoyou.size(); k++) {
                        // 计算第三方分组后以及参与包邮分组
                        for (ShoppingCart scra : shop) {
                            if (scra.getThirdId().equals(baoyou.get(k).getThirdId()) && scra.getMarketgoodsPrice().compareTo(baoyou.get(k).getMarketing().getShippingMoney()) == -1) {

                                // 参与包邮但没有达到条件包邮
                                    nobaoyou.add(baoyou.get(k));
                            }
                        }
                    }

                }

            }
        }
        return nobaoyou;

    }
    /**
     * 新产品使用购物车
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    public Map<String, Object> shopCartMap(HttpServletRequest request, HttpServletResponse response,Long type) {
        Map<String, Object> cartMap = new HashMap<String, Object>();
        // 获取用户ID
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 如果用户登录了
        if (customerId != null) {
            // 查询所有购物车商品
            List<ShoppingCart> shoplist = shoppingCartMapper.shoppingCartMini(customerId);
            // 查询购物车商品详细
            selectShoppingCartDetail(shoplist);
            cartMap.put(SHOPLIST, shoplist);
            // 购物车商品商家
            cartMap.put("stores",shoppingCartMapper.selectStoreTempByCustomerId(customerId));

        } else {
            try {
                // 获取cookie中的数据
                List<ShopCarUtil> list = loadCookShopCar(request);
                // 定义购物车List
                List<ShoppingCart> shoplist = new ArrayList<ShoppingCart>();
                // 查询所有参与的促销
                List<Marketing> marketinglist=null;
                if (list != null && !list.isEmpty()) {
                    for (int i = 0; i < list.size(); i++) {
                        // 封装数据
                        ShoppingCart sc = new ShoppingCart();
                        sc.setGoodsInfoId(list.get(i).getProductId());
                        sc.setShoppingCartId(list.get(i).getProductId());
                        sc.setMarketingId(list.get(i).getMarketId());
                        sc.setMarketingActivityId(list.get(i).getMarketActiveId());
                        sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                        sc.setFitId(list.get(i).getFitId());
                        sc.setDistinctId(list.get(i).getDistinctId());
                        shoplist.add(sc);
                    }
                    // 查询购物车商品详细
                    selectShoppingCartDetail(shoplist);

                }
                cartMap.put(SHOPLIST, shoplist);
                cartMap.put(MARKETINGLIST, marketinglist);

            } catch (UnsupportedEncodingException e) {
            }

        }
        return cartMap;
    }


    /**
     * 新版移动端商品详情页查询购车车数量
     *
     * @param request
     * @author houyichang 2015/10/21
     */
    @Override
    public MiniShoppingCart shopCartMapMobile(HttpServletRequest request,Long type) {
        MiniShoppingCart miniShoppingCart = new MiniShoppingCart();
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        List<ShoppingCart> shoplist = new ArrayList<ShoppingCart>();
        // 如果已经登录
        if (customerId != null) {
            shoplist = shoppingCartMapper.shoppingCartMini(customerId);
            if (shoplist != null && !shoplist.isEmpty()) {
                for (int i = 0; i < shoplist.size(); i++) {
                    if (shoplist.get(i).getFitId() == null) {
                        shoplist.get(i).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(shoplist.get(i).getGoodsInfoId(), shoplist.get(i).getDistinctId(),null));
                    }
                }
            }
        } else {
            List<ShopCarUtil> list = null;
            try {
                list = loadCookShopCar(request);
            } catch (UnsupportedEncodingException e) {
            }
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    ShoppingCart sc = new ShoppingCart();
                    if (list.get(i).getFitId() == null) {
                        sc.setGoodsInfoId(list.get(i).getProductId());
                    } else {
                        // 设置套装商品id
                        sc.setGoodsInfoId(Long.parseLong(PROINFO + list.get(i).getFitId()));
                        // 设置套装id
                        sc.setFitId(list.get(i).getFitId());
                    }
                    sc.setGoodsNum(Long.valueOf(list.get(i).getGoodsNum()));
                    sc.setMarketing(null);
                    sc.setShoppingCartId(Long.valueOf(i));
                    sc.setDistinctId(list.get(i).getDistinctId());
                    shoplist.add(sc);
                }
                if (shoplist != null && !shoplist.isEmpty()) {
                    for (int i = 0; i < shoplist.size(); i++) {
                        if (((ShoppingCart) shoplist.get(i)).getFitId() != null) {
                        } else {
                            ((ShoppingCart) shoplist.get(i)).setGoodsDetailBean(goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoplist.get(i)).getGoodsInfoId(),
                                    list.get(i).getDistinctId(),null));
                        }
                    }

                }
            }
        }
        List<MiniGoods> minilist = new ArrayList<MiniGoods>();

        if (shoplist != null && !shoplist.isEmpty()) {
            for (int i = 0; i < shoplist.size(); i++) {
                if (shoplist.get(i).getFitId() == null) {
                    if (shoplist.get(i).getGoodsDetailBean() != null && shoplist.get(i).getGoodsDetailBean().getProductVo() != null) {
                        MiniGoods mg = new MiniGoods();
                        mg.setShoppingCartId(shoplist.get(i).getShoppingCartId());
                        mg.setBuNum(shoplist.get(i).getGoodsNum());
                        if (shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId() != null
                                && !"".equals(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId())) {
                            mg.setProductPic(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoImgId());
                        } else {
                            if (shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList() != null
                                    && !shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList().isEmpty()) {
                                mg.setProductPic(shoplist.get(i).getGoodsDetailBean().getProductVo().getImageList().get(0).getImageThumName());
                            }

                        }

                        mg.setMarketing(shoplist.get(i).getMarketing());
                        mg.setGoodsId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsId());
                        mg.setGoodsInfoId(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoId());
                        mg.setProductName(shoplist.get(i).getGoodsDetailBean().getProductVo().getProductName());
                        mg.setProductPrice(shoplist.get(i).getGoodsDetailBean().getProductVo().getGoodsInfoPreferPrice());
                        minilist.add(mg);
                    }

                }
            }
            miniShoppingCart.setMiniGoodsList(minilist);
        }

        return miniShoppingCart;
    }

    /**
     * 查询ShoppingCart 每个商品的详细信息
     *
     * @param shoppingCartList
     */
    public void selectShoppingCartDetail(List<ShoppingCart> shoppingCartList) {
        // 判断是否为null
        if (shoppingCartList != null && !shoppingCartList.isEmpty()) {
            for (int i = 0; i < shoppingCartList.size(); i++) {
                // 如果商品不是套装
                if (shoppingCartList.get(i).getFitId() == null) {
                    // 查询商品详细
                    shoppingCartList.get(i).setGoodsDetailBean(
                            goodsProductService.queryDetailBeanByProductId(((ShoppingCart) shoppingCartList.get(i)).getGoodsInfoId(), 0L, null));
                    // 拿出的商品详情为空时 跳出本次循环
                    if (shoppingCartList.get(i).getGoodsDetailBean() == null) {
                        continue;
                    }
                }
            }

        }
    }
}
