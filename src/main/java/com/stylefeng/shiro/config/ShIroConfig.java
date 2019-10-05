package com.stylefeng.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.stylefeng.shiro.service.UserService;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ShIro配置类
 */
@Configuration
public class ShIroConfig {

    /**
     * 创建ShIroFilterFactoryBean
     */

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier(value = "defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         * 常用的过滤器：
         * anon：无需认证（登录）可以访问
         * authc：必须认证才可以访问
         * user：如果使用了rememberMe的功能可以直接访问
         * perms：该资源必须得到资源权限才可以访问
         * role：该资源必须得到角色权限才可以访问
         */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/user","authc");
        filterChainDefinitionMap.put("/*","authc");
        filterChainDefinitionMap.put("/login","anon");
        //资源授权拦截
        filterChainDefinitionMap.put("/add","perms[青铜]");
        filterChainDefinitionMap.put("/update","perms[青铜]");
        shiroFilterFactoryBean.setLoginUrl("/toLogin");  //未登录==跳转登录
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建DefaultWebSecurityManager
     *
     */
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier(value = "userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }


    /**
     * 创建Realm
     */

    @Bean(name = "userRealm")
    public UserRealm getUserRealm(){
        return new UserRealm();
    }


    /**
     * shiro对权限页面的处理
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
