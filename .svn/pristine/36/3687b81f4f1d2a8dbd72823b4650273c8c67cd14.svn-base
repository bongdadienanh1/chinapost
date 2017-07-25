package com.ylife.main.mapper;


import com.ylife.main.model.MobPageTag;

import java.util.List;
import java.util.Map;

/**
 * DAO-移动版页面标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午8:00:14
 */
public interface MobPageTagMapper {
    /**
     * 删除
     * 
     * @param mobPageTagId
     * @return
     */
    int deleteByPrimaryKey(Long mobPageTagId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(MobPageTag record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(MobPageTag record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobPageTag record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobPageTag record);

    /**
     * 根据ID查询移动版页面标签
     * 
     * @param mobPageTagId
     * @return
     */
    MobPageTag selectByPrimaryKey(Long mobPageTagId);

    /**
     * 根据sort查询移动版页面标签
     *
     * @param sort
     * @return
     */
    MobPageTag selectBySort(Integer sort);

    /**
     * 查询所有页面标签数量，分页用
     * 
     * @return
     */
    int selectCount();

    /**
     * 分页查询页面标签
     * 
     * @param map
     * @return
     */
    List<Object> selectByPb(Map<String, Object> map);

    /**
     * 查询所有页面标签，前台展示用
     * 
     * @return
     */
    List<MobPageTag> selectAllForSite();
}
