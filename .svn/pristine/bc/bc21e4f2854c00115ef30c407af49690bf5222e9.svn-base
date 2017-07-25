package com.ylife.chinapost.mobile.controller;

import com.ylife.address.service.AddressService;
import com.ylife.chinapost.mobile.service.CurrentLoginService;
import com.ylife.chinapost.mobile.util.LoginUtil;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.model.OrderInfoBean;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerAddressService;
import com.ylife.customer.service.CustomerService;
import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.PageBean;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.fdfs.FastDFS;
import com.ylife.goods.model.GoodsProductVo;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.service.InventoryService;
import com.ylife.shoppingcart.mapper.ShoppingCartMapper;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.BASE64DecodedMultipartFile;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Wangqi on 2016/5/6.
 * 用户控制器
 */
@Controller
public class CustomerController {

    @Resource
    private ChinapostCustomerService chinapostCustomerService;

    @Resource
    private CustomerUcoinService customerUcoinService;

    @Resource
    private CustomerAddressService customerAddressService;

    @Resource
    private CustomerService customerService;

    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private CurrentLoginService currentLoginService;

    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @Resource
    private InventoryService inventoryService;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private GoodsProductService goodsProductService;

    @Resource
    private AddressService addressService;

    @Resource
    private FastDFS dfs;

