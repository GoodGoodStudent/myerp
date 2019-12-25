package com.puhuanyu.erp.myerp;

import com.puhuanyu.erp.myerp.bean.Dep;
import com.puhuanyu.erp.myerp.bean.Goodstype;
import com.puhuanyu.erp.myerp.bean.Rank;
import com.puhuanyu.erp.myerp.service.GoodstypeService;
import com.puhuanyu.erp.myerp.service.RankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyerpApplicationTests
{
    @Autowired
    GoodstypeService goodstypeService;

    @Test
    void goodstypeServiceTest()//商品类型业务测试
    {
        System.out.println(goodstypeService.doGoodstype("洗衣机"));
        Goodstype g = goodstypeService.findGoodstypeByid(5);
        System.out.println(g);
        g.setName("吹风机");
        System.out.println(goodstypeService.updateGoodstype(g));
        System.out.println(goodstypeService.findGoodstypeByid(5));
        System.out.println(goodstypeService.delGoodsType(5));
        List<Goodstype> list  = goodstypeService.findAllGoodstype();
        for (Goodstype g1 : list){
            System.out.println(g1.toString());
        }
    }

    @Autowired
    RankService rankService;
    @Test
    public void rankServiceTest(){
        System.out.println(rankService.doRank("人事小哥哥",7));
        Rank rank = rankService.findRankById(7003);
        System.out.println(rank);
        rank.setName("人事大哥哥");
        System.out.println(rankService.updateRank(rank));
        System.out.println(rank);
        System.out.println(rankService.delRank(7003));
        List<Rank> list1 = rankService.findAllRank();
        for(Rank r : list1){
            System.out.println(r);
        }
        List<Rank> list2 = rankService.findAllRankByDep_id(7);
        for(Rank r : list2){
            System.out.println(r);
        }
    }
}
