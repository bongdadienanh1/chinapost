package com.ylife.chinapost.mobile.controller;

import com.ylife.chinapost.mobile.service.PlaceBackOrderService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.fdfs.FastDFS;
import com.ylife.order.model.CredentialType;
import com.ylife.order.model.CreditOrder;
import com.ylife.order.model.CreditOrderReason;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/5/18.
 */
@Controller
public class BackOrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private PlaceBackOrderService placeBackOrderService;

    @Resource
    private CreditOrderService creditOrderService;

    @Resource
    private FastDFS dfs;

    /**
     * 跳转到申请退款页面
     * @param orderCode 订单号
     * @return
     */
    @RequestMapping(value = "/applyBackMoney")
    public String applyBackMoney(HttpServletRequest request,long orderCode) {
        request.setAttribute("order",orderService.getOrder(orderCode));
        return "apply_back_money";
    }

    /**
     * 跳转到申请退货页面
     * @param orderCode 订单号
     * @return
     */
    @RequestMapping(value = "/applyBackGoods")
    public String applyBackGoods(HttpServletRequest request,long orderCode) {
        request.setAttribute("order",orderService.getOrder(orderCode));
        return "apply_back_goods";
    }

    /**
     * 提交申请退款
     * @param request
     * @param orderNo 订单号
     * @param reason 退款原因
     * @param credentialDoc 凭证附件
     * @param remark 备注
     * @return
     */
    @RequestMapping(value = "/submitApplyBackMoney",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitApplyBackMoney(HttpServletRequest request, Long orderNo, CreditOrderReason reason, String credentialDoc, String remark) {
        placeBackOrderService.applyBackMoney(orderNo,reason,CredentialType.NO_CREDENTIAL,credentialDoc,remark);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 提交申请退货
     * @param request
     * @param orderNo 订单号
     * @param backGoodsId 要退单的订单商品ID列表
     * @param reason 退款原因
     * @param credential 申请凭据
     * @param credentialDoc 凭证附件
     * @param remark 备注
     * @param backWay 退回方式
     * @return
     */
    @RequestMapping(value = "/submitApplyBackGoods",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitApplyBackGoods(HttpServletRequest request,String backGoodsId, Long orderNo, CreditOrderReason reason, CredentialType credential,String credentialDoc, String remark,String backWay) {
        placeBackOrderService.applyBackGoods(orderNo, backGoodsId, reason, credential, credentialDoc,remark,backWay);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 跳转到退款/退货详情页面
     * @param backOrderCode 退单号
     * @return
     */
    @RequestMapping(value = "/backorderpricedetail")
    public String backOrderPriceDetail(HttpServletRequest request,long backOrderCode) {
        CreditOrder bOrder = creditOrderService.selectByCreditOrderCode(backOrderCode);
        request.setAttribute("bOrder", bOrder);
        return "back_order_detail";
    }

    /***
     * 填写退货物流信息
     * @param wlname
     *            物流名称
     * @param wlno
     *            物流单号
     * @param orderNo
     *            订单号
     * @return
     */
    @RequestMapping(value ="/saveMBackOrderGeneral",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String saveBackOrderGeneral(HttpServletRequest request, String wlname, String wlno, long orderNo) {
        placeBackOrderService.saveBackOrderGeneral(wlname,wlno,orderNo);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 申请退单时上传图片
     *
     * @param request
     * @param resp
     */
    @RequestMapping("back/upload")
    public void uploadBackImg(MultipartHttpServletRequest request, HttpServletResponse resp, Long orderId) throws Exception {
        PrintWriter out = resp.getWriter();
        String msg = null;
        MultipartFile file = request.getFile("uploadDocument");
        file.getOriginalFilename();
        // 检查文件大小
        if (file.getSize() > 4 * 2014 * 2014) {
            msg = "101";
        } else if (!checkExtendsName(file.getOriginalFilename())) {
            msg = "102";
        } else {
            msg = dfs.saveFile(file, "jpg") + "," + orderId;
        }
        out.append("<script>parent.callback('" + msg + "');</script>");
    }

    /**
     * 检查文件扩展名是否为图片
     *
     * @param fileName
     *            上传的文件的文件名
     * @return
     */
    private boolean checkExtendsName(String fileName) {
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
