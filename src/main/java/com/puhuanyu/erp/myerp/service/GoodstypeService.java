package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Goodstype;
import com.puhuanyu.erp.myerp.mapper.GoodstypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodstypeService {//商品信息业务类

    @Autowired
    private GoodstypeMapper goodstypeMapper;

    /**
     * 添加商品类型，先查找数据库是否有数据，有数据找到最大id值，自加1，没有的话就初始id为1
     * */
    public String doGoodstype(String name){
        int id = 0;
        if(goodstypeMapper.fingMaxId()!=null){
            id = Integer.parseInt(goodstypeMapper.fingMaxId()) + 1;
        }else{
            id = 1;
        }
        if(goodstypeMapper.doGoodstype(id,name)==0){
            return "添加失败";
        }else {
            return "添加成功！";
        }
    }

    public String delGoodsType(int id){//删除商品类型
        if(goodstypeMapper.delGoodstype(id)==0){
            return "删除失败";
        }
        return "删除成功";
    }

    public String updateGoodstype(Goodstype goodstype){//修改商品类型
        if(goodstypeMapper.updateGoodstype(goodstype)==0){
            return "修改失败";
        }
        return "修改成功";
    }

    public Goodstype findGoodstypeByid(int id){//根据id找商品类型
        return goodstypeMapper.findGoodstypeById(id);
    }

    public List<Goodstype> findAllGoodstype(){//查找所有的商品类型
        return goodstypeMapper.fingAllGoodstype();
    }
}
