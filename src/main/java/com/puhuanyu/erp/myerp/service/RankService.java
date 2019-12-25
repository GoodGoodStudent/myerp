package com.puhuanyu.erp.myerp.service;

import com.puhuanyu.erp.myerp.bean.Dep;
import com.puhuanyu.erp.myerp.bean.Rank;
import com.puhuanyu.erp.myerp.mapper.RankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {//角色业务类

    @Autowired
    RankMapper rankMapper;
    @Autowired
    Rank rank;
    @Autowired
    Dep dep;

    /**
     * 添加角色，根据部门id找到当前部门角色最大的id+1，如果该部门为新部门，则1*1000+1
     * */
    public String doRank(String name,int dep_id){
        int id = 0;
        if(rankMapper.findMaxRankIdByDep_id(dep_id)!=null){
            id = Integer.parseInt(rankMapper.findMaxRankIdByDep_id(dep_id)) + 1;
        }else{
            id = dep_id*1000 + 1;
        }
        rank.setId(id);
        rank.setName(name);
        dep.setId(dep_id);
        rank.setDep(dep);
        if(rankMapper.doRank(rank)>0){
            return "添加成功";
        }
        return "添加失败";
    }

    public String delRank(int id){//删除角色
        if(rankMapper.delRank(id)>0){
            return "删除成功";
        }
        return "删除失败";
    }

    /**
     * 修改角色,首先获取id的位数，通过id/(位数-1)，得到相关联的首数字，对比首数字和部门id是否一样，
     * 一样，就简单的修改其他属性，否则连Id也修改了
     * */
    public String updateRank(Rank rank){
        int count = 0;
        int newId = 0;
        int id = rank.getId();
        while (id > 0){
            id /= 10;
            count++;
        }
        int title = (int) (rank.getId()/Math.pow(10,count-1));
        if(title != rank.getDep().getId()){
            newId = Integer.parseInt(rankMapper.findMaxRankIdByDep_id(rank.getDep().getId())) + 1;
        }else {
            newId = rank.getId();
        }
        if(rankMapper.update(rank,newId)>0){
            return "修改成功";
        }
        return "修改失败";
    }

    public Rank findRankById(int id){//根据id找角色
        return rankMapper.findRankByid(id);
    }

    public List<Rank> findAllRank(){//查询所以的角色
        return rankMapper.findAllRank();
    }

    public List<Rank> findAllRankByDep_id(int dep_id){//查询某一部门的所有角色
        return rankMapper.findAllRankByDep_id(dep_id);
    }
}
