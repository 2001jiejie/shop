package com.example.mapper;
import org.apache.ibatis.jdbc.SQL;

public class GoodsSqlProvider {

    // 构建动态 SQL 查询
    public String buildDynamicQuery(String gname,String gtype) {
        return new SQL() {{
            SELECT("*");
            FROM("goodstable");
            if (gname != null && !gname.isEmpty()) {
                WHERE("gname LIKE CONCAT('%', #{gname}, '%')");
            }
            if(gtype !=null && !gtype.isEmpty()) {
                Integer goodsTypeId=GoodsTypeMapper.getTypeId(gtype);
                if(goodsTypeId!=null) {
                    WHERE("goodstype_id="+goodsTypeId);
                }
            }
        }}.toString();
    }
}