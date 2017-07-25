package com.ylife.chinapost.service.impl;

import com.ylife.address.model.City;
import com.ylife.address.model.District;
import com.ylife.address.model.Province;
import com.ylife.address.service.AddressService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerEnterpriseService;
import com.ylife.customer.service.CustomerWithUcoinInfoService;
import com.ylife.data.json.json.Renderer;
import com.ylife.data.json.json.SimpleRenderer;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.AuthorizationException;
import com.ylife.exception.UserOperationException;
import com.ylife.expression.Expression;
import com.ylife.expression.exception.MagicException;
import com.ylife.system.model.BusinessType;
import com.ylife.system.model.Param;
import com.ylife.system.model.ParamType;
import com.ylife.system.model.ParamValue;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.system.service.ParamService;
import com.ylife.system.service.ParamValueService;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import com.ylife.utils.CheckIdcard;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/4/7.
 * UcoinGrandServiceImpl
 */
@Service
public class UcoinGrandServiceImpl implements UcoinGrandService {

    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private EnterpriseBatchGrandService enterpriseBatchGrandService;
    @Resource
    private CustomerWithUcoinInfoService customerWithUcoinInfoService;
    @Resource
    private BusinessTypeService businessTypeService;
    @Resource
    private ParamService paramService;
    @Resource
    private ParamValueService paramValueService;
    @Resource
    private AddressService addressService;
    @Resource
    private CustomerEnterpriseService customerEnterpriseService;

    private Renderer renderer = new SimpleRenderer();

    private static final String HEAD_ID_CARD = "身份证号";
    private static final String HEAD_PROVINCE = "省";
    private static final String HEAD_CITY = "市";
    private static final String HEAD_DISTRICT = "区";
    private static final String HEAD_ADDR = "详细地址";
    private static final String HEAD_NAME = "姓名";
    private static final String HEAD_PRICE = "促销邮豆金额";
    private static final String HEAD_REMARK = "备注";
    private static final String HEAD_PHONE = "联系电话";
    private static final String ERROR_IDCARD = "身份证不合法";


    @Override
    public boolean isNewCustomer(String idCardNo) {
        return !chinapostCustomerService.isExist(idCardNo);
    }

    @Override
    public ChinapostCustomer getCustomer(String idCardNo) {
        return customerWithUcoinInfoService.getInfo(idCardNo, currentLoginService.getCurrentLoginEnterpriseId());
    }

