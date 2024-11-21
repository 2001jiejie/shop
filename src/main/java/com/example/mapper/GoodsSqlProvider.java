package com.example.mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class GoodsSqlProvider {

    // 构建动态 SQL 查询
    public String buildDynamicQuery(@Param("gname") String gname, @Param("gtype") Integer gtype) {
        return new SQL() {{
            SELECT("*");
            FROM("goodstable");

            // 动态添加 gname 条件
            if (gname != null && !gname.isEmpty()) {
                WHERE("gname LIKE CONCAT('%', #{gname}, '%')");
            }

            // 动态添加 gtype 条件
            if (gtype != null) {
                // 将 gtype 转换为 goodsTypeId，这里假设前端直接传 goodsTypeId
                WHERE("goodstype_id = #{gtype}");
            }
        }}.toString();
    }
}
