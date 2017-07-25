package com.ylife.client.bean;

import java.util.Date;

public class Sync {
    private String syncFunctionName;

    private Date syncFunctionTime;

    public String getSyncFunctionName() {
        return syncFunctionName;
    }

    public void setSyncFunctionName(String syncFunctionName) {
        this.syncFunctionName = syncFunctionName == null ? null : syncFunctionName.trim();
    }

    public Date getSyncFunctionTime() {
        return syncFunctionTime;
    }

    public void setSyncFunctionTime(Date syncFunctionTime) {
        this.syncFunctionTime = syncFunctionTime;
    }
}