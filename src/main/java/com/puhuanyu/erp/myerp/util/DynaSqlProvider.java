package com.puhuanyu.erp.myerp.util;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Set;

public class DynaSqlProvider {

    public String selectWithParamSql(String table, Map<String,Object> map,int firstScore, int pageSize){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(table);
                Set<Map.Entry<String,Object>> entries = map.entrySet();
                for(Map.Entry<String,Object> entry : entries){
                    if(entry.getValue() != null){
                        WHERE("" + entry.getKey() + " like " + entry.getValue());
                    }
                }
            }
        }.toString();
        sql = sql + " limit " + firstScore + "," + pageSize;
        System.out.println(sql);
        return sql;
    }
}
