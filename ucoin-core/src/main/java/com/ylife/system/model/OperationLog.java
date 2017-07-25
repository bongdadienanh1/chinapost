package com.ylife.system.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志实体
 * 
 * @author NINGPAI-SunKefeng
 * @since 2014年06月24日 下午4:59
 * @version 0.0.1
 */
public class OperationLog implements Serializable {
    /**
     * 日志编号op_id
     * 
     * @see #getOpId()
     * @see #setOpId(Long)
     */
    private Long opId;

    /**
     * 机构名称
     */
    private String opEnterpriseName;


    /**
     * 操作人
     * 
     * @see #getOpName()
     * @see #setOpName(String)
     */
    private String opName;

    // 操作时间
    private Date opTime;

    // 操作IP
    private String opIp;

    // 操作关键字
    private String opCode;

    // 操作内容
    private String opContent;

    // 企业ID
    private Long enterpriseId;

    private Long managerId;

    public Long getOpId() {
        return opId;
    }

    public void setOpId(Long opId) {
        this.opId = opId;
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName;
    }

    public Date getOpTime() {
        return (Date) opTime.clone();
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime == null ? null : (Date) opTime.clone();
    }

    public String getOpIp() {
        return opIp;
    }

    public void setOpIp(String opIp) {
        this.opIp = opIp;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public String getOpEnterpriseName() {
        return opEnterpriseName;
    }

    public void setOpEnterpriseName(String opEnterpriseName) {
        this.opEnterpriseName = opEnterpriseName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }
}
