/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.service.impl;


import com.ylife.main.mapper.MobSiteBasicMapper;
import com.ylife.main.model.MobSiteBasic;
import com.ylife.main.service.MobSiteBasicService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * SERVICE实体类-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:42:31
 */
@Service
public class MobSiteBasicServiceImpl implements MobSiteBasicService {

    @Resource
    private MobSiteBasicMapper mobSiteBasicMapper;

    private static final Logger LOGGER = Logger.getLogger(MobSiteBasicServiceImpl.class);

    /*
     * 
     * 
     * @see
     * com.ysh.system.mobile.service.MobSiteBasicService#saveMobSiteBasic
     * (com.ysh.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int saveMobSiteBasic(MobSiteBasic record) {

        return this.mobSiteBasicMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.system.mobile.service.MobSiteBasicService#updateMobSiteBasic
     * (com.ysh.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int updateMobSiteBasic(MobSiteBasic record) {
        int n = -1;
        try {
            record.setUpdateDate(new Date());
            n = this.mobSiteBasicMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改移动版站点信息异常：=》", e);
        }
        return n;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.system.mobile.service.MobSiteBasicService#selectCurrMobSiteBasic
     * ()
     */
    @Override
    public MobSiteBasic selectCurrMobSiteBasic(HttpServletRequest request) {
        MobSiteBasic msb = this.mobSiteBasicMapper.selectCurr();
        if (null == msb) {
            try {
                String path = request.getContextPath();
                String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
                // 如果当前没有站点信息，创建一个并保存
                msb = new MobSiteBasic();
                msb.setSiteBasicId(1L);
                msb.setSiteAddress("/mobile");
                msb.setName("福气商城");
                msb.setEname("paistore");
                msb.setTechnicalSupport("paistore");
                msb.setBackgroudColor("#2589c9");
                msb.setBackgroudImage(basePath + "images/curtain_word.png");
                msb.setCopyright("NINGPAI");
                msb.setIsShowBuffer("1");
                Date date = new Date();
                msb.setCreateDate(date);
                msb.setUpdateDate(date);
                this.saveMobSiteBasic(msb);
            } catch (Exception e) {
                LOGGER.error("添加移动版站点信息异常：=》", e);
            }
        }
        return msb;
    }

}
