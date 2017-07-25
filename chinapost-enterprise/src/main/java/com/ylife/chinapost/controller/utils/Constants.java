package com.ylife.chinapost.controller.utils;

import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.utils.DateUtil;
import com.ylife.utils.StringUtil;
import org.apache.commons.validator.routines.RegexValidator;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by InThEnd on 2016/4/26.
 * 常量
 */
public abstract class Constants {

    public static final String DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String DEFAULT_DAY_FORMAT = "yyyy-MM-dd";

    public static final Parser SIMPLE_PARSER = new SimpleParser();

    public static final String JS_VERSION = "0.210";

    /**
     * 用户名格式验证
     */
    public static final RegexValidator USERNAME_VALIDATOR = new RegexValidator("^[a-zA-Z0-9]{1}[a-zA-Z0-9|-|_]{2,14}[a-zA-Z0-9]{1}$");

    /**
     * 密码格式验证
     */
    public static final RegexValidator PASSWORD_VALIDATOR = new RegexValidator("^[0-9a-zA-Z]{6,16}$");

    /**
     * 邮箱格式验证
     */
    public static final RegexValidator EMAIL_VALIDATOR = new RegexValidator("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    /**
     * 手机号格式验证
     */
    public static final RegexValidator PHONE_VALIDATOR = new RegexValidator("^(0|86|17951)?(13[0-9]|15[012356789]|18[0-9]|14[57]|177)[0-9]{8}$");

    /**
     * 中文，英文，数字的组合，首位不能为数字，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR = new RegexValidator("^[a-zA-Z\u4e00-\u9fa5]{1}[a-zA-Z0-9\u4e00-\u9fa5]{1,14}$");

    /**
     * 中文，英文，数字的组合，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR2 = new RegexValidator("^[a-zA-Z0-9\u4e00-\u9fa5]{1,15}$");

    /**
     * 中文，英文，数字的组合，不能为纯数字，1-15位
     */
    public static final RegexValidator SYSTEM_NAME_VALIDATOR3 = new RegexValidator("^[[a-zA-Z0-9\u4e00-\u9fa5]*[a-zA-Z\u4e00-\u9fa5][a-zA-Z0-9\u4e00-\u9fa5]*]{1,15}$");


    public static Date nullOrMorning(String date) {
        Date time = null;
        if (!StringUtil.isBlank(date)) {
            time = DateUtil.fromString(date, DEFAULT_DATE_FORMAT);
            time = DateUtil.getMorning(time);
        }
        return time;
    }

    public static Date nullOrNight(String date) {
        Date time = null;
        if (!StringUtil.isBlank(date)) {
            time = DateUtil.fromString(date, DEFAULT_DATE_FORMAT);
            time = DateUtil.getNight(time);
        }
        return time;
    }

    public static String nullOrNotBlank(String arg) {
        if (StringUtil.isBlank(arg))
            return null;
        else
            return arg;
    }

    public static String figureToString(Integer data){
        if(data==null)
            return "";
        else
            return data.toString();

    }


    public static String figureToString(BigDecimal data){
        if(data==null)
            return "";
        else
            return data.toString();

    }

    public static String defaultToString(BigDecimal data){
        if(data==null)
            return "0.00";
        else
            return data.setScale(2).toString();
    }

    public static String amountToString(Integer data){
        if(data==null)
            return "0";
        else
            return data.toString();

    }

    public static String nullString(String arg) {
        if (StringUtil.isBlank(arg))
            return "";
        else
            return arg;
    }

}
