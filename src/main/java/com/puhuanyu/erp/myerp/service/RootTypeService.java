package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import com.puhuanyu.erp.myerp.util.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RootTypeService
{
    @Autowired
    private RootTypeMapper rootTypeMapper;
    @Autowired
    private Roottype roottype;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private List<Object> rootTypeList;
    @Autowired
    RedisTemplateUtil redisTemplateUtil;

    //添加权限类型，找到最大的id再加1
    public String doRootType(String name)
    {
        int id=0;
        if(rootTypeMapper.findRootMaxId()!=null)
        {
            id=Integer.parseInt(rootTypeMapper.findRootMaxId())+1;
        }
        else {
            id=1;
        }
        roottype.setId(id);
        roottype.setName(name);
        if(rootTypeMapper.doRootType(roottype)!=0)
        {
            return "添加成功！";
        }else
            {
                return "添加失败！";
            }
    }
    //根据权限类型id删除
    public String deleteRootType(int id)
    {
        if(rootTypeMapper.deleteRootType(id)!=0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
    //根据权限类型id修改
    public String updateRootType(Roottype r)
    {
        if(rootTypeMapper.updateRootType(r)!=0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }
    //查询所有的权限类型
    public List<Object> findRootTypeAll()
    {
//        String key="rootTypeAll";
//        ListOperations<String, Roottype> operations=redisTemplate.opsForList();
//        boolean hasKey = redisTemplate.hasKey(key);
//        List<Roottype> list=null;
//        if(hasKey)
//        {
//            list=operations.range(key,0,-1);
//        }
//        else
//        {
//            list=rootTypeMapper.findRootTypeByAll();
//            operations.leftPushAll(key,list);
//        }
        if (redisTemplateUtil.findObjectList(rootTypeList,"All")==null)
        {
            rootTypeList= Collections.singletonList(rootTypeMapper.findRootTypeByAll());
            redisTemplateUtil.setObjectByList(rootTypeList,"All");
        }
        else
        {
            rootTypeList=redisTemplateUtil.findObjectList(rootTypeList,"All");
        }
        return rootTypeList;
    }

    //根据id查找类型
    public Roottype findRootTypeById(int id)
    {
//        String key="rootType_"+id;
//        ValueOperations<String, Roottype> operations=redisTemplate.opsForValue();
//        boolean hasKey = redisTemplate.hasKey(key);
//        Roottype r=null;
//        if(hasKey)
//        {
//            r=operations.get(key);
//        }
//        else
//        {
//            r=rootTypeMapper.findRootTypeById(id);
//            operations.set(key,r,1, TimeUnit.HOURS);
//        }
//        return r;
        //先到redis缓存中找id对应的对象，没有就从数据库查，再添加到缓存中
        Roottype type= (Roottype)redisTemplateUtil.findObjectByOne(roottype,id);
        if(type==null)
        {
            roottype=rootTypeMapper.findRootTypeById(id);
            redisTemplateUtil.setObjectByOne(roottype,id);//添加对象到缓存
        }
        else
        {
            roottype=type;//缓存中存在的对象
        }
        return roottype;
    }
}
