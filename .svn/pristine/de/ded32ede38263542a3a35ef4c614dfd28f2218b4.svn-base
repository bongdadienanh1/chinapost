/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.goods.mapper.GoodsTagMapper;
import com.ylife.goods.model.GoodsTag;
import com.ylife.goods.model.ValueUtil;
import com.ylife.goods.service.GoodsTagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 商品标签业务层实现类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午2:32:27
 * @version
 */
@Service("GoodsTagServiceImpl")
public class GoodsTagServiceImpl implements GoodsTagService {

    // 注入标签数据层实现类
    // 标签的数据层
    @Resource
    private GoodsTagMapper goodsTagMapper;

    /**
     * 根据标签ID查询标签实体
     *
     * @param tagId
     *            {@link java.lang.Long}
     * @return 查询到的标签实体 {@link com.ysh.goods.bean.GoodsTag}
     */
    public GoodsTag selectByPaimarykey(Long tagId) {
        return this.goodsTagMapper.selectByPrimaryKey(tagId);
    }

    /**
     * 根据主键删除
     *
     * @param tagId
     *            标签ID
     * @param delName
     *            删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteByPrimaryKey(Long tagId, String delName) {
        //定义一个Hashmap 集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 封装参数
            map.put("tagId", tagId.toString());
            map.put("del_name", delName);
            //根据主键删除
            return this.goodsTagMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            //LOGGER.info(ValueUtil.DELETEGOODSTAG + delName);
            //this.cascDelMapper.cascDel(delName);
            map = null;
        }
    }

    /**
     * 插入一个商品标签(可选属性)
     *
     * @param record
     *            待插入的商品标签实体 {@link com.ysh.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int insertSelective(GoodsTag record) {
        // 设置创建人名称 因该为登陆用户
        record.setTagCreateName("admin");
        // 设置删除标记为未删除
        record.setTagDelflag("0");
        //打印日志
        //LOGGER.info(ValueUtil.INSERTTAG);
        //插入一个商品标签
        return this.goodsTagMapper.insertSelective(record);
    }

    /**
     * 批量删除商品标签
     *
     * @param tagIds
     *            需要删除的标签ID
     * @param delName
     *            删除人名称 {@link com.ysh.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDeleteTag(Long[] tagIds, String delName) {
        // 受影响的行数
        int count = 0;
        // 把参数封装为Map
        Map<String, String> map = new HashMap<String, String>();
        try {
            for (int i = 0; i < tagIds.length; i++) {
                map.put("tagId", tagIds[i].toString());
                map.put("del_name", delName);
                //根据主键删除
                count += this.goodsTagMapper.deleteByPrimaryKey(map);
                map.clear();
            }
        } finally {
            //打印日志
            //LOGGER.info(ValueUtil.BATCHDELETETAG + delName);
            //this.cascDelMapper.cascDel(delName);
            map = null;
        }
        return count;
    }

    /**
     * 根据条件模糊查询
     *
     * @param columnName
     *            模糊查询的列名
     * @param paramvalue
     *            模糊查询的值
     * @return {@link com.ysh.goods.bean.GoodsTag} {@link java.util.List}
     */
    public List<GoodsTag> queryTagByParam(String columnName, String paramvalue) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放入map集合中
            map.put("columnName", columnName);
            map.put("paramvalue", "'%" + paramvalue + "%'");
        } finally {
            map = null;
        }
        //根据条件模糊查询
        return this.goodsTagMapper.queryTagByParam(map);
    }

    /**
     * 更新商品标签
     *
     * @param tag
     *            {@link com.ysh.goods.bean.GoodsTag}
     *            {@link java.lang.Integer}
     * @param username
     *            修改人名称
     * @return
     */
    @Transactional
    public int updateTagSelective(GoodsTag tag, String username) {
        // 修改人名称 应该为当前登陆用户
        tag.setTagModifiedName(username);
        //打印日志
        //LOGGER.info(ValueUtil.UPDATETAGSELECTIVE + username);
        //更新商品标签
        return this.goodsTagMapper.updateByPrimaryKeySelective(tag);
    }

    /**
     * 验证标签名称是否已经存在
     *
     * @param tagName
     *            待验证的标签名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkTagName(String tagName) {
        //根据标签名称查询行数
        return this.goodsTagMapper.queryByTagName(tagName) > 0 ? false : true;
    }

}
