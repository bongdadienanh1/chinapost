package com.ylife.client.service;

import com.ylife.client.mapper.SyncStatusMapper;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by WangQi on 2016/6/20.
 * 同步状态
 */
@Controller
public class SyncStatus {

    @Resource
    private SyncStatusMapper syncStatusMapper;

    @PostConstruct
    private void init() {
        //程序启动，状态初始化为 0
        syncStatusMapper.updateSyncStatus();
    }

}
