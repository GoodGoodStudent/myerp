package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Emp;
import com.puhuanyu.erp.myerp.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService
{
    @Autowired
    private EmpMapper empMapper;

    public Emp findEmpById(int id)//通过员工编号查询一个员工的所有信息
    {
        return empMapper.findById(id);
    }

    public Emp findEmpByIdAndPassword(int id,String password)//员工登录
    {
        return empMapper.findLogin(id,password);
    }

    public Emp findEmpByName(String name)//通过姓名查询员工的所有信息
    {
        return empMapper.findByName(name);
    }

    public String delEmp(int id)//删除员工信息
    {
        if(empMapper.deleteEmp(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }

    public String updateEmp(Emp emp)//修改员工信息
    {
        if(empMapper.updateEmp(emp)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    public String doEmp(Emp emp)//添加员工
    {
        if(empMapper.doEmp(emp)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }

    public String updateEmpRank(int id,int rankid)//修改角色权限
    {
        if(empMapper.updateRank(id,rankid)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    public String updatePassword(int id,String password)//根据id修改员工自身的密码
    {
        if(empMapper.updatePassword(id,password)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    public List<Emp> findByLavetime(String lavetime)//根据离职状态查询所有待离职员工
    {
        return empMapper.findEmpByLavetime(lavetime);
    }
}
