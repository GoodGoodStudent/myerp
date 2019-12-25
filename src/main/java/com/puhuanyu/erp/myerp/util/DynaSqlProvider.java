package com.puhuanyu.erp.myerp.util;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Set;

public class DynaSqlProvider {

    public String selectWithParamSql(String table, Map<String,Object> map){
        String sql = new SQL(){
            {
                SELECT("*");
                FROM(table);
                Set<Map.Entry<String,Object>> entries = map.entrySet();

            }
        }.toString();
        return sql;
    }
}
