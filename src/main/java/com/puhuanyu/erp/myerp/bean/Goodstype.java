package com.puhuanyu.erp.myerp.bean;

public class Goodstype//商品类型
{
    private int id;
    private String name;

    public Goodstype()
    {
        super();
    }

    public Goodstype(int id, String name)
    {
        super();
        this.id = id;
        this.name = name;
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

    public String toString(){
        return "Goodstype:[id=" + id + ",name="+name+"]";
    }
}
