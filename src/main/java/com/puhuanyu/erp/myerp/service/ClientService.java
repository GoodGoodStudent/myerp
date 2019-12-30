package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Client;
import com.puhuanyu.erp.myerp.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService
{
    @Autowired
    private ClientMapper clientMapper;
    public List<Client> findAll(String state2)//返回所有供应商或者客户
    {
        return clientMapper.findAll(state2);
    }

    public Client findByName(String name)//通过名字查询一个供应商或者客户的信息
    {
        return clientMapper.findByName(name);
    }

    public String doClient(Client client)//是否添加成功并返回字符串
    {
        if(clientMapper.doClient(client)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }

    public String updateClient(Client client)//是否修改成功并返回字符串
    {
        if(clientMapper.updateClient(client)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败!";
        }
    }

    public  String delClient(Client client)//是否删除成功并返回字符串
    {
        if(clientMapper.delClient(client)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
}
