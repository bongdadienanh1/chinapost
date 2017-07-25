package com.ylife.chinapost.third.controller;


import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.third.controller.utils.Constants;
import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.chinapost.third.service.GoodsManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserNotLoginException;
import com.ylife.goods.model.*;
import com.ylife.goods.service.GoodsBrandService;
import com.ylife.goods.service.GoodsCateService;
import com.ylife.goods.service.GoodsSpecDetailService;
import com.ylife.goods.service.GoodsSpecService;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by XuKai on 2016/4/27.
 * 商品管理控制器
 */
@Controller
@RequestMapping(value = "/goodsManager")
public class GoodsManagerAPIController {

    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private GoodsCateService goodsCateService;
    @Resource
    private GoodsBrandService goodsBrandService;
    @Resource
    private GoodsSpecService goodsSpecService;
    @Resource
    private GoodsSpecDetailService goodsSpecDetailService;
    @Resource
    private CurrentLoginService currentLoginService;

    private Generator generator = IdGeneratorFactory.create("Goods_Info_Code", IdGeneratorFactory.SPartSize.TWO, IdGeneratorFactory.NPartSize.ONE);

    @ExceptionHandler(UserNotLoginException.class)
    public String handlerUserNotLoginException() {
        return "redirect:/login";
    }

