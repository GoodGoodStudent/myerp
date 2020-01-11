package com.puhuanyu.erp.myerp.config;

import com.puhuanyu.erp.myerp.shiro.CustomRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
        defaultSecurityManager.setRememberMeManager(cookieRememberMeManager());
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

        map.put("/swagger-ui.html", "anon");
        map.put("/webjars/**", "anon");
        map.put("/v2/**", "anon");
        map.put("/swagger-resources/**","anon");
        map.put("/code.jpg","anon");
        map.put("/logout","logout");
        map.put("/login","anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/jq/**", "anon");
        map.put("/**","user");
        shiroFilterFactoryBean.setLoginUrl("/");//设置未登陆跳转的页面
        shiroFilterFactoryBean.setSuccessUrl("/index");//设置登陆成功跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");//设置未授权跳转的页面
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * @Description 开启shiro的aop注解，即在controller中使用 @RequiresPermissions("user/userList")等注解
     * @Param [securityManager] 安全管理
     * @return org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @Author 忠哥
     * @Date 2020-1-10 10:46
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * @Description 让shiro自己代理自己
     * @Param
     * @return org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @Author 忠哥
     * @Date 2020-1-10 11:05
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * @Description cookie对象
     * @Param []
     * @return org.apache.shiro.web.servlet.SimpleCookie
     * @Author 忠哥
     * @Date 2020-1-10 13:11
     */
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie = new SimpleCookie("remeber");
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * @Description cookie管理对象
     * @Param []
     * @return org.apache.shiro.web.mgt.CookieRememberMeManager
     * @Author 忠哥
     * @Date 2020-1-10 13:18
     */
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie());
        cookieRememberMeManager.setCipherKey("MyselfCookie_key".getBytes());
        return cookieRememberMeManager;
    }
}
