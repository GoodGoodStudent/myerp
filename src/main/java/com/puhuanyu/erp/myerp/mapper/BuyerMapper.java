package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Buyer;
import org.apache.ibatis.annotations.*;

public interface BuyerMapper
{
    //通过编号查询订单信息
    @Select("select * from buyer where numberid=#{numberid}")
    public Buyer findByNumber(String numberid);

    //存储订单
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.DepMapper.findById")),
            @Result(property = "emp",column = "emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.EmpMapper.findRankById")),
            @Result(property = "goodsinfo",column = "goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Insert("insert into buyer(numberid,builddate,auditdate,begaindate,overdate,dep_id,emp_id1,emp_id2,goodsinfo_id,number,state) value(#{numberid},#{builddate},#{auditdate},#{begaindate},#{overdate},#{buyer.dep_id},#{buyer.emp_id1},#{buyer.emp_id2},#{buyer.goodsinfo_id},#{number},#{state})")
    public int doBuyer(Buyer buyer);
}
