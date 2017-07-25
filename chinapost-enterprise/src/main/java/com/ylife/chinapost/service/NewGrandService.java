package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface NewGrandService {

    /**
     * 顶级账号批量发放扣减的导入excel解析
     *
     * @param uploadExcel
     * @param type        true发放 false扣减
     * @return
     */
    Map<String, Object> parseFile(MultipartFile uploadExcel, Boolean type) throws IOException, InvalidFormatException;


    void batchGrand(Map<String, Object> map, String paykey, Boolean type);

    void checkBalance(Map<String,Object> map,Boolean type);


    /**
     * 获取本网点批量发放账单
     * @param code
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<EnterpriseBatchGrand> getMyGrand(Long code,  Date start, Date end, Pageable pageable);

    /**
     * 获取本网点批量扣减账单
     * @param code
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<EnterpriseBatchGrand> getMyDecut(Long code,  Date start, Date end, Pageable pageable);

    /**
     * 获取网点补贴
     * @param code
     * @param start
     * @param end
     * @param pageable
     * @return
     * */
    Page<CurrEnterpriseBill> getEnterpriseIdOrderSubsidy(Long code, Date start, Date end, Pageable pageable);

    /**
     * 订单退款返还
     * @param code
     * @param start
     * @param end
     * @param pageable
     * @return
     * */
    Page<CurrEnterpriseBill> getEnterpriseIdBackOrderSubsidy(Long code, Date start, Date end, Pageable pageable);

}






