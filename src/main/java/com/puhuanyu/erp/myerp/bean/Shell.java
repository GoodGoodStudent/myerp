package com.puhuanyu.erp.myerp.bean;

import java.io.Serializable;

//出售订单表
public class Shell implements Serializable
{
    private int id;
    private String numberid;//订单编号
    private String begaindate;//开始时间
    private String overdate;//结束时间
    private Clientinfo clientinfo;//下单员
    private Emp emp;//审核员
    private Goodsinfo goodsinfo;//货物信息
    private int number;
    private int state;//0表示未完成，1表示已完成

    public Shell(String numberid, String begaindate, String overdate, Clientinfo clientinfo, Emp emp, Goodsinfo goodsinfo, int number, int state)
    {
        this.numberid = numberid;
        this.begaindate = begaindate;
        this.overdate = overdate;
        this.clientinfo = clientinfo;
        this.emp = emp;
        this.goodsinfo = goodsinfo;
        this.number = number;
        this.state = state;
    }

    public String toString()
    {
        return "Shell:[id="+id+",numberid="+numberid+",begaindate="+begaindate+"," +
                "overdate="+overdate+","+clientinfo+","+emp+","+goodsinfo+",number="+number+",state="+state+",]";
    }
    public Shell()
    {
    }

    public Shell(int id, String numberid, String begaindate, String overdate, Clientinfo clientinfo, Emp emp, Goodsinfo goodsinfo, int number, int state)
    {
        this.id = id;
        this.numberid = numberid;
        this.begaindate = begaindate;
        this.overdate = overdate;
        this.clientinfo = clientinfo;
        this.emp = emp;
        this.goodsinfo = goodsinfo;
        this.number = number;
        this.state = state;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNumberid()
    {
        return numberid;
    }

    public void setNumberid(String numberid)
    {
        this.numberid = numberid;
    }

    public String getBegaindate()
    {
        return begaindate;
    }

    public void setBegaindate(String begaindate)
    {
        this.begaindate = begaindate;
    }

    public String getOverdate()
    {
        return overdate;
    }

    public void setOverdate(String overdate)
    {
        this.overdate = overdate;
    }

    public Goodsinfo getGoodsinfo()
    {
        return goodsinfo;
    }

    public void setGoodsinfo(Goodsinfo goodsinfo)
    {
        this.goodsinfo = goodsinfo;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getState()
    {
        return state;
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public Clientinfo getClientinfo()
    {
        return clientinfo;
    }

    public void setClientinfo(Clientinfo clientinfo)
    {
        this.clientinfo = clientinfo;
    }

    public Emp getEmp()
    {
        return emp;
    }

    public void setEmp(Emp emp)
    {
        this.emp = emp;
    }
}
