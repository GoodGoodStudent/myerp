package com.puhuanyu.erp.myerp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 //开启swagger2
public class SwaggerConfig
{
    @Bean
    public Docket docket1()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.puhuanyu.erp.myerp.loginController"))
                .build();
    }
    @Bean
    public Docket docket2()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }
    @Bean
    public Docket docket3()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C");
    }
    //配置bean实例
    /**
     * @Description 修改swagger页面的默认信息
     * @Param [environment]
     * @Author 青哥
     * @return springfox.documentation.spring.web.plugins.Docket
     * @Date  2020/1/11  19:53
     **/
    @Bean
    public Docket docket(Environment environment)
    {
        //设置要显示的swagger的环境
        //Profiles profiles = Profiles.of("dev","text");
        //通过environment.acceptsProfiles判断是否处在自己设定的环境中
        //boolean flan=environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("青哥") //搜索栏的名字
                //enable(false) 是否启动swagger，默认为true，false则swagger不能被浏览器访问
                .enable(true)
                .select()
                //RequestHandlerSelectors 配置要扫描的方式
                //basePackage:指定要扫描的路径
                //any():扫描全部
                //one（）:不扫描
                //withClassAnnotation() 扫描类上的注解，参数是一个注解的反射对象
                //withMethodAnnotation() 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.puhuanyu.erp.myerp.qingController"))
                //过滤扫描路径
                //.paths(PathSelectors.ant("/text/**"))
                .build();
    }

    private ApiInfo apiInfo()
    {
        //作者信息
        Contact contact = new Contact("青哥","http://www.baidu.com","1756767337@qq.com");
        return new ApiInfo(
                "青哥的SwaggerAPI文档",
                "年轻的心是被梦想撑大的！",
                "v1.0",
                "https://baidu.com",
                contact,
                "Apache 2.0",
                "http://www.baidu.com",
                new ArrayList<>()
        );
    }
}
