package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Client;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ClientMapper
{
    //查询所有客户或者供应商
    @Select("select * from client where state2=#{state2}")
    public List<Client> findAll(String state2);

    //通过客户或者供应商名字查询客户或者供应商信息
    @Select("select * from client where name=#{name}")
    public Client findByName(String name);

    //添加一个客户或者供应商
    @Insert("insert into client(id,name,phone,address,state1,state2,email) value(#{id},#{name},#{phone},#{address},#{state1},#{state2},#{email})")
    public int doClient(Client client);

    //修改供应商或者客户的信息
    @Update("update client set id=#{id},name=#{name},phone=#{phone},address=#{address},state1=#{state1},state2=#{state2},email=#{email}")
    public int updateClient(Client client);

    //删除一家供应商或者客户
    @Delete("delete from client where name=#{name}")
    public int delClient(Client client);

    //通过id查询一个客户或者供应商
    @Select("select * from client where id=#{id}")
    public Client findById(int id);
}
