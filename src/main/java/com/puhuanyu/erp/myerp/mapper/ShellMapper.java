package com.puhuanyu.erp.myerp.mapper;

import com.puhuanyu.erp.myerp.bean.Shell;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
//出售订单管理
@Component
@Mapper
public interface ShellMapper
{
    @Insert("insert into shell" +
            "(numberid,begaindate,overdate,clientinfo_id,emp_id,goodsinfo_id,number,state)" +
            " values(#{numberid},#{begaindate},#{overdate},#{clientinfo.id},#{emp.id},#{goodsinfo.id},#{number},#{state})")
    public void doShell(Shell shell);

    @Delete("delete from shell where id=#{id}")
    public void deleteShellById(int id);

    @Delete("delete from shell where numberid=#{nubid}")
    public void deleteShellBynumberid(int nubid);

    @Update("update shell set numberid=#{numberid},begaindate=#{begaindate},overdate=#{overdate}," +
            "clientinfo_id=#{clientinfo.id},emp_id=#{emp.id},goodsinfo_id=#{goodsinfo.id}," +
            "number=#{number},state=#{state} where id=#{id}")
    public void updateShell(Shell shell);

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
    @Select("select * from shell where state=#{state}")
    public List<Shell> findShellByState(int state);//根据订单状态查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where begaindate=#{begaindate}")
    public List<Shell> findShellBybegaindate(String begaindate);//开始时间查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell where overdate=#{overdate}")
    public List<Shell> findShellByoverdate(String overdate);//结束时间查找

    @Results({
            @Result(property="clientinfo",column="clientinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.ClientinfoMapper.findById")),
            @Result(property="emp",column="emp_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.EmpMapper.findById")),
            @Result(property="goodsinfo",column="goodsinfo_id",many = @Many(select = "com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper.findGoodsinfoByid"))
    })
    @Select("select * from shell")
    public List<Shell> findShellAll();
}
