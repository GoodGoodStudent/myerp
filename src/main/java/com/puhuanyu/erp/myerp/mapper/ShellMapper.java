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
            "(numberid,begaindate,overdate,clientinfo_id,emp_id,goodsslnfo_id,number,state)" +
            " values(#{numberid},#{begaindate},#{overdate},#{clientinfo.id},#{emp.id},#{goodsslnfo.id},#{number},#{state})")
    public void doShell(Shell shell);

    @Delete("delete from shell where id=#{id}")
    public void deleteShellById(int id);

    @Delete("delete from shell where numberid=#{nubid}")
    public void deleteShellBynumberid(int nubid);

    @Update("update shell set numberid=#{numberid},begaindate=#{begaindate},overdate=#{overdate}," +
            "clientinfo_id=#{clientinfo.id},emp_id=#{emp.id},goodsslnfo_id=#{goodsslnfo.id}," +
            "number=#{number},state=#{state} where id=#{id}")
    public void updateShell(Shell shell);

    @Select("select * from shell where id=#{id}")
    public Shell findShellById(int id);

    @Select("select * from shell where numberid=#{numid}")
    public Shell findShellByNumberid(int numid);//根据订单编号查找

    @Select("select * from shell where state=#{state} limit (#{pageIndex}-1)*pageSize,pageSize")
    public List<Shell> findShellByState(int state,int pageIndex,int pageSize);//根据订单状态查找

    @Select("select * from shell where begaindate=#{begaindate}")
    public List<Shell> findShellBybegaindate(String begaindate);//开始时间查找

    @Select("select * from shell where overdate=#{overdate}")
    public List<Shell> findShellByoverdate(String overdate);//结束时间查找

    @Select("select * from shell")
    public List<Shell> findShellAll();
}
