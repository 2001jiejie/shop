package com.example.mapper;

import com.example.entity.bUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface Usermapper {

    @Select("select * from buser where bname = #{username} ")
    bUser findByUsername(@Param("username") String username);

    @Insert("INSERT INTO buser (bname, bpwd) VALUES (#{username}, #{password})")
    void register(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(1) from buser where bname =#{username}")
    int checkUsername(@Param("username") String username);

    @Select("select bname from buser where id = #{id}")
    bUser getUserById(Integer id);

    //查询购物车中是否存在该商品
    @Select("SELECT COUNT(*) FROM cart WHERE bustable_id = #{buserId} AND goodstable_id = #{goodstable_id}")
    Integer checkCartExists(@Param("buserId") Integer buserId, @Param("goodstable_id") Integer goodstable_id);

    //查询购物车中商品数量
    @Select("SELECT shoppingnum FROM cart WHERE bustable_id = #{buserId} AND goodstable_id = #{goodstable_id}")
    Integer getShoppingNum(@Param("buserId") Integer buserId, @Param("goodstable_id") Integer goodstable_id);

    //添加商品到购物车
    @Insert("INSERT INTO cart (bustable_id, goodstable_id, shoppingnum) VALUES (#{buserId}, #{goodstable_id}, #{shoppingnum})")
    void addCart(@Param("buserId") Integer buserId, @Param("goodstable_id") Integer goodstable_id, @Param("shoppingnum") Integer shoppingnum);

    //更新购物车中商品数量
    @Update("UPDATE cart SET shoppingnum = #{shoppingnum} WHERE bustable_id = #{buserId} AND goodstable_id = #{goodstable_id}")
    void updateCart(@Param("buserId") Integer buserId, @Param("goodstable_id") Integer goodstable_id, @Param("shoppingnum") Integer shoppingnum);
}
