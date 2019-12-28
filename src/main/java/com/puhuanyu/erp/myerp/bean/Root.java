package com.puhuanyu.erp.myerp.bean;

import java.io.Serializable;

public class Root implements Serializable
{
    private int id;
    private String name;
    private Roottype roottype;

    public Root()
    {
    }

    public Root(int id, String name, Roottype roottype)
    {
        this.id = id;
        this.name = name;
        this.roottype = roottype;
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

    public Roottype getRoottype()
    {
        return roottype;
    }

    public void setRoottype(Roottype roottype)
    {
        this.roottype = roottype;
    }

    public String toString()
    {
        return "Root:[id="+id+",name="+name+","+roottype+"]";
    }
}
