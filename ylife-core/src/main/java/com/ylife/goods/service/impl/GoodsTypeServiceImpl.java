/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsTypeMapper;
import com.ylife.goods.model.*;
import com.ylife.goods.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品类型Service
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月23日 上午10:32:36
 */
@Service("GoodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    // 商品类型的Dao
    @Resource
    private GoodsTypeMapper goodsTypeMapper;
    // 商品类型关联品牌Service
    @Resource
    private GoodsTypeBrandService goodsTypeBrandService;
    // 商品类型关联商品规格Service
    @Resource
    private GoodsTypeSpecService goodsTypeSpecService;
    // 商品类型扩展属性Service
    @Resource
    private GoodsTypeExpandParamService goodsTypeExpandParamService;
    // 商品类型扩展属性值Service
    @Resource
    private GoodsTypeExpandParamValueService goodsTypeExpandParamValueService;
    // 商品详细参数Service
    @Resource
    private GoodsTypeParamService goodsTypeParamService;

    /**
     * 保存商品类型
     *
     * @param goodsType 商品类型实体 {@Link com.ysh.goods.bean.GoodsType }
     * @param username  操作人名称
     * @return 最新插入的ID {@link java.lang.Long}
     */
    @Transactional
    public Long saveGoodsType(GoodsType goodsType, String username) {
        goodsType.setTypeCreateName(username);
        //执行添加方法
        this.goodsTypeMapper.insertSelective(goodsType);
        //打印日志
        //LOGGER.info(ValueUtil.SAVEGOODSTYPE + username);
        //查询最后一个插入的id
        return this.goodsTypeMapper.selectLastId();
    }

    /**
     * 根据主键删除商品类型
     *
     * @param typeId   主键ID {@link java.lang.Long}
     * @param username 操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsType(Long typeId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 封装删除的参数
            map.put("delName", username);
            map.put("typeId", typeId.toString());
            //执行删除方法
            return this.goodsTypeMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            //LOGGER.info(ValueUtil.DELGOODSTYPE + username);
            //this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 更新商品类型
     *
     * @param goodsType 商品类型实体 {@Link com.ysh.goods.bean.GoodsType }
     * @param username  操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsType(GoodsType goodsType, String username) {
        goodsType.setTypeModifiedName(username);
        //打印日志
        //LOGGER.info(ValueUtil.UPDATEGOODSTYPE + username);
        //执行修改方法
        return this.goodsTypeMapper.updateByPrimaryKeySelective(goodsType);
    }

    /**
     * 根据商品类型ID 查询Vo
     *
     * @param typeId 主键ID {@link java.lang.Long}
     * @return 商品类型Vo实体 {@Link com.ysh.goods.vo.GoodsTypeVo }
     */
    public GoodsTypeVo queryTypeVoByTypeId(Long typeId) {
        //执行查询方法
        return this.goodsTypeMapper.selectByPrimaryKey(typeId);
    }

    /**
     * 根据分页参数查询商品类型列表
     *
     * @param pb 分页辅助类
     * @return 分页辅助类
     */
    public PageBean queryListByPageBean(PageBean pb) {
        //设置总行数
        pb.setRows(this.goodsTypeMapper.queryTotalCount());
        //定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页需要的参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            //设置集合数据
            pb.setList(this.goodsTypeMapper.selectAllType(map));
        } finally {
            // 参数置空
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除商品类型
     *
     * @param typeIds  需要删除的商品类型的集合 {@link java.lang.Long}
     * @param username 操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDelType(Long[] typeIds, String username) {
        Integer count = 0;
        try {
            for (int i = 0; i < typeIds.length; i++) {
                count += delGoodsType(typeIds[i], username);
            }
            return count;
        } finally {
            //打印日志
            //LOGGER.info(ValueUtil.BATCHDELTYPE + username);
            //this.cascDelMapper.cascDel(username);
            // 参数置空
            count = null;
        }
    }

    /**
     * 保存商品类型关联品牌
     *
     * @param username 操作人名称呢过
     * @param lastId   类型ID
     * @param brandIds 商品类型ID数组
     */
    @Transactional
    private void saveBrand(String username, Long lastId, String[] brandIds) {
        if (brandIds != null) {
            // 创建一个迭代对象
            GoodsTypeBrand goodsTypeBrand = null;
            for (int i = 0; i < brandIds.length; i++) {
                // 添加进各种参数
                goodsTypeBrand = new GoodsTypeBrand();
                goodsTypeBrand.setTypeId(lastId);
                goodsTypeBrand.setBrandId(Long.parseLong(brandIds[i]));
                goodsTypeBrand.setDelflag(ValueUtil.DEFAULTDELFLAG);
                //执行添加方法
                this.goodsTypeBrandService.insertTypeBrand(goodsTypeBrand,
                        username);
            }
            // 置空参数
            goodsTypeBrand = null;
        }
    }

    /**
     * 查询所有的商品类型
     *
     * @return 查询到的列表 {@link java.util.List}
     * {@link com.ysh.goods.bean.GoodsType}
     */
    public List<Object> queryAllType() {
        //定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, 0);
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, this.goodsTypeMapper.queryTotalCount());
            //执行查询方法
            return this.goodsTypeMapper.selectAllType(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品分类ID查询商品类型VO
     *
     * @param catId 商品分类ID {@link java.lang.Long}
     * @return 查询到的VO实体 {@link com.ysh.goods.vo.GoodsTypeVo}
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        //执行查询方法
        return this.goodsTypeMapper.queryTypeVoByCatId(catId);
    }

    /**
     * 验证类型名称是否可用
     *
     * @param typeName 待验证的类型名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkTypeName(String typeName) {
        //执行方法返回结果
        return this.goodsTypeMapper.queryCountByTypeName(typeName) > 0 ? false
                : true;
    }

}
