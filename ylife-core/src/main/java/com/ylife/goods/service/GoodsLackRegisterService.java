/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.PageBean;
import com.ylife.goods.model.GoodsLackRegister;

/**
 * 到货通知Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午11:39:52
 * @version 1.0
 */
public interface GoodsLackRegisterService {
    /**
     * 插入一条到货通知记录
     * 
     * @param goodsLackRegister
     *            {@link com.ysh.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    int insert(GoodsLackRegister goodsLackRegister);

    /**
     * 更新到货通知记录
     * 
     * @param goodsLackRegister
     *            {@link com.ysh.goods.bean.GoodsLackRegister}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int update(GoodsLackRegister goodsLackRegister);

    /**
     * 根据主键查询到货通知
     * 
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ysh.goods.bean.GoodsLackRegister}
     */
    GoodsLackRegister queryByPrimaryId(Long lackRegisterId);

    /**
     * 根据PageBean查询分页列表
     * 
     * @param pb
     *            {@link com.ysh.goods.util.PageBean}
     * @return {@link com.ysh.goods.util.PageBean}
     */
    PageBean queryByPageBean(PageBean pb);

    /**
     * 批量进行到货通知
     * 
     * @param lackIds
     *            到货通知实体的id数组 {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateLackRegisterStatus(Long[] lackIds);

    /**
     * 批量删除到货通知
     * 
     * @param lackIds
     *            待删除的到货通知的ID数组
     * @return 批量删除的行数
     */
    int batchDel(Long[] lackIds);

    /**
     * 根据货品Id更新通知状态
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateStatusByProductId(Long productId);
}
