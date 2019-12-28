package com.puhuanyu.erp.myerp.util;

import com.puhuanyu.erp.myerp.bean.Roottype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
public class RedisTemplateUtil
{
    @Autowired
    RedisTemplate redisTemplate;

    //根据id和对象拼成一个key,然后去redis缓存中找，有则返回对象，无则返回null
    public Object findObjectByOne(Object object,int id)
    {
        String key=object+"_"+id;//生成key
        ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);//判断redis中有没有生成的key
        if(hasKey)
        {
            object=operations.get(key);//有就查出key值对应的value
        }
        else
        {
            object=null;//无则返回null
        }
        return object;
    }
    //如果redis中没有key的缓存，就添加从数据库查出来的对象存入缓存
    public void setObjectByOne(Object object,int id)
    {
        String key=object+"_"+id;//生成key
        ValueOperations<Serializable,Object> operations=redisTemplate.opsForValue();
        operations.set(key,object,1, TimeUnit.HOURS);//存入缓存，TimeUnit.hours是代表缓存的生命周期，小时为单位
    }
    //添加list集合到redis缓存中
    public List<Object> findObjectList(List<Object> list,String where)
    {
        String key=list+"_"+where;//生成key
        ListOperations<Serializable, Object> operations=redisTemplate.opsForList();
        boolean hasKey = redisTemplate.hasKey(key);//判断redis中有没有生成的key
        if (hasKey)
        {
            list=operations.range(key,0,-1);//获取缓存中的list集合
        }
        else
        {
            list=null;
        }
        return list;
    }
    //如果redis中没有key的缓存，就添加从数据库查出来的对象集合存入缓存
    public void setObjectByList(List<Object> list,String where)
    {
        String key=list+"_"+where;//生成key
        ListOperations<Serializable, Object> operations=redisTemplate.opsForList();
        operations.leftPushAll(key,list);//将集合存入缓存
    }
}
