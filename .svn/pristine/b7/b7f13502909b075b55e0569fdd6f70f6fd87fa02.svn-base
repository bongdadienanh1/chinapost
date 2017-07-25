package com.ylife.chinapost.mobile.controller;

import com.ylife.chinapost.mobile.service.InventorySearchProductService;
import com.ylife.data.json.message.ErrorCode;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsDetailBean;
import com.ylife.goods.model.GoodsImage;
import com.ylife.goods.service.GoodsProductService;
import com.ylife.thirdplatform.entity.ThirdPlatformEntity;
import com.ylife.thirdplatform.mapper.ThirdPlatformMapper;
import com.ylife.thirdplatforminterface.entity.ThirdPlatformInterfaceEntity;
import com.ylife.thirdplatforminterface.service.ThirdPlatformInterfaceService;
import com.ylife.utils.Assert;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/7.
 */
@Controller
public class GoodsProductController {
    //货品service
    @Resource
    private GoodsProductService productService;

    @Resource
    private InventorySearchProductService inventorySearchProductService;

    @Resource
    private ThirdPlatformMapper thirdPlatformMapper;

    @Resource
    private ThirdPlatformInterfaceService thirdPlatformInterfaceService;


    /**
     * 商品详情页
     * @param request
     * @param productId
     * @author lih 货品ID
     */
    @RequestMapping("/productdetail")
    public String goodsDetail(HttpServletRequest request,long productId) {
        // 查询商品详情
        GoodsDetailBean detailBean = this.productService.queryDetailBeanByProductId(productId, 0L, null);
        if (detailBean != null) {
            if (null != detailBean.getProductVo()) {
                // 重新为图片排序，默认图片放到第一个
                List<GoodsImage> imageList = detailBean.getProductVo().getImageList();
                if (CollectionUtils.isNotEmpty(imageList)) {
                    for (int i = 0; i < imageList.size(); i++) {
                        if(imageList.get(i).getGoodsImgSort() != null){
                            if ("1".equals(imageList.get(i).getGoodsImgSort().toString())) {
                                GoodsImage go = imageList.get(0);
                                imageList.set(0, imageList.get(i));
                                imageList.set(i, go);
                            }
                        }
                    }
                    detailBean.getProductVo().setImageList(imageList);
                }

                //处理商品详情
                String mobileDesc = detailBean.getProductVo().getGoods().getMobileDesc();
                if(!StringUtils.isBlank(mobileDesc)) {
                    while (mobileDesc.indexOf("width") != -1) {
                        mobileDesc = mobileDesc.substring(0, mobileDesc.indexOf("width")) + mobileDesc.substring(mobileDesc.indexOf("width") + 6);
                    }
                    // 将图片路径中的单引号转换为双引号
                    detailBean.getProductVo().getGoods().setMobileDesc(mobileDesc.replaceAll("\'", "\""));
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("detailBean", detailBean);
            request.setAttribute("map", map);
            return "item";
        } else {//商品已下架或过期不存在
            return "no_exit";
        }
    }

    /**
     * 商品图片
     * 移动版前端商品详情页点击轮播大图处调用
     *
     * @author houyichang 2015/10/26
     * */
    @RequestMapping("/showBigImage")
    public String queryBigImage(HttpServletRequest request,Long productId){
        GoodsDetailBean detailBean = this.productService.queryDetailBeanByProductId(productId, 1103L, null);
        request.setAttribute("detailBean",detailBean);
        return "goods_detail_pic";
    }

    /**
     * 手机搜索商品
     * @param goodsInfoName 商品名称
     * */
    @RequestMapping("/getInventorySearchProductList")
    public String getInventorySearchProductList(HttpServletRequest request,
                                                @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        request.setAttribute("goodsInfoName",goodsInfoName);
        request.setAttribute("pageBean", inventorySearchProductService.getSearchProductBygoodsName(goodsInfoName, new Pageable(page, size)));
        return "main/pro_es_list";
    }

    /**
     * 手机搜索商品
     * @param goodsInfoName 商品名称
     * */
    @RequestMapping(value = "/getInventorySearchProductListAjax",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getInventorySearchProductListAjax(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        Page pageBean = inventorySearchProductService.getSearchProductBygoodsName(goodsInfoName, new Pageable(page, size));
        return new JsonResponseBean(pageBean).toJson();
    }

    /**
     * 获取供应商某商品的库存
     * @param productNo 货品编号
     * @param thirdId 供应商ID
     * @return
     */
    public String getStockByThirdId(@RequestParam(value = "productNo") String productNo,
                                @RequestParam(value = "thirdId") long thirdId){

        ThirdPlatformEntity thirdPlatformEntity = thirdPlatformMapper.queryByStoreId(thirdId);
        if(thirdPlatformEntity.getPlatformType().equals("1")) {
            ThirdPlatformInterfaceEntity item = thirdPlatformInterfaceService.queryByStoreId(thirdId);
            Assert.notNull(item);
            return new JsonResponseBean(inventorySearchProductService.getStockByProdNo(item.getInventoryQuery(), item.getMerchantId(), item.getMerchantKey(), productNo)).toJson();
        }
        return JsonResponseBean.getErrorResponse(ErrorCode.AUTHENTICATE_FAILURE,"不属于抛单类型的商品").toJson();
    }
}
