package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RootTypeService
{
    @Autowired
    private RootTypeMapper rootTypeMapper;
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
        if(rootTypeMapper.doRootType(new Roottype(id,name))!=0)
        {
            return "添加成功！";
        }else
            {
                return "添加失败！";
            }
    }
}
