package com.common.util;

import com.ylife.chinapost.service.UcoinStatisticalService;
import com.ylife.mail.service.EmailServerService;
import com.ylife.ucoin.mapper.CustomerUcoinHistoryMapper;
import com.ylife.utils.ClasspathPropertiesHelper;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/15.
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailUtil {

    @Resource
    private UcoinStatisticalService ucoinStatisticalService;

    @Resource
    private CustomerUcoinHistoryMapper customerUcoinHistoryMapper;

    @Test
    public  void testSecurityResolver() throws Exception {
        ucoinStatisticalService.ucoinStatistical();
    }

    @Test
    public void testExcel() throws IOException, InvalidFormatException {
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
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        List<List<String>> list = ExcelUtil.readExcel(is);
        System.out.println(list);
    }
}
