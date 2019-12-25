package com.puhuanyu.erp.myerp.util;

public class PageBean
{
    private int count;//总记录数
    private int pageSize;//每页记录条数
    private int pageIndex;//当前页
    private int pages;//总页数

    public void setCount(int count)
    {
        this.count = count;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setPageIndex(int pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    public void setPages(int pages)
    {
        this.pages = pages;
    }

    public int getCount()
    {
        return count;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public int getPageIndex()
    {
        return pageIndex;
    }

    public int getPages()
    {
        return pages;
    }
    public void getPage()
    {
        this.pages=count%pageSize==0? count/pageSize:count/pageSize+1;
    }
}
