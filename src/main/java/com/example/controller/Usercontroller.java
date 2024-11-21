package com.example.controller;

import com.example.context.BaseContext;
import com.example.entity.Result;
import com.example.entity.bUser;
import com.example.service.UserService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class Usercontroller {
    @Autowired
    private UserService userservice;

    //用户登录
    @PostMapping("/login")
    public Result<String> Login(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        bUser user = userservice.login(username, password);
        if (user != null) {
            String jwt=JwtUtils.generateToken(user.getId());
            // 将JWT添加到响应头中
            response.setHeader("Authorization", "Bearer " + jwt);
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

    //用户个人信息
    @GetMapping("/user")
    public Result<bUser> getUser() {
        Integer buserId = BaseContext.getCurrentId();
        bUser buser = userservice.getUserById(buserId);
        if (buser != null) {
            return Result.success(buser);
        } else {
            return Result.error("用户不存在");
        }
    }

    //用户退出
    @GetMapping("/logout")
    public Result<String> logout(){
        return Result.success();
    }

    //添加商品到购物车
    @PostMapping("/addcart")
    public Result<String> addCart(@RequestParam("goodstable_id") Integer goodstable_id){
        Integer buserId = BaseContext.getCurrentId();
        userservice.addCart(goodstable_id,buserId);
        return Result.success();
    }

    //查看用户的购物车


    //购物车购买商品

}
