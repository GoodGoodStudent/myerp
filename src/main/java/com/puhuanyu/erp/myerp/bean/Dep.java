package com.puhuanyu.erp.myerp.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Dep implements Serializable
{
    private int id;
    private String name;

    public Dep()
    {
        super();
    }

    public Dep(int id, String name)
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
        return "Dep:[id="+id+",name="+name+"]";
    }
}
