package com.ylife.chinapost.service.impl;


import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.NewGrandService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerEnterpriseService;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.mapper.EnterpriseUcoinHistoryMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseUcoinHistory;
import com.ylife.enterprise.service.EnterpriseFunctionService;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.AuthorizationException;
import com.ylife.exception.GrandException;
import com.ylife.exception.UserOperationException;
import com.ylife.system.model.BusinessType;
import com.ylife.tempryUcoinUtil.RestoreData;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import com.ylife.wealth.model.CurrEnterpriseBill;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by XuKai on 2016/8/16.
 * NewGrandServiceImpl
 */
@Service
public class NewGrandServiceImpl implements NewGrandService {

    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private EnterpriseBatchGrandService enterpriseBatchGrandService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private CustomerEnterpriseService customerEnterpriseService;
    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private EnterpriseFunctionService enterpriseFunctionService;
    @Resource
    private EnterpriseUcoinHistoryMapper enterpriseUcoinHistoryMapper;

    @Resource
    private RestoreData restoreData;

    public static final Generator generator = IdGeneratorFactory.create("UCOIN_HISTORY2", IdGeneratorFactory.SPartSize.TWO, IdGeneratorFactory.NPartSize.FIVE);


    private static final String HEAD_ACCOUNT_NAME = "机构编号";
    private static final Integer NUM_ACCOUNT_NAME=1;
    private static final String  HEAD_SERIAL_NUMBER="流水号";
    private static final Integer NUM_SERIAL_NUMBER=0;
    private static final String HEAD_ID_CARD = "身份证号";
    private static final Integer NUM_ID_CARD=2;
    private static final String HEAD_NAME = "姓名";
    private static final Integer NUM_NAME=3;
    private static final String HEAD_PHONE = "电话";
    private static final Integer NUM_PHONE=4;
    private static final String HEAD_MARKET_PRICE = "标准积分";
    private static final Integer NUM_MARKET_PRICE=5;
    private static final String HEAD_SALE_PRICE = "促销积分";
    private static final Integer NUM_SALE_PRICE=6;
    private static final String BUSINESS_TIME = "日期";
    private static final Integer NUM_BUSINESS_TIME=7;
    private static final String ERROR_IDCARD = "身份证不合法";
    private static final String GRAND = "市局统一发放";
    private static final String DECUT = "市局统一扣减";
    private static final Map<String, Long> enterpriseMap = new ConcurrentHashMap<>();
    private static final Map<String, Long> customerMap = new ConcurrentHashMap<>();
    private static final Map<String,String> serialMap=new ConcurrentHashMap();

