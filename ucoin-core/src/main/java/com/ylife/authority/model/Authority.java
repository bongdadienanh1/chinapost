package com.ylife.authority.model;

import java.util.Date;

/**
 * Created by InThEnd on 2016/4/6.
 * 权限模型。
 */
public class Authority {

    public static final long TAG_ID = 0L;

    public static final String ADMIN_NAME = "ADMIN";

    public static final String SECOND_NAME = "SECOND_ADMIN";

    public static final String NOT_NAME = "NOT_ADMIN";

    private Long id;

    private Long enterpriseId;

    private String designation;

    private String characterization;

    private Date createTime;

    private Date modTime;

    private Byte isDelete;

    private Long creatorId;


    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCharacterization() {
        return characterization;
    }

    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModTime() {
        return modTime;
    }

    public void setModTime(Date modTime) {
        this.modTime = modTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public static boolean isAdmin(Authority authority) {
        return authority.getEnterpriseId() == TAG_ID;
    }
}
