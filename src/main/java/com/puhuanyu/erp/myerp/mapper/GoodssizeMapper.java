package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Goodssize;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodssizeMapper {//商品规格接口
    //添加商品规格
    @Insert("insert into goodssize values(#{id},#{name},#{goodsinfo_id},#{price})")
    public void doGoodssize(Goodssize goodssize);

    //通过id删除商品规格
    @Delete("delete from goodssize where id=#{id}")
    public void delGoodssize(int id);

    //修改商品规格
    @Update("update goodssize set id=#{id},size=#{size},goodsinfo_id=#{goodsinfo_id},price=#{price} where id=#{id}")
    public void updateGoodssize(Goodssize goodssize);

    //通过id查询商品信息
    @Select("select * from goodssize where id=#{id}")
    public Goodssize findGoodssizeByid(int id);

    //通过商品信息的id查询所有规格
    @Results(
            @Result(column = "goodsinfo_id",property = "goodsinfo",many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.GoodsinfoMapper.findGoodsinfoByid"))
    )
    @Select("select * from goodssize where goodsinfo_id=#{goodsinfo_id}")
    public List<Goodssize> findGoodssizeByGoodsinfo_id(Goodssize goodssize);
}
