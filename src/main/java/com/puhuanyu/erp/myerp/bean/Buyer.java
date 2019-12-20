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

    public Buyer()
    {
    }

    public Buyer(int id, String numberid, String builddate, String auditdate, String begaindate, String overdate, Dep dep)
    {
        this.id = id;
        this.numberid = numberid;
        this.builddate = builddate;
        this.auditdate = auditdate;
        this.begaindate = begaindate;
        this.overdate = overdate;
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
}
