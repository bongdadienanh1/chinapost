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
import com.ylife.main.model.MobAdver;
import com.ylife.main.service.MobAdverService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-移动版广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午11:27:36
 */
@Service
public class MobAdverServiceImpl implements MobAdverService {

    @Resource
    private MobAdverMapper mobAdverMapper;
    /** 记录日志对象 */
    private Logger LOGGER = Logger.getLogger(this.getClass());


    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#batchDelMobAdver(java.util
     * .List)
     */
    @Override
    public int batchDelMobAdver(List<Long> ids) {

        return mobAdverMapper.batchDelMobAdver(ids);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#deleteMobAdver(java.lang.Long)
     */
    @Override
    public int deleteMobAdver(Long mobAdverId) {

        return mobAdverMapper.deleteByPrimaryKey(mobAdverId);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#updateMobAdver(com.ysh
     * .mobile.bean.MobAdver)
     */
    @Override
    public int updateMobAdver(MobAdver mobAdver) {
        mobAdver.setUpdateDate(new Date());
        return mobAdverMapper.updateByPrimaryKeySelective(mobAdver);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#createMobAdver(com.ysh
     * .mobile.bean.MobAdver)
     */
    @Override
    public int createMobAdver(MobAdver mobAdver) {
        Date date = new Date();
        mobAdver.setCreateDate(date);
        mobAdver.setUpdateDate(date);
        return mobAdverMapper.insertSelective(mobAdver);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#getMobAdver(java.lang.Long)
     */
    @Override
    public MobAdver getMobAdver(Long mobAdverId) {

        return mobAdverMapper.selectByPrimaryKey(mobAdverId);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#selectByStoreyIdAndPb(com.
     * ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectByStoreyIdAndPb(PageBean pb, Long storeyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(mobAdverMapper.selectCountByStoreyId(storeyId));
            map.put("storeyId", storeyId);
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(mobAdverMapper.selectByStoreyIdAndPb(map));
        } catch (Exception e) {
            LOGGER.error("移动版广告分页查询错误：=>", e);
        }
        return pb;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#selectCountByStoreyId(java
     * .lang.Long)
     */
    @Override
    public boolean selectCountByStoreyId(Long storeyId) {

        return mobAdverMapper.selectCountByStoreyId(storeyId) > 0 ? false : true;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#selectByStoreyIdForSite(java
     * .lang.Long)
     */
    @Override
    public List<MobAdver> selectByStoreyIdForSite(Long storeyId) {

        return mobAdverMapper.selectByStoreyIdForSite(storeyId);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.mobile.service.MobAdverService#changeMobAdverUserdStatus(
     * java.lang.Long)
     */
    @Override
    public int changeMobAdverUserdStatus(Long mobAdverId) {
        MobAdver mobAdver = this.mobAdverMapper.selectByPrimaryKey(mobAdverId);
        if ("0".equals(mobAdver.getUserStatus())) {
            mobAdver.setUserStatus("1");
        } else {
            mobAdver.setUserStatus("0");
        }
        mobAdver.setUpdateDate(new Date());
        return this.mobAdverMapper.updateByPrimaryKeySelective(mobAdver);
    }

}
