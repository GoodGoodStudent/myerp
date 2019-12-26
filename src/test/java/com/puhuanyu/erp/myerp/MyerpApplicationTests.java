package com.puhuanyu.erp.myerp;

import com.puhuanyu.erp.myerp.bean.*;
import com.puhuanyu.erp.myerp.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    GoodsinfoService goodsinfoService;
    @Test
    public void goodsinfoService(){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("client_id",101);
        System.out.println(goodsinfoService.findGoodsinfoByCondition(map,0,2));
    }

    @Autowired
    GoodssizeService goodssizeService;
    @Test
    public void goodssizeServiceTest(){
        Goodssize goodssize = goodssizeService.findGoodssizeById(1000201);
        System.out.println(goodssize);
        goodssize.getGoodsinfo().setId(40002);
        goodssize.setPrice(1000000);
        System.out.println(goodssizeService.doGoodssize(goodssize));
        System.out.println(goodssizeService.findAllGoodssizeByGoodsinfo_id(40002));
        Goodssize goodssize1 = goodssizeService.findGoodssizeById(4000203);
        goodssize1.getGoodsinfo().setId(30002);
        goodssize1.setSize("33D");
        System.out.println(goodssizeService.updateGoodssize(goodssize1));
        System.out.println(goodssizeService.findAllGoodssizeByGoodsinfo_id(30002));
        System.out.println(goodssizeService.delGoodssizeById(3000203));
    }

    @Autowired
    RanksService ranksService;
    @Test
    public void RanksServiceTest(){
        Ranks ranks = ranksService.findRanksById(1);
        System.out.println(ranks);
        ranks.getRoot().setId(102);
        System.out.println(ranksService.doRanks(ranks));
        System.out.println(ranksService.findAllRanksByRank_id(1001));
        Ranks ranks1 = ranksService.findRanksById(2);
        int newRoot_id=103;
        System.out.println(ranksService.updateRanks(ranks1.getId(),newRoot_id));
        System.out.println(ranksService.findAllRanksByRank_id(1001));
        System.out.println(ranksService.delRanks(2));
    }
}
