package com.example.mapper;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import com.example.entity.focus;
import com.example.entity.goodstable;
import com.example.entity.orderbase;
import com.example.entity.orderdetail;
@Mapper
public interface Goodsmapper {
    @Select("select * from goodstable")
    List<goodstable> selectAll();
    //查询所有商品


    //动态查询商品
    @SelectProvider(type=GoodsSqlProvider.class, method = "buildDynamicQuery")
    List<goodstable> searchGoods(
            @Param("gname") String gname,
            @Param("gtype") Integer gtype
            );
    //查询商品价格
    @Select("select grprice from goodstable where id=#{goodstable_id}")
    double getPriceByGoodstableId(@Param("goodstable_id") Integer goodstable_id);



    //查询商品库存
    @Select("select gstore from goodstable where id=#{goodstable_id}")
    int getStoreByGoodstableId(@Param("goodstable_id") Integer goodstable_id);
    
    //更新商品库存
    @Update("update goodstable set gstore=#{gstore} where id=#{goodstable_id}")
    int updateStoreByGoodstableId(@Param("goodstable_id") Integer goodstable_id, @Param("gstore") Integer gstore);

    //通过goodstable_id查询商品
    @Select("select * from goodstable where id = #{goodstableId}")
    goodstable selectByGoodstableId(Integer goodstableId);
    //插入收藏
    @Insert("<script>" +
            "insert into focus (bustable_id, goodstable_id) values " +
            "<foreach collection='goodstableIds' item='goodstableId' separator=','>" +
            "(#{bustable_id}, #{goodstableId})" +
            "</foreach>" +
            "</script>")
    int insertFocusBatch(@Param("bustable_id") Integer bustable_id,
                         @Param("goodstableIds") List<Integer> goodstableIds);
    //删除收藏
    @Delete("delete from focus where bustable_id=#{bustable_id} and goodstable_id=#{goodstable_id}")
    int deleteFocus(@Param("bustable_id") Integer bustable_id, @Param("goodstable_id") Integer goodstable_id);

    
    //查询收藏
    @Select("select * from focus where bustable_id=#{bustable_id}")
    List<focus> selectFocusByBustableId(@Param("bustable_id") Integer bustable_id);

    @Select("SELECT goodstable_id FROM focus WHERE bustable_id = #{userId}")
    List<Integer> selectExistingFocus(@Param("userId") Integer userId);
    
    //插入订单基础信息,返回订单ID
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into orderbase (bustable_id,amount,status,orderdate) values (#{bustable_id},#{amount},#{status},#{orderdate})")
    int insertOrderBase(orderbase order);
    
    
            //查询订单基础信息
    @Select("select * from orderbase where bustable_id=#{bustable_id}")
    List<orderbase> selectOrderBaseByBustableId(@Param("bustable_id") Integer bustable_id);
    
    //查询所有订单基础信息
    @Select("select * from orderbase")
    List<orderbase> selectAllOrderBase();

    //删除订单基础信息
    @Delete("delete from orderbase where orderbase_id=#{orderbase_id}")
    int deleteOrderBase(@Param("orderbase_id") Integer orderbase_id);
    

    
    //插入订单详情信息
    @Insert({
        "<script>",
        "insert into orderdetail (orderbasetable_id, goodstable_id, shoppingnum) values ",
        "<foreach collection='orderDetails' item='detail' separator=','>",
        "(#{detail.orderbasetable_id}, #{detail.goodstable_id}, #{detail.shoppingnum})",
        "</foreach>",
        "</script>"
    })
    int insertOrderDetail(@Param("orderDetails") List<orderdetail> orderDetails);

    
                }