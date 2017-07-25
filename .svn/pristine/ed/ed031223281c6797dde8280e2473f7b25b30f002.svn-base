/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.InsideLetterMapper;
import com.ylife.customer.model.InsideLetter;
import com.ylife.customer.model.InsideLetterVo;
import com.ylife.customer.service.InsideLetterService;
import com.ylife.data.page.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 站内信Service
 *
 * @author jiping
 * @version 0.0.1
 * @since 2014年8月7日 上午11:52:01
 */
@Service("insideLetterService")
public class InsideLetterServiceImpl implements InsideLetterService {
    // spring注解
    private InsideLetterMapper insideletter;

    public InsideLetterMapper getInsideletter() {
        return insideletter;
    }

    @Resource(name = "insideLetterMapper")
    public void setInsideletter(InsideLetterMapper insideletter) {
        this.insideletter = insideletter;
    }

    /**
     * 删除站内信
     *
     * @param letterId
     * @return 0 失败 1成功
     */
    @Override
    public int deleteByPrimaryKey(Long letterId) {
        return insideletter.deleteByPrimaryKey(letterId);
    }

    /**
     * 添加站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int insert(InsideLetter record) {
        return insideletter.insert(record);
    }

    /**
     * 添加站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int insertSelective(InsideLetter record) {
        return insideletter.insertSelective(record);
    }

    /**
     * 根据会员Id添加站内信
     *
     * @param record
     * @param customerIds
     * @return 0 失败 1成功
     */
    @Override
    public int insertNotices(InsideLetter record, Long[] customerIds) {
        List<InsideLetter> list = new ArrayList<InsideLetter>();
        if (customerIds != null && customerIds.length > 0) {
            for (Long id : customerIds) {
                InsideLetter insi = new InsideLetter();
                insi.setLetterTitle(record.getLetterTitle());
                insi.setLetterContent(record.getLetterContent());
                insi.setCustomerId(id);
                list.add(insi);
            }
        }
        return insideletter.insertNotices(list);
    }

    /**
     * 查询站内信
     *
     * @param letterId
     * @return {@link InsideLetter}
     */
    @Override
    public InsideLetter selectByPrimaryKey(Long letterId) {
        return insideletter.selectByPrimaryKey(letterId);
    }

    /**
     * 修改站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int updateByPrimaryKeySelective(InsideLetter record) {
        return insideletter.updateByPrimaryKeySelective(record);
    }

    /**
     * 修改站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int updateByPrimaryKey(InsideLetter record) {
        return insideletter.updateByPrimaryKey(record);
    }

    /**
     * 查询全部站内信
     *
     * @param paramMap 装在查询的条件
     * @param pb       根据条件查询得到的 分页对象
     * @return
     */
    @Override
    public PageBean queryInsideLetter(Map<String, Object> paramMap, PageBean pb) {
        Long count = insideletter.queryInsideCount((Long) paramMap
                .get("customerId"));
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(insideletter.queryInsideLetter(paramMap));
        return pb;
    }

    /**
     * 标记为已读
     */
    @Override
    public int readedLetter(InsideLetterVo inside) {
        return insideletter.readedLetter(inside);
    }

    /**
     * 删除
     */
    @Override
    public int deleteLetter(Long relaId) {
        return insideletter.deleteLetter(relaId);
    }

    /**
     * 是否已读
     */
    @Override
    public Long letterIsRead(Map<String, Object> paramMap) {
        return insideletter.letterIsRead(paramMap);
    }

    /**
     * 删除未读
     */
    @Override
    public int deleteLetterNo(InsideLetterVo inside) {
        return insideletter.deleteLetterNo(inside);
    }

    /**
     * 根据会员Id和letteId删除
     */
    @Override
    public int deleteByLetterIdCustId(Map<String, Object> paramMap) {
        return insideletter.deleteByLetterIdCustId(paramMap);
    }

    /*
     * 获得未读站内信数量
     */
    @Override
    public Long getIsNotReadCount(Long customerId) {
        return this.insideletter.findInsideCount(customerId);
    }

    /**
     * 查询全部站内信
     *
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> queryInsideLetters(Map<String, Object> paramMap) {
        return insideletter.selectListNoPage(paramMap);
    }

}
