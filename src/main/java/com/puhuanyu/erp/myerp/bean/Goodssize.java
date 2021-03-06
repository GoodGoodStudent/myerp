package com.puhuanyu.erp.myerp.bean;

import java.io.Serializable;

public class Goodssize implements Serializable//商品规格
{
    private int id;
    private String size;
    private Goodsinfo goodsinfo;
    private double price;

    public Goodssize()
    {
    }

    public Goodssize(int id, String size, Goodsinfo goodsinfo, double price)
    {
        this.id = id;
        this.size = size;
        this.goodsinfo = goodsinfo;
        this.price = price;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public Goodsinfo getGoodsinfo()
    {
        return goodsinfo;
    }

    public void setGoodsinfo(Goodsinfo goodsinfo)
    {
        this.goodsinfo = goodsinfo;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public String toString(){
        return "Goodssize:[id="+id+",size="+size+","+goodsinfo+"price="+price+"]";
    }
}
