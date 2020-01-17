package com.puhuanyu.erp.myerp.config;

import com.puhuanyu.erp.myerp.shiro.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  @Description shiro配置类，主要的要有f3：自定义的Realm,安全管理器,过滤器
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
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());//设置加密方式
        return customRealm;
    }

    /**
     * @Description 加密方式
     * @Param []
     * @return org.apache.shiro.authc.credential.HashedCredentialsMatcher
     * @Author 忠哥
     * @Date 2020-1-16 15:33
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");//md5加密
        hashedCredentialsMatcher.setHashIterations(1024);//加密次数
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);//默认是true,true表示密码加密使用Hex编码,false用Base64编码
        return hashedCredentialsMatcher;
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
        defaultSecurityManager.setCacheManager(redisCacheManager());
        defaultSecurityManager.setSessionManager(defaultWebSessionManager());
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
        map.put("/druid/**","anon");
        map.put("/css/**", "anon");
        map.put("/js/**", "anon");
        map.put("/jq/**", "anon");
        map.put("/img/**", "anon");
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
     * @Description 未授权时，指定跳转的页面
     * @Param []
     * @return org.springframework.web.servlet.handler.SimpleMappingExceptionResolver
     * @Author 忠哥
     * @Date 2020-1-16 15:46
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /*未授权处理页*/
        properties.setProperty("UnauthorizedException", "noAuth.html");
        resolver.setExceptionMappings(properties);
        return resolver;
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
        simpleCookie.setMaxAge(259200);//单位是秒，cookie生效时间30天
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
        cookieRememberMeManager.setCipherKey("MyselfCookie_key".getBytes());//设置cookie加密的密钥
        return cookieRememberMeManager;
    }


    @Value("${spring.redis.host}")
    private String host;//redis主机
    @Value("${spring.redis.port}")
    private int port;//redis端口号
    @Value("${spring.redis.password}")
    private String password; //redis密码

    /**
     * @Description shiro里面的redis管理器
     * @Param []
     * @return org.crazycake.shiro.RedisManager
     * @Author 忠哥
     * @Date 2020-1-11 10:58
     */
    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * @Description redis的缓存管理器
     * @Param []
     * @return org.crazycake.shiro.RedisCacheManager
     * @Author 忠哥
     * @Date 2020-1-16 15:47
     */
    @Bean
    public RedisCacheManager redisCacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * @Description redis的会话dao
     * @Param []
     * @return org.crazycake.shiro.RedisSessionDAO
     * @Author 忠哥
     * @Date 2020-1-16 15:48
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * @Description 默认网络会话管理
     * @Param []
     * @return org.apache.shiro.web.session.mgt.DefaultWebSessionManager
     * @Author 忠哥
     * @Date 2020-1-16 15:49
     */
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO());
        return defaultWebSessionManager;
    }
}
