package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Roottype;
import org.apache.ibatis.annotations.*;

import java.util.List;

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
