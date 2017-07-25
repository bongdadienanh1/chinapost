package com.ylife.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by InThEnd on 2016/7/15.
 * classpath目录下linux风格的conf配置文件工具。
 */
public class ClasspathConfHelper {

    private Map<String, String> confMap = new HashMap<>();

    public ClasspathConfHelper(String name) {
        InputStream fis;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(name);
            Assert.notNull(fis, "classpath项目根目录下：" + name + "文件不存在。");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String s;
            while ((s = reader.readLine()) != null) {
                s = s.replaceAll("\\s*", "");
                if (!StringUtil.isBlank(s)) {
                    String[] kv = s.split("=");
                    if (kv.length != 2) {
                        throw new RuntimeException("配置文件中的行：" + "\"" + s + "\"" + "格式错误，无法解析。");
                    }
                    confMap.put(kv[0], kv[1]);
                }
            }
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<String> allKeys() {
        return confMap.keySet();
    }

    public String getValue(String key) {
        return confMap.get(key);
    }

}
