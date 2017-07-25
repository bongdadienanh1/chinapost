package com.ylife.cache.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * Created by InThEnd on 2017/2/7.
 */
public class CacheNamespaceHandler extends NamespaceHandlerSupport {


    static final String CACHE_MANAGER_ATTRIBUTE = "cache-manager";
    static final String DEFAULT_CACHE_MANAGER_BEAN_NAME = "cacheManager";

    static String extractCacheManager(Element element) {
        return (element.hasAttribute(CACHE_MANAGER_ATTRIBUTE) ? element.getAttribute(CACHE_MANAGER_ATTRIBUTE) : DEFAULT_CACHE_MANAGER_BEAN_NAME);
    }

    static BeanDefinition parseKeyGenerator(Element element, BeanDefinition def) {
        String name = element.getAttribute("key-generator");
        if (StringUtils.hasText(name)) {
            def.getPropertyValues().add("keyGenerator", new RuntimeBeanReference(name.trim()));
        }
        return def;
    }

    @Override
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenDefinitionParser());
    }
}
