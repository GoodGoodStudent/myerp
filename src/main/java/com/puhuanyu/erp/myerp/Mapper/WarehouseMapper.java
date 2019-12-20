package com.puhuanyu.erp.myerp.Mapper;

import com.puhuanyu.erp.myerp.bean.Warehouse;

import java.util.List;

public interface WarehouseMapper
{
    public void doWarehouse(Warehouse warehouse);

    public void deleteWarehouse(int id);

    public void updateWarehouse(Warehouse warehouse);

    public Warehouse findWarehouseById(int id);

    public List<Warehouse> findWarehouseByAll();
}