    @Override
    public Map<Integer, List<List<String>>> parseFile(MultipartFile uploadExcel, int typeId) throws IOException, InvalidFormatException {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        BusinessType businessType = businessTypeService.getDetails(typeId);
        List<Param> def = businessType.getParams();
        Map<Integer, List<List<String>>> excelAndErrorinfo = new LinkedHashMap<>();
        List<List<String>> errorInfo = new ArrayList<>();
        List<List<String>> excelList;
        excelList = ExcelUtil.readExcel(uploadExcel.getInputStream(), 0);
        System.out.println("大小：" + excelList.size());
        List<String> head = excelList.get(0);
        checkExcelHeads(def, head);
        head.add("营销邮豆金额");
        List<Param> params = businessType.getParams();
        Map<String, ParamValue> paramValueMap = new HashMap<>();
        for (Param param : params) {
            if (param.getParamType() == ParamType.TYPE_ENUM) {
                List<ParamValue> paramValues = param.getParamValues();
                for (ParamValue paramValue : paramValues) {
                    paramValueMap.put(param.getParamName() + "-" + paramValue.getParamValueName(), paramValue);
                }
            }
        }

        Map<String, Set<String>> paramValueNameMap = new HashMap<>();
        for (Param param : params) {
            if (param.getParamType() == ParamType.TYPE_ENUM) {
                List<ParamValue> paramValues = param.getParamValues();
                Set<String> names = new HashSet<>();
                for (ParamValue paramValue : paramValues) {
                    names.add(paramValue.getParamValueName());
                }
                paramValueNameMap.put(param.getParamName(), names);
            }
        }

        long time4 = System.currentTimeMillis();
        for (int i = 1; i < excelList.size(); i++) {
            List<String> body = excelList.get(i);
            List<String> errorList = new ArrayList<>();
            String card = body.get(0);
            String remark = body.get(8);

            //判断备注长度，大于50报错并处理成空字符串
            if (remark != null && remark.length() > 50) {
                errorList.add(i + "^_^" + ExcelUtil.getcellColumnFlag(8 + 1) + "列" + "【" + "备注" + "】" + "备注过长");
                body.set(8, "备注超过限定长度(限定50)");
            }
            if (!CheckIdcard.isValid(card)) {
                errorList.add(0 + "^_^" + ExcelUtil.getcellColumnFlag(1) + "列" + "【" + "身份证号" + "】" + "错误");
            }
            //验证省市区，信息不全做空字符串处理
            String provinceName = body.get(1);
            String cityName = body.get(2);
            String districtName = body.get(3);
            String detailAddress = body.get(4);
            boolean blank = false;
            if (StringUtil.isBlank(provinceName) || StringUtil.isBlank(cityName)
                    || StringUtil.isBlank(districtName) || StringUtil.isBlank(detailAddress)) {
                blank = true;
            } else {
                Province province = addressService.getProvinceByName(provinceName);
                if (province == null) {
                    blank = true;
                } else {
                    City city = addressService.getCityByName(province.getProvinceId(), cityName);
                    if (city == null) {
                        blank = true;
                    } else {
                        try {
                            District district = addressService.getDistrictByName(city.getCityId(), districtName);
                            if (district == null) {
                                blank = true;
                            } else {
                                body.set(3, district.getDistrictName());
                            }
                        } catch (Exception e) {
                            blank = true;
                        }
                    }
                }
            }
            if (blank) {
                body.set(1, "");
                body.set(2, "");
                body.set(3, "");
                body.set(4, "");
            }


            Map<Integer, BigDecimal> values = new HashMap<>();
            //写入网折比例
            values.put(Param.DISCOUNT_ID, function.getDiscountPct());
            for (int j = 9; j < body.size(); j++) {
                Param param = def.get(j - 9);
                String paramContent = body.get(j);
                if (param.getRequired()) {
                    if (param.getParamType() == ParamType.TYPE_ENUM) {
                        if (StringUtil.isBlank(paramContent) || !paramValueNameMap.get(param.getParamName
                                ()).contains(paramContent)) {
                            errorList.add(j + "^_^" + ExcelUtil.getcellColumnFlag(j +
                                    1) + "列" + "【" + paramContent + "】" + "内容错误");
                            values.put(param.getParamId(), BigDecimal.ZERO);
                        } else {
                            ParamValue paramValue = paramValueMap.get(param.getParamName() + "-" + paramContent);
                            if (!paramValue.getDeletable()) {
                                paramValueService.use(paramValue.getParamValueId());
                            }
                            BigDecimal v = paramValue.getParamValue();
                            if (v == null) {
                                errorList.add(j + "-" + ExcelUtil.getcellColumnFlag(9 + 1) + "列" + "参数值" +
                                        paramContent + "未定义");
                            }
                            values.put(param.getParamId(), v);
                        }
                    } else if (param.getParamType() == ParamType.TYPE_FLOAT) {
                        BigDecimal mapValue;
                        if (StringUtil.isBlank(paramContent)) {
                            errorList.add(j + "^_^" +
                                    ExcelUtil.getcellColumnFlag(j + 1) + "列" + "【" + param.getParamName() + "】" + "必填");
                            values.put(param.getParamId(), BigDecimal.ZERO);
                        } else {
                            try {
                                mapValue = new BigDecimal(paramContent);
                                if (mapValue.compareTo(BigDecimal.ZERO) == -1) {
                                    errorList.add(j + "^_^" +
                                            ExcelUtil.getcellColumnFlag(j + 1) + "列" + "【" + param.getParamName() + "】" + "输入值错误，不能为负数！");
                                    values.put(param.getParamId(), BigDecimal.ZERO);
                                } else {
                                    values.put(param.getParamId(), mapValue);
                                }
                            } catch (NumberFormatException e) {
                                errorList.add(j + "^_^" +
                                        ExcelUtil.getcellColumnFlag(j + 1) + "列" + "【" + param.getParamName() + "】" + "浮点类型格式输入错误！");
                                mapValue = BigDecimal.ZERO;
                                values.put(param.getParamId(), mapValue);
                            }
                        }
                    }
                }
            }
            //获取促销邮豆金额，计算出营销邮豆金额并插入excel的list
            BigDecimal salePrice;
            String priceString = body.get(7);
            if (StringUtil.isBlank(priceString)) {
                salePrice = BigDecimal.ZERO;
                body.set(7, salePrice.toString());
            } else {
                try {
                    salePrice = new BigDecimal(priceString);
                    if (salePrice.compareTo(BigDecimal.ZERO) == -1) {
                        errorList.add(7 + "^_^" + ExcelUtil.getcellColumnFlag(7 + 1) +
                                "列" + "【" + "促销邮豆金额" + "】" + "不能为负");
                    }
                } catch (NumberFormatException e) {
                    //body.set(7, "浮点类型数字格式错误！");
                    errorList.add(7 + "^_^" + ExcelUtil.getcellColumnFlag(7 + 1) + "列" + "【" + "促销邮豆金额" + "】" + "格式错误！");
                }
            }

            //计算出营销邮豆金额并保留两位小数
            BigDecimal marketPrice = compute(businessType.getExpression(), values);
            if (marketPrice.compareTo(BigDecimal.ZERO) == -1) {
                errorList.add("结果验证" + "^_^" + "请检查公式设置，根据公式计算的营销金额为负数！");
            }
            //浮点类型的计算精度问题
            BigDecimal priceFormate = marketPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
            body.add(priceFormate.toString());
            errorInfo.add(errorList);
        }
        long time5 = System.currentTimeMillis();
        System.out.println("解析：" + (time5 - time4));
        excelAndErrorinfo.put(1, excelList);
        excelAndErrorinfo.put(2, errorInfo);
        List<List<String>> typeList = new ArrayList<>();
        List<String> typeNameList = new ArrayList<>();
        typeNameList.add(businessType.getTypeName());
        typeList.add(typeNameList);
        excelAndErrorinfo.put(3, typeList);
        return excelAndErrorinfo;
    }

