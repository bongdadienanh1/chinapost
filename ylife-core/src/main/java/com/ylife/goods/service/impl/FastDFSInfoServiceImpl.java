/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.goods.service.impl;

import com.ylife.goods.mapper.FastDFSInfoMapper;
import com.ylife.goods.model.FastDFSInfo;
import com.ylife.goods.service.FastDFSInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * SERVICE实体类-FastDFS设置信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日上午11:56:18
 */
@Service
public class FastDFSInfoServiceImpl implements FastDFSInfoService {

    // Spring注入
    @Resource
    private FastDFSInfoMapper fastdfsMapper;

    /*
     * 
     * 
     * @see
     * com.ysh.system.service.FastDFSInfoService#getFastDFSInfoById(java
     * .lang.Long)
     */
    @Override
    public FastDFSInfo getFastDFSInfoByCurr() {
        // 获取FastDfs配置
        return fastdfsMapper.selectAllFastDFS().get(0);
    }

    /*
     * 
     * 
     * @see
     * com.ysh.system.service.FastDFSInfoService#updateFastDFSInfo(com.ysh
     * .system.bean.FastDFSInfo)
     */
    @Override
    public int updateFastDFSInfo(FastDFSInfo fastDFS) {
        // 修改FastDfs配置
        return fastdfsMapper.updateByPrimaryKeySelective(fastDFS);
    }

}
