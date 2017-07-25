package com.ylife.product.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long goodsInfoId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long goodsInfoId);

    //获取货品列表，网点有库存的显示库存，无库存的显示0
    GoodsInfo selectByGoodsINfoIdAndEnterpriseId(@Param("goodsInfoId") Long goodsInfoId,
                                                 @Param("enterpriseId") Long enterpriseId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    GoodsManagerResult selectByGoodsInfoId(long goodsInfoId);

    GoodsManagerResult selectGoodsInfoByGoodsNumber(String goodsInfoItemNo);



    /**
     * 获取所有集采商品
     *
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirId
     * @param goodsInfoType
     * @param online
     * @param valetShow
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectGoods(@Param("goodsInfoName") String goodsInfoName,
                                         @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                         @Param("goodsInfoAdded") String goodsInfoAdded,
                                         @Param("brandId") Long brandId,
                                         @Param("typeId") Long typeId,
                                         @Param("thirdId") Long thirId,
                                         @Param("goodsInfoType") Integer goodsInfoType,
                                         @Param("onlineShow") Boolean online,
                                         @Param("valetShow") Boolean valetShow,
                                         @Param("auditStatus")String auditStatus,
                                         @Param("pageable") Pageable pageable);

    /**
     * 集采商品总数totalElements
     *
     * @return
     */
    int countGoodsResult(@Param("goodsInfoName") String goodsInfoName,
                         @Param("goodsInfoItemNo") String goodsInfoItemNo,
                         @Param("goodsInfoAdded") String goodsInfoAdded,
                         @Param("brandId") Long brandId,
                         @Param("typeId") Long typeId,
                         @Param("thirdId") Long thirId,
                         @Param("goodsInfoType") Integer goodsInfoType,
                         @Param("onlineShow") Boolean online,
                         @Param("valetShow") Boolean valetShow,
                         @Param("auditStatus")String auditStatus);


    /**
     * 商品管理:我的自购商品或者联合机构的自购商品
     *
     * @param enterpriseIds   选取查看的机构id集合，不传参数的时候默认登录账号
     * @param onlineShow      商城和代客商城的显示
     * @param goodsInfoName   货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded  货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectSettleGoods(@Param("enterpriseIds") List<Long> enterpriseIds,
                                               @Param("onlineShow") Boolean onlineShow,
                                               @Param("valetShow") Boolean valetShow,
                                               @Param("goodsInfoName") String goodsInfoName,
                                               @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                               @Param("goodsInfoAdded") String goodsInfoAdded,
                                               @Param("brandId") Long brandId,
                                               @Param("typeId") Long typeId,
                                               @Param("thirdId") Long thirdId,
                                               @Param("highPrice") BigDecimal highPrice,
                                               @Param("lowprice") BigDecimal lowprice,
                                               @Param("pageable") Pageable pageable);

    /**
     * 我的自购商品数量
     *
     * @param enterpriseIds
     * @param onlineShow
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @return
     */
    int countSettleGoods(@Param("enterpriseIds") List<Long> enterpriseIds,
                         @Param("onlineShow") Boolean onlineShow,
                         @Param("valetShow") Boolean valetShow,
                         @Param("goodsInfoName") String goodsInfoName,
                         @Param("goodsInfoItemNo") String goodsInfoItemNo,
                         @Param("goodsInfoAdded") String goodsInfoAdded,
                         @Param("brandId") Long brandId,
                         @Param("typeId") Long typeId,
                         @Param("thirdId") Long thirdId,
                         @Param("highPrice") BigDecimal highPrice,
                         @Param("lowprice") BigDecimal lowprice);


    /**
     * 根据节点id查询该节点下级自购商品列表
     *
     * @param maxCatalog
     * @param minCatalog
     * @param goodsInfoName   货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded  货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectSettleGoodsByEnterpriseId(@Param("maxCatalog") Long maxCatalog,
                                                             @Param("minCatalog") Long minCatalog,
                                                             @Param("goodsInfoName") String goodsInfoName,
                                                             @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                                             @Param("goodsInfoAdded") String goodsInfoAdded,
                                                             @Param("brandId") Long brandId,
                                                             @Param("typeId") Long typeId,
                                                             @Param("thirdId") Long thirdId,
                                                             @Param("pageable") Pageable pageable);

    /**
     * 根据节点id查询该节点下级自购商品数量
     *
     * @param maxCatalog
     * @param minCatalog
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @return
     */
    int countSettleGoodsByEnterpriseId(@Param("maxCatalog") Long maxCatalog,
                                       @Param("minCatalog") Long minCatalog,
                                       @Param("goodsInfoName") String goodsInfoName,
                                       @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                       @Param("goodsInfoAdded") String goodsInfoAdded,
                                       @Param("brandId") Long brandId,
                                       @Param("typeId") Long typeId,
                                       @Param("thirdId") Long thirdId);


    /**
     * 网点查询网点权限内的自购商品,包括二三级自购商品
     *
     * @param enterpriseId    选取查看的机构id，不穿参数的时候默认登录账号
     * @param goodsInfoName   货品名称
     * @param goodsInfoItemNo 货品编号
     * @param goodsInfoAdded  货品上下架信息
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectSettleGoodsByNetwork(@Param("enterpriseId") Long enterpriseId,
                                                        @Param("goodsInfoName") String goodsInfoName,
                                                        @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                                        @Param("goodsInfoAdded") String goodsInfoAdded,
                                                        @Param("brandId") Long brandId,
                                                        @Param("typeId") Long typeId,
                                                        @Param("thirdId") Long thirdId,
                                                        @Param("pageable") Pageable pageable);

    /**
     * 网点查询网点权限内的自购商品数量
     *
     * @param enterpriseId
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @return
     */
    int countSettleGoodsByByNetwork(@Param("enterpriseId") Long enterpriseId,
                                    @Param("goodsInfoName") String goodsInfoName,
                                    @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                    @Param("goodsInfoAdded") String goodsInfoAdded,
                                    @Param("brandId") Long brandId,
                                    @Param("typeId") Long typeId,
                                    @Param("thirdId") Long thirdId);


    /**
     * 获取代客下单全部商品：集采商品+自购商品
     *
     * @param enterpriseId  当前登录账号id
     * @param goodsInfoName
     * @param lowPrice
     * @param highPrice
     * @param pageable
     */
    List<GoodsManagerResult> selectValetGoods(@Param("enterpriseId") Long enterpriseId,
                                              @Param("enterpriseIds") List<Long> enterpriseIds,
                                              @Param("goodsInfoName") String goodsInfoName,
                                              @Param("lowPrice") BigDecimal lowPrice,
                                              @Param("highPrice") BigDecimal highPrice,
                                              @Param("pageable") Pageable pageable);


    int countValeGoodsResult(@Param("enterpriseId") Long enterpriseId,
                             @Param("enterpriseIds") List<Long> enterpriseIds,
                             @Param("goodsInfoName") String goodsInfoName,
                             @Param("lowPrice") BigDecimal lowPrice,
                             @Param("highPrice") BigDecimal highPrice);


    /**
     * 获取本网点库存大于0的商品
     *
     * @param goodsInfoName
     * @param lowPrice
     * @param highPrice
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectNetWorkGoods(@Param("enterpriseId") Long enterpriseId,
                                                @Param("goodsInfoId") Long goodsInfoId,
                                                @Param("goodsInfoName") String goodsInfoName,
                                                @Param("lowPrice") BigDecimal lowPrice,
                                                @Param("highPrice") BigDecimal highPrice,
                                                @Param("pageable") Pageable pageable);

    int countNetWorkGoods(@Param("enterpriseId") Long enterpriseId,
                          @Param("goodsInfoId") Long goodsInfoId,
                          @Param("goodsInfoName") String goodsInfoName,
                          @Param("lowPrice") BigDecimal lowPrice,
                          @Param("highPrice") BigDecimal highPrice);


    /**
     * 获取品牌列表
     *
     * @return
     */
    List<GoodsManagerResult> selectBrand();

    /**
     * 获取类型列表
     *
     * @return
     */
    List<GoodsManagerResult> selectType();

    /**
     * 获取集采商品商家列表
     *
     * @return
     */
    List<GoodsManagerResult> selectThirdName(@Param(value = "thirdName")String thirdName);

    /**
     * 获取自购商品商家列表
     *
     * @return
     */
    List<GoodsManagerResult> selectThirdEnterpriseName(@Param(value = "thirdName")String thirdName);



    List<GoodsManagerResult> selectBrandTypeThird();


    /**
     * 货品下架
     *
     * @param goodsId
     * @return
     */
    int unshelves(long goodsId);

    /**
     * 货品上架
     *
     * @param goodsId
     * @return
     */
    int shelves(long goodsId);

    /**
     * 第三方商城显示
     */
    int updateShow(@Param("valetShow") Boolean valteShow,
                   @Param("onlineShow") Boolean onlineShow,
                   @Param("goodsInfoId") long goodsInfoId);

    /**
     *  批量上下架
     * @param goodsInfoIds
     * @param goodsInfoAdded
     * @return
     */
    int batchUpdateGoodsInfoAdded(@Param("goodsInfoIds")String[] goodsInfoIds,@Param("goodsInfoAdded")String goodsInfoAdded);


    /**
     * 删除货品
     * @param thirdIds
     * @return
     */
    int deleteGoodsInfo(String[] thirdIds);
}