    private void checkExcelHeads(List<Param> def, List<String> heads) {
        if (heads.size() - 9 != def.size()
                || !Objects.equals(heads.get(0), HEAD_ID_CARD) || !Objects.equals(heads.get(1), HEAD_PROVINCE) ||
                !Objects.equals(heads.get(2), HEAD_CITY)
                || !Objects.equals(heads.get(3), HEAD_DISTRICT) || !Objects.equals(heads.get(4), HEAD_ADDR) || !
                Objects.equals(heads.get(5), HEAD_NAME)
                || !Objects.equals(heads.get(6), HEAD_PHONE)
                || !Objects.equals(heads.get(7), HEAD_PRICE) || !Objects.equals(heads.get(8), HEAD_REMARK)) {
            throw new UserOperationException("格式不匹配，请重新下载对应的模板！");
        }
        for (int i = 0; i < def.size(); i++) {
            if (!def.get(i).getParamName().equals(heads.get(i + 9))) {
                throw new UserOperationException("格式不匹配，请重新下载对应的模板！");
            }
        }

    }

    @Override
    @Transactional
    public void singleGrand(int typeId, String paykey, String contactPhone, Map<String, String> valuesMap) {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new AuthorizationException("支付密码错误。");
        }
        BusinessType businessType = businessTypeService.getDetails(typeId);
        if (businessType.getDeletable() == null || businessType.getDeletable()) {
            businessTypeService.use(typeId);
        }
        List<Param> params = businessType.getParams();
        List<String> content = new ArrayList<>();
        String name = valuesMap.get(HEAD_NAME);
        String idCard = valuesMap.get(HEAD_ID_CARD);
        String priceString = valuesMap.get(HEAD_PRICE);
        BigDecimal decimal = new BigDecimal(priceString);
        if (decimal.compareTo(BigDecimal.ZERO) == -1) {
            throw new UserOperationException("存储金额错误，不能小于0！");
        }
        String remark = valuesMap.get(HEAD_REMARK);
        if (remark != null && remark.length() > 50) {
            throw new UserOperationException("备注过长，最大长度不能超过50！");
        }

        if (!CheckIdcard.isValid(idCard)) {
            throw new UserOperationException(ERROR_IDCARD);
        }
        content.add(idCard);
        String pId = "";
        content.add(pId);
        String cId = "";
        content.add(cId);
        String dId = "";
        content.add(dId);
        content.add("");
        content.add(name);
        content.add(contactPhone);
        content.add(priceString);
        content.add(remark);
        for (Param param : businessType.getParams()) {
            String value = valuesMap.get(param.getParamName());
            if (param.getRequired()) {
                Assert.hasLength(value, "参数" + param.getParamName() + "的值未提供。");
            }
            if (value == null) {
                value = "";
            }
            content.add(value);
        }

