package com.puhuanyu.erp.myerp;

import com.puhuanyu.erp.myerp.bean.*;
import com.puhuanyu.erp.myerp.service.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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

    @Autowired
    private EmpService empService;
    @Test
    public void test01(){
        //加密方式
        String hashAlgorithmName = "MD5";
        //加密次数
        int hashInteractions = 1024;
        //盐值
        String salt = empService.findEmpById(5001001).getName();
        String salt1 = empService.findEmpById(5002001).getName();
        String salt2 = empService.findEmpById(6001001).getName();
        String salt3 = empService.findEmpById(6001002).getName();
        String salt4 = empService.findEmpById(6001003).getName();
        String salt5 = empService.findEmpById(7001001).getName();
        String salt6 = empService.findEmpById(7002001).getName();
        //原密码
        String pwd = "abc123";
        //将得到的result放到数据库中就行了。
        String result = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt), hashInteractions).toHex();
        String result1 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt1), hashInteractions).toHex();
        String result2 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt2), hashInteractions).toHex();
        String result3 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt3), hashInteractions).toHex();
        String result4 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt4), hashInteractions).toHex();
        String result5 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt5), hashInteractions).toHex();
        String result6 = new SimpleHash(hashAlgorithmName, pwd, ByteSource.Util.bytes(salt6), hashInteractions).toHex();
        System.out.println(result);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
        System.out.println(result6);
    }
}
