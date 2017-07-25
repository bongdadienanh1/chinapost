package com.ylife.client.mapper;

import com.ylife.client.bean.SyncStatus;

public interface SyncStatusMapper {
    int deleteByPrimaryKey(String synctype);

    int insert(SyncStatus record);

    int insertSelective(SyncStatus record);

    SyncStatus selectByPrimaryKey(String synctype);

    int updateByPrimaryKeySelective(SyncStatus record);

    int updateByPrimaryKey(SyncStatus record);

    int updateSyncStatus();
}