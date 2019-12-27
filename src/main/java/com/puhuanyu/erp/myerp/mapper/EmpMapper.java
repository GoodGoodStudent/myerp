package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Emp;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface EmpMapper
{
    //通过工号查询员工
    @Results({
            @Result(property="dep",column = "dep_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property="rank",column = "rank_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RankMapper.findRankByid"))
    })
    @Select("select * from emp where id=#{id}")
    public Emp findById(int id);

    //员工登录
    @Results({
            @Result(property="dep",column = "dep_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.DepMapper.findById")),
            @Result(property="rank",column = "rank_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RankMapper.findRankByid"))
    })
    @Select("select * from emp where id=#{id},password=#{password}")
    public Emp findLogin(int id,String password);
    //通过姓名查询部门员工
    @Results({
            @Result(property = "dep", column = "dep_id", many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.DepMapper.findById")),
            @Result(property = "rank",column = "rank_id",many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.RankMapper.findRankByid"))
    })
    @Select("select * from emp where name=#{name}")
    public Emp findByName(String name);

    //通过工号删除员工
    @Delete("delete from emp where id=#{id}")
    public int deleteEmp(int id);

    //修改员工信息
    @Update("update emp set id=#{id},name=#{name},password=#{password},sex=#{sex},phone=#{phone},address=#{address},card=#{card},worktime=#{worktime},birthtime=#{birthtime},lavetime=#{lavetime},dep_id=#{emp.dep_id},rank_id=#{emp.rank_id}")
    public int updateEmp(Emp emp);

    //添加员工信息
    @Insert("insert into emp(id,name,password,sex,phone,address,card,worktimme,birthtime,lavetime,dep_id,rank_id) value(#{id},#{name},#{password},#{sex},#{phone},#{address},#{card},#{worktime},#{birthtime},#{lavetime},#{emp.dep_id},#{emp.rank_id})")
    public int doEmp(Emp emp);

    //通过员工工号修改角色权限
    @Results(
            @Result(property = "rank",column = "rank_id",many = @Many(select = "com.puhuanyu.erp.myerp.Mapper.RankMapper.findRankByid"))
    )
    @Update("update emp set rank_id=#{rankId} where id=#{id}")
    public int updateRank(int id,int rankId);

}
