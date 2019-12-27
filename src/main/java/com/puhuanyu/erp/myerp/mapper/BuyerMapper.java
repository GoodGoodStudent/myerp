package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Buyer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface BuyerMapper
{

    //添加订单
    @Insert("insert into buyer(id,numberid,builddate,auditdate,begaindate,overdate,dep_id,emp_id1,emp_id2,goodsinfo_id,number,state) value(#{id},#{numberid},#{builddate},#{auditdate},#{begaindate},#{overdate},#{dep.id},#{emp.id},#{emp.id},#{goodsinfo.id},#{number},#{state})")
    public int doBuyer(Buyer buyer);

    //根据id删除进货订单
    @Delete("delete from buyer where id=#{id}")
    public int deleteBuyerById(int id);

    //根据编号删除进货订单
    @Delete("delete from buyer where numberid=#{nid}")
    public int deleteBuyerByNumId(int nid);

    //修改进货信息
    @Update("update buyer set numberid=#{numberid},builddate=#{builddate},auditdate=#{auditdate},begaindate=#{begaindate},overdate=#{overdate}," +
            "dep_id=#{dep.id},emp_id1=#{emp.id},emp_id2=#{emp.id},goodsinfo_id=#{goodsinfo.id}" +
            "number=#{number},state=#{state} where id=#{id}")
    public int update(Buyer buyer);

    //根据id查询进货订单
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from buyer where id=#{id}")
    public Buyer findById(int id);

    //通过编号查询订单信息
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from buyer where numberid=#{numberid}")
    public Buyer findByNumber(String numberid);

    //根据订单状态查找并分页
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from buyer where state=#{state} limit #{firstScore},#{pageSize}")
    public List<Buyer> findShellByState(int state, int firstScore, int pageSize);

    //根据审核员查找订单并分页
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from buyer where emp_id1=#{empId} limit #{firstScore},#{pageSize}")
    public List<Buyer> findShellByEmp1(int empId, int firstScore, int pageSize);

    //根据采购员查找订单并分页
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from buyer where emp_id2=#{empId} limit #{firstScore},#{pageSize}")
    public List<Buyer> findShellByEmp2(int empId, int firstScore, int pageSize);

    //根据多条件查询订单，可以为一个条件，也可以为多个,并分页
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property = "emp", column = "emp_id1", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "emp", column = "emp_id2", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property = "goodsinfo", column = "goodsinfo_id", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @SelectProvider(type = com.puhuanyu.erp.myerp.util.DynaSqlProvider.class,method = "selectWithParamSql")
    public List<Buyer> findShellByCondition(String table, Map<String,Object> map, int firstScore, int pageSize);
}