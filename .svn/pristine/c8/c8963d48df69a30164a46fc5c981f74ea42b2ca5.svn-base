package com.ylife.cache.interceptor;

import com.ylife.cache.KeyGenerator;
import com.ylife.cache.NirvanaCacheConfiguration;
import com.ylife.cache.exception.ClassDefinitionException;
import com.ylife.cache.exception.OperationNotFoundException;
import com.ylife.utils.StringUtil;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by InThEnd on 2017/2/24.
 */
public abstract class AbstractSwitchableCurdInterceptor implements CacheInterceptor {

    protected NirvanaCacheConfiguration configuration;

    public AbstractSwitchableCurdInterceptor() {
    }

    public AbstractSwitchableCurdInterceptor(NirvanaCacheConfiguration configuration) {
        this.configuration = configuration;
    }

    private Map<ContextKey, CurdCacheContext> contextCache = new ConcurrentHashMap<>();

    private KeyGenerator keyGenerator = new KeyGenerator() {

        private static final String SEPARATOR = "-";

        @Override
        public Object generator(Class<?> targetClass, Method method, Object[] args) {
            CurdCacheContext context = getContext(method, targetClass);
            if (context == CurdCacheContext.NULL) {
                throw new OperationNotFoundException("此目标上缓存行为为空。");
            }
            CacheClassDefinition definition = CacheClassDefinition.getDefinition(context.getClazz());
            String className;
            String fieldName = context.getKeyProperty();
            if (!definition.hasField(fieldName)) {
                throw new ClassDefinitionException("类中字段不存在：" + fieldName);
            }
            String shortName = definition.getShortName();
            if (!StringUtil.isBlank(shortName)) {
                className = shortName;
            } else {
                className = context.getClazz().getName();
            }
            return className + SEPARATOR + fieldName + SEPARATOR + args[0].toString();
        }
    };

    private volatile boolean off;

    public void switchOff() {
        off = true;
    }

    public void switchOn() {
        off = false;
    }

    public boolean isOff() {
        return off;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (isOff()) {
            return invocation.proceed();
        }
        CurdCacheContext context = getContext(invocation.getMethod(), invocation.getThis().getClass());
        if (context == CurdCacheContext.NULL) {
            switchOff();
            return invocation.proceed();
        }
        return process(invocation);
    }

    abstract Object process(MethodInvocation invocation) throws Throwable;

    Object getCacheKey(Method method, Class<?> targetClass, Object[] args) {
        return keyGenerator.generator(targetClass, method, args);
    }

    /**
     * 获取CURD缓存在目标对象和具体方法上的缓存信息。
     * 如果此ContextKey(method,targetClass)获取到的CurdCacheContext==CurdCacheContext.NULL,
     * 表示此处无CurdCacheContext.
     */
    protected final CurdCacheContext getContext(Method method, Class<?> targetClass) {
        ContextKey contextKey = new ContextKey(method, targetClass);
        CurdCacheContext context = contextCache.get(contextKey);
        if (context == null) {
            context = parseContext(method, targetClass);
            if (context == null) {
                context = CurdCacheContext.NULL;
            }
            contextCache.put(contextKey, context);
        }
        return context;
    }

    /**
     * 解析CurdCacheContext,返回结果可为{@code null}.
     */
    abstract CurdCacheContext parseContext(Method method, Class<?> targetClass);

    public static class CurdCacheContext {

        public static final CurdCacheContext NULL = new CurdCacheContext() {
        };

        private Class<?> clazz;

        private String keyProperty;

        public Class<?> getClazz() {
            return clazz;
        }

        public void setClazz(Class<?> clazz) {
            this.clazz = clazz;
        }

        public String getKeyProperty() {
            return keyProperty;
        }

        public void setKeyProperty(String keyProperty) {
            this.keyProperty = keyProperty;
        }
    }

    private static class ContextKey {

        private Method method;

        private Class<?> targetClass;

        public ContextKey(Method method, Class<?> targetClass) {
            this.method = method;
            this.targetClass = targetClass;
        }

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

            ContextKey that = (ContextKey) o;

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

    public void setConfiguration(NirvanaCacheConfiguration configuration) {
        this.configuration = configuration;
    }

    protected final <T extends Annotation> List<T> getAnnotations(Method method, Class<?> targetClass, Class<T> annotationClass) {
        return configuration.getAnnotationSource().getAnnotations(method, targetClass, annotationClass);
    }
}
