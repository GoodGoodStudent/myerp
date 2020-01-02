package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Clientinfo;
import com.puhuanyu.erp.myerp.mapper.ClientinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientinfoService
{
    @Autowired
    private ClientinfoMapper clientinfoMapper;

    public List<Clientinfo> findAll(int cid)//返回一个客户或者供应商的所有联系人
    {
        return clientinfoMapper.findByCid(cid);
    }

    public Clientinfo findByName(String name)//返回一个供应商或者客户的联系人通过名字
    {
        return clientinfoMapper.findByName(name);
    }

    public String updateClientinfo(Clientinfo clientinfo)//修改客户或者供应商的联系人信息
    {
        if(clientinfoMapper.updateClientinfo(clientinfo)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    public String delClientinfo(String name)//通过名字删除供应商或者客户的联系人
    {
        if(clientinfoMapper.delClientinfoByName(name)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }

    public  String delClientinfo(int id)//通过客户或者供应商联系人的编号来删除一个客户或者供应商
    {
        if(clientinfoMapper.delClientinfoById(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }

    public String doClientinfo(Clientinfo clientinfo)//添加一个供应商或者客户的联系人
    {
        if(clientinfoMapper.doClientinfo(clientinfo)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }

    public  Clientinfo findClientinfoById(int id)//通过供应商或者客户的编号来查询一个供应商或者客户联系人的所有的信息
    {
        return clientinfoMapper.findById(id);
    }
}
