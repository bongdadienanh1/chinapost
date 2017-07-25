/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ylife.main.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 站点基本设置实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午4:51:11
 * @version 1.0
 */
public class BasicSet implements Serializable {
    // 序列化编号
    private static final long serialVersionUID = 6774425534238622364L;

    /*
     * 基本ID
     */
    private Long bsetId;
    /*
     * 站点名称
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetName;
    /*
     * 站点描述
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String bsetDesc;
    /*
     * 站点地址
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetAddress;
    /*
     * 联系电话
     */
    @Pattern(regexp = "^0?1[0-9]{10}$")
    private String bsetPhone;
    /*
     * 网站负责人
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetAdmin;
    /*
     * 邮件
     */
    @Email
    private String bsetEmail;
    /*
     * 网站logo
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetLogo;
    /*
     * 商家登陆页logo
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetThirdLogo;

    /*
     * 标签页图标
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetHotline;
    /*
     * 版权信息
     */
    private String bsetCopyright;

    /*
     * 第三方地址
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetThirdAddress;

    /*
     * 主域名
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetDomain;

    /*
     * 前台登陆页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String siteLoginImg;
    /*
     * 前台注册页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String siteRegImg;
    /*
     * 第三方登陆页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String thirdLoginImg;

    /*
     * 后台登陆是非要启用验证码
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String loginPatcha;

    /*
     * 用户协议
     */
    private String bsetUseragreement;

    /**
     * 商家注册协议
     */
    private String thirdUserment;
    /*
     * 会员注册协议
     */
    private String bsetUseragreementuser;

    /**
     * 前台注册成功后背景图片
     * */
    private String siteRegSuccImg;
    /**
     * 第三方注册页面背景图片
     * */
    private String thirdRegImg;
    /**
     * 第三方版权信息
     * */
    private String thirdCopyright;

    public String getThirdUserment() {
        return thirdUserment;
    }

    public void setThirdUserment(String thirdUserment) {
        this.thirdUserment = thirdUserment;
    }

    public String getLoginPatcha() {
        return loginPatcha;
    }

    public void setLoginPatcha(String loginPatcha) {
        this.loginPatcha = loginPatcha;
    }

    public String getBsetDomain() {
        return bsetDomain;
    }

    public void setBsetDomain(String bsetDomain) {
        this.bsetDomain = bsetDomain;
    }

    public String getBsetThirdAddress() {
        return bsetThirdAddress;
    }

    public void setBsetThirdAddress(String bsetThirdAddress) {
        this.bsetThirdAddress = bsetThirdAddress;
    }

    public void setSiteRegImg(String siteRegImg) {
        this.siteRegImg = siteRegImg;
    }

    public String getSiteRegImg() {
        return this.siteRegImg;
    }

    public String getBsetUseragreementuser() {
        return bsetUseragreementuser;
    }

    public void setBsetUseragreementuser(String bsetUseragreementuser) {
        this.bsetUseragreementuser = bsetUseragreementuser;
    }

    public String getBsetHotline() {
        return bsetHotline;
    }

    public void setBsetHotline(String bsetHotline) {
        this.bsetHotline = bsetHotline;
    }

    public Long getBsetId() {
        return bsetId;
    }

    public void setBsetId(Long bsetId) {
        this.bsetId = bsetId;
    }

    public String getBsetName() {
        return bsetName;
    }

    public void setBsetName(String bsetName) {
        this.bsetName = bsetName;
    }

    public String getBsetDesc() {
        return bsetDesc;
    }

    public void setBsetDesc(String bsetDesc) {
        this.bsetDesc = bsetDesc;
    }

    public String getBsetAddress() {
        return bsetAddress;
    }

    public void setBsetAddress(String bsetAddress) {
        this.bsetAddress = bsetAddress;
    }

    public String getBsetPhone() {
        return bsetPhone;
    }

    public void setBsetPhone(String bsetPhone) {
        this.bsetPhone = bsetPhone;
    }

    public String getBsetAdmin() {
        return bsetAdmin;
    }

    public void setBsetAdmin(String bsetAdmin) {
        this.bsetAdmin = bsetAdmin;
    }

    public String getBsetEmail() {
        return bsetEmail;
    }

    public void setBsetEmail(String bsetEmail) {
        this.bsetEmail = bsetEmail;
    }

    public String getBsetLogo() {
        return bsetLogo;
    }

    public void setBsetLogo(String bsetLogo) {
        this.bsetLogo = bsetLogo;
    }

    public String getBsetCopyright() {
        return bsetCopyright;
    }

    public void setBsetCopyright(String bsetCopyright) {
        this.bsetCopyright = bsetCopyright;
    }

    public String getBsetUseragreement() {
        return bsetUseragreement;
    }

    public void setBsetUseragreement(String bsetUseragreement) {
        this.bsetUseragreement = bsetUseragreement;
    }

    public String getSiteLoginImg() {
        return siteLoginImg;
    }

    public void setSiteLoginImg(String siteLoginImg) {
        this.siteLoginImg = siteLoginImg;
    }

    public String getThirdLoginImg() {
        return thirdLoginImg;
    }

    public void setThirdLoginImg(String thirdLoginImg) {
        this.thirdLoginImg = thirdLoginImg;
    }

    public String getBsetThirdLogo() {
        return bsetThirdLogo;
    }

    public void setBsetThirdLogo(String bsetThirdLogo) {
        this.bsetThirdLogo = bsetThirdLogo;
    }

    public String getSiteRegSuccImg() {
        return siteRegSuccImg;
    }

    public void setSiteRegSuccImg(String siteRegSuccImg) {
        this.siteRegSuccImg = siteRegSuccImg;
    }

    public String getThirdRegImg() {
        return thirdRegImg;
    }

    public void setThirdRegImg(String thirdRegImg) {
        this.thirdRegImg = thirdRegImg;
    }

    public String getThirdCopyright() {
        return thirdCopyright;
    }

    public void setThirdCopyright(String thirdCopyright) {
        this.thirdCopyright = thirdCopyright;
    }
}
