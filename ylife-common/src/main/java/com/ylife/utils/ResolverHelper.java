package com.ylife.utils;

import com.ylife.security.annotation.SecurityResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/28.
 * 解析自定义注解SecurityResource
 */
public class ResolverHelper {

    /**
     * 解析自定义注解SecurityResource
     * @return
     * @throws Exception
     */
    public Map<String,Object> ResolverSecurityResource() throws Exception {
        Map<String,Object> map = new HashMap<>();
        //获取某个包下的所有类
        PackageHelper helper = new PackageHelper("com.ylife.chinapost.controller");
        List<Class<?>> list = helper.getClasses();
        for (Class<?> className : list) {
            //controller注解
            if (className.isAnnotationPresent(Controller.class)) {
                //获取controller RequestMapping的value值
                String url = "";
                String parentUrl="";
                String name="";
                boolean primary=false;
                String description="";
                if(className.isAnnotationPresent(RequestMapping.class)){
                    url = className.getAnnotation(RequestMapping.class).value()[0];
                }
                if(className.isAnnotationPresent(SecurityResource.class)){
                    parentUrl = className.getAnnotation(SecurityResource.class).parent();
                    name = className.getAnnotation(SecurityResource.class).name();
                    primary = className.getAnnotation(SecurityResource.class).primary();
                    description = className.getAnnotation(SecurityResource.class).description();
                }
                //controller中的方法
                for (Method method : className.getMethods()) {
                    if(!method.isAnnotationPresent(RequestMapping.class) || (!method.isAnnotationPresent(SecurityResource.class) && !className.isAnnotationPresent(SecurityResource.class))) {//controller上面的不需要加
                        continue;
                    }
                    //自定义注解SecurityResource
                    SecurityBean securityBean = new SecurityBean();
                    securityBean.setUrl(url);
                    if (method.isAnnotationPresent(SecurityResource.class)) {
                        //获取自定义注解的属性值
                        name = method.getAnnotation(SecurityResource.class).name();
                        primary = method.getAnnotation(SecurityResource.class).primary();
                        description = method.getAnnotation(SecurityResource.class).description();
                        parentUrl = method.getAnnotation(SecurityResource.class).parent();
                        securityBean.setPriority(method.getAnnotation(SecurityResource.class).priority());
                    }
                    if(method.isAnnotationPresent(RequestMapping.class)) {
                        securityBean.setUrl(url.concat(method.getAnnotation(RequestMapping.class).value()[0]));
                    }
                    securityBean.setParent(parentUrl);
                    securityBean.setName(name);
                    securityBean.setPrimary(primary);
                    securityBean.setDescription(description);
                    map.put(securityBean.getUrl(),securityBean);
                }
            }
        }

        return map;
    }

    /**
     * 自定义权限Bean
     */
    public class SecurityBean{
        /*权限名称*/
        private String name;
        /*是否为基本权限*/
        private boolean primary;
        /*父权限值*/
        private String parent;
        /*权限描述*/
        private String description;
        /*优先级*/
        private int priority;
        /*URL*/
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isPrimary() {
            return primary;
        }

        public void setPrimary(boolean primary) {
            this.primary = primary;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
