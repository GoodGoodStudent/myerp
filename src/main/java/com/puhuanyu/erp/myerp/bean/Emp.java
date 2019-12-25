package com.puhuanyu.erp.myerp.bean;

public class Emp
{
    private int id;
    private String name;
    private String password;
    private String sex;
    private String phone;
    private String address;
    private String card;
    private String worktime;
    private String birthtime;
    private String leavetime;
    private Dep dep;
    private Rank rank;

    public Emp()
    {
    }

    public Emp(int id, String name, String password, String sex, String phone, String address, String card, String worktime, String birthtime, String leavetime, Dep dep, Rank rank)
    {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.card = card;
        this.worktime = worktime;
        this.birthtime = birthtime;
        this.leavetime = leavetime;
        this.dep = dep;
        this.rank = rank;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
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

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getCard()
    {
        return card;
    }

    public void setCard(String card)
    {
        this.card = card;
    }

    public String getWorktime()
    {
        return worktime;
    }

    public void setWorktime(String worktime)
    {
        this.worktime = worktime;
    }

    public String getBirthtime()
    {
        return birthtime;
    }

    public void setBirthtime(String birthtime)
    {
        this.birthtime = birthtime;
    }

    public String getLeavetime()
    {
        return leavetime;
    }

    public void setLeavetime(String leavetime)
    {
        this.leavetime = leavetime;
    }

    public Dep getDep()
    {
        return dep;
    }

    public void setDep(Dep dep)
    {
        this.dep = dep;
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }

    public String toString(){
        return "Emp:[id="+id+",name="+name+",password="+password+",sex="+sex+",phone="+phone+",address="+address+",card="+card+
                ",worktime="+worktime+",birthtime="+birthtime+",leavetime="+leavetime+",dep:"+dep+",rank:"+rank+"]";
    }
}
