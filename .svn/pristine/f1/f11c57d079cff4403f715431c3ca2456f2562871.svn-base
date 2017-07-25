package com.ylife.chinapost.mobile.controller;

import com.ylife.goods.model.GoodsDetailBean;
import com.ylife.goods.model.GoodsImage;
import com.ylife.goods.service.GoodsProductService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
                        if ("1".equals(imageList.get(i).getGoodsImgSort().toString())) {
                            GoodsImage go = imageList.get(0);
                            imageList.set(0, imageList.get(i));
                            imageList.set(i, go);
                        }
                    }
                    detailBean.getProductVo().setImageList(imageList);
                }

                //处理商品详情
                String mobileDesc = detailBean.getProductVo().getGoods().getMobileDesc();
                while (mobileDesc.indexOf("width") != -1) {
                    mobileDesc = mobileDesc.substring(0, mobileDesc.indexOf("width")) + mobileDesc.substring(mobileDesc.indexOf("width") + 6);
                }
                // 将图片路径中的单引号转换为双引号
                detailBean.getProductVo().getGoods().setMobileDesc(mobileDesc.replaceAll("\'", "\""));
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

}
