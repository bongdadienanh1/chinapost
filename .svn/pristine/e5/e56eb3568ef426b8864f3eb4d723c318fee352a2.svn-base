package com.ylife.product.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/27.
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Integer goodsInfoType, Boolean online, Boolean valetShow,String auditStatus, Pageable pageable) {
        List<GoodsManagerResult> goodsManagerResults = goodsInfoMapper.selectGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, goodsInfoType, online, valetShow,auditStatus, pageable);
        int totalElements = goodsInfoMapper.countGoodsResult(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, goodsInfoType, online, valetShow,auditStatus);
        Page<GoodsManagerResult> page = new PageImpl<>(pageable, totalElements, goodsManagerResults);
        return page;
    }

    @Override
    public Page<GoodsManagerResult> getSettleGoods(List<Long> enterpriseIds, Boolean onlineShow, Boolean valetShow, String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Pageable pageable) {
        List<GoodsManagerResult> goodsManagerResultList = goodsInfoMapper.selectSettleGoods(enterpriseIds, onlineShow, valetShow, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, null, null, pageable);
        int totalElements = goodsInfoMapper.countSettleGoods(enterpriseIds, onlineShow, valetShow, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, null, null);
        Page<GoodsManagerResult> goodsManagerResultPage = new PageImpl<>(pageable, totalElements, goodsManagerResultList);
        return goodsManagerResultPage;
    }

    @Override
    public Page<GoodsManagerResult> getSettleGoodsByEnterpriseId(Long maxCatalog, Long minCatalog, String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Pageable pageable) {

        List<GoodsManagerResult> goodsManagerResultList = goodsInfoMapper.selectSettleGoodsByEnterpriseId(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, pageable);
        int totalElements = goodsInfoMapper.countSettleGoodsByEnterpriseId(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId);
        Page<GoodsManagerResult> goodsManagerResultPage = new PageImpl<>(pageable, totalElements, goodsManagerResultList);
        return goodsManagerResultPage;
    }

    @Override
    public GoodsInfo getById(Long goodsInfoId) {
        return goodsInfoMapper.selectByPrimaryKey(goodsInfoId);
    }

    @Override
    public GoodsManagerResult getGoodsInfoByGoodsNumber(String goodsInfoItemNo) {
        return goodsInfoMapper.selectGoodsInfoByGoodsNumber(goodsInfoItemNo);
    }

    /* @Override
    public Page<GoodsManagerResult> getValetGoods(String goodsInfoName, Float lowPrice, Float highPrice) {
        List<GoodsManagerResult> goodsManagerResults=goodsInfoMapper.selectValetGoods(goodsInfoName,lowPrice,highPrice);
        int totalElements=goodsInfoMapper.countValeGoodsResult(lowPrice,highPrice,goodsInfoName);
        Page<GoodsManagerResult> page=new PageImpl<>(pageable,totalElements,goodsManagerResults);
        return page;
    }*/

    @Deprecated
    private List<GoodsManagerResult> removeRepeate(List<GoodsManagerResult> listin) {
        Map<Long, GoodsManagerResult> resultMap = new HashMap<>();
        for (GoodsManagerResult result : listin) {
            GoodsManagerResult goodsResult = resultMap.get(result.getGoodsInfoId());
            if (goodsResult != null) {
                goodsResult.setSpecString(goodsResult.getSpecString() + "," + result.getGoodsSpecification() + ":" + result.getGoodsSpecValue());
            } else {
                result.setSpecString(result.getGoodsSpecification() + ":" + result.getGoodsSpecValue());
                result.setGoodsSpecification(null);
                result.setGoodsSpecValue(null);
                resultMap.put(result.getGoodsInfoId(), result);
            }
        }
        List<GoodsManagerResult> listout = new ArrayList<>();
        for (GoodsManagerResult result : resultMap.values()) {
            listout.add(result);
        }
        return listout;
    }

    @Override
    public List<GoodsManagerResult> getBrands() {
        return goodsInfoMapper.selectBrand();
    }

    @Override
    public List<GoodsManagerResult> getType() {
        return goodsInfoMapper.selectType();
    }

    @Override
    public List<GoodsManagerResult> getThirdName(String thirdName) {
        return goodsInfoMapper.selectThirdName(thirdName);
    }

    @Override
    public List<GoodsManagerResult> getThirdEnterprisenName(String thirdName) {
        return goodsInfoMapper.selectThirdEnterpriseName(thirdName);
    }

    @Override
    public List<GoodsManagerResult> getBrandTypeThird() {
        return goodsInfoMapper.selectBrandTypeThird();
    }

    @Override
    public int unshelves(long goodsInfoId) {
        return goodsInfoMapper.unshelves(goodsInfoId);
    }

    @Override
    public int shelves(long goodsInfoId) {
        return goodsInfoMapper.shelves(goodsInfoId);
    }

    @Override
    public int updateShow(Boolean onlineShow, Boolean valetShow, long goodsInfoId) {
        GoodsInfo goodsInfo = new GoodsInfo();
        goodsInfo.setGoodsInfoId(goodsInfoId);
        goodsInfo.setOnlineShow(onlineShow);
        goodsInfo.setValetShow(valetShow);
        return goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
    }

    /**
     * 批量修改上下架
     *
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    @Override
    public int batchUpdateGoodsInfoAdded(String[] goodsInfoIds, String goodsInfoAdded) {
        return goodsInfoMapper.batchUpdateGoodsInfoAdded(goodsInfoIds,goodsInfoAdded);
    }

    @Override
    public int deleteGoodsInfo(String[] thirdIds) {
        return goodsInfoMapper.deleteGoodsInfo(thirdIds);
    }

}
