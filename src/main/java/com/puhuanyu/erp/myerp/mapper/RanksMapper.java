package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Ranks;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RanksMapper {//角色和权限接口

    //给角色添加权限
    @Insert("insert into ranks(rank_id,root_id) values(#{rank_id},#{root_id})")
    public void doRanks(Ranks ranks);

    //删除角色的某个权限
    @Delete("delete from ranks where id=#{id}")
    public void delRanks(int id);

    //修改角色的权限
    public void updateRanks(Ranks ranks);

    //通过id查询某个角色的某个权限
    public Ranks findRanksByid(int id);

    //查询某个角色的所有权限
    public List<Ranks> findRanksByRoot_id(int root_id);


}
