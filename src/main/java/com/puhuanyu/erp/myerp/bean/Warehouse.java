package com.puhuanyu.erp.myerp.bean;

public class Warehouse
{
    private int id;
    private String name;
    private String address;
    private Emp emp;

    public Warehouse(String name, String address, Emp emp)
    {
        this.name = name;
        this.address = address;
        this.emp = emp;
    }

    public Warehouse()
    {
    }

    public Warehouse(int id, String name, String address, Emp emp)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.emp = emp;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Emp getEmp()
    {
        return emp;
    }

    public void setEmp(Emp emp)
    {
        this.emp = emp;
    }

    public String toString(){
        return "Warehouse:[id="+id+",name="+name+",address="+address+","+emp+"]";
    }
}
