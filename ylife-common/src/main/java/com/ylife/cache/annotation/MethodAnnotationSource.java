package com.ylife.cache.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * Created by InThEnd on 2017/3/1.
 */
public interface MethodAnnotationSource {

    <T extends Annotation> List<T> getAnnotations(Method method, Class<?> targetClass, Class<T> annotationClass);

}
