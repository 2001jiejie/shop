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

    @Select("select shoppingnum from  cart where bustable_id = #{buserId} and goodstable_id = #{goodstable_id}")
    Integer getShoppingNum(Integer buserId, Integer goodstable_id);

    @Insert("INSERT INTO cart (bustable_id, goodstable_id, shoppingnum) VALUES (#{buserId}, #{goodstable_id}, #{shoppingnum})")
    void addCart(Integer buserId, Integer goodstable_id, Integer shoppingnum);

    @Update("UPDATE cart SET shoppingnum = #{shoppingnum} WHERE bustable_id = #{buserId} AND goodstable_id = #{goodstable_id}")
    void updateCart(Integer buserId, Integer goodstable_id, Integer shoppingnum);
}
