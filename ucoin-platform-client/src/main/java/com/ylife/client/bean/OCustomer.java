package com.ylife.client.bean;

import java.util.Date;

/**
 * 会员基础信息
 * 
 * @author jack chen
 * @version 2.0
 * @since 15/8/26
 */
public class OCustomer {

    /**
     * 账户状态
     *
     * @see #getIsFlag()
     * @see #setIsFlag(String)
     */
    private String isFlag;
    /**
     * 登陆IP
     *
     * @see #getLoginIp()
     * @see #setLoginIp(String)
     */
    private String loginIp;
    /**
     * 登陆时间
     *
     * @see #getLoginTime()
     * @see #setLoginTime(Date)
     */
    private Date loginTime;
    /**
     * 创建时间
     *
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 修改时间
     *
     * @see #getModifiedTime()
     * @see #setModifiedTime(Date)
     */
    private Date modifiedTime;
    /**
     * 删除标记
     *
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 删除时间
     *
     * @see #getDelTime()
     * @see #setDelTime(Date)
     */
    private Date delTime;

    /**
     * 手机验证码
     *
     * @see #getCaptcha()
     * @see #setCaptcha(String)
     */
    private String captcha;
    /**
     * 验证码失效时间
     *
     * @see #getAeadTime()
     * @see #setAeadTime(Date)
     */
    private Date aeadTime;

    /**
     * 邮件验证码
     *
     * @see #getCaptcha()
     * @see #setCaptcha(String)
     */
    private String pwdCaptcha;
    /**
     * 邮件验证码失效时间
     *
     * @see #getAeadTime()
     * @see #setAeadTime(Date)
     */
    private Date pwdAeadTime;

    /**
     * 是否临时会员
     *
     * @see #getIsTempCust()
     * @see #setIsTempCust(String)
     */
    private String isTempCust;

    /**
     * 会员编号
     */
    private Long customerId;

    /**
     * 会员名称
     */
    private String customerUsername;

    /**
     * 会员别名
     */
    private String customerNickname;

    /**
     * 会员图片
     */
    private String customerImg;

    /**
     * 会员等级名称
     */
    private String pointLvelName;

    /**
     * 手机是否验证
     */
    private String isMobile;

    /**
     * 邮箱是否验证
     */
    private String isEmail;

    /**
     * 商家标记 0普通会员 1商家 2店铺员工
     */
    private String isSeller;

    /**
     * 第三方商家id
     */
    private String thirdId;

    /**
     * 用户密码
     */
    private String customerPassword;

    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * 用户密码
     * 
     * @param customerPassword
     *            用户密码
     * @return
     */
    public OCustomer setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public OCustomer setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public OCustomer setIsFlag(String isFlag) {
        this.isFlag = isFlag;
        return this;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public OCustomer setLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    public Date getLoginTime() {
        if(loginTime==null){
            return null;
        }else{
            return (Date) loginTime.clone();
        }

    }

    public OCustomer setLoginTime(Date loginTime) {
        this.loginTime = loginTime == null ? null : (Date) loginTime.clone();
        return this;
    }

    public Date getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return (Date) createTime.clone();
        }
    }

    public OCustomer setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
        return this;
    }

    public Date getModifiedTime() {
        if(modifiedTime==null){
            return null;
        }else{
            return (Date) modifiedTime.clone();
        }
    }

    public OCustomer setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : (Date) modifiedTime.clone();
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public OCustomer setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Date getDelTime() {
        if(delTime==null){
            return null;
        }else{
            return (Date) delTime.clone();
        }
    }

    public OCustomer setDelTime(Date delTime) {

        this.delTime = delTime == null ? null : (Date) delTime.clone();
        return this;
    }

    public String getCaptcha() {
        return captcha;
    }

    public OCustomer setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    public Date getAeadTime() {

        if(aeadTime==null){
            return null;
        }else{
            return (Date) aeadTime.clone();
        }
    }

    public OCustomer setAeadTime(Date aeadTime) {
        this.aeadTime = aeadTime == null ? null : (Date) aeadTime.clone();
        return this;
    }

    public String getPwdCaptcha() {
        return pwdCaptcha;
    }

    public OCustomer setPwdCaptcha(String pwdCaptcha) {
        this.pwdCaptcha = pwdCaptcha;
        return this;
    }

    public Date getPwdAeadTime() {
        if(pwdAeadTime==null){
            return null;
        }else{
            return (Date) pwdAeadTime.clone();
        }
    }

    public OCustomer setPwdAeadTime(Date pwdAeadTime) {
        this.pwdAeadTime = pwdAeadTime == null ? null : (Date) pwdAeadTime.clone();
        return this;
    }

    public String getIsTempCust() {
        return isTempCust;
    }

    public OCustomer setIsTempCust(String isTempCust) {
        this.isTempCust = isTempCust;
        return this;
    }

    /**
     * 会员名称
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 会员别名
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    /**
     * 会员图片
     */
    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * 会员等级名称
     */
    public String getPointLvelName() {
        return pointLvelName;
    }

    public void setPointLvelName(String pointLvelName) {
        this.pointLvelName = pointLvelName;
    }

    /**
     * 手机是否验证
     */
    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 邮箱是否验证
     */
    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    /**
     * 商家标记 0普通会员 1商家 2店铺员工
     */
    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }

    /**
     * 第三方商家id
     */
    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }
}
