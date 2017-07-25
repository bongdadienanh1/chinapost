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
import com.ylife.main.mapper.MobStoreyMapper;
import com.ylife.main.model.MobStorey;
import com.ylife.main.service.MobStoreyService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午8:15:25
 */
@Service
public class MobStoreyServiceImpl implements MobStoreyService {

    @Resource
    private MobStoreyMapper mobStoreyMapper;

    @Resource
    private MobAdverMapper mobAdverMapper;

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
    public int deleteMobStorey(Long mobStoreyId) {
        int n = -1;
        try {
            n = this.mobAdverMapper.deleteByStoreyId(mobStoreyId);
            n += this.mobStoreyMapper.deleteByPrimaryKey(mobStoreyId);
        } catch (Exception e) {
            LOGGER.error("删除移动版楼层及其广告错误：=>", e);
        }
        return n;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#createMobStorey(com.ysh
     * .mobile.bean.MobStorey)
     */
    @Override
    public int createMobStorey(MobStorey mobStorey) {
        Date date = new Date();
        mobStorey.setCreateDate(date);
        mobStorey.setUpdateDate(date);
        return this.mobStoreyMapper.insertSelective(mobStorey);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#updateMobStorey(com.ysh
     * .mobile.bean.MobStorey)
     */
    @Override
    public int updateMobStorey(MobStorey mobStorey) {
        mobStorey.setUpdateDate(new Date());
        return this.mobStoreyMapper.updateByPrimaryKeySelective(mobStorey);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#getMobStorey(java.lang.Long)
     */
    @Override
    public MobStorey getMobStorey(Long mobStoreyId) {

        return this.mobStoreyMapper.selectByPrimaryKey(mobStoreyId);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#selectMobStoreyByPb(com.ysh
     * .util.PageBean)
     */
    @Override
    public PageBean selectMobStoreyByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.mobStoreyMapper.selectCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.mobStoreyMapper.selectByPb(map));
        } catch (Exception e) {
            LOGGER.error("分页查询移动版楼层错误：=>", e);
        }
        return pb;
    }

    /*
     * 
     * 
     * @see com.ysh.mobile.service.MobStoreyService#selectMobStoreyForSite()
     */
    @Override
    public List<MobStorey> selectMobStoreyForSite() {

        return this.mobStoreyMapper.selectAllForSite();
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobStoreyService#changeMobStoreyUserdStatus
     * (java.lang.Long)
     */
    @Override
    public int changeMobStoreyUserdStatus(Long mobStoreyId) {
        MobStorey mobStorey = mobStoreyMapper.selectByPrimaryKey(mobStoreyId);
        if ("0".equals(mobStorey.getUserStatus())) {
            mobStorey.setUserStatus("1");
        } else {
            mobStorey.setUserStatus("0");
        }
        mobStorey.setUpdateDate(new Date());
        return this.mobStoreyMapper.updateByPrimaryKeySelective(mobStorey);
    }
}
