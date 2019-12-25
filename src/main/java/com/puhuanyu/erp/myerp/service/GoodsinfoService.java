package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Goodsinfo;
import com.puhuanyu.erp.myerp.mapper.GoodsinfoMapper;
import org.springframework.stereotype.Service;

@Service
public class GoodsinfoService {

    private GoodsinfoMapper goodsinfoMapper;

    /**
     * 添加商品信息，如果goodstype_id下没有最大值的goodsinfo的id，则goodstype_id*10000+1，否则最大值下自增1
     * */
    public String doGoodsinfo(Goodsinfo goodsinfo){
        int id = 0;
        if(goodsinfoMapper.findGoodsinfoMaxIDByGoodstype_id(goodsinfo.getGoodstype().getId()) != null){
            id = Integer.parseInt(goodsinfoMapper.findGoodsinfoMaxIDByGoodstype_id(goodsinfo.getGoodstype().getId()));
        }else{
            id = goodsinfo.getGoodstype().getId() * 10000 + 1;
        }
        goodsinfo.setId(id);
        if(goodsinfoMapper.doGoodsinfo(goodsinfo) > 0){
            return "添加成功";
        }
        return "添加失败";
    }

    public String delGoodsinfo(int id){//删除商品信息
        if(goodsinfoMapper.delGoodsinfo(id) > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    /**
     * 修改商品信息，首先获取id的位数，通过id/(位数-1)，得到相关联的首数字，对比首数字和商品类型id是否一样，
     * 一样，就简单的修改其他属性，否则连Id也修改了
     * */
    public String updateGoodsinfo(Goodsinfo goodsinfo){
        int count = 0;
        int newId = 0;
        int id = goodsinfo.getId();
        while(id > 0){
            id /= 10;
            count++;
        }
        int title = (int)(goodsinfo.getId()/Math.pow(10,count-1));
        if(title != goodsinfo.getGoodstype().getId()){
            newId = Integer.parseInt(goodsinfoMapper.findGoodsinfoMaxIDByGoodstype_id(goodsinfo.getGoodstype().getId())) + 1;
        }else{
            newId = goodsinfo.getId();
        }
        if(goodsinfoMapper.updateGoodsinfo(goodsinfo, newId) > 0){
            return "修改成功";
        }
        return "修改失败";
    }

    public Goodsinfo findGoodsinfoByid(int id){//根据Id找商品信息
        return goodsinfoMapper.findGoodsinfoByid(id);
    }


}
