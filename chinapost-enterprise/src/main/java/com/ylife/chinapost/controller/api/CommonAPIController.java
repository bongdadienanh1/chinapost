package com.ylife.chinapost.controller.api;

import com.ylife.address.model.City;
import com.ylife.address.model.District;
import com.ylife.address.model.Province;
import com.ylife.address.model.Street;
import com.ylife.address.service.AddressService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/21.
 * <p/>
 * 通用信息获取控制器。
 */
@Controller
@RequestMapping(value = "/web/api/common", produces = "application/json;charset=utf-8")
public class CommonAPIController {

    @Resource
    private AddressService addressService;
    @Resource
    private UcoinGrandService ucoinGrandService;

    private Generator generator = IdGeneratorFactory.create("Goods_Info_Code", IdGeneratorFactory.SPartSize.TWO, IdGeneratorFactory.NPartSize.ONE);


    /**
     * 获取所有省份。
     */
    @RequestMapping("/provincies")
    @ResponseBody
    public String getProvincies() {
        List<Province> provinces = addressService.getProvinces();
        return new JsonResponseBean(provinces).toJson();
    }

    /**
     * 根据省ID获取所有城市。
     */
    @RequestMapping("/cities")
    @ResponseBody
    public String getCities(@RequestParam("provinceId") long provinceId) {
        List<City> cities = addressService.getCities(provinceId);
        return new JsonResponseBean(cities).toJson();
    }

    /**
     * 根据市ID获取所有区域。
     */
    @RequestMapping("/districts")
    @ResponseBody
    public String getDistricts(@RequestParam("cityId") long cityId) {
        List<District> districts = addressService.getDistricts(cityId);
        return new JsonResponseBean(districts).toJson();
    }

    /**
     * 获取所有省份。
     */
    @RequestMapping("/streets")
    @ResponseBody
    public String getStreets(@RequestParam("districtId") long districtId) {
        List<Street> streets = addressService.getStreets(districtId);
        return new JsonResponseBean(streets).toJson();
    }

    /**
     * 是否为新用户。
     *
     * @param idCard 身份证号
     */
    @RequestMapping("/isNew")
    @ResponseBody
    public String isNew(@RequestParam("idCard") String idCard) {
        Assert.hasLength(idCard);
        boolean isNew = ucoinGrandService.isNewCustomer(idCard);
        return new JsonResponseBean(isNew).toJson();
    }

    /**
     * 获取所有省份。
     */
    @RequestMapping("/address")
    @ResponseBody
    public String getAddress() {
        Map<String, Object> map = new HashMap<>();
        List<Province> provinces = addressService.getProvinces();
        for (Province province : provinces) {
            Map<String, Object> provinceMap = new HashMap<>();
            Map<String, Object> cityMap = new HashMap<>();
            List<City> cities = addressService.getCities(province.getProvinceId());
            for (City city : cities) {
                Map<String, Object> districtMap = new HashMap<>();
                List<District> districts = addressService.getDistricts(city.getCityId());
                districtMap.put("cityId", city.getCityId());
                districtMap.put("cityName", city.getCityName());
                districtMap.put("content", districts);
                cityMap.put(city.getCityId().toString(), districtMap);
            }
            provinceMap.put("provinceId", province.getProvinceId());
            provinceMap.put("provinceName", province.getProvinceName());
            provinceMap.put("content", cityMap);
            map.put(province.getProvinceId().toString(), provinceMap);
        }
        return new JsonResponseBean(map).toJson();
    }

    /**
     * 是否为新用户。
     *
     * @param count 生成
     */
    @RequestMapping("/createGenerator")
    @ResponseBody
    public String createGenerator(int count) {
        String[] str = new String[count];
        for (int i=0;i<count;i++){
            str[i]= String.valueOf(generator.generate());
        }
        return new JsonResponseBean(str).toJson();
    }

}
