package com.ylife.thirdplatform.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
* ThirdPlatformEntity实体
* @author henry
* @date 2017-06-30 08:52:14
* @version <b>1.0.0</b>
*/
public class ThirdPlatformEntity implements java.io.Serializable {
	/**  */
	private Long id;
	/**
	 * 平台登录账号
	 */
	private String thirdUserName;
	/**
	 * 密码
	 */
	private String thirdUserPwd;
	/**
	 * 平台类型，0为C店，1为抛单
	 */
	private String platformType;
	/**
	 * 删除标识（0未删除，1删除）
	 */
	private String deleteFlag;
	/**
	 * 创建时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String createTime;
	/**
	 * 修改时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private String modifyTime;

	private String[] ids;
	private String check;

	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 店铺名称
	 */
	private String storeName;

	/**
	 * 店铺id
	 * */
	private Long storeId;

	/**
	 * 设置id
	 *
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获得id
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置thirdUserName
	 *
	 * @param thirdUserName the thirdUserName to set
	 */
	public void setThirdUserName(String thirdUserName) {
		this.thirdUserName = thirdUserName;
	}

	/**
	 * 获得thirdUserName
	 *
	 * @return the thirdUserName
	 */
	public String getThirdUserName() {
		return thirdUserName;
	}

	/**
	 * 设置thirdUserPwd
	 *
	 * @param thirdUserPwd the thirdUserPwd to set
	 */
	public void setThirdUserPwd(String thirdUserPwd) {
		this.thirdUserPwd = thirdUserPwd;
	}

	/**
	 * 获得thirdUserPwd
	 *
	 * @return the thirdUserPwd
	 */
	public String getThirdUserPwd() {
		return thirdUserPwd;
	}

	/**
	 * 设置platformType
	 *
	 * @param platformType the platformType to set
	 */
	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	/**
	 * 获得platformType
	 *
	 * @return the platformType
	 */
	public String getPlatformType() {
		return platformType;
	}

	/**
	 * 设置deleteFlag
	 *
	 * @param deleteFlag the deleteFlag to set
	 */
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	/**
	 * 获得deleteFlag
	 *
	 * @return the deleteFlag
	 */
	public String getDeleteFlag() {
		return deleteFlag;
	}

	/**
	 * 设置createTime
	 *
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获得createTime
	 *
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 设置modifyTime
	 *
	 * @param modifyTime the modifyTime to set
	 */
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获得modifyTime
	 *
	 * @return the modifyTime
	 */
	public String getModifyTime() {
		return modifyTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
}