    @RequestMapping("/getGoodsInfo")
    public String getGoodsInfo(HttpServletRequest request,@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                               @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                               @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                               @RequestParam(value = "brandId", required = false) Long brandId,
                               @RequestParam(value = "typeId", required = false) Long typeId,
                               @RequestParam(value = "onlineShow", required = false) Boolean onlineShow,
                               @RequestParam(value = "valetShow",required = false)Boolean valetShow,
                               @RequestParam(value = "auditStatus",required = false)String auditStatus,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                               @RequestParam(value = "type", required = false) String type) {
        String url;
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);
        if(!"".equals(type) && type !=null){
            auditStatus = "1";
            url= "thirdProductAdded";
            request.setAttribute("twoNav", '3');
        }else {
            auditStatus = Constants.nullOrNotBlank(auditStatus);
            url = "thirdProductManager";
            request.setAttribute("twoNav", '2');
        }
        Page<GoodsManagerResult> goodsManagerResultPage = goodsManageService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded,brandId, typeId, currentLoginService.getCurrentLoginThirdId(),2, onlineShow,valetShow,auditStatus,new Pageable(page, size));
        request.setAttribute("goodsInfoName",goodsInfoName);
        request.setAttribute("goodsInfoItemNo",goodsInfoItemNo);
        request.setAttribute("goodsInfoAdded",goodsInfoAdded);
        request.setAttribute("brandId",brandId);
        request.setAttribute("auditStatus",auditStatus);
        request.setAttribute("pageBean",goodsManagerResultPage);
        request.setAttribute("firstNav", '1');
        request.setAttribute("VERSION", Constants.JS_VERSION);
        return url;
    }

    /**
     * 根据父分类ID查询子分类集合
     *
     * @param catId
     *            父分类ID {@link Long}
     * @param cateName
     *            分类名称
     * @return 子分类集合{@link List}
     */
    @RequestMapping(value ="/queryCateByCatIdAndName", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String querySonCateByParentIdAndName(Long catId, String cateName) {
        GoodsCate goodsCate = new GoodsCate();
        goodsCate.setCatParentId(catId);
        if(!StringUtils.isBlank(cateName)) {
            goodsCate.setCatName(cateName);
        }
        goodsCate.setGoodsInfoType("1");
        List<GoodsCateVo> cateVoList = goodsCateService.queryAllCateByParentIdAndLikeName(goodsCate);
        return new JsonResponseBean(cateVoList).toJson();
    }


    /**
     * 获取所有品牌
     * @return
     */
    @RequestMapping(value = "/queryAllBrands", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllBrands(){
        return new JsonResponseBean(goodsBrandService.queryAllBrand("1")).toJson();
    }

    /**
     * 根据品牌名称查询品牌信息
     * @param brandName
     * @return
     */
    @RequestMapping("/queryBrandsByName")
    @ResponseBody
    public String getBrandsByName(String brandName){
        return new JsonResponseBean(goodsBrandService.queryallbrandbyName(brandName)).toJson();
    }

    /**
     * 获取所有的规格信息
     * @return
     */
    @RequestMapping(value = "/queryAllSpec", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllSpec(){
        return new JsonResponseBean(goodsSpecService.queryAllSpec("1")).toJson();
    }

    /**
     * 获取所有的规格值信息
     * @return
     */
    @RequestMapping(value = "/queryAllSpecDetail", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllSpecDetail(long specId){
        return new JsonResponseBean(goodsSpecDetailService.queryBySpecId(specId)).toJson();
    }

    /**
     * 新流程添加商品
     *
     */
    @RequestMapping(value = "/newUploadGood", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String newUploadGood(String goodsJson,String goodsDetailDesc){
        this.goodsManageService.saveGoods(goodsJson,goodsDetailDesc);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改货品
     *
     */
    @RequestMapping(value = "/updateGoodInfo", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateGoodInfo(String goodsJson,String goodsDetailDesc){
        this.goodsManageService.updateGoods(goodsJson,goodsDetailDesc);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改货品库存
     * @param goodsInfoId 货品id
     * @param goodsInfoStock 货品库存
     */
    @RequestMapping(value = "/updateStockByGoodsInfoId", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateStockByGoodsInfoId(Long goodsInfoId,Long goodsInfoStock){
        this.goodsManageService.updateStockByGoodsInfoId(goodsInfoId,goodsInfoStock);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
    /**
     * 根据货品ID获取货品信息
     */
    @RequestMapping(value = "/getGoodsInfoById", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getGoodsInfoById(@RequestParam(value = "id", required = true) Long id) {
        return new JsonResponseBean(goodsManageService.getGoodsInfoById(id)).toJson();
    }

    /**
     * 生成货品编号
     *
     * @param count 生成
     */
    @RequestMapping(value = "/createGenerator", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String createGenerator(int count) {
        String[] str = new String[count];
        for (int i=0;i<count;i++){
            str[i]= String.valueOf(generator.generate());
        }
        return new JsonResponseBean(str).toJson();
    }

    /**
     * 组合规格值
     * @param data
     * @return
     */
    @RequestMapping(value="getCombineSpecDetail",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getCombineSpecDetail(String data){
        List<GoodsSpec> goodsSpecs = Constants.SIMPLE_PARSER.parseJSON(data, new TypeToken<List<GoodsSpec>>() {
        });
        List<List<GoodsSpecDetail>> goodsSpecDetails = new ArrayList<List<GoodsSpecDetail>>();
        for (GoodsSpec spec : goodsSpecs) {
            goodsSpecDetails.add(spec.getDetails());
        }
        List<List<GoodsProductReleSpec>> result = new ArrayList<List<GoodsProductReleSpec>>();
        result = goodsManageService.getAllGoodsSpecDetail(goodsSpecDetails, goodsSpecDetails.get(0), new ArrayList<GoodsSpecDetail>(), result);
        StringBuilder str = new StringBuilder();
        for(List<GoodsProductReleSpec> releSpecs :result){
            StringBuilder combineSpecDetail = new StringBuilder();
            for(GoodsProductReleSpec item :releSpecs){
                combineSpecDetail.append(item.getSpecValueRemark()).append("/");
            }
            combineSpecDetail.deleteCharAt(combineSpecDetail.length()-1);
            str.append(combineSpecDetail).append(",");
        }
        return new JsonResponseBean(str.substring(0,str.length()-1)).toJson();
    }


    /**
     * 批量上下架
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    @RequestMapping(value = "/batchUpdateGoodsInfoAdded", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String batchUpdateGoodsInfoAdded(String goodsInfoIds, String goodsInfoAdded) {
        goodsManageService.batchUpdateGoodsInfoAdded(goodsInfoIds,goodsInfoAdded);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
}