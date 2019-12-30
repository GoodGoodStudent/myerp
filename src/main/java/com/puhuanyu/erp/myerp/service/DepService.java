package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Dep;
import com.puhuanyu.erp.myerp.mapper.DepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepService
{
    @Autowired
    private DepMapper depMapper;
    public List<Dep> findAll()//返回所有的部门
    {
        return depMapper.findAll();
    }

    public Dep findById(int id)//通过部门id返回一个部门信息
    {
        return depMapper.findById(id);
    }
}
