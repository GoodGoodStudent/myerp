package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Emp;
import org.apache.ibatis.annotations.Select;

public interface EmpMapper
{
    //通过工号查询员工
    @Select("select * from emp where id=#{id}")
    public Emp findById(int id);

    //通过姓名查询部门员工
    @Select("select * from emp where name=#{name}")
    public Emp findByName(String name);


}
