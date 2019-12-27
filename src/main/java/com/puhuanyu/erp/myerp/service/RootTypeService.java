package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootTypeService
{
    @Autowired
    private RootTypeMapper rootTypeMapper;
    @Autowired
    private Roottype roottype;
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
    public List<Roottype> findRootTypeAll()
    {
        return rootTypeMapper.findRootTypeByAll();
    }

    //根据id查找类型
    public Roottype findRootTypeById(int id)
    {
        return rootTypeMapper.findRootTypeById(id);
    }
}
