package com.ylife.chinapost.service;

import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.system.model.BusinessType;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamValue;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/7.
 * U宝发放服务。
 */
public interface UcoinGrandService {

    /**
     * 是否新用户。
     *
     * @param idCardNo 身份证号。
     */
    boolean isNewCustomer(String idCardNo);


    /**
     * 根据身份证号获取用户信息。
     *
     * @param idCardNo 身份证号。
     */
    ChinapostCustomer getCustomer(String idCardNo);



    /**
     * 解析EXCEL
     */
    Map<Integer, List<List<String>>> parseFile(MultipartFile uploadExcel, int typeId) throws IOException, InvalidFormatException;


    /**
     * 单个发放。
     *
     * @param typeId    业务类型ID
     * @param paykey    操作人的支付密码（必须）
     * @param valuesMap 参数值MAP
     */
    void singleGrand(int typeId, String paykey, String contactPhone, Map<String, String> valuesMap);

    /**
     * 批量发放。
     *
     * @param typeId           业务类型ID
     * @param excelAndErrorMap EXCEL数据和匹配错误信息
     * @param paykey           操作人的支付密码（必须）
     */
    void batchGrand(int typeId, Map<Integer, List<List<String>>> excelAndErrorMap, String paykey);

    /**
     * 获取所有业务类型
     *
     * @return
     */
    List<BusinessType> getBusinessType();

    /**
     * 获取业务参数
     *
     * @param typeId
     * @return
     */
    List<Param> getParam(Integer typeId);

    /**
     * 计算邮宝
     *
     * @param typeId
     * @param valuesMap
     */
    BigDecimal computeUcoin(int typeId, Map<String, String> valuesMap);

    /**
     * 根据参数ID获取参数值
     *
     * @param paramId 参数ID
     */
    List<ParamValue> getParamValues(Integer paramId);

    Page<CustomerUcoinHistory> getGrandHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable);

}
