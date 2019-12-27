package com.puhuanyu.erp.myerp.controller;

import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootMapper;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import com.puhuanyu.erp.myerp.bean.Root;
import com.puhuanyu.erp.myerp.service.RootTypeService;
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
    private RootTypeService rootTypeService;
    @RequestMapping("/hello")
    public String hello()
    {
        return rootTypeService.findRootTypeById(1).toString();
    }
}
