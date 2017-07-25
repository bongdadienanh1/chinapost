package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.mapper.CustomerTagMapper;
import com.ylife.customer.mapper.CustomerWithUcoinInfoMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.model.CustomerTagKey;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerEnterpriseService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/8.
 * <p/>
 * ChinapostCustomerServiceImpl
 */
@Service
public class ChinapostCustomerServiceImpl implements ChinapostCustomerService {

    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;
    @Resource
    private CustomerTagMapper customerTagMapper;
    @Resource
    private CustomerEnterpriseService customerEnterpriseService;
    @Resource
    private CustomerWithUcoinInfoMapper customerWithUcoinInfoMapper;

    @Override
    public ChinapostCustomer getCustomer(String idCardNo) {
        return chinapostCustomerMapper.selectByIdCard(idCardNo);
    }

    @Override
    public ChinapostCustomer getCustomer(Long customerId) {
        return chinapostCustomerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public boolean isExist(String idCardNo) {
        Long id = chinapostCustomerMapper.selectId(idCardNo);
        return id != null;
    }

    @Override
    @Transactional
    public long addCustomer(String idCard,
                            String name,
                            String linkPhone,
                            Long provinceId,
                            Long cityId,
                            Long districtId,
                            String addr,
                            String image,
                            String managerNo,
                            Long enterpriseId,
                            Long creatorId,
                            List<Integer> tags,
                            String remark,
                            String contactAddr) {
        ChinapostCustomer customer = new ChinapostCustomer();
        customer.setIdcardNo(idCard);
        customer.setFullname(name);
        customer.setContactPhone(linkPhone);
        customer.setManagerNo(managerNo);
        customer.setProvinceId(provinceId);
        customer.setCityId(cityId);
        customer.setDistrictId(districtId);
        customer.setAddr(addr);
        customer.setImgUrl(image);
        customer.setEnterpriseId(enterpriseId);
        customer.setCreatorId(creatorId);
        customer.setContactAddr(contactAddr);
        customer.setRemark(remark);
        Date now = new Date();
        customer.setCreateTime(now);

        //TODO 地址操作
        customer.setContactAddr(contactAddr);
        try {
            chinapostCustomerMapper.insertSelective(customer);
        } catch (DuplicateKeyException e) {
            throw new UserOperationException("用户已存在。");
        }
        long id=customer.getId();
        if (tags != null) {
            for (Integer tagId : tags) {
                CustomerTagKey key = new CustomerTagKey();
                key.setCustomerId(id);
                key.setTagId(tagId);
                customerTagMapper.insert(key);
            }
        }
        customerEnterpriseService.addRelation(customer.getId(), enterpriseId);
        return id;
    }

    @Override
    public void editCustomer(long id,
                             String name,
                             String linkPhone,
                             Long provinceId,
                             Long cityId,
                             Long districtId,
                             String addr,
                             String managerNo,
                             List<Integer> tags,
                             String remark,
                             String contactAddr,
                             String imgUrl) {
        ChinapostCustomer customer = new ChinapostCustomer();
        customer.setId(id);
        customer.setFullname(name);
        customer.setContactPhone(linkPhone);
        customer.setManagerNo(managerNo);
        customer.setProvinceId(provinceId);
        customer.setCityId(cityId);
        customer.setDistrictId(districtId);
        customer.setAddr(addr);
        customer.setImgUrl(imgUrl);
        customer.setRemark(remark);
        //TODO 地址操作
        customer.setContactAddr(contactAddr);
        chinapostCustomerMapper.updateByPrimaryKeySelective(customer);
        if (tags != null) {
            //删除所有关系
            customerTagMapper.deleteByCustomerId(id);
            //重新建立关系
            for (Integer tagId : tags) {
                CustomerTagKey key = new CustomerTagKey();
                key.setCustomerId(customer.getId());
                key.setTagId(tagId);
                customerTagMapper.insert(key);
            }
        }
    }

    @Override
    public void activateCustomer(String idCardNo, String password) {
        ChinapostCustomer customer = new ChinapostCustomer();
        customer.setIdcardNo(idCardNo);
        customer.setPassword(password);
        chinapostCustomerMapper.updateByIdCardSelective(customer);
    }

    @Override
    public void activateCustomer(long id, String password) {
        ChinapostCustomer customer = new ChinapostCustomer();
        customer.setId(id);
        customer.setPassword(password);
        customer.setIsActive(true);
        chinapostCustomerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void setPaymentPassword(String idCardNo, String paykey) {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setIdcardNo(idCardNo);
        model.setPaykey(paykey);
        chinapostCustomerMapper.updateByIdCardSelective(model);
    }

    @Override
    public void setLoginPassword(long id, String password) {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setId(id);
        model.setPassword(password);
        chinapostCustomerMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void setPaymentPassword(long id, String paykey) {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setId(id);
        model.setPaykey(paykey);
        chinapostCustomerMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public boolean checkPaykey(long customerId, String paykey) {
        return chinapostCustomerMapper.checkPaykey(customerId, paykey);
    }

    @Override
    public boolean checkpassword(long customerId, String password) {
        return chinapostCustomerMapper.checkpassword(customerId, password);
    }

    @Override
    public void updatePassword(long id, String password) {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setId(id);
        model.setPassword(password);
        chinapostCustomerMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void updateByBatch(Map<Long,ChinapostCustomer> map) {
            chinapostCustomerMapper.updateByBatch(map);
    }

    /**
     * 编辑
     * @param male
     * @param contactPhone
     */
    @Override
    public void updateMaleAndPhone(Long id,boolean male, String contactPhone,String phoneNo) {
        ChinapostCustomer model = new ChinapostCustomer();
        model.setId(id);
        model.setContactPhone(contactPhone);
        model.setMale(male);
        model.setPhoneNo(phoneNo);
        chinapostCustomerMapper.updateByPrimaryKeySelective(model);
    }


}
