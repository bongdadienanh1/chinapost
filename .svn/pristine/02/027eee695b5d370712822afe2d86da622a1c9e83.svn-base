/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.PageBean;
import com.ylife.goods.model.GoodsType;
import com.ylife.goods.model.GoodsTypeVo;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * 商品类型Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午10:25:57
 * @version 1.0
 */
public interface GoodsTypeService {
    /**
     * 保存商品类型
     * 
     * @param goodsType
     *            商品类型实体 {@Link com.ysh.goods.bean.GoodsType }
     * @param username
     *            操作人名称
     * @return 最新插入的ID {@link java.lang.Long}
     */
    Long saveGoodsType(GoodsType goodsType, String username);

    /**
     * 根据主键删除商品类型
     * 
     * @param typeId
     *            主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int delGoodsType(Long typeId, String username);

    /**
     * 更新商品类型
     * 
     * @param goodsType
     *            商品类型实体 {@Link com.ysh.goods.bean.GoodsType }
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateGoodsType(GoodsType goodsType, String username);

    /**
     * 根据商品类型ID 查询Vo
     * 
     * @param typeId
     *            主键ID {@link java.lang.Long}
     * @return 商品类型Vo实体 {@Link com.ysh.goods.vo.GoodsTypeVo }
     */
    GoodsTypeVo queryTypeVoByTypeId(Long typeId);

    /**
     * 根据分页参数查询商品类型列表
     * 
     * @param pb
     *            分页辅助类
     * @return 分页辅助类
     */
    PageBean queryListByPageBean(PageBean pb);

    /**
     * 查询所有的商品类型
     * 
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ysh.goods.bean.GoodsType}
     */
    List<Object> queryAllType();

    /**
     * 批量删除商品类型
     * 
     * @param typeIds
     *            需要删除的商品类型的集合 {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int batchDelType(Long[] typeIds, String username);

    /**
     * 根据商品分类ID查询商品类型VO
     * 
     * @param catId
     *            商品分类ID {@link java.lang.Long}
     * @return 查询到的VO实体 {@link com.ysh.goods.vo.GoodsTypeVo}
     */
    GoodsTypeVo queryTypeVoByCatId(Long catId);

    /**
     * 验证类型名称是否可用
     * 
     * @param typeName
     *            待验证的类型名称
     * @return 可用返回true 不可用返回false
     */
    boolean checkTypeName(String typeName);
}
