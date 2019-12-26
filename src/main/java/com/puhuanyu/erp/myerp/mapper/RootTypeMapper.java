package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Roottype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
//权限类型管理
@Component
@Mapper
public interface RootTypeMapper
{
    @Insert("insert into roottype(id,name) values(#{id},#{name})")
    public int doRootType(Roottype roottype);

    @Delete("delete from roottype where id=#{id}")
    public int deleteRootType(int id);

    @Update("update roottype set name=#{name} where id=#{id}")
    public int updateRootType(Roottype roottype);

    @Select("select * from roottype where id=#{id}")
    public Roottype findRootTypeById(int id);

    @Select("select * from roottype")
    public List<Roottype> findRootTypeByAll();

    @Select("select max(id) from roottype")
    public String findRootMaxId();
}
