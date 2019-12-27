package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Shell;
import com.puhuanyu.erp.myerp.util.DynaSqlProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//出售订单管理
@Component
@Mapper
public interface ShellMapper
{
    @Insert("insert into shell" +
            "(id,numberid,begaindate,overdate,clientinfo_id,emp_id,goodsinfo_id,number,state)" +
            " values(#{id},#{numberid},#{begaindate},#{overdate},#{clientinfo.id},#{emp.id},#{goodsinfo.id},#{number},#{state})")
    public int doShell(Shell shell);

    @Delete("delete from shell where id=#{id}")
    public int deleteShellById(int id);

    @Delete("delete from shell where numberid=#{nubid}")
    public int deleteShellBynumberid(String nubid);

    @Update("update shell set numberid=#{numberid},begaindate=#{begaindate},overdate=#{overdate}," +
            "clientinfo_id=#{clientinfo.id},emp_id=#{emp.id},goodsinfo_id=#{goodsinfo.id}," +
            "number=#{number},state=#{state} where id=#{id}")
    public int updateShell(Shell shell);

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where id=#{id}")
    public Shell findShellById(int id);

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where numberid=#{numid}")
    public Shell findShellByNumberid(String numid);//根据订单编号查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where state=#{state} limit #{firstScore},#{pageSize}")
    public List<Shell> findShellByState(int state,int firstScore,int pageSize);//根据订单状态查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where begaindate=#{begaindate} limit #{firstScore},#{pageSize}")
    public List<Shell> findShellBybegaindate(String begaindate,int firstScore,int pageSize);//开始时间查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where overdate=#{overdate} limit #{firstScore},#{pageSize}")
    public List<Shell> findShellByoverdate(String overdate,int firstScore,int pageSize);//结束时间查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell limit #{firstScore},#{pageSize}")
    public List<Shell> findShellAll(int firstScore,int pageSize);

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @SelectProvider(type = com.puhuanyu.erp.myerp.util.DynaSqlProvider.class,method = "selectWithParamSql")
    public List<Shell> findShellByCondition(String table, Map<String,Object> map,int firstScore,int pageSize);

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select max(numberid) from shell")
    public String findShellByMaxNumberId();

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select max(id) from shell")
    public String findShellByMaxId();
}
