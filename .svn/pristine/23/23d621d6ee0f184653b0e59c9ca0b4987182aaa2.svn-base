package com.ylife.data.dto;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2016/4/11.
 */
public class DTOHelper {

    private static final Map<String, Field> map = new ConcurrentHashMap<>();

    public static <T> T ignore(T object, String[] fieldNames) {
        try {
            Class<?> clazz = object.getClass();
            for (String string : fieldNames) {
                Field field;
                String fullName = clazz.getName() + "." + string;
                field = map.get(fullName);
                if (field == null) {
                    field = clazz.getDeclaredField(string);
                    field.setAccessible(true);
                }
                field.set(object, null);
            }
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("参数中：" + e.getMessage() + "字段不存在。");
        } catch (IllegalAccessException e) {
        }
        return object;
    }

    public static <T> T leave(T object, String[] fieldNames) {
        try {
            Class<?> clazz = object.getClass();
            T newObject = (T) clazz.newInstance();
            for (String string : fieldNames) {
                Field field;
                String fullName = clazz.getName() + "." + string;
                field = map.get(fullName);
                if (field == null) {
                    field = clazz.getDeclaredField(string);
                    field.setAccessible(true);
                }
                field.set(newObject, field.get(object));
            }
            return newObject;
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("参数中：" + e.getMessage() + "字段不存在。");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("这不可能！！");
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("未找到默认的无参构造方法。");
        }
    }
}
