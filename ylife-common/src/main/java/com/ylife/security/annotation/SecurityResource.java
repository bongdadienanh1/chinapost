package com.ylife.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by InThEnd on 2016/6/22.
 * 用于描述权限资源文件,仅作用于Class.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SecurityResource {

    /*权限名称*/
    public String name() default "";

    /*是否为基本权限*/
    public boolean primary() default true;

    /*父权限值*/
    public String parent() default "";

    /*权限描述*/
    public String description() default "";

    /*优先级*/
    public int priority() default 0;

    /*URL*/
    public String url() default "";


}
