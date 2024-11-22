package com.example.controller;

import com.example.entity.Result;
import com.example.entity.bUser;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Usercontroller {
    @Autowired
    private UserService userservice;

    //用户登录
    @PostMapping("/login")
    public Result<String> Login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletRequest request) {
        bUser user = userservice.login(username, password);
        if (user != null) {
            String jwt=JwtUtils.generateToken(user.getId());
            return Result.success(jwt);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    //用户注册
    @PostMapping("/register")
    public Result<String> Register(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
        if (userservice.register(username, password)){
            return Result.success();
        }
        return Result.error("注册失败");
    }
}
