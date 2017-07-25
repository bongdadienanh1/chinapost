package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.UcoinStatisticalService;
import com.ylife.mail.service.EmailServerService;
import com.ylife.ucoin.mapper.CustomerUcoinHistoryMapper;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/16.
 */
public class UcoinStatisticalServiceImpl implements UcoinStatisticalService {

    private String receivers;
    private boolean sendEnable;

    @Resource
    private EmailServerService emailServerService;

    @Resource
    private CustomerUcoinHistoryMapper customerUcoinHistoryMapper;

    public UcoinStatisticalServiceImpl(String receivers, boolean sendEnable) {
        this.receivers = receivers;
        this.sendEnable = sendEnable;
    }

    private static final Logger LOGGER = Logger.getLogger(UcoinStatisticalServiceImpl.class);

    @Override
    public void ucoinStatistical() throws IOException {
        LOGGER.info("开始执行发送邮件!!!!");
        if(sendEnable)
        {
            emailServerService.sendToStore(receivers.split(","),"【邮豆管理系统】每日报表_"+ DateUtil.formatToString(DateUtil.beforeDay(), "yyyy年MM月dd日"),getByteArrayInputStream());
        }
        LOGGER.info("结束执行发送邮件!!!!");
    }


    private ByteArrayInputStream getByteArrayInputStream() throws IOException {

        List<Map<String,Object>> objList = customerUcoinHistoryMapper.ucoinStatistical();

        List<String> heads = new ArrayList<>();
        heads.add("网点名称");
        heads.add("新增会员数");
        heads.add("邮豆发放笔数");
        heads.add("营销邮豆发放金额");
        heads.add("促销邮豆发放金额");
        heads.add("总邮豆金额");
        heads.add("订单数");

        List<List<String>> outList = new ArrayList<>();
        for (Map<String,Object> item : objList) {
            List<String> row = new ArrayList<>();
            row.add(item.get("enterpriseName").toString());
            row.add(item.get("addMemberCount")==null?"0":item.get("addMemberCount").toString());
            row.add(item.get("sendUcoinCount")==null?"0":item.get("sendUcoinCount").toString());
            row.add(item.get("marketUcoinSum")==null?"0":item.get("marketUcoinSum").toString());
            row.add(item.get("salesUcoinSum")==null?"0":item.get("salesUcoinSum").toString());
            row.add(item.get("ucoinSum")==null?"0":item.get("ucoinSum").toString());
            row.add(item.get("orderCount")==null?"0":item.get("orderCount").toString());
            outList.add(row);
        }

        Workbook wb = ExcelUtil.excelExport(heads, outList);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
