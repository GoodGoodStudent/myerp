package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Dep;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DepMapper
{
    //查询所有部门
    @Select("select * from dep")
    public List<Dep> findAll();

    //通过id查部门
    @Select("select * from dep where id=#{id}")
    public Dep findById(int id);

}
