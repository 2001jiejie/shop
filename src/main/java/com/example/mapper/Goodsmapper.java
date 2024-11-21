package com.example.mapper;

import com.example.entity.goodstable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
@Mapper
public interface Goodsmapper {
    @Select("select * from goodstable")
    List<goodstable> selectAll();

    @SelectProvider(type=GoodsSqlProvider.class, method = "buildDynamicQuery")
    List<goodstable> searchGoods(
            @Param("gname") String gname,
            @Param("gtype") Integer gtype
            );

}
