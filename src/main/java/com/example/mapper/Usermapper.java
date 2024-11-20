package com.example.mapper;

import com.example.entity.bUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Usermapper {

    @Select("select * from buser where bname = #{username} ")
    bUser findByUsername(@Param("username") String username);

    @Insert("INSERT INTO buser (bname, bpwd) VALUES (#{username}, #{password})")
    void register(@Param("username") String username, @Param("password") String password);

    @Select("SELECT COUNT(1) from buser where bname =#{username}")
    int checkUsername(@Param("username") String username);
}
