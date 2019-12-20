package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Goodssize;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodssizeMapper {//商品规格接口
    //添加商品规格
    @Insert("insert into goodssize values(#{id},#{name},#{goodsinfo_id},#{price})")
    public void doGoodssize(Goodssize goodssize);

    //通过id删除商品规格
    @Delete("delete from ")
    public void delGoodssize(int id);

    public void updateGoodssize(Goodssize goodssize);

    public Goodssize findGoodssizeByid(int id);

    public List<Goodssize> findGoodssizeByGoodsinfo_id(Goodssize goodssize);


}
