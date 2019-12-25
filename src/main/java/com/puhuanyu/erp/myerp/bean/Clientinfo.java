package com.puhuanyu.erp.myerp.bean;

public class Clientinfo
{
    private int id;
    private String name;
    private String sex;
    private String phone;
    private String email;
    private Client client;

    public String toString()
    {
        return "Clientinfo:[id="+id+",name="+name+",sex="+sex+",phone="+phone+"," +
                "email="+email+","+client+"]";
    }

    public Clientinfo()
    {
    }

    public Clientinfo(int id, String name, String sex, String phone, String email, Client client)
    {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.client = client;
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

    public String getSex()
    {
        return sex;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Client getClient()
    {
        return client;
    }

    public void setClient(Client client)
    {
        this.client = client;
    }
}
