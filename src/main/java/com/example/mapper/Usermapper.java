package com.example.mapper;

import com.example.entity.bUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Usermapper {

    @Select("select * from buser where bname = #{username} and bpwd = #{password}")
    bUser login(@Param("username") String username,@Param("password") String password);

}
