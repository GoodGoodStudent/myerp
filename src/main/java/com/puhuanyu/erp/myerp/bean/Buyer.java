package com.puhuanyu.erp.myerp.bean;

public class Buyer
{
    private int id;
    private String numberid;
    private String builddate;
    private String auditdate;
    private String begaindate;
    private String overdate;
    private Dep dep;
    private Emp emp1;//审核员
    private Emp emp2;//采购员
    private Goodsinfo goodsinfo;
    private int number;
    private int state;//0表示未完成，1表示已完成
    public String toString()
    {
        return "Buyer:[id="+id+",numberid="+numberid+",builddate="+builddate+",auditdate="+auditdate+",begaindate="+begaindate+",overdate="+overdate+","+dep+","+emp1+","+emp2+","+goodsinfo+"," +
                "number="+number+",state="+state+"]";
    }

    public Buyer()
    {
    }

    public Buyer(int id, String numberid, String builddate, String auditdate, String begaindate, String overdate, Dep dep, Emp emp1, Emp emp2, Goodsinfo goodsinfo, int number, int state)
    {
        this.id = id;
        this.numberid = numberid;
        this.builddate = builddate;
        this.auditdate = auditdate;
        this.begaindate = begaindate;
        this.overdate = overdate;
        this.dep = dep;
        this.emp1 = emp1;
        this.emp2 = emp2;
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

    public String getBuilddate()
    {
        return builddate;
    }

    public void setBuilddate(String builddate)
    {
        this.builddate = builddate;
    }

    public String getAuditdate()
    {
        return auditdate;
    }

    public void setAuditdate(String auditdate)
    {
        this.auditdate = auditdate;
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

    public Dep getDep()
    {
        return dep;
    }

    public void setDep(Dep dep)
    {
        this.dep = dep;
    }

    public Emp getEmp1()
    {
        return emp1;
    }

    public void setEmp1(Emp emp1)
    {
        this.emp1 = emp1;
    }

    public Emp getEmp2()
    {
        return emp2;
    }

    public void setEmp2(Emp emp2)
    {
        this.emp2 = emp2;
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
}
