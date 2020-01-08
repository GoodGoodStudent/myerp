package com.puhuanyu.erp.myerp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puhuanyu.erp.myerp.bean.Emp;
import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootMapper;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import com.puhuanyu.erp.myerp.bean.Root;
import com.puhuanyu.erp.myerp.service.EmpService;
import com.puhuanyu.erp.myerp.service.RootTypeService;
import com.puhuanyu.erp.myerp.util.RedisTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
public class HelleoController
{
    @Autowired
    EmpService empService;
    @Autowired
    Emp emp;
    @Autowired
    private RootMapper rootMapper;
    @Autowired
    private RootTypeService rootTypeService;
    @Autowired
    private Roottype roottype;
    @Autowired
    private RedisTemplateUtil redisTemplateUtil;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/hello")
    public void hello(HttpServletResponse response, HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        String key = "";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            key = redisTemplateUtil.getKey("Roottype", 1);
            if(key == null){
                roottype = rootTypeService.findRootTypeById(1);
                redisTemplateUtil.setObject("Roottype", roottype, 1);
                out.println(JSON.toJSONString(roottype));
            }else {
                out.println(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

    @RequestMapping("/hello1")
    public String hello1() {
        String key = "";
        key = redisTemplateUtil.getKey("Roottype", 1);
        if(key == null){
            roottype = rootTypeService.findRootTypeById(1);
            redisTemplateUtil.setObject("Roottype", roottype, 1);

            return JSON.toJSONString(roottype);
        }else {
            return key;
        }

    }
    @RequestMapping("/world")
    public void world(HttpServletResponse response, HttpServletRequest request){
        response.setCharacterEncoding("utf-8");
        String key = "";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            key = redisTemplateUtil.getKey("Roottype", null);
            if(key == null){
                List<Roottype> list = rootTypeService.findRootTypeAll();
                redisTemplateUtil.setObject("Roottype", list, null);
                out.println(JSONArray.toJSONString(list));
            }else {
                out.println(key);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
    }

    @RequestMapping("/world1")
    public String world1(){
        String key = "";
        key = redisTemplateUtil.getKey("Roottype", null);
        if(key == null){
            List<Roottype> list = rootTypeService.findRootTypeAll();
            redisTemplateUtil.setObject("Roottype", list, null);
            return JSONArray.toJSONString(list);
        }else {
           return key;
        }

    }
    @RequestMapping("/wc")
    public void wc(HttpServletResponse response, HttpServletRequest request){
        rootTypeService.updateRootType(new Roottype(6, "仓库管理"));
        redisTemplateUtil.delKey("Roottype");
    }

}
