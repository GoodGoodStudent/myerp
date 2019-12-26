package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Ranks;
import com.puhuanyu.erp.myerp.mapper.RanksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RanksService {//权限和角色的业务

    @Autowired
    private RanksMapper ranksMapper;

    public String doRanks(Ranks ranks){//添加
        if(ranksMapper.findRanksMaxId() != null){
            ranks.setId(Integer.parseInt(ranksMapper.findRanksMaxId()) + 1);
        }else{
            ranks.setId(1);
        }
        if(ranksMapper.doRanks(ranks) > 0){
            return "添加成功";
        }
        return "添加失败";
    }

    public String delRanks(int id){//删除
        if(ranksMapper.delRanks(id) > 0){
            return "删除成功";
        }
        return "删除失败";
    }

    public String updateRanks(int id, int newRoot_id){//修改某个用户的权限
        if(ranksMapper.updateRanks(id,newRoot_id) > 0){
            return "修改成功";
        }
        return "修改失败";
    }

    public Ranks findRanksById(int id){//查询某个角色的某个权限
        return ranksMapper.findRanksByid(id);
    }

    public List<Ranks> findAllRanksByRank_id(int rank_id){//查询某个角色的所有权限
        return ranksMapper.findRanksByRank_id(rank_id);
    }
}
