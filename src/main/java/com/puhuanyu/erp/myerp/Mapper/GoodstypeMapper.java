package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Goodstype;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface GoodstypeMapper {//商品类型接口

    //通过名称添加商品类型
    @Insert("insert into goodstype(name) values(#{name})")
    public void doGoodstype(String name);

    //通过id删除商品类型
    @Delete("delete from goodstype where id=#{id}")
    public void delGoodstype(int id);

    //修改商品信息
    @Update("update goodstype set name=#{name} where id=#{id}")
    public void updateGoodstype(Goodstype goodstype);

    //通过id查找商品信息
    @Select("select * from goodstype where id=#{id}")
    public Goodstype findGoodstypeById(int id);

    //查询所有商品信息
    @Select("select * from goodstype")
    public List<Goodstype> fingAllGoodstype();
}
