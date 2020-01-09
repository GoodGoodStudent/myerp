package com.puhuanyu.erp.myerp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 *  @Description 这是一个redis缓存配置类，主要定义数据存入redis缓存的序列化方式
 *  @ClassName RedisConfig
 *  @Author 忠哥
 *  @data 2019-12-31 13:52
 */
@Configuration
public class RedisConfig {
    /**
     * @Description key和hash的key,value都采用string方式存入缓存
     * @Param [factory] redis连接工厂
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object> 返回一个redisTemplate
     * @Author 忠哥
     * @Date 2019-12-31 13:55
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();//创建一个redisTemplate
        template.setConnectionFactory(factory);//连接工厂
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();//字符串序列化
        template.setKeySerializer(stringRedisSerializer);//key序列化成String
        template.setHashKeySerializer(stringRedisSerializer);//hash的key序列化成String
        template.setValueSerializer(stringRedisSerializer);//value序列化成String
        template.setHashValueSerializer(stringRedisSerializer);//hash的value序列化成String
        template.afterPropertiesSet();//初始化template
        return template;
    }
}
