package com.ylife.cache.annotation;

import com.ylife.utils.Assert;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2017/3/1.
 */
public class SimpleSpecificMethodAnnotationSource implements MethodAnnotationSource {

    /**
     * 根据注解的位置和注解类型缓存解析的注解。
     * Collection==null表示此类型注解尚未解析，结果尚未缓存。
     * Collection.isEmpty()==true表示此类型缓存已解析，并且为空。
     */
    private Map<AnnotationKey, Map<Class<? extends Annotation>, List<Annotation>>> cache = new ConcurrentHashMap<>();


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Annotation> List<T> getAnnotations(Method method, Class<?> targetClass, Class<T> annotationClass) {
        Assert.notNull(method);
        Assert.notNull(targetClass);
        Assert.notNull(annotationClass);

        AnnotationKey annotationKey = getAnnotationKey(method, targetClass);

        Map<Class<? extends Annotation>, List<Annotation>> collectionMap = cache.get(annotationKey);
        if (collectionMap == null) {
            collectionMap = new ConcurrentHashMap<>();
            cache.put(annotationKey, collectionMap);
        }

        List<T> annotations = (List<T>) collectionMap.get(annotationClass);
        if (annotations != null) {
            return annotations;
        }

        Method specificMethod = ClassUtils.getMostSpecificMethod(method, targetClass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

        annotations = findAnnotations(specificMethod, annotationClass);

        if (annotations.isEmpty()) {
            if (specificMethod != method) {
                annotations = findAnnotations(method, annotationClass);
            }
        }

        collectionMap.put(annotationClass, (List<Annotation>) annotations);
        return annotations;
    }

    private <T extends Annotation> List<T> findAnnotations(Method method, Class<T> annotationClass) {
        Set<T> annotations = new HashSet<>();

        T ann = method.getAnnotation(annotationClass);
        if (ann != null) {
            annotations.add(ann);
        }

        for (Annotation metaAnn : method.getAnnotations()) {
            ann = metaAnn.annotationType().getAnnotation(annotationClass);
            if (ann != null) {
                annotations.add(ann);
            }
        }
        List<T> list = new ArrayList<>();
        list.addAll(annotations);
        return list;
    }

    private AnnotationKey getAnnotationKey(Method method, Class<?> targetClass) {
        return new AnnotationKey(method, targetClass);
    }

    private static class AnnotationKey {

        AnnotationKey(Method method, Class<?> targetClass) {
            this.method = method;
            this.targetClass = targetClass;
        }

        private Method method;

        private Class<?> targetClass;

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public Class<?> getTargetClass() {
            return targetClass;
        }

        public void setTargetClass(Class<?> targetClass) {
            this.targetClass = targetClass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            AnnotationKey that = (AnnotationKey) o;

            if (method != null ? !method.equals(that.method) : that.method != null) return false;
            return targetClass != null ? targetClass.equals(that.targetClass) : that.targetClass == null;

        }

        @Override
        public int hashCode() {
            int result = method != null ? method.hashCode() : 0;
            result = 31 * result + (targetClass != null ? targetClass.hashCode() : 0);
            return result;
        }
    }


}
