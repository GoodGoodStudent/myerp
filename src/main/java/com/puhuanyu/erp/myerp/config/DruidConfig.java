package com.puhuanyu.erp.myerp.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 *  @Description druid连接池的配置类
 *  @ClassName DruidConfig
 *  @Author 忠哥
 *  @data 2020-01-17 16:59
 */
@Configuration
public class DruidConfig {

    /**
     * @Description 注入数据源的相关信息
     * @Param []
     * @return javax.sql.DataSource
     * @Author 忠哥
     * @Date 2020-1-17 17:36
     */
    @ConfigurationProperties(prefix = "spring.datasource")//匹配配置文件前缀为spring.datasource的属性
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * @Description 配置drudi的监控：1、配置一个管理后台的servlet
     * @Param []
     * @return org.springframework.boot.web.servlet.ServletRegistrationBean
     * @Author 忠哥
     * @Date 2020-1-17 17:37
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //http://localhost:8080/druid/
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> map = new HashMap<String, String>();
        map.put("loginUsername","admin");//账号
        map.put("loginPassword","123456");//密码
        map.put("allow","");//允许所有人访问
        servletRegistrationBean.setInitParameters(map);
        return servletRegistrationBean;
    }

    /**
     * @Description 配置drudi的监控：2、配置一个web监控的filter
     * @Param []
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     * @Author 忠哥
     * @Date 2020-1-17 17:38
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> map = new HashMap<String, String>();
        map.put("exclusions", "*.js,*.css,/druid/*");//过滤js、css、druid页面的相关监听
        filterRegistrationBean.setInitParameters(map);
        return filterRegistrationBean;
    }
}
