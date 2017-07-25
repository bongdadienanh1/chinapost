package com.ylife.cache.annotation.lifecircle;

import com.ylife.utils.Null;

import java.lang.annotation.*;

/**
 * Created by InThEnd on 2017/1/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheUpdate {

    String keyProperty();

    /**
     * 缓存的类型。默认为Null表示以方法返回值声明类型为准。
     */
    Class<?> clazz() default Null.class;

}
