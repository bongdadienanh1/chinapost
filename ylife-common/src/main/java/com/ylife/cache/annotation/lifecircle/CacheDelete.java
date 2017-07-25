package com.ylife.cache.annotation.lifecircle;

import java.lang.annotation.*;

/**
 * Created by InThEnd on 2017/1/16.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheDelete {

    String keyProperty();

    Class<?> clazz();

}
