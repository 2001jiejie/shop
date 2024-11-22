package com.example.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import com.example.entity.focus;
import com.example.entity.goodstable;
import com.example.entity.orderbase;
import com.example.entity.orderdetail;
@Mapper
public interface Goodsmapper {
    @Select("select * from goodstable")
    List<goodstable> selectAll();

    @SelectProvider(type=GoodsSqlProvider.class, method = "buildDynamicQuery")
    List<goodstable> searchGoods(
            @Param("gname") String gname,
            @Param("gtype") Integer gtype
            );

    @Select("select grprice from goodstable where id=#{goodstable_id}")
    double getPriceByGoodstableId(@Param("goodstable_id") Integer goodstable_id);
    //商品的mapper


    @Insert("insert into focus (bustable_id,goodstable_id) values (#{bustable_id},#{goodstable_id})")
    int insertFocus(@Param("bustable_id") Integer bustable_id, @Param("goodstable_id") Integer goodstable_id);
    
    @Delete("delete from focus where bustable_id=#{bustable_id} and goodstable_id=#{goodstable_id}")
    int deleteFocus(@Param("bustable_id") Integer bustable_id, @Param("goodstable_id") Integer goodstable_id);

    @Select("select * from focus where bustable_id=#{bustable_id}")
    List<focus> selectFocusByBustableId(@Param("bustable_id") Integer bustable_id);
    //收藏表的mapper      
    @Insert("insert into orderbase (bustable_id,amount,status,orderdate) values (#{bustable_id},#{amount},#{status},#{orderdate})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    int insertOrderBase(@Param("bustable_id") Integer bustable_id, @Param("amount") Double amount,
                    @Param("status") Integer status, @Param("orderdate") Date orderdate);
    //订单基础表的mapper，插入订单基础信息，返回订单ID

    @Select("select * from orderbase where bustable_id=#{bustable_id}")
    List<orderbase> selectOrderBaseByBustableId(@Param("bustable_id") Integer bustable_id);

    @Select("select * from orderbase")
    List<orderbase> selectAllOrderBase();

    @Delete("delete from orderbase where orderbase_id=#{orderbase_id}")
    int deleteOrderBase(@Param("orderbase_id") Integer orderbase_id);

    //订单基础表的mapper，删除订单基础信息

    @Insert({
        "<script>",
        "insert into orderdetail (orderbasetable_id, goodstable_id, shoppingnum) values ",
        "<foreach collection='orderDetails' item='detail' separator=','>",
        "(#{detail.orderbasetable_id}, #{detail.goodstable_id}, #{detail.shoppingnum})",
        "</foreach>",
        "</script>"
    })
    int insertOrderDetail(@Param("orderDetails") List<orderdetail> orderDetails);
    //订单详情表的mapper，插入订单详情信息
                }