package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Shell;
import com.puhuanyu.erp.myerp.mapper.ShellMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShellService
{
    @Autowired
    private ShellMapper shellMapper;

    //添加出售订单
    public String doShell(Shell shell)
    {
        if(shellMapper.findShellByMaxId()!=null&&shellMapper.findShellByMaxNumberId()!=null)
        {
            shell.setId(Integer.parseInt(shellMapper.findShellByMaxId())+1);
            int unm=Integer.parseInt(shellMapper.findShellByMaxNumberId())+1;
            shell.setNumberid(unm+"");
        }
        else
        {
            shell.setId(1);
            shell.setNumberid("20192000001");
        }
        if(shellMapper.doShell(shell)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }
    //根据订单id删除订单
    public String deleteShellById(int id)
    {
        if(shellMapper.deleteShellById(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }

    //根据订单编号删除订单
    public String deleteShellByNid(String nid)
    {
        if(shellMapper.deleteShellBynumberid(nid)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }

    //根据订单id修改订单
    public String updateShell(Shell shell)
    {
        if(shellMapper.updateShell(shell)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    //根据订单id查询
    public Shell findShellById(int id)
    {
        return shellMapper.findShellById(id);
    }
    //根据订单编号查询
     public Shell findShellByNumId(String nid)
     {
         return shellMapper.findShellByNumberid(nid);
     }

     //根据订单状态查询并分页
    public List<Shell> findShellByState(int state,int firstScore,int pageSize)
    {
        return shellMapper.findShellByState(state,firstScore,pageSize);
    }
    //根据结束时间查询并分页
    public List<Shell> findShellByOverDate(String overDate,int firstScore,int pageSize)
    {
        return shellMapper.findShellByoverdate(overDate,firstScore,pageSize);
    }

    //根据开始时间查询并分页
    public List<Shell> findShellByBegainDate(String begainDate,int firstScore,int pageSize)
    {
        return shellMapper.findShellBybegaindate(begainDate,firstScore,pageSize);
    }

    //查询所有订单并分页
    public List<Shell> findShellByAll(int firstScore,int pageSize)
    {
        return shellMapper.findShellAll(firstScore,pageSize);
    }

    //根据多条件查询订单，可以为一个条件，也可以为多个
    public List<Shell> findShellByCondition(Map<String,Object> map,int firstScore,int pageSize)
    {
        return shellMapper.findShellByCondition("shell",map,firstScore,pageSize);
    }
}
