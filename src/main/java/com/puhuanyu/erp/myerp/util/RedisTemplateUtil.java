package com.puhuanyu.erp.myerp.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Service
public class RedisTemplateUtil
{
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @Description 根据实体类名称和条件判断redis缓存中的key是否存在，
     * @Param [entityName, where] entityName为实体类名，where为sql语句中where后面的条件
     * @return java.lang.String  redis存在key返回json字符串，不存在返回null
     * @Author 忠哥
     * @Date 2019-12-31 15:44
     */
    public Object findObject(String entityName, Object where) {
        String key = getKey(entityName, where);//生成key
        ValueOperations<String,Object> operations=redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);//判断redis中有没有生成的key
        if(hasKey) {
            return operations.get(key);
        }
        return null;
    }

    /**
     * @Description 将object转换为Json字符串存进redis缓存中
     * @Param [entityName, object, where] entutyName为实体类名，object为sql查询找到的对象或者集合，where为sql查询where后面的条件
     * @return void
     * @Author 忠哥
     * @Date 2020-1-1 15:55
     */
    public void setObject(String entityName, Object object, Object where) {
        String key = getKey(entityName, where);
        ValueOperations<String,Object> operations=redisTemplate.opsForValue();
        String objectString = "";
        if(object instanceof List){
            objectString = JSONArray.toJSONString(object);
        }else {
            objectString = JSON.toJSONString(object);//将对象转换成json字符串
        }
        operations.set(key,objectString);//存入缓存，TimeUnit.hours是代表缓存的生命周期，不写代表生命周期不存在
    }

    /**
     * @Description 根据表名和条件生成redis的key
     * @Param [entityName, where] entityName为实体类名、where为sql查询where后面的条件
     * @return java.lang.String 返回redis的key
     * @Author 忠哥
     * @Date 2019-12-31 15:35
     */
    public String getKey(String entityName, Object where){
        entityName = entityName.substring(0,1).toUpperCase() + entityName.substring(1);
        String key = "";
        if(where != null){
          key = entityName + "_" + where;
        } else{
           key = entityName;
        }
        return key;
    }

    /**
     * @Description 删除该实体类名开头的所有缓存
     * @Param [entityName] entityName为试题名
     * @return void
     * @Author 忠哥
     * @Date 2020-1-1 16:55
     */
    public void delKey(String entityName){
        String key = entityName + "*";
        Set<String> keys = redisTemplate.keys(key);
        if(!keys.isEmpty()){
            redisTemplate.delete(keys);
        }
    }
}
