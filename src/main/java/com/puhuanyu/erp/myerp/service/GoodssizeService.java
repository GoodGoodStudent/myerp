package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Goodssize;
import com.puhuanyu.erp.myerp.mapper.GoodssizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodssizeService {//商品规格的业务

    @Autowired
    private GoodssizeMapper goodssizeMapper;

    public String doGoodssize(Goodssize goodssize){//添加商品规格
        int id;
        if(goodssizeMapper.findGoodssizeMaxIdByGoodsinfo_id(goodssize.getGoodsinfo().getId()) != null){
            id = Integer.parseInt(goodssizeMapper.findGoodssizeMaxIdByGoodsinfo_id(goodssize.getGoodsinfo().getId())) + 1;
        }else{
            id = goodssize.getGoodsinfo().getId() * 100 + 1;
        }
        goodssize.setId(id);
        if(goodssizeMapper.doGoodssize(goodssize) > 0){
            return "添加成功";
        }
        return "添加失败";
    }

    public String delGoodssizeById(int id){//删除商品规格
        if(goodssizeMapper.delGoodssize(id) > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    public String updateGoodssize(Goodssize goodssize){//修改商品规格
        int newId;
        System.out.println((int)(goodssize.getId()/100));
        System.out.println(goodssize.getGoodsinfo().getId());
        if((int)(goodssize.getId()/100) != goodssize.getGoodsinfo().getId()){
            newId = Integer.parseInt(goodssizeMapper.findGoodssizeMaxIdByGoodsinfo_id(goodssize.getGoodsinfo().getId())) + 1;
        }else {
            newId = goodssize.getId();
        }
        if(goodssizeMapper.updateGoodssize(goodssize,newId) > 0){
            return "修改成功";
        }
        return "修改失败";
    }

    public Goodssize findGoodssizeById(int id){//根据Id找到商品规格
        return goodssizeMapper.findGoodssizeByid(id);
    }

    public List<Goodssize> findAllGoodssizeByGoodsinfo_id(int goodsinfo_id){//根据商品信息Id找到所有规格
        return goodssizeMapper.findGoodssizeByGoodsinfo_id(goodsinfo_id);
    }
}
