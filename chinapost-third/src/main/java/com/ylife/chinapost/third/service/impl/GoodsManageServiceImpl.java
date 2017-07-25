package com.ylife.chinapost.third.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.third.controller.utils.Constants;
import com.ylife.chinapost.third.service.CurrentLoginService;
import com.ylife.chinapost.third.service.GoodsManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.mapper.GoodsImageMapper;
import com.ylife.goods.mapper.GoodsProductMapper;
import com.ylife.goods.model.*;
import com.ylife.goods.service.GoodsProductReleSpecService;
import com.ylife.goods.service.GoodsService;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.Assert;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by XuKai on 2016/4/28.
 */
@Service
public class GoodsManageServiceImpl implements GoodsManageService {

    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;
    @Resource
    private GoodsService goodsService;
    @Resource
    private CurrentLoginService currentLoginService;
    // 货品Service
    @Resource
    private GoodsProductMapper goodsProductMapper;
    // 货品关联规格值Service
    @Resource
    private GoodsProductReleSpecService goodsProductReleSpecService;
    @Resource
    private GoodsImageMapper goodsImageMapper;


    public Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded,
                                             Long brandId, Long typeId, Long thirdId, Integer goodsInfoType, Boolean online, Boolean valetShow,String auditStatus, Pageable pageable) {
        return goodsInfoService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, goodsInfoType, online, valetShow,auditStatus, pageable);
    }



    /**
     * 保存商品
     *
     * @param jsonData
     */
    @Transactional
    public void saveGoods(String jsonData, String goodsDetailDesc) {
        Goods goods;
        try {
            goods = Constants.SIMPLE_PARSER.parseJSON(jsonData, new TypeToken<Goods>() {
            });
        } catch (NumberFormatException ex) {
            throw new UserOperationException("参数错误");
        } catch (Exception ex) {
            throw new UserOperationException("参数错误");
        }
        Assert.notNull(goods);
        //1、保存商品
        goods.setGoodsDetailDesc(goodsDetailDesc);
        goods.setMobileDesc(goodsDetailDesc);
        goods.setIsThird("1");
        goods.setGoodsBelo(currentLoginService.getCurrentLoginThirdId());
        goods.setGoodsBeloName(currentLoginService.getCurrentStoreName());
        goods.setGoodsImg(goods.getGoodsInfos().get(0).getInfoImages().get(0).getImageInName());
        goods.setGoodsInfoItemNo(goods.getGoodsInfos().get(0).getGoodsInfoItemNo());
        goods.setGoodsDelflag("0");
        goods.setGoodsCreateTime(new Date());
        goods.setGoodsPrice(goods.getGoodsInfos().get(0).getGoodsInfoPreferPrice());
        goodsService.saveGoods(goods);


        //2、保存系统规格 及商品规格
        List<List<GoodsSpecDetail>> goodsSpecDetails = new ArrayList<List<GoodsSpecDetail>>();
        for (GoodsSpec spec : goods.getSpecs()) {
            goodsSpecDetails.add(spec.getDetails());
        }

        List<List<GoodsProductReleSpec>> result = new ArrayList<List<GoodsProductReleSpec>>();
        result = getAllGoodsSpecDetail(goodsSpecDetails, goodsSpecDetails.get(0), new ArrayList<GoodsSpecDetail>(), result);

        //3、保存货品、规格、图片、网点货品关系
        for (GoodsProduct goodsProduct : goods.getGoodsInfos()) {
            goodsProduct.setGoodsId(goods.getGoodsId());
//            goodsProduct.setGoodsInfoStock(0L);
            goodsProduct.setGoodsInfoDelflag("0");
            goodsProduct.setGoodsInfoCreateTime(new Date());
            goodsProduct.setOnlineShow(true);
            goodsProduct.setValetShow(true);
            goodsProduct.setGoodsInfoType(2);
            goodsProduct.setGoodsInfoAdded(goods.getGoodsAdded());
            goodsProduct.setGoodsInfoImgId(goodsProduct.getInfoImages().get(0).getImageInName());
            goodsProduct.setGoodsInfoUnit(goods.getGoodsInfoUnit());
            goodsProduct.setThirdId(currentLoginService.getCurrentLoginThirdId());
            goodsProduct.setThirdName(currentLoginService.getCurrentStoreName());
            goodsProduct.setAuditStatus("0");
            try {
                goodsProductMapper.insertSelective(goodsProduct);
            }catch (DuplicateKeyException e) {
                throw new UserOperationException("货品编号不能重复！");
            }
            //图片
            for (GoodsImage goodsImage : goodsProduct.getInfoImages()) {
                goodsImage.setGoodsInfoId(goodsProduct.getGoodsInfoId());
                goodsImage.setImageBigName(goodsImage.getImageInName());
                goodsImage.setImageThumName(goodsImage.getImageInName());
                goodsImage.setGoodsImgDelflag("0");
                goodsImageMapper.insertSelective(goodsImage);
            }

            //规格
            for (int i = 0; i < result.get(goods.getGoodsInfos().indexOf(goodsProduct)).size(); i++) {
                this.goodsProductReleSpecService.saveProductReleSpec(goodsProduct.getGoodsInfoId(), result.get(goods.getGoodsInfos().indexOf(goodsProduct)).get(i).getSpecId(), result.get(goods.getGoodsInfos().indexOf(goodsProduct)).get(i).getSpecDetailId(), result.get(goods.getGoodsInfos().indexOf(goodsProduct)).get(i).getSpecValueRemark(),
                        "");
            }
        }
    }


    /**
     * 删除重复元素
     *
     * @param list
     */
    public static void removeDuplicateWithOrder(List<GoodsSpecDetail> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).getSpecId().equals(list.get(i).getSpecId())) {
                    list.remove(j);
                }
            }
        }
    }

    /**
     * 递归生成货品规格
     *
     * @param list
     * @param arr
     * @param items
     * @param result
     * @return
     */
    public List<List<GoodsProductReleSpec>> getAllGoodsSpecDetail(List<List<GoodsSpecDetail>> list, List<GoodsSpecDetail> arr, List<GoodsSpecDetail> items, List<List<GoodsProductReleSpec>> result) {
        for (int i = 0; i < list.size(); i++) {
            //取得当前的数组
            if (i == list.indexOf(arr)) {
                //迭代数组
                for (GoodsSpecDetail st : arr) {
                    items.add(st);
                    if (i < list.size() - 1) {
                        getAllGoodsSpecDetail(list, list.get(i + 1), items, result);
                    } else if (i == list.size() - 1) {

                        if (items.size() > list.size()) {
                            Collections.reverse(items);
                            removeDuplicateWithOrder(items);
                        }

                        List<GoodsProductReleSpec> resultItem = new ArrayList<GoodsProductReleSpec>();
                        for (GoodsSpecDetail goodsSpecDetail : items) {
                            GoodsProductReleSpec goodsProductReleSpec = new GoodsProductReleSpec();
                            goodsProductReleSpec.setSpecId(goodsSpecDetail.getSpecId());
                            goodsProductReleSpec.setSpecDetailId(goodsSpecDetail.getSpecDetailId());
                            goodsProductReleSpec.setSpecValueRemark(goodsSpecDetail.getSpecDetailName());
                            resultItem.add(goodsProductReleSpec);
                        }
                        result.add(resultItem);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 修改货品时加载信息
     *
     * @param goodsInfoId
     * @return
     */
    public GoodsInfo getGoodsInfoById(long goodsInfoId) {
        return goodsInfoMapper.selectByGoodsINfoIdAndEnterpriseId(goodsInfoId, null);
    }

    /**
     * 修改商品及货品
     *
     * @param jsonData
     */
    @Transactional
    public void updateGoods(String jsonData, String goodsDetailDesc) {
        //数据
        Goods goods;
        try {
            goods = Constants.SIMPLE_PARSER.parseJSON(jsonData, new TypeToken<Goods>() {
            });
        } catch (NumberFormatException ex) {
            throw new UserOperationException("参数错误");
        } catch (Exception ex) {
            throw new UserOperationException("参数错误");
        }
        Assert.notNull(goods);
        if (goods.getGoodsInfos().get(0).getGoodsInfoPreferPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new UserOperationException("商城价为非负数");
        }
        if (goods.getGoodsInfos().get(0).getGoodsInfoSettlePrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new UserOperationException("结算价为非负数");
        }

        //1、修改商品
        goods.setGoodsDetailDesc(goodsDetailDesc);
        goods.setMobileDesc(goodsDetailDesc);
        goods.setGoodsDelflag("0");
        goodsService.updateGoods(goods);

        //2、修改货品、规格、图片、网点货品关系
        for (GoodsProduct goodsProduct : goods.getGoodsInfos()) {
            goodsProduct.setGoodsInfoImgId(goodsProduct.getInfoImages().get(0).getImageInName());
            goodsProduct.setGoodsInfoUnit(goods.getGoodsInfoUnit());
            //设置货品为待审核状态
            goodsProduct.setAuditStatus("0");
            goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);

            //图片
            goodsImageMapper.deleteByGoodsInfoId(goodsProduct.getGoodsInfoId());
            for (GoodsImage goodsImage : goodsProduct.getInfoImages()) {
                goodsImage.setGoodsInfoId(goodsProduct.getGoodsInfoId());
                goodsImage.setImageBigName(goodsImage.getImageInName());
                goodsImage.setImageThumName(goodsImage.getImageInName());
                goodsImage.setGoodsImgDelflag("0");
                goodsImageMapper.insertSelective(goodsImage);
            }
        }
    }

    /**
     * 批量上下架
     *
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    @Override
    public int batchUpdateGoodsInfoAdded(String goodsInfoIds, String goodsInfoAdded) {
        return goodsInfoService.batchUpdateGoodsInfoAdded(goodsInfoIds.split(","),goodsInfoAdded);
    }

    /**
     * 修改货品库存
     * @param goodsInfoId 货品id
     * @param goodsInfoStock
     * */
    @Override
    public int updateStockByGoodsInfoId(Long goodsInfoId,Long goodsInfoStock) {
        GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(goodsInfoId);
        goodsProduct.setGoodsInfoStock(goodsInfoStock);
        return goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
    }

}