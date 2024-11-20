package com.example.mapper;

import com.example.entity.aUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface Adminmapper {
    @Select("select * from auser where aname = #{ausername} ")
    aUser findByAdminname(@Param("ausername") String ausername);
}
