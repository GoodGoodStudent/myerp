package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Clientinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ClientinfoMapper
{
    //查询一个客户或者供应商的所有联系人
    @Results(
            @Result(property = "client",column = "client_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById"))
    )
    @Select("select * from clientinfo where client_id=#{cid}")
    public List<Clientinfo> findByCid(int cid);

    //通过名字查询客户或者供应商的联系人
    @Results(
            @Result(property = "client",column = "client_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById"))
    )
    @Select("select * from clientinfo where name=#{name}")
    public Clientinfo findByName(String name);

    //修改客户或者供应商联系人
    @Update("update clientinfo set name=#{name},phone=#{phone},sex=#{sex},email=#{email},client_id=#{client.id} where id=#{id}")
    public int updateClientinfo(Clientinfo clientinfo);

    //通过名字删出供应商或者客户的联系人
    @Delete("delete from clientinfo where name=#{name}")
    public int delClientinfoByName(String name);

    //通过名字删出供应商或者客户的联系人
    @Delete("delete from clientinfo where id=#{id}")
    public int delClientinfoById(int id);

    //添加一个客户或者供应商的联系人
    @Insert("insert into clientinfo(id,name,phone,sex,email client_id) value(#{id},#{name},#{phone},#{sex},#{email},#{client.id})")
    public int doClientinfo(Clientinfo clientinfo);

    //通过id查询供应商或者客户联系人
    @Results(
            @Result(property = "client",column = "client_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById"))
    )
    @Select("select * from clientinfo where id=#{id}")
    public Clientinfo findById(int id);
}
