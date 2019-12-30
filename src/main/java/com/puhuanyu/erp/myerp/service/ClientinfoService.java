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

    public List<Clientinfo> findAll(int id)//返回一个客户或者供应商的所有联系人
    {
        return clientinfoMapper.findByCid(id);
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

    public  String delClientinfo(int id)
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
}
