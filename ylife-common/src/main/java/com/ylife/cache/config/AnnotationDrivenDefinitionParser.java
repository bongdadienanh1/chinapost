package com.ylife.cache.config;

import com.ylife.cache.NirvanaCacheConfiguration;
import com.ylife.cache.interceptor.*;
import com.ylife.cache.interceptor.operation.*;
import org.springframework.aop.config.AopNamespaceUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by InThEnd on 2017/2/3.
 */
public class AnnotationDrivenDefinitionParser implements BeanDefinitionParser {

    public static final String CACHE_CONFIG_BEAN_NAME = "com.nirvana.cache.config.nirvanaConfiguration";


    public static final String CACHE_CREATE_ADVISOR_BEAN_NAME = "com.nirvana.cache.advisor.cacheCreateAdvisor";
    public static final String CACHE_DELETE_ADVISOR_BEAN_NAME = "com.nirvana.cache.advisor.cacheDeleteAdvisor";
    public static final String CACHE_UPDATE_ADVISOR_BEAN_NAME = "com.nirvana.cache.advisor.cacheUpdateAdvisor";
    public static final String CACHE_READ_ADVISOR_BEAN_NAME = "com.nirvana.cache.advisor.cacheReadAdvisor";

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {

        AopNamespaceUtils.registerAutoProxyCreatorIfNecessary(parserContext, element);


        if (!parserContext.getRegistry().containsBeanDefinition(CACHE_CONFIG_BEAN_NAME)) {
            Object eleSource = parserContext.extractSource(element);

            Object order = null;
            if (element.hasAttribute("order")) {
                order = element.getAttribute("order");
            }

            registerConfiguration(element, parserContext, eleSource);
            registerCacheCreateAdvisor(parserContext, eleSource, order);
            registerCacheDeleteAdvisor(parserContext, eleSource, order);
            registerCacheUpdateAdvisor(parserContext, eleSource, order);
            registerCacheReadAdvisor(parserContext, eleSource, order);
        }
        return null;
    }

    private void registerConfiguration(Element element, ParserContext parserContext, Object eleSource) {
        RootBeanDefinition configurationDef = new RootBeanDefinition(NirvanaCacheConfiguration.class);
        configurationDef.setSource(eleSource);
        configurationDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        configurationDef.getPropertyValues().add("cacheManager", new RuntimeBeanReference(CacheNamespaceHandler.extractCacheManager(element)));
        parserContext.getRegistry().registerBeanDefinition(CACHE_CONFIG_BEAN_NAME, configurationDef);
    }


    private void registerCacheCreateAdvisor(ParserContext parserContext, Object eleSource, Object order) {
        registerAdvisor(parserContext, eleSource, order, CACHE_CREATE_ADVISOR_BEAN_NAME, CacheCreateInterceptor.class, new CacheCreateOperation());
    }

    private void registerCacheDeleteAdvisor(ParserContext parserContext, Object eleSource, Object order) {
        registerAdvisor(parserContext, eleSource, order, CACHE_DELETE_ADVISOR_BEAN_NAME, CacheDeleteInterceptor.class, new CacheDeleteOperation());
    }

    private void registerCacheUpdateAdvisor(ParserContext parserContext, Object eleSource, Object order) {
        registerAdvisor(parserContext, eleSource, order, CACHE_UPDATE_ADVISOR_BEAN_NAME, CacheUpdateInterceptor.class, new CacheUpdateOperation());
    }

    private void registerCacheReadAdvisor(ParserContext parserContext, Object eleSource, Object order) {
        registerAdvisor(parserContext, eleSource, order, CACHE_READ_ADVISOR_BEAN_NAME, CacheReadInterceptor.class, new CacheReadOperation());
    }

    private void registerAdvisor(ParserContext parserContext, Object eleSource, Object order, String advisorName, Class<? extends CacheInterceptor> interceptorClass, CacheOperation operation) {
        //interceptor
        RootBeanDefinition interceptorDef = new RootBeanDefinition(interceptorClass);
        interceptorDef.setSource(eleSource);
        interceptorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        interceptorDef.getPropertyValues().add("configuration", new RuntimeBeanReference(CACHE_CONFIG_BEAN_NAME));
        String interceptorName = parserContext.getReaderContext().registerWithGeneratedName(interceptorDef);

        //advisor
        RootBeanDefinition advisorDef = new RootBeanDefinition(CacheOperationPointcutAdvisor.class);
        advisorDef.setSource(eleSource);
        advisorDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        advisorDef.getPropertyValues().add("operation", operation);
        advisorDef.getPropertyValues().add("advice", new RuntimeBeanReference(interceptorName));
        if (order != null) {
            advisorDef.getPropertyValues().add("order", order);
        }
        parserContext.getRegistry().registerBeanDefinition(advisorName, advisorDef);
    }

}
