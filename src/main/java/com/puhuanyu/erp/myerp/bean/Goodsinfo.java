package com.puhuanyu.erp.myerp.bean;

public class Goodsinfo
{
    private int id;
    private String name;
    private Client client;
    private String unit;
    private Goodstype goodstype;
    private int number;
    private Warehouse warehouse;

    public Goodsinfo()
    {
    }

    public Goodsinfo(int id, String name, Client client, String unit, Goodstype goodstype, int number, Warehouse warehouse)
    {
        this.id = id;
        this.name = name;
        this.client = client;
        this.unit = unit;
        this.goodstype = goodstype;
        this.number = number;
        this.warehouse = warehouse;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }

    public String getUnit()
    {
        return unit;
    }

    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    public Goodstype getGoodstype()
    {
        return goodstype;
    }

    public void setGoodstype(Goodstype goodstype)
    {
        this.goodstype = goodstype;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public Warehouse getWarehouse()
    {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse)
    {
        this.warehouse = warehouse;
    }

    public String toString(){
        return "Goodsinfo:[id="+id+",name="+name+client+",unit="+unit+goodstype+",number="+number+warehouse+"]";
    }
}
