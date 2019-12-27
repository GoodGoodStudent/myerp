package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Warehouse;
import com.puhuanyu.erp.myerp.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WareHouseService//管理厂库信息
{
    @Autowired
    private WarehouseMapper warehouseMapper;
    //添加仓库
    public String doWareHouse(Warehouse warehouse)
    {
        if(warehouseMapper.findWareByMaxId()!=null)
        {
            warehouse.setId(Integer.parseInt(warehouseMapper.findWareByMaxId())+1);
        }
        if(warehouseMapper.doWarehouse(warehouse)>0)
        {
            return "添加成功！";
        }
        else
        {
            return "添加失败！";
        }
    }
    //删除仓库
    public String deleteWareHouse(int id)
    {
        if(warehouseMapper.deleteWarehouse(id)>0)
        {
            return "删除成功！";
        }
        else
        {
            return "删除失败！";
        }
    }
    //修改仓库信息
    public String updateWareHouse(Warehouse warehouse)
    {
        if(warehouseMapper.updateWarehouse(warehouse)>0)
        {
            return "修改成功！";
        }
        else
        {
            return "修改失败！";
        }
    }
    //根据id查询仓库
    public Warehouse findWareHouseById(int id)
    {
        return warehouseMapper.findWarehouseById(id);
    }

    //查询仓管所管理的仓库
    public Warehouse findWareHouseByEmpId(int eid)
    {
        return warehouseMapper.findWarehouseByEmpId(eid);
    }

    //查询仓库所在区域的仓库
    public List<Warehouse> findWareHouseByAddress(String address)
    {
        return warehouseMapper.findWarehouseByAddress(address);
    }
    //查询所有仓库
    public List<Warehouse> findWareHouseByAll()
    {
        return warehouseMapper.findWarehouseByAll();
    }
}
