/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ylife.main.service.impl;


import com.ylife.main.mapper.TempTokenMapper;
import com.ylife.main.model.TempToken;
import com.ylife.main.service.TempTokenService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月6日下午8:21:54
 */
@Service
public class TempTokenServiceImpl implements TempTokenService {

    @Resource
    private TempTokenMapper tokenMapper;

    /** 记录日志对象 */
    private Logger LOGGER = Logger.getLogger(this.getClass());

    /*
     * 
     * 
     * @see
     * com.ysh.temp.service.TempTokenService#saveToken(com.ysh.temp.
     * bean.TempToken)
     */
    @Override
    public int createToken(TempToken token) {

        try {
            Date date = new Date();
            token.setCreateDate(date);
            token.setUpdateDate(date);
            return tokenMapper.insertSelective(token);
        } catch (Exception e) {
            LOGGER.error("添加模板token错误：=>", e);
        }
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.temp.service.TempTokenService#updateToken(com.ysh.temp
     * .bean.TempToken)
     */
    @Override
    public int updateToken(TempToken token) {

        try {
            token.setUpdateDate(new Date());
            return tokenMapper.updateByPrimaryKeySelective(token);
        } catch (Exception e) {
            LOGGER.error("修改模板token错误：=>", e);
        }
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.temp.service.TempTokenService#deleteToken(com.ysh.temp
     * .bean.TempToken)
     */
    @Override
    public int deleteToken(Long tokenId) {
        try {
            return tokenMapper.deleteByPrimaryKey(tokenId);
        } catch (Exception e) {
            LOGGER.error("删除模板token错误：=>" + e.getLocalizedMessage());
        }
        return 0;
    }

    /*
     * 
     * 
     * @see com.ysh.temp.service.TempTokenService#getCurrToken()
     */
    @Override
    public TempToken getCurrToken() {
        TempToken token = null;
        try {
            List<TempToken> list = tokenMapper.selectAllToken();
            if (!list.isEmpty()) {
                token = list.get(0);
            }
        } catch (Exception e) {
            LOGGER.error("获取当前模板token错误：=>" + e.getLocalizedMessage());
        }
        return token;
    }

    /*
     * 
     * 
     * @see
     * com.ysh.temp.service.TempTokenService#selectTokenByType(java.lang
     * .String)
     */
    @Override
    public TempToken selectTokenByType(String temp1) {
        return this.tokenMapper.selectTokenByType(temp1);
    }

    /*
     * 
     * @see
     * com.ysh.temp.service.TempTokenService#updateTokenValue(com.ysh
     * .temp.bean.TempToken)
     */
    @Override
    public int updateTokenValue(TempToken tToken) {
        // 生成一个MD5加密计算摘要

        MessageDigest md;
        String token = "";
        try {
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(new Date().toString().getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，
            // 实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            token = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {

            LOGGER.error("更新token失败，加密异常！", e);
        }

        tToken.setToken(token);
        tToken.setUpdateDate(new Date());
        return tokenMapper.updateTokenValue(tToken);
    }

}
