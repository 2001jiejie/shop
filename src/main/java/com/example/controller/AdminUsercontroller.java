package com.example.controller;

import com.example.entity.Result;
import com.example.entity.aUser;
import com.example.entity.bUser;
import com.example.service.AdminService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class AdminUsercontroller {

    @Autowired
    private AdminService adminservice;

    //管理员登录
    @PostMapping("/adminlogin")
    public Result<String> Login(@RequestParam("ausername") String ausername,
                                @RequestParam("apassword") String apassword,
                                HttpServletRequest request){
        aUser user = adminservice.login(ausername, apassword);
        if (user != null) {
            String jwt = JwtUtils.generateToken(user.getId());
            request.getSession().setAttribute("jwt", jwt);
            request.getSession().setAttribute("user", user);
            return Result.success();
        } else {
            return Result.error("管理员用户名或密码错误");
        }
    }

    //管理员退出
    @GetMapping("/adminlogout")
    public Result<String> logout(){
        return Result.success();
    }
}
