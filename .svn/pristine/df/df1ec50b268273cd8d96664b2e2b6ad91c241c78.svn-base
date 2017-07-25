package com.ylife.chinapost.boss.controller;

import com.alibaba.fastjson.JSON;
import com.ylife.chinapost.boss.controller.utils.Constants;
import com.ylife.goods.model.GoodsCate;
import com.ylife.goods.service.GoodsCateService;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2017/7/3.
 *
 *商品分类
 */
@Controller
@RequestMapping(value = "/GoodsCategoryAdmin")
public class GoodsCateController {
    //注入service
    @Resource
    private GoodsCateService goodsCateService;

    /**
     * String flag =1 显示为第三方管理下的列表
     * 商品分类列表的操作
     * @return
     */
    @RequestMapping(value = "ListPage")
    public ModelAndView goodsCateListPage(){
        return new ModelAndView(returnPath("GoodsCate")).addObject("VERSION", Constants.JS_VERSION);
    }

    /**
     * ajax 查询商品分类列表
     * @param goodsCate
     * @return
     */
    @RequestMapping(value = "/querySonCateByCatIdAndName", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryAllCateByParentIdAndLikeName(GoodsCate goodsCate){
        goodsCate.setGoodsInfoType("1");
        return JSON.toJSONString(goodsCateService.queryAllCateByParentIdAndLikeName(goodsCate));
    }

    /**
     * 检查商品分类名称是否重复
     * @param catName
     * @return
     */
    @RequestMapping(value = "/checkGoodsCateName", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String checkGoodsCateName(String catName){
        if(!StringUtil.isBlank(catName)) {
            return goodsCateService.checkGoodsCateName(catName) > 0 ? "error" : "success";
        }else{
            return "error";
        }
    }

    /**
     * 对商品分类的添加，编辑，删除
     * @param goodsCate
     * @return
     *
     *
     */
    @RequestMapping(value = "/addGoodsCate", produces = "application/text;charset=utf-8")
    @ResponseBody
    public  String addGoodsCate(GoodsCate goodsCate) {
        int result = 0;
        if (goodsCate !=null){
            if (goodsCate.getCatId() != null){ //说明存在
                result = goodsCateService.updateByPrimaryKeySelective(goodsCate);
            }else{
                //不存在则添加
                goodsCate.setCatDelflag("0");//0未删除
                goodsCate.setGoodsInfoType("1");//设置货品类型(0表示自购商品，1表示线上商品)
                result = goodsCateService.insertSelective(goodsCate);
            }
        }
        return result > 0 ? "success" : "error";
    }

    /**
     * 按主键查询 返回该对象
     * @param catId
     * @return
     */
    @RequestMapping(value = "/queryById", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String queryById(Long catId){
        return JSON.toJSONString(goodsCateService.queryById(catId));
    }

    /**
     * 根据catId 检查是否存在 子类
     * @param catId
     * @return
     */
    @RequestMapping(value = "/selectSonCountByParentId", produces = "application/text;charset=utf-8")
    @ResponseBody
    public String selectSonCountByParentId(Long catId){
        return goodsCateService.selectSonCountByParentId(catId) > 0 ? "success" : "error";
    }







    /**
     * 定义路径
     *
     */
    private final String MODULE_PATH = "/thirdPlatform/";

    //返回路径
    private String returnPath(String jspName) {
        return MODULE_PATH + jspName;
    }


}
