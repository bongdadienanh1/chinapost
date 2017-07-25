/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.goods.model.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 货品信息Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午4:29:04
 * @version
 */
public interface GoodsProductService {
    /**
     * 根据商品ID查询货品列表
     *
     * @param goodsId
     *            商品ID
     * @return 货品列表
     */
    List<Object> queryAllProductListByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询货品信息
     *
     * @param productId
     *            货品信息ID
     * @return 查询到的货品信息实体
     */
    GoodsProductVo queryProductByProductId(Long productId);

    /**
     * 根据分类ID查询货品信息的集合
     *
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount);

    /**
     * 根据专区ID查询货品列表
     *
     * @param ucoinAreaId
     *            U宝专区ID
     * @param distinctId
     *            地区ID
     * @return 查询到的集合
     */
    List<GoodsProduct> queryProductByUcoinAreaId(Long ucoinAreaId,Long distinctId);

    /**
     * 查询最近一月该分类下的热销商品
     *
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询行数 {@link Integer}
     * @return 查询到的商品集合 {@link List}
     */
    List<GoodsProduct> queryHotSalesTopSix(Long catId, Integer rowCount);

    /**
     * 根据分类ID查询最新上架的货品
     *
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品
     */
    List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount);

    /**
     * 根据货品ID查询
     *
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @param distinctId
     *            选择的地区ID
     * @return 查询到的组装商品详细页的Bean
     */
    GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId,Long subjectId);

    /**
     * 保存商品咨询
     *
     * @param type
     *            咨询类型
     * @param comment
     *            咨询内容
     * @return 是否保存成功
     */
    int saveProductCommentAsk(int type, String comment, Long custId, Long productId);

    /**
     * 进行对比
     *
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    List<GoodsDetailBean> execCompProduct(List<Long> productIds);

    /**
     * 进行对比,传递请求
     *
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    List<GoodsDetailBean> execCompProduct(List<Long> productIds, HttpServletRequest request);

    /**
     * 根据货品ID和查询行数查询销售最高的几条记录
     *
     * @param productId
     *            货品ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品集合
     */
    List<GoodsProduct> queryTopSalesByProductId(Long productId, Integer rowCount);

    /**
     * 新增兑换积分
     * @param map
     * @return
     */
    int insertExchangeCusmomer(Map<String, Object> map);

    /**
     * 根据地区ID查询关联的仓库ID
     * @param distinctId 地区ID
     * @return 仓库ID
     */
    Long selectWareIdByDistinctId(Long distinctId);

    /**
     * 查询货品信息，不关联查询其他信息
     * @param goodsInfoId
     * @return
     */
    GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId);

    /**
     * 查询货品，按销量降序
     * @return
     */
    List<GoodsProduct> queryInfosOrderBySales(Long count);

    /**
     * 根据货品ID查询
     *
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @param distinctId
     *            选择的地区ID
     * @return 查询到的组装商品详细页的Bean
     */
    GoodsDetailBean queryDetailByProductId(Long productId, Long distinctId);

    /**
     * 减少库存
     *
     * @param list
     *            计算库存辅助Bean集合
     * @return 更新的行数
     */
    int minStock(List<CalcStockUtil> list);

    /**
     * 更新货品信息
     *
     * @param goodsProduct
     *            待更新的货品实体
     * @param username
     *            操作人名称
     * @param specId
     *            规格ID的数组
     * @param specDetailIds
     *            规格值得ID数组
     * @return 更新影响的行数
     */
    int updateProduct(GoodsProduct goodsProduct, String username,
                      String[] specId, String[] specDetailIds, String[] specRemarks);

    /**
     * 根据货品ID查询货品预览页的Vo
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo
     */
    GoodsProductDetailViewVo queryViewVoByProductIdGoods(Long productId);

    /**
     * 同步货品信息
     */
    public void saveGoodsProducts() throws Exception ;

    public boolean endSynchronization(String syncGoodsProduct);

    public boolean beginSynchronization(String syncGoodsProduct);
}
