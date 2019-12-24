package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Roottype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
//权限类型管理
@Component
@Mapper
public interface RootTypeMapper
{
    @Insert("insert into roottype(name) values(#{name})")
    public void doRootType(Roottype roottype);

    @Delete("delete from roottype where id=#{id}")
    public void deleteRootType(int id);

    @Update("update roottype set name=#{name} where id=#{id}")
    public void updateRootType(Roottype roottype);

    @Select("select * from roottype where id=#{id}")
    public Roottype findRootTypeById(int id);

    @Select("select * from roottype")
    public List<Roottype> findRootTypeByAll();
}
