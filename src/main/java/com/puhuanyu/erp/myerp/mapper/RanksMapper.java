package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Ranks;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RanksMapper {//角色和权限接口

    //给角色添加权限
    @Insert("insert into ranks values(#{id},#{rank.id},#{root.id})")
    public int doRanks(Ranks ranks);

    //删除角色的某个权限
    @Delete("delete from ranks where id=#{id}")
    public int delRanks(int id);

    //修改角色的权限
    @Update("update ranks set root_id=#{newRoot_id} where id=#{id}")
    public int updateRanks(int id, int newRoot_id);

    //通过id查询某个角色的某个权限
    @Results({
            @Result(column = "rank_id",property = "rank",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RankMapper.findRankByid")),
            @Result(column = "root_id",property = "root",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootMapper.findRootById"))
    })
    @Select("select * from ranks where id=#{id}")
    public Ranks findRanksByid(int id);

    //查询某个角色的所有权限
    @Results({
            @Result(column = "rank_id",property = "rank",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RankMapper.findRankByid")),
            @Result(column = "root_id",property = "root",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootMapper.findRootById"))
    })
    @Select("select * from ranks where rank_id=#{rank_id}")
    public List<Ranks> findRanksByRank_id(int rank_id);

    @Select("select max(id) from ranks")
    public String findRanksMaxId();

}