        Map<String, BigDecimal> paramValueMap = new HashMap<>();
        for (Param param : params) {
            if (param.getParamType() == ParamType.TYPE_ENUM) {
                List<ParamValue> paramValues = param.getParamValues();
                for (ParamValue paramValue : paramValues) {
                    paramValueMap.put(param.getParamName() + "-" + paramValue.getParamValueName(),
                            paramValue.getParamValue());
                }
            }
        }

        Map<Integer, BigDecimal> values = new HashMap<>();
        //写入网折比例
        values.put(Param.DISCOUNT_ID, function.getDiscountPct());
        for (int j = 9; j < content.size(); j++) {
            Param param = params.get(j - 9);
            String paramContent = content.get(j);
            if (param.getRequired()) {
                if (StringUtil.isBlank(paramContent)) {
                    throw new UserOperationException(param.getParamName() + "必填！！");
                }
                if (param.getParamType() == ParamType.TYPE_ENUM) {
                    List<ParamValue> paramValues = paramValueService.getParamValues(param.getParamId());
                    List<String> valuenames = new ArrayList<>();
                    for (ParamValue paramValue : paramValues) {
                        valuenames.add(paramValue.getParamValueName());
                    }
                    if (StringUtil.isBlank(paramContent) || !valuenames.contains(paramContent)) {

                        values.put(param.getParamId(), BigDecimal.ZERO);
                    } else {
                        //参数值标记为已用，不可删除
                       /* ParamValue paramValue = paramValueService.selectByValueNameAndId(param.getParamId(), 
paramContent);
                        if(param.getDeletable()==null){
                        paramValueService.use(paramValue.getParamValueId());}*/
                        BigDecimal v = paramValueMap.get(param.getParamName() + "-" + paramContent);
                        values.put(param.getParamId(), v);
                    }
                } else if (param.getParamType() == ParamType.TYPE_FLOAT) {
                    BigDecimal mapValue;
                    if (StringUtil.isBlank(paramContent)) {
                        mapValue = BigDecimal.ZERO;
                        values.put(param.getParamId(), mapValue);
                    } else {
                        try {
                            mapValue = new BigDecimal(paramContent);
                            if (mapValue.compareTo(BigDecimal.ZERO) == -1) {
                                throw new UserOperationException("数字不能为负！");
                            } else {
                                values.put(param.getParamId(), mapValue);
                            }
                        } catch (NumberFormatException e) {
                            throw new UserOperationException(param.getParamName() + "输入错误！");

                        }
                    }
                }
            }
        }
        //获取促销邮豆金额，计算出营销邮豆金额并插入excel的list
        BigDecimal salePrice = BigDecimal.ZERO;
        priceString = content.get(7);
        if (StringUtil.isBlank(priceString)) {
            salePrice = BigDecimal.ZERO;
        } else {
            try {
                salePrice = new BigDecimal(priceString);
                if (salePrice.compareTo(BigDecimal.ZERO) == -1) {
                    throw new UserOperationException(7 + "-" + "促销邮豆金额不能为负！");
                }
                // DecimalFormat df = new DecimalFormat("#.00");
                //salePrice = df.format(salePrice);
                //body.set(7, salePrice);
            } catch (NumberFormatException e) {
                content.set(7, "浮点类型输入错误！");
            }
        }

        BigDecimal marketPrice = compute(businessType.getExpression(), values);
        if (marketPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new UserOperationException("请检查公式设置，根据公式计算的营销金额为负数！");
        }

        BigDecimal price = marketPrice.add(salePrice);
        content.add(marketPrice.toString());

