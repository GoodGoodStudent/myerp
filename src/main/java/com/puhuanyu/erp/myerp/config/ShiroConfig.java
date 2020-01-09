package com.puhuanyu.erp.myerp.config;

import com.puhuanyu.erp.myerp.shiro.CustomRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  @Description
 *  @ClassName ShiroConfig
 *  @Author 忠哥
 *  @data 2020-01-03 10:50
 */
@Configuration
public class ShiroConfig {

    /**
     * @Description 将自定义的验证方式加入到容器中
     * @Param []
     * @return com.puhuanyu.erp.myerp.shiro.CustomRealm 自定义realm
     * @Author 忠哥
     * @Date 2020-1-3 10:52
     */
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        return customRealm;
    }

    /**
     * @Description 安全管理，配置主要是Realm的管理认证
     * @Param []
     * @return org.apache.shiro.mgt.SecurityManager 安全管理
     * @Author 忠哥
     * @Date 2020-1-3 10:56
     */
    @Bean
    public DefaultSecurityManager defaultSecurityManager (@Qualifier("customRealm") CustomRealm customRealm){
        DefaultSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm);
        return defaultSecurityManager;
    }


    /**
     * @Description 过滤器
     * @Param [securityManager] 安全管理
     * @return org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Author 忠哥
     * @Date 2020-1-3 11:07
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("defaultSecurityManager") DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);//设置安全管理器
        //添加内置过滤器
        /**
         *  shiro内置过滤器，可实现相关权限的拦截
         *      常用的过滤器：
         *          anon: 无需认证（登陆）可以访问
         *          authc: 必须认证才能访问
         *          user: 如何使用rememberMe功能可以访问
         *          perms: 该资源必须得到权限资源才可以访问
         *          role: 该资源必须得到角色资源才可以访问
         */
        Map<String,String> map = new LinkedHashMap<String,String>();
        map.put("/hello", "anon");
        map.put("/world", "anon");
        map.put("/code.jpg","anon");
        map.put("/logout","logout");
        map.put("/login","anon");
        //map.put("/**","authc");
        shiroFilterFactoryBean.setLoginUrl("/");//修改调整登陆的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
