package com.ylife.client.service;

import com.ylife.client.bean.OpenLog;
import com.ylife.client.mapper.OpenLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/6/27.
 */
@Service
public class OpenLogService {

    @Resource
    private OpenLogMapper openLogMapper;

    /**
     * 添加提交日志
     * @param openType 提交方法名称
     * @param logContent 提交内容或接口返回内容
     * @param logStatus 0表示未提交，1表示提交成功，2表示提交失败
     * @param orderCode 订单号
     */
    public void addOpenLog(String openType,String logContent,short logStatus,Long orderCode){
        OpenLog openLog = new OpenLog();
        openLog.setOpenType(openType);
        openLog.setLogContent(logContent);
        openLog.setLogStatus(logStatus);
        openLog.setOrderCode(orderCode);
        openLog.setCreateTime(new Date());
        openLogMapper.insertSelective(openLog);
    }
}
