package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Rank;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RankMapper {//角色接口

    //添加角色

    @Insert("insert into ranks values(#{id},#{rank.id},#{root.id})")
    public int doRank(Rank rank);

    //删除角色
    @Delete("delete from rank where id=#{id}")
    public int delRank(int id);

    //修改角色
    @Update("update rank set id=#{newId},name=#{rank.name},dep_id=#{rank.dep.id} where id=#{rank.id}")
    public int update(Rank rank,int newId);

    //通过id查询角色
    @Results(
            @Result(property = "dep",column = "dep_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById"))
    )
    @Select("select * from rank where id=#{id}")
    public Rank findRankByid(int id);

    //查询所有角色
    @Results(
            @Result(property = "dep",column = "dep_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById"))
    )
    @Select("select * from rank")
    public List<Rank> findAllRank();

    //根据部门id查找所有的角色
    @Results(
            @Result(property = "dep",column = "dep_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById"))
    )
    @Select("select * from rank where dep_id=#{dep_id}")
    public List<Rank> findAllRankByDep_id(int dep_id);

    //根据部门id查询出编号最大的id
    @Results(
            @Result(column = "dep_id",property = "dep",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById"))
    )
    @Select("select Max(id) from rank where dep_id=#{dep_id}")
    public String findMaxRankIdByDep_id(int dep_id);

}