    /**
     * 跳转到个人中心
     *
     * @return
     */
    @RequestMapping(value = "/customercenter")
    public String customercenter(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            Map map = new HashMap();
            map.put("customerId", request.getSession().getAttribute("customerId"));
            //退款的订单总数
            request.setAttribute("cancelCount", customerService.searchTotalCountBack(map));
            //未付款的订单总数
            map.put("type", "1");
            request.setAttribute("noMoneyCount", customerService.searchTotalCount(map));
            //代发货的订单
            map.put("type", "2");
            request.setAttribute("unfilledOrders", customerService.searchTotalCount(map));
            //待收货的订单总数
            map.put("type", "3");
            request.setAttribute("noFaHuoCount", customerService.searchTotalCount(map));

            //待评价的订单总数
            map.put("type", "4");
            request.setAttribute("unDeliveryCount", customerService.searchTotalCount(map));

            //个人信息
            request.setAttribute("cust",chinapostCustomerService.getCustomer((Long) request.getSession().getAttribute("customerId")));
            return "member_index";
        } else {
            return "redirect:/login?url=customercenter";
        }
    }

    /**
     * 跳转到我的地址页面
     *
     * @return
     */
    @RequestMapping(value = "/myaddress")
    public String myaddress(HttpServletRequest request, String flag) {
        request.setAttribute("flag", flag);
        return "myaddress";
    }

    /**
     * 跳转到我的地址页面
     *
     * @param request
     * @param addressId    地址ID
     * @param enterpriseId 自提点企业ID
     * @param addressName  自提点地址
     * @param flag         从确认订单页跳转 还是从我的地址跳转过来
     * @param enterpriseName 企业名称
     * @param selfFlag     订单确认页面跳转过来，自提标识
     * @return
     */
    @RequestMapping(value = "/distribution")
    public String distribution(HttpServletRequest request, Long addressId, Long enterpriseId, String addressName, String flag,String enterpriseName,String selfFlag) {
        // 判断用户是否登录
        if (LoginUtil.checkLoginStatus(request)) {
            // 地址列表
            List<CustomerAddress> addresses = customerAddressService.queryCustomerAddress((Long) request.getSession().getAttribute("customerId"));
            request.setAttribute("addresses", addresses);
            request.setAttribute("addressId", addressId);
            request.setAttribute("enterpriseId", enterpriseId);
            request.setAttribute("addressName", addressName);
            request.setAttribute("enterpriseName",enterpriseName);
            request.setAttribute("flag", flag);
            request.setAttribute("selfFlag",selfFlag);
            return "distribution";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 验证支付密码是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkExistsPayPassWord", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkExistsPayPassWord(HttpServletRequest request) {
        long customerId = (long) request.getSession().getAttribute("customerId");
        if (StringUtil.isBlank(chinapostCustomerService.getCustomer(customerId).getPaykey())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.DATA_NOT_FOUND, "支付密码不存在").toJson();
        }
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 跳转到我的支付密码页面
     *
     * @return
     */
    @RequestMapping(value = "/paypassword")
    public String paypassword(HttpServletRequest request, String flag) {
        request.setAttribute("flag", flag);
        return "updatePayPassword";
    }

    /**
     * 跳转到帐号中心
     *
     * @return
     */
    @RequestMapping(value = "/account")
    public String myaccount(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            ChinapostCustomer customer = currentLoginService.getCurrentLoginCustomer();
            request.setAttribute("imgUrl", customer.getImgUrl());
            request.setAttribute("fullname", customer.getFullname());
            request.setAttribute("male", customer.getMale());
            request.setAttribute("phoneNo", customer.getPhoneNo());
            request.setAttribute("idcardNo", customer.getIdcardNo());
            request.setAttribute("contactPhone", customer.getContactPhone());
            request.setAttribute("contactAddr", customer.getContactAddr());
            return "myaccount";
        } else {
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 跳转到帐号安全
     * @return
     */
    @RequestMapping(value = "/accountSecurity")
    public String accountSecurity(HttpServletRequest request){
        if (LoginUtil.checkLoginStatus(request)) {
            return "ord_account";
        }else{
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 跳转到支付密码页面
     *
     * @return
     */
    @RequestMapping(value = "/paypasswordindex")
    public String paypasswordView(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            long customerId = (long) request.getSession().getAttribute("customerId");
            if (StringUtil.isBlank(chinapostCustomerService.getCustomer(customerId).getPaykey())) {
                request.setAttribute("isExist", "0");
            } else {
                request.setAttribute("isExist", "1");
            }
            return "pay_password";
        } else {
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 跳转到登录密码页面
     *
     * @return
     */
    @RequestMapping(value = "/passwordindex")
    public String passwordindex(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            return "login_password";
        } else {
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 跳转到修改登录密码页面
     *
     * @return
     */
    @RequestMapping(value = "/updatepassword")
    public String updatepassword(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            return "updateLoginPassword";
        } else {
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 修改我的支付密码
     *
     * @param request
     * @param oldpayPassword 原来的支付密码
     * @param payPassword    新的支付密码
     * @param newpassword    再次输入的支付密码
     * @param flag           从确认订单页跳转 还是从我的地址跳转过来
     * @return
     */
    @RequestMapping(value = "/updatePayPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updatePayPassword(HttpServletRequest request, String oldpayPassword, String payPassword, String newpassword, String flag) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, "请跳转到登录页面").toJson();
        }
        //判断前端传过来的值
        if (flag.equals("1")) {
            if (StringUtil.isBlank(oldpayPassword.trim())) {
                return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, "请输入原支付密码").toJson();
            }
            if (!chinapostCustomerService.checkPaykey(customerId, oldpayPassword)) {
                return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, "原支付密码错误").toJson();
            }
        }
        if (StringUtil.isBlank(payPassword.trim())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_NOT_EXISTS, "请输入支付密码").toJson();
        }
        if (StringUtil.isBlank(newpassword.trim())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_AGAIN_NOT_EXISTS, "请输入新的支付密码").toJson();
        }
        if (!newpassword.equals(payPassword)) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_NOT_EQUAL, "两次输入的新支付密码不一致").toJson();
        }
        chinapostCustomerService.setPaymentPassword(customerId, payPassword);
        if (StringUtil.isBlank(flag)) {
            return new JsonResponseBean("suborder").toJson();
        }
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改我的登录密码
     *
     * @param request
     * @param oldPassword 原来的登录密码
     * @param Password    新的登录密码
     * @param newpassword 再次输入登录密码
     * @return
     */
    @RequestMapping(value = "/updatePassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updatePassword(HttpServletRequest request, String oldPassword, String Password, String newpassword) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, "请跳转到登录页面").toJson();
        }
        //判断前端传过来的值
        if (StringUtil.isBlank(oldPassword.trim())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, "请输入原登录密码").toJson();
        }
        if (!chinapostCustomerService.checkpassword(customerId, oldPassword)) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, "原登录密码错误").toJson();
        }
        if (StringUtil.isBlank(Password.trim())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_NOT_EXISTS, "请输入登录密码").toJson();
        }
        if (StringUtil.isBlank(newpassword.trim())) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_AGAIN_NOT_EXISTS, "请输入新的登录密码").toJson();
        }
        if (!newpassword.equals(Password)) {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_NOT_EQUAL, "两次输入的新登录密码不一致").toJson();
        }
        chinapostCustomerService.updatePassword(customerId, Password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 判断支付密码正确性
     *
     * @param request
     * @param payPassword 支付密码
     * @return
     */
    @RequestMapping(value = "/checkPayPassword", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkPayPassword(HttpServletRequest request, String payPassword) {
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId == null) {
            return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN, "请跳转到登录页面").toJson();
        }
        if (chinapostCustomerService.checkPaykey(customerId, payPassword)) {
            return JsonResponseBean.getSuccessResponse().toJson();
        } else {
            return JsonResponseBean.getErrorResponse(ErrorCode.PAYPASSWORD_ERROR, "支付密码错误").toJson();
        }
    }

    /**
     * 我的邮宝余额
     *
     * @return
     */
    @RequestMapping(value = "/myUcoinBalance", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String myUcoinBalance(HttpServletRequest request) {
        BigDecimal myUcoinBalance = customerUcoinService.getUcoinBalance((Long) request.getSession().getAttribute("customerId"));
        return new JsonResponseBean(myUcoinBalance).toJson();
    }

    /**
     * 更改订单收货地址
     *
     * @param request
     * @param addressId
     * @return
     */
    @RequestMapping("/changeAddress")
    public String changeAddress(HttpServletRequest request, Long addressId,String flag) {
        // 用户编号
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        if (customerId != null) {
            // 创建容器存储要修改的地址信息
            CustomerAddress address = new CustomerAddress();
            address.setAddressId(addressId);
            address.setCustomerId(customerId);
            // 1为使用
            address.setIsDefault("1");
            // 将所有的地址都更改为默认状态0
            customerAddressService.updateAddressDef(customerId);
            // 修改指定地址为使用
            customerAddressService.updateAddress(address, customerId);
            // 判断返回地址
            if (!StringUtil.isBlank(flag) && flag.equals("1")) {
                return "redirect:/customercenter";
            }
            // 订单
            return "redirect:/suborder";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 更改自提点地址
     *
     * @param request
     * @param enterpriseId 企业ID
     * @param addressName  企业地址
     * @param enterpriseName 企业名称
     * @return
     */
    @RequestMapping("/changeDeliveryAddress")
    public String changeDeliveryAddress(HttpServletRequest request, Long enterpriseId, String addressName,String enterpriseName, RedirectAttributes attr) {
        attr.addAttribute("enterpriseId", enterpriseId);
        attr.addAttribute("addressName", addressName);
        attr.addAttribute("enterpriseName", enterpriseName);
        if(request.getSession().getAttribute("box")!=null && enterpriseId>0){
            //缺货商品
            String unInventoryGoodsInfo="缺货商品：";
            //购物车信息
            List<ShoppingCart> shoplist = shoppingCartMapper.shopCartListByIds(Arrays.asList((Long[])request.getSession().getAttribute("box")));
            for(ShoppingCart shoppingCart:shoplist) {
                Inventory inventory = inventoryService.getInventory(enterpriseId,shoppingCart.getGoodsInfoId());
                if(inventory == null || inventory.getAvailable().intValue()<shoppingCart.getGoodsNum()){
                    GoodsProductVo goodsProductVo = goodsProductService.queryProductByProductId(shoppingCart.getGoodsInfoId());
                    unInventoryGoodsInfo=unInventoryGoodsInfo.concat(goodsProductVo.getProductName().concat(","));
                }
            }
            if(!unInventoryGoodsInfo.equals("缺货商品：")) {
                attr.addAttribute("unInventoryGoodsInfo", unInventoryGoodsInfo.substring(0, unInventoryGoodsInfo.length() - 1));
            }
        }
        return "redirect:/suborder";
    }

    /**
     * 修改收货地址
     */
    @RequestMapping("/orderupdateaddress")
    public String orderUpdateAddress(HttpServletRequest request, CustomerAddress address, String flag, RedirectAttributes attr) {
        if (LoginUtil.checkLoginStatus(request)) {
            //如果新添加地址设置为默认了，就把用户之前添加的地址设置为不是默认
            if (("1").equals(address.getIsDefault())) {
                // 地址列表
                List<CustomerAddress> addresses = customerAddressService.queryCustomerAddress((Long) request.getSession().getAttribute("customerId"));
                CustomerAddress addr = new CustomerAddress();
                for (Object object : addresses) {
                    addr = (CustomerAddress) object;
                    addr.setIsDefault("0");
                    customerAddressService.updateAddress(addr, (Long) request.getSession().getAttribute("customerId"));
                }
            }
            //修改地址
            customerAddressService.updateAddress(address, (Long) request.getSession().getAttribute("customerId"));
            attr.addAttribute("flag", flag);
            return "redirect:/distribution";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 去修改地址
     *
     * @return
     */
    @RequestMapping("toupdateAddress")
    public String toupdateAddress(HttpServletRequest request, Long addressId, String flag) {
        // 获得用户Id
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        // 判断是否登录
        if (customerId != null) {
            CustomerAddress address = customerAddressService.queryCustomerAddressById(addressId, customerId);

            request.setAttribute("orderaddress", address);
            request.setAttribute("flag", flag);
            return "myaddress";
        } else {
            // 去登录
            return "redirect:/login";
        }
    }

    /**
     * 添加收货地址
     *
     * @return
     */
    @RequestMapping("/orderaddaddress")
    public String orderAddAddress(HttpServletRequest request, CustomerAddress address, String flag, RedirectAttributes attr) {
        if (LoginUtil.checkLoginStatus(request)) {
            //如果新添加地址设置为默认了，就把用户之前添加的地址设置为不是默认
            if (("1").equals(address.getIsDefault())) {
                // 地址列表
                List<CustomerAddress> addresses = customerAddressService.queryCustomerAddress((Long) request.getSession().getAttribute("customerId"));
                CustomerAddress addr = new CustomerAddress();
                for (Object object : addresses) {
                    addr = (CustomerAddress) object;
                    addr.setIsDefault("0");
                    customerAddressService.updateAddress(addr, (Long) request.getSession().getAttribute("customerId"));
                }
            }
            if (StringUtil.isBlank(address.getIsDefault())) {
                address.setIsDefault("0");
            }
            customerAddressService.addAddress(address, (Long) request.getSession().getAttribute("customerId"));
            attr.addAttribute("flag", flag);
            return "redirect:/distribution";
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 删除
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/delAddress")
    public String delAddress(HttpServletRequest request, Long addressId,String flag) {
        if (LoginUtil.checkLoginStatus(request)) {
            // 创建容器
            CustomerAddress address = new CustomerAddress();
            // 地址Id
            address.setAddressId(addressId);
            // 1为删除状态
            address.setDelFlag("1");
            // 修改地址状态 为删除
            customerAddressService.updateAddress(address, (Long) request.getSession().getAttribute("customerId"));
            return "redirect:/distribution?flag="+flag;
        } else {
            return "redirect:/login";
        }
    }

    /**
     * 跳转我的订单页面
     *
     * @param request
     * @return ModelAndview
     */
    @RequestMapping("/myorder")
    public String queryAllOrders(HttpServletRequest request, HttpServletResponse response, PageBean pb, String type) {
        // 检查用户是否登录
        if (LoginUtil.checkLoginStatus(request)) {

            Map<String, Object> paramMap = new HashMap<String, Object>();
            Long customerId = (Long) request.getSession().getAttribute("customerId");
            paramMap.put("customerId", customerId);
            paramMap.put("type", type);
            request.setAttribute("type", type);
            if (type != null && "6".equals(type)) {
                request.setAttribute("pb", customerService.queryAllBackMyOrders(paramMap, pb));
                return "back_order_list";
            } else {
                request.setAttribute("pb", customerService.queryAllMyOrders(paramMap, pb));
                return "order_list";
            }
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }

    /**
     * 跳转我的订单页面
     *
     * @param request
     */
    @RequestMapping(value ="/allmyorder", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryAllMyOrders(HttpServletRequest request, PageBean pb, String type,String state) {

        // 检查用户是否登录
        if (LoginUtil.checkLoginStatus(request)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Long customerId = (Long) request.getSession().getAttribute("customerId");
            paramMap.put("customerId", customerId);
            paramMap.put("type", type);
            paramMap.put("state", state);
            resultMap.put("type", type);
            if(type != null && "6".equals(type)){
                resultMap.put("pb", customerService.queryAllBackMyOrders(paramMap, pb));
            }else{
                resultMap.put("pb", customerService.queryAllMyOrders(paramMap, pb));
            }

            return new JsonResponseBean(resultMap).toJson();
        }else{
            return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN,"用户未登录").toJson();
        }
    }


    /**
     * 根据订单编号查询订单信息
     *
     * @param orderId 订单编号
     * @return ModelAndView
     */
    @RequestMapping("/orderdetails")
    public String queryOrderByOrderId(HttpServletRequest request, Long orderId) {
        if (LoginUtil.checkLoginStatus(request)) {
            OrderInfoBean order = customerService.queryOrderByParamMap(orderId, (Long) request.getSession().getAttribute("customerId"));
            if(order.getEnterpriseId()!=null && order.getEnterpriseId()>0){
                request.setAttribute("enterpriseInfo",enterpriseInfoService.getEnterpriseInfo(order.getEnterpriseId()));
            }
            request.setAttribute("order", order);
            return "order_detail";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }

    /**
     * 我的邮宝页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/myUcoin")
    public String myUcoin(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            request.setAttribute("myUcoinBalance", customerUcoinService.getUcoinBalance((Long) request.getSession().getAttribute("customerId")));
            request.setAttribute("customerUcoinHistoryList", customerUcoinHistoryService.getHistory((Long) request.getSession().getAttribute("customerId"),new Pageable(1,15)));
            return "myUcoin";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }

    /**
     * 我的邮宝记录分页
     *
     * @param request
     * @param pageNumber 页数
     * @return
     * */
    @RequestMapping(value ="/myUcoinPage", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String myUcoinPage(HttpServletRequest request,int pageNumber){
        if(LoginUtil.checkLoginStatus(request)){
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("customerUcoinHistoryList", customerUcoinHistoryService.getHistory((Long) request.getSession().getAttribute("customerId"), new Pageable(pageNumber, 15)));
            return new JsonResponseBean(paramMap).toJson();
        }else {
            return JsonResponseBean.getErrorResponse(ErrorCode.NOT_LOGIN,"用户未登录").toJson();
        }
    }

    /**
     * 邮宝明细页面
     * @param request
     * @param ucoinHistoryId 邮宝记录ID
     * @return
     */
    @RequestMapping("/billDeta")
    public String billDeta(HttpServletRequest request,long ucoinHistoryId) {
        if (LoginUtil.checkLoginStatus(request)) {
            request.setAttribute("customerUcoinHistory", customerUcoinHistoryService.selectDetailByPrimaryKey(ucoinHistoryId));
            return "billDetail";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }


    /**
     * 个人资料 联系地址
     * @param request
     * @return
     */
    @RequestMapping("/contactAddress")
    public String contactAddress(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            ChinapostCustomer customer = currentLoginService.getCurrentLoginCustomer();
            if(StringUtil.isBlank(customer.getContactAddr())){
                return "updateAddress";
            }
            request.setAttribute("customer",customer);
            return "contactAddress";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }

    /**
     * 个人资料 修改联系地址页面
     * @return
     */
    @RequestMapping("/goupdateAddress")
    public String goupdateAddress(HttpServletRequest request){
        if (LoginUtil.checkLoginStatus(request)) {
            request.setAttribute("customer", currentLoginService.getCurrentLoginCustomer());
            return "updateAddress";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }


    /**
     * 个人资料 修改联系地址
     * @return
     */
    @RequestMapping("/updateAddress")
    public String updateAddress(HttpServletRequest request,long provinceId,long cityId,long districtId,String addr){
        if (LoginUtil.checkLoginStatus(request)) {
            ChinapostCustomer customer = currentLoginService.getCurrentLoginCustomer();
            String contactAddr = addressService.getAddressString(provinceId,cityId,districtId,addr);
            chinapostCustomerService.editCustomer(customer.getId(),customer.getFullname(),customer.getPhoneNo(),provinceId,cityId,districtId,addr,customer.getManagerNo(),null,"",contactAddr,null);
            return "redirect:/contactAddress";
        } else {
            // 没登录的用户跳转到登录页面
            return "redirect:/login";
        }
    }

    /**
     * 修改用户头像
     *
     * @param request
     * @param resp
     * @throws java.io.IOException
     */
    @RequestMapping("updateCustomerImg")
    public void uploadShareImg(MultipartHttpServletRequest request, HttpServletResponse resp) throws Exception {
        PrintWriter out = resp.getWriter();
        String msg = null;
        String imgs = request.getParameter("CustomerImg");
        MultipartFile file = null;
        if(imgs != null){
            file = new BASE64DecodedMultipartFile(imgs);
        }
        // 检查文件大小
        if (file.getSize() > 4 * 2014 * 2014) {
            msg = "101";
        } else if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "102";
        } else {
            ChinapostCustomer customer = currentLoginService.getCurrentLoginCustomer();
            msg = dfs.saveFile(file,"jpg");
            customer.setImgUrl(msg);
            chinapostCustomerService.editCustomer(customer.getId(),null,null,customer.getProvinceId(),customer.getCityId(),customer.getDistrictId(),null,null,null,"",null,msg);
        }
        out.println("<script>parent.callback('" + msg + "');</script>");
    }

    /**
     * 检查文件扩展名是否为图片
     *
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
        //非空验证 文件拓展名
        if(null != fileName){
            //LOGGER.info("检查文件扩展名【"+fileName+"】是否为图片");
        }
        if (fileName.indexOf(".") < 0) {
            return false;
        }
        String extend = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] extendNames = { "jpg", "jpeg", "bmp", "png", "gif" };
        for (String extendName : extendNames) {
            if (extend.equals(extendName)) {
                return true;
            }
        }
        return false;
    }

}
