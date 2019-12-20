package com.puhuanyu.erp.myerp.bean;

public class Client
{
    private int id;
    private String name;
    private String phone;
    private String address;
    private String state1;//是否在线
    private String state2;//客户还是供应商
    private String email;

    public Client()
    {
    }

    public Client(int id, String name, String phone, String address, String state1, String state2, String email)
    {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.state1 = state1;
        this.state2 = state2;
        this.email = email;
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

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getState1()
    {
        return state1;
    }

    public void setState1(String state1)
    {
        this.state1 = state1;
    }

    public String getState2()
    {
        return state2;
    }

    public void setState2(String state2)
    {
        this.state2 = state2;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
