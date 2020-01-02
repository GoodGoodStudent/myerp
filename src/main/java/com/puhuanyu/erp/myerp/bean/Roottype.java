package com.puhuanyu.erp.myerp.bean;

import org.springframework.stereotype.Component;

@Component
public class Roottype {
    private int id;
    private String name;

    public Roottype()
    {
        super();
    }

    public Roottype(String name)
    {
        this.name = name;
    }

    public Roottype(int id, String name)
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

    public String toString()
    {
        return "RootType:[id="+id+",name="+name+"]";
    }
}
