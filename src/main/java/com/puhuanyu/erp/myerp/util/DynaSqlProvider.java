package com.puhuanyu.erp.myerp.util;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Set;

/**
 *  @Description 动态sql和分页的工具类
 *  @ClassName DynaSqlProvider
 *  @Author 忠哥
 *  @data 2019-12-27 14:26
 */
public class DynaSqlProvider {

    /**
     * @Description map里的String存数据库的列名，Object存参数，根据map集合的内容动态的拼装sql，最后再拼接分页
     * @Param [table, map, firstScore, pageSize]
     *      table为传过来的表名，map存列名和数据，firstScore为第几条数据开始，pageSize为每一页应有多少条数据
     * @return java.lang.String
     * @Author 忠哥
     * @Date 2019-12-27 14:27
     */
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
