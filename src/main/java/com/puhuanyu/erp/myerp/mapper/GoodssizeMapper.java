package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Goodssize;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodssizeMapper {//商品规格接口

    //添加商品规格
    @Insert("insert into goodssize values(#{id},#{size},#{goodsinfo.id},#{price})")
    public int doGoodssize(Goodssize goodssize);

    //通过id删除商品规格
    @Delete("delete from goodssize where id=#{id}")
    public int delGoodssize(int id);

    //修改商品规格
    @Update("update goodssize set id=#{newId},size=#{goodssize.size},goodsinfo_id=#{goodssize.goodsinfo.id},price=#{goodssize.price} where id=#{goodssize.id}")
    public int updateGoodssize(Goodssize goodssize, int newId);

    //通过id查询商品规格
    @Results(
            @Result(column = "goodsinfo_id",property = "goodsinfo",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    )
    @Select("select * from goodssize where id=#{id}")
    public Goodssize findGoodssizeByid(int id);

    //通过商品信息的id查询所有规格
    @Results(
            @Result(column = "goodsinfo_id",property = "goodsinfo",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    )
    @Select("select * from goodssize where goodsinfo_id=#{goodsinfo_id}")
    public List<Goodssize> findGoodssizeByGoodsinfo_id(int goodsinfo_id);

    //根据商品信息找到商品规格最大id值
    @Select("select max(id) from goodssize where goodsinfo_id=#{goodsinfo_id}")
    public String findGoodssizeMaxIdByGoodsinfo_id(int goodsinfo_id);
}
