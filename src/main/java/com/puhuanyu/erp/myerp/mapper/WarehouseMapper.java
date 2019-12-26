package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Warehouse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
//厂库信息管理
@Component
@Mapper
public interface WarehouseMapper
{
    @Insert("insert into warehouse(name,address,emp_id) values(#{name},#{address},#{emp.id})")
    public int doWarehouse(Warehouse warehouse);

    @Delete("delete from warehouse where id=#{id}")
    public int deleteWarehouse(int id);

    @Update("update warehouse set name=#{name},address=#{address},emp_id=#{emp.id} where id=#{id}")
    public int updateWarehouse(Warehouse warehouse);

    @Results(
            @Result(property="emp",column="emp_id",many=@Many(select="com.puhuanyu.erp.myerp.mapper.EmpMapper.findById"))
    )
    @Select("select * from warehouse where id=#{id}")
    public Warehouse findWarehouseById(int id);

    @Results(
            @Result(property="emp",column="emp_id",many=@Many(select="com.puhuanyu.erp.myerp.mapper.EmpMapper.findById"))
    )
    @Select("select * from warehouse")
    public List<Warehouse> findWarehouseByAll();
}
