/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.main.service.impl;


import com.ylife.customer.service.DefaultAddressService;
import com.ylife.data.page.PageBean;
import com.ylife.main.mapper.MobPageTagGoodsMapper;
import com.ylife.main.model.GoodsSiteSearchBean;
import com.ylife.main.model.MobPageTagGoods;
import com.ylife.main.service.MobPageTagGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-移动页面标签商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:37:44
 */
@Service
public class MobPageTagGoodsServiceImpl implements MobPageTagGoodsService {

    private static final String MOBPAGETAGID = "mobPageTagId";
    private static final String ISHOT = "isHot";
    private static final String DISTINCTID = "distinctId";

    /** 移动页面标签商品数据访问接口 */
    private MobPageTagGoodsMapper mobPageTagGoodsMapper;

    @Resource
    private DefaultAddressService addressService;

    /*
     * 
     * 
     * @see com.ysh.moblie.service.MobPageTagGoodsService#
     * deleteMobPageTagGoods(java.lang.Long)
     */
    @Override
    public int deleteMobPageTagGoods(Long MobPageTagGoodsproductId, Long userId) {
        MobPageTagGoods storeyGoods = this.mobPageTagGoodsMapper.selectByPrimaryKey(MobPageTagGoodsproductId);
        storeyGoods.setDelflag("1");
        storeyGoods.setUpdateUserId(userId);
        storeyGoods.setUpdateDate(new Date());
        return mobPageTagGoodsMapper.updateByPrimaryKeySelective(storeyGoods);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.moblie.service.MobPageTagGoodsService#saveMobPageTagGoods
     * (com.ysh.moblie.bean.MobPageTagGoods)
     */
    @Override
    public int saveMobPageTagGoods(MobPageTagGoods record) {
        record.setDelflag("0");
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return mobPageTagGoodsMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see com.ysh.moblie.service.MobPageTagGoodsService#
     * updateMobPageTagGoods(com.ysh.moblie.bean.MobPageTagGoods)
     */
    @Override
    public int updateMobPageTagGoods(MobPageTagGoods record) {
        record.setUpdateDate(new Date());
        return mobPageTagGoodsMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * 
     * @see com.ysh.moblie.service.MobPageTagGoodsService#
     * getMobPageTagGoodsById(java.lang.Long)
     */
    @Override
    public MobPageTagGoods getMobPageTagGoodsById(Long MobPageTagGoodsproductId) {

        return mobPageTagGoodsMapper.selectByPrimaryKey(MobPageTagGoodsproductId);
    }

    /*
     * 
     * 
     * @see com.ysh.moblie.service.MobPageTagGoodsService#
     * selectMobPageTagGoodsByParam(com.ysh.util.PageBean,
     * java.lang.Long, java.lang.Long, java.lang.String)
     */
    @Override
    public PageBean selectMobPageTagGoodsByParam(PageBean pb, Long mobPageTagId, String isHot) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put(MOBPAGETAGID, mobPageTagId);
            map.put(ISHOT, isHot);
            pb.setRows(this.mobPageTagGoodsMapper.selectMobPageTagGoodsCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.mobPageTagGoodsMapper.selectMobPageTagGoodsByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 根据分页参数和移动页面标签ID、是否热销查询频道楼层商品-前台展示用
     *
     * @param mobPageTagId
     * @param isHot
     * @param searchBean
     * @param pageBean
     * @return
     */
    @Override
    public List<MobPageTagGoods> selectMobPageTagGoodsByParamForChannelSite(HttpServletRequest request, Long mobPageTagId, String isHot,
                                                                            GoodsSiteSearchBean searchBean, PageBean pageBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MOBPAGETAGID, mobPageTagId);
        map.put(ISHOT, isHot);
        map.put("searchBean", searchBean);
        Long distinctId = null;
        if (null != request.getSession().getAttribute(DISTINCTID)) {
            distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
        }
        if (pageBean != null) {
            pageBean.setRows(this.mobPageTagGoodsMapper.selectMobPageTagGoodsNumberByParam(map));
            map.put("startRowNum", pageBean.getStartRowNum());
            map.put("endRowNum", pageBean.getEndRowNum());
            if (null == distinctId) {
                Long dId = addressService.getDefaultIdService();
                if (dId == null) {
                    distinctId = addressService.getDefaultIdService();
                    if (distinctId == null) {
                        distinctId = 1103L;
                    }
                }
                map.put(DISTINCTID, dId);
                List<Object> goods = this.mobPageTagGoodsMapper.selectMobPageTagGoodsByParamForChannelSite(map);
                pageBean.setList(goods);
            } else {
                map.put(DISTINCTID, distinctId);
                List<Object> goods = this.mobPageTagGoodsMapper.selectMobPageTagGoodsByParamForChannelSite(map);
                pageBean.setList(goods);
            }
        }
        return this.mobPageTagGoodsMapper.selectMobPageTagGoodsByParamForSite(map);
    }

    /*
     * 
     * 
     * @see com.ysh.moblie.service.MobPageTagGoodsService#
     * selectMobPageTagGoodsByParamForSite(java.lang.Long, java.lang.Long,
     * java.lang.String)
     */
    @Override
    public List<MobPageTagGoods> selectMobPageTagGoodsByParamForSite( Long mobPageTagId, String isHot) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MOBPAGETAGID, mobPageTagId);
        map.put(ISHOT, isHot);
        return this.mobPageTagGoodsMapper.selectMobPageTagGoodsByParamForSite(map);
    }

    public MobPageTagGoodsMapper getMobPageTagGoodsMapper() {
        return mobPageTagGoodsMapper;
    }

    @Resource(name = "mobPageTagGoodsMapper")
    public void setMobPageTagGoodsMapper(MobPageTagGoodsMapper mobPageTagGoodsMapper) {
        this.mobPageTagGoodsMapper = mobPageTagGoodsMapper;
    }

}
