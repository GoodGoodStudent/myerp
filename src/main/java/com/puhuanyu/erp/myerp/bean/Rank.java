package com.puhuanyu.erp.myerp.bean;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Rank implements Serializable
{
    private int id;
    private String name;
    private Dep dep;

    public Rank()
    {
    }

    public Rank(int id, String name, Dep dep) {
        this.id = id;
        this.name = name;
        this.dep = dep;
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

    public Dep getDep() {
        return dep;
    }

    public void setDep(Dep dep) {
        this.dep = dep;
    }

    public String toString(){
        return "Rank:[id="+id+",name="+name+","+dep+"]";
    }
}
