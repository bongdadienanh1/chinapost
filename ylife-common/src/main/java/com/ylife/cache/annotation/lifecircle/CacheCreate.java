package com.ylife.cache.annotation.lifecircle;

import com.ylife.utils.Null;

import java.lang.annotation.*;

/**
 * Created by InThEnd on 2017/1/16.
 * 缓存创建。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheCreate {

    String keyProperty();

    Class<?> clazz() default Null.class;

}
