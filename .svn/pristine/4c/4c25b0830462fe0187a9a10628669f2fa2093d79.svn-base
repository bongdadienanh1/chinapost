package com.ylife.goods.mapper;

import com.ylife.goods.model.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 货品信息DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午2:57:36
 * @version 1.0
 */
public interface GoodsProductMapper {
    /**
     * 根据商品ID查询所有的货品
     *
     * @param goodsId
     * @return 查询到的货品列表
     */
    List<Object> queryProductByGoodsId(Long goodsId);

    /**
     * 根据productId获取货品信息
     *
     * @param productId
     * @return
     */
    GoodsProduct queryProductByGoodsInfoId(Long productId);

    /**
     * 根据货品id查询货品详细信息
     * @param goodsInfoId
     * @return
     */
    GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId);

    /**
     * 根据货品id查询货品详细信息
     * @param goodsInfoId
     * @return
     */
    GoodsProduct queryByGoodsInfoDetail1(Long goodsInfoId);

    /**
     * 根据套装id查询所有商品
     * @param groupId
     * @return
     */
    List<GoodsProductVo> queryDetailByGroupId(Long groupId);

    /**
     * 根据货品ID查询货品信息
     *
     * @return 查询到的货品信息
     */
    GoodsProductVo queryPrductByProductId(Long productId);

    /**
     * 根据参数查询销量最高的货品
     *
     * @param map
     *            参数 catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map);

    /**
     * 根据专区ID查询货品列表
     *
     * @param ucoinAreaId
     *            U宝专区ID
     * @return 查询到的集合
     */
    List<GoodsProduct> queryProductByUcoinAreaId(Long ucoinAreaId);

    /**
     * 根据分类ID查询最近一月的热销商品
     *
     * @param map
     *            参数 catId
     * @return 查询到的集合{@link List}
     */
    List<GoodsProduct> queryHotSalesByCatId(Map<String, Object> map);

    /**
     * 根据参数查询最新上架的货品
     *
     * @param map
     *            catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopNewInfoByCatIds(Map<String, Object> map);

    /**
     * 根据货品ID查询货品详细信息
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品详细信息 {@link com.ysh.site.goods.GoodsProductVo}
     */
    GoodsProductVo queryDetailByProductId(Long productId);

    /**
     * 根据条件查询货品详情
     * @param map
     * @return
     */
    GoodsProductVo queryDetailByParam(Map<String,Object> map);

    /**
     * 根据商品ID查询生成的第一个货品
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的货品信息 {@link com.ysh.goods.bean.GoodsProduct}
     */
    GoodsProduct queryFirstProductByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询属于同一组合下的货品
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品集合 {@link com.ysh.goods.bean.GoodsProduct}
     */
    List<GoodsProduct> queryGroupProductByProductId(Long productId);

    /**
     * 根据货品ID的数组查询货品集合
     *
     * @param map
     *            货品Id数组
     * @return 查询到的货品集合
     */
    List<GoodsProductVo> queryProductsByProductIds(Map<String, Object> map);

    /**
     * 保存商品浏览记录
     *
     * @return 插入的行数
     */
    int saveGoodsBrow(Map<String, Object> map);

    /**
     * 保存商品咨询信息
     *
     * @param map
     *            参数
     * @return 保存是否成功
     */
    int saveAskComment(Map<String, Object> map);

    /**
     * 根据货品ID查询最近一个月销量最高的几件货品
     *
     * @param map
     *            参数
     * @return 查询到的货品集合
     */
    List<GoodsProduct> queryTopSalesInfoByProductId(Map<String, Object> map);

    /**
     * 新增兑换积分
     * @param map
     * @return
     */
    int insertExchangeCusmomer(Map<String, Object> map);

    /**
     * 根据分类id查询货品
     * @return
     */
    List<GoodsProduct> queryGoodsInfoByCatIds(Map<String, Object> map);

    /**
     * 查询货品，按销量降序
     * @param map
     * @return
     */
    List<GoodsProduct> queryTopSalesInfos(Map<String, Object> map);

    /**
     * 根据分类ID查询10条不重复的货品id
     * @param map
     * @return
     */
    List<Long> queryGoodsInfoIdsByCatIds(Map<String, Object> map);

    /**
     * 根据货品ID查询货品
     * @param map
     * @return
     */
    List<GoodsProduct> queryGoodsInfoByGoodInfoIds(Map<String, Object> map);

    /**
     * 更新货品信息
     * @param map
     * @return
     */
    int updateGoodsProduct(Map<String,Object> map);

    /**
     * 更新信息(可选属性 推荐)
     *
     * @param record
     *            {@link com.ysh.goods.bean.GoodsProduct}
     * @return 更新的行数{@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsProduct record);

    /**
     * 减货品基本库存
     *
     * @return 更新的行数
     */
    int minBaseStock(Map<String, Object> map);

    /**
     * 减仓库库存
     *
     * @return 更新的行数
     */
    int minStockToWare(Map<String, Object> map);

    /**
     * 退款成功返还库存
     *
     * @param map
     * @return
     */
    int addBaseStock(Map<String, Object> map);

    /**
     * 根据货品Id查询
     *
     * @param map
     *            货品ID {@link }
     * @return 查询到的实体 {@link com.ysh.goods.vo.GoodsProductDetailViewVo}
     */
    GoodsProductDetailViewVo queryByProductId(Map<String, Object> map);

    /**
     * 插入记录(可选属性 推荐)
     *
     * @param record
     *            待插入的实体{@link com.ysh.goods.bean.GoodsProduct}
     * @return 插入的行数{@link java.lang.Integer}
     */
    int insertSelective(GoodsProduct record);

    /**
     * 查询货品预览页的Vo集合
     * @return
     */
    List<GoodsProductDetailViewVo> queryViewVoByProductId(Map<String, Object> map);


    /**
     * 取goodsInfo中最大的修改时间
     * @return
     */
    Date selectGoodsInfoByModifiedDesc();
}
