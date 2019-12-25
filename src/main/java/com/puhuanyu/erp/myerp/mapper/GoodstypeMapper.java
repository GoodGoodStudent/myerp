package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Goodstype;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodstypeMapper {//商品类型接口

    //通过名称添加商品类型
    @Insert("insert into goodstype values(#{id},#{name})")
    public int doGoodstype(int id,String name);

    //通过id删除商品类型
    @Delete("delete from Goodstype where id=#{id}")
    public int delGoodstype(int id);

    //修改商品信息
    @Update("update Goodstype set name=#{name} where id=#{id}")
    public int updateGoodstype(Goodstype goodstype);

    //通过id查找商品信息
    @Select("select * from Goodstype where id=#{id}")
    public Goodstype findGoodstypeById(int id);

    //查询所有商品信息
    @Select("select * from goodstype")
    public List<Goodstype> fingAllGoodstype();

    //查询出id最大值
    @Select("select max(id) from goodstype")
    public String fingMaxId();
}
