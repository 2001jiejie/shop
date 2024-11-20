package com.example.controller;

import com.example.entity.Result;
import com.example.entity.bUser;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class Usercontroller {
    @Autowired
    private UserService userservice;

    //用户登录
    @GetMapping("/login")
    public Result<String> Login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletRequest request){
        bUser user = userservice.login(username, password);
        if (user != null) {
            String jwt = JwtUtils.generateToken(user.getId());
            request.getSession().setAttribute("jwt", jwt);
            request.getSession().setAttribute("user", user);
            return Result.success();
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
