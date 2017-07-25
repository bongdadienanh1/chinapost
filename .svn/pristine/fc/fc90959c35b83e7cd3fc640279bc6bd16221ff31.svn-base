package com.ylife.customer.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/7.
 * U宝客户模型。
 */
public class ChinapostCustomer {

    private Long id;

    private String idcardNo;

    private String password;

    private String paykey;

    private String phoneNo;

    private Boolean mobileChecked;

    private String fullname;

    private Boolean male;

    private String imgUrl;

    private String contactPhone;

    private String contactAddr;

    private Boolean isActive;

    private Boolean nonDisabled;

    private String managerNo;

    //创建账号的企业ID
    private Long creatorId;

    private Long enterpriseId;

    private String remark;

    private Long provinceId;

    private Long cityId;

    private Long districtId;

    private Long streetId;

    private String addr;

    private Date lastLoginTime;

    private Date createTime;

    /**
     * 额外字段
     */

    //邮豆总额
    private BigDecimal totalUcoin;

    //企业邮豆总额
    private BigDecimal enterpriseUcoin;

    //账号创建网点名称
    private String enterpriseName;

    //账号创建者名称
    private String username;



    private List<ChinapostTag> tags;

    private ExtValues values;

    private List<DeliverInfo> deliverInfos;

       public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public List<DeliverInfo> getDeliverInfos() {
        return deliverInfos;
    }

    public void setDeliverInfos(List<DeliverInfo> deliverInfos) {
        this.deliverInfos = deliverInfos;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaykey() {
        return paykey;
    }

    public void setPaykey(String paykey) {
        this.paykey = paykey;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public Boolean getMobileChecked() {
        return mobileChecked;
    }

    public void setMobileChecked(Boolean mobileChecked) {
        this.mobileChecked = mobileChecked;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getNonDisabled() {
        return nonDisabled;
    }

    public void setNonDisabled(Boolean nonDisabled) {
        this.nonDisabled = nonDisabled;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

    public String getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(String managerNo) {
        this.managerNo = managerNo;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigDecimal getTotalUcoin() {
        return totalUcoin;
    }

    public void setTotalUcoin(BigDecimal totalUcoin) {
        this.totalUcoin = totalUcoin;
    }

    public List<ChinapostTag> getTags() {
        return tags;
    }

    public void setTags(List<ChinapostTag> tags) {
        this.tags = tags;
    }

    public ExtValues getValues() {
        return values;
    }

    public void setValues(ExtValues values) {
        this.values = values;
    }

    public BigDecimal getEnterpriseUcoin() {
        return enterpriseUcoin;
    }

    public void setEnterpriseUcoin(BigDecimal enterpriseUcoin) {
        this.enterpriseUcoin = enterpriseUcoin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChinapostCustomer that = (ChinapostCustomer) o;

        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * 收货信息
     */

    public static class DeliverInfo {
        Long customerId;

        Long addressId;
        String deliverName;
        String addressMobile;
        String addressPhone;
        Long provinceId;
        Long cityId;
        Long districtId;
        String addressDetail;

        String provinceName;
        String cityName;
        String districtName;

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public Long getAddressId() {
            return addressId;
        }

        public void setAddressId(Long addressId) {
            this.addressId = addressId;
        }

        public String getDeliverName() {
            return deliverName;
        }

        public void setDeliverName(String deliverName) {
            this.deliverName = deliverName;
        }

        public String getAddressMobile() {
            return addressMobile;
        }

        public void setAddressMobile(String addressMobile) {
            this.addressMobile = addressMobile;
        }

        public String getAddressPhone() {
            return addressPhone;
        }

        public void setAddressPhone(String addressPhone) {
            this.addressPhone = addressPhone;
        }

        public Long getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(Long provinceId) {
            this.provinceId = provinceId;
        }

        public Long getCityId() {
            return cityId;
        }

        public void setCityId(Long cityId) {
            this.cityId = cityId;
        }

        public Long getDistrictId() {
            return districtId;
        }

        public void setDistrictId(Long districtId) {
            this.districtId = districtId;
        }

        public String getAddressDetail() {
            return addressDetail;
        }

        public void setAddressDetail(String addressDetail) {
            this.addressDetail = addressDetail;
        }
    }

    /**
     * 额外数值信息
     */
    public static class ExtValues {

        private BigDecimal totalUcoin;

        //邮宝消费总额
        private BigDecimal UcoinConsume;

        //现金消费总额
        private BigDecimal CashConsume = BigDecimal.ZERO;

        public BigDecimal getTotalUcoin() {
            return totalUcoin;
        }

        public void setTotalUcoin(BigDecimal totalUcoin) {
            this.totalUcoin = totalUcoin;
        }

        public BigDecimal getUcoinConsume() {
            return UcoinConsume;
        }

        public void setUcoinConsume(BigDecimal ucoinConsume) {
            UcoinConsume = ucoinConsume;
        }

        public BigDecimal getCashConsume() {
            return CashConsume;
        }

        public void setCashConsume(BigDecimal cashConsume) {
            CashConsume = cashConsume;
        }
    }
}
