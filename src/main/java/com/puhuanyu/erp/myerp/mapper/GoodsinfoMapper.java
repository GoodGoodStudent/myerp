package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Goodsinfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    @Select("select * from goodsinfo where id=#{id}")
    public Goodsinfo findGoodsinfoByid(int id);

    //根据条件查找所有商品信息
    @Results({
            @Result(column = "client_id", property = "client", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientMapper.findById")),
            @Result(column = "warehouse_id", property = "warehouse", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.WarehouseMapper.findWarehouseById")),
            @Result(column = "goodstype_id", property = "goodstype", many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodstypeMapper.findGoodstypeById"))
    })
    @SelectProvider(type = com.puhuanyu.erp.myerp.util.DynaSqlProvider.class, method = "selectWithParamSql")
    public List<Goodsinfo> findGoodsinfoByCondition(String table, Map<String,Object> map, int firstScore, int pageSize);

    //根据商品类型的id，查找该商品信息id最大值
    @Select("select Max(id) from goodsinfo where goodstype_id=#{goodstype_id}")
    public String findGoodsinfoMaxIDByGoodstype_id(int goodstype_id);
}
