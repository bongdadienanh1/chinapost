/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.service.impl;


import com.ylife.data.page.PageBean;
import com.ylife.main.mapper.MobAdverMapper;
import com.ylife.main.mapper.MobPageTagMapper;
import com.ylife.main.model.MobPageTag;
import com.ylife.main.service.MobPageTagGoodsService;
import com.ylife.main.service.MobPageTagService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-移动版页面标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午8:15:25
 */
@Service
public class MobPageTagServiceImpl implements MobPageTagService {

    @Resource
    private MobPageTagMapper mobPageTagMapper;

    @Resource
    private MobAdverMapper mobAdverMapper;

    @Resource
    private MobPageTagGoodsService mobPageTagGoodsService;

    /** 记录日志对象 */
    private Logger LOGGER = Logger.getLogger(this.getClass());


    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#deleteMobStorey(java.lang
     * .Long)
     */
    @Override
    public int deleteMobPageTag(Long mobPageTagId,Long userId) {
        MobPageTag mobPageTag = this.mobPageTagMapper.selectByPrimaryKey(mobPageTagId);
        mobPageTag.setDelflag("1");
        mobPageTag.setUpdateUserId(userId);
        mobPageTag.setUpdateDate(new Date());
        return this.mobPageTagMapper.updateByPrimaryKeySelective(mobPageTag);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#createMobStorey(com.ysh
     * .mobile.bean.MobStorey)
     */
    @Override
    public int createMobPageTag(MobPageTag mobPageTag) {
        Date date = new Date();
        mobPageTag.setDelflag("0");
        mobPageTag.setCreateDate(date);
        mobPageTag.setUpdateDate(date);
        return this.mobPageTagMapper.insertSelective(mobPageTag);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#updateMobStorey(com.ysh
     * .mobile.bean.MobStorey)
     */
    @Override
    public int updateMobPageTag(MobPageTag mobPageTag) {
        mobPageTag.setUpdateDate(new Date());
        return this.mobPageTagMapper.updateByPrimaryKeySelective(mobPageTag);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#getMobStorey(java.lang.Long)
     */
    @Override
    public MobPageTag getMobPageTag(Long mobPageTagId) {

        return this.mobPageTagMapper.selectByPrimaryKey(mobPageTagId);
    }

    /*
     *
     *
     * @see
     * com.ysh.mobile.service.getPageTag#getPageTag(java.lang.int)
     */
    @Override
    public MobPageTag getPageTagBySort(Integer sort) {

        return this.mobPageTagMapper.selectBySort(sort);
    }


    /**
     * 按排序获取页面标签,包含单身信息
     *
     * @param sort
     * @return
     */
    @Override
    public MobPageTag getPageTagAllBySort(Integer sort) {
        // 拿取活动对象
        MobPageTag mobPageTag = getPageTagBySort(sort);
        if(mobPageTag!=null){
            mobPageTag.setPageTagGoods(mobPageTagGoodsService.selectMobPageTagGoodsByParamForSite(mobPageTag.getMobPageTagId(),null));
        }
        //mobPageTag.setStartTime(UtilDate.stringToDate(UtilDate.dataFormat(mobPageTag.getStartTime())));
        //mobPageTag.setStartTime(new Date());
        return mobPageTag;
    }




    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#selectMobStoreyByPb(com.ysh
     * .util.PageBean)
     */
    @Override
    public PageBean selectMobPageTagByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.mobPageTagMapper.selectCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.mobPageTagMapper.selectByPb(map));
        } catch (Exception e) {
            LOGGER.error("分页查询移动版页面标签错误：=>", e);
        }
        return pb;
    }

    /*
     * 
     * 
     * @see com.ysh.mobile.service.MobStoreyService#selectMobStoreyForSite()
     */
    @Override
    public List<MobPageTag> selectMobPageTagForSite() {

        return this.mobPageTagMapper.selectAllForSite();
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#changeMobStoreyUserdStatus
     * (java.lang.Long)
     */
    @Override
    public int changeMobPageTagUserdStatus(Long mobPageTagId) {
        MobPageTag mobPageTag = mobPageTagMapper.selectByPrimaryKey(mobPageTagId);
        if ("0".equals(mobPageTag.getUserStatus())) {
            mobPageTag.setUserStatus("1");
        } else {
            mobPageTag.setUserStatus("0");
        }
        mobPageTag.setUpdateDate(new Date());
        return this.mobPageTagMapper.updateByPrimaryKeySelective(mobPageTag);
    }


}
