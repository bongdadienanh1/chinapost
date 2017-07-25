package com.ylife.address.service;

import com.ylife.address.mapper.CityMapper;
import com.ylife.address.mapper.DistrictMapper;
import com.ylife.address.mapper.ProvinceMapper;
import com.ylife.address.mapper.StreetMapper;
import com.ylife.address.model.City;
import com.ylife.address.model.District;
import com.ylife.address.model.Province;
import com.ylife.address.model.Street;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/23.
 * <p/>
 * AddressServiceImpl
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private ProvinceMapper provinceMapper;
    @Resource
    private CityMapper cityMapper;
    @Resource
    private DistrictMapper districtMapper;
    @Resource
    private StreetMapper streetMapper;


    @Override
    public List<Province> getProvinces() {
        return provinceMapper.selectAll();
    }

    @Override
    public List<City> getCities(long provinceId) {
        return cityMapper.selectByProvinceId(provinceId);
    }

    @Override
    public List<District> getDistricts(long cityId) {
        return districtMapper.selectByCityId(cityId);
    }

    @Override
    public List<Street> getStreets(long districtId) {
        return streetMapper.selectByDistrictId(districtId);
    }

    @Override
    public String getAddressString(Long provinceId, Long cityId, Long districtId, String addressDetail) {
        String contactAddr = "";
        //省
        if (provinceId != null) {
            Province province = provinceMapper.selectByPrimaryKey(provinceId);
            if (province != null) {
                contactAddr = contactAddr.concat(province.getProvinceName());
            }
        }
        //市
        if (cityId != null) {
            City city = cityMapper.selectByPrimaryKey(cityId);
            if (city != null) {
                contactAddr = contactAddr.concat(city.getCityName());
            }
        }
        //县
        if (districtId != null) {
            District district = districtMapper.selectByPrimaryKey(districtId);
            if (district != null) {
                contactAddr = contactAddr.concat(district.getDistrictName());
            }
        }
        //详细地址
        if (addressDetail != null) {
            contactAddr = contactAddr.concat(addressDetail);
        }
        return contactAddr;
    }

    @Override
    public Province getProvinceByName(String provinceName) {
        return provinceMapper.selectByName(provinceName);
    }

    @Override
    public City getCityByName(Long provinceId, String cityName) {
        return cityMapper.selectByName(provinceId,cityName);
    }

    @Override
    public District getDistrictByName(Long cityId, String districtName) {
        return districtMapper.selectByName(cityId,districtName);
    }
}