        long batchId = enterpriseBatchGrandService.addBatchGrand(enterpriseId, businessType.getTypeName(), remark, businessType.getTypeId(),null,null);
        doGrand(function, batchId, businessType, params, content, contactPhone, null, null, null, null);
        enterpriseBatchGrandService.updateFee(batchId, price, 1,null,null);
    }

    @Override
    @Transactional
    public void batchGrand(int typeId, Map<Integer, List<List<String>>> excelAndErrorList, String paykey) {
        List<List<String>> excelList = excelAndErrorList.get(1);
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new AuthorizationException("支付密码错误。");
        }

        BusinessType businessType = businessTypeService.getDetails(typeId);
        if (businessType.getDeletable() == null || businessType.getDeletable()) {
            businessTypeService.use(typeId);
        }

        List<Param> params = businessType.getParams();
        long batchId = enterpriseBatchGrandService.addBatchGrand(enterpriseId, businessType.getTypeName(), null, businessType.getTypeId(),null,null);
        int size = excelList.size();
        BigDecimal totalFee = BigDecimal.ZERO;
        for (int i = 1; i < size; i++) {
            List<String> contents = excelList.get(i);
            //验证省市区，信息不全做空字符串处理
            String provinceName = contents.get(1);
            String cityName = contents.get(2);
            String districtName = contents.get(3);
            String detailAddress = contents.get(4);
            String contactphone = contents.get(6);
            Long provinceId = null;
            Long cityId = null;
            Long districtId = null;
            if (!StringUtil.isBlank(provinceName) && !StringUtil.isBlank(cityName) && !StringUtil.isBlank
                    (districtName) && !StringUtil.isBlank(detailAddress)) {
                Province province = addressService.getProvinceByName(provinceName);
                if (province == null) {
                    contents.set(1, "");
                    contents.set(2, "");
                    contents.set(3, "");
                    contents.set(4, "");
                } else {
                    City city = addressService.getCityByName(province.getProvinceId(), cityName);
                    if (city == null) {
                        contents.set(1, "");
                        contents.set(2, "");
                        contents.set(3, "");
                        contents.set(4, "");
                    } else {
                        District district = addressService.getDistrictByName(city.getCityId(), districtName);
                        if (district == null) {
                            contents.set(1, "");
                            contents.set(2, "");
                            contents.set(3, "");
                            contents.set(4, "");
                        } else {
                            provinceId = province.getProvinceId();
                            cityId = city.getCityId();
                            districtId = district.getDistrictId();
                        }
                    }
                }
            } else {
                contents.set(1, "");
                contents.set(2, "");
                contents.set(3, "");
                contents.set(4, "");
            }
            String Addr = addressService.getAddressString(provinceId, cityId, districtId, detailAddress);
            //price:发放的邮豆金额 marketPrice:公式计算出的邮豆金额（营销邮豆金额） salePrice:促销邮豆金额
            BigDecimal fee = doGrand(function, batchId, businessType, params, contents, contactphone,
                    provinceId, cityId, districtId, Addr);
            totalFee = totalFee.add(fee);
        }
        enterpriseBatchGrandService.updateFee(batchId, totalFee, size - 1,null,null);
    }


    private BigDecimal doGrand(EnterpriseFunction function, long batchId, BusinessType businessType, List<Param>
            params, List<String> content, String contactPhone, Long provinceId, Long cityId, Long districtId, String addr) {
        BigDecimal totalFee = BigDecimal.ZERO;
        String customerName = content.get(5);
        String idCard = content.get(0);
        BigDecimal salePrice = StringUtil.isBlank(content.get(7)) ? BigDecimal.ZERO : new BigDecimal(content.get
                (7));
        if (salePrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new UserOperationException("促销邮豆金额不能为负！");
        }
        String remark = content.get(8);
        if (remark != null && remark.length() > 50) {
            throw new UserOperationException("备注过长，不能超过50！");
        }
        String sendType = businessType.getTypeName();
        int typeId = businessType.getTypeId();

        //如果为新用户，保存用户
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Long creatorId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        //String contactAddr = addressService.getAddressString(provinceId, cityId, districtId, addr);
        if (StringUtil.isBlank(addr)) {
            addr = null;
        }
        if (isNewCustomer(idCard)) {
            chinapostCustomerService.addCustomer(idCard, customerName, contactPhone, provinceId, cityId,
                    districtId, addr, null, null, enterpriseId, creatorId, null, null, addr);
        } else {
            ChinapostCustomer exitCustomer = chinapostCustomerService.getCustomer(idCard);
            chinapostCustomerService.editCustomer(exitCustomer.getId(), customerName, contactPhone, provinceId,
                    cityId, districtId, addr, null, null, null, addr, null);
            customerEnterpriseService.addRelation(exitCustomer.getId(), enterpriseId);
        }
        Long id = chinapostCustomerMapper.selectId(idCard);

        Map<String, String> paramMap = new HashMap<>();
        int sonSize = content.size();
        for (int j = 9; j < sonSize - 1; j++) {
            Param param = params.get(j - 9);
            if (param.getDeletable() == null || param.getDeletable()) {
                paramService.use(param.getParamId());
            }
            if (param.getParamType().equals(ParamType.TYPE_ENUM)) {
                ParamValue paramValue = paramValueService.selectByValueNameAndId(param.getParamId(), content.get
                        (j));
                if (paramValue.getDeletable() == null || paramValue.getDeletable()) {
                    paramValueService.use(paramValue.getParamValueId());
                }
            }
            paramMap.put(param.getParamName(), content.get(j));
        }

        BigDecimal marketPrice;
        marketPrice = new BigDecimal(content.get(content.size() - 1));
        BigDecimal price = marketPrice.add(salePrice);
        totalFee = totalFee.add(price);

        //添加U宝
        Date createDate = new Date();
        customerUcoinService.changeUcoin(id, enterpriseId, price, createDate, null, true);
        BigDecimal balancePrice=customerUcoinService.getUcoinBalance(id);
        //customerUcoinService.grandUcoin(id, function.getId(), price, null, null, sendType, remark);
        customerUcoinHistoryService.addHistory(id, function.getId(), batchId, HistoryType.ENTERPRISE_GRAND, null,price,
                marketPrice, salePrice,balancePrice, typeId, remark, null, renderer.render(paramMap),null);
        //扣款
        enterpriseService.addUcoinAmount(function.getId(), price.negate());
        return price;
    }


    @Override
    public List<BusinessType> getBusinessType() {
        List<BusinessType> types = businessTypeService.getEnabledTypes();
        BusinessType grand = new BusinessType();
        BusinessType decut = new BusinessType();
        grand.setTypeName("市局统一发放");
        grand.setTypeId(-100);
        types.add(grand);
        return types;
    }

    @Override
    public List<Param> getParam(Integer typeId) {
        return paramService.getEnabledParams(typeId);
    }

    private BigDecimal compute(String expression, Map<Integer, BigDecimal> values) {
        Expression ex = Expression.create(expression);
        BigDecimal price;
        try {
            price = ex.compute(values);
        } catch (MagicException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        //营销金额
        return price;
    }

    @Override
    public BigDecimal computeUcoin(int typeId, Map<String, String> valuesMap) {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        BusinessType businessType = businessTypeService.getDetails(typeId);
        Map<String, BigDecimal> paramValueMap = new HashMap<>();
        for (Param param : businessType.getParams()) {
            if (param.getParamType() == ParamType.TYPE_ENUM) {
                List<ParamValue> paramValues = param.getParamValues();
                for (ParamValue paramValue : paramValues) {
                    paramValueMap.put(param.getParamName() + "-" + paramValue.getParamValueName(),
                            paramValue.getParamValue());
                }
            }
        }
        Map<Integer, BigDecimal> values = new HashMap<>();
        //写入网折比例
        values.put(Param.DISCOUNT_ID, function.getDiscountPct());
        for (Param param : businessType.getParams()) {
            if (param.getRequired()) {
                if (param.getParamType() == ParamType.TYPE_ENUM) {
                    BigDecimal v = paramValueMap.get(param.getParamName() + "-" + valuesMap.get(param.getParamName
                            ()));
                    if (v == null) {
                        throw new UserOperationException("参数值" + valuesMap.get(param.getParamName()) + "不存在,检查Excel！");
                    }
                    values.put(param.getParamId(), v);
                } else if (param.getParamType() == ParamType.TYPE_FLOAT) {
                    String name = param.getParamName();
                    BigDecimal mapValue;
                    try {
                        mapValue = new BigDecimal(valuesMap.get(name));
                        if (mapValue.compareTo(BigDecimal.ZERO) == -1) {
                            throw new UserOperationException("数字不能为负！");
                        } else {
                            values.put(param.getParamId(), mapValue);
                        }
                    } catch (NumberFormatException e) {
                        throw new UserOperationException(param.getParamName() + "输入错误！");
                    }
                }
            }
        }
        return compute(businessType.getExpression(), values);
    }

    @Override
    public List<ParamValue> getParamValues(Integer paramId) {
        return paramValueService.getEnabledParamValues(paramId);
    }

    @Override
    public Page<CustomerUcoinHistory> getGrandHistory(Long enterpriseId, Integer typeId, Date start, Date end,
                                                      Pageable pageable) {
        return customerUcoinHistoryService.getHistory(enterpriseId, typeId, start, end, pageable);
    }
}
