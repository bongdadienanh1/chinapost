package com.ylife.product.mapper;


import com.ylife.product.model.GoodsImage;

import java.util.List;
import java.util.Map;

/**
 * 货品关联图片DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午4:01:32
 * @version 1.0
 */
public interface GoodsImagesMapper {

    /**
     * 根据主键删除
     *
     * @param map
     *            {@link java.util.Map}
     * @return 删除的行数 {@link Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 根据货品ID删除
     *
     * @param goodsInfoId
     * @return 删除的行数 {@link Integer}
     */
    int deleteByGoodsInfoId(Long goodsInfoId);

    /**
     * 插入一条记录
     *
     * @param record
     *
     * @return 插入的行数 {@link Long}
     */
    Long insertSelective(GoodsImage record);

    /**
     * 更新记录
     *
     * @param record
     *
     * @return 更新的行数 {@link Integer}
     */
    int updateByPrimaryKeySelective(GoodsImage record);

    /**
     * 根据主键ID查询
     *
     * @param goodsImgId
     *            主键ID

     */
    GoodsImage selectByPrimaryKey(Long goodsImgId);

    /**
     * 根据货品ID查询所有的关联记录
     *
     * @param GoodsInfoId
     *            货品ID {@link Long}
     * @return 查询到的列表 {@link java.util.List}
     *
     */
    List<GoodsImage> queryByGoodsInfoId(Long GoodsInfoId);

    /**
     * 根据sku id修改商品排序
     * 
     * @param GoodsInfoId
     *            skuId
     * @return
     */
    int updateByGoodsInfoId(Long GoodsInfoId);

    /**
     * 设置sku 默认图片
     * 
     * @param goodsImgId
     *            商品图片id
     * @return
     */
    int setDefaultImage(Long goodsImgId);

}
