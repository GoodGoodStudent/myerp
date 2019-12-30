package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Dep;
import org.apache.ibatis.annotations.*;
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

    //添加一个部门
    @Insert("insert into dep(id,name) value(#{id},#{name})")
    public int doDep(Dep dep);

    //修改一个部门的信息
    @Update("update dep set id=#{id},name=#{name}")
    public int updateDep(Dep dep);

    //删除一个部门
    @Delete("delete from dep where id=#{id}")
    public int delDep(int id);
}
