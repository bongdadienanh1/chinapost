package com.ylife.customer.service.impl;

import com.ylife.customer.mapper.CustomerAddressMapper;
import com.ylife.customer.model.CustomerAddress;
import com.ylife.customer.service.CustomerAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年8月20日 下午1:54:29
 */
@Service("customerAddressService")
public class CustomerAddressServiceImpl implements CustomerAddressService {

    @Resource
    private CustomerAddressMapper customerAddressMapper;

    @Override
    public List<CustomerAddress> queryCustomerAddress(Long customerId) {
        return customerAddressMapper.selectAllCustomerAddressByCustomerId(customerId);
    }

    @Override
    public CustomerAddress queryCustomerAddressById(Long addressId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("addressId", addressId);
        paramMap.put("customerId", customerId);
        return customerAddressMapper.queryCustomerAddressById(paramMap);
    }

    @Override
    public CustomerAddress queryDefaultAddr(Long customerId) {
        return customerAddressMapper.selectDefaultAddr(customerId);
    }

    @Override
    public int updateAddress(CustomerAddress address, Long customerId) {
        address.setCustomerId(customerId);
        return customerAddressMapper.updateAddress(address);
    }

    /**
     * 修改用户的地址为 0 默认
     *
     * @param customerId
     */
    @Override
    public int updateAddressDef(Long customerId) {
        return customerAddressMapper.updateAddressDef(customerId);
    }

    @Override
    public CustomerAddress selectByCIdFirst(Long customerId) {
        return customerAddressMapper.selectByCIdFirst(customerId);
    }

    @Override
    public long addAddress(CustomerAddress address, Long customerId) {
        address.setCustomerId(customerId);
        customerAddressMapper.insertSelective(address);
        return address.getAddressId();
    }

    @Override
    public void deleteAddress(Long addressId) {
        customerAddressMapper.deleteAddressById(addressId);

    }
}