    @Override
    public Map<String, Object> parseFile(MultipartFile uploadExcel, Boolean type) throws IOException, InvalidFormatException {
        List<List<String>> errorList = new ArrayList<>();
        List<List<String>> excelList;
        String excelName=uploadExcel.getOriginalFilename();
        excelList = ExcelUtil.readExcel(uploadExcel.getInputStream(), 1);
        List<String> head = excelList.get(0);
        checkHead(head);
        List<String> listName = new ArrayList<>() ;
        for (int i = 1; i < excelList.size(); i++) {
            List<String> body = excelList.get(i);
            List<String> error = new ArrayList<>();
            String accountName = body.get(NUM_ACCOUNT_NAME);
            String serialNumber=null;
            if (StringUtil.isBlank(accountName)) {
                if (error.size() < 100) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_ACCOUNT_NAME+1) + "^_^" + "列" + "【" + HEAD_ACCOUNT_NAME + "】" + "不能为空");
                }
            } else {
                Long enterpriseId = enterpriseMap.get(accountName);
                if (enterpriseId == null) {
                    EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfoByOrganization(accountName);
                    if (enterpriseInfo == null) {
                        if (error.size() < 100) {
                            error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_ACCOUNT_NAME+1) + "^_^" + "列" + "【" + HEAD_ACCOUNT_NAME + "】" + "该编号不存在");
                        }
                    } else {
                        enterpriseMap.put(accountName, enterpriseInfo.getEnterpriseId());
                    }
                }
            }
            if (type) {
                String listNumber = body.get(0);
                if (listNumber!=null&&listNumber!=""){
                    if (listName.contains(listNumber)){
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_SERIAL_NUMBER + 1) + "^_^" + "列" + "【" + HEAD_SERIAL_NUMBER + "】" + "该流水号已存在");
                    }else{
                        if (listNumber!=null&&listNumber!=""){
                            listName.add(listNumber);
                        }
                    }
                }
                serialNumber = body.get(NUM_SERIAL_NUMBER).trim() == "" ? null : body.get(NUM_SERIAL_NUMBER);
                body.set(NUM_SERIAL_NUMBER,serialNumber);
                //判断流水号是否重复
                if (serialNumber != null) {
                    if (!serialMap.keySet().contains(serialNumber)) {
                        CustomerUcoinHistory history = customerUcoinHistoryService.getHistoryBySerialNum(serialNumber);
                        if (history == null) {

                        } else {
                            if (error.size() < 100) {
                                error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_SERIAL_NUMBER + 1) + "^_^" + "列" + "【" + HEAD_SERIAL_NUMBER + "】" + "该流水号已存在");
                            }
                        }
                    } else {
                        if (error.size() < 100) {
                            error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_SERIAL_NUMBER + 1) + "^_^" + "列" + "【" + HEAD_SERIAL_NUMBER + "】" + "该流水号重复");
                        }
                    }
                }
            }else {
                body.set(NUM_SERIAL_NUMBER,null);
            }
            String idCard=body.get(NUM_ID_CARD);
            if (idCard.length()<=0 || idCard==null){
                if (error.size() < 100) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_ID_CARD + 1) + "^_^" + "列" + "【" + HEAD_ID_CARD + "】" + ERROR_IDCARD);
                }
            }
           /* String idCard = body.get(1);
            if (!CheckIdcard.isValid(idCard)) {
                if (error.size() < 100) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(1 + 1) + "^_^" + "列" + "【" + HEAD_ID_CARD + "】" + ERROR_IDCARD);
                }
            }*/
          /*  String phone = body.get(3);
            if (phone.length() != 0) {
                if (!Constants.PHONE_VALIDATOR.isValid(phone)) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(3 + 1) + "^_^" + "列" + "【" + HEAD_PHONE + "】" + "电话号码错误");
                }
            }*/

            String marketPrice = body.get(NUM_MARKET_PRICE);
            if (marketPrice.length() == 0) {
                body.set(NUM_MARKET_PRICE, "0");
            } else {
                try {
                    BigDecimal mPrice = new BigDecimal(marketPrice);
                    if (mPrice.compareTo(BigDecimal.ZERO) == -1) {
                        if (error.size() < 100) {
                            error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_MARKET_PRICE + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "不能是负数");
                        }
                    }
                    if (!type) {
                        mPrice = mPrice.negate();
                        body.set(NUM_MARKET_PRICE, mPrice.toString());
                    }
                } catch (NumberFormatException e) {
                    if (error.size() < 100) {
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_MARKET_PRICE + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "应该是大于零的数字");
                    }
                }
            }
            String salePrice = body.get(NUM_SALE_PRICE);
            if (salePrice.length() == 0) {
                body.set(NUM_SALE_PRICE, "0");
            } else {
                try {
                    BigDecimal sPrice = new BigDecimal(salePrice);
                    if (sPrice.compareTo(BigDecimal.ZERO) == -1) {
                        if (error.size() < 100) {
                            error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_SALE_PRICE + 1) + "^_^" + "列" + "【" + HEAD_SALE_PRICE + "】" + "不能是负数");
                        }
                    }
                    if (!type) {
                        sPrice = sPrice.negate();
                        body.set(NUM_SALE_PRICE, sPrice.toString());
                    }
                } catch (NumberFormatException e) {
                    if (error.size() < 100) {
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_SALE_PRICE + 1) + "^_^" + "列" + "【" + HEAD_SALE_PRICE + "】" + "应该是大于零的数字");
                    }
                }
            }
            String time = body.get(NUM_BUSINESS_TIME);
            try {
                DateUtil.fromString(time, "yyyy-MM-dd");
            } catch (IllegalArgumentException e) {
                if (error.size() < 100) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(NUM_BUSINESS_TIME + 1) + "^_^" + "列" + "【" + BUSINESS_TIME + "】" + "日期格式不对");
                }
            }
            if (!error.isEmpty()) {
                errorList.add(error);
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("head", head);
        map.put("content", excelList);
        map.put("error", errorList);
        map.put("fileName",excelName);
        return map;
    }


    @Override
    @Transactional
    public void batchGrand(Map<String, Object> excelAndErrorList, String paykey, Boolean type) {
        class RelationUpdate {
            private long enterpriseId;
            private long customerId;
            public RelationUpdate(long enterpriseId, long customerId) {
                this.enterpriseId = enterpriseId;
                this.customerId = customerId;
            }

            public long getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(long enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public long getCustomerId() {
                return customerId;
            }

            public void setCustomerId(long customerId) {
                this.customerId = customerId;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                RelationUpdate that = (RelationUpdate) o;

                if (enterpriseId != that.enterpriseId) return false;
                return customerId == that.customerId;

            }

            @Override
            public int hashCode() {
                int result = (int) (enterpriseId ^ (enterpriseId >>> 32));
                result = 31 * result + (int) (customerId ^ (customerId >>> 32));
                return result;
            }
        }

        class BatchUpdate {
            private long batchId;
            private int count;
            private BigDecimal fee;
            private Long enterpriseId;

            private BatchUpdate(long batchId, Long enterpriseId) {
                this.batchId = batchId;
                this.count = 0;
                this.fee = BigDecimal.ZERO;
                this.enterpriseId = enterpriseId;
            }

            private void sizePP() {
                this.count++;
            }

            private void feePP(BigDecimal fee) {
                this.fee = this.fee.add(fee);
            }

            public long getBatchId() {
                return batchId;
            }

            public void setBatchId(long batchId) {
                this.batchId = batchId;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public BigDecimal getFee() {
                return fee;
            }

            public void setFee(BigDecimal fee) {
                this.fee = fee;
            }

            public Long getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(Long enterpriseId) {
                this.enterpriseId = enterpriseId;
            }
        }


        class CustomerUcoinChange {
            private Long customerId;
            private Long enterpriseId;
            private BigDecimal price;

            public Long getCustomerId() {
                return customerId;
            }

            public void setCustomerId(Long customerId) {
                this.customerId = customerId;
            }

            public Long getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(Long enterpriseId) {
                this.enterpriseId = enterpriseId;
            }

            public BigDecimal getPrice() {
                return price;
            }

            public void setPrice(BigDecimal price) {
                this.price = price;
            }
        }

        //用户批量更新列表
        Map<Long,ChinapostCustomer> customerToUpdateMap = new HashMap<>();

        //企业用户关系列表
        Set<RelationUpdate> relationUpdateSet = new HashSet<>();

        //批量发放Map
        Map<String, BatchUpdate> batchToUpdateMap = new HashMap<>();

        //历史记录插入列表
        List<CustomerUcoinHistory> historyInsertList = new ArrayList<>();
        //会员邮豆变化
        List<CustomerUcoinChange> customerUcoinList = new ArrayList<>();

        Object object = excelAndErrorList.get("content");
        String fileName=(String)excelAndErrorList.get("fileName");

        List errorList = (List) excelAndErrorList.get("error");
        Assert.isEmpty(errorList, "EXCEL存在错误，无法发放。");
        @SuppressWarnings("unchecked")
        List<List<String>> lists = (List<List<String>>) object;

        Long id = currentLoginService.getCurrentLoginEnterpriseId();
        if (!enterpriseService.checkPaykey(id, paykey)) {
            throw new AuthorizationException("支付密码错误。");
        }





        int size = lists.size();
        Map<Object, Object> errorMap = new HashMap<>();
        Long creatorId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        System.out.println(new Date(System.currentTimeMillis()));

        Long batchCode=generator.generate();
        for (int i = 1; i < size; i++) {

            List<String> contents = lists.get(i);
            String organization = contents.get(NUM_ACCOUNT_NAME);
            String idCard = contents.get(NUM_ID_CARD);

            String serialNum=null;
            if (type) {
                serialNum = contents.get(NUM_SERIAL_NUMBER);
                if (serialNum != null) {
                    serialMap.put(serialNum, "");
                }
            }
            String customerName = contents.get(NUM_NAME);
            String contactPhone = contents.get(NUM_PHONE);
            BigDecimal marketPrice = new BigDecimal(contents.get(NUM_MARKET_PRICE));
            BigDecimal salePrice = StringUtil.isBlank(contents.get(NUM_SALE_PRICE)) ? BigDecimal.ZERO : new BigDecimal(contents.get(NUM_SALE_PRICE));
            BigDecimal price = marketPrice.add(salePrice);
            String time = contents.get(NUM_BUSINESS_TIME);
            Date businessTime = StringUtil.isBlank(time) ? new Date() : DateUtil.fromString(time, "yyyy-MM-dd");
            Long enterpriseId = enterpriseInfoService.getEnterpriseInfoByOrganization(organization).getEnterpriseId();
            if (enterpriseId == null) {
                errorMap.put(organization, "不存在");
                throw new UserOperationException("机构号不存在！");
            }

            BatchUpdate batchInfo = batchToUpdateMap.get(organization);

            Long batchId;
            if (batchInfo == null) {
                if (type) {
                    batchId = enterpriseBatchGrandService.addBatchGrand(enterpriseId, GRAND, null, BusinessType.TYPE_ID_GRAND,batchCode,fileName);
                    batchInfo = new BatchUpdate(batchId, enterpriseId);
                } else {
                    batchId = enterpriseBatchGrandService.addBatchGrand(enterpriseId, DECUT, null, BusinessType.TYPE_ID_DECUT,batchCode,fileName);
                    batchInfo = new BatchUpdate(batchId, enterpriseId);
                }
                batchToUpdateMap.put(organization, batchInfo);
            } else {
                batchId = batchInfo.getBatchId();
            }

            /***********************************************************************************************/

            //如果为新用户，保存用户
            Long customerId = customerMap.get(idCard);
            ChinapostCustomer customerToUpdate = new ChinapostCustomer();
            if (customerId == null) {
                ChinapostCustomer chinapostCustomer = chinapostCustomerService.getCustomer(idCard);
                if (chinapostCustomer != null) {
                    customerId = chinapostCustomer.getId();
                    customerToUpdate.setId(customerId);
                    customerToUpdate.setFullname(customerName);
                    customerToUpdate.setContactPhone(contactPhone);
                    //添加更新列表。
                    customerToUpdateMap.put(customerId,customerToUpdate);
                    relationUpdateSet.add(new RelationUpdate(enterpriseId, customerId));
                    //customerMap.put(idCard,customerId);
                } else {
                    customerId = chinapostCustomerService.addCustomer(idCard, customerName, contactPhone, null, null, null, null, null, null, enterpriseId, creatorId, null, null, null);
                }
            }else {
                customerToUpdate.setId(customerId);
                customerToUpdate.setFullname(customerName);
                customerToUpdate.setContactPhone(contactPhone);
                customerToUpdateMap.put(customerId,customerToUpdate);
                relationUpdateSet.add(new RelationUpdate(enterpriseId,customerId));
            }

            CustomerUcoinHistory history = new CustomerUcoinHistory();
            Long code = generator.generate();
            history.setSerialNum(serialNum);
            history.setCustomerId(customerId);
            history.setEnterpriseId(enterpriseId);
            history.setBatchId(batchId);
            history.setPrice(price);
            history.setMarketPrice(marketPrice);
            history.setSalesPrice(salePrice);
            history.setCode(code);
            history.setBusinessTime(businessTime);
            history.setCreateTime(new Date());
            if (type) {
                history.setType(HistoryType.ENTERPRISE_GRAND);
                history.setTypeId(BusinessType.TYPE_ID_GRAND);
            } else {
                history.setType(HistoryType.UCOIN_DEDUCT);
                history.setTypeId(BusinessType.TYPE_ID_DECUT);
            }

            //改变会员邮豆
            if(price.compareTo(BigDecimal.ZERO)!=0) customerUcoinService.changeUcoin(customerId,enterpriseId,price,new Date(),null,true);
            BigDecimal balance=customerUcoinService.getUcoinBalanceByEnterpriseIdAndEnterpriseId(customerId,enterpriseId);
            history.setBalancePrice(balance);
            historyInsertList.add(history);

            /***********************************************************************************************/
            batchInfo.feePP(price);
            batchInfo.sizePP();
        }



        //批量更新会员信息
        if (customerToUpdateMap.size() > 0) {
            chinapostCustomerService.updateByBatch(customerToUpdateMap);
        }

        //批量更新会员邮豆变更记录
        try {
            if(historyInsertList.size()>0) {
                customerUcoinHistoryService.insertByBatch(historyInsertList);
            }
        }catch (DuplicateKeyException e) {
            throw new UserOperationException("流水号不能重复！");
        }

        //批量插入企业会员关系
        for (RelationUpdate relationUpdate : relationUpdateSet) {
            customerEnterpriseService.addRelation(relationUpdate.getCustomerId(), relationUpdate.getEnterpriseId());
        }

        //扣减企业邮豆，更新发放记录
        Long managerId=currentLoginService.getCurrentLoginEnterpriseManagerId();
        String managerName=currentLoginService.getCurrentLoginUsername();
        for (String org : batchToUpdateMap.keySet()) {
            BatchUpdate batchUpdate = batchToUpdateMap.get(org);


            BigDecimal fee=batchUpdate.getFee();;
          /*  if(type){
                fee = batchUpdate.getFee();
            }else {
                fee=batchUpdate.getFee().negate();
            }*/

            enterpriseBatchGrandService.updateFee(batchUpdate.getBatchId(), fee.abs(), batchUpdate.getCount(),managerId,managerName);

            enterpriseService.changeEnterpriseUcoin(batchUpdate.getEnterpriseId(), fee.negate());
            EnterpriseFunction function=enterpriseFunctionService.get(batchUpdate.getEnterpriseId());
            EnterpriseUcoinHistory history=new EnterpriseUcoinHistory();
            history.setBalancePrice(function.getUndistributedPrice());
            history.setEnterpriseId(batchUpdate.getEnterpriseId());
            history.setCreateTime(new Date());
            enterpriseUcoinHistoryMapper.insertSelective(history);
        }
        if (!errorMap.isEmpty()) {
            throw new GrandException(errorMap);
        }

    }

    @Override
    @Transactional
    public void checkBalance(Map<String, Object> map,Boolean type) {
        class BatchUpdate {
            private int count;
            private BigDecimal fee;
            private Long enterpriseId;

            private BatchUpdate( Long enterpriseId) {
                this.count = 0;
                this.fee = BigDecimal.ZERO;
                this.enterpriseId = enterpriseId;
            }

            private void sizePP() {
                this.count++;
            }

            private void feePP(BigDecimal fee) {
                this.fee = this.fee.add(fee);
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public BigDecimal getFee() {
                return fee;
            }

            public void setFee(BigDecimal fee) {
                this.fee = fee;
            }

            public Long getEnterpriseId() {
                return enterpriseId;
            }

            public void setEnterpriseId(Long enterpriseId) {
                this.enterpriseId = enterpriseId;
            }
        }


        Object object=map.get("content");
        List<List<String>> excelList=(List<List<String>>)object;
        //批量发放的map
        Map<String, BatchUpdate> batchToUpdateMap = new HashMap<>();

        Map<String,String> errorMap=new HashMap<>();


        for(int i=1;i<excelList.size();i++){
            List<String> contents=excelList.get(i);
            String organization=contents.get(NUM_ACCOUNT_NAME);
            BigDecimal marketPrice = new BigDecimal(contents.get(NUM_MARKET_PRICE));
            BigDecimal salePrice = StringUtil.isBlank(contents.get(NUM_SALE_PRICE)) ? BigDecimal.ZERO : new BigDecimal(contents.get(NUM_SALE_PRICE));
            BigDecimal price = marketPrice.add(salePrice);

            Long enterpriseId = enterpriseMap.get(organization);
            BatchUpdate batchInfo = batchToUpdateMap.get(organization);

            if (batchInfo == null) {
                batchInfo = new BatchUpdate(enterpriseId);
                batchToUpdateMap.put(organization, batchInfo);
            }

            batchInfo.feePP(price);
            batchInfo.sizePP();

        }

        for (String org : batchToUpdateMap.keySet()) {
            BatchUpdate batchUpdate = batchToUpdateMap.get(org);
            BigDecimal fee = batchUpdate.getFee();
            Long enterpriseId=enterpriseMap.get(org);
            EnterpriseInfo enterpriseInfo=enterpriseInfoService.getEnterpriseInfo(enterpriseId);
            String enterpriseName=enterpriseInfo.getEnterpriseName();
            EnterpriseFunction function=enterpriseFunctionService.get(enterpriseId);
            BigDecimal undistributePrice=function.getUndistributedPrice();
            if(type){
            if (undistributePrice.compareTo(BigDecimal.ZERO)==-1){
                errorMap.put(org,enterpriseName+"("+org.toString()+")"+"已经透支"+undistributePrice.negate()+"邮豆! 还差"+fee+"邮豆");
            }else if(fee.compareTo(undistributePrice)==1){
                errorMap.put(org,enterpriseName+"("+org.toString()+")"+"余额不足! "+"还差"+fee.add(undistributePrice.negate())+"邮豆");
            }}
        }
        if(!errorMap.isEmpty()){
            throw new GrandException(errorMap);
        }

    }




    @Deprecated
    private BigDecimal doGrand(long batchId, List<String> content, Boolean type, Map<Object, Object> map, Long creatorId, Long enterpriseId) {
        String accountName = content.get(0);
        String customerName = content.get(2);
        String idCard = content.get(1);
        String contactPhone = content.get(3);
        BigDecimal salePrice = StringUtil.isBlank(content.get(5)) ? BigDecimal.ZERO : new BigDecimal(content.get(5));
        //如果为新用户，保存用户
        if (ucoinGrandService.isNewCustomer(idCard)) {
            chinapostCustomerService.addCustomer(idCard, customerName, contactPhone, null, null, null, null, null, null, enterpriseId, creatorId, null, null, null);
        } else {
            ChinapostCustomer exitCustomer = chinapostCustomerService.getCustomer(idCard);
            chinapostCustomerService.editCustomer(exitCustomer.getId(), customerName, contactPhone, null, null, null, null, null, null, null, null, null);
            customerEnterpriseService.addRelation(exitCustomer.getId(), enterpriseId);
        }
        Long id = chinapostCustomerMapper.selectId(idCard);

        BigDecimal marketPrice;
        BigDecimal price = BigDecimal.ZERO;

        marketPrice = new BigDecimal(content.get(4));

        if (marketPrice.compareTo(BigDecimal.ZERO) != 0) {
            price = marketPrice.add(salePrice);
        }

        EnterpriseFunction function = enterpriseService.getEnterpriseFunction(enterpriseId);
        BigDecimal enterpriseResePrice = function.getUndistributedPrice();
        EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(enterpriseId);
        String name = enterpriseInfo.getEnterpriseName();
        if (enterpriseResePrice.compareTo(price) == -1) {
            map.put(accountName, name);
            throw new UserOperationException("余额不足！");
        }
        //添加U宝
        String time = content.get(6);
        Date createDate;
        if (StringUtil.isBlank(time)) {
            createDate = new Date();
        } else {
            createDate = DateUtil.fromString(time, "yyyy-MM-dd");
        }

        if (price.compareTo(BigDecimal.ZERO) != 0) {
            customerUcoinService.changeUcoin(id, enterpriseId, price, createDate, null, true);
            BigDecimal balancePrice=customerUcoinService.getUcoinBalance(id);
            if (type) {
                customerUcoinHistoryService.addHistory(id, enterpriseId, batchId, HistoryType.ENTERPRISE_GRAND, null,price, marketPrice, salePrice,balancePrice, BusinessType.TYPE_ID_GRAND, null, null, null,null);
            } else {
                customerUcoinHistoryService.addHistory(id, enterpriseId, batchId, HistoryType.UCOIN_DEDUCT, null,price.negate(), marketPrice.negate(), salePrice.negate(),balancePrice,BusinessType.TYPE_ID_DECUT, null, null, null,null);
            }
        }

        //扣款
        enterpriseService.addUcoinAmount(enterpriseId, price.negate());
        return price;
    }


    private void checkHead(List<String> head) {
        if (head.size() != 8 || !Objects.equals(head.get(NUM_ACCOUNT_NAME), HEAD_ACCOUNT_NAME)
                || !Objects.equals(head.get(NUM_SERIAL_NUMBER),HEAD_SERIAL_NUMBER)
                ||!Objects.equals(head.get(NUM_ID_CARD), HEAD_ID_CARD)
                || !Objects.equals(head.get(NUM_NAME), HEAD_NAME)
                || !Objects.equals(head.get(NUM_PHONE), HEAD_PHONE)
                || !Objects.equals(head.get(NUM_MARKET_PRICE), HEAD_MARKET_PRICE)
                || !Objects.equals(head.get(NUM_SALE_PRICE), HEAD_SALE_PRICE)
                || !Objects.equals(head.get(NUM_BUSINESS_TIME), BUSINESS_TIME)) {
            throw new UserOperationException("模板不匹配，请重新下载");
        }
    }


    @Override
    public Page<EnterpriseBatchGrand> getMyGrand(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        int typeId = -100;
        return enterpriseBatchGrandService.getBatchGrands(code, enterpriseId, start, end, typeId, pageable);

    }

    @Override
    public Page<EnterpriseBatchGrand> getMyDecut(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        int typeId = -200;
        return enterpriseBatchGrandService.getBatchGrands(code, enterpriseId, start, end, typeId, pageable);
    }

    @Override
    public Page<CurrEnterpriseBill> getEnterpriseIdOrderSubsidy(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseBatchGrandService.getEnterpriseIdOrderSubsidy(code,enterpriseId,start,end,pageable);
    }

    @Override
    public Page<CurrEnterpriseBill> getEnterpriseIdBackOrderSubsidy(Long code, Date start, Date end, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return enterpriseBatchGrandService.getEnterpriseIdBackOrderSubsidy(code,enterpriseId,start,end,pageable);
    }
}
