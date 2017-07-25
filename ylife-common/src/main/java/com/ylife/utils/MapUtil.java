package com.ylife.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据实体bean生成Map
 *
 * @Author henry .
 * @Create 2017-04-15 15:20.
 */
public final class MapUtil {

    private MapUtil() {

    }

    /**
     * 通过bean产生map对象：如map.put("username",username);map.put("password",password),
     * key 值为bean的属性名 value 为bean的属性值
     * 
     * @param bean
     *            实体bean
     * @return Map
     */
    public static Map<String, Object> getParamsMap(Object bean) {
        Map<String, Object> map = new HashMap<String, Object>();

        // 获取对象的class 包括父类class
        for (Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz
                .getSuperclass()) {
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // 判断是否是静态的
                if (!Modifier.isStatic(fields[i].getModifiers())) {
                    String fieldName = fields[i].getName();
                    // 组装get数据方法名
                    String getterMethodName = "get"
                            + Character.toUpperCase(fieldName.charAt(0))
                            + fieldName.substring(1, fieldName.length());
                    try {
                        Method method = clazz.getMethod(getterMethodName);
                        if (method != null) {
                            // 获取到的属性数据不为 空 存入map
                            if(method.invoke(bean) != null && !method.invoke(bean).equals("")){
                                map.put(fieldName, method.invoke(bean));
                            }
                        }

                    } catch (IllegalAccessException e) {
                        map = null;
                    } catch (IllegalArgumentException e) {
                        map = null;
                    } catch (InvocationTargetException e) {
                        map = null;
                    } catch (NoSuchMethodException e) {
                        map = null;
                    } catch (SecurityException e) {
                        map = null;
                    }
                }
            }
        }

        return map;
    }

    /**
     * 获取easyui 展示的map
     * 
     * @param resultList
     *            查询结果集
     * @param resultCount
     *            查询结果集的数量
     * @return Map
     */
    public static Map<String, Object> getJsonMap(List<?> resultList,
            long resultCount) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        // 查询总条数 存放在jsonMap中,存放总记录数，必须的
        jsonMap.put("total", resultCount);
        // rows键 存放每页记录 list
        jsonMap.put("rows", resultList);
        return jsonMap;
    }

}
