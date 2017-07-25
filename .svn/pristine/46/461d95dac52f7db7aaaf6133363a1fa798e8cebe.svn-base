package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.SystemManageService;
import com.ylife.chinapost.service.pojo.ParamValueInfo;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.system.model.*;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/7.
 * 系统设置控制器
 */
@Controller
@SecurityResource(parent = "/web/businessType", primary = false)
@RequestMapping(value = "/web/api/sysmanage", produces = "application/json;charset=utf-8")
public class SystemManageController {

    @Resource
    private SystemManageService systemManageService;

    /**
     * 获取业务类型变动记录。
     */
    @RequestMapping(value = "/getHistories")
    @ResponseBody
    public String getHistories(@RequestParam(value = "typeId", required = false) Integer typeId,
                               @RequestParam(value = "start", required = false) String start,
                               @RequestParam(value = "end", required = false) String end,
                               @RequestParam(value = "page", required = false, defaultValue = "5") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "5") int size) {
        Date startTime = null;
        Date endTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<BusinessTypeHistory> list = systemManageService.getHistories(typeId, startTime, endTime, new Pageable(page, size));
        return new JsonResponseBean(list).toJson();
    }

    /**
     * 获取所有业务类型。
     */
    @RequestMapping(value = "/getAllTypes")
    @ResponseBody
    public String getAll() {
        List<BusinessType> types = systemManageService.getAll();
        return new JsonResponseBean(types).toJson();
    }

    /**
     * 获取可以参与计算的参数
     */
    @RequestMapping(value = "/getComputableParams")
    @ResponseBody
    public String getComputableParam(@RequestParam("typeId") int typeId) {
        List<Param> params = systemManageService.getComputableParam(typeId);
        return new JsonResponseBean(params).toJson();
    }

    /**
     * 添加业务类型
     *
     * @param name 业务类型名称
     */
    @RequestMapping(value = "/addBussinessType")
    @ResponseBody
    public String addBusinessType(@RequestParam("name") String name, @RequestParam(value = "serialNo", required = false) Integer serialNo) {
        Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR3, name, "名称格式不正确。名称格式为长度1~15的中文英文数字组合，不能为纯数字。");
        BusinessType type = systemManageService.addBusinessType(name, serialNo);
        return new JsonResponseBean(type).toJson();
    }

    /**
     * 编辑业务类型。
     *
     * @param typeId 业务类型ID
     * @param name   业务类型名称
     */
    @RequestMapping(value = "/editBussinessType")
    @ResponseBody
    public String editBusinessType(@RequestParam("typeId") int typeId,
                                   @RequestParam("name") String name,
                                   @RequestParam("enabled") Boolean enabled) {
        Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR3, name, "名称格式不正确。名称格式为长度1~15的中文英文数字组合，不能为纯数字。");
        systemManageService.editBusinessType(typeId, name, enabled);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 删除业务类型。
     *
     * @param typeId 业务类型ID
     */
    @RequestMapping(value = "/deleteBussinessType")
    @ResponseBody
    public String deleteBusinessType(@RequestParam("typeId") int typeId) {
        systemManageService.deleteBusinessType(typeId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 添加参数。
     *
     * @param typeId     业务类型ID
     * @param paramType  参数类型
     * @param name       参数名
     * @param required   参数是否必需填
     * @param valuesJson 参数值集合
     */
    @RequestMapping(value = "/addParam")
    @ResponseBody
    public String addParam(@RequestParam("typeId") int typeId,
                           @RequestParam("paramType") ParamType paramType,
                           @RequestParam("name") String name,
                           @RequestParam("required") boolean required,
                           @RequestParam(value = "serialNo", required = false) Integer serialNo,
                           @RequestParam("valuesJson") String valuesJson) {
        Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR3, name, "名称格式不正确。名称格式为长度1~15的中文英文数字组合，不能为纯数字。");
        List<ParamValueInfo> valueInfos = Constants.SIMPLE_PARSER.parseJSON(valuesJson, new TypeToken<List<ParamValueInfo>>() {
        });
        Param param = systemManageService.addParam(typeId, paramType, name, required, serialNo, valueInfos);
        return new JsonResponseBean(param).toJson();
    }

    /**
     * 编辑参数。
     *
     * @param paramId  参数ID
     * @param name     参数名称
     * @param required 参数是否必需填
     * @param enabled  是否启用
     */
    @RequestMapping(value = "/editParam")
    @ResponseBody
    public String editParam(@RequestParam("paramId") int paramId,
                            @RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "required", required = false) Boolean required,
                            @RequestParam(value = "enabled", required = false) Boolean enabled) {
        name = StringUtil.nullOrNotBlank(name);
        if (name != null) {
            Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR3, name, "名称格式不正确。名称格式为长度1~15的中文英文数字组合，不能为纯数字。");
        }
        systemManageService.editParam(paramId, name, required, enabled);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 增加参数值。
     */
    @RequestMapping(value = "/addParamValue")
    @ResponseBody
    public String addParamValue(@RequestParam("paramId") int paramId,
                                @RequestParam("name") String name,
                                @RequestParam("value") BigDecimal value,
                                @RequestParam("enabled") boolean enabled,
                                @RequestParam(value = "serialNo", required = false) Integer serialNo) {
        Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR2, name, "名称格式不正确，名称格式为1~15位数字字母中文字符组合。");
        ParamValue value1 = systemManageService.addParamValue(paramId, name, value, enabled, serialNo);
        return new JsonResponseBean(value1).toJson();
    }

    /**
     * 删除参数。
     */
    @RequestMapping(value = "/deleteParam")
    @ResponseBody
    public String deleteParam(@RequestParam("paramId") int paramId) {
        systemManageService.deleteParam(paramId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 编辑参数值。
     */
    @RequestMapping(value = "/editParamValue")
    @ResponseBody
    public String editParamValue(@RequestParam("paramId") int valueId,
                                 @RequestParam("name") String name,
                                 @RequestParam("value") BigDecimal value,
                                 @RequestParam("enabled") Boolean enabled) {
        Assert.isValid(Constants.SYSTEM_NAME_VALIDATOR2, name, "名称格式不正确，名称格式为1~15位数字字母中文字符组合。");
        systemManageService.editParamValue(valueId, name, value, enabled);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 删除参数值。
     *
     * @param valueId 参数值ID
     */
    @RequestMapping(value = "/deleteParamValue")
    @ResponseBody
    public String deleteParamValue(@RequestParam("valueId") int valueId) {
        systemManageService.deleteParamValue(valueId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 添加公式
     *
     * @param typeId     业务类型
     * @param expression 公式
     */
    @RequestMapping(value = "/editExpression")
    @ResponseBody
    public String editExpression(@RequestParam("typeId") int typeId,
                                 @RequestParam("expression") String expression,
                                 @RequestParam(value = "note", required = false) String note) {
        Assert.hasLength(expression, "公式不能为空。");
        systemManageService.editExpression(typeId, expression, note);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 更新业务类型顺序。
     */
    @RequestMapping(value = "/updateTypeSerial")
    @ResponseBody
    public String updateTypeSerial(@RequestParam("serialJson") String serialJson) {
        Map<Integer, Integer> map = Constants.SIMPLE_PARSER.parseJSON(serialJson, new TypeToken<Map<Integer, Integer>>() {
        });
        systemManageService.updateTypeSerial(map);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 更新参数顺序。
     */
    @RequestMapping(value = "/updateParamSerial")
    @ResponseBody
    public String updateParamSerial(@RequestParam("serialJson") String serialJson) {
        Map<Integer, Integer> map = Constants.SIMPLE_PARSER.parseJSON(serialJson, new TypeToken<Map<Integer, Integer>>() {
        });
        systemManageService.updateParamSerial(map);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 更新参数值顺序。
     */
    @RequestMapping(value = "/updateValueSerial")
    @ResponseBody
    public String updateParamValueSerial(@RequestParam("serialJson") String serialJson) {
        Map<Integer, Integer> map = Constants.SIMPLE_PARSER.parseJSON(serialJson, new TypeToken<Map<Integer, Integer>>() {
        });
        systemManageService.updateParamValueSerial(map);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
}
