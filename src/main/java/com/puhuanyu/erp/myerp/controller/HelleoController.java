package com.puhuanyu.erp.myerp.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.puhuanyu.erp.myerp.bean.Roottype;
import com.puhuanyu.erp.myerp.mapper.RootMapper;
import com.puhuanyu.erp.myerp.mapper.RootTypeMapper;
import com.puhuanyu.erp.myerp.bean.Root;
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
import java.util.List;

/**
 *  注意：@RestController是@RequestBody和@Controller的合并，然而@RequestBody修饰的方法返回的是字符串
 *       没有修饰的方法可以跳转到页面
 * */
@Controller
public class HelleoController
{
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
    public String hello(HttpServletResponse response, HttpServletRequest request) {
        /*response.setCharacterEncoding("utf-8");
        String key = "";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            key = redisTemplateUtil.findObject("Roottype", 1);
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
        }*/
        return "index.html";
    }

    @ResponseBody
    @RequestMapping("/world")
    public void world(HttpServletResponse response, HttpServletRequest request) {
        response.setCharacterEncoding("utf-8");
        String key = "";
        PrintWriter out = null;
        try {
            out = response.getWriter();
            key = redisTemplateUtil.findObject("Roottype", null);
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

    @RequestMapping("/wc")
    public void wc(HttpServletResponse response, HttpServletRequest request){
        rootTypeService.updateRootType(new Roottype(6, "仓库管理"));
        redisTemplateUtil.delKey("Roottype");
    }
}
