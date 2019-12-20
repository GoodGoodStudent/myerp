package com.puhuanyu.erp.myerp.bean;

public class Ranks
{
    private int id;
    private Rank rank;
    private Root root;

    public Ranks()
    {
    }

    public Ranks(int id, Rank rank, Root root)
    {
        this.id = id;
        this.rank = rank;
        this.root = root;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Rank getRank()
    {
        return rank;
    }

    public void setRank(Rank rank)
    {
        this.rank = rank;
    }

    public Root getRoot()
    {
        return root;
    }

    public void setRoot(Root root)
    {
        this.root = root;
    }
}
