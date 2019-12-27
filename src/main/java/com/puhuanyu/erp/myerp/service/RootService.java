package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Root;
import com.puhuanyu.erp.myerp.mapper.RootMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RootService//权限业务管理
{
    @Autowired
    private RootMapper rootMapper;
    //添加权限
    public String doRoot(Root root)
    {
        if(rootMapper.findRootMaxById(root.getRoottype().getId())!=null)
        {
            root.setId(Integer.parseInt(rootMapper.findRootMaxById(root.getRoottype().getId()))+1);
        }
        else
        {
            root.setId(1);
        }
        if(rootMapper.doRoot(root)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }
    //删除权限
    public String deleteRoot(int id)
    {
        if(rootMapper.deleteRoot(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
    //修改权限
    public String updateRoot(Root root)
    {
        if(rootMapper.updateRoot(root)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }
    //根据id查询
    public Root findRootById(int id)
    {
        return rootMapper.findRootById(id);
    }
    //根据权限类型id查询
    public List<Root> findRootByTid(int tid)
    {
        return rootMapper.findRootByTypeId(tid);
    }

    //查询所有权限
    public List<Root> findRootByAll()
    {
        return rootMapper.findRootByAll();
    }
}
