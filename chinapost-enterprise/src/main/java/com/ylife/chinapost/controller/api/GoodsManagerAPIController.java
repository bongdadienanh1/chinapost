package com.ylife.chinapost.controller.api;


import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.GoodsManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.goods.model.GoodsCateVo;
import com.ylife.goods.service.GoodsBrandService;
import com.ylife.goods.service.GoodsCateService;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.security.annotation.SecurityResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by XuKai on 2016/4/27.
 * 商品管理控制器
 */
@Controller
@SecurityResource(parent = "/web/itemManager", primary = false)
@RequestMapping(value = "/web/api/goodsManager", produces = "application/json;charset=utf-8")
public class GoodsManagerAPIController {

    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private GoodsCateService goodsCateService;
    @Resource
    private GoodsBrandService goodsBrandService;

    @RequestMapping("/getGoodsInfo")
    @ResponseBody
    public String getGoodsInfo(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                               @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                               @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                               @RequestParam(value = "brandId", required = false) Long brandId,
                               @RequestParam(value = "typeId", required = false) Long typeId,
                               @RequestParam(value = "thirdId", required = false) Long thirdId,
                               @RequestParam(value = "onlineShow", required = false) Boolean onlineShow,
                               @RequestParam(value = "valetShow",required = false)Boolean valetShow,
                               @RequestParam(value = "goodsInfoType",required = false)Integer goodsInfoType,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);
        Page<GoodsManagerResult> goodsManagerResultPage = goodsManageService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded,brandId, typeId, thirdId,0, onlineShow,valetShow,new Pageable(page, size));
        return new JsonResponseBean(goodsManagerResultPage).toJson();
    }


    @RequestMapping("/unshelves")
    @ResponseBody
    public String unshelves(@RequestParam(value = "goodsInfoId", required = false) long goodsInfoId) {
        goodsInfoService.unshelves(goodsInfoId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/shelves")
    @ResponseBody
    public String shelves(@RequestParam(value = "goodsInfoId", required = false) long goodsInfoId) {
        goodsInfoService.shelves(goodsInfoId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/updateShow")
    @ResponseBody
    public String updateShow(@RequestParam(value = "valetShow",required = false)Boolean valteShow,
                             @RequestParam(value = "onlineShow",required = false)Boolean onlineShow,
                             @RequestParam(value = "goodsInfoId",required = false)long goodsInfoId ){
        goodsInfoService.updateShow(onlineShow,valteShow,goodsInfoId);
        return JsonResponseBean.getSuccessResponse().toJson();
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
    @RequestMapping("/queryCateByCatIdAndName")
    @ResponseBody
    public String querySonCateByParentIdAndName(Long catId, String cateName) {
        List<GoodsCateVo> cateVoList = goodsCateService.querySonCateVoByParentIdAndName(catId, cateName);
        return new JsonResponseBean(cateVoList).toJson();
    }


    @RequestMapping("/queryAllBrands")
    @ResponseBody
    public String getAllBrands(){
        return new JsonResponseBean(goodsBrandService.queryAllBrand()).toJson();
    }

    @RequestMapping("/queryBrandsByName")
    @ResponseBody
    public String getBrandsByName(String brandName){
        return new JsonResponseBean(goodsBrandService.queryallbrandbyName(brandName)).toJson();
    }

    /**
     * 新流程添加商品
     *
     */
    @RequestMapping("/newUploadGood")
    @ResponseBody
    public String newUploadGood(String goodsJson,String goodsDetailDesc){
        this.goodsManageService.saveGoods(goodsJson,goodsDetailDesc);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 修改货品
     *
     */
    @RequestMapping("/updateGoodInfo")
    @ResponseBody
    public String updateGoodInfo(String goodsJson,String goodsDetailDesc){
        this.goodsManageService.updateGoods(goodsJson,goodsDetailDesc);
        return JsonResponseBean.getSuccessResponse().toJson();
    }


    /**
     * 获取我的自购商品
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param onlineShow
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/getSettleGoods")
    @ResponseBody
    public String getSettleGoods(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                 @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                                 @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                                 @RequestParam(value = "brandId", required = false) Long brandId,
                                 @RequestParam(value = "typeId", required = false) Long typeId,
                                 @RequestParam(value = "thirdId", required = false) Long thirdId,
                                 @RequestParam(value = "onlineShow", required = false) Boolean onlineShow,
                                 @RequestParam(value = "valetShow",required = false)Boolean valetShow,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") int size){
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);
        Page<GoodsManagerResult> goodsManagerResultPage = goodsManageService.getSettleGoods(onlineShow, valetShow, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(goodsManagerResultPage).toJson();
    }


    /**
     * 根据节点id查询该节点权限内所有自购商品
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/getSettleGoodsByEnterpriseId")
    @ResponseBody
    public String getSettleGoodsByEnterpriseId(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                               @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                               @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                                               @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                                               @RequestParam(value = "brandId", required = false) Long brandId,
                                               @RequestParam(value = "typeId", required = false) Long typeId,
                                               @RequestParam(value = "thirdId", required = false) Long thirdId,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);
        Page<GoodsManagerResult> goodsManagerResultPage=goodsManageService.getSettleGoodsByEnterpriseId(enterpriseId,goodsInfoName,goodsInfoItemNo,goodsInfoAdded,brandId,typeId,thirdId,new Pageable(page,size));
        return new JsonResponseBean(goodsManagerResultPage).toJson();
    }


    //集采的商家
    @RequestMapping("/getThirdName")
    @ResponseBody
    public  String getThirdName(@RequestParam(value = "thirdName",required = false)String thirdName){
        thirdName=Constants.nullOrNotBlank(thirdName);
        List<GoodsManagerResult> results= goodsManageService.selectThirdName(thirdName);
        return new JsonResponseBean(results).toJson();
    }

    //自购的商家
    @RequestMapping("/getThirdEnterpriseName")
    @ResponseBody
    public  String getThirdEnterpriseName(@RequestParam(value = "thirdName",required = false)String thirdName){
        thirdName=Constants.nullOrNotBlank(thirdName);
        List<GoodsManagerResult> results= goodsManageService.selectThirdEnterpriseName(thirdName);
        return new JsonResponseBean(results).toJson();
    }






}
