package com.puhuanyu.erp.myerp.controller;

import com.puhuanyu.erp.myerp.mapper.RootMapper;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import com.puhuanyu.erp.myerp.bean.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelleoController
{
    @Autowired
    private RootMapper rootMapper;
    @Autowired
    private RootTypeMapper rootTypeMapper;
    @RequestMapping("/hello")
    public String hello()
    {
        Root root=rootMapper.findRootById(101);
        String str=root.toString()+" hello";
        return str;
    }
}
