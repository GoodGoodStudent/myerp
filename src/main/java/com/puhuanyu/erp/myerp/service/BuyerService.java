package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Buyer;
import com.puhuanyu.erp.myerp.mapper.BuyerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyerService
{
    @Autowired
    private BuyerMapper buyerMapper;

    public String doBuyer(Buyer buyer)//添加订单信息
    {
        if(buyerMapper.doBuyer(buyer)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }

    public String delBuyerById(int id)//通过id删除订单
    {
        if(buyerMapper.deleteBuyerById(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
    public String delBuyerByNumId(int numid)//通过编号删除订单
    {
        if(buyerMapper.deleteBuyerByNumId(numid)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
    public String updateBuyer(Buyer buyer)//修改进货信息
    {
        if(buyerMapper.doBuyer(buyer)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }

    public Buyer findBuyerById(int id)//通过id查询订单信息

    {
        return buyerMapper.findById(id);
    }

    public Buyer findBuyerByNunId(String numid)//通过订单编号查询订单信息

    {
        return buyerMapper.findByNumber(numid);
    }

    public List<Buyer> findBuyerByNunId(int state, int firstScore, int pageSize)//根据订单状态查找并分页

    {
        return buyerMapper.findShellByState(state,firstScore,pageSize);
    }

    public List<Buyer> findShellByEmp1(int empId, int firstScore, int pageSize)//根据审核员查找并分页

    {
        return buyerMapper.findShellByEmp1(empId,firstScore,pageSize);
    }

    public List<Buyer> findShellByEmp2(int empId, int firstScore, int pageSize)//根据采购员查找并分页

    {
        return buyerMapper.findShellByEmp2(empId,firstScore,pageSize);
    }

    public List<Buyer> findShellByCondition(String table, Map<String,Object> map, int firstScore, int pageSize)//根据多条件查询订单，可以为一个条件，也可以为多个,并分页

    {
        return buyerMapper.findShellByCondition(table,map,firstScore,pageSize);
    }
}
