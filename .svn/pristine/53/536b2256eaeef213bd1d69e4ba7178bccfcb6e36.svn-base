package com.ylife.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据实体bean生成Map
 *
 * @author zhangyue
 * @version 创建时间：2013年11月11日 上午11:18:29
 */
public final class MapUtil {

    private MapUtil() {
    }

    /**
     * 通过bean产生map对象：如map.put("username",username);map.put("password",password),
     * key 值为bean的属性名 value 为bean的属性值
     *
     * @param bean 实体bean
     * @return Map
     */
    public static Map<String, Object> getParamsMap(Object bean) {
        Map<String, Object> map = new HashMap<>();

        // 获取对象的class 包括父类class
        for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                // 判断是否是静态的
                if (!Modifier.isStatic(field.getModifiers())) {
                    String fieldName = field.getName();
                    String getterMethodName = "get"
                            + Character.toUpperCase(fieldName.charAt(0))
                            + fieldName.substring(1, fieldName.length());
                    try {
                        Method method = clazz.getMethod(getterMethodName);
                        if (method != null) {
                            map.put(fieldName, method.invoke(bean));
                        }

                    } catch (Exception e) {
                        map = null;
                    }
                }
            }
        }

        return map;
    }

}
