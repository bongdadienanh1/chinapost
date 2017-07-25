/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsLackRegisterMapper;
import com.ylife.goods.model.GoodsLackRegister;
import com.ylife.goods.model.ValueUtil;
import com.ylife.goods.service.GoodsLackRegisterService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 到货通知Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午11:46:02
 * @version 1.0
 */
@Service("GoodsLackRegisterService")
public class GoodsLackRegisterServiceImpl implements GoodsLackRegisterService {
    // 到货通知Mapper
    @Resource
    private GoodsLackRegisterMapper goodsLackRegisterMapper;

    /**
     * 插入一条到货通知记录
     *
     * @param goodsLackRegister
     *            {@link com.ysh.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    @Transactional
    public int insert(GoodsLackRegister goodsLackRegister) {
        // 打印日志
        //LOGGER.info(ValueUtil.INSERTLACKREG);
        // 插入一条到货通知记录
        return this.goodsLackRegisterMapper.insertSelective(goodsLackRegister);
    }

    /**
     * 更新到货通知记录
     *
     * @param goodsLackRegister
     *            {@link com.ysh.goods.bean.GoodsLackRegister}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int update(GoodsLackRegister goodsLackRegister) {
        // 打印日志
        //LOGGER.info(ValueUtil.UPDATELACKREG);
        // 更新到货通知记录
        return this.goodsLackRegisterMapper
                .updateByPrimaryKeySelective(goodsLackRegister);
    }

    /**
     * 根据主键查询到货通知
     *
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ysh.goods.bean.GoodsLackRegister}
     */
    public GoodsLackRegister queryByPrimaryId(Long lackRegisterId) {
        // 根据主键查询到货通知
        return this.goodsLackRegisterMapper.selectByPrimaryKey(lackRegisterId);
    }

    /**
     * 根据PageBean查询分页列表
     *
     * @param pb
     *            {@link com.ysh.goods.util.PageBean}
     * @return {@link com.ysh.goods.util.PageBean}
     */
    public PageBean queryByPageBean(PageBean pb) {
        // 查询总行数
        pb.setRows(this.goodsLackRegisterMapper.queryTotalCount());
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 设置列表
            pb.setList(this.goodsLackRegisterMapper.queryAllByPageBean(map));
        } finally {
            // 参数置空
            map = null;
        }
        return pb;
    }

    /**
     * 批量进行到货通知
     *
     * @param lackIds
     *            到货通知实体的id数组 {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateLackRegisterStatus(Long[] lackIds) {
        // 受影响的行数
        Integer count = 0;
        try {
            for (int i = 0; i < lackIds.length; i++) {
                // 批量进行到货通知
                count += this.goodsLackRegisterMapper
                        .updateNoticeStatus(lackIds[i]);
            }
            return count;
        } finally {
            // 打印日志
            //LOGGER.info(ValueUtil.UPDATELACKREGISTERSTATUS);
            count = null;
        }
    }

    /**
     * 批量删除到货通知
     *
     * @param lackIds
     *            待删除的到货通知的ID数组
     * @return 批量删除的行数
     */
    @Transactional
    public int batchDel(Long[] lackIds) {
        Integer count = 0;
        try {
            for (int i = 0; i < lackIds.length; i++) {
                // 批量删除到货通知
                count += this.goodsLackRegisterMapper
                        .deleteByPrimaryKey(lackIds[i]);
            }
            return count;
        } finally {
            // 打印日志
            //LOGGER.info(ValueUtil.BATCHDELLACKREG);
            //this.cascDelMapper.cascDel("");
            count = null;
        }
    }

    /**
     * 根据货品Id更新通知状态
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateStatusByProductId(Long productId) {
        // 打印日志
        //LOGGER.info(ValueUtil.UPDATESTATUSBYPRODUCTID);
        // 根据货品Id更新通知状态
        return this.goodsLackRegisterMapper.updateStatusByProductId(productId);
    }

}
