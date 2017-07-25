package com.ylife.authority.model;

/**
 * Created by InThEnd on 2016/4/10.
 */
public class ManagerAuthorityKey {

    private Long authorityId;

    private Long managerId;

    public ManagerAuthorityKey() {
    }

    public ManagerAuthorityKey(Long managerId, Long authorityId) {
        this.authorityId = authorityId;
        this.managerId = managerId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
