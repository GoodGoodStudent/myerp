package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Emp;
import com.puhuanyu.erp.myerp.mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  @Description
 *  @ClassName EmpService
 *  @Author 忠哥
 *  @data 2020-01-02 14:31
 */
@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;

    public Emp findEmpByLogin(int id, String password){
        return empMapper.findLogin(id, password);
    }
}
