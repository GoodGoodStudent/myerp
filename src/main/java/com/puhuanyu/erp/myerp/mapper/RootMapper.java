package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Root;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
//员工管理权限
@Component
@Mapper
public interface RootMapper
{
    @Insert("insert into root(id,name,roottype_id) values(#{id},#{name},#{roottype.id})")
    public int doRoot(Root root);

    @Delete("delete from root where id=#{id}")
    public int deleteRoot(int id);

    @Update("update root set name=#{name},roottype_id=#{roottype.id} where id=#{id}")
    public int updateRoot(Root root);

    @Results(
            @Result(property="roottype",column="roottype_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootTypeMapper.findRootTypeById"))
    )
    @Select("select * from root where id=#{id}")
    public Root findRootById(int id);

    @Results(
            @Result(property="roottype",column="roottype_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootTypeMapper.findRootTypeById"))
    )
    @Select("select * from root where roottype_id=#{tid}")
    public List<Root> findRootByTypeId(int tid);//根据权限类型查找所属的权限

    @Results(
            @Result(property="roottype",column="roottype_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootTypeMapper.findRootTypeById"))
    )
    @Select("select * from root")
    public List<Root> findRootByAll();

    @Results(
            @Result(property="roottype",column="roottype_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.RootTypeMapper.findRootTypeById"))
    )
    @Select("select max(id) from root where roottype_id=#{rid}")
    public String findRootMaxById(int rid);
}
