package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Goodsinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsinfoMapper {//商品信息接口

    //添加商品信息
    @Insert("insert into goodsinfo values(#{id},#{name},#{client.id},#{unit},#{goodstype.id},#{number},#{warehouse.id})")
    public int doGoodsinfo(Goodsinfo goodsinfo);

    //删除商品信息
    @Delete("delete from goodsinfo where id=#{id}")
    public int delGoodsinfo(int id);

    //修改商品信息
    @Update("update goodsinfo set id=#{newId},name=#{goodsinfo.name},client_id=#{goodsinfo.client.id},unit=#{goodsinfo.unit}," +
            "goodstype_id=#{goodsinfo.goodstype.id},number=#{goodsinfo.number},warehouse_id=#{goodsinfo.warehouse.id} " +
            "where id=#{goodsinfo.id}")
    public int updateGoodsinfo(Goodsinfo goodsinfo,int newId);

    //根据id查找商品信息
    @Select("select * from goodsinfo where id=#{id}")
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    public Goodsinfo findGoodsinfoByid(int id);

    //根据仓库的id查找所有商品信息
    @Select("select * from goodsinfo where warehouse_id=#{warehouse_id}")
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    public List<Goodsinfo> findGoodsinfoByWarehouse_id(int warehouse_id);

    //根据商品类型的id查找所有的商品
    @Select("select * from goodsinfo where goodstype_id=#{goodstype_id}")
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    public List<Goodsinfo> findGoodsinfoByGoodstype_id(int goodstype_id);

    //根据供应商/客户的id查找所有商品
    @Select("select * from goodsinfo where client_id=#{client_id}")
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    public List<Goodsinfo> findGoodsinfoByClient_id(int client_id);

    //根据商品类型的id，查找该商品信息id最大值
    @Select("select Max(*) from goodsinfo where goodstype_id=#{goodstype_id}")
    public String findGoodsinfoMaxIDByGoodstype_id(int goodstype_id);
}
