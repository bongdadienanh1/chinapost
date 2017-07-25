package com.ylife.customer.mapper;

import com.ylife.customer.model.AddressUtil;
import com.ylife.customer.model.DistrictBean;

import java.util.List;
import java.util.Map;

/**
 * 区县DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:22:36
 * @version 1.0
 */
public interface CustomDistrictMapper {
    /**
     * 根据主键删除区县信息
     * 
     * @param districtId
     *            待删除的区县的主键ID
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Long districtId);

    /**
     * 添加一条区县记录
     * 
     * @param record
     *            待添加的实体
     * @return 插入的行数
     */
    int insertSelective(DistrictBean record);

    /**
     * 根据主键查询区县信息
     * 
     * @param districtId
     *            主键ID
     * @return 查询到的实体
     */
    DistrictBean selectByPrimaryKey(Long districtId);

    /**
     * 更新区县信息
     * 
     * @param record
     *            区县信息实体
     * @return 更新的行数
     */
    int updateByPrimaryKeySelective(DistrictBean record);

    /**
     * 根据 查询参数查询所有的行数
     * 
     * @param map
     * @return
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数和查询参数查询列表
     * 
     * @param map
     *            封装了分页参数
     * @return 查询到的列表
     */
    List<Object> queryDistrictListByPb(Map<String, Object> map);

    /**
     * 根据城市ID查询区县的列表
     * 
     * @param cityId
     *            城市ID
     * @return 查询到的列表
     */
    List<DistrictBean> queryDistrictListByCityId(Long cityId);

    /**
     * 根据区县名称查询是否存在
     * 
     * @param districtName
     *            区县名称
     * @return 查询到的行数
     */
    int queryDistrictByDistrictName(Map<String, Object> map);

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @param districtId
     *            区县ID
     * @return 地区帮助Bean
     */
    AddressUtil queryAddressNameByDistrictId(Long districtId);

}
