package com.puhuanyu.erp.myerp.controller;

import com.alibaba.fastjson.JSON;
import com.puhuanyu.erp.myerp.bean.Emp;
import com.puhuanyu.erp.myerp.service.EmpService;
import com.puhuanyu.erp.myerp.util.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController
{
    @Autowired
    EmpService empService;
    @Autowired
    Emp emp;
    @Autowired
    RedisTemplateUtil redisTemplateUtil;
    @GetMapping("/login")
    public String Login(@RequestParam("id") String id, @RequestParam("pwd") String pwd)
    {
        String key = redisTemplateUtil.getKey("Emp", id);
        emp = (Emp) redisTemplateUtil.findObject("Emp", id);
        if (emp == null)
        {
            int id1 = Integer.parseInt(id);
            emp = empService.findEmpByIdAndPassword(id1, pwd);
            redisTemplateUtil.setObject(key, emp, id);
            return JSON.toJSONString(emp);
        }
        else
        {
            return null;
        }

    }
}
