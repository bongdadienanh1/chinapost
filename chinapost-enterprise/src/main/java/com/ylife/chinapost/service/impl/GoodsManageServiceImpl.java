package com.ylife.chinapost.service.impl;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.GoodsManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.mapper.*;
import com.ylife.goods.model.*;
import com.ylife.goods.service.*;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.mapper.GoodsInfoEnterpriseMapper;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.model.GoodsInfoEnterpriseKey;
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
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;
    @Resource
    EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private GoodsInfoEnterpriseMapper goodsInfoEnterpriseMapper;
    // 货品Service
    @Resource
    private GoodsProductMapper goodsProductMapper;
    // 商品规格值Service
    @Resource
    private GoodsSpecMapper goodsSpecMapper;
    @Resource
    private GoodsSpecDetailMapper goodsSpecDetailMapper;
    @Resource
    private GoodsTypeParamMapper goodsTypeParamMapper;
    // 保存开启规格的Service
    @Resource
    private GoodsOpenSpecService goodsOpenSpecService;
    // 商品开启规格值Service
    @Resource
    private GoodsOpenSpecValueService goodsOpenSpecValueService;
    // 货品关联规格值Service
    @Resource
    private GoodsProductReleSpecService goodsProductReleSpecService;
    @Resource
    private GoodsImageMapper goodsImageMapper;
    @Resource
    private GoodsReleParamService goodsReleParamService;


    @Override
    public Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded,
                                             Long brandId, Long typeId, Long thirdId, Integer goodsInfoType, Boolean online, Boolean valetShow, Pageable pageable) {
        return goodsInfoService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, goodsInfoType, online, valetShow,null, pageable);
    }

    @Override
    public Page<GoodsManagerResult> getSettleGoods(Boolean onlineShow, Boolean valetShow, String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Pageable pageable) {
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        List<Long> ids = new ArrayList<>();
        ids.add(enterpriseId);
        return goodsInfoService.getSettleGoods(ids, true, true, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, pageable);
    }

 /*   //获取最底级除掉顶级以外所有上级节点id（包括最底级自身的id）
    public List<Long> getNodeId(List<Long> ids,Long id){
        EnterpriseFunction function=enterpriseFunctionMapper.selectByPrimaryKey(id);
        Long parenId=function.getParentId();
        if(parenId==1){
           return ids ;
        }else {
            ids.add(parenId);
            return getNodeId(ids,parenId);
        }
    }*/

    @Override
    public Page<GoodsManagerResult> getSettleGoodsByEnterpriseId(Long enterpriseId, String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Pageable pageable) {
        Page<GoodsManagerResult> goodsManagerResultPage = null;
        List<Long> enterpriseIds = new ArrayList<>();
        if (enterpriseId == null) {
            enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        }
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        if (function.getEnd()) {
            enterpriseIds.add(enterpriseId);
            enterpriseIds = currentLoginService.getNodeId(enterpriseIds, enterpriseId);
            goodsManagerResultPage = goodsInfoService.getSettleGoods(enterpriseIds, null, null, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, pageable);

        } else {
            Long maxCatalog = function.getMaxCatalog();
            Long minCatalog = function.getMinCatalog();
            goodsManagerResultPage = goodsInfoService.getSettleGoodsByEnterpriseId(maxCatalog, minCatalog, goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, pageable);
        }
        return goodsManagerResultPage;
    }

    @Override
    public List<GoodsManagerResult> selectBrand() {
        return goodsInfoMapper.selectBrand();
    }

    @Override
    public List<GoodsManagerResult> selectType() {
        return goodsInfoMapper.selectType();
    }

    @Override
    public List<GoodsManagerResult> selectThirdName(String thirdName) {
        return goodsInfoService.getThirdName(thirdName);
    }

    @Override
    public List<GoodsManagerResult> selectThirdEnterpriseName(String thirdName) {
        return goodsInfoService.getThirdEnterprisenName(thirdName);
    }

    @Override
    public List<GoodsManagerResult> getBrandTypeThird() {
        return null;
    }

    @Override
    public GoodsManagerResult getByGoodsInfoId(long goodsInfoId) {
        return goodsInfoMapper.selectByGoodsInfoId(goodsInfoId);
    }

    @Override
    public Boolean exportExcel() {
        return null;
    }

    /**
     * 保存商品
     *
     * @param jsonData
     */
    @Transactional
    @Override
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
        if (goods.getGoodsPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new UserOperationException("商城价为非负数");
        }
        //1、保存商品
        goods.setGoodsDetailDesc(goodsDetailDesc);
        goods.setMobileDesc(goodsDetailDesc);
        goods.setIsThird("0");
        goods.setGoodsBelo(0L);
        goods.setGoodsBeloName("BOSS");
        goods.setGoodsImg(goods.getGoodsInfos().get(0).getInfoImages().get(0).getImageInName());
        goods.setGoodsInfoItemNo(goods.getGoodsInfos().get(0).getGoodsInfoItemNo());
        goods.setGoodsDelflag("0");
        goods.setGoodsCreateTime(new Date());
        goodsService.saveGoods(goods);


        //2、保存系统规格 及商品规格
        List<List<GoodsSpecDetail>> goodsSpecDetails = new ArrayList<>();
        for (GoodsSpec spec : goods.getSpecs()) {
            spec.setSpecDelflag("0");
            goodsSpecMapper.insertSelective(spec);
            this.goodsOpenSpecService.saveOpenSpec(goods.getGoodsId(), spec.getSpecId());
            for (GoodsSpecDetail specDetail : spec.getDetails()) {
                specDetail.setSpecId(spec.getSpecId());
                specDetail.setSpecDetailDelflag("0");
                goodsSpecDetailMapper.insertSelective(specDetail);
                this.goodsOpenSpecValueService.saveOpenSpecVal(goods.getGoodsId(), spec.getSpecId(), specDetail.getSpecDetailId(), "", specDetail.getSpecDetailName());
            }
            goodsSpecDetails.add(spec.getDetails());
        }

        List<List<GoodsProductReleSpec>> result = new ArrayList<>();
        result = getAllGoodsSpecDetail(goodsSpecDetails, goodsSpecDetails.get(0), new ArrayList<GoodsSpecDetail>(), result);

        //3、保存扩展参数
        for (GoodsTypeParam typeParam : goods.getTypeSpecs()) {
            goodsTypeParamMapper.insertSelective(typeParam);
            goodsReleParamService.saveGoodsReleParam(goods.getGoodsId(), typeParam.getParamId(), typeParam.getParamValue(), "");
        }

        //4、保存货品、规格、图片、网点货品关系
        for (GoodsProduct goodsProduct : goods.getGoodsInfos()) {
            goodsProduct.setGoodsId(goods.getGoodsId());
            goodsProduct.setGoodsInfoStock(0L);
            goodsProduct.setGoodsInfoDelflag("0");
            goodsProduct.setGoodsInfoCreateTime(new Date());
            goodsProduct.setOnlineShow(true);
            goodsProduct.setValetShow(true);
            goodsProduct.setGoodsInfoType(1);
            goodsProduct.setGoodsInfoAdded(goods.getGoodsAdded());
            goodsProduct.setGoodsInfoImgId(goodsProduct.getInfoImages().get(0).getImageInName());
            goodsProduct.setGoodsInfoUnit(goods.getGoodsInfoUnit());
            goodsProduct.setGoodsInfoPack(goods.getGoodsInfoPack());
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

            //网点货品关系
            GoodsInfoEnterpriseKey item = new GoodsInfoEnterpriseKey(currentLoginService.getCurrentLoginEnterpriseId(), goodsProduct.getGoodsInfoId());
            goodsInfoEnterpriseMapper.insertSelective(item);
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
                if (list.get(j).getSpecId() == list.get(i).getSpecId()) {
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
    public static List<List<GoodsProductReleSpec>> getAllGoodsSpecDetail(List<List<GoodsSpecDetail>> list, List<GoodsSpecDetail> arr, List<GoodsSpecDetail> items, List<List<GoodsProductReleSpec>> result) {
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

                        List<GoodsProductReleSpec> resultItem = new ArrayList<>();
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
    @Override
    public GoodsInfo getGoodsInfoById(long goodsInfoId) {
        return goodsInfoMapper.selectByGoodsINfoIdAndEnterpriseId(goodsInfoId, null);
    }

    /**
     * 修改商品及货品
     *
     * @param jsonData
     */
    @Override
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

        //2、修改商品规格值
        for (GoodsSpec spec : goods.getSpecs()) {
            List<Object> goodsSpecDetails = goodsSpecDetailMapper.querySpecDeetailBySpecId(spec.getSpecId());

            for (GoodsSpecDetail specDetail : spec.getDetails()) {
                short flag = 0;
                for (Object sourceSpecDetail : goodsSpecDetails) {
                    if (specDetail.getSpecDetailName().equals(((GoodsSpecDetail) sourceSpecDetail).getSpecDetailName())) {
                        if (specDetail.getSpecDetailId().doubleValue() != ((GoodsSpecDetail) sourceSpecDetail).getSpecDetailId().doubleValue()) {
                            throw new UserOperationException(specDetail.getSpecDetailName() + "规格值已存在");
                        }
                        flag++;
                    }
                }
                if (flag == 0) {//新增规格值
                    specDetail.setSpecDetailId(null);
                    specDetail.setSpecId(spec.getSpecId());
                    specDetail.setSpecDetailDelflag("0");
                    goodsSpecDetailMapper.insertSelective(specDetail);
                    this.goodsOpenSpecValueService.saveOpenSpecVal(goods.getGoodsId(), spec.getSpecId(), specDetail.getSpecDetailId(), "", specDetail.getSpecDetailName());
                } else if (flag == 1) {//规格值没修改
                    break;
                } else {//新增规格值已存在
                    throw new UserOperationException(specDetail.getSpecDetailName() + "规格值已存在");
                }
            }
        }

        //3、修改扩展参数
        goodsReleParamService.delAllReleParamByGoodsId(goods.getGoodsId(), "");
        for (GoodsTypeParam typeParam : goods.getTypeSpecs()) {
            goodsTypeParamMapper.insertSelective(typeParam);
            goodsReleParamService.saveGoodsReleParam(goods.getGoodsId(), typeParam.getParamId(), typeParam.getParamValue(), "");
        }

        //4、修改货品、规格、图片、网点货品关系
        for (GoodsProduct goodsProduct : goods.getGoodsInfos()) {
            goodsProduct.setGoodsInfoImgId(goodsProduct.getInfoImages().get(0).getImageInName());
            goodsProduct.setGoodsInfoUnit(goods.getGoodsInfoUnit());
            goodsProduct.setGoodsInfoPack(goods.getGoodsInfoPack());
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

            //规格
            for (GoodsSpec spec : goods.getSpecs()) {
                this.goodsProductReleSpecService.updateProductReleSpec(goodsProduct.getGoodsInfoId(), spec.getSpecId(), spec.getDetails().get(0).getSpecDetailId(), spec.getDetails().get(0).getSpecDetailName());
            }
        }
    }
}
