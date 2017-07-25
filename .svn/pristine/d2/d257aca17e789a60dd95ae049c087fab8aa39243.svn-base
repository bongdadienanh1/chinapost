/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ylife.main.service.impl;

import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsCateMapper;
import com.ylife.goods.model.GoodsCate;
import com.ylife.main.mapper.MobCateBarMapper;
import com.ylife.main.model.ChannelAdver;
import com.ylife.main.model.MobCateBar;
import com.ylife.main.model.MobCateBarVo;
import com.ylife.main.service.MobCateBarService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * SERVICE实现类-移动版分类导航
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月19日下午1:56:18
 */
@Service
public class MobCateBarServiceImpl implements MobCateBarService {

    @Resource
    private MobCateBarMapper mobCateBarMapper;

    @Resource
    private GoodsCateMapper goodsCateMapper;

    /** 记录日志对象 */
    private Logger LOGGER = Logger.getLogger(this.getClass());

    /**
     */
    @Override
    public List<ChannelAdver> selectStoreListImage(String userStatus) {
        return this.mobCateBarMapper.selectStoreListImage(userStatus);
    }

    /**
     */
    @Override
    public PageBean selectMobCateBarByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.mobCateBarMapper.selectCountByPb());


            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.mobCateBarMapper.selectAllByPb(map));
        } catch (Exception e) {
            LOGGER.error("移动版分页查询错误：=>" ,e);
        }
        return pb;
    }

    /**
     */
    public List<MobCateBarVo> selectMobCateBarForSite(ArrayList<String> advMappings) {
        Map<String,Object> map = new HashMap<>();
        map.put("AdvMappings",advMappings);
        return this.mobCateBarMapper.selectAll(map);
    }

    /**
     */
    @Override
    public List<MobCateBarVo> selectMobCateBarForSite() {
        return selectMobCateBarForSite(null);
    }

    /**
     */
    @Override
    public List<MobCateBarVo> selectMobCateBarForSiteChilds() {
        ArrayList<String> advMappings = new ArrayList<>();
        advMappings.add("com.ysh.mobile.dao.MobCateBarMapper.selectUsedByParentIds");
        return selectMobCateBarForSite(advMappings);
    }

    @Override
    public List<MobCateBar> selectMobCateBarForMobChoose() {
        return this.mobCateBarMapper.selectAllForMobChoose();
    }

    /**
     */
    @Override
    public MobCateBar selectMobcateBarById(Long mobcatebarId) {
        return this.mobCateBarMapper.selectByPrimaryKey(mobcatebarId);
    }

    /**
     */
    @Override
    public int createMobCateBar(MobCateBar mobCateBar) {
        int n = -1;
        Date date = new Date();
        mobCateBar.setCreateDate(date);
        mobCateBar.setUpdateDate(date);

        n = this.mobCateBarMapper.insertSelective(mobCateBar);
        // 一级分类，级联添加它的子分类
        if (mobCateBar.getGrade() == 1) {
            List<GoodsCate> list = goodsCateMapper.querySonCatByParentId(mobCateBar.getCateId());
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    MobCateBar child = new MobCateBar();
                    child.setCateId(list.get(i).getCatId());
                    child.setParentId(mobCateBar.getCateBarId());
                    child.setName(list.get(i).getCatName());
                    child.setGrade(2);
                    child.setSort(i + 1);
                    child.setIsUsing("0");
                    child.setDelflag("0");
                    child.setCreateDate(date);
                    child.setUpdateDate(date);
                    this.mobCateBarMapper.insertSelective(child);
                }
            }
        }
        return n;
    }

    /**
     */
    @Override
    public int updateMobCateBar(MobCateBar mobCateBar) {
        Date date = new Date();
        // 设置为不启用时，它的子分类也要设置为不启用
        if ("0".equals(mobCateBar.getIsUsing())) {
            List<MobCateBarVo> list = this.mobCateBarMapper.selectByParentId(mobCateBar.getCateBarId());
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    MobCateBarVo cateBar = list.get(i);
                    cateBar.setIsUsing("0");
                    cateBar.setUpdateDate(date);
                    this.mobCateBarMapper.updateByPrimaryKeySelective(cateBar);
                }
            }
        }
        mobCateBar.setUpdateDate(date);
        return this.mobCateBarMapper.updateByPrimaryKeySelective(mobCateBar);
    }

    /**
     */
    @Override
    public int deleteMobCateBar(Long mobCateBarId) {
        Date date = new Date();
        MobCateBar mobCateBar = this.mobCateBarMapper.selectByPrimaryKey(mobCateBarId);
        mobCateBar.setDelflag("1");
        mobCateBar.setUpdateDate(date);
        List<MobCateBarVo> list = this.mobCateBarMapper.selectByParentId(mobCateBar.getCateBarId());
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                MobCateBarVo cateBar = list.get(i);
                cateBar.setDelflag("1");
                cateBar.setUpdateDate(date);
                this.mobCateBarMapper.updateByPrimaryKeySelective(cateBar);
            }
        }
        return this.mobCateBarMapper.updateByPrimaryKeySelective(mobCateBar);
    }

    /**
     */
    @Override
    public boolean checkMobCateBarIsOnly(Long cateId) {

        return this.mobCateBarMapper.selectCountByCateId(cateId) > 0 ? false : true;
    }

    /**
     */
    @Override
    public boolean checkDelete(Long mobCateBarId) {
        boolean b = true;
        List<MobCateBarVo> list = this.mobCateBarMapper.selectByParentId(mobCateBarId);
        if (null != list && !list.isEmpty()) {
            b = false;
        }
        return b;
    }

    /**
     */
    @Override
    public int changeUserdStatus(Long mobCateBarId) {
        Date date = new Date();
        MobCateBar mobCateBar = this.mobCateBarMapper.selectByPrimaryKey(mobCateBarId);
        String status = "0".equals(mobCateBar.getIsUsing()) ? "1" : "0";
        mobCateBar.setIsUsing(status);
        // 设置为不启用时，它的子分类也要设置为不启用
        if (mobCateBar.getGrade() == 1/* && "0".equals(mobCateBar.getIsUsing())*/) {
            List<MobCateBarVo> list = this.mobCateBarMapper.selectByParentId(mobCateBar.getCateBarId());
            if (!list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    MobCateBarVo cateBar = list.get(i);
                    cateBar.setIsUsing(status);
                    cateBar.setUpdateDate(date);
                    this.mobCateBarMapper.updateByPrimaryKeySelective(cateBar);
                }
            }
        }
        mobCateBar.setUpdateDate(date);
        return this.mobCateBarMapper.updateByPrimaryKeySelective(mobCateBar);
    }
    /**
     * 查询所有未删除、已启用的一级分类导航，返回List，前台调用
     *
     * @return List<MobCateBar>
     */
    @Override
    public List<MobCateBar> selectOneMobCate() {
        return this.mobCateBarMapper.selectOneMobCate();
    }
    /**
     * 根据ID查询它的子分类集合
     *
     * @param mobcatebarId 父级分类编号
     * @return List<MobCateBarVo>
     */
    @Override
    public List<MobCateBarVo> queryMobcateBarById(Long mobcatebarId) {
        return this.mobCateBarMapper.selectByParentId(mobcatebarId);
    }

}
