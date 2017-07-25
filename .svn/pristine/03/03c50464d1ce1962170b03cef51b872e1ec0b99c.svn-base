package com.ylife.main.mapper;


import com.ylife.goods.model.GoodsShowListVo;
import com.ylife.main.model.MobPageTagGoods;

import java.util.List;
import java.util.Map;

/**
 * DAO-移动页面标签商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:21:41
 */
public interface MobPageTagGoodsMapper {

    /**
     * 根据主键删除（彻底删除）
     *
     * @param map 删除数据时候用到的删除条件
     * @return int
     */
    int deleteMobPageTagGoodsnew(Map<String, Object> map);
    /**
     * 根据主键删除
     * 
     * @param MobPageTagGoodsproductId
     * @return int
     */
    int deleteByPrimaryKey(Long MobPageTagGoodsproductId);

    /**
     * 添加
     * 
     * @param record
     * @return int
     */
    int insert(MobPageTagGoods record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return int
     */
    int insertSelective(MobPageTagGoods record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(MobPageTagGoods record);

    /**
     * 修改
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(MobPageTagGoods record);

    /**
     * 根据主键查询
     * 
     * @param MobPageTagGoodsproductId
     * @return MobPageTagGoods
     */
    MobPageTagGoods selectByPrimaryKey(Long MobPageTagGoodsproductId);

    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品总行数
     * 
     * @param map
     * @return
     */
    Integer selectMobPageTagGoodsCountByParam(Map<String, Object> map);
    
    /**根据分页参数和移动页面标签ID、是否热销查询频道楼层商品总行数
     * @param map
     * @return  Integer
     */
    Integer selectMobPageTagGoodsNumberByParam(Map<String, Object> map);

    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品
     * 
     * @param map
     * @return
     */
    List<Object> selectMobPageTagGoodsByParam(Map<String, Object> map);

    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param map
     * @return
     */
    List<MobPageTagGoods> selectMobPageTagGoodsByParamForSite(Map<String, Object> map);
    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     *
     * @param map
     * @return
     */
    List<Object> selectMobPageTagGoodsByParamForChannelSite(Map<String, Object> map);
    /**
     * 通过商品Id查询商品的信息
     * @param map
     * @return
     */
    GoodsShowListVo queryGoodsListByGoodsInfoId(Map<String, Object> map);
    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     * 
     * @param map
     * @return
     */
    List<MobPageTagGoods> selectMobPageTagGoodsByParamForSiteRandom(Map<String, Object> map);
}
