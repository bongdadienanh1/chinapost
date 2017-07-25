package com.ylife.client.service;

import com.ylife.utils.ClasspathPropertiesHelper;
import com.ylife.utils.DateUtil;
import com.ylife.utils.HttpUtil;
import com.ylife.utils.MD5Util;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/6.
 * <p/>
 * 开放平台客户端。
 */
public class PlatformClient {

    private static final String USERNAME_KEY = "userName";
    private static final String SIGN_KEY = "sign";
    private static final String TIME_KEY = "timestamp";
    private static String urlKey;
    private static String userName;
    private static String syncToken;

    static{
        ClasspathPropertiesHelper tt = new ClasspathPropertiesHelper("sync.properties");
        userName = tt.getProperty("USERNAME_KEY");
        urlKey = tt.getProperty("URL_KEY");
        syncToken = tt.getProperty("SYNC_TOKEN");
    }


    public static String post(final String url, final Map<String, String> values) throws IOException {
        addSecretKey(values);
        return HttpUtil.doPost(urlKey + url, values);
    }

    public static String get(final String url, final Map<String, String> values) throws IOException {
        addSecretKey(values);
        return HttpUtil.doGet(urlKey + url, values);
    }

    private static void addSecretKey(final Map<String, String> values) {
        values.put(USERNAME_KEY, userName);
        values.put(SIGN_KEY, MD5Util.md5Hex(userName + DateUtil.formatToString(new Date(), "yyyyMMddHHmmss") + syncToken));
        values.put(TIME_KEY, DateUtil.formatToString(new Date(), "yyyyMMddHHmmss"));
    }


}
