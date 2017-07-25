package com.ylife.chinapost.service.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/5/10.
 * 财富管理结果
 */
public class WealthManageResult {

    private boolean isTop;

    private Long enterpriseId;

    private String enterpriseName;

    private String accountName;

    private BigDecimal totalWealth;

    private BigDecimal myWealth;

    private BigDecimal allocatedWealth;

    private BigDecimal unAllocatedWealth;

    private BigDecimal sonsTotalWealth;

    private List<SonWealth> sonWealths;

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public BigDecimal getTotalWealth() {
        return totalWealth;
    }

    public void setTotalWealth(BigDecimal totalWealth) {
        this.totalWealth = totalWealth;
    }

    public BigDecimal getMyWealth() {
        return myWealth;
    }

    public void setMyWealth(BigDecimal myWealth) {
        this.myWealth = myWealth;
    }

    public BigDecimal getAllocatedWealth() {
        return allocatedWealth;
    }

    public void setAllocatedWealth(BigDecimal allocatedWealth) {
        this.allocatedWealth = allocatedWealth;
    }

    public BigDecimal getUnAllocatedWealth() {
        return unAllocatedWealth;
    }

    public void setUnAllocatedWealth(BigDecimal unAllocatedWealth) {
        this.unAllocatedWealth = unAllocatedWealth;
    }

    public BigDecimal getSonsTotalWealth() {
        return sonsTotalWealth;
    }

    public void setSonsTotalWealth(BigDecimal sonsTotalWealth) {
        this.sonsTotalWealth = sonsTotalWealth;
    }

    public List<SonWealth> getSonWealths() {
        return sonWealths;
    }

    public void setSonWealths(List<SonWealth> sonWealths) {
        this.sonWealths = sonWealths;
    }

    public static class SonWealth {

        private Long id;

        private String name;

        private BigDecimal wealth;

        public SonWealth(Long id, String name, BigDecimal wealth) {
            this.id = id;
            this.name = name;
            this.wealth = wealth;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getWealth() {
            return wealth;
        }

        public void setWealth(BigDecimal wealth) {
            this.wealth = wealth;
        }
    }
}
