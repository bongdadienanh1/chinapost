package com.ylife.cache.interceptor;

import com.ylife.cache.annotation.Cacheable;
import com.ylife.cache.annotation.MainKey;
import com.ylife.cache.exception.ClassDefinitionException;
import com.ylife.utils.StringUtil;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2017/2/22.
 */
public class CacheClassDefinition {

    private static final Map<Class, CacheClassDefinition> definitionCache = new ConcurrentHashMap<>();

    private Class clazz;

    private String shortName;

    private String keyFieldName;

    private Map<String, Field> fieldCache = new HashMap<>();

    public static CacheClassDefinition getDefinition(Class<?> clazz) {
        CacheClassDefinition definition = definitionCache.get(clazz);
        if (definition == null) {
            definition = new CacheClassDefinition(clazz);
            definitionCache.put(clazz, definition);
        }
        return definition;
    }

    private CacheClassDefinition(Class<?> clazz) {
        this.clazz = clazz;
        Cacheable cacheable = clazz.getAnnotation(Cacheable.class);
        if (cacheable != null) {
            String shortName = cacheable.shortName();
            if (!StringUtil.isBlank(shortName)) {
                this.shortName = shortName;
            }
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            MainKey mainKey = field.getAnnotation(MainKey.class);
            if (mainKey != null) {
                this.keyFieldName = fieldName;
            }
            fieldCache.put(field.getName(), field);
        }
        if (StringUtil.isBlank(keyFieldName)) {
            throw new ClassDefinitionException("类型：" + clazz.getName() + "无法确定缓存主键。");
        }
    }

    public Class getClazz() {
        return clazz;
    }

    public String getShortName() {
        return shortName;
    }

    public String getKeyFieldName() {
        return keyFieldName;
    }

    public Field getField(String fieldName) {
        return fieldCache.get(fieldName);
    }

    public boolean hasField(String fieldName) {
        return getField(fieldName) != null;
    }

}
